package cn.com.sinosoft.common.model;
// Generated 2015-6-22 19:41:12 by Hibernate Tools 3.2.2.GA



/**
 * TResumeLanguage generated by hbm2java
 */
public class TResumeLanguage  implements java.io.Serializable {


     private String id;
     private String resumeId;
     private byte[] content;
     private String lanType;
     private String readAb;
     private String listenAb;

    public TResumeLanguage() {
    }

	
    public TResumeLanguage(String id, String resumeId) {
        this.id = id;
        this.resumeId = resumeId;
    }
    public TResumeLanguage(String id, String resumeId, byte[] content, String lanType, String readAb, String listenAb) {
       this.id = id;
       this.resumeId = resumeId;
       this.content = content;
       this.lanType = lanType;
       this.readAb = readAb;
       this.listenAb = listenAb;
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
    public byte[] getContent() {
        return this.content;
    }
    
    public void setContent(byte[] content) {
        this.content = content;
    }
    public String getLanType() {
        return this.lanType;
    }
    
    public void setLanType(String lanType) {
        this.lanType = lanType;
    }
    public String getReadAb() {
        return this.readAb;
    }
    
    public void setReadAb(String readAb) {
        this.readAb = readAb;
    }
    public String getListenAb() {
        return this.listenAb;
    }
    
    public void setListenAb(String listenAb) {
        this.listenAb = listenAb;
    }




}


