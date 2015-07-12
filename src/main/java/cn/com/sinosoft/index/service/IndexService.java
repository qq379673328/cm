/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-6-26
 */
package cn.com.sinosoft.index.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;

import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.util.UserUtil;

/**
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-26
 */
@Service
public class IndexService extends SimpleServiceImpl {
	
	private static final int INDEX_COUNT = 5;
	
	@Resource
	UserUtil userUtil;

	/**
	 * 获取用户首页信息
	 * 
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public Object getIndex() {
		Map<String, Object> ret = new HashMap<String, Object>();
		String userId = userUtil.getLoginUser().getId();
		//我的职位-自己录入或者合作职位
		ret.put("myjobs", dao.queryListBySql(
				"select t.*,getDictName(t.team) teams_desc from t_job t " +
					" where t.create_user = ? or ? IN ( "
					+ " 	     SELECT u.id FROM  t_user u WHERE  "
					+ " 	     (u.team IN ( SELECT m.user_id FROM t_job_team m WHERE m.job_id = t.id)) "
					+ " 	     OR "
					+ " 	     (u.id IN ( SELECT m.user_id FROM t_job_team m WHERE m.job_id = t.id)) "
					+ "      )"
					+ " order by t.create_time desc" +
					" limit " + INDEX_COUNT,
				new Object[]{userId, userId},
				new Type[]{StringType.INSTANCE, StringType.INSTANCE}));
		//我的客户-自己录入
		ret.put("mycustoms", dao.queryListBySql(
				"select t.*,getDictName(t.team) teams_desc from t_custom t " +
					" where t.create_user = ? order by t.create_time desc" +
					" limit " + INDEX_COUNT,
				new Object[]{userId},
				new Type[]{StringType.INSTANCE}));
		//我的工作绩效
		ret.put("myscore", dao.queryListBySql(
				  " SELECT  "
				//+ " -- 服务客户 "
				+ " ( SELECT COUNT(1) FROM t_custom t WHERE t.create_user = ? ) AS custom_serv,  "
				//+ " -- 签约客户 "
				+ " ( SELECT COUNT(1) FROM t_custom t WHERE t.create_user = ? AND t.state = '签约运作' ) AS custom_in,  "
				//+ " -- 录入职位 "
				+ " ( SELECT COUNT(1) FROM t_job t WHERE t.create_user = ? ) AS job_in,  "
				//+ " -- 客户评语 "
				+ " ( SELECT COUNT(1) FROM t_custom_communication t WHERE t.create_user = ? ) AS custom_comm,  "
				//+ " -- 新增简历 "
				+ " ( SELECT COUNT(1) FROM t_resume t WHERE t.create_user = ? ) AS resume_add,  "
				//+ " -- 推荐人选 "
				+ " ( SELECT COUNT(1) FROM t_resume_job t WHERE t.create_user = ? ) AS resume_pub, "
				//+ " -- 简历评语 "
				+ " ( SELECT COUNT(1) FROM t_resume_communication t WHERE t.create_user = ? ) AS resume_comm, "
				//+ " -- 合作职位 "
				+ " ( SELECT COUNT(1) FROM t_job t WHERE t.create_user = (SELECT team  FROM t_user WHERE id = ? ) ) AS job_team,  "
				//+ " -- 上岗人数 "
				+ " ( SELECT COUNT(1) FROM t_resume_job t WHERE t.create_user = ? AND t.recom_state = '已上岗') AS job_work  "
				+ " FROM dual ",
				new Object[]{userId, userId, userId, userId, userId, userId, userId, userId, userId},
				new Type[]{StringType.INSTANCE, StringType.INSTANCE, StringType.INSTANCE, StringType.INSTANCE, StringType.INSTANCE
						, StringType.INSTANCE, StringType.INSTANCE, StringType.INSTANCE, StringType.INSTANCE}));
		
		//公告
		ret.put("pubs", dao.queryListBySql(
				" select * from t_pub t "
				+ "where t.state = '已发布' "
				+ "order by t.pub_time desc ",
				new Object[]{},
				new Type[]{}));
		
		return ret;
	}
	
	/**
	 * 工作绩效原始sql
	 SELECT 
		-- 服务客户
		( SELECT COUNT(1) FROM t_custom t WHERE t.create_user = '1' ) AS custom_serv, 
		-- 签约客户
		( SELECT COUNT(1) FROM t_custom t WHERE t.create_user = '1' AND t.state = '已签约' ) AS custom_in, 
		-- 录入职位
		( SELECT COUNT(1) FROM t_job t WHERE t.create_user = '1' ) AS job_in, 
		-- 客户评语
		( SELECT COUNT(1) FROM t_custom_communication t WHERE t.create_user = '1' ) AS custom_comm, 
		-- 新增简历
		( SELECT COUNT(1) FROM t_resume t WHERE t.create_user = '1' ) AS resume_add, 
		-- 推荐人选
		( SELECT COUNT(1) FROM t_resume_pub t WHERE t.create_user = '1' ) AS resume_pub,
		-- 简历评语
		( SELECT COUNT(1) FROM t_resume_comm t WHERE t.create_user = '1' ) AS resume_comm,
		-- 合作职位
		( SELECT COUNT(1) FROM t_job t WHERE t.create_user = (SELECT team  FROM t_user WHERE id = '1' ) ) AS job_team, 
		-- 上岗人数
		( SELECT COUNT(1) FROM t_job t WHERE t.t_resume_pub = '1' AND t.state = '已上岗') AS job_team, 
		FROM dual
	 * 
	 */

}
