package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by IPC_Server on 5/6/2017.
 */
public class ProceduresJson implements Serializable {

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("doctorCode")
    private String doctorCode;
    // doctor code of logged in provider

    @JsonProperty("hospitalCode")
    private String hospitalCode;
    // clinic/hospital for doctor/hospital

    @JsonProperty("referenceNo")
    private String referenceNo;
    // consultation reference number

    @JsonProperty("deviceID")
    private String deviceID;
    // UDID of device used

    @JsonProperty("userID")
    private String userID;
    // user id of the logged in provider or coordinator

    @JsonProperty("app")
    private String app;
    // provider or coordinator

    @JsonProperty("diagProcedures")
    private MaceRequestProcedure[] procedures;
    // List of procedures with amounts and the corresponding diagnosis from consult record.

    public ProceduresJson() {}

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

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public MaceRequestProcedure[] getProcedures() {
        return procedures;
    }

    public void setProcedures(MaceRequestProcedure[] procedures) {
        this.procedures = procedures;
    }
}
