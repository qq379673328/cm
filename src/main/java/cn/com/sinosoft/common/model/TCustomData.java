package cn.com.sinosoft.common.model;
// Generated 2015-6-9 21:12:46 by Hibernate Tools 3.2.2.GA



/**
 * TCustomData generated by hbm2java
 */
public class TCustomData  implements java.io.Serializable {


     private String id;
     private String customId;
     private String attachmentId;

    public TCustomData() {
    }

    public TCustomData(String id, String customId, String attachmentId) {
       this.id = id;
       this.customId = customId;
       this.attachmentId = attachmentId;
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
    public String getAttachmentId() {
        return this.attachmentId;
    }
    
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }




}


