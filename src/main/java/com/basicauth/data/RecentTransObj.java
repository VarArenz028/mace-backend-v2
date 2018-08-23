package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jabito on 02/11/2017.
 */
public class RecentTransObj implements Serializable{

    @JsonProperty("memCode")
    private String memCode;
    @JsonProperty("memName")
    private String memName;
    @JsonProperty("maceReqId")
    private Integer maceReqId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("serviceType")
    private String serviceType;
    @JsonProperty("approvalNo")
    private String[] approvalNo;
    @JsonProperty("requestDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date requestDateTime;
//    @JsonProperty("diagDesc")
    @JsonIgnore
    private String diagDesc;
//    @JsonProperty("costCenter")
    @JsonIgnore
    private String costCenter;
//    @JsonProperty("procedures")
    @JsonIgnore
    private String[] procedures;

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public Integer getMaceReqId() {
        return maceReqId;
    }

    public void setMaceReqId(Integer maceReqId) {
        this.maceReqId = maceReqId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String[] getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String[] approvalNo) {
        this.approvalNo = approvalNo;
    }

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String[] getProcedures() {
        return procedures;
    }

    public void setProcedures(String[] procedures) {
        this.procedures = procedures;
    }
}
