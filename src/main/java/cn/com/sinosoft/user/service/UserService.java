package cn.com.sinosoft.user.service;

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

import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.common.util.security.Md5PwdEncoder;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

/**
 * 用户管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Service("userService")
public class UserService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;
	
	/**
	 *	密码修改
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @param newPwdRepeat
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult pwdReset(String oldPwd, String newPwd, String newPwdRepeat) {
		FormResult ret = new FormResult();
		//验证非空
		if(StrUtils.isNull(oldPwd)
			||StrUtils.isNull(newPwd)
			||StrUtils.isNull(newPwdRepeat)){
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("密码不能为空");
			return ret;
		}
		//验证格式
		if(!isPwdValid(oldPwd)
				||!isPwdValid(newPwd)
				||!isPwdValid(newPwdRepeat)){
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("密码只能由6到20位字母、数字、下划线组成");
			return ret;
		}
		//验证新密码是否一致
		if(!newPwd.equals(newPwdRepeat)){
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("两次输入的新密码不一致");
			return ret;
		}
		
		TUser user = userUtil.getLoginUser();
		//验证原始密码
		if(!isPwdCorrect(user.getId(), oldPwd)){
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("原始密码错误");
			return ret;
		}
		dao.executeDelOrUpdateSql(
				"update t_user set password = ? where id = ? ",
				new Object[]{new Md5PwdEncoder().encodePassword(newPwd), user.getId()},
				new Type[]{StringType.INSTANCE, StringType.INSTANCE});
		
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("密码修改成功");
		return ret;
	}
	
	/**
	 * 密码是否合法
	 *
	 * 
	 * @param pwd
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	private boolean isPwdValid(String pwd){
		if(StrUtils.isNull(pwd)){
			return false;
		}
		return pwd.matches("^[\\w\\d_]{6,20}$");
	}
	
	/**
	 * 验证用户密码是否正确
	 * 
	 * @param userId
	 * @param pwd
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	private boolean isPwdCorrect(String userId, String pwd){
		return dao.queryCountBySql(
				"select count(1) from t_user where id = ? and password = ? ",
				new Object[]{userId, new Md5PwdEncoder().encodePassword(pwd)},
				new Type[]{StringType.INSTANCE, StringType.INSTANCE}) == 0 ? false : true;
	}
	
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
		StringBuffer sb = new StringBuffer(" select id, username,is_disabled, " +
				" NAME, sex, duty, entry_date, state, " +
				"id_card, np_place, phone, edu_school, " +
				"edu_date, department, team, user_type, email," +
				" msn, education, professional, positive_date," +
				" leave_date, skills, user_create, create_time," +
				" last_update_user, last_update_time " +
				" from t_user where 1=1 and user_type <> '1' ");
		if(!StrUtils.isNull(params.get("username"))){//用户名
			sb.append(" AND name like ? ");
			values.add("%" + params.get("username") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("loginname"))){//登录名
			sb.append(" AND username like ? ");
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
			user.setUserCreate(loginUser.getId());
			//用户类型
			if(!StrUtils.isNull("userType")){
				//默认用户类型助理
				userType = ("顾问".equals(userType) || "助理".equals(userType)) ? userType : "助理";
			}
			user.setUserType(userType);//用户类型
			user.setPassword(UserUtil.DEFAULT_PWD);//默认密码
			user.setIsDisabled("1");//未锁定
		}else{//更新
			user.setLastUpdateTime(new Date());
			user.setLastUpdateUser(loginUser.getId());
		}
		
		//验证-登录名
		if(StrUtils.isNull(user.getUsername())){
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("登录名不能为空");
			return ret;
		}
		//验证-姓名
		if(StrUtils.isNull(user.getName())){
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("姓名不能为空");
			return ret;
		}
		//验证-登录名重复
		if(!isUpdate && isLoginNameExise(user.getUsername())){
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("登录名已存在");
			return ret;
		}
		
		if(isUpdate){//更新用户时保留原始信息
			TUser oldUser = dao.queryById(user.getId(), TUser.class);
			user.setCreateTime(oldUser.getCreateTime());
			user.setUserCreate(oldUser.getUserCreate());
			user.setUsername(oldUser.getUsername());
			user.setPassword(oldUser.getPassword());
			user.setIsDisabled(oldUser.getIsDisabled());
			dao.getTemplate().merge(user);
			ret.setMessage("用户编辑成功");
		}else{//新增
			dao.getTemplate().save(user);
			ret.setMessage("用户新增成功");
		}
		
		
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
		return dao.queryListBySql("select * from t_user where username = ? ",
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
		List<TUser> users = dao.getTemplate().find("from TUser where username = ? ",
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
			dao.executeDelOrUpdateSql(" update t_user set is_disabled = '0' where id = ? ",
					new Object[]{userId},
					new Type[]{StringType.INSTANCE});
		}
		ret.setSuccess(FormResult.SUCCESS);
		return ret;
	}
	
	/**
	 * 解锁用户
	 * 
	 * @param userId
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult enableUser(String userId) {
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
	 * 重置用户密码
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult pwdReset(String id) {
		FormResult ret = new FormResult();
		if(StrUtils.isNull(id)){
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("密码重置失败，用户为空");
			return ret;
		}
		dao.executeDelOrUpdateSql("update t_user set password = ? where id = ? ",
				new Object[]{UserUtil.DEFAULT_PWD, id},
				new Type[]{StringType.INSTANCE, StringType.INSTANCE});
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("密码重置成功");
		return ret;
	}

}
