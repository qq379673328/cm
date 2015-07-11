package cn.com.sinosoft.job.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.model.TCustom;
import cn.com.sinosoft.common.model.TJob;
import cn.com.sinosoft.common.model.TJobCommunication;
import cn.com.sinosoft.common.model.TJobTeam;
import cn.com.sinosoft.common.model.TResumeCommunication;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

/**
 * 职位管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Service
public class JobService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;
	
	/**
	 * 获取职位列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getJobList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" SELECT t.*, cus.custom_name, getDictName(t.team) teams_desc, "
				+ " IFNULL(jobcou.jobcount, 0) jobcount "
				+ " from "
				+ " t_job t left join t_custom cus on t.custom_id = cus.id"
				+ " left join "
				+ "(select job.job_id, count(1) jobcount from t_resume_job job group by job.job_id  ) jobcou "
				+ " on t.id = jobcou.job_id "
				+ " where 1=1 ");
		
		sb.append(" order by t.last_update_time desc ");
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 根据id获取职位
	 * @param id
	 * @return
	 */
	public Object getJobViewById(String id) {
		Map<String, Object> ret = new HashMap<String, Object>();
		if(StrUtils.isNull(id)){
			return ret;
		}
		TJob job = dao.queryById(id, TJob.class);
		ret.put("job", job);
		if(job != null){
			//客户信息
			ret.put("custom", dao.queryById(job.getCustomId(), TCustom.class));
			//向企业投递的简历
			ret.put("pubresumes", dao.queryListBySql(
					"select t.*,j.recom_state,j.verify_state,"
					+ "j.id as jid,j.recom_time, j.verify_time, "
					+ " getDictName(j.recom_user) recom_user_desc"
					+ " from t_resume t,t_resume_job j"
					+ " where t.id = j.resume_id and j.job_id = ?"
					+ " and j.recom_state = '已推荐' ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//内部推荐
			ret.put("inteamresumes", dao.queryListBySql(
					"select t.*,j.recom_state,j.verify_state,"
					+ "j.id as jid,j.recom_time, j.verify_time, "
					+ " getDictName(j.recom_user) recom_user_desc "
					+ " from t_resume t,t_resume_job j"
					+ " where t.id = j.resume_id and j.job_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//职位交流信息
			ret.put("jobcomms", dao.queryListBySql(
					"select t.*,"
					+ " getDictName(t.create_user) create_user_desc "
					+ " from t_job_communication t"
					+ " where t.job_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			
		}
		return ret;
	}
	
	//更新职位简历的状态
	

	/**
	 * 编辑职位
	 * @param Job
	 * @return
	 */
	@Transactional
	public FormResult edit(TJob job) {
		FormResult ret = new FormResult();
		
		//保存执行团队信息
		dao.executeDelOrUpdateSql("delete from t_job_team where job_id = ? ",
				new Object[]{job.getId()},
				new Type[]{StringType.INSTANCE});
		if(!StrUtils.isNull(job.getTeam())){
			List<TJobTeam> ts = new ArrayList<TJobTeam>();
			String teams = job.getTeam();
			String[] tIds = teams.split(",");
			for(String tId : tIds){
				ts.add(
						new TJobTeam(
								UUID.randomUUID().toString(),
								tId,
								job.getId()));
			}
			if(ts.size() > 0){
				dao.batchSave(ts);
			}
		}
		
		if(StrUtils.isNull(job.getId())){//新增
			job.setId(UUID.randomUUID().toString());
			job.setCreateUser(userUtil.getLoginUser().getId());
			job.setCreateTime(new Date());
			job.setLastUpdateTime(new Date());
			job.setLastUpdateUser(userUtil.getLoginUser().getId());
			//绑定最新合同
			String contractId = getCustomNewContract(job.getCustomId());
			if(contractId == null){
				ret.setSuccess(FormResult.ERROR);
				ret.setMessage("职位保存失败，无最新合同");
				return ret;
			}else{
				job.setContractId(contractId);
			}
			dao.save(job);
		}else{
			job.setLastUpdateTime(new Date());
			job.setLastUpdateUser(userUtil.getLoginUser().getId());
			dao.update(job);
		}
		
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(job.getId());
		return ret;
	}
	
	/**
	 * 获取客户最新合同id
	 * @param customId
	 * @return
	 */
	public String getCustomNewContract(String customId){
		List<Map<String, Object>> items = dao.queryListBySql("select * from t_contract where custom_id = ? order by in_date desc limit 1 ",
				new Object[]{customId},
				new Type[]{StringType.INSTANCE});
		if(items.size() == 0){
			return null;
		}else{
			return String.valueOf(items.get(0).get("id"));
		}
	}

	/**
	 * 向企业投递简历
	 * @return
	 */
	@Transactional
	public FormResult pubResume(String rjId) {
		FormResult ret = new FormResult();
		dao.executeDelOrUpdateSql(
				"update t_resume_job set recom_state = ?, recom_time = ? where id = ?",
				new Object[]{"已推荐", new Date(), rjId},
				new Type[]{StringType.INSTANCE, TimestampType.INSTANCE, StringType.INSTANCE});
		ret.setMessage("简历投递成功");
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}
	
	/**
	 * 取消向企业投递简历
	 * @return
	 */
	@Transactional
	public FormResult cancleResume(String rjId) {
		FormResult ret = new FormResult();
		dao.executeDelOrUpdateSql(
				"update t_resume_job set recom_state = ?, recom_time = ?"
				+ ", verify_state = ? , verify_time = ? where id = ?",
				new Object[]{null, null, null, null, rjId},
				new Type[]{StringType.INSTANCE, TimestampType.INSTANCE,
						StringType.INSTANCE, TimestampType.INSTANCE,
						StringType.INSTANCE});
		ret.setMessage("简历取消投递成功");
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}
	
	/**
	 * 审核向企业投递的简历
	 * @return
	 */
	@Transactional
	public FormResult verifyResume(String rjId, String status) {
		FormResult ret = new FormResult();
		dao.executeDelOrUpdateSql(
				"update t_resume_job set "
				+ " verify_state = ?, verify_time = ?, verify_user = ? "
				+ " where id = ?",
				new Object[]{status, new Date(), getLoginUserId() , rjId},
				new Type[]{StringType.INSTANCE, TimestampType.INSTANCE,
						StringType.INSTANCE, StringType.INSTANCE});
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}
	
	/**
	 * 操作职位沟通记录-新增或者编辑
	 * @return
	 */
	@Transactional
	public FormResult editJobComm(String id, String jobId, String content) {
		FormResult ret = new FormResult();
		TJobCommunication comm = new TJobCommunication();
		if(StrUtils.isNull(id)){
			id = UUID.randomUUID().toString();
		}
		comm.setId(id);
		comm.setJobId(jobId);
		comm.setContent(content);
		comm.setCreateTime(new Date());
		comm.setCreateUser(getLoginUserId());
		dao.getTemplate().saveOrUpdate(comm);
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}
	
	/**
	 * 操作职位沟通记录-删除
	 * @return
	 */
	@Transactional
	public FormResult delJobComm(String id) {
		FormResult ret = new FormResult();
		dao.executeDelOrUpdateSql(
				"delete from t_job_communication where id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}

}
