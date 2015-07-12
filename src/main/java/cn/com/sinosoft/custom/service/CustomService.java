package cn.com.sinosoft.custom.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.sinosoft.common.model.TCustom;
import cn.com.sinosoft.common.model.TCustomCommunication;
import cn.com.sinosoft.common.model.TCustomData;
import cn.com.sinosoft.common.model.TCustomTeam;
import cn.com.sinosoft.common.model.TUser;
import cn.com.sinosoft.common.util.SqlUtil;
import cn.com.sinosoft.common.util.StrUtils;
import cn.com.sinosoft.core.service.AttachmentService;
import cn.com.sinosoft.core.service.SimpleServiceImpl;
import cn.com.sinosoft.core.service.model.FormResult;
import cn.com.sinosoft.core.service.model.PageParam;
import cn.com.sinosoft.core.service.model.PagingResult;
import cn.com.sinosoft.core.service.model.PagingSrcSql;
import cn.com.sinosoft.core.util.UserUtil;

@Service
public class CustomService extends SimpleServiceImpl {
	
	@Resource
	UserUtil userUtil;
	@Resource
	AttachmentService attachmentService;

	/**
	 * 获取客户列表-分页
	 * @param params
	 * @param pageParams
	 * @return
	 */
	public PagingResult getCustomList(Map<String, String> params, PageParam pageParams) {
		
		PagingSrcSql srcSql = new PagingSrcSql();
		List<Object> values = new ArrayList<Object>();
		List<Type> types = new ArrayList<Type>();
		TUser user = userUtil.getLoginUser();
		String userId = user.getId();
		StringBuffer sb = new StringBuffer(" select tt.* from ( SELECT "
				+ " t.*, getDictName(t.team) teams_desc,  "
				+ " CASE "
				+ "     WHEN t.create_user = ?  "
				+ "     THEN 'my'  "
				+ "      WHEN ? IN ( "
				+ " 	     SELECT u.id FROM  t_user u WHERE  "
				+ " 	     (u.team IN ( SELECT m.user_id FROM t_custom_team m WHERE m.custom_id = t.id)) "
				+ " 	     OR "
				+ " 	     (u.id IN ( SELECT m.user_id FROM t_custom_team m WHERE m.custom_id = t.id)) "
				+ "      ) "
				+ "     THEN 'team'  "
				+ "     ELSE 'other'  "
				+ "   END AS beyond "

				+ " FROM t_custom t ) tt WHERE 1=1 ");
		values.add(userId);
		types.add(StringType.INSTANCE);
		values.add(userId);
		types.add(StringType.INSTANCE);
		
		if(!StrUtils.isNull(params.get("customIndustry"))){//客户行业
			sb.append(" AND tt.industry like ? ");
			values.add("%" + params.get("customIndustry") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("customSource"))){//客户来源
			sb.append(" AND tt.source = ? ");
			values.add(params.get("customSource"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("customStatus"))){//客户状态
			sb.append(" AND tt.state = ? ");
			values.add(params.get("customStatus"));
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("companyName"))){//公司名称
			sb.append(" AND tt.custom_name like ? ");
			values.add("%" + params.get("companyName") + "%");
			types.add(StringType.INSTANCE);
		}
		if(!StrUtils.isNull(params.get("createTimeStart"))){//创建日期-开始
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeStart"), 1, 0) + " <= tt.create_time ");
		}
		if(!StrUtils.isNull(params.get("createTimeEnd"))){//创建日期-结束
			sb.append(" AND " + SqlUtil.toDate(params.get("createTimeEnd"), 1, 0) + " >= tt.create_time ");
		}
		//客户类型-我的、合作、其他
		if(!StrUtils.isNull(params.get("beyond"))){
			sb.append(" AND tt.beyond = ? ");
			values.add(params.get("beyond"));
			types.add(StringType.INSTANCE);
		}
		
		sb.append(" ORDER BY tt.create_time DESC ");
		
		srcSql.setSrcSql(sb.toString());
		srcSql.setTypes(types.toArray(new Type[0]));
		srcSql.setValues(values.toArray());
		
		return pagingSearch(params, pageParams, srcSql);
	}

