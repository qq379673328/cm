package cn.com.sinosoft.resume.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.core.action.CommonAction;
import cn.com.sinosoft.resume.service.ResumeService;

@Controller
@RequestMapping("resume")
public class ResumeController extends CommonAction {
	
	@Resource
	ResumeService resumeService;

}
