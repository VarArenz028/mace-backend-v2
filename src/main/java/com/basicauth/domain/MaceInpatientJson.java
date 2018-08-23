package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Jabito on 29/11/2017.
 */
public class MaceInpatientJson implements Serializable {

    @JsonProperty("maceRequestId")
    private Integer maceRequestId;
    @JsonProperty("appUsername")
    private String appUsername;
    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("hospitalCode")
    private String hospitalCode;
    @JsonProperty("reasonForAdmission")
    private String reasonForAdmission;
    @JsonProperty("doctorCodes")
    private List<String> doctorCodes;
    @JsonProperty("diagCodes")
    private List<String> diagCodes;
    @JsonProperty("procCodes")
    private ProcJson[] procCodes;
    @JsonProperty("roomRate")
    private BigDecimal roomRate;
    @JsonProperty("roomNo")
    private String roomNo;
    @JsonProperty("dateTimeAdmitted")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateTimeAdmitted;
    @JsonProperty("coorContact")
    private String coorContact;
    @JsonProperty("coorEmail")
    private String coorEmail;
    @JsonProperty("memberContact")
    private String memberContact;
    @JsonProperty("searchType")
    private String searchType;
    @JsonProperty("category")
    private String category;
    @JsonProperty("roomType")
    private String roomType;
    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("requestOrigin")
    private String requestOrigin;
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("admissionType")
    private String admissionType;
    @JsonProperty("dateFrom")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateFrom;
    @JsonProperty("lengthOfStay")
    private String lengthOfStay;
    @JsonProperty("status")
    private String status;
    @JsonIgnore
    private int tempHeader;

    public int getTempHeader() {
        return tempHeader;
    }

    public void setTempHeader(int tempHeader) {
        this.tempHeader = tempHeader;
    }

    public static class ProcJson implements Serializable{
        @JsonProperty("procCode")
        private String procCode;
        @JsonProperty("procAmount")
        private BigDecimal procAmount;

        public String getProcCode() {
            return procCode;
        }

        public void setProcCode(String procCode) {
            this.procCode = procCode;
        }

        public BigDecimal getProcAmount() {
            return procAmount;
        }

        public void setProcAmount(BigDecimal procAmount) {
            this.procAmount = procAmount;
        }
    }

    //<editor-fold desc="Getters and Setters" default=collapsed>

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public String getAppUsername() {
        return appUsername;
    }

    public void setAppUsername(String appUsername) {
        this.appUsername = appUsername;
    }

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

    public List<String> getDoctorCodes() {
        return doctorCodes;
    }

    public void setDoctorCodes(List<String> doctorCodes) {
        this.doctorCodes = doctorCodes;
    }

    public List<String> getDiagCodes() {
        return diagCodes;
    }

    public void setDiagCodes(List<String> diagCodes) {
        this.diagCodes = diagCodes;
    }

    public BigDecimal getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(BigDecimal roomRate) {
        this.roomRate = roomRate;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Date getDateTimeAdmitted() {
        return dateTimeAdmitted;
    }

    public void setDateTimeAdmitted(Date dateTimeAdmitted) {
        this.dateTimeAdmitted = dateTimeAdmitted;
    }

    public String getCoorContact() {
        return coorContact;
    }

    public void setCoorContact(String coorContact) {
        this.coorContact = coorContact;
    }

    public String getCoorEmail() {
        return coorEmail;
    }

    public void setCoorEmail(String coorEmail) {
        this.coorEmail = coorEmail;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getReasonForAdmission() {
        return reasonForAdmission;
    }

    public void setReasonForAdmission(String reasonForAdmission) {
        this.reasonForAdmission = reasonForAdmission;
    }

    public ProcJson[] getProcCodes() {
        return procCodes;
    }

    public void setProcCodes(ProcJson[] procCodes) {
        this.procCodes = procCodes;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(String memberContact) {
        this.memberContact = memberContact;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getLengthOfStay() {
        return lengthOfStay;
    }

    public void setLengthOfStay(String lengthOfStay) {
        this.lengthOfStay = lengthOfStay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //</editor-fold>
}
