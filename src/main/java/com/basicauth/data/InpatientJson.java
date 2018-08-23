package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by angulo on 11/23/2016.
 */
public class InpatientJson implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private double id;

    @JsonProperty(value = "memberCode", required = true)
    private String memberCode;

    @JsonProperty("memberName")
    private String memberName;

    @JsonProperty(value = "hospitalCode", required = true)
    private String hospitalCode;

    @JsonProperty(value = "hospitalName", required = true)
    private String hospitalName;

    @JsonProperty(value = "username", required = true)
    private String username;

    @JsonProperty(value = "doctorCode")
    private String doctorCode;

    @JsonProperty(value = "doctorName", required = true)
    private String doctorName;

    @JsonProperty(value = "companyCode")
    private String companyCode;

    @JsonProperty(value = "diagnosisCode")
    private String diagnosisCode;

    @JsonProperty("roomType")
    private String roomType;

    @JsonProperty("deviceId")
    private String deviceId;

    @JsonProperty("roomNumber")
    private String roomNumber;

    @JsonProperty("roomPrice")
    private String roomPrice;

    @JsonProperty("category")
    private String category;

    @JsonProperty("procedureCode")
    private String procedureCode;

    @JsonProperty("procedureDesc")
    private String procedureDesc;

    @JsonProperty("diagnosisDesc")
    private String diagnosisDesc;

    @JsonProperty("runningBill")
    private BigDecimal runningBill;

    @JsonProperty("dateAdmitted")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateAdmitted;

    @JsonProperty("status")
    private String status;

    @JsonProperty("updatedBy")
    private String updatedBy;

    @JsonProperty("updatedDate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date updatedDate;

    @JsonProperty("searchType")
    private String searchType;


    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public BigDecimal getRunningBill() {
        return runningBill;
    }

    public void setRunningBill(BigDecimal runningBill) {
        this.runningBill = runningBill;
    }

    public String getProcedureDesc() {
        return procedureDesc;
    }

    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    public String getDiagnosisDesc() {
        return diagnosisDesc;
    }

    public void setDiagnosisDesc(String diagnosisDesc) {
        this.diagnosisDesc = diagnosisDesc;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Date getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(Date dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    //</editor-fold>
}
