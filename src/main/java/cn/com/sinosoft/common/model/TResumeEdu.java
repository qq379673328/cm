package cn.com.sinosoft.common.model;
// Generated 2015-6-9 21:12:46 by Hibernate Tools 3.2.2.GA



/**
 * TResumeEdu generated by hbm2java
 */
public class TResumeEdu  implements java.io.Serializable {


     private String id;
     private String resumeId;
     private byte[] content;

    public TResumeEdu() {
    }

    public TResumeEdu(String id, String resumeId, byte[] content) {
       this.id = id;
       this.resumeId = resumeId;
       this.content = content;
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




}


