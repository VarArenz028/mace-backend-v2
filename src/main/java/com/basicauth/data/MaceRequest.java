package com.basicauth.data;

import com.basicauth.domain.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bryan on 5/6/2017.
 */
@Entity
@Table(name = "mace_request")
public class MaceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private Integer requestId;

    @JsonProperty("requestCode")
    @Column(name = "REQUEST_CODE")
    private String requestCode;

    @JsonProperty("serviceTypeId")
    @Column(name = "servicetype_id")
    private Integer serviceTypeId;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @JsonProperty("serviceType")
    private String serviceType;

    @JsonProperty("memCode")
    @Column(name = "member_code")
    private String memCode;

    @JsonProperty("memLname")
    @Column(name = "mem_lname")
    private String memLname;

    @JsonProperty("memFname")
    @Column(name = "mem_fname")
    private String memFname;

    @JsonProperty("memMi")
    @Column(name = "mem_mi")
    private String memMi;

    @JsonProperty("memCompany")
    @Column(name = "mem_company")
    private String memCompany;

    @JsonProperty("memAcct")
    @Column(name = "mem_acct")
    private String memAcct;

    @JsonProperty("memStat")
    @Column(name = "mem_stat")
    private String memStat;

    @JsonProperty("memBdate")
    @Column(name = "mem_bdate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT +8")
    private Date memBdate;

    @JsonProperty("memGender")
    @Column(name = "mem_gender")
    private String memGender;

    @JsonProperty("memAge")
    @Column(name = "mem_age")
    private Integer memAge;

    @JsonProperty("memType")
    @Column(name = "mem_type")
    private String memType;

    @JsonProperty("idremarks")
    @Column(name = "idremarks")
    private String idremarks;

    @JsonProperty("acctValidity")
    @Column(name = "acct_validity")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date acctValidity;

    @JsonProperty("acctEffectivity")
    @Column(name = "acct_effectivity")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date acctEffectivity;

    @JsonProperty("requestOrigin")
    @Column(name = "request_origin")
    private String requestOrigin;

    @JsonProperty("requestFromhosp")
    @Column(name = "request_fromhosp")
    private String requestFromhosp;

    @JsonProperty("requestFrommem")
    @Column(name = "request_frommem")
    private String requestFrommem;

    @JsonProperty("requestByCode")
    @Column(name = "request_by_code")
    private String requestByCode;

    @JsonProperty("requestBy")
    @Column(name = "request_by")
    private String requestBy;

    @JsonProperty("requestDevice")
    @Column(name = "request_device")
    private String requestDevice;

    @JsonProperty("requestDatetime")
    @Column(name = "request_datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date requestDatetime;

    @JsonProperty("disclaimerTicked")
    @Column(name = "disclaimer_ticked")
    private Boolean disclaimerTicked;

    @JsonProperty("lastupdateOn")
    @Column(name = "lastupdate_on")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date lastupdateOn;

    @JsonProperty("lastupdateBy")
    @Column(name = "lastupdate_by")
    private String lastupdateBy;

    @JsonProperty("status")
    @Column(name = "status")
    private String status;

    @JsonProperty("statusAssignee")
    @Column(name = "status_assignee")
    private String statusAssignee;

    private String statusRemarks;

    @JsonProperty("override")
    @Column(name = "override")
    private Boolean override;

    @JsonProperty("parRequestId")
    @Column(name = "par_request_id")
    private Integer parRequestId;

    @JsonProperty("mbasCode")
    @Column(name = "mbas_code")
    private String mbasCode;

    @JsonProperty("mbasApprover")
    @Column(name = "mbas_approver")
    private String mbasApprover;

    @JsonProperty("mbasupdateOn")
    @Column(name = "mbasupdate_on")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date mbasupdateOn;

    @JsonProperty("requestType")
    private String requestType;

    @JsonProperty("doctorName")
    private String doctorName;

    @JsonProperty("doctorSpec")
    private String doctorSpec;

    @JsonProperty("primaryDiag")
    private String primaryDiag;

    @JsonProperty("reasonForConsult")
    private String reasonForConsult;

    @JsonProperty("approvalNo")
    private String approvalNo;

    @JsonProperty("hospContact")
    private String hospContact;

    @JsonProperty("hospEmail")
    private String hospEmail;

    @JsonProperty("memEmail")
    private String memEmail;

    @JsonProperty("memContact")
    private String memContact;

    @JsonProperty("totalUtilization")
    @Column(name = "total_utilization")
    private String totalUtilization;

    @JsonProperty("admissionType")
    @Column(name = "admission_type")
    private String admissionType;

    @JsonProperty("admissionDate")
    @Column(name = "admission_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date admissionDate;

    @JsonProperty("diagCaseType")
    private String diagCaseType;

    public String getDiagCaseType() {
        return diagCaseType;
    }

    public void setDiagCaseType(String diagCaseType) {
        this.diagCaseType = diagCaseType;
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public String getMemContact() {
        return memContact;
    }

    public void setMemContact(String memContact) {
        this.memContact = memContact;
    }

    public String getHospContact() {
        return hospContact;
    }

    public void setHospContact(String hospContact) {
        this.hospContact = hospContact;
    }

    public String getHospEmail() {
        return hospEmail;
    }

    public void setHospEmail(String hospEmail) {
        this.hospEmail = hospEmail;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getPrimaryDiag() {
        return primaryDiag;
    }

    public void setPrimaryDiag(String primaryDiag) {
        this.primaryDiag = primaryDiag;
    }

    public String getReasonForConsult() {
        return reasonForConsult;
    }

    public void setReasonForConsult(String reasonForConsult) {
        this.reasonForConsult = reasonForConsult;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpec() {
        return doctorSpec;
    }

    public void setDoctorSpec(String doctorSpec) {
        this.doctorSpec = doctorSpec;
    }


    //<editor-fold desc="Constructors" default="collapsed">
    public MaceRequest() {
    }

    public MaceRequest(CustomerCare c, MemberDetails member, Integer serviceTypeId,
                       String requestOrigin, String username) {
        this.serviceTypeId = serviceTypeId;
        this.memCode = c.getMemberCode();
        this.memLname = c.getMemLname();
        this.memFname = c.getMemFname();
        this.memMi = c.getMemMi();
        this.memCompany = c.getMemCompany();
        this.memAcct = member.getACCOUNT_CODE();
        this.memStat = member.getMem_OStat_Code();
        try {
            this.memBdate = new SimpleDateFormat("MMM dd, yyyy").parse(member.getBDAY());
            this.acctValidity = new SimpleDateFormat("MMM dd, yyyy").parse(member.getVAL_DATE());
            this.acctEffectivity = new SimpleDateFormat("MMM dd, yyyy").parse(member.getEFF_DATE());
        } catch (Exception e) {
        }
        this.memGender = member.getMEM_SEX() == 0 ? "FEMALE" : "MALE";
        this.memType = member.getMEM_TYPE();
        this.idremarks = null != member.getID_REM() ? member.getID_REM() : "";
        this.requestOrigin = requestOrigin;
        this.requestFromhosp = c.getHospitalCode();
        this.requestFrommem = c.getMemberCode();
        this.requestBy = username;
        this.requestDevice = c.getDeviceId();
        this.requestDatetime = c.getDateAdmitted();
        this.disclaimerTicked = true; //todo get this from app
        this.status = c.getActionTaken() == 0 ? "APPROVED" : "PENDING";
        this.override = false;
    }
    //</editor-fold>


    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
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

    public String getMemAcct() {
        return memAcct;
    }

    public void setMemAcct(String memAcct) {
        this.memAcct = memAcct;
    }

    public String getMemStat() {
        return memStat;
    }

    public void setMemStat(String memStat) {
        this.memStat = memStat;
    }

    public Date getMemBdate() {
        return memBdate;
    }

    public void setMemBdate(Date memBdate) {
        this.memBdate = memBdate;
    }

    public String getMemGender() {
        return memGender;
    }

    public void setMemGender(String memGender) {
        this.memGender = memGender;
    }

    public Integer getMemAge() {
        return memAge;
    }

    public void setMemAge(Integer memAge) {
        this.memAge = memAge;
    }

    public String getMemType() {
        return memType;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public String getIdremarks() {
        return idremarks;
    }

    public void setIdremarks(String idremarks) {
        this.idremarks = idremarks;
    }

    public Date getAcctValidity() {
        return acctValidity;
    }

    public void setAcctValidity(Date acctValidity) {
        this.acctValidity = acctValidity;
    }

    public Date getAcctEffectivity() {
        return acctEffectivity;
    }

    public void setAcctEffectivity(Date acctEffectivity) {
        this.acctEffectivity = acctEffectivity;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public String getRequestFromhosp() {
        return requestFromhosp;
    }

    public void setRequestFromhosp(String requestFromhosp) {
        this.requestFromhosp = requestFromhosp;
    }

    public String getRequestFrommem() {
        return requestFrommem;
    }

    public void setRequestFrommem(String requestFrommem) {
        this.requestFrommem = requestFrommem;
    }

    public String getRequestByCode() {
        return requestByCode;
    }

    public void setRequestByCode(String requestByCode) {
        this.requestByCode = requestByCode;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getRequestDevice() {
        return requestDevice;
    }

    public void setRequestDevice(String requestDevice) {
        this.requestDevice = requestDevice;
    }

    public Date getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(Date requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public Boolean getDisclaimerTicked() {
        return disclaimerTicked;
    }

    public void setDisclaimerTicked(Boolean disclaimerTicked) {
        this.disclaimerTicked = disclaimerTicked;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusAssignee() {
        return statusAssignee;
    }

    public void setStatusAssignee(String statusAssignee) {
        this.statusAssignee = statusAssignee;
    }

    public String getStatusRemarks() {
        return statusRemarks;
    }

    public void setStatusRemarks(String statusRemarks) {
        this.statusRemarks = statusRemarks;
    }

    public Boolean getOverride() {
        return override;
    }

    public void setOverride(Boolean override) {
        this.override = override;
    }

    public Integer getParRequestId() {
        return parRequestId;
    }

    public void setParRequestId(Integer parRequestId) {
        this.parRequestId = parRequestId;
    }

    public String getMbasCode() {
        return mbasCode;
    }

    public void setMbasCode(String mbasCode) {
        this.mbasCode = mbasCode;
    }

    public String getMbasApprover() {
        return mbasApprover;
    }

    public void setMbasApprover(String mbasApprover) {
        this.mbasApprover = mbasApprover;
    }

    public Date getMbasupdateOn() {
        return mbasupdateOn;
    }

    public void setMbasupdateOn(Date mbasupdateOn) {
        this.mbasupdateOn = mbasupdateOn;
    }

    public String getTotalUtilization() {
        return totalUtilization;
    }

    public void setTotalUtilization(String totalUtilization) {
        this.totalUtilization = totalUtilization;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setMemberDetails(MemberDetails memberDetails) {
        try {
            this.setMemBdate(new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getBDAY()));
            this.setAcctValidity(new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getVAL_DATE()));
            this.setAcctEffectivity(new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getEFF_DATE()));
        } catch (Exception e) {
        }
        this.setMemFname(memberDetails.getMEM_FNAME());
        this.setMemCompany(memberDetails.getACCOUNT_NAME());
        this.setMemCode(memberDetails.getPRIN_CODE());
        this.setMemLname(memberDetails.getMEM_LNAME());
        this.setMemMi("");
        this.setMemAcct(memberDetails.getACCOUNT_CODE());
        this.setMemAge(memberDetails.getAGE());
        this.setMemGender(memberDetails.getMEM_SEX() == 0 ? "FEMALE" : "MALE");
        this.setMemStat(memberDetails.getMem_OStat_Code());
        this.setMemType(memberDetails.getMEM_TYPE());
    }

    public void setUpdateDetails(UpdateInpatientJson updateDetails) {
        this.requestDatetime = updateDetails.getDateAdmitted();
        this.lastupdateOn = new Date();
        this.lastupdateBy = updateDetails.getUsername();
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
