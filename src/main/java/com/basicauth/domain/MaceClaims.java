package com.basicauth.domain;

import com.basicauth.data.MaceRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jabito on 12/01/2018.
 */
public class MaceClaims implements Serializable{

    @JsonProperty("loaNumber")
    private String loaNumber;
    @JsonProperty("controlCode")
    private String controlCode;
    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("memberName")
    private String memberName;
    @JsonProperty("visitDate")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT +8")
    private Date visitDate;
    @JsonProperty("memType")
    private String memType;
    @JsonProperty("saAmount")
    private BigDecimal saAmount;
    @JsonProperty("disapproved")
    private BigDecimal disapproved;
    @JsonProperty("advances")
    private BigDecimal advances;
    @JsonProperty("erc")
    private BigDecimal erc;
    @JsonProperty("approved")
    private BigDecimal approved;
    @JsonProperty("type")
    private String type;
    @JsonProperty("status")
    private String status;
    @JsonProperty("submittedBy")
    private String submittedBy;
    @JsonProperty("dateSubmitted")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT +8")
    private Date dateSubmitted;
    @JsonProperty("dateRecieved")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT +8")
    private Date dateRecieved;
    @JsonProperty("hospitalClinic")
    private String hospitalClinic;
    @JsonProperty("dueDate")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT +8")
    private Date dueDate;

    public MaceClaims(){}

    public MaceClaims(MaceRequest request) {
        this.memberCode = request.getMemCode();
        this.hospitalClinic = request.getRequestFromhosp();
        this.status = "PENDING";
        this.dateSubmitted = new Date();
    }

    public String getLoaNumber() {
        return loaNumber;
    }

    public void setLoaNumber(String loaNumber) {
        this.loaNumber = loaNumber;
    }

    public String getControlCode() {
        return controlCode;
    }

    public void setControlCode(String controlCode) {
        this.controlCode = controlCode;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getMemType() {
        return memType;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public BigDecimal getSaAmount() {
        return saAmount;
    }

    public void setSaAmount(BigDecimal saAmount) {
        this.saAmount = saAmount;
    }

    public BigDecimal getDisapproved() {
        return disapproved;
    }

    public void setDisapproved(BigDecimal disapproved) {
        this.disapproved = disapproved;
    }

    public BigDecimal getAdvances() {
        return advances;
    }

    public void setAdvances(BigDecimal advances) {
        this.advances = advances;
    }

    public BigDecimal getErc() {
        return erc;
    }

    public void setErc(BigDecimal erc) {
        this.erc = erc;
    }

    public BigDecimal getApproved() {
        return approved;
    }

    public void setApproved(BigDecimal approved) {
        this.approved = approved;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Date getDateRecieved() {
        return dateRecieved;
    }

    public void setDateRecieved(Date dateRecieved) {
        this.dateRecieved = dateRecieved;
    }

    public String getHospitalClinic() {
        return hospitalClinic;
    }

    public void setHospitalClinic(String hospitalClinic) {
        this.hospitalClinic = hospitalClinic;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
