/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2014-12-25
 */
package cn.com.sinosoft.core.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.common.util.spring.SpringUtil;
import cn.com.sinosoft.user.service.UserService;

/**
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2014-12-25
 */
public class UserRealm extends AuthorizingRealm {
	/**
	 * 授权
	 * 
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principals);
			SecurityUtils.getSubject().logout();
			return null;
		}
		
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        
        //获取用户类型-角色
        UserService userService = (UserService)SpringUtil.getBean("userService");
        String username = (String)principals.getPrimaryPrincipal();
        TUser user = userService.findUserByLoginName(username);
        authorizationInfo.addRole(user.getUserType());
        return authorizationInfo;
	}

	/**
	 * 认证
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		UserService userService = (UserService)SpringUtil.getBean("userService");
		UsernamePasswordToken uToken = (UsernamePasswordToken)token;
		String username = uToken.getUsername();
        TUser user = userService.findUserByLoginName(username);
        if(user == null) {//用户名或者密码错误
            throw new AuthenticationException();
        }
        if("0".equals(user.getIsDisabled())) {//用户已锁定
            throw new LockedAccountException();
        }
        
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        		username, //用户名
        		user.getPassword(), //密码
                getName()
        );
        return authenticationInfo;
	}

}
