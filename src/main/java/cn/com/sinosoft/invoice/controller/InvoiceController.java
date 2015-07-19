package cn.com.sinosoft.invoice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TInvoice;
import cn.com.sinosoft.contract.service.ContractService;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.util.UserUtil;
import cn.com.sinosoft.invoice.service.InvoiceService;
import cn.com.sinosoft.resume.service.ResumeService;

/**
 * 发票管理
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-15
 */
@Controller
@RequestMapping("invoice")
public class InvoiceController extends BaseController{
	
	@Resource
	InvoiceService invoiceService;
	@Resource
	ContractService contractService;
	@Resource
	UserUtil userUtil;
	
	/**
	 * 获取发票列表
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public PagingResult getInvoiceList(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return invoiceService.getInvoiceList(params, pageParams);
	}
	
	/**
	 * 根据发票id获取发票信息
	 * @return
	 */
	@RequestMapping("getInvoiceViewById")
	@ResponseBody
	public Object getInvoiceViewById(String id){
		return invoiceService.getInvoiceViewById(id);
	}
	
	/**
	 * 编辑发票信息-保存或新增
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public FormResult edit(TInvoice invoice){
		return invoiceService.edit(invoice);
	}
	
	/**
	 * 删除发票信息
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	public FormResult del(String id){
		return invoiceService.delById(id, "t_invoice");
	}

	/**
	 * 用户是否能开发票
	 * @return
	 */
	@RequestMapping("isCanInvoice")
	@ResponseBody
	public Object isCanInvoice(){
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isCanInvoice", invoiceService.isCanInvoice());
		return ret;
	}
	
	/**
	 * 获取客户合同列表
	 * @param params
	 * @param pageParams
	 * @return
	 */
	@RequestMapping("getCustomContract")
	@ResponseBody
	public PagingResult getCustomContract(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return contractService.getContractList(params, pageParams, false);
	}
	
	/**
	 * 获取入职人选
	 * @param params
	 * @param pageParams
	 * @return
	 */
	@RequestMapping("getContractResume")
	@ResponseBody
	public PagingResult getContractResume(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return contractService.getContractResume(params, pageParams);
	}

}
