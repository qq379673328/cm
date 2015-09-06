package cn.com.sinosoft.resume.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.common.model.TAttachment;
import cn.com.sinosoft.common.model.TJob;
import cn.com.sinosoft.common.model.TResume;
import cn.com.sinosoft.common.model.TResumeCommunication;
import cn.com.sinosoft.common.model.TResumeEdu;
import cn.com.sinosoft.common.model.TResumeJob;
import cn.com.sinosoft.common.model.TResumeLanguage;
import cn.com.sinosoft.common.model.TResumeTarget;
import cn.com.sinosoft.common.model.TResumeWorkhistory;
import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.common.util.Excel.Table2Excel;
import cn.com.sinosoft.core.action.BaseController;
import cn.com.sinosoft.core.service.CommonService;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.util.UserUtil;
import cn.com.sinosoft.formdesign.Test;
import cn.com.sinosoft.job.service.JobService;
import cn.com.sinosoft.resume.service.ResumeService;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 简历管理
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-6-15
 */
@Controller
@RequestMapping("resume")
public class ResumeController extends BaseController {

	@Resource
	ResumeService resumeService;
	@Resource
	UserUtil userUtil;
	@Resource
	CommonService commonService;
	@Resource
	JobService jobService;

	/**
	 * 获取简历列表
	 * 
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public PagingResult getResumeList(@RequestParam Map<String, String> params,
			PageParam pageParams) {
		return resumeService.getResumeList(params, pageParams);
	}

	/**
	 * 根据简历id获取简历信息
	 * 
	 * @return
	 */
	@RequestMapping("getResumeById")
	@ResponseBody
	public TResume getResumeById(String id) {
		return resumeService.getResumeById(id);
	}

	/**
	 * 根据简历id获取简历信息-查看编辑简历
	 * 
	 * @return
	 */
	@RequestMapping("getResumeViewById")
	@ResponseBody
	public Object getResumeViewById(String id) {
		return resumeService.getResumeViewById(id);
	}

	/**
	 * 编辑简历信息-编辑基本信息
	 * 
	 * @return
	 */
	@RequestMapping("saveBaseinfo")
	@ResponseBody
	public FormResult saveBaseinfo(TResume resume) {
		TUser user = userUtil.getLoginUser();
		if (StrUtils.isNull(resume.getId())) {// 新增
			resume.setId(UUID.randomUUID().toString());
			resume.setCreateTime(new Date());
			resume.setCreateUser(user.getId());
			resume.setLastUpdateTime(new Date());
			resume.setLastUpdateUser(user.getId());
			return resumeService.saveBean(resume);
		} else {// 更新
			resume.setLastUpdateTime(new Date());
			resume.setLastUpdateUser(user.getId());
			return resumeService.updateBean(resume);
		}
	}

	/**
	 * 编辑简历信息-编辑求职意向
	 * 
	 * @return
	 */
	@RequestMapping("saveTarget")
	@ResponseBody
	public FormResult saveTarget(TResumeTarget target) {
		if (StrUtils.isNull(target.getId())) {// 新增
			target.setId(UUID.randomUUID().toString());
			target.setCreateTime(new Date());
			target.setCreateUser(resumeService.getLoginUserId());
			return resumeService.saveBean(target);
		} else {// 更新
			return resumeService.updateBean(target);
		}
	}

	/**
	 * 编辑简历信息-编辑工作经历
	 * 
	 * @return
	 */
	@RequestMapping("saveWorkHistory")
	@ResponseBody
	public FormResult saveWorkHistory(TResumeWorkhistory item) {
		if (StrUtils.isNull(item.getId())) {// 新增
			item.setId(UUID.randomUUID().toString());
			item.setCreateTime(new Date());
			item.setCreateUser(resumeService.getLoginUserId());
			return resumeService.saveBean(item);
		} else {// 更新
			return resumeService.updateBean(item);
		}
	}

	/**
	 * 编辑简历信息-删除工作经历
	 * 
	 * @return
	 */
	@RequestMapping("delWorkHistory")
	@ResponseBody
	public FormResult delWorkHistory(String id) {
		return resumeService.delById(id, "t_resume_workhistory");
	}

