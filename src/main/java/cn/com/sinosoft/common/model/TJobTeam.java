package cn.com.sinosoft.common.model;
// Generated 2015-7-25 14:48:24 by Hibernate Tools 3.2.2.GA



/**
 * TJobTeam generated by hbm2java
 */
public class TJobTeam  implements java.io.Serializable {


     private String id;
     private String userId;
     private String jobId;

    public TJobTeam() {
    }

    public TJobTeam(String id, String userId, String jobId) {
       this.id = id;
       this.userId = userId;
       this.jobId = jobId;
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
    public String getJobId() {
        return this.jobId;
    }
    
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }




}


