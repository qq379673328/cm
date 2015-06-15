package cn.com.sinosoft.pub.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.model.TPub;
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

}
