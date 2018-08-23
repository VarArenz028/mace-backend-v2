package com.basicauth.domain;

import com.basicauth.data.CustomerCare;
import com.basicauth.data.EmergencyRoomInquiryJson;
import com.basicauth.data.MaceRequestErJson;
import com.basicauth.data.MemberDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * A MaceReqER.
 */
@Entity
public class MaceReqEr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    @JsonProperty("transactionId")
    private Integer transactionId;

    @Column(name = "macerequest_id")
    @JsonProperty("maceRequestId")
    private Integer maceRequestId;

    @Column(name = "request_from")
    @JsonProperty("requestFrom")
    private String requestFrom;

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @Column(name = "status_remarks")
    @JsonProperty("statusRemarks")
    private String statusRemarks;

    @Column(name = "approved_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    @JsonProperty("approvedOn")
    private Date approvedOn;

    @Column(name = "approved_by")
    @JsonProperty("approvedBy")
    private String approvedBy;

    @Column(name = "approval_remarks")
    @JsonProperty("approvalRemarks")
    private String approvalRemarks;

    @Column(name = "disapproved_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    @JsonProperty("disapprovedOn")
    private Date disapprovedOn;

    @Column(name = "disapproved_by")
    @JsonProperty("disapprovedBy")
    private String disapprovedBy;

    @Column(name = "disapproval_remarks")
    @JsonProperty("disapprovalRemarks")
    private String disapprovalRemarks;

    @Column(name = "disapproval_reason")
    @JsonProperty("disapprovalReason")
    private String disapprovalReason;

    @Column(name = "loa_no")
    @JsonProperty("loaNo")
    private String loaNo;

    @Column(name = "validfrom")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    @JsonProperty("validfrom")
    private Date validfrom;

    @Column(name = "validto")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    @JsonProperty("validto")
    private Date validto;

    @Column(name = "admitted_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    @JsonProperty("admittedOn")
    private Date admittedOn;

    @Column(name = "roomboard")
    @JsonProperty("roomboard")
    private String roomboard;

    @Column(name = "room_no")
    @JsonProperty("roomNo")
    private String roomNo;

    @Column(name = "roomrate_perday", precision=10, scale=2)
    @JsonProperty("roomratePerday")
    private BigDecimal roomratePerday;

    @Column(name = "record_submitted_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    @JsonProperty("recordSubmittedOn")
    private Date recordSubmittedOn;

    @Column(name = "record_submitted_by")
    @JsonProperty("recordSubmittedBy")
    private String recordSubmittedBy;

    @Column(name = "er_reason_remarks")
    @JsonProperty("erReasonRemarks")
    private String erReasonRemarks;

    @Column(name = "doc_hosp_id")
    @JsonProperty("docHospId")
    private Long docHospId;

    @Column(name = "doctor_code")
    @JsonProperty("doctorCode")
    private String doctorCode;

    @Column(name = "hospital_code")
    @JsonProperty("hospitalCode")
    private String hospitalCode;

    @Column(name = "estimated_cost", precision=10, scale=2)
    @JsonProperty("estimatedCost")
    private BigDecimal estimatedCost;

    @Column(name = "primary_diagnosis_code")
    @JsonProperty("primaryDiagnosisCode")
    private String primaryDiagnosisCode;

    @Column(name = "primary_diagnosis_icd_10")
    @JsonProperty("primaryDiagnosisIcd10")
    private String primaryDiagnosisIcd10;

    @Column(name = "primary_diagnosis_desc")
    @JsonProperty("primaryDiagnosisDesc")
    private String primaryDiagnosisDesc;

    @Column(name = "disease_type")
    @JsonProperty("diseaseType")
    private String diseaseType;

    @Column(name = "dx_remarks")
    @JsonProperty("dxRemarks")
    private String dxRemarks;

    @Column(name = "discharged_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    @JsonProperty("dischargedOn")
    private Date dischargedOn;

    @Column(name = "dischargeupdate_by")
    @JsonProperty("dischargeupdateBy")
    private String dischargeupdateBy;

    @Column(name = "lastupdate_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    @JsonProperty("lastupdateOn")
    private Date lastupdateOn;

    @Column(name = "lastupdate_by")
    @JsonProperty("lastupdateBy")
    private String lastupdateBy;

    @Column(name = "pec_limit")
    @JsonProperty("pecLimit")
    private String pecLimit;

    @Column(name = "pec_remlimit")
    @JsonProperty("pecRemlimit")
    private String pecRemlimit;

    @Column(name = "dd_limit")
    @JsonProperty("ddLimit")
    private String ddLimit;

    @Column(name = "dd_remlimit")
    @JsonProperty("ddRemlimit")
    private String ddRemlimit;

    @Column(name = "disposition")
    @JsonProperty("disposition")
    private String disposition;

    @Column(name = "disp_remarks")
    @JsonProperty("dispRemarks")
    private String dispRemarks;

    @Column(name= "trans_code")
    @JsonProperty("transCode")
    private String transCode;

    public MaceReqEr(){
        this.status = "APPROVED";
        this.statusRemarks = "Approved automatically by the system.";
        this.approvalRemarks = "Procedures - Approved";
        this.validfrom = new Date();
        this.validto = new Date();
        this.admittedOn = new Date();
        this.approvedOn = new Date();
    }

    public MaceReqEr(CustomerCare c, MemberDetails md, MaceErJSON ej){
        this.requestFrom = c.getUpdatedBy();
        this.status = "APPROVED";
        this.statusRemarks = "Approved automatically by the system.";
        this.approvedOn = new Date();
        this.approvedBy = c.getBatchCode();
        this.approvalRemarks = "Procedures - Approved";
        this.validfrom = new Date();
        this.validto = new Date();
        this.admittedOn = new Date();
        this.hospitalCode = ej.getHospitalCode();
        this.erReasonRemarks= ej.getReasonChiefComplaint();
    }

    public MaceReqEr(CustomerCare c, MemberDetails md, EmergencyRoomInquiryJson ej){
        this.requestFrom = c.getUpdatedBy();
        this.status = "APPROVED";
        this.statusRemarks = "Approved automatically by the system.";
        this.approvedOn = new Date();
        this.approvedBy = c.getBatchCode();
        this.approvalRemarks = "Procedures - Approved";
        this.validfrom = new Date();
        this.validto = new Date();
        this.admittedOn = new Date();
        this.hospitalCode = ej.getHospitalCode();
        this.erReasonRemarks= ej.getErReason();
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusRemarks() {
        return statusRemarks;
    }

    public void setStatusRemarks(String statusRemarks) {
        this.statusRemarks = statusRemarks;
    }

    public Date getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(Date approvedOn) {
        this.approvedOn = approvedOn;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovalRemarks() {
        return approvalRemarks;
    }

    public void setApprovalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks;
    }

    public Date getDisapprovedOn() {
        return disapprovedOn;
    }

    public void setDisapprovedOn(Date disapprovedOn) {
        this.disapprovedOn = disapprovedOn;
    }

    public String getDisapprovedBy() {
        return disapprovedBy;
    }

    public void setDisapprovedBy(String disapprovedBy) {
        this.disapprovedBy = disapprovedBy;
    }

    public String getDisapprovalRemarks() {
        return disapprovalRemarks;
    }

    public void setDisapprovalRemarks(String disapprovalRemarks) {
        this.disapprovalRemarks = disapprovalRemarks;
    }

    public String getDisapprovalReason() {
        return disapprovalReason;
    }

    public void setDisapprovalReason(String disapprovalReason) {
        this.disapprovalReason = disapprovalReason;
    }

    public String getLoaNo() {
        return loaNo;
    }

    public void setLoaNo(String loaNo) {
        this.loaNo = loaNo;
    }

    public Date getValidfrom() {
        return validfrom;
    }

    public void setValidfrom(Date validfrom) {
        this.validfrom = validfrom;
    }

    public Date getValidto() {
        return validto;
    }

    public void setValidto(Date validto) {
        this.validto = validto;
    }

    public Date getAdmittedOn() {
        return admittedOn;
    }

    public void setAdmittedOn(Date admittedOn) {
        this.admittedOn = admittedOn;
    }

    public String getRoomboard() {
        return roomboard;
    }

    public void setRoomboard(String roomboard) {
        this.roomboard = roomboard;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public BigDecimal getRoomratePerday() {
        return roomratePerday;
    }

    public void setRoomratePerday(BigDecimal roomratePerday) {
        this.roomratePerday = roomratePerday;
    }

    public Date getRecordSubmittedOn() {
        return recordSubmittedOn;
    }

    public void setRecordSubmittedOn(Date recordSubmittedOn) {
        this.recordSubmittedOn = recordSubmittedOn;
    }

    public String getRecordSubmittedBy() {
        return recordSubmittedBy;
    }

    public void setRecordSubmittedBy(String recordSubmittedBy) {
        this.recordSubmittedBy = recordSubmittedBy;
    }

    public String getErReasonRemarks() {
        return erReasonRemarks;
    }

    public void setErReasonRemarks(String erReasonRemarks) {
        this.erReasonRemarks = erReasonRemarks;
    }

    public Long getDocHospId() {
        return docHospId;
    }

    public void setDocHospId(Long docHospId) {
        this.docHospId = docHospId;
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

    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public String getPrimaryDiagnosisIcd10() {
        return primaryDiagnosisIcd10;
    }

    public void setPrimaryDiagnosisIcd10(String primaryDiagnosisIcd10) {
        this.primaryDiagnosisIcd10 = primaryDiagnosisIcd10;
    }

    public String getPrimaryDiagnosisDesc() {
        return primaryDiagnosisDesc;
    }

    public void setPrimaryDiagnosisDesc(String primaryDiagnosisDesc) {
        this.primaryDiagnosisDesc = primaryDiagnosisDesc;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getDxRemarks() {
        return dxRemarks;
    }

    public void setDxRemarks(String dxRemarks) {
        this.dxRemarks = dxRemarks;
    }

    public Date getDischargedOn() {
        return dischargedOn;
    }

    public void setDischargedOn(Date dischargedOn) {
        this.dischargedOn = dischargedOn;
    }

    public String getDischargeupdateBy() {
        return dischargeupdateBy;
    }

    public void setDischargeupdateBy(String dischargeupdateBy) {
        this.dischargeupdateBy = dischargeupdateBy;
    }

    public Date getLastupdateOn() {
        return lastupdateOn;
    }

    public void setLastupdateOn(Date lastupdateOn) {
        this.lastupdateOn = lastupdateOn;
    }

    public String getLastupdateBy() {
        return lastupdateBy;
    }

    public void setLastupdateBy(String lastupdateBy) {
        this.lastupdateBy = lastupdateBy;
    }

    public String getPecLimit() {
        return pecLimit;
    }

    public void setPecLimit(String pecLimit) {
        this.pecLimit = pecLimit;
    }

    public String getPecRemlimit() {
        return pecRemlimit;
    }

    public void setPecRemlimit(String pecRemlimit) {
        this.pecRemlimit = pecRemlimit;
    }

    public String getDdLimit() {
        return ddLimit;
    }

    public void setDdLimit(String ddLimit) {
        this.ddLimit = ddLimit;
    }

    public String getDdRemlimit() {
        return ddRemlimit;
    }

    public void setDdRemlimit(String ddRemlimit) {
        this.ddRemlimit = ddRemlimit;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getDispRemarks() {
        return dispRemarks;
    }

    public void setDispRemarks(String dispRemarks) {
        this.dispRemarks = dispRemarks;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }
}
