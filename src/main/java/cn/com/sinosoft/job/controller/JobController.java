package cn.com.sinosoft.job.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.core.action.CommonAction;
import cn.com.sinosoft.job.service.JobService;

@Controller
@RequestMapping("job")
public class JobController extends CommonAction {

	@Resource
	JobService jobService;
	
}
