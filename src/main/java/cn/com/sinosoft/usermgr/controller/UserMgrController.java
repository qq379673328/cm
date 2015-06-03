/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-5-29
 */
package cn.com.sinosoft.usermgr.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.usermgr.model.TUser;
import cn.com.sinosoft.usermgr.service.UserMgrService;

/**
 * 用户管理
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-5-29
 */
@Controller
@RequestMapping("usermgr")
public class UserMgrController extends BaseController {
	
	@Resource
	UserMgrService userMgrService;
	
	/**
	 * 获取用户列表
	 *
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("getUserList")
	@ResponseBody
	public Object getUserList(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return userMgrService.getUserList(params, pageParams);
	}
	
	/**
	 * 编辑用户
	 *
	 * 
	 * @param user
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("editUser")
	@ResponseBody
	public FormResult editUser(TUser user){
		return userMgrService.editUser(user);
	}
	
	/**
	 * 删除用户
	 *
	 * 
	 * @param userId
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("delUser")
	@ResponseBody
	public FormResult delUser(String id){
		return userMgrService.delUser(id);
	}

	/**
	 * 根据id获取用户信息
	 *
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("getUserById")
	@ResponseBody
	public TUser getUserById(String id){
		TUser user = userMgrService.getUserById(id);
		user.setPassword(null);
		return user;
	}
	
	/**
	 * 锁定用户
	 *
	 * 
	 * @param userId
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("disabledUser")
	@ResponseBody
	public FormResult disabledUser(String id){
		return userMgrService.disabledUser(id);
	}
	
	/**
	 * 解锁用户
	 *
	 * 
	 * @param userId
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("enableUser")
	@ResponseBody
	public FormResult enableUser(String id){
		return userMgrService.enableUser(id);
	}
	
}
