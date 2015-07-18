package cn.com.sinosoft.common.model;
// Generated 2015-7-18 17:13:22 by Hibernate Tools 3.2.2.GA


import java.util.Date;

/**
 * TInvoice generated by hbm2java
 */
public class TInvoice  implements java.io.Serializable {


     private String id;
     private String applyUser;
     private Date applyTime;
     private String customContract;
     private String inUser;
     private String type;
     private String property;
     private float total;
     private String state;
     private String checkUser;
     private String incomeState;
     private String comment;
     private String createUser;
     private Date createTime;

    public TInvoice() {
    }

	
    public TInvoice(String id, String applyUser, Date applyTime, String customContract, String type, float total, String state, String checkUser, String createUser) {
        this.id = id;
        this.applyUser = applyUser;
        this.applyTime = applyTime;
        this.customContract = customContract;
        this.type = type;
        this.total = total;
        this.state = state;
        this.checkUser = checkUser;
        this.createUser = createUser;
    }
    public TInvoice(String id, String applyUser, Date applyTime, String customContract, String inUser, String type, String property, float total, String state, String checkUser, String incomeState, String comment, String createUser, Date createTime) {
       this.id = id;
       this.applyUser = applyUser;
       this.applyTime = applyTime;
       this.customContract = customContract;
       this.inUser = inUser;
       this.type = type;
       this.property = property;
       this.total = total;
       this.state = state;
       this.checkUser = checkUser;
       this.incomeState = incomeState;
       this.comment = comment;
       this.createUser = createUser;
       this.createTime = createTime;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getApplyUser() {
        return this.applyUser;
    }
    
    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }
    public Date getApplyTime() {
        return this.applyTime;
    }
    
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    public String getCustomContract() {
        return this.customContract;
    }
    
    public void setCustomContract(String customContract) {
        this.customContract = customContract;
    }
    public String getInUser() {
        return this.inUser;
    }
    
    public void setInUser(String inUser) {
        this.inUser = inUser;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getProperty() {
        return this.property;
    }
    
    public void setProperty(String property) {
        this.property = property;
    }
    public float getTotal() {
        return this.total;
    }
    
    public void setTotal(float total) {
        this.total = total;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getCheckUser() {
        return this.checkUser;
    }
    
    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }
    public String getIncomeState() {
        return this.incomeState;
    }
    
    public void setIncomeState(String incomeState) {
        this.incomeState = incomeState;
    }
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
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




}


