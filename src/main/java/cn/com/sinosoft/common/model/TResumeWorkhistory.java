package cn.com.sinosoft.common.model;
// Generated 2015-8-18 0:35:05 by Hibernate Tools 3.2.2.GA


import java.util.Date;

/**
 * TResumeWorkhistory generated by hbm2java
 */
public class TResumeWorkhistory  implements java.io.Serializable {


     private String id;
     private String resumeId;
     private String content;
     private String timeBegin;
     private String timeEnd;
     private String company;
     private Date createTime;
     private String createUser;
     private String zhiwei;
     private String hangye;

    public TResumeWorkhistory() {
    }

	
    public TResumeWorkhistory(String id, String resumeId, String content) {
        this.id = id;
        this.resumeId = resumeId;
        this.content = content;
    }
    public TResumeWorkhistory(String id, String resumeId, String content, String timeBegin, String timeEnd, String company, Date createTime, String createUser, String zhiwei, String hangye) {
       this.id = id;
       this.resumeId = resumeId;
       this.content = content;
       this.timeBegin = timeBegin;
       this.timeEnd = timeEnd;
       this.company = company;
       this.createTime = createTime;
       this.createUser = createUser;
       this.zhiwei = zhiwei;
       this.hangye = hangye;
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
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    public String getTimeBegin() {
        return this.timeBegin;
    }
    
    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }
    public String getTimeEnd() {
        return this.timeEnd;
    }
    
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(String company) {
        this.company = company;
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
    public String getZhiwei() {
        return this.zhiwei;
    }
    
    public void setZhiwei(String zhiwei) {
        this.zhiwei = zhiwei;
    }
    public String getHangye() {
        return this.hangye;
    }
    
    public void setHangye(String hangye) {
        this.hangye = hangye;
    }




}


