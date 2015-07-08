package cn.com.sinosoft.performance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;

import cn.com.sinosoft.common.util.SqlUtil;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;

@Service
public class PerformanceService extends SimpleServiceImpl {
	
	/**
	 * 获取绩效列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getPerformanceList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" select all.* from ( ");
		
		sb.append(" SELECT  ");
		sb.append(" 	u.id,u.name, ");
		sb.append(" 	custom_serv.c custom_serv, ");
		sb.append(" 	custom_in.c custom_in, ");
		sb.append(" 	job_in.c job_in, ");
		sb.append(" 	custom_comm.c custom_comm, ");
		sb.append(" 	resume_add.c resume_add, ");
		sb.append(" 	resume_pub.c resume_pub, ");
		sb.append(" 	resume_comm.c resume_comm, ");
		sb.append(" 	job_team.c job_team, ");
		sb.append(" 	job_work.c job_work ");
		sb.append(" 	 ");
		sb.append(" FROM ");
		sb.append(" 	t_user u ");
		sb.append("   ");
		sb.append("  -- 服务客户 ");
		sb.append("  LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_custom WHERE 1=1 ");
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= create_time ");
		}
		sb.append("   GROUP BY create_user) custom_serv ");
		sb.append(" ON u.id = custom_serv.create_user ");
		sb.append("  ");
		sb.append(" -- 签约客户 ");
		sb.append("  LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_custom WHERE state = '已签约' ");
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= create_time ");
		}
		sb.append("   GROUP BY create_user) custom_in ");
		sb.append(" ON u.id = custom_in.create_user ");
		sb.append("  ");
		sb.append(" -- 录入职位 ");
		sb.append("  LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_job  WHERE 1=1 ");
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= create_time ");
		}
		sb.append("   GROUP BY create_user) job_in ");
		sb.append(" ON u.id = job_in.create_user ");
		sb.append("  ");
		sb.append(" -- 客户评语 ");
		sb.append("  LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_custom_communication  WHERE 1=1 ");
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= create_time ");
		}
		sb.append("   GROUP BY create_user) custom_comm ");
		sb.append(" ON u.id = custom_comm.create_user ");
		sb.append("  ");
		sb.append(" -- 新增简历 ");
		sb.append("  LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_resume  WHERE 1=1 ");
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= create_time ");
		}
		sb.append("   GROUP BY create_user) resume_add ");
		sb.append(" ON u.id = resume_add.create_user ");
		sb.append("  ");
		sb.append(" -- 推荐人选 ");
		sb.append("  LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_resume_job  WHERE 1=1 ");
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= create_time ");
		}
		sb.append("   GROUP BY create_user) resume_pub ");
		sb.append(" ON u.id = resume_pub.create_user ");
		sb.append("  ");
		sb.append(" -- 简历评语 ");
		sb.append("  LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_resume_communication  WHERE 1=1 ");
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= create_time ");
		}
		sb.append("   GROUP BY create_user) resume_comm ");
		sb.append(" ON u.id = resume_comm.create_user ");
		sb.append("  ");
		sb.append(" -- 合作职位 ");
		sb.append("  LEFT JOIN(SELECT uu.team create_user,COUNT(1) AS c FROM t_job j LEFT JOIN t_user uu ON j.create_user = uu.id  WHERE 1=1 ");
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= create_time ");
		}
		sb.append("   GROUP BY uu.team) job_team ");
		sb.append(" ON u.id = job_team.create_user ");
		sb.append("  ");
		sb.append(" -- 上岗人数 ");
		sb.append("  LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_resume_job WHERE recom_state = '已上岗'  ");
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= create_time ");
		}
		sb.append("   GROUP BY create_user) job_work ");
		sb.append(" ON u.id = job_work.create_user ");
		sb.append(" ) all ");
		

		if(!StrUtils.isNull(params.get("name"))){//用户姓名
			sb.append(" AND all.name like ? ");
			values.add("%" + params.get("name") + "%");
			types.add(StringType.INSTANCE);
		}
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	
	/**
	 SELECT 
	u.id,u.name,
	custom_serv.c custom_serv,
	custom_in.c custom_in,
	job_in.c job_in,
	custom_comm.c custom_comm,
	resume_add.c resume_add,
	resume_pub.c resume_pub,
	resume_comm.c resume_comm,
	job_team.c job_team,
	job_work.c job_work
	
FROM
	t_user u
 
 -- 服务客户
 LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_custom WHERE 1=1
  GROUP BY create_user) custom_serv
ON u.id = custom_serv.create_user

-- 签约客户
 LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_custom WHERE state = '已签约'
  GROUP BY create_user) custom_in
ON u.id = custom_in.create_user

-- 录入职位
 LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_job  WHERE 1=1
  GROUP BY create_user) job_in
ON u.id = job_in.create_user

-- 客户评语
 LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_custom_communication  WHERE 1=1
  GROUP BY create_user) custom_comm
ON u.id = custom_comm.create_user

-- 新增简历
 LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_resume  WHERE 1=1
  GROUP BY create_user) resume_add
ON u.id = resume_add.create_user

-- 推荐人选
 LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_resume_job  WHERE 1=1
  GROUP BY create_user) resume_pub
ON u.id = resume_pub.create_user

-- 简历评语
 LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_resume_communication  WHERE 1=1
  GROUP BY create_user) resume_comm
ON u.id = resume_comm.create_user

-- 合作职位
 LEFT JOIN(SELECT uu.team create_user,COUNT(1) AS c FROM t_job j LEFT JOIN t_user uu ON j.create_user = uu.id  WHERE 1=1
  GROUP BY uu.team) job_team
ON u.id = job_team.create_user

-- 上岗人数
 LEFT JOIN(SELECT create_user,COUNT(1) AS c FROM t_resume_job WHERE recom_state = '已上岗' 
  GROUP BY create_user) job_work
ON u.id = job_work.create_user

	 */
	
}
