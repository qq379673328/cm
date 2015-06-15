package cn.com.sinosoft.job.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.model.TJob;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

/**
 * 职位管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Service
public class JobService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;
	
	/**
	 * 获取职位列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getJobList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" SELECT * from t_job t where 1=1 ");
		
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 根据id获取职位
	 * @param id
	 * @return
	 */
	public TJob getJobById(String id) {
		return dao.queryById(id, TJob.class);
	}

	/**
	 * 编辑职位
	 * @param Job
	 * @return
	 */
	@Transactional
	public FormResult edit(TJob job) {
		FormResult ret = new FormResult();
		if(StrUtils.isNull(job.getId())){//新增
			job.setId(UUID.randomUUID().toString());
			job.setCreateUser(userUtil.getLoginUser().getId());
			job.setCreateTime(new Date());
		}else{
			job.setLastUpdateTime(new Date());
			job.setLastUpdateUser(userUtil.getLoginUser().getId());
		}
		
		dao.save(job);
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(job.getId());
		return ret;
	}

}
