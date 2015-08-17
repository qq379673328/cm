package cn.com.sinosoft.pub.controller;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TPub;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.util.UserUtil;
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
	@Resource
	UserUtil userUtil;
	
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
	@RequiresRoles("管理员")
	public FormResult edit(TPub pub){
		String userId = userUtil.getLoginUser().getId();
		if(StrUtils.isNull(pub.getId())){//新增
			pub.setId(UUID.randomUUID().toString());
			pub.setCreateTime(new Date());
			pub.setCreateUser(userId);
			pub.setLastUpdateTime(new Date());
			pub.setLastUpdateUser(userId);
			pub.setState("未发布");
			return pubService.saveBean(pub);
		}else{//更新
			pub.setState("未发布");
			pub.setLastUpdateTime(new Date());
			pub.setLastUpdateUser(userId);
			return pubService.edit(pub);
		}
	}
	
	/**
	 * 公告-删除公告
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	@RequiresRoles("管理员")
	public FormResult del(String id){
		return pubService.delById(id, "t_pub");
	}
	
	/**
	 * 公告-发布公告
	 * @return
	 */
	@RequestMapping("pubp")
	@ResponseBody
	@RequiresRoles("管理员")
	public FormResult pub(String id){
		return pubService.pub(id);
	}
	
	/**
	 * 公告-删除公告
	 * @return
	 */
	@RequestMapping("canclepub")
	@ResponseBody
	@RequiresRoles("管理员")
	public FormResult canclepub(String id){
		return pubService.canclepub(id);
	}
	
}
