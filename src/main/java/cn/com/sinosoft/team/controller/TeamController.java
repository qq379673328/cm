package cn.com.sinosoft.team.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.team.service.TeamService;

@Controller
@RequestMapping("team")
public class TeamController extends BaseController {
	
	@Resource
	TeamService teamService;
	
	/**
	 * 获取团队选择的团队
	 * @return
	 */
	@RequestMapping("getSelectTeams")
	@ResponseBody
	public Object getSelectTeams(){
		return teamService.getSelectTeams();
	}

}