	/**
	 * 编辑简历信息-编辑教育经历
	 * 
	 * @return
	 */
	@RequestMapping("saveEduHistory")
	@ResponseBody
	public FormResult saveEduHistory(TResumeEdu item) {
		if (StrUtils.isNull(item.getId())) {// 新增
			item.setId(UUID.randomUUID().toString());
			item.setCreateTime(new Date());
			item.setCreateUser(resumeService.getLoginUserId());
			return resumeService.saveBean(item);
		} else {// 更新
			return resumeService.updateBean(item);
		}
	}

	/**
	 * 编辑简历信息-删除教育经历
	 * 
	 * @return
	 */
	@RequestMapping("delEduHistory")
	@ResponseBody
	public FormResult delEduHistory(String id) {
		return resumeService.delById(id, "t_resume_edu");
	}

	/**
	 * 编辑简历信息-编辑语言
	 * 
	 * @return
	 */
	@RequestMapping("saveLanguage")
	@ResponseBody
	public FormResult saveLanguage(TResumeLanguage item) {
		if (StrUtils.isNull(item.getId())) {// 新增
			item.setId(UUID.randomUUID().toString());
			item.setCreateTime(new Date());
			item.setCreateUser(resumeService.getLoginUserId());
			return resumeService.saveBean(item);
		} else {// 更新
			return resumeService.updateBean(item);
		}
	}

	/**
	 * 编辑简历信息-删除语言经历
	 * 
	 * @return
	 */
	@RequestMapping("delLanguage")
	@ResponseBody
	public FormResult delLanguage(String id) {
		return resumeService.delById(id, "t_resume_language");
	}

	/**
	 * 编辑简历信息-上传头像
	 * 
	 * @return
	 */
	@RequestMapping("saveIcon")
	@ResponseBody
	public FormResult saveIcon(String resumeId, String iconId, int x, int y,
			int w, int h) {
		try{
			TAttachment att = commonService.getFile(iconId);
			if (att != null) {
				// 原始图片
				File srcFile = new File(CommonService.ATTAPATH + File.separator
						+ att.getPath());
				// 处理图片
				BufferedImage bi = (BufferedImage)ImageIO.read(srcFile);
				h = Math.min(h, bi.getHeight());
				w = Math.min(w, bi.getWidth());
				if (h <= 0)
					h = bi.getHeight();
				if (w <= 0)
					w = bi.getWidth();
				y = Math.min(Math.max(0, y), bi.getHeight() - h);
				x = Math.min(Math.max(0, x), bi.getWidth() - w);
				BufferedImage bi_cropper = bi.getSubimage(x, y, w, h);
				ImageIO.write(bi_cropper, att.getType(), srcFile);
			}
		}catch(Exception e){
			e.printStackTrace();
			FormResult ret = new FormResult();
			ret.setSuccess(FormResult.ERROR);
			ret.setMessage("头像更新失败");
			return ret;
		}
		return resumeService.saveIcon(resumeId, iconId);
	}

	/**
	 * 编辑简历信息-上传附件
	 * 
	 * @return
	 */
	@RequestMapping("uploadAttas")
	@ResponseBody
	public FormResult uploadAttas(String attas, String id) {
		return resumeService.uploadAttas(attas, id);
	}

	/**
	 * 编辑简历信息-添加沟通记录
	 * 
	 * @return
	 */
	@RequestMapping("addComm")
	@ResponseBody
	public FormResult addComm(TResumeCommunication comm) {
		String userId = userUtil.getLoginUser().getId();
		if (StrUtils.isNull(comm.getId())) {// 新增
			comm.setId(UUID.randomUUID().toString());
			comm.setCreateTime(new Date());
			comm.setCreateUser(userId);
			return resumeService.saveBean(comm);
		} else {// 更新
			return resumeService.updateBean(comm);
		}
	}

	/**
	 * 编辑简历信息-删除沟通记录
	 * 
	 * @return
	 */
	@RequestMapping("delComm")
	@ResponseBody
	public FormResult delComm(String id) {
		return resumeService.delById(id, "t_resume_communication");
	}

	/**
	 * 简历推荐-推荐简历
	 * 
	 * @return
	 */
	@RequestMapping("pub")
	@ResponseBody
	public FormResult pub(String resumeId, String jobId) {
		return resumeService.pub(resumeId, jobId);
	}

	/**
	 * 简历推荐-取消推荐简历
	 * 
	 * @return
	 */
	@RequestMapping("canclePub")
	@ResponseBody
	public FormResult canclePub(String resumeId, String jobId) {
		return resumeService.canclePub(resumeId, jobId);
	}

