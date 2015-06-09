/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-3-4
 */
package cn.com.sinosoft.formdesign.service;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.common.util.SqlUtil;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.dao.SimpleBaseDao;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;
import cn.com.sinosoft.formdesign.model.TFormdesignForm;
import cn.com.sinosoft.formdesign.model.TFormdesignFormdata;

/**
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-3-4
 */
@Service
public class FormDesignService extends SimpleServiceImpl{
	
	@Resource
	SimpleBaseDao baseDao;
	@Resource
	UserUtil userUtil;

	/**
	 * 添加表单
	 * 
	 * @param form
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult add(String title, String content, String contentSrc, String id, String fields) {
		if(!StrUtils.isNull(id)){
			//删除原始表单以及相关数据
			String delFormSql = "delete from t_formdesign_form where id = ? ";
			String delDataSql = "delete from t_formdesign_formdata where form_id = ? ";
			baseDao.executeDelOrUpdateSql(delFormSql, new String[]{id}, new Type[]{StringType.INSTANCE});
			baseDao.executeDelOrUpdateSql(delDataSql, new String[]{id}, new Type[]{StringType.INSTANCE});
		}else{
			id = UUID.randomUUID().toString();
		}
		FormResult result = new FormResult();
		Date date = new Date();
		TFormdesignForm form = new TFormdesignForm();
		form.setDtCreate(date);
		form.setDtUpdate(date);
		form.setId(id);
		form.setIsPub("0");
		try{
			form.setContent(content.getBytes("utf-8"));
			form.setContentSrc(contentSrc.getBytes("utf-8"));
			form.setFields(fields.getBytes("utf-8"));
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result.setSuccess(FormResult.ERROR);
			result.setMessage("保存失败");
			return result;
		}
		form.setTitle(title);
		baseDao.save(form);
		result.setSuccess(FormResult.SUCCESS);
		result.setData(id);
		return result;
	}
	
	/**
	 * 删除表单
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult del(String id) {
		String delFormSql = "delete from t_formdesign_form where id = ? ";
		String delDataSql = "delete from t_formdesign_formdata where form_id = ? ";
		baseDao.executeDelOrUpdateSql(delFormSql, new String[]{id}, new Type[]{StringType.INSTANCE});
		baseDao.executeDelOrUpdateSql(delDataSql, new String[]{id}, new Type[]{StringType.INSTANCE});
		
		FormResult result = new FormResult();
		result.setSuccess(FormResult.SUCCESS);
		return result;
	}

	/**
	 * 发布表单
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult pub(String id, String type) {
		String sql = " update t_formdesign_form set is_pub = 1, dt_pub = ?, pub_type = ? where id = ? ";
		baseDao.executeDelOrUpdateSql(sql,
				new Object[]{new Date(), type, id}, 
				new Type[]{TimestampType.INSTANCE, StringType.INSTANCE, StringType.INSTANCE});
		FormResult result = new FormResult();
		result.setSuccess(FormResult.SUCCESS);
		return result;
	}

	/**
	 * 取消发布表单
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult cancelPub(String id) {
		String sql = " update t_formdesign_form set is_pub = 0, dt_pub = null, pub_type = null where id = ? ";
		baseDao.executeDelOrUpdateSql(sql, new Object[]{id}, new Type[]{StringType.INSTANCE});
		FormResult result = new FormResult();
		result.setSuccess(FormResult.SUCCESS);
		return result;
	}

	/**
	 * 获取表单列表
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public PagingResult getForms(Map<String, String> params,
			PageParam pageParams) {
		PagingSrcSql srcSql = getFormsListSql(params);
		return pagingSearch(params, pageParams, srcSql);
	}
	
	/**
	 * 获取表单列表原始sql
	 *
	 * 
	 * @param params
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	private PagingSrcSql getFormsListSql(Map<String, String> params) {
		TUser user = userUtil.getLoginUser();
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" SELECT z.*,IFNULL(m.cou, 0)  cou from ( " +
				"select t.id,t.title,t.dt_create,t.is_pub,t.dt_pub,t.pub_type  " +
				"FROM t_formdesign_form t where 1=1 "
				);
		//非超级管理员只能查询自己发布的表单
		if(!UserUtil.USERTYPE_SUPERADMIN.equals(user.getUserType())){
			sb.append(" AND t.create_user = ? ");
			values.add(user.getId());
			types.add(StringType.INSTANCE);
		}
		
		if(!StrUtils.isNull(params.get("title"))){//标题
			sb.append(" AND t.title like ? ");
			values.add("%" + params.get("title") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("ispub"))){//发布状态
			sb.append(" AND t.is_pub = ? ");
			values.add(params.get("ispub"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("pubtype"))){//发布类型
			sb.append(" AND t.pub_type = ? ");
			values.add(params.get("pubtype"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("dtcreate_begin"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("dtcreate_begin"), 1, 0) + " <= t.dt_create ");
		}
		if(!StrUtils.isNull(params.get("dtcreate_end"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("dtcreate_end"), 1, 0) + " >= t.dt_create ");
		}
		if(!StrUtils.isNull(params.get("dtpub_begin"))){//发布日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("dtpub_begin"), 1, 0) + " <= t.dt_pub ");
		}
		if(!StrUtils.isNull(params.get("dtpub_end"))){//发布日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("dtpub_end"), 1, 0) + " >= t.dt_pub ");
		}
		sb.append(" order by t.dt_create desc ) z ");
		sb.append("LEFT JOIN ( SELECT form_id, COUNT(1) cou " +
				"FROM t_formdesign_formdata GROUP BY form_id) " +
				"m ON z.id = m.form_id ");
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		return srcSql;
	}
	
	/**
	 * 获取可上报表单列表
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public PagingResult getMyForms(Map<String, String> params,
			PageParam pageParams) {
		PagingSrcSql srcSql = getMyFormsListSql(params);
		return pagingSearch(params, pageParams, srcSql);
	}
	
	/**
	 * 获取可上报表单列表原始sql
	 *
	 * 
	 * @param params
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	private PagingSrcSql getMyFormsListSql(Map<String, String> params) {
		TUser user = userUtil.getLoginUser();
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		StringBuffer sb = new StringBuffer(" " +
				"select t.id,t.title,t.dt_create,t.is_pub,t.dt_pub  " +
				"FROM t_formdesign_form t left join t_user u on t.create_user = u.id" +
				" where t.is_pub = '1' "
				);
		//只能查询自己可上报的表单
		//所有机构
		sb.append(" and ( t.pub_type = '1' ");
		//本机构
		sb.append(" or (t.pub_type = '2' and u.org_code = ? ) ");
		//本机构本科室
		sb.append(" or (t.pub_type = '3' and u.org_code = ? and u.department = ? ) ");
		values.add(user.getDepartment());
		types.add(StringType.INSTANCE);
		//指定用户-暂未实现
		
		sb.append(" ) ");
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		return srcSql;
	}

	/**
	 * 获取表单数据列表
	 * 
	 * @param params
	 * @param pageParams
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public PagingResult getFormDatas(Map<String, String> params,
			PageParam pageParams) {
		PagingSrcSql srcSql = getFormsDataListSql(params);
		PagingResult result =  pagingSearch(params, pageParams, srcSql);
		//转换类型
		Collection<Object> items = result.getRows();
		Collection<Object> newItems = new ArrayList<Object>();
		for(Object obj : items){
			Map<String, Object> item = (Map<String, Object>)obj;
			Map<String, Object> newItem = new HashMap<String, Object>();
			newItem.put("id", item.get("id"));
			newItem.put("dt_create", item.get("dt_create"));
			try {
				newItem.put("form_data", new String((byte[])(item.get("form_data")), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			newItems.add(newItem);
		}
		result.setRows(newItems);
		return result;
	}
	
	/**
	 * 获取表单数据列表原始sql
	 *
	 * 
	 * @param params
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	private PagingSrcSql getFormsDataListSql(Map<String, String> params) {
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		
		StringBuffer sb = new StringBuffer(" select id,dt_create,form_data from t_formdesign_formdata " +
				" where 1=1 ");
		if(!StrUtils.isNull(params.get("formid"))){//标题
			sb.append(" AND form_id = ? ");
			values.add(params.get("formid"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("dtcreate_begin"))){//上报日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("dtcreate_begin"), 1, 0) + " <= dt_create ");
		}
		if(!StrUtils.isNull(params.get("dtcreate_end"))){//上报日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("dtcreate_end"), 1, 0) + " >= dt_create ");
		}
		sb.append(" order by dt_create desc ");
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		return srcSql;
	}
	
	/**
	 * 获取表单数据信息
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public TFormdesignFormdata getFormDataById(String id) {
		return baseDao.queryById(id, TFormdesignFormdata.class);
	}
	
	/**
	 * 获取表单信息
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public TFormdesignForm getFormById(String id) {
		return baseDao.queryById(id, TFormdesignForm.class);
	}

	/**
	 * 表单数据保存
	 * 
	 * @param data 提交的数据
	 * @param formid 表单id
	 * @return 数据id
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public String formdatasave(Map<String, Object> data, String formid) {
		//解析data中数据为json格式
		TFormdesignFormdata formData = new TFormdesignFormdata();
		String formDataId = UUID.randomUUID().toString();
		Date date = new Date();
		formData.setDtCreate(date);
		formData.setDtUpdate(date);
		formData.setId(formDataId);
		formData.setFormId(formid);
		try {
			formData.setFormData(transData2Json(data));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		baseDao.save(formData);
		return formDataId;
	}
	private byte[] transData2Json(Map<String, Object> data) throws Exception{
		ObjectMapper om =  new ObjectMapper();
		return om.writeValueAsBytes(data);
	}
	
	/**
	 * 删除表单数据
	 * 
	 * @param id
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Transactional
	public FormResult formDataDel(String id) {
		String delDataSql = "delete from t_formdesign_formdata where id = ? ";
		baseDao.executeDelOrUpdateSql(delDataSql, new String[]{id}, new Type[]{StringType.INSTANCE});
		
		FormResult result = new FormResult();
		result.setSuccess(FormResult.SUCCESS);
		return result;
	}

	/**
	 * 获取所有表单数据
	 * 
	 * @param formId
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getFormDatasByFormId(String formId) {
		String sql = "select * from t_formdesign_formdata where form_id = ? ";
		List<Map<String, Object>> datas = baseDao.queryListBySql(sql, new Object[]{formId}, new Type[]{StringType.INSTANCE});
		return datas;
	}
	
	/**
	 * 处理表单数据excel
	 *
	 * 
	 * @param form
	 * @param formDatas
	 * @param stream
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public void handleFormExcel(
			TFormdesignForm form, 
			List<Map<String, Object>> formDatas,
			OutputStream stream
			){
		HSSFWorkbook wb = new HSSFWorkbook();
		//普通单元格样式
		CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom((short)1);
        cellStyle.setBorderTop((short)1);
        cellStyle.setBorderLeft((short)1);
        cellStyle.setBorderRight((short)1);
        cellStyle.setBottomBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyle.setTopBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyle.setLeftBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyle.setRightBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        //特殊单元格样式
        CellStyle cellStyleSpe = wb.createCellStyle();
        cellStyleSpe.setBorderBottom((short)1);
        cellStyleSpe.setBorderTop((short)1);
        cellStyleSpe.setBorderLeft((short)1);
        cellStyleSpe.setBorderRight((short)1);
        cellStyleSpe.setBottomBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyleSpe.setTopBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyleSpe.setLeftBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyleSpe.setRightBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyleSpe.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleSpe.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyleSpe.setWrapText(true);
        Font fontSpe = wb.createFont();
        fontSpe.setColor(HSSFFont.COLOR_RED);
        cellStyleSpe.setFont(fontSpe);
        
        //表头单元格样式
        CellStyle cellStyleHeader = wb.createCellStyle();
        cellStyleHeader.setBorderBottom((short)1);
        cellStyleHeader.setBorderTop((short)1);
        cellStyleHeader.setBorderLeft((short)1);
        cellStyleHeader.setBorderRight((short)1);
        cellStyleHeader.setBottomBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyleHeader.setTopBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyleHeader.setLeftBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyleHeader.setRightBorderColor(HSSFFont.COLOR_NORMAL);
        cellStyleHeader.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleHeader.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyleHeader.setWrapText(true);
        
        Font font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cellStyleHeader.setFont(font);
        
        //带描述的页
        Sheet sheetpart = wb.createSheet(form.getTitle() + " - 只包含描述的字段");
        //所有字段的页
        Sheet sheetall = wb.createSheet(form.getTitle() + " -  全部字段");
        int currentRowAll = 0;
        int currentRowPart = 0;
        
        //title行
        Row titleRowAll = sheetall.createRow(currentRowAll);
        Row titleRowPart = sheetpart.createRow(currentRowPart);
        currentRowAll++;
        currentRowPart++;
        Cell titleCellAll = titleRowAll.createCell(0);
        titleCellAll.setCellStyle(cellStyleHeader);
        titleCellAll.setCellValue(form.getTitle() + " - 全部字段");
        Cell titleCellPart = titleRowPart.createCell(0);
        titleCellPart.setCellStyle(cellStyleHeader);
        titleCellPart.setCellValue(form.getTitle() + "- 只包含描述的字段");
        
        //表头行
        Row headerRowAll = sheetall.createRow(currentRowAll);
        Row headerRowPart = sheetpart.createRow(currentRowPart);
        currentRowAll++;
        currentRowPart++;
        
        try {
        	String fieldsStr = new String(form.getFields(), "utf-8");
        	ObjectMapper om = new ObjectMapper();
			Map<String, FormField> fields = om.readValue(fieldsStr, new TypeReference<Map<String, FormField>>(){});
			List<FormField> formFieldsAll = new ArrayList<FormField>();
			List<FormField> formFieldsPart = new ArrayList<FormField>();
			
			for(String key : fields.keySet()){
				FormField field = fields.get(key);
				String desc = field.getFielddesc();
				formFieldsAll.add(field);
				if(desc != null && !desc.trim().equals("")){
					formFieldsPart.add(field);
				}
			}
			
			//合并title
	        CellRangeAddress addressAll = new CellRangeAddress(0, 0, 0, formFieldsAll.size() - 1);
	        CellRangeAddress addressPart = new CellRangeAddress(0, 0, 0, formFieldsPart.size() - 1);
	        sheetall.addMergedRegion(addressAll);
	        sheetpart.addMergedRegion(addressPart);
			
			//表头信息
			int cellIdxAll = 0;
			for(FormField formField : formFieldsAll){
				Cell cell = headerRowAll.createCell(cellIdxAll);
				cellIdxAll++;
				cell.setCellStyle(cellStyle);
				String desc = formField.getFielddesc();
				if(desc == null || desc.trim().equals("")){
					cell.setCellValue("未定义");
					cell.setCellStyle(cellStyleSpe);
				}else{
					cell.setCellValue(formField.getFielddesc());
				}
			}
			int cellIdxPart = 0;
			for(FormField formField : formFieldsPart){
				Cell cell = headerRowPart.createCell(cellIdxPart);
				cellIdxPart++;
				cell.setCellStyle(cellStyle);
				cell.setCellValue(formField.getFielddesc());
			}
			
			//表数据
			for(Map<String, Object> data : formDatas){
				String formDataStr = new String((byte[])data.get("form_data"), "utf-8");
				Map<String, String> formDataMap = om.readValue(formDataStr,
						new TypeReference<Map<String, String>>(){});
				Row rowAll = sheetall.createRow(currentRowAll);
				Row rowPart = sheetpart.createRow(currentRowPart);
				currentRowAll++;
				currentRowPart++;
				
				cellIdxAll = 0;
				for(FormField formField : formFieldsAll){
					Cell cell = rowAll.createCell(cellIdxAll);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(formDataMap.get(formField.getName()));
					cellIdxAll++;
				}
				cellIdxPart = 0;
				for(FormField formField : formFieldsPart){
					Cell cell = rowPart.createCell(cellIdxPart);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(formDataMap.get(formField.getName()));
					cellIdxPart++;
				}
			}
			wb.write(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
