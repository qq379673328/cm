/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-3-4
 */
package cn.com.sinosoft.formdesign.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.formdesign.model.TFormdesignForm;
import cn.com.sinosoft.formdesign.model.TFormdesignFormdata;
import cn.com.sinosoft.formdesign.service.FormDesignService;

/**
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-3-4
 */
@Controller
@RequestMapping("formdesign")
public class FormDesignController extends BaseController {
	
	@Resource
	FormDesignService formDesignService;
	
	/**
	 * 表单添加
	 *
	 * 
	 * @param form
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("add")
	/*@RequiresRoles("admin")*/
	public FormResult add(String title, 
			String content, 
			String contentSrc,
			String fields,
			String id){
		return formDesignService.add(title, content, contentSrc, id, fields);
	}
	
	/**
	 * 表单删除
	 *
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("del")
	/*@RequiresRoles("admin")*/
	public FormResult del(String id){
		return formDesignService.del(id);
	}

	/**
	 * 表单发布
	 *
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("pub")
	/*@RequiresRoles("admin")*/
	public FormResult pub(String id, String type){
		return formDesignService.pub(id, type);
	}
	
	/**
	 * 取消发布
	 *
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("canclepub")
	/*@RequiresRoles("admin")*/
	public FormResult canclePub(String id){
		return formDesignService.cancelPub(id);
	}
	
	/**
	 * 通过id获取表单
	 *
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("getformbyid")
	/*@RequiresRoles("admin")*/
	public FormResult getFormById(String id){
		TFormdesignForm form = formDesignService.getFormById(id);
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("title", form.getTitle());
		item.put("id", form.getId());
		item.put("dtCreate", form.getDtCreate());
		item.put("dtPub", form.getDtPub());
		item.put("dtUpdate", form.getDtUpdate());
		item.put("isPub", form.getIsPub());
		try {
			item.put("content", new String(form.getContent(), "UTF-8"));
			item.put("contentSrc", new String(form.getContentSrc(), "UTF-8"));
			item.put("fields", new String(form.getFields(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("获取表单内容失败-不支持的编码");
		}
		FormResult ret = new FormResult();
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(item);
		return ret;
		
	}
	
	/**
	 * 分页查询表单
	 *
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("getforms")
	/*@RequiresRoles("admin")*/
	public PagingResult getForms(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return formDesignService.getForms(params, pageParams);
	}
	
	/**
	 * 分页查询可上报表单-上报用户
	 *
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("getMyforms")
	/*@RequiresRoles("admin")*/
	public PagingResult getMyForms(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return formDesignService.getMyForms(params, pageParams);
	}
	
	/**
	 * 分页查询表单数据
	 *
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("getformdatas")
	/*@RequiresRoles("admin")*/
	public PagingResult getFormDatas(
			@RequestParam Map<String ,String> params,
			PageParam pageParams){
		return formDesignService.getFormDatas(params, pageParams);
	}
	
	/**
	 * 表单数据录入页面
	 *
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("formdatainput/{formid}")
	/*@RequiresRoles("formreport")*/
	public ModelAndView formdatainput(@PathVariable String formid){
		ModelAndView mav = new ModelAndView("pages/forminput/forminput");
		TFormdesignForm form = formDesignService.getFormById(formid);
		mav.addObject("title", form.getTitle());
		mav.addObject("id", form.getId());
		mav.addObject("dtCreate", form.getDtUpdate());
		mav.addObject("isPub", form.getIsPub());
		try {
			mav.addObject("content", new String(form.getContent(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("获取表单内容失败-不支持的编码");
		}
		return mav;
	}
	
	/**
	 * 表单数据查看页面
	 *
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("/formdataview/{formdataid}")
	/*@RequiresRoles("formreport")*/
	public ModelAndView formdataview(@PathVariable String formdataid){
		ModelAndView mav = new ModelAndView("pages/forminput/forminputview");
		TFormdesignFormdata formdata = formDesignService.getFormDataById(formdataid);
		TFormdesignForm form = formDesignService.getFormById(formdata.getFormId());
		mav.addObject("title", form.getTitle());
		mav.addObject("dtFormCreate", form.getDtCreate());
		mav.addObject("dtFormDataCreate", formdata.getDtCreate());
		try {
			mav.addObject("formdata", new String(formdata.getFormData(), "UTF-8"));
			mav.addObject("content", new String(form.getContent(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("获取表单内容失败-不支持的编码");
		}
		return mav;
	}
	
	/**
	 * 表单数据录入-保存
	 *
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("formdatasave")
	/*@RequiresRoles("formreport")*/
	public ModelAndView formdatasave(@RequestParam Map<String, Object> data, String formid){
		String dataid = formDesignService.formdatasave(data, formid);
		if(dataid == null){//保存失败
			ModelAndView mav = new ModelAndView("pages/forminput/forminputerror");
			mav.addObject("info", "操作失败,服务器异常");
			return mav;
		}else{
			return new ModelAndView("redirect:/formdesign/formdataview/" + dataid);
		}
	}
	
	/**
	 * 表单数据删除
	 *
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("formdatadel")
	/*@RequiresRoles("admin")*/
	public FormResult formDataDel(String id){
		return formDesignService.formDataDel(id);
	}
	
	/**
	 * 导出excel-单个表单所有数据
	 * 
	 * @param response
	 * @param tableJson
	 * @param title
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("exportFormData2Excel/{formId}")
	/*@RequiresRoles("admin")*/
	public void exportExcel(HttpServletResponse response, @PathVariable String formId){
		try {
			//表单信息
			TFormdesignForm form = formDesignService.getFormById(formId);
			//表单数据信息
			List<Map<String, Object>> formDatas = formDesignService.getFormDatasByFormId(formId);
			String title = form.getTitle();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" 
					+ new String(title.getBytes("gb18030"),"ISO8859-1") 
					+ ".xls"); 
			OutputStream outputStream = response.getOutputStream();
			//处理生成excel
			formDesignService.handleFormExcel(form, formDatas, outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
