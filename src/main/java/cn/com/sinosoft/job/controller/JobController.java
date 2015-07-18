package cn.com.sinosoft.job.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TJob;
import cn.com.sinosoft.common.model.TResumeJobComm;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.job.service.JobService;

/**
 * 职位管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Controller
@RequestMapping("job")
public class JobController extends BaseController {

	@Resource
	JobService jobService;
	
	/**
	 * 获取职位列表
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public PagingResult getJobList(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return jobService.getJobList(params, pageParams);
	}
	
	/**
	 * 根据职位id获取职位相关信息
	 * @return
	 */
	@RequestMapping("getJobViewById")
	@ResponseBody
	public Object getJobViewById(String id){
		return jobService.getJobViewById(id);
	}
	
	/**
	 * 编辑职位信息-保存或新增
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public FormResult edit(TJob Job){
		return jobService.edit(Job);
	}
	
	/**
	 * 编辑职位信息-删除
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	public FormResult del(String id){
		return jobService.del(id);
	}
	
	/**
	 * 向企业投递简历
	 * @return
	 */
	@RequestMapping("pubResume")
	@ResponseBody
	public FormResult pubResume(String rjId){
		return jobService.pubResume(rjId);
	}
	
	/**
	 * 取消向企业投递简历
	 * @return
	 */
	@RequestMapping("cancleResume")
	@ResponseBody
	public FormResult cancleResume(String rjId){
		return jobService.cancleResume(rjId);
	}
	
	/**
	 * 审核向企业投递简历
	 * @return
	 */
	@RequestMapping("verifyResume")
	@ResponseBody
	public FormResult verifyResume(String rjId, String status){
		return jobService.verifyResume(rjId, status);
	}
	
	/**
	 * 操作职位沟通记录-新增或者编辑
	 * @return
	 */
	@RequestMapping("editJobComm")
	@ResponseBody
	public FormResult editJobComm(String id, String jobId, String content){
		return jobService.editJobComm(id, jobId, content);
	}
	
	/**
	 * 操作职位沟通记录-删除
	 * @return
	 */
	@RequestMapping("delJobComm")
	@ResponseBody
	public FormResult delJobComm(String id){
		return jobService.delJobComm(id);
	}
	
	/**
	 * 加载推荐简历的沟通记录
	 * @return
	 */
	@RequestMapping("loadRJComm")
	@ResponseBody
	public Object loadRJComm(String id){
		return jobService.loadRJComm(id);
	}
	/**
	 * 编辑推荐简历的沟通记录
	 * @return
	 */
	@RequestMapping("editRJComm")
	@ResponseBody
	public Object editRJComm(TResumeJobComm comm){
		return jobService.editRJComm(comm);
	}
	/**
	 * 删除推荐简历的沟通记录
	 * @return
	 */
	@RequestMapping("delRJComm")
	@ResponseBody
	public Object delRJComm(String id){
		return jobService.delRJComm(id);
	}
	
}
