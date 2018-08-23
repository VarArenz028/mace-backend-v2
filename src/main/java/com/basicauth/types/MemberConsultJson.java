package com.basicauth.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Giancarlo Angulo.
 */
public class MemberConsultJson implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("doctorCode")
    private String doctorCode;

    @JsonProperty("username")
    private String username;

    @JsonProperty("deviceID")
    private String deviceId;

    @JsonProperty("hospitalCode")
    private String hospitalCode;

    @JsonProperty("diagnosisCode")
    private String diagnosisCode;

    @JsonProperty("procedureCode")
    private String procedureCode;

    @JsonProperty("procedureAmount")
    private BigDecimal procedureAmount;

    @JsonProperty("locationCode")
    private String locationCode;

    @JsonProperty("procedureDesc")
    private String procedureDesc;

    @JsonProperty("diagnosisDesc")
    private String diagnosisDesc;

    @JsonProperty("primaryComplaint")
    private String primaryComplaint;

    @JsonProperty("searchType")
    private String searchType;

    public MemberConsultJson() {
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public BigDecimal getProcedureAmount() {
        return procedureAmount;
    }

    public void setProcedureAmount(BigDecimal procedureAmount) {
        this.procedureAmount = procedureAmount;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
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

    public String getPrimaryComplaint() {
        return primaryComplaint;
    }

    public void setPrimaryComplaint(String primaryComplaint) {
        this.primaryComplaint = primaryComplaint;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
