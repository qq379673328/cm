package cn.com.sinosoft.custom.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TCustom;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.custom.service.CustomService;

/**
 * 客户管理
 * @author lizhiyong
 *
 */
@RequestMapping("custom")
@Controller
public class CustomController extends BaseController {
	
	@Resource
	CustomService customService;
	
	/**
	 * 获取客户列表
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public PagingResult getCustomList(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return customService.getCustomList(params, pageParams);
	}
	
	/**
	 * 根据客户id获取客户信息
	 * @return
	 */
	@RequestMapping("getCustomById")
	@ResponseBody
	public TCustom getCustomById(String id){
		return customService.getCustomById(id);
	}
	
	/**
	 * 编辑客户信息-保存或新增
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public FormResult edit(TCustom custom){
		return customService.edit(custom);
	}

}
