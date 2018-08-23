package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by angulo on 11/23/2016.
 */
public class ConsultationRecordVersion03Json implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private int id;

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

    @JsonProperty("consultationDate")
    private Date consultationDate;

    @JsonProperty("chiefComplaint")
    private String chiefComplaint;

    @JsonProperty("historyOfPresentIllness")
    private String historyOfPresentIllness;

    @JsonProperty("pastOrFamilyHistory")
    private String pastOrFamilyHistory;

    @JsonProperty("reviewOfSystems")
    private String reviewOfSystems;

    @JsonProperty("vitalsBp")
    private String vitalsBp;

    @JsonProperty("vitalsHr")
    private String vitalsHr;

    @JsonProperty("vitalsRr")
    private String vitalsRr;

    @JsonProperty("vitalsTemp")
    private String vitalsTemp;

    @JsonProperty(value = "physicalExamination", required = true)
    private String physicalExamination;

    @JsonProperty(value = "primaryDiagnosisCode", required = true)
    private String primaryDiagnosisCode;

    @JsonProperty(value = "prescribedTestOrProceduresForPrimaryDiagnosisCodeList",required = true)
    private List<String> prescribedTestOrProceduresForPrimaryDiagnosisCodeList;

    @JsonProperty("dxRemarks")
    private String dxRemarks;

    @JsonProperty(value = "otherDiagnosisContributoryToChiefComplaint")
    private List<DiagnosisTestProcedureDto> otherDiagnosisContributoryToChiefComplaintList;

    @JsonProperty(value = "otherDiagnosisNonContributoryToChiefComplaint")
    private List<DiagnosisTestProcedureDto> otherDiagnosisNonContributoryToChiefComplaintList;

    @JsonProperty("illnessType")
    private String illnessType;

    @JsonProperty(value = "planOfManagementOrNotes" , required = true)
    private String planOfManagementOrNotes;

    @JsonProperty("proceduresDoneInClinicList")
    private List<String> proceduresDoneInClinicList;

    @JsonProperty(value="contriProcedures")
    private String contriProcedures;

    @JsonProperty(value="nonContriProcedures")
    private String nonContriProcedures;

    @JsonProperty(value="disclaimerTicked")
    private Integer disclaimerTicked;

    public ConsultationRecordVersion03Json() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public String getHistoryOfPresentIllness() {
        return historyOfPresentIllness;
    }

    public void setHistoryOfPresentIllness(String historyOfPresentIllness) {
        this.historyOfPresentIllness = historyOfPresentIllness;
    }

    public String getPastOrFamilyHistory() {
        return pastOrFamilyHistory;
    }

    public void setPastOrFamilyHistory(String pastOrFamilyHistory) {
        this.pastOrFamilyHistory = pastOrFamilyHistory;
    }

    public String getReviewOfSystems() {
        return reviewOfSystems;
    }

    public void setReviewOfSystems(String reviewOfSystems) {
        this.reviewOfSystems = reviewOfSystems;
    }

    public String getVitalsBp() {
        return vitalsBp;
    }

    public void setVitalsBp(String vitalsBp) {
        this.vitalsBp = vitalsBp;
    }

    public String getVitalsHr() {
        return vitalsHr;
    }

    public void setVitalsHr(String vitalsHr) {
        this.vitalsHr = vitalsHr;
    }

    public String getVitalsRr() {
        return vitalsRr;
    }

    public void setVitalsRr(String vitalsRr) {
        this.vitalsRr = vitalsRr;
    }

    public String getVitalsTemp() {
        return vitalsTemp;
    }

    public void setVitalsTemp(String vitalsTemp) {
        this.vitalsTemp = vitalsTemp;
    }

    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public List<String> getPrescribedTestOrProceduresForPrimaryDiagnosisCodeList() {
        return prescribedTestOrProceduresForPrimaryDiagnosisCodeList;
    }

    public void setPrescribedTestOrProceduresForPrimaryDiagnosisCodeList(List<String> prescribedTestOrProceduresForPrimaryDiagnosisCodeList) {
        this.prescribedTestOrProceduresForPrimaryDiagnosisCodeList = prescribedTestOrProceduresForPrimaryDiagnosisCodeList;
    }

    public String getDxRemarks() {
        return dxRemarks;
    }

    public void setDxRemarks(String dxRemarks) {
        this.dxRemarks = dxRemarks;
    }

    public List<DiagnosisTestProcedureDto> getOtherDiagnosisContributoryToChiefComplaint() {
        return otherDiagnosisContributoryToChiefComplaintList;
    }

    public void setOtherDiagnosisContributoryToChiefComplaint(List<DiagnosisTestProcedureDto> otherDiagnosisContributoryToChiefComplaint) {
        this.otherDiagnosisContributoryToChiefComplaintList = otherDiagnosisContributoryToChiefComplaint;
    }

    public List<DiagnosisTestProcedureDto> getOtherDiagnosisNonContributoryToChiefComplaint() {
        return otherDiagnosisNonContributoryToChiefComplaintList;
    }

    public void setOtherDiagnosisNonContributoryToChiefComplaint(List<DiagnosisTestProcedureDto> otherDiagnosisNonContributoryToChiefComplaint) {
        this.otherDiagnosisNonContributoryToChiefComplaintList = otherDiagnosisNonContributoryToChiefComplaint;
    }

    public String getIllnessType() {
        return illnessType;
    }

    public void setIllnessType(String illnessType) {
        this.illnessType = illnessType;
    }

    public String getPlanOfManagementOrNotes() {
        return planOfManagementOrNotes;
    }

    public void setPlanOfManagementOrNotes(String planOfManagementOrNotes) {
        this.planOfManagementOrNotes = planOfManagementOrNotes;
    }

    public List<String> getProceduresDoneInClinic() {
        return proceduresDoneInClinicList;
    }

    public void setProceduresDoneInClinic(List<String> proceduresDoneInClinic) {
        this.proceduresDoneInClinicList = proceduresDoneInClinic;
    }

    public String getContriProcedures() {
        return contriProcedures;
    }

    public void setContriProcedures(String contriProcedures) {
        this.contriProcedures = contriProcedures;
    }

    public String getNonContriProcedures() {
        return nonContriProcedures;
    }

    public void setNonContriProcedures(String nonContriProcedures) {
        this.nonContriProcedures = nonContriProcedures;
    }

    public Integer getDisclaimerTicked() {
        return disclaimerTicked;
    }

    public void setDisclaimerTicked(Integer disclaimerTicked) {
        this.disclaimerTicked = disclaimerTicked;
    }
}
