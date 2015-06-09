package cn.com.sinosoft.pub.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.core.action.CommonAction;
import cn.com.sinosoft.pub.service.PubService;

@Controller
@RequestMapping("pub")
public class PubController extends CommonAction {

	@Resource
	PubService pubService;
	
}
