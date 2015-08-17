package cn.com.sinosoft.common.model;
// Generated 2015-8-18 0:35:05 by Hibernate Tools 3.2.2.GA


import java.util.Date;

/**
 * TContract generated by hbm2java
 */
public class TContract  implements java.io.Serializable {


     private String id;
     private String customId;
     private String no;
     private String state;
     private Date inDate;
     private String inPercentage;
     private String payway;
     private String firstPay;
     private String useLimit;
     private String otherRequire;
     private Date createTime;
     private String createUser;
     private Date updateTime;
     private String updateUser;

    public TContract() {
    }

	
    public TContract(String id, String customId, String no, String state, Date inDate) {
        this.id = id;
        this.customId = customId;
        this.no = no;
        this.state = state;
        this.inDate = inDate;
    }
    public TContract(String id, String customId, String no, String state, Date inDate, String inPercentage, String payway, String firstPay, String useLimit, String otherRequire, Date createTime, String createUser, Date updateTime, String updateUser) {
       this.id = id;
       this.customId = customId;
       this.no = no;
       this.state = state;
       this.inDate = inDate;
       this.inPercentage = inPercentage;
       this.payway = payway;
       this.firstPay = firstPay;
       this.useLimit = useLimit;
       this.otherRequire = otherRequire;
       this.createTime = createTime;
       this.createUser = createUser;
       this.updateTime = updateTime;
       this.updateUser = updateUser;
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
    public String getNo() {
        return this.no;
    }
    
    public void setNo(String no) {
        this.no = no;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public Date getInDate() {
        return this.inDate;
    }
    
    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }
    public String getInPercentage() {
        return this.inPercentage;
    }
    
    public void setInPercentage(String inPercentage) {
        this.inPercentage = inPercentage;
    }
    public String getPayway() {
        return this.payway;
    }
    
    public void setPayway(String payway) {
        this.payway = payway;
    }
    public String getFirstPay() {
        return this.firstPay;
    }
    
    public void setFirstPay(String firstPay) {
        this.firstPay = firstPay;
    }
    public String getUseLimit() {
        return this.useLimit;
    }
    
    public void setUseLimit(String useLimit) {
        this.useLimit = useLimit;
    }
    public String getOtherRequire() {
        return this.otherRequire;
    }
    
    public void setOtherRequire(String otherRequire) {
        this.otherRequire = otherRequire;
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
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateUser() {
        return this.updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }




}


