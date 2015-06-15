package cn.com.sinosoft.custom.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.model.TCustom;
import cn.com.sinosoft.common.model.TCustomCommunication;
import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.common.util.SqlUtil;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.AttachmentService;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

@Service
public class CustomService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;
	@Resource
	AttachmentService attachmentService;

	/**
	 * 获取客户列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getCustomList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" SELECT * FROM t_custom t WHERE 1=1 ");
		
		if(!StrUtils.isNull(params.get("customIndustry"))){//客户行业
			sb.append(" AND t.industry like ? ");
			values.add("%" + params.get("customIndustry") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("customSource"))){//客户来源
			sb.append(" AND t.source = ? ");
			values.add(params.get("customSource"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("customStatus"))){//客户状态
			sb.append(" AND t.state = ? ");
			values.add(params.get("customStatus"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("companyName"))){//公司名称
			sb.append(" AND t.custom_name like ? ");
			values.add("%" + params.get("companyName") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= t.create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= t.create_time ");
		}
		//客户类型-我的、合作、其他
		TUser user = userUtil.getLoginUser();
		String userId = user.getId();
		
		
		
		sb.append(" ORDER BY t.create_time DESC ");
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 根据id获取客户
	 * @param id
	 * @return
	 */
	public Object getCustomById(String id) {
		Map<String, Object> ret = new HashMap<String, Object>();
		//客户信息
		ret.put("custom", dao.queryById(id, TCustom.class));
		//沟通信息
		ret.put("communs", dao.queryListBySql(
				"select * from t_custom_communication where custom_id = ? order by create_time desc",
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		//附件信息
		ret.put("attachs", dao.queryListBySql(
				"select a.path, a.name, a.upload_time, a.type from t_custom_data t "
				+ "left join t_attachment a on t.attachment_id = a.id "
				+ " where t.custom_id = ? order by a.upload_time desc",
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		
		return ret;
	}

	/**
	 * 编辑客户
	 * @param custom 客户
	 * @param commun 沟通记录
	 * @return
	 */
	@Transactional
	public FormResult edit(TCustom custom, String commun) {
		FormResult ret = new FormResult();
		if(StrUtils.isNull(custom.getId())){//新增
			custom.setId(UUID.randomUUID().toString());
			custom.setCreateTime(new Date());
			custom.setCreateUser(userUtil.getLoginUser().getId());
			custom.setLastUpdateTime(new Date());
			custom.setLastUpdateUser(userUtil.getLoginUser().getId());
			dao.save(custom);
		}else{//更新
			custom.setLastUpdateTime(new Date());
			custom.setLastUpdateUser(userUtil.getLoginUser().getId());
			dao.update(custom);
		}
		//保存沟通记录
		if(!StrUtils.isNull(commun)){
			TCustomCommunication c = new TCustomCommunication();
			c.setId(UUID.randomUUID().toString());
			c.setContent(commun);
			c.setCreateTime(new Date());
			c.setCustomId(custom.getId());
			dao.save(c);
		}
		
		
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(custom.getId());
		return ret;
	}

	/**
	 * 删除客户
	 * @param id
	 * @return
	 */
	@Transactional
	public FormResult del(String id){
		FormResult ret = new FormResult();
		
		//删除客户表
		dao.executeDelOrUpdateSql(
				"delete from t_custom where id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除客户沟通记录
		dao.executeDelOrUpdateSql(
				"delete from t_custom_communication where custom_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除客户附件
		attachmentService.delAttach(dao.queryListBySql(//附件文件
				" select path from t_attachment where id in ("
				+ " select attachment_id from t_custom_data where custom_id = ? ) ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		dao.executeDelOrUpdateSql(//附件表
				"delete from t_attachment where id in ("
				+ " select attachment_id from t_custom_data where custom_id = ? ) ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		dao.executeDelOrUpdateSql(//附件
				"delete from t_custom_data where custom_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除客户职位
		dao.executeDelOrUpdateSql(
				"delete from t_job where custom_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除客户合同
		dao.executeDelOrUpdateSql(
				"delete from t_contract where custom_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除客户推荐简历表
		dao.executeDelOrUpdateSql(
				"delete from t_custom_resume where custom_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("删除客户成功");
		return ret;
	}

	/**
	 * 删除沟通记录
	 * @param id
	 * @return
	 */
	@Transactional
	public FormResult delCommun(String id) {
		FormResult ret = new FormResult();
		dao.executeDelOrUpdateSql(
				"delete from t_custom_communication where id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("删除沟通记录成功");
		return ret;
	}

	/**
	 * 查询客户沟通记录
	 * @param customId
	 * @return
	 */
	public Object getCommuns(String customId) {
		//沟通信息
		return dao.queryListBySql(
						"select * from t_custom_communication where custom_id = ? order by create_time desc",
						new Object[]{customId},
						new Type[]{StringType.INSTANCE});
	}
	
}
