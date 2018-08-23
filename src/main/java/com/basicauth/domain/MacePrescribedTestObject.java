package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Jabito on 09/02/2018.
 */
public class MacePrescribedTestObject implements Serializable{

    @JsonProperty("prescribedTestId")
    private int prescribedTestId;
    @JsonProperty("reqDiagId")
    private int reqDiagId;
    @JsonProperty("maceRequestId")
    private int maceRequestId;
    @JsonProperty("consTransactionId")
    private int consTransactionId;
    @JsonProperty("procCode")
    private String procCode;
    @JsonProperty("procDesc")
    private String procDesc;
    @JsonProperty("group")
    private String group;
    @JsonProperty("procType")
    private String procType;
    @JsonProperty("procTypeDesc")
    private String procTypeDesc;
    @JsonProperty("procClass")
    private String procClass;
    @JsonProperty("procClassDesc")
    private String procClassDesc;
    @JsonProperty("approvalTypeId")
    private int approvalTypeId;
    @JsonProperty("approvalType")
    private String approvalType;
    @JsonProperty("costCenterId")
    private int costCenterId;
    @JsonProperty("costCenter")
    private String costCenter;
    @JsonProperty("status")
    private int status;
    @JsonProperty("maceSubType")
    private int maceSubType;

    public int getPrescribedTestId() {
        return prescribedTestId;
    }

    public void setPrescribedTestId(int prescribedTestId) {
        this.prescribedTestId = prescribedTestId;
    }

    public int getReqDiagId() {
        return reqDiagId;
    }

    public void setReqDiagId(int reqDiagId) {
        this.reqDiagId = reqDiagId;
    }

    public int getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(int maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public int getConsTransactionId() {
        return consTransactionId;
    }

    public void setConsTransactionId(int consTransactionId) {
        this.consTransactionId = consTransactionId;
    }

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getProcType() {
        return procType;
    }

    public void setProcType(String procType) {
        this.procType = procType;
    }

    public String getProcTypeDesc() {
        return procTypeDesc;
    }

    public void setProcTypeDesc(String procTypeDesc) {
        this.procTypeDesc = procTypeDesc;
    }

    public String getProcClass() {
        return procClass;
    }

    public void setProcClass(String procClass) {
        this.procClass = procClass;
    }

    public String getProcClassDesc() {
        return procClassDesc;
    }

    public void setProcClassDesc(String procClassDesc) {
        this.procClassDesc = procClassDesc;
    }

    public int getApprovalTypeId() {
        return approvalTypeId;
    }

    public void setApprovalTypeId(int approvalTypeId) {
        this.approvalTypeId = approvalTypeId;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public int getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(int costCenterId) {
        this.costCenterId = costCenterId;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMaceSubType() {
        return maceSubType;
    }

    public void setMaceSubType(int maceSubType) {
        this.maceSubType = maceSubType;
    }
}
