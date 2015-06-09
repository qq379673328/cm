package cn.com.sinosoft.contract.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.contract.service.ContractService;
import cn.com.sinosoft.core.action.CommonAction;

@Controller
@RequestMapping("contract")
public class ContractController extends CommonAction {
	
	@Resource
	ContractService contractService;

}
