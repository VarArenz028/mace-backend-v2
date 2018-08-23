package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by macbookpro on 2/21/18.
 */
public class MaceErJSON {
//    @JsonProperty("maceRequestId")
//    private Integer maceRequestId;
    @JsonProperty("appUsername")
    private String appUsername;
    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("hospitalCode")
    private String hospitalCode;
    @JsonProperty("reasonChiefComplaint")
    private String reasonChiefComplaint;
    @JsonProperty("doctorCodes")
    private List<String> doctorCodes;
    @JsonProperty("diagCodes")
    private List<String> diagCodes;
    @JsonProperty("procCodes")
    private MaceErJSON.ProcJson[] procCodes;
    @JsonProperty("dateTimeSubmitted")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateTimeSubmitted;
    @JsonProperty("coorContact")
    private String coorContact;
    @JsonProperty("coorEmail")
    private String coorEmail;
//    @JsonProperty("memberContact")
//    private String memberContact;
    @JsonProperty("searchType")
    private String searchType;
    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("requestOrigin")
    private String requestOrigin;
    @JsonProperty("remarks")
    private String remarks;

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

    public String getReasonChiefComplaint() {
        return reasonChiefComplaint;
    }

    public void setReasonChiefComplaint(String reasonChiefComplaint) {
        this.reasonChiefComplaint = reasonChiefComplaint;
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

    public ProcJson[] getProcCodes() {
        return procCodes;
    }

    public void setProcCodes(ProcJson[] procCodes) {
        this.procCodes = procCodes;
    }

    public Date getDateTimeSubmitted() {
        return dateTimeSubmitted;
    }

    public void setDateTimeSubmitted(Date dateTimeSubmitted) {
        this.dateTimeSubmitted = dateTimeSubmitted;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public static class ProcJson implements Serializable {
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

}
