package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bryan on 5/6/2017.
 */
//@Entity
//@Table(name = "MACEREQ_PROCEDURE")

public class MaceRequestProcedure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSACTION_ID")
    @JsonProperty("transactionId")
    private long transactionId;

    @Column(name = "MACEREQUEST_ID")
    @JsonProperty("maceRequestId")
    private long maceRequestId;

    @JsonProperty ("userId")
    @Column(name = "REQUEST_FROM")
    private String requestFrom;

    @Column(name = "STATUS")
    @JsonProperty("status")
    private String status;

    @Column(name = "STATUS_REMARKS")
    @JsonProperty("statusRemarks")
    private String statusRemarks;

    @Column(name = "APPROVED_ON")
    @JsonProperty("approvedOn")
    private Date approvedOn;

    @Column(name = "APPROVED_BY")
    @JsonProperty("approvedBy")
    private String approvedBy;

    @Column(name = "APPROVAL_REMARKS")
    @JsonProperty("approvalRemarks")
    private String approvalRemarks;

    @Column(name = "DISAPPROVED_ON")
    @JsonProperty("disapprovedOn")
    private Date disapprovedOn;

    @Column(name = "DISAPPROVED_BY")
    @JsonProperty("disapprovedBy")
    private String disapprovedBy;

    @Column(name = "DISAPPROVAL_REMARKS")
    @JsonProperty("disapprovalRemarks")
    private String disapprovalRemarks;

    @Column(name = "DISAPPROVAL_REASON")
    @JsonProperty("disapprovalReason")
    private String disapprovalReason;

    @Column(name = "APPROVAL_NO")
    @JsonProperty("approvalNo")
    private String approvalNo;

    @Column(name = "VALIDFROM")
    @JsonProperty("validFrom")
    private Date validFrom;

    @Column(name = "VALIDTO")
    @JsonProperty("validTo")
    private Date validTo;

    @Column(name = "NOTES")
    @JsonProperty("notes")
    private String notes;

    @Column(name = "EXPIRED_ON")
    @JsonProperty("expiredOn")
    private Date expiredOn;

    @JsonProperty("reasonForConsult")
    @Column(name = "CONSULT_REASON")
    private String consultReason;

    @Column(name = "DOC_HOSP_ID")
    @JsonProperty ("docHospId")
    private long docHospId;

    @JsonProperty ("doctorCode")
    @Column(name = "DOCTOR_CODE")
    private String doctorCode;

    @JsonProperty("hospitalCode")
    @Column(name = "HOSPITAL_CODE")
    private String hospitalCode;

    @JsonProperty("primaryDiagnosis")
    @Column(name = "PRIMARY_DIAGNOSIS_CODE")
    private String primaryDiagnosisCode;

    @Column(name = "PRIMARY_DIAGNOSIS_ICD10")
    @JsonProperty("primaryDiagnosisICD10")
    private String primaryDiagnosisICD10;

    @Column(name = "PRIMARY_DIAGNOSIS_DESC")
    @JsonProperty("primaryDiagnosisDesc")
    private String primaryDiagnosisDesc;

    @Column(name = "DX_REMARKS")
    @JsonProperty("dxRemarks")
    private String dxRemarks;

    @JsonProperty("availHospId")
    @Column(name = "AVAIL_HOSP_ID")
    private String availHospId;

    @Column(name = "PLANOFMANAGEMENT")
    @JsonProperty("planOfManagement")
    private String planOfManagement;

    @Column(name = "TRANSAMOUNT")
    @JsonProperty("transamount")
    private BigDecimal transamount;

    @Column(name = "TEST_SUBTYPE")
    @JsonProperty("testSubtype")
    private int testSubtype;

    @Column(name = "MACE_TESTGROUP")
    @JsonProperty("maceTestGroup")
    private String maceTestGroup;

    @JsonProperty("loaMaceRequestId")
    @Column(name = "REF_REQUESTID")
    private int refRequestId;

    @JsonProperty("referenceCode")
    @Column(name = "REF_REFNO")
    private String refRefNo;

    @Column(name = "PROC_DESC")
    private String procDesc;

    private String costCenter;

    @Column(name="TRANS_CODE")
    private String transCode;

//    @OneToMany(mappedBy = "maceRequestOpProcedures")
    @JsonProperty ("procedures")
    private Set<MaceRequestOpProcedure> procedures;

    //    @OneToOne (mappedBy = "maceRequest")
    @JsonProperty ("mainRequest")
    private MaceRequest maceRequest;


    public Set<MaceRequestOpProcedure> getProcedures() {
        return procedures;
    }

    public void setProcedures (Set<MaceRequestOpProcedure> maceRequestOpProcedures) {
        this.procedures = maceRequestOpProcedures;
    }

    public MaceRequest getMaceRequest() {
        return maceRequest;
    }

    public void setMaceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
    }


    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(long maceRequestId) {
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

    public String getDisapprvalReason() {
        return disapprovalReason;
    }

    public void setDisapprvalReason(String disapprovalReason) {
        this.disapprovalReason = disapprovalReason;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
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

    public long getDocHospId() {
        return docHospId;
    }

    public void setDocHospId(long docHospId) {
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

    public String getPrimaryDiagnosisICD10() {
        return primaryDiagnosisICD10;
    }

    public void setPrimaryDiagnosisICD10(String primaryDiagnosisICD10) {
        this.primaryDiagnosisICD10 = primaryDiagnosisICD10;
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

    public String getPlanOfManagement() {
        return planOfManagement;
    }

    public void setPlanOfManagement(String planOfManagement) {
        this.planOfManagement = planOfManagement;
    }

    public BigDecimal getTransamount() {
        return transamount;
    }

    public void setTransamount(BigDecimal transamount) {
        this.transamount = transamount;
    }

    public int getTestSubtype() {
        return testSubtype;
    }

    public void setTestSubtype(int testSubtype) {
        this.testSubtype = testSubtype;
    }

    public String getMaceTestGroup() {
        return maceTestGroup;
    }

    public void setMaceTestGroup(String maceTestGroup) {
        this.maceTestGroup = maceTestGroup;
    }

    public int getRefRequestId() {
        return refRequestId;
    }

    public void setRefRequestId(int refRequestId) {
        this.refRequestId = refRequestId;
    }

    public String getRefRefNo() {
        return refRefNo;
    }

    public void setRefRefNo(String refRefNo) {
        this.refRefNo = refRefNo;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public MaceRequestProcedure procedures (Set<MaceRequestOpProcedure> maceRequestOpProcedures) {
        this.procedures = maceRequestOpProcedures;
        return this;
    }

    public MaceRequestProcedure addProcedure(MaceRequestOpProcedure maceRequestOpProcedures) {
        this.procedures.add(maceRequestOpProcedures);
        //maceRequestOpProcedures.set(this); -- Include in object MaceRequestOpProcedure propery MaceRequestProcedure
        return this;
    }

    public MaceRequestProcedure removeProcedure (MaceRequestOpProcedure maceRequestOpProcedures) {
        this.procedures.remove(maceRequestOpProcedures);
        //maceRequestOpProcedures.set(null);
        return this;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getDisapprovalReason() {
        return disapprovalReason;
    }

    public void setDisapprovalReason(String disapprovalReason) {
        this.disapprovalReason = disapprovalReason;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }
}
