package cn.com.sinosoft.common.model;
// Generated 2015-6-9 21:12:46 by Hibernate Tools 3.2.2.GA


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

    public TCustomCommunication() {
    }

    public TCustomCommunication(String id, String customId, String commuDate, String content, Date createTime) {
       this.id = id;
       this.customId = customId;
       this.commuDate = commuDate;
       this.content = content;
       this.createTime = createTime;
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




}

