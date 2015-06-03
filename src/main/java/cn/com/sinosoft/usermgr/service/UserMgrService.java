/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-5-29
 */
package cn.com.sinosoft.usermgr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;
import cn.com.sinosoft.usermgr.model.TUser;

/**
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-5-29
 */
@Service(value="userMgrService")
public class UserMgrService extends SimpleServiceImpl{
	
	@Resource
	UserUtil userUtil;

	/**
	 * 获取用户列表
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public Object getUserList(Map<String, String> params, PageParam pageParams) {
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" select id,org_code,login_name,user_name," +
				"comment,department,sex,birthday,user_type,is_disabled" +
				" from t_user where 1=1 and user_type <> '1' ");
		if(!StrUtils.isNull(params.get("username"))){//用户名
			sb.append(" AND user_name like ? ");
			values.add("%" + params.get("username") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("loginname"))){//登录名
			sb.append(" AND login_name like ? ");
			values.add("%" + params.get("loginname") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("usertype"))){//用户类型
			sb.append(" AND user_type = ? ");
			values.add(params.get("usertype"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("isDisabled"))){//用户状态
			sb.append(" AND is_disabled = ? ");
			values.add(params.get("isDisabled"));
			types.add(StringType.INSTANCE);
		}
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 编辑其他用户信息-非本人
	 * 
	 * @param user
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult editUser(TUser user) {
		FormResult ret = new FormResult();
		boolean isUpdate = true;
		
		TUser loginUser = userUtil.getLoginUser();
		if(StrUtils.isNull(user.getId())){//新增
			String userType = "";
			user.setId(UUID.randomUUID().toString());
			isUpdate = false;
			user.setCreateTime(new Date());
			user.setCreateUser(loginUser.getId());
			//用户类型
			String loginUserType = loginUser.getUserType();
			if(UserUtil.USERTYPE_SUPERADMIN.equals(loginUserType)){//超级管理员
				userType = UserUtil.USERTYPE_FORMADMIN;//表单管理员
			}else if(UserUtil.USERTYPE_FORMADMIN.equals(loginUserType)){//表单管理员
				userType = UserUtil.USERTYPE_REPORTUSER;//上报人员
			}else if(UserUtil.USERTYPE_REPORTUSER.equals(loginUserType)){//上报人员
				userType = UserUtil.USERTYPE_REPORTUSER;
			}else{
				userType = UserUtil.USERTYPE_REPORTUSER;
			}
			user.setUserType(userType);
			user.setPassword(UserUtil.DEFAULT_PWD);
		}else{//更新
			user.setUpdateTime(new Date());
			user.setUpdateUser(loginUser.getId());
		}
		
		//验证-登录名
		if(StrUtils.isNull(user.getLoginName())){
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("登录名不能为空");
			return ret;
		}
		//验证-姓名
		if(StrUtils.isNull(user.getUserName())){
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("姓名不能为空");
			return ret;
		}
		//验证-登录名重复
		if(!isUpdate && isLoginNameExise(user.getLoginName())){
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("登录名已存在");
			return ret;
		}
		
		if(isUpdate){//更新用户时保留原始信息
			TUser oldUser = dao.queryById(user.getId(), TUser.class);
			user.setUserType(oldUser.getUserType());
			user.setCreateTime(oldUser.getCreateTime());
			user.setCreateUser(oldUser.getCreateUser());
			user.setLoginName(oldUser.getLoginName());
			user.setPassword(oldUser.getPassword());
			user.setIsDisabled(oldUser.getIsDisabled());
		}
		
		dao.getTemplate().merge(user);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", user.getId());
		ret.setData(data);
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}
	
	/**
	 * 用户名是否存在
	 *
	 * 
	 * @param userName
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public boolean isLoginNameExise(String loginName){
		if(StrUtils.isNull(loginName)){
			return true;
		}
		return dao.queryListBySql("select * from t_user where login_name = ? ",
					new Object[]{loginName},
					new Type[]{StringType.INSTANCE})
			.size() > 0 ? true : false;
	}

	/**
	 * 删除用户
	 * 
	 * @param userId
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult delUser(String userId) {
		FormResult ret = new FormResult();
		if(!StrUtils.isNull(userId)){
			dao.executeDelOrUpdateSql(" delete from t_user where id = ? ",
					new Object[]{userId},
					new Type[]{StringType.INSTANCE});
		}
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}

	/**
	 * 根据id获取用户信息
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public TUser getUserById(String id) {
		return dao.queryById(id, TUser.class);
	}

	/**
	 * 根据登录名获取用户
	 * 
	 * @param loginName
	 * @return 无此用户，则返回null
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@SuppressWarnings({ "unchecked"})
	public TUser findUserByLoginName(String loginName) {
		List<TUser> users = dao.getTemplate().find("from TUser where loginName = ? ",
				loginName);
		if(users.size() == 0){
			return null;
		}else{
			return users.get(0);
		}
	}
	
	/**
	 * 锁定用户
	 * 
	 * @param userId
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult disabledUser(String userId) {
		FormResult ret = new FormResult();
		if(!StrUtils.isNull(userId)){
			dao.executeDelOrUpdateSql(" update t_user set is_disabled = '1' where id = ? ",
					new Object[]{userId},
					new Type[]{StringType.INSTANCE});
		}
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}
	
	/**
	 * 锁定用户
	 * 
	 * @param userId
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult enableUser(String userId) {
		FormResult ret = new FormResult();
		if(!StrUtils.isNull(userId)){
			dao.executeDelOrUpdateSql(" update t_user set is_disabled = '0' where id = ? ",
					new Object[]{userId},
					new Type[]{StringType.INSTANCE});
		}
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}

}
