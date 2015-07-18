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
import cn.com.sinosoft.common.model.TResumeJob;
import cn.com.sinosoft.common.model.TResumeJobComm;
import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.common.util.SqlUtil;
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
		TUser user = userUtil.getLoginUser();
		String userId = user.getId();
		StringBuffer sb = new StringBuffer(
				" SELECT t.*, cus.custom_name, getDictName(t.team) teams_desc, "
				+ " IFNULL(jobcou.jobcount, 0) jobcount, "
				+ " IFNULL(jobcourec.jobcourec, 0) jobcourec, "
				
				+ " CASE "
				+ "     WHEN t.create_user = ?  "
				+ "     THEN 'my' "
				+ "      WHEN ? IN ( "
				+ " 	     SELECT u.id FROM  t_user u WHERE  "
				+ " 	     (u.team IN ( SELECT m.user_id FROM t_job_team m WHERE m.job_id = t.id)) "
				+ " 	     OR "
				+ " 	     (u.id IN ( SELECT m.user_id FROM t_job_team m WHERE m.job_id = t.id)) "
				+ "      ) "
				+ "     THEN 'team'  "
				+ "     ELSE 'other'  "
				+ "   END AS beyond "
				
				+ " from "
				+ " t_job t left join t_custom cus on t.custom_id = cus.id"
				
				+ " left join "
				+ "(select job.job_id, count(1) jobcount from t_resume_job job group by job.job_id  ) jobcou "
				+ " on t.id = jobcou.job_id "
				
				+ " left join "
				+ "(select jobb.job_id, count(1) jobcourec "
					+ "from t_resume_job jobb where jobb.recom_state = '已推荐' "
					+ " group by jobb.job_id  ) jobcourec "
				+ " on t.id = jobcourec.job_id "
				
				+ " where 1=1 ");
		values.add(userId);
		types.add(StringType.INSTANCE);
		values.add(userId);
		types.add(StringType.INSTANCE);
		
		if(!StrUtils.isNull(params.get("industry"))){//职位行业
			sb.append(" AND t.industry like ? ");
			values.add(params.get("industry"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("workplace"))){//工作地点
			sb.append(" AND t.workplace = ? ");
			values.add(params.get("workplace"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("payMin"))){//职位年薪-最小
			sb.append(" AND t.pay_min > ? ");
			values.add(params.get("payMin"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("payMax"))){//职位年薪-最大
			sb.append(" AND t.pay_max < ? ");
			values.add(params.get("payMax"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("state"))){//职位状态
			sb.append(" AND t.state = ? ");
			values.add(params.get("state"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("name"))){//职位关键词
			sb.append(" AND t.name like ? ");
			values.add("%" + params.get("name") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= t.create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= t.create_time ");
		}
		//客户类型-我的、合作、其他
		if(!StrUtils.isNull(params.get("beyond"))){
			sb.append(" AND t.beyond = ? ");
			values.add(params.get("beyond"));
			types.add(StringType.INSTANCE);
		}
		
		sb.append(" order by t.last_update_time desc ");
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}
	
	/**
	 * 删除职位
	 * @param id
	 * @return
	 */
	@Transactional
	public FormResult del(String id){
		FormResult ret = new FormResult();
		
		//删除职位表
		dao.executeDelOrUpdateSql(
				"delete from t_job where id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除职位沟通记录
		dao.executeDelOrUpdateSql(
				"delete from t_job_communication where job_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除职位团队关联表
		dao.executeDelOrUpdateSql(
				"delete from t_job_team where job_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除职位-简历关联表
		dao.executeDelOrUpdateSql(
				"delete from t_resume_job where job_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("删除职位成功");
		return ret;
	}

	/**
	 * 根据id获取职位
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object getJobViewById(String id) {
		Map<String, Object> ret = new HashMap<String, Object>();
		if(StrUtils.isNull(id)){
			return ret;
		}
		TJob job = dao.queryById(id, TJob.class);
		ret.put("job", job);
		//执行团队
		if(!StrUtils.isNull(job.getTeam())){
			List<Map<String, String>> items = dao.queryListBySql(
					"select getDictName(?) team_desc from dual",
					new Object[]{job.getTeam()},
					new Type[]{StringType.INSTANCE});
			if(items != null && items.size() > 0){
				ret.put("jobTeamDesc", items.get(0).get("team_desc"));
			}
		}
		if(job != null){
			//客户信息
			ret.put("custom", dao.queryById(job.getCustomId(), TCustom.class));
			//内部推荐
			ret.put("inteamresumes", dao.queryListBySql(
					"select t.*,j.recom_state,j.verify_state,"
					+ "j.id as jid,j.recom_time, j.verify_time, "
					+ " getDictName(j.recom_user) recom_user_desc "
					+ " from t_resume t,t_resume_job j"
					+ " where t.id = j.resume_id and j.job_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
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
			//职位交流信息
			ret.put("jobcomms", dao.queryListBySql(
					"select t.*,"
					+ " getDictName(t.create_user) create_user_desc "
					+ " from t_job_communication t"
					+ " where t.job_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//职位归属-本人？团队？
			ret.put("beyond", getJobType(id));
			//职位推荐的处理状态
			ret.put("jobrecstate", dao.queryListBySql(
					" select j.verify_state,count(1) cou from t_resume_job j where j.job_id = ?"
					+ " and j.recom_state = '已推荐' group by j.verify_state ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
		}
		return ret;
	}
	
	/**
	 * 职位类型-自己
	 */
	public static final String JOBTYPE_MY = "my";
	/**
	 * 职位类型-团队
	 */
	public static final String JOBTYPE_TEAM = "team";
	/**
	 * 职位类型-其他
	 */
	public static final String JOBTYPE_OTHER = "other";
	/**
	 * 获取职位类型-用于决定是否具有权限
	 * @param customId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getJobType(String jobId){
		String userId = getLoginUserId();
		List<Map<String, String>> items = dao.queryListBySql(
				" select "
				+ " CASE "
				+ "     WHEN t.create_user = ?  "
				+ "     THEN 'my'  "
				+ "      WHEN ? IN ( "
				+ " 	     SELECT u.id FROM  t_user u WHERE  "
				+ " 	     (u.team IN ( SELECT m.user_id FROM t_job_team m WHERE m.job_id = t.id)) "
				+ " 	     OR "
				+ " 	     (u.id IN ( SELECT m.user_id FROM t_job_team m WHERE m.job_id = t.id)) "
				+ "      ) "
				+ "     THEN 'team'  "
				+ "     ELSE 'other'  "
				+ "   END AS beyond"
				+ " from t_job t where id = ? ",
				new Object[]{userId, userId, jobId},
				new Type[]{StringType.INSTANCE, StringType.INSTANCE, StringType.INSTANCE});
		if(items.size() > 0){
			return items.get(0).get("beyond");
		}else{
			return JOBTYPE_OTHER;
		}
		
	}
	
	/**
	 * 编辑职位
	 * @param Job
	 * @return
	 */
	@Transactional
	public FormResult edit(TJob job) {
		FormResult ret = new FormResult();
		
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

	/**
	 * 加载推荐简历的沟通记录
	 * @param id
	 * @return
	 */
	public Object loadRJComm(String id) {
		return dao.queryListBySql(
				"select * from t_resume_job_comm where resumejob_id = ? ",
				new Object[]{id}, 
				new Type[]{StringType.INSTANCE});
	}
	
	/**
	 * 编辑推荐简历的沟通记录
	 * @param id
	 * @return
	 */
	@Transactional
	public FormResult editRJComm(TResumeJobComm comm) {
		FormResult ret = new FormResult();
		if(StrUtils.isNull(comm.getId())){//新增
			comm.setId(UUID.randomUUID().toString());
			comm.setCreateUser(getLoginUserId());
			comm.setCreateDate(new Date());
			dao.save(comm);
		}else{//更新
			comm.setCreateUser(getLoginUserId());
			comm.setCreateDate(new Date());
			dao.update(comm);
		}
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("编辑沟通记录成功");
		return ret;
	}
	
	/**
	 * 删除推荐简历的沟通记录
	 * @param id
	 * @return
	 */
	@Transactional
	public FormResult delRJComm(String id) {
		FormResult ret = new FormResult();
		dao.executeDelOrUpdateSql(
				"delete from t_resume_job_comm where id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("删除沟通记录成功");
		return ret;
	}

}
