package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by angulo on 11/23/2016.
 */
public class OtherTestJson implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonProperty("diagnosisCode")
    private String diagnosisCode;

    @JsonProperty("diagnosisDesc")
    private String diagnosisDesc;

    @JsonProperty("procedureCode")
    private String procedureCode;

    @JsonProperty("procedureDesc")
    private String procedureDesc;

    @JsonProperty("diagnosisList")
    private ArrayList<String> diagnosisList;

    @JsonProperty("procedureList")
    private ArrayList<ProcedureJson> procedureList;

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("hospitalCode")
    private String hospitalCode;

    @JsonProperty("username")
    private String username;

    @JsonProperty("doctorCode")
    private String doctorCode;

    @JsonProperty("deviceId")
    private String deviceId;

    @JsonProperty("totalProcAmount")
    private BigDecimal totalProcAmount;

    @JsonProperty("locationCode")
    private String locationCode;

    @JsonProperty("primaryComplaint")
    private String primaryComplaint;

    @JsonProperty("searchType")
    private String searchType;


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

    public String getProcedureDesc() {
        return procedureDesc;
    }

    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    public ArrayList<String> getDiagnosisList() {
        return diagnosisList;
    }

    public void setDiagnosisList(ArrayList<String> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    public ArrayList<ProcedureJson> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(ArrayList<ProcedureJson> procedureList) {
        this.procedureList = procedureList;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public BigDecimal getTotalProcAmount() {
        return totalProcAmount;
    }

    public void setTotalProcAmount(BigDecimal totalProcAmount) {
        this.totalProcAmount = totalProcAmount;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
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
