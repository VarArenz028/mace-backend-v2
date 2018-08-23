package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jabito on 04/08/2017.
 */
public class MaceRequestReturn implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty("requestId")
    private Integer requestId;

    @JsonProperty("requestCode")
    private String requestCode;

    @JsonProperty("serviceTypeId")
    private Integer serviceTypeId;

    @JsonProperty("serviceType")
    private String serviceType;

    @JsonProperty("memCode")
    private String memCode;

    @JsonProperty("memLname")
    private String memLname;

    @JsonProperty("memFname")
    private String memFname;

    @JsonProperty("memMi")
    private String memMi;

    @JsonProperty("memCompany")
    private String memCompany;

    @JsonProperty("memAcct")
    private String memAcct;

    @JsonProperty("memStat")
    private String memStat;

    @JsonProperty("memBdate")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT +8")
    private Date memBdate;

    @JsonProperty("memGender")
    private String memGender;

    @JsonProperty("memAge")
    private Integer memAge;

    @JsonProperty("memType")
    private String memType;

    @JsonProperty("idremarks")
    private String idremarks;

    @JsonProperty("acctValidity")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date acctValidity;

    @JsonProperty("acctEffectivity")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date acctEffectivity;

    @JsonProperty("requestOrigin")
    private String requestOrigin;

    @JsonProperty("requestFromhosp")
    private String requestFromhosp;

    @JsonProperty("requestFrommem")
    private String requestFrommem;

    @JsonProperty("requestBy")
    private String requestBy;

    @JsonProperty("requestDevice")
    private String requestDevice;

    @JsonProperty("requestDatetime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date requestDatetime;

    @JsonProperty("disclaimerTicked")
    private Boolean disclaimerTicked;

    @JsonProperty("lastupdateOn")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date lastupdateOn;

    @JsonProperty("lastupdateBy")
    private String lastupdateBy;

    @JsonProperty("status")
    private String status;

    private String statusAssignee;
    private String statusRemarks;

    @JsonProperty("override")
    private Boolean override;

    @JsonProperty("parRequestId")
    private Integer parRequestId;

    @JsonProperty("mbasCode")
    private String mbasCode;

    @JsonProperty("mbasApprover")
    private String mbasApprover;

    @JsonProperty("mbasupdateOn")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date mbasupdateOn;

    @JsonProperty("requestType")
    private String requestType;

    @JsonProperty("requestTypeDetail01")
    private String requestTypeDetail01;

    @JsonProperty("requestTypeDetail02")
    private String requestTypeDetail02;

    @JsonProperty("requestTypeDetail03")
    private String requestTypeDetail03;

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

    @JsonProperty("mappedTest")
    private MappedTest[] mappedTest;

    @JsonProperty("hospitalName")
    private String hospitalName;

    @JsonProperty("hospitalAddress")
    private String hospitalAddress;

    @JsonProperty("hospitalContact")
    private String hospitalContact;

    @JsonProperty("diagnosis")
    private String diagnosis;

    @JsonProperty("diagType")
    private Integer diagType;

    @JsonProperty("hospContact")
    private String hospContact;

    @JsonProperty("hospEmail")
    private String hospEmail;

    @JsonProperty("memEmail")
    private String memEmail;

    @JsonProperty("memContact")
    private String memContact;

    @JsonProperty("totalAmount")
    private BigDecimal totalAmount;

    @JsonProperty("groupedByCostCenters")
    private GroupedByCostCenter[] groupedByCostCenters;

    @JsonProperty("attachments")
    private List<MaceRequestAttachment> attachments;

    public static class GroupedByCostCenter{

        @JsonProperty("costCenter")
        private String costCenter;

        @JsonProperty("subTotal")
        private BigDecimal subTotal;

        @JsonProperty("status")
        private String status;

        @JsonProperty("groupedByDiag")
        private GroupedByDiag[] groupedByDiag;

        public static class GroupedByDiag implements Serializable{
            @JsonProperty("diagType")
            private Integer diagType;

            @JsonProperty("diagDesc")
            private String diagDesc;

            @JsonProperty("approvalNo")
            private String approvalNo;

            @JsonProperty("mappedTests")
            private MappedTest[] mappedTests;

            public Integer getDiagType() {
                return diagType;
            }

            public void setDiagType(Integer diagType) {
                this.diagType = diagType;
            }

            public String getDiagDesc() {
                return diagDesc;
            }

            public void setDiagDesc(String diagDesc) {
                this.diagDesc = diagDesc;
            }

            public String getApprovalNo() {
                return approvalNo;
            }

            public void setApprovalNo(String approvalNo) {
                this.approvalNo = approvalNo;
            }

            public MappedTest[] getMappedTests() {
                return mappedTests;
            }

            public void setMappedTests(MappedTest[] mappedTests) {
                this.mappedTests = mappedTests;
            }
        }

        public String getCostCenter() {
            return costCenter;
        }

        public void setCostCenter(String costCenter) {
            this.costCenter = costCenter;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public GroupedByDiag[] getGroupedByDiag() {
            return groupedByDiag;
        }

        public void setGroupedByDiag(GroupedByDiag[] groupedByDiag) {
            this.groupedByDiag = groupedByDiag;
        }

        public BigDecimal getSubTotal() {
            return subTotal;
        }

        public void setSubTotal(BigDecimal subTotal) {
            this.subTotal = subTotal;
        }
    }

    public MappedTest[] getMappedTest() {
        return mappedTest;
    }

    public void setMappedTest(MappedTest[] mappedTest) {
        this.mappedTest = mappedTest;
    }

    public static class MappedTest{
        @JsonProperty("procCode")
        private String procCode;
        @JsonProperty("diagType")
        private Integer diagType;
        @JsonProperty("procDesc")
        private String procDesc;
        @JsonProperty("costCenter")
        private String costCenter;
        @JsonProperty("amount")
        private BigDecimal amount;
        @JsonProperty("notes")
        private String notes;

        public String getProcCode() {
            return procCode;
        }

        public void setProcCode(String procCode) {
            this.procCode = procCode;
        }

        public Integer getDiagType() {
            return diagType;
        }

        public void setDiagType(Integer diagType) {
            this.diagType = diagType;
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

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    }

    //<editor-fold default=collapsed desc="Getters and Setters">


    public List<MaceRequestAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MaceRequestAttachment> attachments) {
        this.attachments = attachments;
    }

    public Integer getDiagType() {
        return diagType;
    }

    public void setDiagType(Integer diagType) {
        this.diagType = diagType;
    }

    public GroupedByCostCenter[] getGroupedByCostCenters() {
        return groupedByCostCenters;
    }

    public void setGroupedByCostCenters(GroupedByCostCenter[] groupedByCostCenters) {
        this.groupedByCostCenters = groupedByCostCenters;
    }

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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalContact() {
        return hospitalContact;
    }

    public void setHospitalContact(String hospitalContact) {
        this.hospitalContact = hospitalContact;
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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestTypeDetail01() {
        return requestTypeDetail01;
    }

    public void setRequestTypeDetail01(String requestTypeDetail01) {
        this.requestTypeDetail01 = requestTypeDetail01;
    }

    public String getRequestTypeDetail02() {
        return requestTypeDetail02;
    }

    public void setRequestTypeDetail02(String requestTypeDetail02) {
        this.requestTypeDetail02 = requestTypeDetail02;
    }

    public String getRequestTypeDetail03() {
        return requestTypeDetail03;
    }

    public void setRequestTypeDetail03(String requestTypeDetail03) {
        this.requestTypeDetail03 = requestTypeDetail03;
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

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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

    //</editor-fold>

    public MaceRequestReturn(MaceRequest mr){
        this.requestId = mr.getRequestId();
        this.requestCode = mr.getRequestCode();
        this.serviceTypeId = mr.getServiceTypeId();
        this.serviceType = mr.getServiceType();
        this.memCode = mr.getMemCode();
        this.memLname = mr.getMemLname();
        this.memFname = mr.getMemFname();
        this.memMi = mr.getMemMi();
        this.memCompany = mr.getMemCompany();
        this.memAcct = mr.getMemAcct();
        this.memStat = mr.getMemStat();
        this.memBdate = mr.getMemBdate();
        this.memGender = mr.getMemGender();
        this.memAge = mr.getMemAge();
        this.memType = mr.getMemType();
        this.idremarks = mr.getIdremarks();
        this.acctValidity = mr.getAcctValidity();
        this.acctEffectivity = mr.getAcctEffectivity();
        this.requestOrigin = mr.getRequestOrigin();
        this.requestFromhosp = mr.getRequestFromhosp();
        this.requestFrommem = mr.getRequestFrommem();
        this.requestBy = mr.getRequestBy();
        this.requestDevice = mr.getRequestDevice();
        this.requestDatetime = mr.getRequestDatetime();
        this.disclaimerTicked = mr.getDisclaimerTicked();
        this.lastupdateOn = mr.getLastupdateOn();
        this.lastupdateBy = mr.getLastupdateBy();
        this.status = mr.getStatus();
        this.statusAssignee= mr.getStatusAssignee();
        this.statusRemarks = mr.getStatusRemarks();
        this.override = mr.getOverride();
        this.parRequestId = mr.getRequestId();
        this.mbasCode = mr.getMbasCode();
        this.mbasApprover = mr.getMbasApprover();
        this.mbasupdateOn = mr.getMbasupdateOn();
        this.requestType = mr.getRequestType();
        this.hospContact = mr.getHospContact();
        this.hospEmail = mr.getHospEmail();
    }
}
