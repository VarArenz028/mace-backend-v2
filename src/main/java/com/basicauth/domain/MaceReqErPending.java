package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IPC_Laptop5 on 3/1/2018.
 */
public class MaceReqErPending implements Serializable{

    private Integer transactionId;
    private Integer requestId;
    private String transCode;

    @JsonProperty("requestDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT+8")
    private Date requestDateTime;

    private String requestBy;
    private String memberCode;
    private String memberLName;
    private String memberFName;
    private String erReasonRemarks;
    private Integer primaryDiagnosisId;
    private String primaryDiagnosisIdc10;
    private String primaryDiagnosisDesc;
    private BigDecimal estimatedCost;
    private String status;
    private String hospitalCode;
    private String hospitalName;
    private Integer pageNo;
    private Integer totalSize;


    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberLName() {
        return memberLName;
    }

    public void setMemberLName(String memberLName) {
        this.memberLName = memberLName;
    }

    public String getMemberFName() {
        return memberFName;
    }

    public void setMemberFName(String memberFName) {
        this.memberFName = memberFName;
    }

    public String getErReasonRemarks() {
        return erReasonRemarks;
    }

    public void setErReasonRemarks(String erReasonRemarks) {
        this.erReasonRemarks = erReasonRemarks;
    }

    public Integer getPrimaryDiagnosisId() {
        return primaryDiagnosisId;
    }

    public void setPrimaryDiagnosisId(Integer primaryDiagnosisId) {
        this.primaryDiagnosisId = primaryDiagnosisId;
    }

    public String getPrimaryDiagnosisIdc10() {
        return primaryDiagnosisIdc10;
    }

    public void setPrimaryDiagnosisIdc10(String primaryDiagnosisIdc10) {
        this.primaryDiagnosisIdc10 = primaryDiagnosisIdc10;
    }

    public String getPrimaryDiagnosisDesc() {
        return primaryDiagnosisDesc;
    }

    public void setPrimaryDiagnosisDesc(String primaryDiagnosisDesc) {
        this.primaryDiagnosisDesc = primaryDiagnosisDesc;
    }

    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }
}
