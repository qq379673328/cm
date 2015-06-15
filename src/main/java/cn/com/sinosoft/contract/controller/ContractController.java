package cn.com.sinosoft.contract.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TContract;
import cn.com.sinosoft.contract.service.ContractService;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;

/**
 * 合同管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Controller
@RequestMapping("contract")
public class ContractController extends BaseController {
	
	@Resource
	ContractService contractService;
	
	/**
	 * 获取合同列表
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public PagingResult getcontractList(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return contractService.getContractList(params, pageParams);
	}
	
	/**
	 * 根据合同id获取合同信息
	 * @return
	 */
	@RequestMapping("getContractById")
	@ResponseBody
	public TContract getcontractById(String id){
		return contractService.getContractById(id);
	}
	
	/**
	 * 编辑合同信息-保存或新增
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public FormResult edit(TContract contract){
		return contractService.edit(contract);
	}
	

}
