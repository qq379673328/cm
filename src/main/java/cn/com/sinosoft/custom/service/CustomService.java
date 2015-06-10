package cn.com.sinosoft.custom.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.model.TCustom;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

@Service
public class CustomService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;

	/**
	 * 获取客户列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getCustomList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" SELECT * from t_custom t where 1=1 ");
		
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 根据id获取客户
	 * @param id
	 * @return
	 */
	public TCustom getCustomById(String id) {
		return dao.queryById(id, TCustom.class);
	}

	/**
	 * 编辑客户
	 * @param custom
	 * @return
	 */
	@Transactional
	public FormResult edit(TCustom custom) {
		FormResult ret = new FormResult();
		boolean isAdd = false;
		if(StrUtils.isNull(custom.getId())){
			isAdd = true;
			custom.setId(UUID.randomUUID().toString());
			custom.setCreateTime(new Date());
			custom.setCreateUser(userUtil.getLoginUser().getId());
			custom.setLastUpdateTime(new Date());
			custom.setLastUpdateUser(userUtil.getLoginUser().getId());
		}else{
			custom.setLastUpdateTime(new Date());
			custom.setLastUpdateUser(userUtil.getLoginUser().getId());
		}
		
		dao.save(custom);
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(custom.getId());
		return ret;
	}

}
