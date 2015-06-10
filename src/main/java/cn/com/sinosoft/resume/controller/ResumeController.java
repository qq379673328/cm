package cn.com.sinosoft.resume.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.resume.service.ResumeService;

@Controller
@RequestMapping("resume")
public class ResumeController extends BaseController {
	
	@Resource
	ResumeService resumeService;

}
