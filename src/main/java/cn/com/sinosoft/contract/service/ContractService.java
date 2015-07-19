package cn.com.sinosoft.contract.service;

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

import cn.com.sinosoft.common.model.TContract;
import cn.com.sinosoft.common.model.TContractData;
import cn.com.sinosoft.common.model.TCustom;
import cn.com.sinosoft.common.util.SqlUtil;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

/**
 * 合同管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Service
public class ContractService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;
	
	/**
	 * 获取合同列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getContractList(Map<String, String> params, PageParam pageParams, boolean isMy) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" SELECT t.*, cus.custom_name,"
				+ " getDictName(t.create_user) create_user_desc, "
				+ " IFNULL(atta.cou, 0) attacou "
				+ " from t_contract t "
				+ " left join  t_custom cus on t.custom_id = cus.id "
				+ " left join ( select data.contract_id, count(1) cou from t_contract_data data"
				+ "                group by data.contract_id  ) atta  "
				+ "     on t.id = atta.contract_id "
				+ " where 1=1 ");
		
		//只能查询自己的合同
		if(isMy){
			String userId = userUtil.getLoginUser().getId();
			sb.append(" and t.create_user = ? ");
			values.add(userId);
			types.add(StringType.INSTANCE);
		}
		
		if(!StrUtils.isNull(params.get("companyName"))){//公司名称
			sb.append(" AND cus.custom_name like ? ");
			values.add("%" + params.get("companyName") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("no"))){//合同编号
			sb.append(" AND t.no = ? ");
			values.add(params.get("no"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= t.create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= t.create_time ");
		}
		
		sb.append(" order by t.in_date desc ");
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 根据id获取合同
	 * @param id
	 * @return
	 */
	public Object getContractViewById(String id) {
		Map<String, Object> ret = new HashMap<String, Object>();
		if(StrUtils.isNull(id)){
			return ret;
		}
		//合同信息
		TContract contract = dao.queryById(id, TContract.class);
		ret.put("contract", contract);
		if(contract != null){
			//客户信息
			ret.put("custom", dao.queryById(contract.getCustomId(), TCustom.class));
			//合作职位
			ret.put("jobs", dao.queryListBySql(
					"select t.*,getDictName(t.team) team_desc from t_job t where t.contract_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//附件信息
			ret.put("attachs", dao.queryListBySql(
					"select a.id, a.path, a.name, a.upload_time, a.type from t_contract_data t "
					+ "left join t_attachment a on t.attachment_id = a.id "
					+ " where t.contract_id = ? order by a.upload_time desc",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			
		}
		return ret;
	}
	
	//验证客户是否有未结束的合同
	public boolean hasContractNotEnd(String customId){
		return dao.queryCountBySql(
				"SELECT COUNT(1) FROM t_contract t "
				+ "WHERE t.custom_id = ? "
				+ "AND (t.state = '运作' OR t.state = '暂停')",
				new Object[]{customId},
				new Type[]{StringType.INSTANCE}) > 0 ? true : false;
	}

	/**
	 * 编辑合同
	 * @param Contract
	 * @return
	 */
	@Transactional
	public FormResult edit(TContract contract, String attas, boolean isUpdate) {
		FormResult ret = new FormResult();
		
		boolean isNew = false;
		
		if(StrUtils.isNull(contract.getId())){//新增
			isNew = true;
			if(isNoExist(contract.getNo())){//验证合同编号
				ret.setSuccess(FormResult.ERROR);
				ret.setMessage("合同编号已存在");
				return ret;
			}
			
			//验证是否有未结束的合同-暂停或者运作
			String customId = contract.getCustomId();
			if(hasContractNotEnd(customId)){
				ret.setSuccess(FormResult.ERROR);
				ret.setMessage("客户存在未终止的合同,不能录入新合同");
				return ret;
			}
			
			contract.setId(UUID.randomUUID().toString());
			contract.setInDate(new Date());
			contract.setState("运作");//新增合同只能为运作状态
			contract.setCreateTime(new Date());
			contract.setCreateUser(getLoginUserId());
			dao.save(contract);
		}else{//更新
			if(isNoExist(contract.getNo(), contract.getId())){//验证合同编号
				ret.setSuccess(FormResult.ERROR);
				ret.setMessage("合同编号已存在");
				return ret;
			}
			
			contract.setUpdateTime(new Date());
			contract.setUpdateUser(getLoginUserId());
			dao.update(contract);
		}
		//同步更新客户和职位状态
		if(isUpdate){
			String contractState = contract.getState();
			String customState = null;
			String jobState = null;
			if("运作".equals(contractState)){//运作
				customState = "签约运作";
				jobState = "运作";
			}else if("暂停".equals(contractState)){//暂停
				customState = "签约暂停";
				jobState = "暂停";
			}else if("终止".equals(contractState)){//终止
				customState = "签约终止";
				jobState = "结束";
			}
			if(customState != null){
				dao.executeDelOrUpdateSql("update t_custom set state = ? "
						+ "where id = ? ",
						new Object[]{customState, contract.getCustomId()},
						new Type[]{StringType.INSTANCE, StringType.INSTANCE});
			}
			if(jobState != null && !isNew){
				dao.executeDelOrUpdateSql("update t_job set state = ? "
						+ "where custom_id = ? and contract_id = ? ",
						new Object[]{jobState, contract.getCustomId(), contract.getId()},
						new Type[]{StringType.INSTANCE, StringType.INSTANCE, StringType.INSTANCE});
			}
			
		}
		
		
		//更新附件信息
		dao.executeDelOrUpdateSql("delete from t_contract_data where contract_id = ? ",
				new Object[]{contract.getId()},
				new Type[]{StringType.INSTANCE});
		if(!StrUtils.isNull(attas)){
			String[] ids = attas.split(",");
			List<TContractData> tAttachments = new ArrayList<TContractData>();
			for(String id : ids){
				tAttachments.add(new TContractData(UUID.randomUUID().toString(), contract.getId(), id));
			}
			dao.getTemplate().saveOrUpdateAll(tAttachments);
		}
		
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(contract.getId());
		return ret;
	}
	
	/**
	 * 验证合同编号是否存在-新增用
	 *
	 * 
	 * @param no
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	private boolean isNoExist(String no){
		return dao.queryCountBySql("select count(1) from t_contract where no = ? ",
				new Object[]{no},
				new Type[]{StringType.INSTANCE}) == 0 ? false : true;
	}
	
	/**
	 * 验证合同编号是否存在-编辑用
	 *
	 * 
	 * @param no
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	private boolean isNoExist(String no, String contractId){
		return dao.queryCountBySql("select count(1) from t_contract where no = ? and id <> ? ",
				new Object[]{no, contractId},
				new Type[]{StringType.INSTANCE, StringType.INSTANCE}) == 0 ? false : true;
	}

	/***
	 * 查询入职人选
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getContractResume(Map<String, String> params,
			PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(
				" SELECT  "
				+ " r.id rid, "
				+ " r.name resume_name, "
				+ " j.id jid, "
				+ " j.name job_name, "
				+ " c.no "
				+ "  FROM t_resume_job rj  "
				+ " LEFT JOIN t_resume r ON rj.resume_id = r.id "
				+ " LEFT JOIN t_job j ON rj.job_id = j.id "
				+ " LEFT JOIN t_contract c ON j.contract_id = c.id "
				+ " where 1=1 ");
		
		if(!StrUtils.isNull(params.get("no"))){//合同编号
			sb.append(" AND c.no = ? ");
			values.add(params.get("no"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("jobName"))){//职位名
			sb.append(" AND j.name like ? ");
			values.add("%" + params.get("jobName") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("resumeName"))){//人名
			sb.append(" AND r.name like ? ");
			values.add("%" + params.get("resumeName") + "%");
			types.add(StringType.INSTANCE);
		}
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

}
