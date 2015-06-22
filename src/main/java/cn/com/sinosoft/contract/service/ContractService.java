package cn.com.sinosoft.contract.service;

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

import cn.com.sinosoft.common.model.TContract;
import cn.com.sinosoft.common.model.TContractData;
import cn.com.sinosoft.common.model.TCustom;
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
		StringBuffer sb = new StringBuffer(" SELECT t.*, cus.custom_name,"
				+ " getDictName(cus.create_user) create_user, "
				+ " IFNULL(atta.cou, 0) attacou "
				+ " from t_contract t "
				+ " left join  t_custom cus on t.custom_id = cus.id "
				+ " left join ( select data.contract_id, count(1) cou from t_contract_data data"
				+ "                group by data.contract_id  ) atta  "
				+ "     on t.id = atta.contract_id "
				+ " where 1=1 ");
		
		//只能查询自己的合同
		String userId = userUtil.getLoginUser().getId();
		sb.append(" and cus.create_user = ? ");
		values.add(userId);
		types.add(StringType.INSTANCE);
		
		sb.append(" order by t.in_date desc ");
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
	public Object getContractViewById(String id) {
		Map<String, Object> ret = new HashMap<String, Object>();
		if(StrUtils.isNull(id)){
			return ret;
		}
		//合同信息
		TContract contract = dao.queryById(id, TContract.class);
		ret.put("contract", contract);
		if(contract != null){
			//客户信息
			ret.put("custom", dao.queryById(contract.getCustomId(), TCustom.class));
			//合作职位
			ret.put("jobs", dao.queryListBySql(
					"select * from t_job where contract_id = ? ",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			//附件信息
			ret.put("attachs", dao.queryListBySql(
					"select a.id, a.path, a.name, a.upload_time, a.type from t_contract_data t "
					+ "left join t_attachment a on t.attachment_id = a.id "
					+ " where t.contract_id = ? order by a.upload_time desc",
					new Object[]{id},
					new Type[]{StringType.INSTANCE}));
			
		}
		return ret;
	}

	/**
	 * 编辑合同
	 * @param Contract
	 * @return
	 */
	@Transactional
	public FormResult edit(TContract contract, String attas) {
		FormResult ret = new FormResult();
		if(StrUtils.isNull(contract.getId())){//新增
			contract.setId(UUID.randomUUID().toString());
			contract.setInDate(new Date());
			dao.save(contract);
		}else{//更新
			dao.update(contract);
		}
		
		//更新附件信息
		dao.executeDelOrUpdateSql("delete from t_contract_data where contract_id = ? ",
				new Object[]{contract.getId()},
				new Type[]{StringType.INSTANCE});
		if(!StrUtils.isNull(attas)){
			String[] ids = attas.split(",");
			List<TContractData> tAttachments = new ArrayList<TContractData>();
			for(String id : ids){
				tAttachments.add(new TContractData(UUID.randomUUID().toString(), contract.getId(), id));
			}
			dao.getTemplate().saveOrUpdateAll(tAttachments);
		}
		
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(contract.getId());
		return ret;
	}

}
