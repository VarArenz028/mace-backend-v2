package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by angulo on 11/23/2016.
 */
public class ConsultationRecordJson implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private double id;

    @JsonProperty(value = "approvalNumber", required = true)
    private String approvalNumber;

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

    @JsonProperty(value = "doctorCode", required = true)
    private String doctorCode;

    @JsonProperty(value = "doctorName", required = true)
    private String doctorName;

    @JsonProperty(value = "companyCode")
    private String companyCode;

    @JsonProperty("illnessType")
    private String illnessType;

    @JsonProperty(value = "primaryDiagnosisCode")
    private String primaryDiagnosisCode;

    @JsonProperty(value = "diagnosisCode")
    private String diagnosisCode;

    @JsonProperty("diagnosisDesc")
    private String diagnosisDesc;

    @JsonProperty("procedureCode")
    private String procedureCode;

    @JsonProperty("procedureDesc")
    private String procedureDesc;

    @JsonProperty("complaint")
    private String complaint;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("consultationDate")
    private Date consultationDate;

    @JsonProperty("other_diagnosis")
    private String otherDiagnosis;

    @JsonProperty("other_procedure")
    private String otherProcedure;

    @JsonProperty("other_diag_non_contributory")
    private String otherDiagNonContributory;

    @JsonProperty("other_proc_non_contributory")
    private String otherProcNonContributory;


    public ConsultationRecordJson() {
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
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

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
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

    public String getIllnessType() {
        return illnessType;
    }

    public void setIllnessType(String illnessType) {
        this.illnessType = illnessType;
    }

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
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

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOtherDiagnosis() {
        return otherDiagnosis;
    }

    public void setOtherDiagnosis(String otherDiagnosis) {
        this.otherDiagnosis = otherDiagnosis;
    }

    public String getOtherProcedure() {
        return otherProcedure;
    }

    public void setOtherProcedure(String otherProcedure) {
        this.otherProcedure = otherProcedure;
    }

    public String getOtherDiagNonContributory() {
        return otherDiagNonContributory;
    }

    public void setOtherDiagNonContributory(String otherDiagNonContributory) {
        this.otherDiagNonContributory = otherDiagNonContributory;
    }

    public String getOtherProcNonContributory() {
        return otherProcNonContributory;
    }

    public void setOtherProcNonContributory(String otherProcNonContributory) {
        this.otherProcNonContributory = otherProcNonContributory;
    }
}
