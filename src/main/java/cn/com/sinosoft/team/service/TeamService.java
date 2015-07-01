package cn.com.sinosoft.team.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;

import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;

@Service
public class TeamService extends SimpleServiceImpl {

	/**
	 * 获取团队选择的团队-不包含离职的
	 * @param ids 已经选择的团队
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSelectTeams(String ids) {
		return dao.queryListBySql(
				"select id,name,duty,user_type from t_user where state != '离职' and id in " 
						+ createWhereIn(ids) + " ",
				null,
				null);
	}
	private String createWhereIn(String ins){
		if(StrUtils.isNull(ins)){
			return "";
		}
		StringBuffer sb = new StringBuffer(" ( ");
		String[] idSp = ins.split(",");
		for(String id : idSp ){
			sb.append("'" + id + "',");
		}
		sb.toString().endsWith(",");
		return sb.substring(0, sb.length() - 1) + " ) ";
	}

	/**
	 * 团队分页查询
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public PagingResult getTeamList(Map<String, String> params,
			PageParam pageParams) {
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" SELECT "
				+ " t.id,t.username,t.name,t.sex,t.duty,t.entry_date,t.state,t.id_card, "
				+ " t.np_place,t.phone,t.edu_school,t.edu_date,t.department,t.team, "
				+ " getDictName(t.team) team_desc, "
				+ " t.user_type, t.email, t.msn, t.education, t.professional,"
				+ " t.positive_date,t.leave_date,t.skills "
				+ " from t_user t where 1=1 ");
		
		if(!StrUtils.isNull(params.get("name"))){//姓名
			sb.append(" and t.name like ? ");
			values.add("%" + params.get("name") + "%");
			types.add(StringType.INSTANCE);
		}
		
		sb.append(" order by t.last_update_time desc ");
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

}
