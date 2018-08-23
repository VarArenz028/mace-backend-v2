package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by angulo on 11/14/2016.
 */
public class LoaMace implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "ID")
    private long id;

    @JsonProperty("approvalNo")
    @Column(name = "APPROVAL_NO")
    private String approvalNo;

    @JsonProperty("batchCode")
    @Column(name = "BATCH_CODE")
    private String batchCode;

    @JsonProperty("callerId")
    @Column(name = "CALLER_ID")
    private String callerId;

    @JsonProperty("callTypeId")
    @Column(name = "CALL_TYPE_ID")
    private long callTypeId;

    @JsonProperty("memberCode")
    @Column(name = "MEMBER_CODE")
    private String memberCode;

    @JsonProperty("hospitalCode")
    @Column(name = "HOSPITAL_CODE")
    private String hospitalCode;

    @JsonProperty("companyCode")
    @Column(name = "COMPANY_CODE")
    private String companyCode;

    @JsonProperty("doctorCode")
    @Column(name = "DOCTOR_CODE")
    private String doctorCode;

    @JsonProperty("diagnosisCode")
    @Column(name = "DIAGNOSIS_CODE")
    private String diagnosisCode;

    @JsonProperty("procedureCode")
    @Column(name = "PROCEDURE_CODE")
    private String procedureCode;

    @JsonProperty("type")
    @Column(name = "TYPE")
    private long type;

    @JsonProperty("room")
    @Column(name = "ROOM")
    private String room;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT +8")
    @JsonProperty("dateAdmitted")
    @Column(name = "DATE_ADMITTED")
    private Date dateAdmitted;

    @JsonProperty("diagnosis")
    @Column(name = "DIAGNOSIS")
    private String diagnosis;
    //List of Basic Tests
    @JsonProperty("diagnosisTest")
    private ArrayList<Procedure> diagnosisTest;

    @JsonProperty("procedureDesc")
    @Column(name = "PROCEDURE_DESC")
    private String procedureDesc;

    @JsonProperty("procedureAmount")
    @Column(name = "PROCEDURE_AMOUNT")
    private BigDecimal procedureAmount;

    @JsonProperty("actionTaken")
    @Column(name = "ACTION_TAKEN")
    private long actionTaken;

    @JsonProperty("updatedBy")
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT +8")
    @JsonProperty("updatedDate")
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @JsonProperty("remarks")
    @Column(name = "REMARKS")
    private String remarks;

    @JsonProperty("runningBill")
    @Column(name = "RUNNING_BILL")
    private BigDecimal runningBill;

    @JsonProperty("notes")
    @Column(name = "NOTES")
    private String notes;

    @JsonProperty("reason")
    @Column(name = "REASON")
    private String reason;

    @JsonProperty("category")
    @Column(name = "CATEGORY")
    private String category;

    @JsonProperty("memLname")
    @Column(name = "MEM_LNAME")
    private String memLname;

    @JsonProperty("memFname")
    @Column(name = "MEM_FNAME")
    private String memFname;

    @JsonProperty("memMi")
    @Column(name = "MEM_MI")
    private String memMi;

    @JsonProperty("memCompany")
    @Column(name = "MEM_COMPANY")
    private String memCompany;

    @JsonProperty("terminalNo")
    @Column(name = "TERMINAL_NO")
    private String terminalNo;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT +8")
    @JsonProperty("callDate")
    @Column(name = "CALL_DATE")
    private Date callDate;

    @JsonProperty("status")
    @Column(name = "STATUS")
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT +8")
    @JsonProperty("approvalDate" )
    @Column(name = "DATE_OF_APPROVAL")
    private Date approvalDate;

    @JsonProperty("primaryComplaint")
    @Column(name = "PRIMARY_COMPLAINT")
    private String primaryComplaint;

    @JsonProperty("disclaimerTicked")
    @Column(name = "DISCLAIMER_TICKED")
    private int disclaimerTicked;

    @JsonProperty("requestOrigin")
    @Column(name="REQUEST_ORIGIN")
    private String requestOrigin;

    @JsonProperty("diagnosisList")
    private ArrayList<MaceRequestProcedureJson> diagnosisList;
//todo Create table for Basic Test additional fields
    @JsonProperty("proceduresList")
    private ArrayList<String> proceduresList;
    //Other Diagnosis
    @JsonProperty("otherDiagnosisCode")
    private String otherDiagnosisCode;
    //Other Diagnosis
    @JsonProperty("otherDiagnosisDesc")
    private String otherDiagnosisDesc;
    //List of Other Tests
    @JsonProperty("otherDiagnosisTest")
    private ArrayList<MaceRequestProcedureJson> otherDiagnosisTest;

