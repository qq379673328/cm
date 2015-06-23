package cn.com.sinosoft.team.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.team.service.TeamService;

@Controller
@RequestMapping("team")
public class TeamController extends BaseController {
	
	@Resource
	TeamService teamService;
	
	/**
	 * 获取团队选择的团队-转码列表
	 * @param ids 已经选择的团队
	 * @return
	 */
	@RequestMapping("getSelectTeams")
	@ResponseBody
	public Object getSelectTeams(String ids){
		return teamService.getSelectTeams(ids);
	}
	
	/**
	 * 获取团队列表
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public PagingResult getTeamList(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return teamService.getTeamList(params, pageParams);
	}

}
