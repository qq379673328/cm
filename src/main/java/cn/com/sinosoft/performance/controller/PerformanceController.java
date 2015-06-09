package cn.com.sinosoft.performance.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.core.action.CommonAction;
import cn.com.sinosoft.performance.service.PerformanceService;

@Controller
@RequestMapping("performance")
public class PerformanceController extends CommonAction {
	
	@Resource
	PerformanceService performanceService;

}