package cn.com.sinosoft.user.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TAttachment;
import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.CommonService;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.util.UserUtil;
import cn.com.sinosoft.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	
	@Resource
	UserService userService;
	@Resource
	UserUtil userUtil;
	@Resource
	CommonService commonService;
	
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
	@RequiresRoles("管理员")
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
	@RequiresRoles("管理员")
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
	 * 获取登陆用户信息
	 *
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("getLoginUserInfo")
	@ResponseBody
	public TUser getLoginUserInfo(String id){
		return userUtil.getLoginUser();
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
	@RequiresRoles("管理员")
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
	@RequiresRoles("管理员")
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
	@RequiresRoles("管理员")
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

	/**
	 * 编辑简历信息-上传头像
	 * 
	 * @return
	 */
	@RequestMapping("uploadicon")
	@ResponseBody
	public FormResult uploadicon(String iconId, int x, int y,
			int w, int h) {
		try{
			TAttachment att = commonService.getFile(iconId);
			if (att != null) {
				// 原始图片
				File srcFile = new File(CommonService.ATTAPATH + File.separator
						+ att.getPath());
				// 处理图片
				BufferedImage bi = (BufferedImage)ImageIO.read(srcFile);
				h = Math.min(h, bi.getHeight());
				w = Math.min(w, bi.getWidth());
				if (h <= 0)
					h = bi.getHeight();
				if (w <= 0)
					w = bi.getWidth();
				y = Math.min(Math.max(0, y), bi.getHeight() - h);
				x = Math.min(Math.max(0, x), bi.getWidth() - w);
				BufferedImage bi_cropper = bi.getSubimage(x, y, w, h);
				ImageIO.write(bi_cropper, att.getType(), srcFile);
			}
		}catch(Exception e){
			e.printStackTrace();
			FormResult ret = new FormResult();
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("头像更新失败");
			return ret;
		}
		return userService.saveIcon(iconId);
	}
	
}
