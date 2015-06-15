package cn.com.sinosoft.team.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;

@Service
public class TeamService extends SimpleServiceImpl {

	/**
	 * 获取团队选择的团队-不包含离职的
	 * @param ids 已经选择的团队
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSelectTeams(String ids) {
		List<Map<String, Object>> users = dao.queryListBySql(
				"select id,name,duty,user_type from t_user where state != '离职' ",
				null,
				null);
		ids = StrUtils.isNull(ids) ? "" : ids;
		String[] sp = ids.split(",");
		Map<String, Boolean> temp = new HashMap<String, Boolean>();
		for(String id : sp){
			temp.put(id, true);
		}
		Map<String, Object> ret = new HashMap<String, Object>();
		List<Map<String, Object>> selectUser = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> unSelectUser = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> user : users){
			if(temp.get(String.valueOf(user.get("id"))) != null){
				//已选择的
				selectUser.add(user);
			}else{
				//未选择的
				unSelectUser.add(user);
			}
			
		}
		ret.put("select", selectUser);
		ret.put("unSelect", unSelectUser);
		return ret;
	}

}
