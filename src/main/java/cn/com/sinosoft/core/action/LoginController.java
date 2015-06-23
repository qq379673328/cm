package cn.com.sinosoft.core.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cn.com.sinosoft.common.util.security.Md5PwdEncoder;
import cn.com.sinosoft.core.util.UserUtil;


@Controller
public class LoginController {
	
	@Resource
	UserUtil userUtil;
	
	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, 
			@RequestParam(required=false)String username,
			@RequestParam(required=false)String password) {
		password = new Md5PwdEncoder().encodePassword(password);
		// 获取当前的Subject
	    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);//调用此方法,会执行shiro filter-UserReamlm.java-doGetAuthenticationInfo认证
		} catch (UnknownAccountException uae) {
			request.setAttribute("message_login", "未知账户");
		} catch (IncorrectCredentialsException ice) {
			request.setAttribute("message_login", "密码不正确");
		} catch (LockedAccountException lae) {
			request.setAttribute("message_login", "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			request.setAttribute("message_login", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			request.setAttribute("message_login", "用户名或密码不正确");
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			//登陆一次
			userUtil.getLoginUser();
			//登陆成功跳转到首页
			return "redirect:/";
		} else {
			token.clear();
		}
		return "pages/login";
	}

	/**
	 * 用户登出
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
	}

}
