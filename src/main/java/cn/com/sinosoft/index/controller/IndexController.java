/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-6-26
 */
package cn.com.sinosoft.index.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.index.service.IndexService;

/**
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-6-26
 */
@Controller
@RequestMapping("index")
public class IndexController extends BaseController {
	
	@Resource
	IndexService indexService;
	
	/**
	 * 获取用户首页信息
	 *
	 * 
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("getIndex")
	@ResponseBody
	public Object getIndex(){
		return indexService.getIndex();
	}

}
