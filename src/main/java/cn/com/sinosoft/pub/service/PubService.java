package cn.com.sinosoft.pub.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.type.DateType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.model.TPub;
import cn.com.sinosoft.common.util.SqlUtil;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

/**
 * 公告管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Service
public class PubService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;
	
	/**
	 * 获取公告列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getPubList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" SELECT * from t_pub t where 1=1 ");
		
		if(!StrUtils.isNull(params.get("state"))){//发布状态
			sb.append(" and t.state = ? ");
			values.add(params.get("state"));
			types.add(StringType.INSTANCE);
		}
		
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= t.create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= t.create_time ");
		}
		
		sb.append(" order by t.create_time desc ");
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 根据id获取公告
	 * @param id
	 * @return
	 */
	public TPub getPubById(String id) {
		return dao.queryById(id, TPub.class);
	}

	/**
	 * 编辑公告
	 * @param Pub
	 * @return
	 */
	@Transactional
	public FormResult edit(TPub pub) {
		FormResult ret = new FormResult();
		if(StrUtils.isNull(pub.getId())){//新增
			pub.setId(UUID.randomUUID().toString());
			pub.setCreateTime(new Date());
			pub.setCreateUser(userUtil.getLoginUser().getId());
		}else{
			pub.setLastUpdateTime(new Date());
			pub.setLastUpdateUser(userUtil.getLoginUser().getId());
		}
		
		dao.save(pub);
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(pub.getId());
		return ret;
	}

	/**
	 * 发布公告
	 * @param id
	 * @return
	 */
	@Transactional
	public FormResult pub(String id) {
		FormResult ret = new FormResult();
		dao.executeDelOrUpdateSql(
				"update t_pub set state = '已发布', pub_time = ? where id = ? ",
				new Object[]{new Date(), id},
				new Type[]{TimestampType.INSTANCE, StringType.INSTANCE});
		ret.setMessage("发布成功");
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}
	
	/**
	 * 取消发布公告
	 * @param id
	 * @return
	 */
	@Transactional
	public FormResult canclepub(String id) {
		FormResult ret = new FormResult();
		dao.executeDelOrUpdateSql(
				"update t_pub set state = '未发布', pub_time = null where id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		ret.setMessage("取消发布成功");
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}

}
