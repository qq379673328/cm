package cn.com.sinosoft.custom.controller;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TCustom;
import cn.com.sinosoft.common.model.TCustomCommunication;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.util.UserUtil;
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
	@Resource
	UserUtil userUtil;
	
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
	 * 根据客户id获取客户信息-编辑客户信息使用
	 * @return
	 */
	@RequestMapping("getCustomById")
	@ResponseBody
	@RequiresRoles(logical = Logical.OR, value={"管理员", "顾问"})
	public Object getCustomById(String id){
		return customService.getCustomById(id);
	}
	
	/**
	 * 根据客户id获取客户信息-查看客户信息使用
	 * @return
	 */
	@RequestMapping("getCustomViewById")
	@ResponseBody
	public Object getCustomViewById(String id){
		return customService.getCustomViewById(id);
	}
	
	/**
	 * 编辑客户信息-保存或新增
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	@RequiresRoles(logical = Logical.OR, value={"管理员", "顾问"})
	public FormResult edit(TCustom custom, String commun, String attas){
		return customService.edit(custom, commun, attas);
	}

	/**
	 * 删除客户信息
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	@RequiresRoles(logical = Logical.OR, value={"管理员", "顾问"})
	public FormResult del(String id){
		return customService.del(id);
	}

	/**
	 * 删除沟通记录
	 * @return
	 */
	@RequestMapping("delCommun")
	@ResponseBody
	@RequiresRoles(logical = Logical.OR, value={"管理员", "顾问"})
	public FormResult delCommun(String id){
		return customService.delCommun(id);
	}
	
	/**
	 * 加载沟通记录
	 * @return
	 */
	@RequestMapping("getCommuns")
	@ResponseBody
	@RequiresRoles(logical = Logical.OR, value={"管理员", "顾问"})
	public Object getCommuns(String customId){
		return customService.getCommuns(customId);
	}
	
	/**
	 * 添加沟通记录
	 * @param customId
	 * @param comm
	 * @return
	 */
	@RequestMapping("addCommun")
	@ResponseBody
	@RequiresRoles(logical = Logical.OR, value={"管理员", "顾问"})
	public Object addCommun(TCustomCommunication comm){
		String userId = userUtil.getLoginUser().getId();
		if(comm.getId() == null){//添加
			comm.setId(UUID.randomUUID().toString());
			comm.setCreateTime(new Date());
			comm.setCreateUser(userId);
		}else{//修改
			
		}
		return customService.saveBean(comm);
	}
	
}
