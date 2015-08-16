/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2014-12-1
 */
package cn.com.sinosoft.core.util;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.user.service.UserService;

/**
 * 跟用户相关的一些公共操作
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2014-12-1
 */
@Component("userutil")
public class UserUtil {
	
	@Resource
	UserService userService;

	/*用户默认密码*/
	public static final String DEFAULT_PWD = "1eaf60bd1d9d4ffdc335a1207b66e052";//11111111/
	/*session中用户*/
	public static final String SESSION_NAME_USER = "loginuser";
	/*用户类型*/
	/**
	 * 管理员
	 */
	public static final String USERTYPE_SUPERADMIN = "管理员";
	/**
	 * 顾问
	 */
	public static final String USERTYPE_GUWEN = "顾问";
	/**
	 * 助理
	 */
	public static final String USERTYPE_ZHULI = "助理";
	
	/**
	 * 获取当前登陆的用户
	 * 
	 * @param request
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public TUser getLoginUser(){
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser != null){
			Session session = currentUser.getSession();
			if(session.getAttribute(SESSION_NAME_USER) == null){
				String loginName = (String)currentUser.getPrincipal();
				if(loginName == null){
					return null;
				}
				TUser user = userService.findUserByLoginName(loginName);
				session.setAttribute(SESSION_NAME_USER, user);
				return user;
			}else{
				return (TUser)session.getAttribute(SESSION_NAME_USER);
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 是否为管理员
	 * @return
	 */
	public boolean isAdmin(){
		TUser user = getLoginUser();
		return "管理员".equals(user.getUserType()) ? true : false;
	}
	
	/**
	 * 是否为顾问
	 * @return
	 */
	public boolean isGuWen(){
		TUser user = getLoginUser();
		return "顾问".equals(user.getUserType()) ? true : false;
	}

}
