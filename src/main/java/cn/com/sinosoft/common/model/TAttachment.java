package cn.com.sinosoft.common.model;
// Generated 2015-7-18 17:13:22 by Hibernate Tools 3.2.2.GA


import java.util.Date;

/**
 * TAttachment generated by hbm2java
 */
public class TAttachment  implements java.io.Serializable {


     private String id;
     private String module;
     private String path;
     private String name;
     private Date uploadTime;
     private String type;
     private String isUse;
     private Double size;

    public TAttachment() {
    }

	
    public TAttachment(String id, String path, String name, Date uploadTime, String type, String isUse) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.uploadTime = uploadTime;
        this.type = type;
        this.isUse = isUse;
    }
    public TAttachment(String id, String module, String path, String name, Date uploadTime, String type, String isUse, Double size) {
       this.id = id;
       this.module = module;
       this.path = path;
       this.name = name;
       this.uploadTime = uploadTime;
       this.type = type;
       this.isUse = isUse;
       this.size = size;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getModule() {
        return this.module;
    }
    
    public void setModule(String module) {
        this.module = module;
    }
    public String getPath() {
        return this.path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Date getUploadTime() {
        return this.uploadTime;
    }
    
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getIsUse() {
        return this.isUse;
    }
    
    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }
    public Double getSize() {
        return this.size;
    }
    
    public void setSize(Double size) {
        this.size = size;
    }




}


