package cn.com.sinosoft.user.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	
	@Resource
	UserService userService;
	
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
		return userService.getUserList(params, pageParams);
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
		return userService.editUser(user);
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
		return userService.delUser(id);
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
		TUser user = userService.getUserById(id);
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
		return userService.disabledUser(id);
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
		return userService.enableUser(id);
	}
	
	/**
	 * 重置用户密码为默认密码
	 *
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("pwdReset")
	@ResponseBody
	public FormResult pwdReset(String id){
		return userService.pwdReset(id);
	}

	/**
	 * 修改密码
	 *
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @param newPwdRepeat
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("pwdChange")
	@ResponseBody
	public FormResult pwdChange(String oldPwd, String newPwd, String newPwdRepeat){
		return userService.pwdReset(oldPwd, newPwd, newPwdRepeat);
	}

	
}
