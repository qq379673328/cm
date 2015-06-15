package cn.com.sinosoft.resume.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.model.TResume;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

@Service
public class ResumeService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;
	
	/**
	 * 获取简历列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getResumeList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" SELECT * from t_Resume t where 1=1 ");
		
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 根据id获取简历
	 * @param id
	 * @return
	 */
	public TResume getResumeById(String id) {
		return dao.queryById(id, TResume.class);
	}

	/**
	 * 编辑简历
	 * @param Resume
	 * @return
	 */
	@Transactional
	public FormResult edit(TResume resume) {
		FormResult ret = new FormResult();
		if(StrUtils.isNull(resume.getId())){//新增
			resume.setId(UUID.randomUUID().toString());
			resume.setCreateTime(new Date());
			resume.setCreateUser(userUtil.getLoginUser().getId());
		}else{
			resume.setLastUpdateTime(new Date());
			resume.setLastUpdateUser(userUtil.getLoginUser().getId());
		}
		
		dao.save(resume);
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(resume.getId());
		return ret;
	}

}
