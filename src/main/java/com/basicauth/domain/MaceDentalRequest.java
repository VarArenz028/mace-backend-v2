package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaceDentalRequest{


    private int dentalReqId;
    private String approvalNo;
    private String memberCode;
    private String requestThrough;
    private String requestFrom;
    private String requestBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT+8")
    private Date requestDateTime;
    private BigDecimal totalUtilization;
    private String status;
    private String clinicCode;
    private String dentistCode;
    private String updatedBy;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT+8")
    private Date updatedOn;

    public MaceDentalRequest(){}

    public MaceDentalRequest(RequestDentalJson requestJson) {
        this.memberCode = requestJson.getMemberCode();
        this.requestThrough = requestJson.getRequestThrough();
        this.requestFrom = requestJson.getRequestFrom();
        this.requestBy = requestJson.getRequestBy();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            this.requestDateTime = format.parse(requestJson.getRequestDateTime());
        }catch (Exception e){}
        this.totalUtilization = requestJson.getTotalUtilization();
        this.clinicCode = requestJson.getClinicCode();
        this.dentistCode = requestJson.getDentistCode();
    }

    //<editor-fold desc="Getters and Setters" defaultState="collapsed">
    public int getDentalReqId() {
        return dentalReqId;
    }

    public void setDentalReqId(int dentalReqId) {
        this.dentalReqId = dentalReqId;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getRequestThrough() {
        return requestThrough;
    }

    public void setRequestThrough(String requestThrough) {
        this.requestThrough = requestThrough;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public BigDecimal getTotalUtilization() {
        return totalUtilization;
    }

    public void setTotalUtilization(BigDecimal totalUtilization) {
        this.totalUtilization = totalUtilization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClinicCode() {
        return clinicCode;
    }

    public void setClinicCode(String clinicCode) {
        this.clinicCode = clinicCode;
    }

    public String getDentistCode() {
        return dentistCode;
    }

    public void setDentistCode(String dentistCode) {
        this.dentistCode = dentistCode;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    //</editor-fold>
}
