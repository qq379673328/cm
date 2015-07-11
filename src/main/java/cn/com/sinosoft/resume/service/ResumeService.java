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
		StringBuffer sb = new StringBuffer(" SELECT * from t_Resume t where 1=1 ");
		
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
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
			ret.put("target", dao.queryListBySql(" select * from t_resume_target t where t.resume_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//简历附件
			ret.put("resumeDatas", dao.queryListBySql(
					" select att.* from t_resume_date t "
					+ "left join t_attachment att on t.attachment_id = att.id where t.resume_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//期望
			ret.put("target", dao.queryListBySql(" select * from t_resume_target t where t.resume_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//教育
			ret.put("resumeEdus", dao.queryListBySql(" select * from t_resume_edu t where t.resume_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//推荐职位
			ret.put("resumeJobs", dao.queryListBySql(" select * from t_resume_job t where t.resume_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//语言
			ret.put("resumeLanguages", dao.queryListBySql(" select * from t_resume_language t where t.resume_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//工作经历
			ret.put("resumeWorkhistorys", dao.queryListBySql(" select * from t_resume_workhistory t where t.resume_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//沟通记录
			ret.put("resumeComms", dao.queryListBySql(" select * from t_resume_communication t where t.resume_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			
		}
		
		return ret;
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
				+ "      WHEN ? IN ( "
				+ " 	     SELECT u.id FROM  t_user u WHERE  "
				+ " 	     (u.team IN ( SELECT m.user_id FROM t_job_team m WHERE m.job_id = j.id)) "
				+ " 	     OR "
				+ " 	     (u.id IN ( SELECT m.user_id FROM t_job_team m WHERE m.job_id = j.id)) "
				+ "      ) "
				+ "     THEN 'team'  "
				+ "     ELSE 'other'  "
				+ "   END AS beyond , "
				+ " CASE WHEN r.id IS NULL THEN '0' ELSE '1' END AS ispub  "

				+ " FROM t_job j LEFT JOIN t_resume_job r ON j.id = r.job_id ) tt "
				+ " WHERE 1=1 and (tt.beyond = 'team' or tt.beyond = 'my') ");
		values.add(userId);
		types.add(StringType.INSTANCE);
		values.add(userId);
		types.add(StringType.INSTANCE);
		
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
		resumeJob.setId(UUID.randomUUID().toString());
		resumeJob.setJobId(jobId);
		resumeJob.setResumeId(resumeId);
		resumeJob.setRecomState("待处理");
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
