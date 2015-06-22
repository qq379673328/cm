/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2014-11-12
 */
package cn.com.sinosoft.core.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.com.sinosoft.common.model.TAttachment;
import cn.com.sinosoft.common.util.Excel.Table2Excel;
import cn.com.sinosoft.core.service.CommonService;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.util.UserUtil;

/**
 * 公共action
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2014-11-12
 */
@Controller
public class CommonAction extends BaseController {

	@Resource
	CommonService commonService;
	@Resource
	UserUtil userUtil;
	
	/**
	 * 导出简单excel
	 *
	 * 
	 * @param response
	 * @param tableJson
	 * @param title
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("exportSimpleTable")
	public void exportExcel(HttpServletResponse response, String tableJson, String title){
		try {
			if(title == null || title.trim().equals("")){
				title = "export";
			}
			response.setContentType("application/vnd.ms-excel");
			title = URLEncoder.encode(title,"GB2312"); 
			title = URLDecoder.decode(title, "ISO8859_1"); 
			response.setHeader("Content-disposition", "attachment;filename=" + title + ".xls"); 
			OutputStream ouputStream = response.getOutputStream();
			new Table2Excel().transJson2Excel(tableJson, ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 未授权跳转
	 *
	 * 
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("unauthorized")
	public Object unauthorized(
			HttpServletRequest req,
			HttpServletResponse res){
		String xmlHttpRequest = req.getHeader("X-Requested-With");
		ModelAndView mav = new ModelAndView("unauthorized");
		if (xmlHttpRequest != null){
			if (xmlHttpRequest.equalsIgnoreCase("XMLHttpRequest")) {
				res.setStatus(403);
			}
		}
		return mav;
	}
	
	/**
	 * 获取登录用户信息
	 *
	 * 
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("getLoginUser")
	@ResponseBody
	public Object getLoginUser(){
		return userUtil.getLoginUser();
	}
	
	/**
	 * 附件上传
	 * @param file
	 * @param model
	 * @return
	 */
	@RequestMapping("fileUpload")
	@ResponseBody
	public FormResult fileUpload(@RequestParam MultipartFile files, HttpServletRequest request){
		FormResult ret = new FormResult();
		TAttachment atta = null;
		if(files != null){
			try {
				atta = commonService.saveFile(files) ;
			} catch (Exception e) {
				e.printStackTrace();
				ret.setSuccess(FormResult.ERROR);
				ret.setMessage("附件上传失败");
				return ret;
			}
			ret.setSuccess(FormResult.SUCCESS);
			ret.setMessage("附件上传成功");
			ret.setData(atta);
		}else{
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("附件为空");
		}
        return ret;
	}
	
	/**
	 * 附件删除
	 * @param file
	 * @param model
	 * @return
	 */
	@RequestMapping("fileDel")
	@ResponseBody
	public FormResult fileDel(String id){
		return commonService.fileDel(id);
	}
	
	/**
	 * 附件下载
	 * @param response
	 */
	@RequestMapping("download/{id}")
    public void fileDownload(HttpServletResponse response, HttpServletRequest request,
    		@PathVariable("id") String id){
		ServletContext servletContext = request.getServletContext();
        //获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载  
        String path = servletContext.getRealPath("/");
        TAttachment atta = commonService.getFile(id);
        String fileName = "unkonwn";
		try {
			fileName = new String(atta.getName().getBytes("UTF-8"),"iso-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型 
        response.setContentType("application/octet-stream; charset=utf-8");  
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);  
        ServletOutputStream out;  
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  s
        File file = new File(CommonService.ATTAPATH + atta.getPath());  
  
        try {  
            FileInputStream inputStream = new FileInputStream(file);  
  
            //3.通过response获取ServletOutputStream对象(out)  
            out = response.getOutputStream();  
  
            int b = 0;  
            byte[] buffer = new byte[512];  
            while (b != -1){  
                b = inputStream.read(buffer);  
                if(b == -1) break;
                //4.写到输出流(out)中  
                out.write(buffer,0,b);  
            }  
            inputStream.close();  
            out.close();  
            out.flush();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	
}
