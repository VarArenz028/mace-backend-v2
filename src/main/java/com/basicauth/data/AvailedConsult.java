package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by service.incuventure on 3/28/2017.
 */
public class AvailedConsult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private long id;
    @JsonProperty("approvalNo")
    private String approvalNo;
    @JsonProperty("batchCode")
    private String batchCode;
    @JsonProperty("callerId")
    private String callerId;
    @JsonProperty("callTypeId")
    private long callTypeId;
    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("hospitalCode")
    private String hospitalCode;
    @JsonProperty(value = "hospitalName", required = true)
    private String hospitalName;
    @JsonProperty(value = "username", required = true)
    private String username;
    @JsonProperty("companyCode")
    private String companyCode;
    @JsonProperty("doctorCode")
    private String doctorCode;
    @JsonProperty(value = "doctorName", required = true)
    private String doctorName;
    @JsonProperty("diagnosisCode")
    private String diagnosisCode;
    @JsonProperty("procedureCode")
    private String procedureCode;
    @JsonProperty("type")
    private long type;
    @JsonProperty("room")
    private String room;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT +8")
    @JsonProperty("dateAdmitted")
    private Date dateAdmitted;
    @JsonProperty("diagnosis")
    private String diagnosis;
    @JsonProperty("procedureDesc")
    private String procedureDesc;
    @JsonProperty("procedureAmount")
    private BigDecimal procedureAmount;
    @JsonProperty("actionTaken")
    private long actionTaken;
    @JsonProperty("updatedBy")
    private String updatedBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT +8")
    @JsonProperty("updatedDate")
    private Date updatedDate;
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("runningBill")
    private BigDecimal runningBill;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("category")
    private String category;
    @JsonProperty("memLname")
    private String memLname;
    @JsonProperty("memFname")
    private String memFname;
    @JsonProperty("memMi")
    private String memMi;
    @JsonProperty("memCompany")
    private String memCompany;
    @JsonProperty("terminalNo")
    private String terminalNo;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT +8")
    @JsonProperty("callDate")
    private Date callDate;
    @JsonProperty("status")
    private String status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT +8")
    @JsonProperty("approvalDate" )
    private Date approvalDate;
    @JsonProperty("primaryComplaint")
    private String primaryComplaint;
    @JsonProperty("prescribedTest")
    private PrescribedTest prescribedTest;

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
    @JsonProperty(value = "prescribedTestOrProcedures",required = true)
    private List<String> prescribedTestOrProcedures;
    @JsonProperty("dxRemarks")
    private String dxRemarks;
    @JsonProperty(value = "otherDiagnosisContributoryToChiefComplaint")
    private List<String> otherDiagnosisContributoryToChiefComplaint;
    @JsonProperty(value = "otherDiagnosisNonContributoryToChiefComplaint")
    private List<String> otherDiagnosisNonContributoryToChiefComplaint;
    @JsonProperty("illnessType")
    private String illnessType;
    @JsonProperty(value = "planOfManagementOrNotes" , required = true)
    private String planOfManagementOrNotes;
    @JsonProperty("proceduresDoneInClinic")
    private List<String> proceduresDoneInClinic;
    @JsonProperty(value="contriProcedures")
    private String contriProcedures;
    @JsonProperty(value="nonContriProcedures")
    private String nonContriProcedures;
    @JsonProperty(value="disclaimerTicked")
    private int disclaimerTicked;
    @JsonProperty(value="requestOrigin")
    private String requestOrigin;

    public AvailedConsult(LoaMace loa){
        this.approvalNo = loa.getApprovalNo();
        this.batchCode = loa.getBatchCode();
        this.callerId = loa.getCallerId();
        this.callTypeId = loa.getCallTypeId();
        this.memberCode = loa.getMemberCode();
        this.hospitalCode = loa.getHospitalCode();
        this.companyCode = loa.getCompanyCode();
        this.doctorCode = loa.getDoctorCode();
        this.diagnosisCode = loa.getDiagnosisCode();
        this.procedureCode = loa.getProcedureCode();
        this.type = loa.getType();
        this.room = loa.getRoom();
        this.dateAdmitted = loa.getDateAdmitted();
        this.diagnosis = loa.getDiagnosis();
        this.procedureDesc = loa.getProcedureDesc();
        this.procedureAmount = loa.getProcedureAmount();
        this.actionTaken = loa.getActionTaken();
        this.updatedBy = loa.getUpdatedBy();
        this.updatedDate = loa.getUpdatedDate();
        this.remarks = loa.getRemarks();
        this.runningBill = loa.getRunningBill();
        this.notes = loa.getNotes();
        this.reason = loa.getReason();
        this.category = loa.getCategory();
        this.memLname = loa.getMemLname();
        this.memFname = loa.getMemFname();
        this.memMi = loa.getMemMi();
        this.memCompany = loa.getMemCompany();
        this.terminalNo = loa.getTerminalNo();
        this.callDate = loa.getCallDate();
        this.status = loa.getStatus();
        this.approvalDate = loa.getApprovalDate();
        this.primaryComplaint = loa.getPrimaryComplaint();
        this.disclaimerTicked = loa.getDisclaimerTicked();
        this.requestOrigin = loa.getRequestOrigin();
    }

    public void addConsultRecord(ConsultationRecordVersion03Json cr) {
        this.consultationDate = cr.getConsultationDate();
        this.chiefComplaint = cr.getChiefComplaint();
        this.historyOfPresentIllness = cr.getHistoryOfPresentIllness();
        this.pastOrFamilyHistory = cr.getPastOrFamilyHistory();
        this.reviewOfSystems = cr.getReviewOfSystems();
        this.vitalsBp = cr.getVitalsBp();
        this.vitalsHr = cr.getVitalsHr();
        this.vitalsRr = cr.getVitalsRr();
        this.vitalsTemp = cr.getVitalsTemp();
        this.physicalExamination = cr.getPhysicalExamination();
        this.primaryDiagnosisCode = cr.getPrimaryDiagnosisCode();
        this.dxRemarks = cr.getDxRemarks();
        this.illnessType = cr.getIllnessType();
        this.planOfManagementOrNotes = cr.getPlanOfManagementOrNotes();
        this.hospitalName = cr.getHospitalName();
        this.username = cr.getUsername();
        this.doctorName = cr.getDoctorName();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public long getCallTypeId() {
        return callTypeId;
    }

    public void setCallTypeId(long callTypeId) {
        this.callTypeId = callTypeId;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(Date dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getProcedureDesc() {
        return procedureDesc;
    }

    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    public BigDecimal getProcedureAmount() {
        return procedureAmount;
    }

    public void setProcedureAmount(BigDecimal procedureAmount) {
        this.procedureAmount = procedureAmount;
    }

    public long getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(long actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getRunningBill() {
        return runningBill;
    }

    public void setRunningBill(BigDecimal runningBill) {
        this.runningBill = runningBill;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMemLname() {
        return memLname;
    }

    public void setMemLname(String memLname) {
        this.memLname = memLname;
    }

    public String getMemFname() {
        return memFname;
    }

    public void setMemFname(String memFname) {
        this.memFname = memFname;
    }

    public String getMemMi() {
        return memMi;
    }

    public void setMemMi(String memMi) {
        this.memMi = memMi;
    }

    public String getMemCompany() {
        return memCompany;
    }

    public void setMemCompany(String memCompany) {
        this.memCompany = memCompany;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getPrimaryComplaint() {
        return primaryComplaint;
    }

    public void setPrimaryComplaint(String primaryComplaint) {
        this.primaryComplaint = primaryComplaint;
    }

    public PrescribedTest getPrescribedTest() {
        return prescribedTest;
    }

    public void setPrescribedTest(PrescribedTest prescribedTest) {
        this.prescribedTest = prescribedTest;
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

    public List<String> getPrescribedTestOrProcedures() {
        return prescribedTestOrProcedures;
    }

    public void setPrescribedTestOrProcedures(List<String> prescribedTestOrProcedures) {
        this.prescribedTestOrProcedures = prescribedTestOrProcedures;
    }

    public String getDxRemarks() {
        return dxRemarks;
    }

    public void setDxRemarks(String dxRemarks) {
        this.dxRemarks = dxRemarks;
    }

    public List<String> getOtherDiagnosisContributoryToChiefComplaint() {
        return otherDiagnosisContributoryToChiefComplaint;
    }

    public void setOtherDiagnosisContributoryToChiefComplaint(List<String> otherDiagnosisContributoryToChiefComplaint) {
        this.otherDiagnosisContributoryToChiefComplaint = otherDiagnosisContributoryToChiefComplaint;
    }

    public List<String> getOtherDiagnosisNonContributoryToChiefComplaint() {
        return otherDiagnosisNonContributoryToChiefComplaint;
    }

    public void setOtherDiagnosisNonContributoryToChiefComplaint(List<String> otherDiagnosisNonContributoryToChiefComplaint) {
        this.otherDiagnosisNonContributoryToChiefComplaint = otherDiagnosisNonContributoryToChiefComplaint;
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
        return proceduresDoneInClinic;
    }

    public void setProceduresDoneInClinic(List<String> proceduresDoneInClinic) {
        this.proceduresDoneInClinic = proceduresDoneInClinic;
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

    public int getDisclaimerTicked() {
        return disclaimerTicked;
    }

    public void setDisclaimerTicked(int disclaimerTicked) {
        this.disclaimerTicked = disclaimerTicked;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }
}
