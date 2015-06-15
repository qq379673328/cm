package cn.com.sinosoft.contract.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.model.TContract;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

/**
 * 合同管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Service
public class ContractService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;
	
	/**
	 * 获取合同列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getContractList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" SELECT * from t_contract t where 1=1 ");
		
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 根据id获取合同
	 * @param id
	 * @return
	 */
	public TContract getContractById(String id) {
		return dao.queryById(id, TContract.class);
	}

	/**
	 * 编辑合同
	 * @param Contract
	 * @return
	 */
	@Transactional
	public FormResult edit(TContract contract) {
		FormResult ret = new FormResult();
		if(StrUtils.isNull(contract.getId())){//新增
			contract.setId(UUID.randomUUID().toString());
			contract.setInDate(new Date());
		}
		
		dao.save(contract);
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(contract.getId());
		return ret;
	}

}
