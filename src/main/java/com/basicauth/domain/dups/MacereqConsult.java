package com.basicauth.domain.dups;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MACEREQ_CONSULT", schema = "dbo", catalog = "Mace")
public class MacereqConsult implements Serializable{
    private int transactionId;
    private String transCode;
    private Integer macerequestId;
    private Short consultSubtype;
    private String requestFrom;
    private String status;
    private String statusRemarks;
    private Date approvedOn;
    private String approvedBy;
    private String approvalRemarks;
    private Date disapprovedOn;
    private String disapprovedBy;
    private String disapprovalRemarks;
    private String disapprovalReason;
    private String referenceNo;
    private Date validfrom;
    private Date validto;
    private Date availedOn;
    private Boolean availMemberAgreed;
    private Date availMemberAgreedon;
    private Boolean pinEntered;
    private String notes;
    private Date recordSubmittedOn;
    private String recordSubmittedBy;
    private Date expiredOn;
    private Date consultationDate;
    private String consultReason;
    private Long docHospId;
    private String doctorCode;
    private String hospitalCode;
    private BigDecimal fee;
    private String historyOfPresentIllness;
    private String pastOrFamilyHistory;
    private String reviewOfSystems;
    private String vitalsBp;
    private String vitalsHr;
    private String vitalsRr;
    private String vitalsTemp;
    private String physicalExamination;
    private String primaryDiagnosisCode;
    private String primaryDiagnosisIcd10;
    private String primaryDiagnosisDesc;
    private String dxRemarks;
    private String primaryDiagnosisRemarks;
    private Boolean isCongenital;
    private Boolean isMaternity;
    private Boolean isMedicolegal;
    private String planofmanagement;
    private Integer transamount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Basic
    @Column(name = "TRANS_CODE")
    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    @Basic
    @Column(name = "MACEREQUEST_ID")
    public Integer getMacerequestId() {
        return macerequestId;
    }

    public void setMacerequestId(Integer macerequestId) {
        this.macerequestId = macerequestId;
    }

    @Basic
    @Column(name = "CONSULT_SUBTYPE")
    public Short getConsultSubtype() {
        return consultSubtype;
    }

    public void setConsultSubtype(Short consultSubtype) {
        this.consultSubtype = consultSubtype;
    }

