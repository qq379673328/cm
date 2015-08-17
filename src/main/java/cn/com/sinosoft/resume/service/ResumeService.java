package cn.com.sinosoft.resume.service;

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

import cn.com.sinosoft.common.model.TResume;
import cn.com.sinosoft.common.model.TResumeDate;
import cn.com.sinosoft.common.model.TResumeJob;
import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.common.util.SqlUtil;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

@Service
public class ResumeService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;
	
	/**
	 * 获取简历列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getResumeList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		TUser user = userUtil.getLoginUser();
		String userId = user.getId();
		StringBuffer sb = new StringBuffer(
				" SELECT tt.*,ifnull(j.recou, 0) recou from ("
				
				+ " SELECT t.*, "
				+ " (YEAR(CURDATE()) - YEAR(t.birthcay)) - (RIGHT(CURDATE(), 5) < RIGHT(t.birthcay, 5)) AS age , "
				+ " CASE "
				+ "     WHEN t.create_user = ?  "
				+ "     THEN 'my' "
				+ "     ELSE 'other'  "
				+ "   END AS beyond "
				
				+ "  from t_Resume t ) tt"
				+ "  left join ( select r.resume_id, count(1) recou "
					+ "	from t_resume_job r where r.recom_state = '已推荐'"
					+ " group by r.resume_id ) j"
				+ " on tt.id = j.resume_id "
				+ " where 1=1 "
				);
		values.add(userId);
		types.add(StringType.INSTANCE);
		
		if(!StrUtils.isNull(params.get("zone"))){//所在城市
			sb.append(" AND tt.city = ? ");
			values.add(params.get("zone"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("industry"))){//行业
			sb.append(" AND tt.industry = ? ");
			values.add(params.get("industry"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("edu"))){//学历
			sb.append(" AND tt.education = ? ");
			values.add(params.get("edu"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("duty"))){//职业
			sb.append(" AND tt.duty = ? ");
			values.add(params.get("duty"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("sex"))){//性别
			sb.append(" AND tt.sex = ? ");
			values.add(params.get("sex"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("agemin"))){//年龄-最小
			sb.append(" AND tt.age > ? ");
			values.add(params.get("agemin"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("agemax"))){//年龄-最大
			sb.append(" AND tt.age < ? ");
			values.add(params.get("agemax"));
			types.add(StringType.INSTANCE);
		}
		//目前年薪
		String[] yp = handleYearPay(params.get("yearpay"));
		if(yp != null){
			if(yp.length == 1){
				sb.append(" AND tt.year_pay > ? ");//最小
				values.add(yp[0]);
				types.add(StringType.INSTANCE);
			}else{
				sb.append(" AND tt.year_pay >= ? ");//最小
				values.add(yp[0]);
				types.add(StringType.INSTANCE);
				
				sb.append(" AND tt.year_pay <= ? ");//最大
				values.add(yp[1]);
				types.add(StringType.INSTANCE);
			}
		}
		if(!StrUtils.isNull(params.get("createTimeStart"))){//更新日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= tt.last_update_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//更新日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= tt.last_update_time ");
		}
		//简历类型-我的、其他
		if(!StrUtils.isNull(params.get("beyond"))){
			sb.append(" AND tt.beyond = ? ");
			values.add(params.get("beyond"));
			types.add(StringType.INSTANCE);
		}
		
		sb.append(" ORDER BY tt.last_update_time DESC ");
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}
	
	//处理年薪查询条件
	private String[] handleYearPay(String yearPay){
		if(StrUtils.isNull(yearPay)){
			return null;
		}else if(yearPay.endsWith("以下")){
			return new String[]{"0", yearPay.replaceFirst("万以下", "")};
		}else if(yearPay.endsWith("以上")){
			return new String[]{yearPay.replaceFirst("万以上", "")};
		}else{
			String[] sp = yearPay.replace("万", "").split("-");
			return new String[]{sp[0], sp[1]};
		}
	}

	/**
	 * 根据id获取简历
	 * @param id
	 * @return
	 */
	public TResume getResumeById(String id) {
		return dao.queryById(id, TResume.class);
	}

	/**
	 * 编辑简历
	 * @param Resume
	 * @return
	 */
	@Transactional
	public FormResult edit(TResume resume) {
		FormResult ret = new FormResult();
		if(StrUtils.isNull(resume.getId())){//新增
			resume.setId(UUID.randomUUID().toString());
			resume.setCreateTime(new Date());
			resume.setCreateUser(userUtil.getLoginUser().getId());
		}else{
			resume.setLastUpdateTime(new Date());
			resume.setLastUpdateUser(userUtil.getLoginUser().getId());
		}
		
		dao.save(resume);
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(resume.getId());
		return ret;
	}

	/**
	 * 更新头像
	 * @param id
	 * @param iconPath
	 * @return
	 */
	@Transactional
	public FormResult saveIcon(String id, String iconPath) {
		FormResult ret = new FormResult();
		dao.executeDelOrUpdateSql(
				"update t_resume set head_image = ? where id = ? ",
				new Object[]{iconPath, id},
				new Type[]{StringType.INSTANCE, StringType.INSTANCE});
		ret.setMessage("更新头像成功");
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(iconPath);
		return ret;
	}

	/**
	 * 上传附件
	 * @param attas
	 * @return
	 */
	@Transactional
	public FormResult uploadAttas(String attas, String id) {
		FormResult ret = new FormResult();
		//更新附件信息
		dao.executeDelOrUpdateSql("delete from t_resume_date where resume_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		if(!StrUtils.isNull(attas)){
			String[] ids = attas.split(",");
			List<TResumeDate> tAttachments = new ArrayList<TResumeDate>();
			for(String attaid : ids){
				tAttachments.add(new TResumeDate(UUID.randomUUID().toString(), id, attaid));
			}
			dao.getTemplate().saveOrUpdateAll(tAttachments);
		}
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("附件保存成功");
		return ret;
	}

	/**
	 * 获取简历相关信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> getResumeViewById(String id) {
		Map<String, Object> ret = new HashMap<String, Object>();
		
		//简历主表
		TResume resume = dao.queryById(id, TResume.class);
		ret.put("resume", resume);
		if(resume != null){
			//期望
			ret.put("target", dao.queryListBySql(" select * from t_resume_target t where t.resume_id = ? order by t.create_time desc ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//简历附件
			ret.put("resumeDatas", dao.queryListBySql(
					" select att.* from t_resume_date t "
					+ "left join t_attachment att on t.attachment_id = att.id where t.resume_id = ? order by att.upload_time desc ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//教育
			ret.put("resumeEdus", dao.queryListBySql(" select * from t_resume_edu t where t.resume_id = ?  order by t.create_time desc ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//推荐职位
			ret.put("resumeJobs", dao.queryListBySql(" select * from t_resume_job t where t.resume_id = ? order by t.create_time desc ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//语言
			ret.put("resumeLanguages", dao.queryListBySql(" select * from t_resume_language t where t.resume_id = ?  order by t.create_time desc",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//工作经历
			ret.put("resumeWorkhistorys", dao.queryListBySql(" select * from t_resume_workhistory t where t.resume_id = ? order by t.create_time desc ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//沟通记录
			ret.put("resumeComms", dao.queryListBySql(" select * from t_resume_communication t where t.resume_id = ? order by t.create_time desc ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			
			//职位归属-本人？团队？
			ret.put("beyond", isAdmin() ? "my" : getResumeType(id));
		}
		
		return ret;
	}
	
	/**
	 * 简历类型-自己
	 */
	public static final String RESUMETYPE_MY = "my";
	/**
	 * 简历类型-其他
	 */
	public static final String RESUMETYPE_OTHER = "other";
	/**
	 * 获取职位类型-用于决定是否具有权限
	 * @param customId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getResumeType(String resumeId){
		String userId = getLoginUserId();
		List<Map<String, String>> items = dao.queryListBySql(
				" select "
				+ " CASE "
				+ "     WHEN t.create_user = ?  "
				+ "     THEN 'my'  "
				+ "     ELSE 'other'  "
				+ "   END AS beyond"
				+ " from t_resume t where id = ? ",
				new Object[]{userId, resumeId},
				new Type[]{StringType.INSTANCE, StringType.INSTANCE});
		if(items.size() > 0){
			return items.get(0).get("beyond");
		}else{
			return RESUMETYPE_OTHER;
		}
		
	}
	
	/**
	 * 获取某份简历的客户-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getCustomList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		TUser user = userUtil.getLoginUser();
		String userId = user.getId();
		StringBuffer sb = new StringBuffer(
				"select tt.* from ( select j.id,j.state, j.name,j.create_time, "
				+ " (SELECT c.custom_name FROM t_custom c WHERE c.id = j.custom_id) AS custom_name,  "
				+ " CASE "
				+ "     WHEN j.create_user = ?  "
				+ "     THEN 'my'  "
				+ "     ELSE 'other'  "
				+ "   END AS beyond , "
				+ " CASE WHEN r.id IS NULL THEN '0' ELSE '1' END AS ispub  "

				+ " FROM t_job j "
					+ "	LEFT JOIN t_resume_job r "
					+ " ON j.id = r.job_id and r.resume_id = ? ) tt "
				+ " WHERE 1=1 "
				//+ "and ( tt.beyond = 'my') "
				);
		values.add(userId);
		types.add(StringType.INSTANCE);
		
		//简历id
		values.add(params.get("resumeId"));
		types.add(StringType.INSTANCE);
		
		if(!StrUtils.isNull(params.get("companyName"))){//查询客户名
			sb.append(" AND tt.custom_name like ? ");
			values.add("%" + params.get("companyName") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("pubstate"))){//查询-推荐状态
			sb.append(" AND tt.ispub = ? ");
			values.add(params.get("pubstate"));
			types.add(StringType.INSTANCE);
		}
		
		
		sb.append(" ORDER BY tt.ispub desc,tt.create_time DESC ");
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 推荐简历
	 * @param resumeId
	 * @param customId
	 * @return
	 */
	@Transactional
	public FormResult pub(String resumeId, String jobId) {
		FormResult ret = new FormResult();
		//删除旧推荐-防止重复推荐
		dao.executeDelOrUpdateSql(
				"delete from t_resume_job where job_id = ? and resume_id = ? ",
				new Object[]{jobId, resumeId}, 
				new Type[]{StringType.INSTANCE, StringType.INSTANCE});
		
		TResumeJob resumeJob = new TResumeJob();
		//自己创建的简历跟团队简历推荐不一样
		TResume resume = dao.queryById(resumeId, TResume.class);
		//自己创建的直接推荐
		if(resume != null && resume.getCreateUser().equals(getLoginUserId())){
			resumeJob.setRecomState("已推荐");
		}else{//团队推荐的
			resumeJob.setRecomState("待处理");
		}
		
		resumeJob.setId(UUID.randomUUID().toString());
		resumeJob.setJobId(jobId);
		resumeJob.setResumeId(resumeId);
		resumeJob.setRecomTime(new Date());
		resumeJob.setRecomUser(getLoginUserId());
		resumeJob.setCreateUser(getLoginUserId());
		resumeJob.setVerifyState("审核中");
		dao.save(resumeJob);
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}
	
	/**
	 * 取消推荐简历
	 * @param resumeId
	 * @param customId
	 * @return
	 */
	@Transactional
	public FormResult canclePub(String resumeId, String jobId) {
		FormResult ret = new FormResult();
		//删除旧推荐-防止重复推荐
		dao.executeDelOrUpdateSql(
				"delete from t_resume_job where job_id = ? and resume_id = ? ",
				new Object[]{jobId, resumeId}, 
				new Type[]{StringType.INSTANCE, StringType.INSTANCE});
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}

}
