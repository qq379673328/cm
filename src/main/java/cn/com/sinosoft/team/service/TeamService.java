package cn.com.sinosoft.team.service;

import org.springframework.stereotype.Service;

import cn.com.sinosoft.core.service.SimpleServiceImpl;

@Service
public class TeamService extends SimpleServiceImpl {

	/**
	 * 获取团队选择的团队
	 * @return
	 */
	public Object getSelectTeams() {
		return dao.queryListBySql(
				"select id,name,duty,user_type from t_user",
				null,
				null);
	}

}
