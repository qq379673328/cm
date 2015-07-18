package cn.com.sinosoft.common.model;
// Generated 2015-7-18 17:13:22 by Hibernate Tools 3.2.2.GA


import java.util.Date;

/**
 * TCustomCommunication generated by hbm2java
 */
public class TCustomCommunication  implements java.io.Serializable {


     private String id;
     private String customId;
     private String commuDate;
     private String content;
     private Date createTime;
     private String createUser;

    public TCustomCommunication() {
    }

	
    public TCustomCommunication(String id, String customId, String content, String createUser) {
        this.id = id;
        this.customId = customId;
        this.content = content;
        this.createUser = createUser;
    }
    public TCustomCommunication(String id, String customId, String commuDate, String content, Date createTime, String createUser) {
       this.id = id;
       this.customId = customId;
       this.commuDate = commuDate;
       this.content = content;
       this.createTime = createTime;
       this.createUser = createUser;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getCustomId() {
        return this.customId;
    }
    
    public void setCustomId(String customId) {
        this.customId = customId;
    }
    public String getCommuDate() {
        return this.commuDate;
    }
    
    public void setCommuDate(String commuDate) {
        this.commuDate = commuDate;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }




}