    @Basic
    @Column(name = "REQUEST_FROM")
    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "STATUS_REMARKS")
    public String getStatusRemarks() {
        return statusRemarks;
    }

    public void setStatusRemarks(String statusRemarks) {
        this.statusRemarks = statusRemarks;
    }

    @Basic
    @Column(name = "APPROVED_ON")
    public Date getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(Date approvedOn) {
        this.approvedOn = approvedOn;
    }

    @Basic
    @Column(name = "APPROVED_BY")
    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Basic
    @Column(name = "APPROVAL_REMARKS")
    public String getApprovalRemarks() {
        return approvalRemarks;
    }

    public void setApprovalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks;
    }

    @Basic
    @Column(name = "DISAPPROVED_ON")
    public Date getDisapprovedOn() {
        return disapprovedOn;
    }

    public void setDisapprovedOn(Date disapprovedOn) {
        this.disapprovedOn = disapprovedOn;
    }

    @Basic
    @Column(name = "DISAPPROVED_BY")
    public String getDisapprovedBy() {
        return disapprovedBy;
    }

    public void setDisapprovedBy(String disapprovedBy) {
        this.disapprovedBy = disapprovedBy;
    }

    @Basic
    @Column(name = "DISAPPROVAL_REMARKS")
    public String getDisapprovalRemarks() {
        return disapprovalRemarks;
    }

    public void setDisapprovalRemarks(String disapprovalRemarks) {
        this.disapprovalRemarks = disapprovalRemarks;
    }

    @Basic
    @Column(name = "DISAPPROVAL_REASON")
    public String getDisapprovalReason() {
        return disapprovalReason;
    }

    public void setDisapprovalReason(String disapprovalReason) {
        this.disapprovalReason = disapprovalReason;
    }

    @Basic
    @Column(name = "REFERENCE_NO")
    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    @Basic
    @Column(name = "VALIDFROM")
    public Date getValidfrom() {
        return validfrom;
    }

    public void setValidfrom(Date validfrom) {
        this.validfrom = validfrom;
    }

    @Basic
    @Column(name = "VALIDTO")
    public Date getValidto() {
        return validto;
    }

    public void setValidto(Date validto) {
        this.validto = validto;
    }

    @Basic
    @Column(name = "AVAILED_ON")
    public Date getAvailedOn() {
        return availedOn;
    }

    public void setAvailedOn(Date availedOn) {
        this.availedOn = availedOn;
    }

    @Basic
    @Column(name = "AVAIL_MEMBER_AGREED")
    public Boolean getAvailMemberAgreed() {
        return availMemberAgreed;
    }

    public void setAvailMemberAgreed(Boolean availMemberAgreed) {
        this.availMemberAgreed = availMemberAgreed;
    }

    @Basic
    @Column(name = "AVAIL_MEMBER_AGREEDON")
    public Date getAvailMemberAgreedon() {
        return availMemberAgreedon;
    }

    public void setAvailMemberAgreedon(Date availMemberAgreedon) {
        this.availMemberAgreedon = availMemberAgreedon;
    }

    @Basic
    @Column(name = "PIN_ENTERED")
    public Boolean getPinEntered() {
        return pinEntered;
    }

    public void setPinEntered(Boolean pinEntered) {
        this.pinEntered = pinEntered;
    }

    @Basic
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "RECORD_SUBMITTED_ON")
    public Date getRecordSubmittedOn() {
        return recordSubmittedOn;
    }

    public void setRecordSubmittedOn(Date recordSubmittedOn) {
        this.recordSubmittedOn = recordSubmittedOn;
    }

    @Basic
    @Column(name = "RECORD_SUBMITTED_BY")
    public String getRecordSubmittedBy() {
        return recordSubmittedBy;
    }

    public void setRecordSubmittedBy(String recordSubmittedBy) {
        this.recordSubmittedBy = recordSubmittedBy;
    }

    @Basic
    @Column(name = "EXPIRED_ON")
    public Date getExpiredOn() {
        return expiredOn;
    }

    public void setExpiredOn(Date expiredOn) {
        this.expiredOn = expiredOn;
    }

    @Basic
    @Column(name = "CONSULTATION_DATE")
    public Date getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(Date consultationDate) {
        this.consultationDate = consultationDate;
    }

    @Basic
    @Column(name = "CONSULT_REASON")
    public String getConsultReason() {
        return consultReason;
    }

    public void setConsultReason(String consultReason) {
        this.consultReason = consultReason;
    }

    @Basic
    @Column(name = "DOC_HOSP_ID")
    public Long getDocHospId() {
        return docHospId;
    }

    public void setDocHospId(Long docHospId) {
        this.docHospId = docHospId;
    }

    @Basic
    @Column(name = "DOCTOR_CODE")
    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    @Basic
    @Column(name = "HOSPITAL_CODE")
    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    @Basic
    @Column(name = "FEE")
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "HISTORY_OF_PRESENT_ILLNESS")
    public String getHistoryOfPresentIllness() {
        return historyOfPresentIllness;
    }

    public void setHistoryOfPresentIllness(String historyOfPresentIllness) {
        this.historyOfPresentIllness = historyOfPresentIllness;
    }

    @Basic
    @Column(name = "PAST_OR_FAMILY_HISTORY")
    public String getPastOrFamilyHistory() {
        return pastOrFamilyHistory;
    }

    public void setPastOrFamilyHistory(String pastOrFamilyHistory) {
        this.pastOrFamilyHistory = pastOrFamilyHistory;
    }

    @Basic
    @Column(name = "REVIEW_OF_SYSTEMS")
    public String getReviewOfSystems() {
        return reviewOfSystems;
    }

    public void setReviewOfSystems(String reviewOfSystems) {
        this.reviewOfSystems = reviewOfSystems;
    }

    @Basic
    @Column(name = "VITALS_BP")
    public String getVitalsBp() {
        return vitalsBp;
    }

    public void setVitalsBp(String vitalsBp) {
        this.vitalsBp = vitalsBp;
    }

    @Basic
    @Column(name = "VITALS_HR")
    public String getVitalsHr() {
        return vitalsHr;
    }

    public void setVitalsHr(String vitalsHr) {
        this.vitalsHr = vitalsHr;
    }

    @Basic
    @Column(name = "VITALS_RR")
    public String getVitalsRr() {
        return vitalsRr;
    }

    public void setVitalsRr(String vitalsRr) {
        this.vitalsRr = vitalsRr;
    }

    @Basic
    @Column(name = "VITALS_TEMP")
    public String getVitalsTemp() {
        return vitalsTemp;
    }

    public void setVitalsTemp(String vitalsTemp) {
        this.vitalsTemp = vitalsTemp;
    }

    @Basic
    @Column(name = "PHYSICAL_EXAMINATION")
    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    @Basic
    @Column(name = "PRIMARY_DIAGNOSIS_CODE")
    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    @Basic
    @Column(name = "PRIMARY_DIAGNOSIS_ICD10")
    public String getPrimaryDiagnosisIcd10() {
        return primaryDiagnosisIcd10;
    }

    public void setPrimaryDiagnosisIcd10(String primaryDiagnosisIcd10) {
        this.primaryDiagnosisIcd10 = primaryDiagnosisIcd10;
    }

    @Basic
    @Column(name = "PRIMARY_DIAGNOSIS_DESC")
    public String getPrimaryDiagnosisDesc() {
        return primaryDiagnosisDesc;
    }

    public void setPrimaryDiagnosisDesc(String primaryDiagnosisDesc) {
        this.primaryDiagnosisDesc = primaryDiagnosisDesc;
    }

    @Basic
    @Column(name = "DX_REMARKS")
    public String getDxRemarks() {
        return dxRemarks;
    }

    public void setDxRemarks(String dxRemarks) {
        this.dxRemarks = dxRemarks;
    }

    @Basic
    @Column(name = "PRIMARY_DIAGNOSIS_REMARKS")
    public String getPrimaryDiagnosisRemarks() {
        return primaryDiagnosisRemarks;
    }

    public void setPrimaryDiagnosisRemarks(String primaryDiagnosisRemarks) {
        this.primaryDiagnosisRemarks = primaryDiagnosisRemarks;
    }

    @Basic
    @Column(name = "IS_CONGENITAL")
    public Boolean getCongenital() {
        return isCongenital;
    }

    public void setCongenital(Boolean congenital) {
        isCongenital = congenital;
    }

    @Basic
    @Column(name = "IS_MATERNITY")
    public Boolean getMaternity() {
        return isMaternity;
    }

    public void setMaternity(Boolean maternity) {
        isMaternity = maternity;
    }

    @Basic
    @Column(name = "IS_MEDICOLEGAL")
    public Boolean getMedicolegal() {
        return isMedicolegal;
    }

    public void setMedicolegal(Boolean medicolegal) {
        isMedicolegal = medicolegal;
    }

    @Basic
    @Column(name = "PLANOFMANAGEMENT")
    public String getPlanofmanagement() {
        return planofmanagement;
    }

    public void setPlanofmanagement(String planofmanagement) {
        this.planofmanagement = planofmanagement;
    }

    @Basic
    @Column(name = "TRANSAMOUNT")
    public Integer getTransamount() {
        return transamount;
    }

    public void setTransamount(Integer transamount) {
        this.transamount = transamount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MacereqConsult that = (MacereqConsult) o;

        if (transactionId != that.transactionId) return false;
        if (transCode != null ? !transCode.equals(that.transCode) : that.transCode != null) return false;
        if (macerequestId != null ? !macerequestId.equals(that.macerequestId) : that.macerequestId != null)
            return false;
        if (consultSubtype != null ? !consultSubtype.equals(that.consultSubtype) : that.consultSubtype != null)
            return false;
        if (requestFrom != null ? !requestFrom.equals(that.requestFrom) : that.requestFrom != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (statusRemarks != null ? !statusRemarks.equals(that.statusRemarks) : that.statusRemarks != null)
            return false;
        if (approvedOn != null ? !approvedOn.equals(that.approvedOn) : that.approvedOn != null) return false;
        if (approvedBy != null ? !approvedBy.equals(that.approvedBy) : that.approvedBy != null) return false;
        if (approvalRemarks != null ? !approvalRemarks.equals(that.approvalRemarks) : that.approvalRemarks != null)
            return false;
        if (disapprovedOn != null ? !disapprovedOn.equals(that.disapprovedOn) : that.disapprovedOn != null)
            return false;
        if (disapprovedBy != null ? !disapprovedBy.equals(that.disapprovedBy) : that.disapprovedBy != null)
            return false;
        if (disapprovalRemarks != null ? !disapprovalRemarks.equals(that.disapprovalRemarks) : that.disapprovalRemarks != null)
            return false;
        if (disapprovalReason != null ? !disapprovalReason.equals(that.disapprovalReason) : that.disapprovalReason != null)
            return false;
        if (referenceNo != null ? !referenceNo.equals(that.referenceNo) : that.referenceNo != null) return false;
        if (validfrom != null ? !validfrom.equals(that.validfrom) : that.validfrom != null) return false;
        if (validto != null ? !validto.equals(that.validto) : that.validto != null) return false;
        if (availedOn != null ? !availedOn.equals(that.availedOn) : that.availedOn != null) return false;
        if (availMemberAgreed != null ? !availMemberAgreed.equals(that.availMemberAgreed) : that.availMemberAgreed != null)
            return false;
        if (availMemberAgreedon != null ? !availMemberAgreedon.equals(that.availMemberAgreedon) : that.availMemberAgreedon != null)
            return false;
        if (pinEntered != null ? !pinEntered.equals(that.pinEntered) : that.pinEntered != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (recordSubmittedOn != null ? !recordSubmittedOn.equals(that.recordSubmittedOn) : that.recordSubmittedOn != null)
            return false;
        if (recordSubmittedBy != null ? !recordSubmittedBy.equals(that.recordSubmittedBy) : that.recordSubmittedBy != null)
            return false;
        if (expiredOn != null ? !expiredOn.equals(that.expiredOn) : that.expiredOn != null) return false;
        if (consultationDate != null ? !consultationDate.equals(that.consultationDate) : that.consultationDate != null)
            return false;
        if (consultReason != null ? !consultReason.equals(that.consultReason) : that.consultReason != null)
            return false;
        if (docHospId != null ? !docHospId.equals(that.docHospId) : that.docHospId != null) return false;
        if (doctorCode != null ? !doctorCode.equals(that.doctorCode) : that.doctorCode != null) return false;
        if (hospitalCode != null ? !hospitalCode.equals(that.hospitalCode) : that.hospitalCode != null) return false;
        if (fee != null ? !fee.equals(that.fee) : that.fee != null) return false;
        if (historyOfPresentIllness != null ? !historyOfPresentIllness.equals(that.historyOfPresentIllness) : that.historyOfPresentIllness != null)
            return false;
        if (pastOrFamilyHistory != null ? !pastOrFamilyHistory.equals(that.pastOrFamilyHistory) : that.pastOrFamilyHistory != null)
            return false;
        if (reviewOfSystems != null ? !reviewOfSystems.equals(that.reviewOfSystems) : that.reviewOfSystems != null)
            return false;
        if (vitalsBp != null ? !vitalsBp.equals(that.vitalsBp) : that.vitalsBp != null) return false;
        if (vitalsHr != null ? !vitalsHr.equals(that.vitalsHr) : that.vitalsHr != null) return false;
        if (vitalsRr != null ? !vitalsRr.equals(that.vitalsRr) : that.vitalsRr != null) return false;
        if (vitalsTemp != null ? !vitalsTemp.equals(that.vitalsTemp) : that.vitalsTemp != null) return false;
        if (physicalExamination != null ? !physicalExamination.equals(that.physicalExamination) : that.physicalExamination != null)
            return false;
        if (primaryDiagnosisCode != null ? !primaryDiagnosisCode.equals(that.primaryDiagnosisCode) : that.primaryDiagnosisCode != null)
            return false;
        if (primaryDiagnosisIcd10 != null ? !primaryDiagnosisIcd10.equals(that.primaryDiagnosisIcd10) : that.primaryDiagnosisIcd10 != null)
            return false;
        if (primaryDiagnosisDesc != null ? !primaryDiagnosisDesc.equals(that.primaryDiagnosisDesc) : that.primaryDiagnosisDesc != null)
            return false;
        if (dxRemarks != null ? !dxRemarks.equals(that.dxRemarks) : that.dxRemarks != null) return false;
        if (primaryDiagnosisRemarks != null ? !primaryDiagnosisRemarks.equals(that.primaryDiagnosisRemarks) : that.primaryDiagnosisRemarks != null)
            return false;
        if (isCongenital != null ? !isCongenital.equals(that.isCongenital) : that.isCongenital != null) return false;
        if (isMaternity != null ? !isMaternity.equals(that.isMaternity) : that.isMaternity != null) return false;
        if (isMedicolegal != null ? !isMedicolegal.equals(that.isMedicolegal) : that.isMedicolegal != null)
            return false;
        if (planofmanagement != null ? !planofmanagement.equals(that.planofmanagement) : that.planofmanagement != null)
            return false;
        return transamount != null ? transamount.equals(that.transamount) : that.transamount == null;
    }

    @Override
    public int hashCode() {
        int result = transactionId;
        result = 31 * result + (transCode != null ? transCode.hashCode() : 0);
        result = 31 * result + (macerequestId != null ? macerequestId.hashCode() : 0);
        result = 31 * result + (consultSubtype != null ? consultSubtype.hashCode() : 0);
        result = 31 * result + (requestFrom != null ? requestFrom.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (statusRemarks != null ? statusRemarks.hashCode() : 0);
        result = 31 * result + (approvedOn != null ? approvedOn.hashCode() : 0);
        result = 31 * result + (approvedBy != null ? approvedBy.hashCode() : 0);
        result = 31 * result + (approvalRemarks != null ? approvalRemarks.hashCode() : 0);
        result = 31 * result + (disapprovedOn != null ? disapprovedOn.hashCode() : 0);
        result = 31 * result + (disapprovedBy != null ? disapprovedBy.hashCode() : 0);
        result = 31 * result + (disapprovalRemarks != null ? disapprovalRemarks.hashCode() : 0);
        result = 31 * result + (disapprovalReason != null ? disapprovalReason.hashCode() : 0);
        result = 31 * result + (referenceNo != null ? referenceNo.hashCode() : 0);
        result = 31 * result + (validfrom != null ? validfrom.hashCode() : 0);
        result = 31 * result + (validto != null ? validto.hashCode() : 0);
        result = 31 * result + (availedOn != null ? availedOn.hashCode() : 0);
        result = 31 * result + (availMemberAgreed != null ? availMemberAgreed.hashCode() : 0);
        result = 31 * result + (availMemberAgreedon != null ? availMemberAgreedon.hashCode() : 0);
        result = 31 * result + (pinEntered != null ? pinEntered.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (recordSubmittedOn != null ? recordSubmittedOn.hashCode() : 0);
        result = 31 * result + (recordSubmittedBy != null ? recordSubmittedBy.hashCode() : 0);
        result = 31 * result + (expiredOn != null ? expiredOn.hashCode() : 0);
        result = 31 * result + (consultationDate != null ? consultationDate.hashCode() : 0);
        result = 31 * result + (consultReason != null ? consultReason.hashCode() : 0);
        result = 31 * result + (docHospId != null ? docHospId.hashCode() : 0);
        result = 31 * result + (doctorCode != null ? doctorCode.hashCode() : 0);
        result = 31 * result + (hospitalCode != null ? hospitalCode.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        result = 31 * result + (historyOfPresentIllness != null ? historyOfPresentIllness.hashCode() : 0);
        result = 31 * result + (pastOrFamilyHistory != null ? pastOrFamilyHistory.hashCode() : 0);
        result = 31 * result + (reviewOfSystems != null ? reviewOfSystems.hashCode() : 0);
        result = 31 * result + (vitalsBp != null ? vitalsBp.hashCode() : 0);
        result = 31 * result + (vitalsHr != null ? vitalsHr.hashCode() : 0);
        result = 31 * result + (vitalsRr != null ? vitalsRr.hashCode() : 0);
        result = 31 * result + (vitalsTemp != null ? vitalsTemp.hashCode() : 0);
        result = 31 * result + (physicalExamination != null ? physicalExamination.hashCode() : 0);
        result = 31 * result + (primaryDiagnosisCode != null ? primaryDiagnosisCode.hashCode() : 0);
        result = 31 * result + (primaryDiagnosisIcd10 != null ? primaryDiagnosisIcd10.hashCode() : 0);
        result = 31 * result + (primaryDiagnosisDesc != null ? primaryDiagnosisDesc.hashCode() : 0);
        result = 31 * result + (dxRemarks != null ? dxRemarks.hashCode() : 0);
        result = 31 * result + (primaryDiagnosisRemarks != null ? primaryDiagnosisRemarks.hashCode() : 0);
        result = 31 * result + (isCongenital != null ? isCongenital.hashCode() : 0);
        result = 31 * result + (isMaternity != null ? isMaternity.hashCode() : 0);
        result = 31 * result + (isMedicolegal != null ? isMedicolegal.hashCode() : 0);
        result = 31 * result + (planofmanagement != null ? planofmanagement.hashCode() : 0);
        result = 31 * result + (transamount != null ? transamount.hashCode() : 0);
        return result;
    }
}
