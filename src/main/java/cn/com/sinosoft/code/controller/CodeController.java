package cn.com.sinosoft.code.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.code.service.CodeService;
import cn.com.sinosoft.common.model.TCode;
import cn.com.sinosoft.core.action.BaseController;

@Controller
@RequestMapping("code")
public class CodeController extends BaseController{
	
	@Resource
	CodeService codeService;
	
	/**
	 * 获取码表类型
	 */
	@RequestMapping("getCodeTypes")
	@ResponseBody
	public Object getCodeTypes(){
		return codeService.getCodeTypes();
	}
	
	/**
	 * 获取某种类型的码表
	 */
	@RequestMapping("getCodesByType")
	@ResponseBody
	public Object getCodesByType(String codetype){
		return codeService.getCodesByType(codetype);
	}
	
	/**
	 * 编辑码表
	 * @param code
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public Object editCode(TCode code){
		return codeService.editCode(code);
	}

	/**
	 * 删除码表编码
	 * @param codeId
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	public Object delCode(String codeId){
		return codeService.delCode(codeId);
	}
	
}
