package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by jjosephmagculang on 3/12/2018.
 */
public class IpAuditLogsProcedure implements Serializable {

    @JsonProperty("procedureId")
    private String procedureId;
    @JsonProperty("procDesc")
    private String procDesc;
    @JsonProperty("hospAmount")
    private String hospAmount;
    @JsonProperty("actualAmount")
    private String actualAmount;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("createdDate")
    private String createdDate;

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getHospAmount() {
        return hospAmount;
    }

    public void setHospAmount(String hospAmount) {
        this.hospAmount = hospAmount;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
