package cn.com.sinosoft.common.model;
// Generated 2015-8-18 0:35:05 by Hibernate Tools 3.2.2.GA



/**
 * TCustomTeam generated by hbm2java
 */
public class TCustomTeam  implements java.io.Serializable {


     private String id;
     private String userId;
     private String customId;

    public TCustomTeam() {
    }

    public TCustomTeam(String id, String userId, String customId) {
       this.id = id;
       this.userId = userId;
       this.customId = customId;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCustomId() {
        return this.customId;
    }
    
    public void setCustomId(String customId) {
        this.customId = customId;
    }




}


