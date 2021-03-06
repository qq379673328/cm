package cn.com.sinosoft.common.model;
// Generated 2015-8-18 0:35:05 by Hibernate Tools 3.2.2.GA


import java.util.Date;

/**
 * TPub generated by hbm2java
 */
public class TPub  implements java.io.Serializable {


     private String id;
     private String content;
     private String createUser;
     private Date createTime;
     private String state;
     private String lastUpdateUser;
     private Date lastUpdateTime;
     private Date pubTime;

    public TPub() {
    }

	
    public TPub(String id, String content, String createUser, String state) {
        this.id = id;
        this.content = content;
        this.createUser = createUser;
        this.state = state;
    }
    public TPub(String id, String content, String createUser, Date createTime, String state, String lastUpdateUser, Date lastUpdateTime, Date pubTime) {
       this.id = id;
       this.content = content;
       this.createUser = createUser;
       this.createTime = createTime;
       this.state = state;
       this.lastUpdateUser = lastUpdateUser;
       this.lastUpdateTime = lastUpdateTime;
       this.pubTime = pubTime;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getLastUpdateUser() {
        return this.lastUpdateUser;
    }
    
    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }
    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }
    
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    public Date getPubTime() {
        return this.pubTime;
    }
    
    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }




}


