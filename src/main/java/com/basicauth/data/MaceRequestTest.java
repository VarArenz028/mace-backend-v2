package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IPC_Server on 5/18/2017.
 */
public class MaceRequestTest implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSACTION_ID")
    private long transactionId;

    @Column(name = "MACEREQUEST_ID")
    private long maceRequestId;

    @Column(name = "REQUEST_FROM")
    private String requestFrom;

    @Column(name = "STATUS")
    @JsonProperty("status")
    private String status;

    // reason why the request is pending
    // sample: "Pending due to payment verification"
    @Column(name = "STATUS_REMARKS")
    private String statusRemarks;

    @Column(name = "APPROVED_ON")
    private Date approvedOn;

    @Column(name = "APPROVED_BY")
    private String approvedBy;

    @Column(name = "APPROVAL_REMARKS")
    private String approvalRemarks;

    @Column(name = "DISAPPROVED_ON")
    private Date disapprovedOn;

    @Column(name = "DISAPPROVED_BY")
    private String disapprovedBy;

    @Column(name = "DISAPPROVAL_REMARKS")
    private String disapprovalRemarks;

    @Column(name = "DISAPPROVAL_REASON")
    private String disapprovalReason;

    @Column(name = "APPROVAL_NO")
    private String approvalNo;

    @Column(name = "VALIDFROM")
    private Date validFrom;

    @Column(name = "VALIDTO")
    private Date validTo;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "EXPIRED_ON")
    private Date expiredOn;

    @Column(name = "CONSULT_REASON")
    private String consultReason;

    // combination ng doctor code and hospital code
    @Column(name = "DOC_HOSP_ID")
    private long docHospId;

    @Column(name = "DOCTOR_CODE")
    private String doctorCode;

    // from consult
    @Column(name = "HOSPITAL_CODE")
    private String hospitalCode;

    @Column(name = "PRIMARY_DIAGNOSIS_CODE")
    private String primaryDiagnosisCode;

    @Column(name = "PRIMARY_DIAGNOSIS_ICD10")
    private String primaryDiagnosisICD10;

    @Column(name = "PRIMARY_DIAGNOSIS_DESC")
    private String primaryDiagnosisDesc;

    // kapag may consult
    @Column(name = "DX_REMARKS")
    private String dxRemarks;

    // kung saan iaavail yung test
    @Column(name = "AVAIL_HOSP_ID")
    private String availHospId;

    // kapag may consult
    @Column(name = "PLANOFMANAGEMENT")
    private String planOfManagement;

    // total ng amount ng group
    @Column(name = "TRANSAMOUNT")
    private BigDecimal transamount;

    // basic or other
    @Column(name = "TEST_SUBTYPE")
    private int testSubtype;

    public MaceRequest getMaceRequest() {
        return maceRequest;
    }

    public void setMaceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
    }

    private MaceRequest maceRequest;


    public String getServiceSubType() {
        return serviceSubType;
    }

    public void setServiceSubType(String serviceSubType) {
        this.serviceSubType = serviceSubType;
    }

    private String serviceSubType;

    // equal to cost center
    @Column(name = "MACE_TESTGROUP")
    private String maceTestGroup;

    // if galing sa availed consult, equal to sa reference ID ng consult
    @Column(name = "REF_REQUESTID")
    private int refRequestId;

    // if galing sa availed consult, equal to sa reference no ng consult
    @Column(name = "REF_REFNO")
    private String refRefNo;

    @Column(name = "TRANS_CODE")
    private String transCode;

    private String procDesc;

    private String costCenter;


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

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

}
