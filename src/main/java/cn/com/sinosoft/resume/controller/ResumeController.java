package cn.com.sinosoft.resume.controller;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TResume;
import cn.com.sinosoft.common.model.TResumeCommunication;
import cn.com.sinosoft.common.model.TResumeEdu;
import cn.com.sinosoft.common.model.TResumeLanguage;
import cn.com.sinosoft.common.model.TResumeTarget;
import cn.com.sinosoft.common.model.TResumeWorkhistory;
import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.util.UserUtil;
import cn.com.sinosoft.resume.service.ResumeService;

/**
 * 简历管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Controller
@RequestMapping("resume")
public class ResumeController extends BaseController {
	
	@Resource
	ResumeService resumeService;
	@Resource
	UserUtil userUtil;
	
	/**
	 * 获取简历列表
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public PagingResult getResumeList(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return resumeService.getResumeList(params, pageParams);
	}
	
	/**
	 * 根据简历id获取简历信息
	 * @return
	 */
	@RequestMapping("getResumeById")
	@ResponseBody
	public TResume getResumeById(String id){
		return resumeService.getResumeById(id);
	}
	
	/**
	 * 根据简历id获取简历信息-查看编辑简历
	 * @return
	 */
	@RequestMapping("getResumeViewById")
	@ResponseBody
	public Object getResumeViewById(String id){
		return resumeService.getResumeViewById(id);
	}
	
	/**
	 * 编辑简历信息-编辑基本信息
	 * @return
	 */
	@RequestMapping("saveBaseinfo")
	@ResponseBody
	public FormResult saveBaseinfo(TResume resume){
		TUser user = userUtil.getLoginUser();
		if(StrUtils.isNull(resume.getId())){//新增
			resume.setId(UUID.randomUUID().toString());
			resume.setCreateTime(new Date());
			resume.setCreateUser(user.getId());
			resume.setLastUpdateTime(new Date());
			resume.setLastUpdateUser(user.getId());
			return resumeService.saveBean(resume);
		}else{//更新
			resume.setLastUpdateTime(new Date());
			resume.setLastUpdateUser(user.getId());
			return resumeService.updateBean(resume);
		}
	}
	
	/**
	 * 编辑简历信息-编辑求职意向
	 * @return
	 */
	@RequestMapping("saveTarget")
	@ResponseBody
	public FormResult saveTarget(TResumeTarget target){
		if(StrUtils.isNull(target.getId())){//新增
			target.setId(UUID.randomUUID().toString());
			target.setCreateTime(new Date());
			target.setCreateUser(resumeService.getLoginUserId());
			return resumeService.saveBean(target);
		}else{//更新
			return resumeService.updateBean(target);
		}
	}
	
	/**
	 * 编辑简历信息-编辑工作经历
	 * @return
	 */
	@RequestMapping("saveWorkHistory")
	@ResponseBody
	public FormResult saveWorkHistory(TResumeWorkhistory item){
		if(StrUtils.isNull(item.getId())){//新增
			item.setId(UUID.randomUUID().toString());
			item.setCreateTime(new Date());
			item.setCreateUser(resumeService.getLoginUserId());
			return resumeService.saveBean(item);
		}else{//更新
			return resumeService.updateBean(item);
		}
	}
	/**
	 * 编辑简历信息-删除工作经历
	 * @return
	 */
	@RequestMapping("delWorkHistory")
	@ResponseBody
	public FormResult delWorkHistory(String id){
		return resumeService.delById(id, "t_resume_workhistory");
	}
	
	/**
	 * 编辑简历信息-编辑教育经历
	 * @return
	 */
	@RequestMapping("saveEduHistory")
	@ResponseBody
	public FormResult saveEduHistory(TResumeEdu item){
		if(StrUtils.isNull(item.getId())){//新增
			item.setId(UUID.randomUUID().toString());
			item.setCreateTime(new Date());
			item.setCreateUser(resumeService.getLoginUserId());
			return resumeService.saveBean(item);
		}else{//更新
			return resumeService.updateBean(item);
		}
	}
	/**
	 * 编辑简历信息-删除教育经历
	 * @return
	 */
	@RequestMapping("delEduHistory")
	@ResponseBody
	public FormResult delEduHistory(String id){
		return resumeService.delById(id, "t_resume_edu");
	}
	
	/**
	 * 编辑简历信息-编辑语言
	 * @return
	 */
	@RequestMapping("saveLanguage")
	@ResponseBody
	public FormResult saveLanguage(TResumeLanguage item){
		if(StrUtils.isNull(item.getId())){//新增
			item.setId(UUID.randomUUID().toString());
			item.setCreateTime(new Date());
			item.setCreateUser(resumeService.getLoginUserId());
			return resumeService.saveBean(item);
		}else{//更新
			return resumeService.updateBean(item);
		}
	}
	/**
	 * 编辑简历信息-删除语言经历
	 * @return
	 */
	@RequestMapping("delLanguage")
	@ResponseBody
	public FormResult delLanguage(String id){
		return resumeService.delById(id, "t_resume_language");
	}

	/**
	 * 编辑简历信息-上传头像
	 * @return
	 */
	@RequestMapping("saveIcon")
	@ResponseBody
	public FormResult saveIcon(String id, String iconPath){
		return resumeService.saveIcon(id, iconPath);
	}
	
	/**
	 * 编辑简历信息-上传附件
	 * @return
	 */
	@RequestMapping("uploadAttas")
	@ResponseBody
	public FormResult uploadAttas(String attas, String id){
		return resumeService.uploadAttas(attas, id);
	}
	
	/**
	 * 编辑简历信息-添加沟通记录
	 * @return
	 */
	@RequestMapping("addComm")
	@ResponseBody
	public FormResult addComm(TResumeCommunication comm){
		String userId = userUtil.getLoginUser().getId();
		if(StrUtils.isNull(comm.getId())){//新增
			comm.setId(UUID.randomUUID().toString());
			comm.setCreateTime(new Date());
			comm.setCreateUser(userId);
			return resumeService.saveBean(comm);
		}else{//更新
			return resumeService.updateBean(comm);
		}
	}
	
	/**
	 * 编辑简历信息-删除沟通记录
	 * @return
	 */
	@RequestMapping("delComm")
	@ResponseBody
	public FormResult delComm(String id){
		return resumeService.delById(id, "t_resume_communication");
	}
	
	/**
	 * 简历推荐-推荐简历
	 * @return
	 */
	@RequestMapping("pub")
	@ResponseBody
	public FormResult pub(String resumeId, String jobId){
		return resumeService.pub(resumeId, jobId);
	}
	
	/**
	 * 简历推荐-取消推荐简历
	 * @return
	 */
	@RequestMapping("canclePub")
	@ResponseBody
	public FormResult canclePub(String resumeId, String jobId){
		return resumeService.canclePub(resumeId, jobId);
	}
	
	/**
	 * 获取简历客户列表
	 * @param params
	 * @param pageParams
	 * @return
	 */
	@RequestMapping("getCustomList")
	@ResponseBody
	public PagingResult getCustomList(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return resumeService.getCustomList(params, pageParams);
	}
	
}
