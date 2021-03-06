package cn.com.sinosoft.common.model;
// Generated 2015-8-18 0:35:05 by Hibernate Tools 3.2.2.GA


import java.util.Date;

/**
 * TResumeJob generated by hbm2java
 */
public class TResumeJob  implements java.io.Serializable {


     private String id;
     private String resumeId;
     private String jobId;
     private String verifyState;
     private String recomState;
     private Date createTime;
     private String createUser;
     private Date recomTime;
     private Date verifyTime;
     private String recomUser;
     private String verifyUser;
     private String mianshiCount;

    public TResumeJob() {
    }

	
    public TResumeJob(String id, String resumeId, String jobId, String createUser) {
        this.id = id;
        this.resumeId = resumeId;
        this.jobId = jobId;
        this.createUser = createUser;
    }
    public TResumeJob(String id, String resumeId, String jobId, String verifyState, String recomState, Date createTime, String createUser, Date recomTime, Date verifyTime, String recomUser, String verifyUser, String mianshiCount) {
       this.id = id;
       this.resumeId = resumeId;
       this.jobId = jobId;
       this.verifyState = verifyState;
       this.recomState = recomState;
       this.createTime = createTime;
       this.createUser = createUser;
       this.recomTime = recomTime;
       this.verifyTime = verifyTime;
       this.recomUser = recomUser;
       this.verifyUser = verifyUser;
       this.mianshiCount = mianshiCount;
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
    public String getJobId() {
        return this.jobId;
    }
    
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    public String getVerifyState() {
        return this.verifyState;
    }
    
    public void setVerifyState(String verifyState) {
        this.verifyState = verifyState;
    }
    public String getRecomState() {
        return this.recomState;
    }
    
    public void setRecomState(String recomState) {
        this.recomState = recomState;
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
    public Date getRecomTime() {
        return this.recomTime;
    }
    
    public void setRecomTime(Date recomTime) {
        this.recomTime = recomTime;
    }
    public Date getVerifyTime() {
        return this.verifyTime;
    }
    
    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }
    public String getRecomUser() {
        return this.recomUser;
    }
    
    public void setRecomUser(String recomUser) {
        this.recomUser = recomUser;
    }
    public String getVerifyUser() {
        return this.verifyUser;
    }
    
    public void setVerifyUser(String verifyUser) {
        this.verifyUser = verifyUser;
    }
    public String getMianshiCount() {
        return this.mianshiCount;
    }
    
    public void setMianshiCount(String mianshiCount) {
        this.mianshiCount = mianshiCount;
    }




}


