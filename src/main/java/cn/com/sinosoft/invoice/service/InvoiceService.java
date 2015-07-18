package cn.com.sinosoft.invoice.service;

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

import cn.com.sinosoft.common.model.TInvoice;
import cn.com.sinosoft.common.util.SqlUtil;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

/**
 * 发票管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Service
public class InvoiceService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;
	
	/**
	 * 获取发票列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getInvoiceList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(
				" SELECT t.*,getDictName(t.apply_user) apply_user_desc"
				+ "  from t_invoice t left join t_user u on t.apply_user = u.id"
				+ " where 1=1 ");
		
		if(!StrUtils.isNull(params.get("companyName"))){//客户名称
			sb.append(" AND t.custom_name like ? ");
			values.add("%" + params.get("companyName") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("incomestate"))){//回款状态
			sb.append(" AND t.income_state = ? ");
			values.add(params.get("incomestate"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("type"))){//发票类型
			sb.append(" AND t.type = ? ");
			values.add(params.get("type"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("invoicestate"))){//发票状态
			sb.append(" AND t.state = ? ");
			values.add(params.get("invoicestate"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("name"))){//申请人
			sb.append(" AND u.name like ? ");
			values.add("%" + params.get("name") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("timeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("timeStart"), 1, 0) + " <= t.create_time ");
		}
		if(!StrUtils.isNull(params.get("timeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("timeEnd"), 1, 0) + " >= t.create_time ");
		}
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 根据id获取发票
	 * @param id
	 * @return
	 */
	public Map<String, Object> getInvoiceViewById(String id) {
		Map<String, Object> ret = new HashMap<String, Object>();
		
		TInvoice invoice = dao.queryById(id, TInvoice.class);
		ret.put("invoice", invoice);
		
		return ret;
	}

	/**
	 * 编辑发票
	 * @param Invoice
	 * @return
	 */
	@Transactional
	public FormResult edit(TInvoice invoice) {
		FormResult ret = new FormResult();
		if(StrUtils.isNull(invoice.getId())){//新增
			invoice.setId(UUID.randomUUID().toString());
			invoice.setCreateTime(new Date());
			invoice.setCreateUser(userUtil.getLoginUser().getId());
		}
		
		dao.save(invoice);
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(invoice.getId());
		return ret;
	}

}
