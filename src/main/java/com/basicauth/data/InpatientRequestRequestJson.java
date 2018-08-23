package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by IPC_Server on 5/15/2017.
 */
public class InpatientRequestRequestJson implements Serializable {

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("hospitalCode")
    private String hospitalCode;

    @JsonProperty("primaryDoctorCode")
    private String primaryDoctorCode;

    @JsonProperty("primaryDiagnosisCode")
    private String primaryDiagnosisCode;

    @JsonProperty("primaryDiagnosisType")
    private String primaryDiagnosisType;

    @JsonProperty("primaryDiagnosisRemarks")
    private String primaryDiagnosisRemarks;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("doctors")
    private String[] doctors;

    @JsonProperty("rooms")
    private RoomJson[] rooms;

    @JsonProperty("diagProcedureDoctors")
    private DiagnosisProcedureDoctorJson[] diagProcedureDoctorJson;

    @JsonProperty("otherServices")
    private OtherServiceJson[] otherServices;

    //<editor-fold desc="InpatientRequestRequestJson Getters and Setters" defaultstate="collapsed">
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

    public String getPrimaryDoctorCode() {
        return primaryDoctorCode;
    }

    public void setPrimaryDoctorCode(String primaryDoctorCode) {
        this.primaryDoctorCode = primaryDoctorCode;
    }

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public String getPrimaryDiagnosisType() {
        return primaryDiagnosisType;
    }

    public void setPrimaryDiagnosisType(String primaryDiagnosisType) {
        this.primaryDiagnosisType = primaryDiagnosisType;
    }

    public String getPrimaryDiagnosisRemarks() {
        return primaryDiagnosisRemarks;
    }

    public void setPrimaryDiagnosisRemarks(String primaryDiagnosisRemarks) {
        this.primaryDiagnosisRemarks = primaryDiagnosisRemarks;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public RoomJson[] getRooms() {
        return rooms;
    }

    public void setRooms(RoomJson[] rooms) {
        this.rooms = rooms;
    }

    public String[] getDoctors() {
        return doctors;
    }

    public void setDoctors(String[] doctors) {
        this.doctors = doctors;
    }

    public DiagnosisProcedureDoctorJson[] getDiagProcedureDoctorJson() {
        return diagProcedureDoctorJson;
    }

    public void setDiagProcedureDoctorJson(DiagnosisProcedureDoctorJson[] diagProcedureDoctorJson) {
        this.diagProcedureDoctorJson = diagProcedureDoctorJson;
    }

    public OtherServiceJson[] getOtherServices() {
        return otherServices;
    }

    public void setOtherServices(OtherServiceJson[] otherServices) {
        this.otherServices = otherServices;
    }
    //</editor-fold>
}
