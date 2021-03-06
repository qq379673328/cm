package cn.com.sinosoft.common.model;
// Generated 2015-8-18 0:35:05 by Hibernate Tools 3.2.2.GA


import java.util.Date;

/**
 * TResumeCommunication generated by hbm2java
 */
public class TResumeCommunication  implements java.io.Serializable {


     private String id;
     private String resumeId;
     private String commuDate;
     private String content;
     private Date createTime;
     private String createUser;

    public TResumeCommunication() {
    }

	
    public TResumeCommunication(String id, String resumeId, String content, String createUser) {
        this.id = id;
        this.resumeId = resumeId;
        this.content = content;
        this.createUser = createUser;
    }
    public TResumeCommunication(String id, String resumeId, String commuDate, String content, Date createTime, String createUser) {
       this.id = id;
       this.resumeId = resumeId;
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
    public String getResumeId() {
        return this.resumeId;
    }
    
    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
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


