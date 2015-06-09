package cn.com.sinosoft.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.core.action.CommonAction;
import cn.com.sinosoft.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController extends CommonAction {
	
	@Resource
	UserService userService;

}
