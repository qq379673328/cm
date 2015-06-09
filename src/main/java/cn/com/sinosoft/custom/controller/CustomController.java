package cn.com.sinosoft.custom.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.core.action.CommonAction;
import cn.com.sinosoft.custom.service.CustomService;

@Controller
@RequestMapping("custom")
public class CustomController extends CommonAction {
	
	@Resource
	CustomService customService;

}
