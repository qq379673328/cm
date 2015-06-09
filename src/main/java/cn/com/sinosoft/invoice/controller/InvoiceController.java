package cn.com.sinosoft.invoice.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.invoice.service.InvoiceService;

@Controller
@RequestMapping("invoice")
public class InvoiceController {
	
	@Resource
	InvoiceService invoiceService;

}
