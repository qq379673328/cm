package cn.com.sinosoft.invoice.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.invoice.service.InvoiceService;

@Controller
@RequestMapping("invoice")
public class InvoiceController extends BaseController{
	
	@Resource
	InvoiceService invoiceService;
	
	@RequestMapping("test")
	@ResponseBody
	public PagingResult list(){
		return new PagingResult();
	}

}