	/**
	 * 根据id获取客户
	 * @param id
	 * @return
	 */
	public Object getCustomById(String id) {
		Map<String, Object> ret = new HashMap<String, Object>();
		if(StrUtils.isNull(id) 
				|| !getCustomType(id).equals(CUSTOMTYPE_MY)//非本人发布-无权编辑
				){
			return ret;
		}
		//客户信息
		ret.put("custom", dao.queryById(id, TCustom.class));
		//沟通信息
		ret.put("communs", dao.queryListBySql(
				"select * from t_custom_communication where custom_id = ? order by create_time desc",
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		//附件信息
		ret.put("attachs", dao.queryListBySql(
				"select a.id, a.path, a.name, a.upload_time, a.type from t_custom_data t "
				+ "left join t_attachment a on t.attachment_id = a.id "
				+ " where t.custom_id = ? order by a.upload_time desc",
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		
		return ret;
	}

	/**
	 * 编辑客户
	 * @param custom 客户
	 * @param commun 沟通记录
	 * @param attas 附件信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public FormResult edit(TCustom custom, String commun, String attas) {
		FormResult ret = new FormResult();
		String userId = userUtil.getLoginUser().getId();
		
		//原始状态
		String oldState = null;
		if(StrUtils.isNull(custom.getId())){//新增
			custom.setId(UUID.randomUUID().toString());
			custom.setCreateTime(new Date());
			custom.setCreateUser(userId);
			custom.setLastUpdateTime(new Date());
			custom.setLastUpdateUser(userId);
			dao.save(custom);
		}else{//更新
			//获取原始客户状态
			TCustom oldCustom = dao.queryById(custom.getId(), TCustom.class);
			oldState = oldCustom.getState();
			if(oldCustom != null && 
					(("签约终止".equals(oldState) || "签约暂停".equals(oldState)) 
							&& "潜在客户".equals(custom.getState()))){
				ret.setSuccess(FormResult.ERROR);
				ret.setMessage("'签约终止'、'签约暂停'运作状态不能修改为'潜在运行'状态");
				return ret;
			}
			
			custom.setLastUpdateTime(new Date());
			custom.setLastUpdateUser(userId);
			dao.getTemplate().merge(custom);
		}
		//保存沟通记录
		if(!StrUtils.isNull(commun)){
			TCustomCommunication c = new TCustomCommunication();
			c.setId(UUID.randomUUID().toString());
			c.setContent(commun);
			c.setCreateTime(new Date());
			c.setCustomId(custom.getId());
			c.setCreateUser(userId);
			dao.save(c);
		}
		//更新附件信息
		dao.executeDelOrUpdateSql("delete from t_custom_data where custom_id = ? ",
				new Object[]{custom.getId()},
				new Type[]{StringType.INSTANCE});
		if(!StrUtils.isNull(attas)){
			String[] ids = attas.split(",");
			List<TCustomData> tAttachments = new ArrayList<TCustomData>();
			for(String id : ids){
				tAttachments.add(new TCustomData(UUID.randomUUID().toString(), custom.getId(), id));
			}
			dao.getTemplate().saveOrUpdateAll(tAttachments);
		}
		
		//保存执行团队信息
		dao.executeDelOrUpdateSql("delete from t_custom_team where custom_id = ? ",
				new Object[]{custom.getId()},
				new Type[]{StringType.INSTANCE});
		if(!StrUtils.isNull(custom.getTeam())){
			List<TCustomTeam> ts = new ArrayList<TCustomTeam>();
			String teams = custom.getTeam();
			String[] tIds = teams.split(",");
			for(String tId : tIds){
				ts.add(
						new TCustomTeam(
								UUID.randomUUID().toString(),
								tId,
								custom.getId()));
			}
			if(ts.size() > 0){
				dao.batchSave(ts);
			}
		}
		
		//修改客户状态
		if(oldState != null && !oldState.equals(custom.getState())){
			//合同状态
			String contractState = null;
			String jobState = null;
			if("签约暂停".equals(custom.getState())){
				contractState = "暂停";
				jobState = "暂停";
			}
			if("签约终止".equals(custom.getState())){
				contractState = "终止";
				jobState = "结束";
			}
			//更新最新合同状态
			if(contractState != null){
				//获取最新合同
				List<Map<String, String>> items = dao.queryListBySql(
						 "SELECT id FROM t_contract "
						+ "WHERE custom_id = ? "
						+ "ORDER BY create_time DESC LIMIT 1",
						new Object[]{custom.getId()},
						new Type[]{StringType.INSTANCE});
				if(items.size() > 0){
					String contractId = items.get(0).get("id");
					//更新合同状态
					dao.executeDelOrUpdateSql(
							"UPDATE t_contract SET state = ? WHERE id = ? ", 
									new Object[]{contractState, contractId},
									new Type[]{StringType.INSTANCE, StringType.INSTANCE});
					//更新职位状态
					dao.executeDelOrUpdateSql(
							"UPDATE t_job SET state = ? WHERE custom_id = ? and contract_id = ? ", 
									new Object[]{jobState, custom.getId(), contractId},
									new Type[]{StringType.INSTANCE, StringType.INSTANCE, StringType.INSTANCE});
				}
			}
		}
		
		ret.setSuccess(FormResult.SUCCESS);
		ret.setData(custom.getId());
		return ret;
	}

	/**
	 * 删除客户
	 * @param id
	 * @return
	 */
	@Transactional
	public FormResult del(String id){
		FormResult ret = new FormResult();
		
		//删除客户表
		dao.executeDelOrUpdateSql(
				"delete from t_custom where id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除客户沟通记录
		dao.executeDelOrUpdateSql(
				"delete from t_custom_communication where custom_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除客户附件
		attachmentService.delAttach(dao.queryListBySql(//附件文件
				" select path from t_attachment where id in ("
				+ " select attachment_id from t_custom_data where custom_id = ? ) ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		dao.executeDelOrUpdateSql(//附件表
				"delete from t_attachment where id in ("
				+ " select attachment_id from t_custom_data where custom_id = ? ) ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		dao.executeDelOrUpdateSql(//附件
				"delete from t_custom_data where custom_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除客户职位
		dao.executeDelOrUpdateSql(
				"delete from t_job where custom_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除客户合同
		dao.executeDelOrUpdateSql(
				"delete from t_contract where custom_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		//删除客户推荐简历表
		dao.executeDelOrUpdateSql(
				"delete from t_custom_resume where custom_id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("删除客户成功");
		return ret;
	}

	/**
	 * 删除沟通记录
	 * @param id
	 * @return
	 */
	@Transactional
	public FormResult delCommun(String id) {
		FormResult ret = new FormResult();
		dao.executeDelOrUpdateSql(
				"delete from t_custom_communication where id = ? ",
				new Object[]{id},
				new Type[]{StringType.INSTANCE});
		ret.setSuccess(FormResult.SUCCESS);
		ret.setMessage("删除沟通记录成功");
		return ret;
	}

	/**
	 * 查询客户沟通记录
	 * @param customId
	 * @return
	 */
	public Object getCommuns(String customId) {
		//沟通信息
		return dao.queryListBySql(
						"select t.*, getDictName(create_user) create_user_desc "
						+ "from t_custom_communication t "
						+ "where t.custom_id = ? order by t.create_time desc",
						new Object[]{customId},
						new Type[]{StringType.INSTANCE});
	}

	/**
	 * 根据客户id获取客户信息-查看客户信息使用
	 * @param id
	 * @return
	 */
	public Object getCustomViewById(String id) {
		Map<String, Object> ret = new HashMap<String, Object>();
		//客户主表
		ret.put("custom", dao.queryById(id, TCustom.class));
		//客户沟通记录
		ret.put("comms", dao.queryListBySql(
				"select t.*,getDictName(t.create_user) create_user_desc "
				+ " from t_custom_communication t where t.custom_id = ? "
				+ " order by create_time desc ", 
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		//客户附件
		ret.put("attas", dao.queryListBySql("select a.* from t_custom_data t right join t_attachment a"
				+ " on t.attachment_id = a.id where custom_id = ? ", 
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		//客户职位
		ret.put("jobs", dao.queryListBySql("select t.*,getDictName(t.team) team_desc" +
				" from t_job t where t.custom_id = ? "
				+ " order by create_time desc ", 
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		//客户执行团队
		ret.put("teams", dao.queryListBySql(
				"select u.name from t_custom_team t left join t_user u on t.user_id = u.id where t.custom_id = ? ", 
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		//客户合同
		ret.put("contracts", dao.queryListBySql(
				"select * from t_contract where custom_id = ? order by in_date desc ", 
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		//客户合同附件
		ret.put("contractAttas", dao.queryListBySql(
				"select att.* from t_attachment att,t_contract_data data,t_contract con where"
				+ " att.id = data.attachment_id and data.contract_id = con.id and con.custom_id = ? ", 
				new Object[]{id},
				new Type[]{StringType.INSTANCE}));
		//客户归属-本人？团队？
		ret.put("beyond", getCustomType(id));
		
		return ret;
	}
	
	/**
	 * 客户类型-自己
	 */
	public static final String CUSTOMTYPE_MY = "my";
	/**
	 * 客户类型-团队
	 */
	public static final String CUSTOMTYPE_TEAM = "team";
	/**
	 * 客户类型-其他
	 */
	public static final String CUSTOMTYPE_OTHER = "other";
	/**
	 * 获取客户类型-用于决定是否具有权限
	 * @param customId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getCustomType(String customId){
		String userId = getLoginUserId();
		StringBuilder sb = new StringBuilder();
		List<Map<String, String>> items = dao.queryListBySql(
				" select "
				+ " CASE "
				+ "     WHEN t.create_user = ?  "
				+ "     THEN 'my'  "
				+ "      WHEN ? IN ( "
				+ " 	     SELECT u.id FROM  t_user u WHERE  "
				+ " 	     (u.team IN ( SELECT m.user_id FROM t_custom_team m WHERE m.custom_id = t.id)) "
				+ " 	     OR "
				+ " 	     (u.id IN ( SELECT m.user_id FROM t_custom_team m WHERE m.custom_id = t.id)) "
				+ "      ) "
				+ "     THEN 'team'  "
				+ "     ELSE 'other'  "
				+ "   END AS beyond"
				+ " from t_custom t where id = ? ",
				new Object[]{userId, userId, customId},
				new Type[]{StringType.INSTANCE, StringType.INSTANCE, StringType.INSTANCE});
		if(items.size() > 0){
			return items.get(0).get("beyond");
		}else{
			return CUSTOMTYPE_OTHER;
		}
		
	}
	
}
