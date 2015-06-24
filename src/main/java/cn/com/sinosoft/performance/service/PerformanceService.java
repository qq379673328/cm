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
		StringBuffer sb = new StringBuffer(" select * from t_user u where 1=1 ");
		
		if(!StrUtils.isNull(params.get("name"))){//用户姓名
			sb.append(" AND u.name like ? ");
			values.add("%" + params.get("name") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("timeStart"))){//日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("timeStart"), 1, 0) + " <= tt.create_time ");
		}
		if(!StrUtils.isNull(params.get("timeEnd"))){//日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("timeEnd"), 1, 0) + " >= tt.create_time ");
		}
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

}
