package cn.com.sinosoft.performance.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.performance.service.PerformanceService;

/**
 * 绩效管理
 * @author lizhiyong
 *
 */
@Controller
@RequestMapping("performance")
public class PerformanceController extends BaseController{
	
	@Resource
	PerformanceService performanceService;
	
	/**
	 * 获取绩效列表
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	@RequiresRoles("管理员")
	public PagingResult getPerformanceList(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return performanceService.getPerformanceList(params, pageParams);
	}

}
