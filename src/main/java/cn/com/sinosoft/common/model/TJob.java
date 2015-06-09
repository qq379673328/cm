package cn.com.sinosoft.common.model;
// Generated 2015-6-9 21:12:46 by Hibernate Tools 3.2.2.GA


import java.util.Date;

/**
 * TJob generated by hbm2java
 */
public class TJob  implements java.io.Serializable {


     private String id;
     private String customId;
     private String contractId;
     private String name;
     private String payMin;
     private String payMax;
     private String state;
     private String team;
     private String workplace;
     private String jobType;
     private String industry;
     private String workYear;
     private Integer requirePeople;
     private String department;
     private String reportObj;
     private Integer ageMin;
     private Integer ageMax;
     private String sexLimit;
     private String eduLimit;
     private String languageLimit;
     private String jobDesc;
     private byte[] companyDesc;
     private String createUser;
     private Date createTime;
     private String lastUpdateUser;
     private Date lastUpdateTime;

    public TJob() {
    }

	
    public TJob(String id, String customId, String contractId, String name, String state, String team, String createUser, Date createTime, Date lastUpdateTime) {
        this.id = id;
        this.customId = customId;
        this.contractId = contractId;
        this.name = name;
        this.state = state;
        this.team = team;
        this.createUser = createUser;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }
    public TJob(String id, String customId, String contractId, String name, String payMin, String payMax, String state, String team, String workplace, String jobType, String industry, String workYear, Integer requirePeople, String department, String reportObj, Integer ageMin, Integer ageMax, String sexLimit, String eduLimit, String languageLimit, String jobDesc, byte[] companyDesc, String createUser, Date createTime, String lastUpdateUser, Date lastUpdateTime) {
       this.id = id;
       this.customId = customId;
       this.contractId = contractId;
       this.name = name;
       this.payMin = payMin;
       this.payMax = payMax;
       this.state = state;
       this.team = team;
       this.workplace = workplace;
       this.jobType = jobType;
       this.industry = industry;
       this.workYear = workYear;
       this.requirePeople = requirePeople;
       this.department = department;
       this.reportObj = reportObj;
       this.ageMin = ageMin;
       this.ageMax = ageMax;
       this.sexLimit = sexLimit;
       this.eduLimit = eduLimit;
       this.languageLimit = languageLimit;
       this.jobDesc = jobDesc;
       this.companyDesc = companyDesc;
       this.createUser = createUser;
       this.createTime = createTime;
       this.lastUpdateUser = lastUpdateUser;
       this.lastUpdateTime = lastUpdateTime;
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
    public String getContractId() {
        return this.contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPayMin() {
        return this.payMin;
    }
    
    public void setPayMin(String payMin) {
        this.payMin = payMin;
    }
    public String getPayMax() {
        return this.payMax;
    }
    
    public void setPayMax(String payMax) {
        this.payMax = payMax;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getTeam() {
        return this.team;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    public String getWorkplace() {
        return this.workplace;
    }
    
    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }
    public String getJobType() {
        return this.jobType;
    }
    
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
    public String getIndustry() {
        return this.industry;
    }
    
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public String getWorkYear() {
        return this.workYear;
    }
    
    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }
    public Integer getRequirePeople() {
        return this.requirePeople;
    }
    
    public void setRequirePeople(Integer requirePeople) {
        this.requirePeople = requirePeople;
    }
    public String getDepartment() {
        return this.department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getReportObj() {
        return this.reportObj;
    }
    
    public void setReportObj(String reportObj) {
        this.reportObj = reportObj;
    }
    public Integer getAgeMin() {
        return this.ageMin;
    }
    
    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }
    public Integer getAgeMax() {
        return this.ageMax;
    }
    
    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }
    public String getSexLimit() {
        return this.sexLimit;
    }
    
    public void setSexLimit(String sexLimit) {
        this.sexLimit = sexLimit;
    }
    public String getEduLimit() {
        return this.eduLimit;
    }
    
    public void setEduLimit(String eduLimit) {
        this.eduLimit = eduLimit;
    }
    public String getLanguageLimit() {
        return this.languageLimit;
    }
    
    public void setLanguageLimit(String languageLimit) {
        this.languageLimit = languageLimit;
    }
    public String getJobDesc() {
        return this.jobDesc;
    }
    
    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }
    public byte[] getCompanyDesc() {
        return this.companyDesc;
    }
    
    public void setCompanyDesc(byte[] companyDesc) {
        this.companyDesc = companyDesc;
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




}


