package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by service.incuventure on 5/30/2017.
 */
public class RequestProceduresJson implements Serializable {

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

    @JsonProperty("diagnosisDesc")
    private String diagnosisDesc;

    @JsonProperty("searchType")
    private String searchType;

    @JsonProperty("procedureList")
    private ArrayList<ProcedureJson> procedureList;
//<editor-fold defaultstate="collapsed" desc="Getters and Setters">
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

    public String getDiagnosisDesc() {
        return diagnosisDesc;
    }

    public void setDiagnosisDesc(String diagnosisDesc) {
        this.diagnosisDesc = diagnosisDesc;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public ArrayList<ProcedureJson> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(ArrayList<ProcedureJson> procedureList) {
        this.procedureList = procedureList;
    }
    //</editor-fold>
}
