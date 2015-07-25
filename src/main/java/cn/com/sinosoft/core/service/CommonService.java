/**
 * 
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2014-11-12
 */
package cn.com.sinosoft.core.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.com.sinosoft.common.model.TAttachment;
import cn.com.sinosoft.common.util.PropertiesUtil;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.model.FormResult;

/**
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2014-11-12
 */
@Service
public class CommonService extends SimpleServiceImpl {

	/**
	 * 获取某类型码表
	 * 
	 * @param type
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public Object getTypeCode(String type) {
		return dao.queryListBySql("select * from t_com_biz_codes where codetype = ? and isuse = 1 ",
				new String[]{type},
				new Type[]{StringType.INSTANCE} );
	}

	/**
	 * 获取机构列表
	 * 
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public Object getOrgList() {
		return dao.queryListBySql("select * from t_com_org_local WHERE isuse = 1  ",
				null,
				null);
	}
	
	/**
	 * 获取街道列表
	 * 
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public Object getStreetList() {
		return dao.queryListBySql("SELECT t.zonecode, t.zonename FROM t_com_zonecode_local t WHERE t.zonelevel = '4' and isuse = 1 ",
				null,
				null);
	}

	/**
	 * 获取村列表
	 * 
	 * @param streetCode
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public Object getVillageList(String streetCode){
		if(streetCode == null || streetCode.trim().equals("")){
			return new ArrayList<Object>();
		}
		String sCode = null;
		if(streetCode.length() == 12){//本地区划码
			sCode = streetCode.substring(0, 9);
		}else if(streetCode.length() == 8){//全国标准区划
			//补0
			sCode = streetCode.substring(0, 6) + "0" + streetCode.substring(6, 2);
		}else{
			return new ArrayList<Object>();
		}
		sCode += "%";
		return dao.queryListBySql("SELECT t.zonecode, t.zonename FROM t_com_zonecode_local t WHERE t.zonelevel = '5' and isuse = 1" +
				" and t.zonecode like ? ",
				new Object[]{sCode},
				new Type[]{StringType.INSTANCE});
	}

	/**
	 *  获取码值获取码名-本地区划
	 * 
	 * @param code
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@SuppressWarnings("unchecked")
	public Object getLocalZoneNameByCode(String code) {
		Collection<Object> items = dao.queryListBySql("SELECT t.zonename FROM t_com_zonecode_local t WHERE t.zonecode = ? and isuse = 1",
				new Object[]{code},
				new Type[]{StringType.INSTANCE});
		if(items.size() > 0){
			return ((Map<String, Object>)(items.toArray()[0])).get("zonename");
		}else{
			return "";
		}
	}
	
	/*附件路径*/
	public static String ATTAPATH = "";
	static{
		try {
			ATTAPATH = String.valueOf(PropertiesUtil.getValue("app.properties", "app.filepath"));
			if(!ATTAPATH.endsWith(File.separator)){
				ATTAPATH += File.separator;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存附件
	 * @param muFile
	 * @return 附件id
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@Transactional
	public TAttachment saveFile(MultipartFile muFile) throws Exception{
		String fileName = muFile.getOriginalFilename();
		String type = getFileTypeByName(fileName);
		long size = muFile.getSize();
		String id = UUID.randomUUID().toString();
		String yMD = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String newName = yMD + File.separator + id + "." + type;
		//保存附件到文件系统
		File saveFile = new File(ATTAPATH + File.separator + newName);
		if(!saveFile.exists()){
			saveFile.mkdirs();
		}
		saveFile.createNewFile();
		muFile.transferTo(saveFile);
		//保存附件信息到数据库
		TAttachment atta = new TAttachment();
		atta.setId(id);
		atta.setName(fileName);
		atta.setPath(newName);
		atta.setType(type);
		atta.setUploadTime(new Date());
		atta.setIsUse("0");
		atta.setSize(Double.valueOf(size));
		
		dao.save(atta);
		
		return atta;
	}
	private String getFileTypeByName(String fileName){
		if(StrUtils.isNull(fileName)){
			return null;
		}
		String[] nameSp = fileName.split("\\.");
		String type = nameSp[nameSp.length -1];
		if(type.length() >= 10){
			type = "";
		}
		return type;
	}
	
	/**
	 * 删除附件
	 * @param id
	 * @return
	 */
	@Transactional
	public FormResult fileDel(String id) {
		FormResult ret = new FormResult();
		//附件表
		dao.executeDelOrUpdateSql("delete from t_attachment where id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//附件业务关联表
		dao.executeDelOrUpdateSql("delete from t_custom_data where attachment_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		dao.executeDelOrUpdateSql("delete from t_contract_data where attachment_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		dao.executeDelOrUpdateSql("delete from t_resume_date where attachment_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		dao.executeDelOrUpdateSql("delete from t_job_comm_file where attachment_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除附件文件
		TAttachment att = dao.queryById(id, TAttachment.class);
		if(att != null){
			File saveFile = new File(ATTAPATH + File.separator + att.getPath());
			saveFile.delete();
		}
		
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("附件删除成功");
		return ret;
	}

	/**
	 * 获取附件
	 * @param id
	 * @return
	 */
	public TAttachment getFile(String id) {
		return dao.queryById(id, TAttachment.class);
	}

	/**
	 * 获取所有码表
	 * @return
	 */
	public Object getAllCodes() {
		return dao.queryListBySql(
				"select c.* from t_code c order by c.rank "
				,null, null);
	}
	
}
