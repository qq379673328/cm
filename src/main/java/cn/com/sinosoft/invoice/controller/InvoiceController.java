package cn.com.sinosoft.invoice.controller;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TInvoice;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.util.UserUtil;
import cn.com.sinosoft.invoice.service.InvoiceService;

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
		String userId = userUtil.getLoginUser().getId();
		if(StrUtils.isNull(invoice.getId())){//新增
			invoice.setId(UUID.randomUUID().toString());
			invoice.setCreateUser(userId);
			invoice.setCreateTime(new Date());
			return invoiceService.saveBean(invoice);
		}else{
			return invoiceService.updateBean(invoice);
		}
	}


}
