package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jabito on 21/02/2018.
 */
public class MaceInpatientAudit implements Serializable{

    @JsonProperty("auditId")
    private int auditId;
    @JsonProperty("maceRequestId")
    private int maceRequestId;
    @JsonProperty("transactionId")
    private int transactionId;
    @JsonProperty("requestOrigin")
    private String requestOrigin;
    @JsonProperty("requestedBy")
    private String requestedBy;
    @JsonProperty("auditRemarks")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private String auditRemarks;
    @JsonProperty("auditDate")
    private Date auditDate;
    @JsonProperty("memberCode")
    private String memberCode;

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public int getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(int maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getAuditRemarks() {
        return auditRemarks;
    }

    public void setAuditRemarks(String auditRemarks) {
        this.auditRemarks = auditRemarks;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }
}
