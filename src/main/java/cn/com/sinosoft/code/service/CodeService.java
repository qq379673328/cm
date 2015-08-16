package cn.com.sinosoft.code.service;

import java.util.UUID;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.model.TCode;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;

@Service
public class CodeService extends SimpleServiceImpl {

	/**
	 * 获取码表类型
	 * @return
	 */
	public Object getCodeTypes() {
		return dao.queryListBySql(
				"select * from t_codetype where codetypedesc "
				+ "not in ('客户状态', '合同状态', '职位状态') "
				, null, null);
	}

	/**
	 * 获取某种码表类型码表
	 * @param codetype
	 * @return
	 */
	public Object getCodesByType(String codetype) {
		return dao.queryListBySql("select * from t_code where codetype = ? order by rank ",
				new Object[]{codetype},
				new Type[]{StringType.INSTANCE});
	}

	/**
	 * 编辑码表
	 * @param code
	 * @return
	 */
	@Transactional
	public FormResult editCode(TCode code) {
		FormResult ret = new FormResult();
		if(StrUtils.isNull(code.getId())){//新增
			code.setId(UUID.randomUUID().toString());
			dao.save(code);
		}else{
			dao.update(code);
		}
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("编辑成功");
		return ret;
	}

	/**
	 * 删除码表
	 * @param codeId
	 * @return
	 */
	@Transactional
	public FormResult delCode(String codeId) {
		FormResult ret = new FormResult();
		dao.executeDelOrUpdateSql("delete from t_code where id = ? ",
				new Object[]{codeId},
				new Type[]{StringType.INSTANCE});
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("删除成功");
		return ret;
	}

}