	/**
	 * 获取简历客户列表
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 */
	@RequestMapping("getCustomList")
	@ResponseBody
	public PagingResult getCustomList(@RequestParam Map<String, String> params,
			PageParam pageParams) {
		return resumeService.getCustomList(params, pageParams);
	}
	
	/**
	 * 导出简单excel
	 *
	 * 
	 * @param response
	 * @param tableJson
	 * @param title
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("export/{id}")
	public void exportExcel(HttpServletResponse response,
			@PathVariable("id")String id){
		try {
			
			TResumeJob resumtJob = resumeService.queryById(id, TResumeJob.class);
			
			Map<String, Object> resumeData = resumeService.getResumeViewById(resumtJob.getResumeId());
			Map<String, Object> jobData = jobService.getJobViewById(resumtJob.getJobId());
			response.setContentType("application/vnd.ms-excel");
			
			TJob job = (TJob)jobData.get("job");
			TResume resume = (TResume)resumeData.get("resume");
			
			String title = resume.getName() + "-" + job.getName() + "-合信锐博-" +
			 new SimpleDateFormat("yyyyMMdd").format(new Date());
			title = URLEncoder.encode(title,"GB2312"); 
			title = URLDecoder.decode(title, "ISO8859_1"); 
			response.setHeader("Content-disposition", "attachment;filename=" + title + ".doc"); 
			OutputStream ouputStream = response.getOutputStream();
			Template tem = getTemplage();
			Writer writer = new BufferedWriter(
					new OutputStreamWriter(ouputStream));
			tem.process(getData(resumeData, jobData, resumtJob), writer);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Template getTemplage() throws Exception{
		Configuration con = new Configuration();
		con.setDefaultEncoding("utf-8");
		con.setClassForTemplateLoading(Test.class, "/template/freemarker");
		con.setClassicCompatible(true);
		Template tem = con.getTemplate("test.xml");
		return tem;
	}
	private Map<String, Object> getData(
			Map<String, Object> resumeData,
			Map<String, Object> jobData,
			TResumeJob resumeJob){
		Map<String, Object> ret = new HashMap<String, Object>();
		TJob job = (TJob)jobData.get("job");
		TResume resume = (TResume)resumeData.get("resume");
		//title 标题
		ret.put("title", "候选人简历报告");
		//		tjzw 推荐职位
		ret.put("tjzw", saftGet(job.getName()));
		//		tjsj 推荐时间
		ret.put("tjsj", saftGet(
				new SimpleDateFormat("yyyy-MM-dd").format(resumeJob.getRecomTime())));
		//		name 姓名
		ret.put("name", saftGet(resume.getName()));
		//		sex 性别
		ret.put("sex", saftGet(resume.getSex()));
		//		age 年龄
		ret.put("age", getAgeByBirthday(resume.getBirthcay()));
		//		marrage 婚否
		ret.put("marrage", saftGet(resume.getMarrage()));
		//		workplace 工作地点
		ret.put("workplace", saftGet(resume.getCity()));
		//		money_current 目前薪资
		ret.put("money_current", saftGet(resume.getYearPay()));
		//		money_target 期望薪资
		List<Map<String, Object>> targets = (List<Map<String, Object>>)resumeData.get("target");
		if(targets != null && targets.size() > 0){
			ret.put("money_target", targets.get(0).get("target_pay"));
		}else{
			ret.put("money_target", "");
		}
		//		jybj 教育背景
		//			jybj-time 时间
		//			jybj-school 学校
		//			jybj-zhuanye 专业
		//			jybj-xueli 学历
		ret.put("jybj", resumeData.get("resumeEdus"));
		//		gzpj 工作评价
		//			gzpj-content 内容
		//		gzjl 工作经历
		//			gzjl_time 时间段
		//			gzjl_danwei 工作单位
		//			gzjl_zhiwu 职务
		//			gzjl_zhize 工作职责
		//				content 内容
		ret.put("gzjl", resumeData.get("resumeWorkhistorys"));
		//		cyxm 参与项目
		//			cyxm_name 项目名
		//			cyxm_zhiwu 职务
		//			cyxm_jianjie 项目简介
		//			cyxm_zhize 项目职责
		//				content 内容
			
		return ret;
	}
	private String getAgeByBirthday(Date birthday){
		if(birthday == null){
			return "";
		}
		return birthday.getYear() + "";
	}
	private Object saftGet(Object obj){
		if(obj == null){
			return "";
		}else{
			return obj;
		}
	}
}
