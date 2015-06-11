/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2014-12-1
 */
package cn.com.sinosoft.core.util;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import cn.com.sinosoft.common.model.TUser;

/**
 * 跟用户相关的一些公共操作
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2014-12-1
 */
@Component("userutil")
public class UserUtil {

	/*用户默认密码*/
	public static final String DEFAULT_PWD = "1eaf60bd1d9d4ffdc335a1207b66e052";//11111111/
	/*session中用户*/
	public static final String SESSION_NAME_USER = "loginuser";
	/*用户类型*/
	public static final String USERTYPE_SUPERADMIN = "1";//超级管理员
	public static final String USERTYPE_FORMADMIN = "2";//表单管理员
	public static final String USERTYPE_REPORTUSER = "3";//上报人员
	
	private TUser getTestUser(){
		return new TUser("1", "admin", "测试用户", "1", "1", "1", "1", new Date(), new Date());
	}
	
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
				TUser user = getTestUser();
				
				session.setAttribute(SESSION_NAME_USER, user);
				return user;
			}else{
				return (TUser)session.getAttribute(SESSION_NAME_USER);
			}
		}else{
			return null;
		}
	}

}
