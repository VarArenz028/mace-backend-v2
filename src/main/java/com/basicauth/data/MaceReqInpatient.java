package com.basicauth.data;

import com.basicauth.domain.MaceInpatientJson;
import com.basicauth.domain.MaceInpatientPortalJson;
import com.basicauth.domain.MaceReqIpDiag;
import com.basicauth.domain.MaceReqOpDiag;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by service.incuventure on 5/29/2017.
 */
@Entity
@Table(name = "macereq_inpatient")
public class MaceReqInpatient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "macerequest_id")
    private Integer maceRequestId;

    @Column(name = "request_from")
    private String requestFrom;

    @Column(name = "status")
    private String status;

    @Column(name = "status_remarks")
    private String statusRemarks;

    @Column(name = "approved_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date approvedOn;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approval_remarks")
    private String approvalRemarks;

    @Column(name = "disapproved_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date disapprovedOn;

    @Column(name = "disapproved_by")
    private String disapprovedBy;

    @Column(name = "disapproval_remarks")
    private String disapprovalRemarks;

    @Column(name = "disapproval_reason")
    private String disapprovalReason;

    @Column(name = "loa_no")
    private String loaNo;

    @Column(name = "validfrom")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date validfrom;

    @Column(name = "validto")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date validto;

    @Column(name = "admitted_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date admittedOn;

    @Column(name = "roomboard")
    private String roomboard;

    @Column(name = "room_no")
    private String roomNo;

    @Column(name = "roomrate_perday", precision=10, scale=2)
    private BigDecimal roomratePerday;

    @Column(name = "record_submitted_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date recordSubmittedOn;

    @Column(name = "record_submitted_by")
    private String recordSubmittedBy;

    @Column(name = "ip_reason_remarks")
    private String ipReasonRemarks;

    @Column(name = "doc_hosp_id")
    private Long docHospId;

    @Column(name = "doctor_code")
    private String doctorCode;

    @Column(name = "hospital_code")
    private String hospitalCode;

    @Column(name = "estimated_cost", precision=10, scale=2)
    private BigDecimal estimatedCost;

    @Column(name = "primary_diagnosis_code")
    private String primaryDiagnosisCode;

    @Column(name = "primary_diagnosis_icd_10")
    private String primaryDiagnosisIcd10;

    @Column(name = "primary_diagnosis_desc")
    private String primaryDiagnosisDesc;

    @Column(name = "disease_type")
    private String diseaseType;

    @Column(name = "dx_remarks")
    private String dxRemarks;

    @Column(name = "discharged_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dischargedOn;

    @Column(name = "dischargeupdate_by")
    private String dischargeupdateBy;

    @Column(name = "lastupdate_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date lastupdateOn;

    @Column(name = "lastupdate_by")
    private String lastupdateBy;

    @Column(name = "pec_limit")
    private String pecLimit;

    @Column(name = "pec_remlimit")
    private String pecRemlimit;

    @Column(name = "dd_limit")
    private String ddLimit;

    @Column(name = "dd_remlimit")
    private String ddRemlimit;

    @Column(name = "disposition")
    private String disposition;

    @Column(name = "disp_remarks")
    private String dispRemarks;

    @Column(name= "trans_code")
    private String transCode;

    private String coorContact;
    private String coorEmail;
    private String memContact;

    public String getCoorContact() {
        return coorContact;
    }

    public void setCoorContact(String coorContact) {
        this.coorContact = coorContact;
    }

    public String getCoorEmail() {
        return coorEmail;
    }

    public void setCoorEmail(String coorEmail) {
        this.coorEmail = coorEmail;
    }

    public String getMemContact() {
        return memContact;
    }

    public void setMemContact(String memContact) {
        this.memContact = memContact;
    }

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private MaceRequest maceRequest;

    private String requestCode;

    public MaceReqInpatient(){}

    public MaceReqInpatient(CustomerCare c, MemberDetails md, InpatientJson ij){
        this.requestFrom = c.getUpdatedBy();
        this.status = "APPROVED";
        this.statusRemarks = "Approved automatically by the system.";
        this.approvedOn = new Date();
        this.approvedBy = c.getBatchCode();
        this.approvalRemarks = "Procedures - Approved";
        this.validfrom = new Date();
        this.validto = new Date();
        this.admittedOn = new Date();
        this.roomboard = ij.getRoomType();
        this.roomNo = ij.getRoomNumber();
        try {
            this.roomratePerday = BigDecimal.valueOf(Double.valueOf(ij.getRoomPrice()));
        }catch(Exception e){
            this.roomratePerday = BigDecimal.ZERO;
        }
        this.doctorCode = ij.getDoctorCode();
        this.hospitalCode = ij.getHospitalCode();
        this.primaryDiagnosisCode = ij.getDiagnosisCode();
        this.primaryDiagnosisDesc = ij.getDiagnosisDesc();
    }

    public MaceReqInpatient(MaceInpatientJson mij) {
        this.setDoctorCode(mij.getDoctorCodes().get(0));
        this.setHospitalCode(mij.getHospitalCode());
        this.setStatus("ADMITTED");
        this.setRoomratePerday(mij.getRoomRate());
        this.setRoomNo(mij.getRoomNo());
        this.setRoomboard(mij.getRoomType());
        this.setApprovedOn(new Date());
        this.setApprovedBy(mij.getAppUsername());
        this.setApprovalRemarks("AUTOMATICALLY APPROVED BY SYSTEM.");
        this.setRecordSubmittedBy(mij.getAppUsername());
        this.setAdmittedOn(mij.getDateTimeAdmitted());
    }

    public MaceReqInpatient(MaceInpatientPortalJson mij) {
        this.setDoctorCode(mij.getDoctorCode());
        this.setHospitalCode(mij.getHospitalCode());
        this.setStatus("ADMITTED");
        this.setApprovedOn(new Date());
        this.setApprovedBy(mij.getAppUsername());
        this.setApprovalRemarks("AUTOMATICALLY APPROVED BY SYSTEM.");
        this.setRecordSubmittedBy(mij.getAppUsername());
        this.setAdmittedOn(mij.getAdmissionDate());
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public MaceReqInpatient transactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public MaceReqInpatient requestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
        return this;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getStatus() {
        return status;
    }

    public MaceReqInpatient status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusRemarks() {
        return statusRemarks;
    }

    public MaceReqInpatient statusRemarks(String statusRemarks) {
        this.statusRemarks = statusRemarks;
        return this;
    }

    public void setStatusRemarks(String statusRemarks) {
        this.statusRemarks = statusRemarks;
    }

    public Date getApprovedOn() {
        return approvedOn;
    }

    public MaceReqInpatient approvedOn(Date approvedOn) {
        this.approvedOn = approvedOn;
        return this;
    }

    public void setApprovedOn(Date approvedOn) {
        this.approvedOn = approvedOn;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public MaceReqInpatient approvedBy(String approvedBy) {
        this.approvedBy = approvedBy;
        return this;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovalRemarks() {
        return approvalRemarks;
    }

    public MaceReqInpatient approvalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks;
        return this;
    }

    public void setApprovalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks;
    }

    public Date getDisapprovedOn() {
        return disapprovedOn;
    }

    public MaceReqInpatient disapprovedOn(Date disapprovedOn) {
        this.disapprovedOn = disapprovedOn;
        return this;
    }

    public void setDisapprovedOn(Date disapprovedOn) {
        this.disapprovedOn = disapprovedOn;
    }

    public String getDisapprovedBy() {
        return disapprovedBy;
    }

    public MaceReqInpatient disapprovedBy(String disapprovedBy) {
        this.disapprovedBy = disapprovedBy;
        return this;
    }

    public void setDisapprovedBy(String disapprovedBy) {
        this.disapprovedBy = disapprovedBy;
    }

    public String getDisapprovalRemarks() {
        return disapprovalRemarks;
    }

    public MaceReqInpatient disapprovalRemarks(String disapprovalRemarks) {
        this.disapprovalRemarks = disapprovalRemarks;
        return this;
    }

    public void setDisapprovalRemarks(String disapprovalRemarks) {
        this.disapprovalRemarks = disapprovalRemarks;
    }

    public String getDisapprovalReason() {
        return disapprovalReason;
    }

    public MaceReqInpatient disapprovalReason(String disapprovalReason) {
        this.disapprovalReason = disapprovalReason;
        return this;
    }

    public void setDisapprovalReason(String disapprovalReason) {
        this.disapprovalReason = disapprovalReason;
    }

    public String getLoaNo() {
        return loaNo;
    }

    public MaceReqInpatient loaNo(String loaNo) {
        this.loaNo = loaNo;
        return this;
    }

    public void setLoaNo(String loaNo) {
        this.loaNo = loaNo;
    }

    public Date getValidfrom() {
        return validfrom;
    }

    public MaceReqInpatient validfrom(Date validfrom) {
        this.validfrom = validfrom;
        return this;
    }

    public void setValidfrom(Date validfrom) {
        this.validfrom = validfrom;
    }

    public Date getValidto() {
        return validto;
    }

    public MaceReqInpatient validto(Date validto) {
        this.validto = validto;
        return this;
    }

    public void setValidto(Date validto) {
        this.validto = validto;
    }

    public Date getAdmittedOn() {
        return admittedOn;
    }

    public MaceReqInpatient admittedOn(Date admittedOn) {
        this.admittedOn = admittedOn;
        return this;
    }

    public void setAdmittedOn(Date admittedOn) {
        this.admittedOn = admittedOn;
    }

    public String getRoomboard() {
        return roomboard;
    }

    public MaceReqInpatient roomboard(String roomboard) {
        this.roomboard = roomboard;
        return this;
    }

    public void setRoomboard(String roomboard) {
        this.roomboard = roomboard;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public MaceReqInpatient roomNo(String roomNo) {
        this.roomNo = roomNo;
        return this;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public BigDecimal getRoomratePerday() {
        return roomratePerday;
    }

    public MaceReqInpatient roomratePerday(BigDecimal roomratePerday) {
        this.roomratePerday = roomratePerday;
        return this;
    }

    public void setRoomratePerday(BigDecimal roomratePerday) {
        this.roomratePerday = roomratePerday;
    }

    public Date getRecordSubmittedOn() {
        return recordSubmittedOn;
    }

    public MaceReqInpatient recordSubmittedOn(Date recordSubmittedOn) {
        this.recordSubmittedOn = recordSubmittedOn;
        return this;
    }

    public void setRecordSubmittedOn(Date recordSubmittedOn) {
        this.recordSubmittedOn = recordSubmittedOn;
    }

    public String getRecordSubmittedBy() {
        return recordSubmittedBy;
    }

    public MaceReqInpatient recordSubmittedBy(String recordSubmittedBy) {
        this.recordSubmittedBy = recordSubmittedBy;
        return this;
    }

    public void setRecordSubmittedBy(String recordSubmittedBy) {
        this.recordSubmittedBy = recordSubmittedBy;
    }

    public String getIpReasonRemarks() {
        return ipReasonRemarks;
    }

    public MaceReqInpatient ipReasonRemarks(String ipReasonRemarks) {
        this.ipReasonRemarks = ipReasonRemarks;
        return this;
    }

    public void setIpReasonRemarks(String ipReasonRemarks) {
        this.ipReasonRemarks = ipReasonRemarks;
    }

    public Long getDocHospId() {
        return docHospId;
    }

    public MaceReqInpatient docHospId(Long docHospId) {
        this.docHospId = docHospId;
        return this;
    }

    public void setDocHospId(Long docHospId) {
        this.docHospId = docHospId;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public MaceReqInpatient doctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
        return this;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public MaceReqInpatient hospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
        return this;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public MaceReqInpatient estimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
        return this;
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public MaceReqInpatient primaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
        return this;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public String getPrimaryDiagnosisIcd10() {
        return primaryDiagnosisIcd10;
    }

    public MaceReqInpatient primaryDiagnosisIcd10(String primaryDiagnosisIcd10) {
        this.primaryDiagnosisIcd10 = primaryDiagnosisIcd10;
        return this;
    }

    public void setPrimaryDiagnosisIcd10(String primaryDiagnosisIcd10) {
        this.primaryDiagnosisIcd10 = primaryDiagnosisIcd10;
    }

    public String getPrimaryDiagnosisDesc() {
        return primaryDiagnosisDesc;
    }

    public MaceReqInpatient primaryDiagnosisDesc(String primaryDiagnosisDesc) {
        this.primaryDiagnosisDesc = primaryDiagnosisDesc;
        return this;
    }

    public void setPrimaryDiagnosisDesc(String primaryDiagnosisDesc) {
        this.primaryDiagnosisDesc = primaryDiagnosisDesc;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public MaceReqInpatient diseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
        return this;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getDxRemarks() {
        return dxRemarks;
    }

    public MaceReqInpatient dxRemarks(String dxRemarks) {
        this.dxRemarks = dxRemarks;
        return this;
    }

    public void setDxRemarks(String dxRemarks) {
        this.dxRemarks = dxRemarks;
    }

    public Date getDischargedOn() {
        return dischargedOn;
    }

    public MaceReqInpatient dischargedOn(Date dischargedOn) {
        this.dischargedOn = dischargedOn;
        return this;
    }

    public void setDischargedOn(Date dischargedOn) {
        this.dischargedOn = dischargedOn;
    }

    public String getDischargeupdateBy() {
        return dischargeupdateBy;
    }

    public MaceReqInpatient dischargeupdateBy(String dischargeupdateBy) {
        this.dischargeupdateBy = dischargeupdateBy;
        return this;
    }

    public void setDischargeupdateBy(String dischargeupdateBy) {
        this.dischargeupdateBy = dischargeupdateBy;
    }

    public Date getLastupdateOn() {
        return lastupdateOn;
    }

    public MaceReqInpatient lastupdateOn(Date lastupdateOn) {
        this.lastupdateOn = lastupdateOn;
        return this;
    }

    public void setLastupdateOn(Date lastupdateOn) {
        this.lastupdateOn = lastupdateOn;
    }

    public String getLastupdateBy() {
        return lastupdateBy;
    }

    public MaceReqInpatient lastupdateBy(String lastupdateBy) {
        this.lastupdateBy = lastupdateBy;
        return this;
    }

    public void setLastupdateBy(String lastupdateBy) {
        this.lastupdateBy = lastupdateBy;
    }

    public String getPecLimit() {
        return pecLimit;
    }

    public MaceReqInpatient pecLimit(String pecLimit) {
        this.pecLimit = pecLimit;
        return this;
    }

    public void setPecLimit(String pecLimit) {
        this.pecLimit = pecLimit;
    }

    public String getPecRemlimit() {
        return pecRemlimit;
    }

    public MaceReqInpatient pecRemlimit(String pecRemlimit) {
        this.pecRemlimit = pecRemlimit;
        return this;
    }

    public void setPecRemlimit(String pecRemlimit) {
        this.pecRemlimit = pecRemlimit;
    }

    public String getDdLimit() {
        return ddLimit;
    }

    public MaceReqInpatient ddLimit(String ddLimit) {
        this.ddLimit = ddLimit;
        return this;
    }

    public void setDdLimit(String ddLimit) {
        this.ddLimit = ddLimit;
    }

    public String getDdRemlimit() {
        return ddRemlimit;
    }

    public MaceReqInpatient ddRemlimit(String ddRemlimit) {
        this.ddRemlimit = ddRemlimit;
        return this;
    }

    public void setDdRemlimit(String ddRemlimit) {
        this.ddRemlimit = ddRemlimit;
    }

    public String getDisposition() {
        return disposition;
    }

    public MaceReqInpatient disposition(String disposition) {
        this.disposition = disposition;
        return this;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getDispRemarks() {
        return dispRemarks;
    }

    public MaceReqInpatient dispRemarks(String dispRemarks) {
        this.dispRemarks = dispRemarks;
        return this;
    }

    public void setDispRemarks(String dispRemarks) {
        this.dispRemarks = dispRemarks;
    }

    public MaceRequest getMaceRequest() {
        return maceRequest;
    }

    public MaceReqInpatient maceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
        return this;
    }

    public void setMaceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public void setUpdateDetails(UpdateInpatientJson updateDetails, MemberDetails member, MaceReqIpDiag diag, String hospitalCode) {
        this.lastupdateBy = updateDetails.getUsername();
        this.lastupdateOn = new Date();
        this.primaryDiagnosisCode = diag.getDiagCode();
        this.primaryDiagnosisDesc = diag.getDiagDesc();
        this.dxRemarks = diag.getDiagRemarks();
        this.diseaseType = "";
        this.doctorCode = updateDetails.getPrimaryDoctor();
        this.hospitalCode = hospitalCode;
        this.admittedOn = updateDetails.getDateAdmitted();
        this.dischargedOn = updateDetails.getDischargeDate();
        this.dischargeupdateBy = updateDetails.getUsername();
        if(this.dischargedOn != null)
            {this.status = "DISCHARGED";}
        this.ddLimit = String.valueOf(member.getDD_Reg_Limits());
    }

    public void setDiagnosis(Diagnosis diag) {
        this.setPrimaryDiagnosisCode(diag.getDiagCode());
        this.setPrimaryDiagnosisIcd10(diag.getIcd10Code());
        this.setPrimaryDiagnosisDesc(diag.getDiagDesc());
        this.setDxRemarks(diag.getDiagRemarks());
        this.diseaseType(diag.getTypeDesc());
    }
}