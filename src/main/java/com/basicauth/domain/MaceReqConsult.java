package com.basicauth.domain;

import com.basicauth.data.CustomerCare;
import com.basicauth.data.DiagnosisEntity;
import com.basicauth.data.MaceRequest;
import com.basicauth.data.MaceRequestReturn;
import com.basicauth.mapper.mace.MaceMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by service.incuventure on 5/29/2017.
 */
public class MaceReqConsult implements Serializable {


    @Autowired
    private  MaceMapper mapper;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Long transactionId;

    private String transCode;

    private Integer maceRequestId;

    @Column(name = "consult_subtype")
    private Integer consultSubtype;

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

    @Column(name = "reference_no")
    private String referenceNo;

    @Column(name = "validfrom")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date validfrom;

    @Column(name = "validto")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date validto;

    @Column(name = "availed_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date availedOn;

    @Column(name = "avail_member_agreed")
    private Boolean availMemberAgreed;

    @Column(name = "avail_member_agreedon")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date availMemberAgreedon;

    @Column(name = "pin_entered")
    private Boolean pinEntered;

    @Column(name = "notes")
    private String notes;

    @Column(name = "record_submitted_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date recordSubmittedOn;

    @Column(name = "record_submitted_by")
    private String recordSubmittedBy;

    @Column(name = "expired_on")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date expiredOn;

    @Column(name = "consultation_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date consultationDate;

    @Column(name = "consult_reason")
    private String consultReason;

    @Column(name = "doc_hosp_id")
    private Integer docHospId;

    @Column(name = "doctor_code")
    private String doctorCode;

    @Column(name = "hospital_code")
    private String hospitalCode;

    @Column(name = "fee", precision=10, scale=2)
    private BigDecimal fee;

    @Column(name = "history_of_present_illness")
    private String historyOfPresentIllness;

    @Column(name = "past_or_family_history")
    private String pastOrFamilyHistory;

    @Column(name = "review_of_systems")
    private String reviewOfSystems;

    @Column(name = "vitals_bp")
    private String vitalsBp;

    @Column(name = "vitals_hr")
    private String vitalsHr;

    @Column(name = "vitals_rr")
    private String vitalsRr;

    @Column(name = "vitals_temp")
    private String vitalsTemp;

    @Column(name = "physical_examination")
    private String physicalExamination;

    @Column(name = "primary_diagnosis_code")
    private String primaryDiagnosisCode;

    @Column(name = "primary_diagnosis_icd_10")
    private String primaryDiagnosisIcd10;

    @Column(name = "primary_diagnosis_desc")
    private String primaryDiagnosisDesc;

    @Column(name = "dx_remarks")
    private String dxRemarks;

    @Column(name = "primary_diagnosis_remarks")
    private String primaryDiagnosisRemarks;

    @Column(name = "is_congenital")
    private Boolean isCongenital;

    @Column(name = "is_maternity")
    private Boolean isMaternity;

    @Column(name = "is_medicolegal")
    private Boolean isMedicolegal;

    @Column(name = "planofmanagement")
    private String planOfManagement;

    @Column(name = "transamount", precision=10, scale=2)
    private BigDecimal transAmount;

    @Column(name = "doctorName")
    private String doctorName;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getServiceSubType() {
        return serviceSubType;
    }

    public void setServiceSubType(String serviceSubType) {
        this.serviceSubType = serviceSubType;
    }

    private String serviceSubType;

    public MaceRequest getMaceRequest() {
        return maceRequest;
    }

    public void setMaceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
    }

    public List<MaceReqOpDiag> getMaceConsultDiagnosis() {
        return maceConsultDiagnosis;
    }

    public void setMaceConsultDiagnosis(List<MaceReqOpDiag> maceConsultDiagnosis) {
        this.maceConsultDiagnosis = maceConsultDiagnosis;
    }

    public List<com.basicauth.domain.dups.MaceConsPrescribedtest> getMaceConsultPrescribed() {
        return maceConsultPrescribed;
    }

    public void setMaceConsultPrescribed(List<com.basicauth.domain.dups.MaceConsPrescribedtest> maceConsultPrescribed) {
        this.maceConsultPrescribed = maceConsultPrescribed;
    }

    private MaceRequest maceRequest;
    private List<MaceReqOpDiag> maceConsultDiagnosis;
    private List<com.basicauth.domain.dups.MaceConsPrescribedtest> maceConsultPrescribed;

    public MaceReqConsult(){}

    public MaceReqConsult(CustomerCare c) {
        this.hospitalCode = c.getHospitalCode();
        this.doctorCode = c.getDoctorCode();
        this.dxRemarks = c.getDiagnosis();
        this.consultSubtype = c.getRemarks().equals("CONSULTATION")? 1:2;
        this.requestFrom = c.getRequestOrigin();
        this.notes = c.getNotes();
        this.recordSubmittedOn = new Date();
        this.recordSubmittedBy = c.getUpdatedBy();
        this.consultReason = c.getPrimaryComplaint();
        this.isMaternity = this.consultSubtype == 1? false: true;
        this.referenceNo = c.getApprovalNo();
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public Integer getConsultSubtype() {
        return consultSubtype;
    }

    public void setConsultSubtype(Integer consultSubtype) {
        this.consultSubtype = consultSubtype;
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

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
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

    public Date getAvailedOn() {
        return availedOn;
    }

    public void setAvailedOn(Date availedOn) {
        this.availedOn = availedOn;
    }

    public Boolean getAvailMemberAgreed() {
        return availMemberAgreed;
    }

    public void setAvailMemberAgreed(Boolean availMemberAgreed) {
        this.availMemberAgreed = availMemberAgreed;
    }

    public Date getAvailMemberAgreedon() {
        return availMemberAgreedon;
    }

    public void setAvailMemberAgreedon(Date availMemberAgreedon) {
        this.availMemberAgreedon = availMemberAgreedon;
    }

    public Boolean getPinEntered() {
        return pinEntered;
    }

    public void setPinEntered(Boolean pinEntered) {
        this.pinEntered = pinEntered;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Date getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(Date expiredOn) {
        this.expiredOn = expiredOn;
    }

    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getConsultReason() {
        return consultReason;
    }

    public void setConsultReason(String consultReason) {
        this.consultReason = consultReason;
    }

    public Integer getDocHospId() {
        return docHospId;
    }

    public void setDocHospId(Integer docHospId) {
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

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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

    public String getDxRemarks() {
        return dxRemarks;
    }

    public void setDxRemarks(String dxRemarks) {
        this.dxRemarks = dxRemarks;
    }

    public String getPrimaryDiagnosisRemarks() {
        return primaryDiagnosisRemarks;
    }

    public void setPrimaryDiagnosisRemarks(String primaryDiagnosisRemarks) {
        this.primaryDiagnosisRemarks = primaryDiagnosisRemarks;
    }

    public Boolean getCongenital() {
        return isCongenital;
    }

    public void setCongenital(Boolean congenital) {
        isCongenital = congenital;
    }

    public Boolean getMaternity() {
        return isMaternity;
    }

    public void setMaternity(Boolean maternity) {
        isMaternity = maternity;
    }

    public Boolean getMedicolegal() {
        return isMedicolegal;
    }

    public void setMedicolegal(Boolean medicolegal) {
        isMedicolegal = medicolegal;
    }

    public String getPlanOfManagement() {
        return planOfManagement;
    }

    public void setPlanOfManagement(String planOfManagement) {
        this.planOfManagement = planOfManagement;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public void setPrimaryDiagnosisEntity(DiagnosisEntity diagnosisEntity) {
        setPrimaryDiagnosisCode(diagnosisEntity.getDiagCode());
        setPrimaryDiagnosisIcd10(diagnosisEntity.getIcd10Code());
        setPrimaryDiagnosisDesc(diagnosisEntity.getDiagDesc());
        setPrimaryDiagnosisRemarks(diagnosisEntity.getDiagRemarks());
    }
}
