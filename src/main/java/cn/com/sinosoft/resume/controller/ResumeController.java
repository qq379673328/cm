package cn.com.sinosoft.resume.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TResume;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
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
	 * 编辑简历信息-保存或新增
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public FormResult edit(TResume Resume){
		return resumeService.edit(Resume);
	}

}