//
//    @JsonProperty("classification")
//    @Column(name = "CLASSIFICATION")
//    private String classification;

//
//    @JsonProperty("startTime")
//    @Column(name = "START_TIME")
//    private Date startTime;
//
//    @JsonProperty("endTime")
//    @Column(name = "END_TIME")
//    private Date endTime;
//
//    @JsonProperty("returnedCall")
//    @Column(name = "RETURNED_CALL")
//    private long returnedCall;
//
//    @JsonProperty("caller")
//    @Column(name = "CALLER")
//    private String caller;
//
//
//    @JsonProperty("location")
//    @Column(name = "LOCATION")
//    private String location;
//
//    @JsonProperty("memberType")
//    @Column(name = "MEMBER_TYPE")
//    private String memberType;
//
//    @JsonProperty("specialization")
//    @Column(name = "SPECIALIZATION")
//    private String specialization;
//
//    @JsonProperty("accredited")
//    @Column(name = "ACCREDITED")
//    private int accredited;
//




    public LoaMace() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

//    public Date getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Date startTime) {
//        this.startTime = startTime;
//    }
//
//    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }
//
//    public long getReturnedCall() {
//        return returnedCall;
//    }
//
//    public void setReturnedCall(long returnedCall) {
//        this.returnedCall = returnedCall;
//    }

    public long getCallTypeId() {
        return callTypeId;
    }

    public void setCallTypeId(long callTypeId) {
        this.callTypeId = callTypeId;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
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

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

//    public String getCaller() {
//        return caller;
//    }
//
//    public void setCaller(String caller) {
//        this.caller = caller;
//    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }

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

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public Date getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(Date dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

//    public String getMemberType() {
//        return memberType;
//    }
//
//    public void setMemberType(String memberType) {
//        this.memberType = memberType;
//    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
            this.doctorCode = doctorCode;
    }


    public long getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(long actionTaken) {
        this.actionTaken = actionTaken;
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

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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

    public BigDecimal getRunningBill() {
        return runningBill;
    }

    public void setRunningBill(BigDecimal runningBill) {
        this.runningBill = runningBill;
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

    public String getMemCompany() {
        return memCompany;
    }

    public void setMemCompany(String memCompany) {
        this.memCompany = memCompany;
    }

//    public String getSpecialization() {
//        return specialization;
//    }
//
//    public void setSpecialization(String specialization) {
//        this.specialization = specialization;
//    }
//
//    public int getAccredited() {
//        return accredited;
//    }
//
//    public void setAccredited(int accredited) {
//        this.accredited = accredited;
//    }

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

//    public String getClassification() {
//        return classification;
//    }
//
//    public void setClassification(String classification) {
//        this.classification = classification;
//    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemMi() {
        return memMi;
    }

    public void setMemMi(String memMi) {
        this.memMi = memMi;
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

    public ArrayList<String> getProceduresList() {
        return proceduresList;
    }

    public void setProceduresList(ArrayList<String> proceduresList) {
        this.proceduresList = proceduresList;
    }

    public String getOtherDiagnosisCode() {
        return otherDiagnosisCode;
    }

    public void setOtherDiagnosisCode(String otherDiagnosisCode) {
        this.otherDiagnosisCode = otherDiagnosisCode;
    }

    public String getOtherDiagnosisDesc() {
        return otherDiagnosisDesc;
    }

    public void setOtherDiagnosisDesc(String otherDiagnosisDesc) {
        this.otherDiagnosisDesc = otherDiagnosisDesc;
    }

    public ArrayList<Procedure> getDiagnosisTest() {
        return diagnosisTest;
    }

    public void setDiagnosisTest(ArrayList<Procedure> diagnosisTest) {
        this.diagnosisTest = diagnosisTest;
    }

    public ArrayList<MaceRequestProcedureJson> getDiagnosisList() {
        return diagnosisList;
    }

    public void setDiagnosisList(ArrayList<MaceRequestProcedureJson> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    public ArrayList<MaceRequestProcedureJson> getOtherDiagnosisTest() {
        return otherDiagnosisTest;
    }

    public void setOtherDiagnosisTest(ArrayList<MaceRequestProcedureJson> otherDiagnosisTest) {
        this.otherDiagnosisTest = otherDiagnosisTest;
    }
}
