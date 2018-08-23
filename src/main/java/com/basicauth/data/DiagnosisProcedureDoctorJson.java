package com.basicauth.data;

import com.basicauth.domain.MaceReqIpDiag;
import com.basicauth.domain.MaceReqIpDiagproc;
import com.basicauth.domain.MaceReqIpDoctor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by service.incuventure on 5/29/2017.
 */
public class DiagnosisProcedureDoctorJson implements Serializable {

    @JsonProperty("maceReqIpDiag")
    private MaceReqIpDiag maceReqIpDiag;

    @JsonProperty("maceReqIpDiagProc")
    private MaceReqIpDiagproc maceReqIpDiagProc;

    @JsonProperty("maceReqIpDoctor")
    private MaceReqIpDoctor maceReqIpDoctor;

    @JsonProperty("diagnosisCode")
    private String diagnosisCode;

    @JsonProperty("procedureCode")
    private String procedureCode;

    @JsonProperty("doctorCode")
    private String doctorCode;

    @JsonProperty("fee")
    private Double fee;

    @JsonProperty("type")
    private String type;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("status")
    private Integer status;

    //<editor-fold desc="DiagnosisProcedureJson Getters and Setters" defaultstate="collapsed">
    public MaceReqIpDiag getMaceReqIpDiag() {
        return maceReqIpDiag;
    }

    public void setMaceReqIpDiag(MaceReqIpDiag maceReqIpDiag) {
        this.maceReqIpDiag = maceReqIpDiag;
    }

    public MaceReqIpDiagproc getMaceReqIpDiagProc() {
        return maceReqIpDiagProc;
    }

    public void setMaceReqIpDiagProc(MaceReqIpDiagproc maceReqIpDiagProc) {
        this.maceReqIpDiagProc = maceReqIpDiagProc;
    }

    public MaceReqIpDoctor getMaceReqIpDoctor() {
        return maceReqIpDoctor;
    }

    public void setMaceReqIpDoctor(MaceReqIpDoctor maceReqIpDoctor) {
        this.maceReqIpDoctor = maceReqIpDoctor;
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

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    //</editor-fold>
}
