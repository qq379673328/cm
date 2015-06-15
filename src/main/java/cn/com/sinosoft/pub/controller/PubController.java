package cn.com.sinosoft.pub.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TPub;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.pub.service.PubService;

/**
 * 公告管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Controller
@RequestMapping("pub")
public class PubController extends BaseController {

	@Resource
	PubService pubService;
	
	/**
	 * 获取公告列表
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public PagingResult getPubList(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return pubService.getPubList(params, pageParams);
	}
	
	/**
	 * 根据公告id获取公告信息
	 * @return
	 */
	@RequestMapping("getPubById")
	@ResponseBody
	public TPub getPubById(String id){
		return pubService.getPubById(id);
	}
	
	/**
	 * 编辑公告信息-保存或新增
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public FormResult edit(TPub Pub){
		return pubService.edit(Pub);
	}
	
}
