package com.basicauth.domain;

import com.basicauth.data.MaceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by service.incuventure on 5/29/2017.
 */
@Entity
@Table(name = "mace_req_procedure")
public class MaceReqProcedure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "request_from")
    private String requestFrom;

    @Column(name = "status")
    private String status;

    @Column(name = "status_remarks")
    private String statusRemarks;

    @Column(name = "approved_on")
    private Date approvedOn;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approval_remarks")
    private String approvalRemarks;

    @Column(name = "disapproved_on")
    private Date disapprovedOn;

    @Column(name = "disapproved_by")
    private String disapprovedBy;

    @Column(name = "disapproval_remarks")
    private String disapprovalRemarks;

    @Column(name = "disapproval_reason")
    private String disapprovalReason;

    @Column(name = "approval_no")
    private String approvalNo;

    @Column(name = "validfrom")
    private Date validfrom;

    @Column(name = "validto")
    private Date validto;

    @Column(name = "notes")
    private String notes;

    @Column(name = "expired_on")
    private Date expiredOn;

    @Column(name = "consult_reason")
    private String consultReason;

    @Column(name = "doc_hosp_id")
    private Integer docHospId;

    @Column(name = "doctor_code")
    private String doctorCode;

    @Column(name = "hospital_code")
    private String hospitalCode;

    @Column(name = "primary_diagnosis_code")
    private String primaryDiagnosisCode;

    @Column(name = "primary_diagnosis_icd_10")
    private String primaryDiagnosisIcd10;

    @Column(name = "primary_diagnosis_desc")
    private String primaryDiagnosisDesc;

    @Column(name = "dx_remarks")
    private String dxRemarks;

    @Column(name = "avail_hosp_id")
    private String availHospId;

    @Column(name = "planofmanagement")
    private String planofmanagement;

    @Column(name = "transamount", precision=10, scale=2)
    private BigDecimal transamount;

    @Column(name = "test_subtype")
    private Integer testSubtype;

    @Column(name = "mace_testproc_group")
    private String maceTestprocGroup;

    @Column(name = "ref_requestid")
    private Integer refRequestid;

    @Column(name = "ref_refno")
    private String refRefno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
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

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(Date expiredOn) {
        this.expiredOn = expiredOn;
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

    public String getAvailHospId() {
        return availHospId;
    }

    public void setAvailHospId(String availHospId) {
        this.availHospId = availHospId;
    }

    public String getPlanofmanagement() {
        return planofmanagement;
    }

    public void setPlanofmanagement(String planofmanagement) {
        this.planofmanagement = planofmanagement;
    }

    public BigDecimal getTransamount() {
        return transamount;
    }

    public void setTransamount(BigDecimal transamount) {
        this.transamount = transamount;
    }

    public Integer getTestSubtype() {
        return testSubtype;
    }

    public void setTestSubtype(Integer testSubtype) {
        this.testSubtype = testSubtype;
    }

    public String getMaceTestprocGroup() {
        return maceTestprocGroup;
    }

    public void setMaceTestprocGroup(String maceTestprocGroup) {
        this.maceTestprocGroup = maceTestprocGroup;
    }

    public Integer getRefRequestid() {
        return refRequestid;
    }

    public void setRefRequestid(Integer refRequestid) {
        this.refRequestid = refRequestid;
    }

    public String getRefRefno() {
        return refRefno;
    }

    public void setRefRefno(String refRefno) {
        this.refRefno = refRefno;
    }
}
