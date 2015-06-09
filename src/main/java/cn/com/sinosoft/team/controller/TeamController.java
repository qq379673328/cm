package cn.com.sinosoft.team.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.core.action.CommonAction;
import cn.com.sinosoft.team.service.TeamService;

@Controller
@RequestMapping("team")
public class TeamController extends CommonAction {
	
	@Resource
	TeamService teamService;

}
