package cn.com.sinosoft.common.model;
// Generated 2015-7-2 1:30:39 by Hibernate Tools 3.2.2.GA



/**
 * TResumeDate generated by hbm2java
 */
public class TResumeDate  implements java.io.Serializable {


     private String id;
     private String resumeId;
     private String attachmentId;

    public TResumeDate() {
    }

    public TResumeDate(String id, String resumeId, String attachmentId) {
       this.id = id;
       this.resumeId = resumeId;
       this.attachmentId = attachmentId;
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
    public String getAttachmentId() {
        return this.attachmentId;
    }
    
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }




}


