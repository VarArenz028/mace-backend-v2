package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import net.sourceforge.jtds.jdbc.DateTime;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by angulo on 11/14/2016.
 */
@Entity
@Table(name = "SYS_CUST_CARE_MTBL")
public class CustomerCare implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "ID")
    private long id;

    @JsonProperty("callerId")
    @Column(name = "CALLER_ID")
    private String callerId;

    @JsonProperty("startTime")
    @Column(name = "START_TIME")
    private Date startTime;

    @JsonProperty("endTime")
    @Column(name = "END_TIME")
    private Date endTime;

    @JsonProperty("returnedCall")
    @Column(name = "RETURNED_CALL")
    private long returnedCall;

    @JsonProperty("callTypeId")
    @Column(name = "CALL_TYPE_ID")
    private long callTypeId;

    @JsonProperty("callDate")
    @Column(name = "CALL_DATE")
    private Date callDate;

    @JsonProperty("updatedBy")
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @JsonProperty("updatedDate")
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @JsonProperty("hospitalCode")
    @Column(name = "HOSPITAL_CODE")
    private String hospitalCode;

    @JsonProperty("remarks")
    @Column(name = "REMARKS")
    private String remarks;

    @JsonProperty("caller")
    @Column(name = "CALLER")
    private String caller;

    @JsonProperty("companyCode")
    @Column(name = "COMPANY_CODE")
    private String companyCode;

    @JsonProperty("location")
    @Column(name = "LOCATION")
    private String location;

    @JsonProperty("diagnosisCode")
    @Column(name = "DIAGNOSIS_CODE")
    private String diagnosisCode;

    @JsonProperty("procedureCode")
    @Column(name = "PROCEDURE_CODE")
    private String procedureCode;

    @JsonProperty("procedureAmount")
    @Column(name = "PROCEDURE_AMOUNT")
    private BigDecimal procedureAmount;

    @JsonProperty("type")
    @Column(name = "TYPE")
    private long type;

    @JsonProperty("dateAdmitted")
    @Column(name = "DATE_ADMITTED")
    private Date dateAdmitted;

    @JsonProperty("memberType")
    @Column(name = "MEMBER_TYPE")
    private String memberType;

    @JsonProperty("doctorCode")
    @Column(name = "DOCTOR_CODE")
    private String doctorCode;

    @JsonProperty("doctorName")
    @Column(name = "DOCTOR_NAME")
    private String doctorName;

    @JsonProperty("actionTaken")
    @Column(name = "ACTION_TAKEN")
    private long actionTaken;

    @JsonProperty("approvalNo")
    @Column(name = "APPROVAL_NO")
    private String approvalNo;

    @JsonProperty("batchCode")
    @Column(name = "BATCH_CODE")
    private String batchCode;

    @JsonProperty("terminalNo")
    @Column(name = "TERMINAL_NO")
    private String terminalNo;

    @JsonProperty("room")
    @Column(name = "ROOM")
    private String room;

    @JsonProperty("diagnosis")
    @Column(name = "DIAGNOSIS")
    private String diagnosis;

    @JsonProperty("procedureDesc")
    @Column(name = "PROCEDURE_DESC")
    private String procedureDesc;

    @JsonProperty("procedureAmt")
    @Column(name = "PROCEDURE_AMT")
    private BigDecimal procedureAmt;

    @JsonProperty("runningBill")
    @Column(name = "RUNNING_BILL")
    private BigDecimal runningBill;

    @JsonProperty("memLname")
    @Column(name = "MEM_LNAME")
    private String memLname;

    @JsonProperty("memFname")
    @Column(name = "MEM_FNAME")
    private String memFname;

    @JsonProperty("memCompany")
    @Column(name = "MEM_COMPANY")
    private String memCompany;

    @JsonProperty("specialization")
    @Column(name = "SPECIALIZATION")
    private String specialization;

    @JsonProperty("accredited")
    @Column(name = "ACCREDITED")
    private int accredited;

    @JsonProperty("notes")
    @Column(name = "NOTES")
    private String notes;

    @JsonProperty("reason")
    @Column(name = "REASON")
    private String reason;


    @JsonProperty("category")
    @Column(name = "CATEGORY")
    private String category;


    @JsonProperty("classification")
    @Column(name = "CLASSIFICATION")
    private Integer classification;

    @JsonProperty("memberCode")
    @Column(name = "MEMBER_CODE")
    private String memberCode;

    @JsonProperty("memMi")
    @Column(name = "MEM_MI")
    private String memMi;

    @JsonProperty("searchType")
    @Column(name="searchType")
    private String searchType;

    @JsonProperty("deviceId")
    @Column(name="deviceId")
    private String deviceId;

    @JsonProperty("primaryComplaint")
    @Column(name="primaryComplaint")
    private String primaryComplaint;

    @JsonProperty("requestOrigin")
    @Column(name="requestOrigin")
    private String requestOrigin;


    public CustomerCare() {
    }


    public void setDefaults(CustomerCare c) {
        this.callerId = c.getCallerId() == null ? "" : c.getCallerId();
        this.startTime = c.getStartTime() == null ? new Date() : c.getStartTime();
        this.endTime = c.getEndTime() == null ? new Date() : c.getEndTime();
        this.updatedBy = c.getUpdatedBy()== null ? "" : c.getUpdatedBy();
        this.remarks = c.getRemarks()== null ? "" : c.getRemarks();
        this.hospitalCode = c.getHospitalCode()== null ? "" : c.getHospitalCode();
        this.procedureCode = c.getProcedureCode() == null ? "":  c.getProcedureCode() ;
        this.procedureDesc = c.getProcedureDesc() == null ? "": c.getProcedureDesc();
        this.diagnosisCode = c.getDiagnosisCode() == null ? "": c.getDiagnosisCode();
        this.diagnosis = c.getDiagnosis() == null ? "": c.getDiagnosis();
        this.caller = c.getCaller() ==  null ? "": c.getCaller() ;
        this.approvalNo = c.getApprovalNo() ==  null ? "": c.getApprovalNo() ;
        this.companyCode = c.getCompanyCode() ==  null ? "": c.getCompanyCode() ;
        this.startTime = c.getDateAdmitted() == null ? new Date() : c.getDateAdmitted();
        this.doctorCode = c.getDoctorCode()==  null ? "": c.getDoctorCode() ;
        this.doctorName= c.getDoctorName()==  null ? "": c.getDoctorName() ;
        this.room= c.getRoom()==  null ? "": c.getRoom() ;
        this.reason = c.getReason()==  null ? "" : c.getReason() ;
        this.notes = c.getNotes() == null? "": c.getNotes();
        this.classification = c.getClassification()== null ? 0: c.getClassification();
        this.location = c.getLocation() == null? "":c.getLocation();
        this.procedureAmount =  c.getProcedureAmount() == null ? BigDecimal.ZERO : c.getProcedureAmount();
        this.procedureAmt =  c.getProcedureAmt() == null ? BigDecimal.ZERO : c.getProcedureAmt();
        this.runningBill =  c.getRunningBill() == null ? BigDecimal.ZERO : c.getRunningBill();
        this.memMi = c.getMemMi() == null? "":c.getMemMi();
        this.primaryComplaint = c.getPrimaryComplaint() == null? "": c.getPrimaryComplaint();
        this.requestOrigin = c.getRequestOrigin() == null? "":c.getRequestOrigin();

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getReturnedCall() {
        return returnedCall;
    }

    public void setReturnedCall(long returnedCall) {
        this.returnedCall = returnedCall;
    }

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

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
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

    public BigDecimal getProcedureAmt() {
        return procedureAmt;
    }

    public void setProcedureAmt(BigDecimal procedureAmt) {
        this.procedureAmt = procedureAmt;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getAccredited() {
        return accredited;
    }

    public void setAccredited(int accredited) {
        this.accredited = accredited;
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

    public Integer getClassification() {
        return classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

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

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPrimaryComplaint() {
        return primaryComplaint;
    }

    public void setPrimaryComplaint(String primaryComplaint) {
        this.primaryComplaint = primaryComplaint;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }
}
