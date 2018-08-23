package com.basicauth.data;

import com.basicauth.domain.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;


/**
 * Created by shem.sandiego on 2/9/2018.
 */
public class MaceRequestErJson {

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

    @JsonProperty("memStat")
    private String memStat;

    @JsonProperty("memBdate")
    private Date memBdate;

    @JsonProperty("memGender")
    private String memGender;

    @JsonProperty("memAge")
    private Integer memAge;

    @JsonProperty("memType")
    private String memType;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("reasonRemarks")
    private String reasonRemarks;

    @JsonProperty("acctValidity")
    private Date acctValidity;

    @JsonProperty("acctEffectivity")
    private Date acctEffectivity;

    @JsonProperty("requestDatetime")
    private Date requestDatetime;

    @JsonProperty("requestDevice")
    private String requestDevice;

//    Request Through
    @JsonProperty("requestOrigin")
    private String requestOrigin;

    @JsonProperty("totalUtilization")
    private String totalUtilization;

    @JsonProperty("requestFrom")
    private String requestFrom;

    @JsonProperty("requestByCode")
    private String requestByCode;

    @JsonProperty("requestBy")
    private String requestBy;

    @JsonProperty("requestFromHosp")
    private String requestFromHosp;

    @JsonProperty("status")
    private String status;

    @JsonProperty("maceReqErDiagList")
    private List<MaceReqErDiag> maceReqErDiagList;

    @JsonProperty("maceReqErDoctorList")
    private List<MaceReqErDoctor> maceReqErDoctorList;

    @JsonProperty("maceReqErProcedureList")
    private List<MaceReqErProcedure> maceReqErProcedureList;

    @JsonProperty("maceReqErOtherChargesList")
    private List<MaceReqErOtherCharges> maceReqErOtherChargesList;

    @JsonProperty("maceReqErOtherInformationList")
    private List<MaceReqErOtherInformation> maceReqErOtherInformationList;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Date getRequestDatetime() {
        return requestDatetime;
    }

    public void setRequestDatetime(Date requestDatetime) {
        this.requestDatetime = requestDatetime;
    }

    public String getRequestDevice() {
        return requestDevice;
    }

    public void setRequestDevice(String requestDevice) {
        this.requestDevice = requestDevice;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }

    public String getTotalUtilization() {
        return totalUtilization;
    }

    public void setTotalUtilization(String totalUtilization) {
        this.totalUtilization = totalUtilization;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public List<MaceReqErDiag> getMaceReqErDiagList() {
        return maceReqErDiagList;
    }

    public void setMaceReqErDiagList(List<MaceReqErDiag> maceReqErDiagList) {
        this.maceReqErDiagList = maceReqErDiagList;
    }

    public List<MaceReqErDoctor> getMaceReqErDoctorList() {
        return maceReqErDoctorList;
    }

    public void setMaceReqErDoctorList(List<MaceReqErDoctor> maceReqErDoctorList) {
        this.maceReqErDoctorList = maceReqErDoctorList;
    }

    public List<MaceReqErProcedure> getMaceReqErProcedureList() {
        return maceReqErProcedureList;
    }

    public void setMaceReqErProcedureList(List<MaceReqErProcedure> maceReqErProcedureList) {
        this.maceReqErProcedureList = maceReqErProcedureList;
    }

    public List<MaceReqErOtherCharges> getMaceReqErOtherChargesList() {
        return maceReqErOtherChargesList;
    }

    public void setMaceReqErOtherChargesList(List<MaceReqErOtherCharges> maceReqErOtherChargesList) {
        this.maceReqErOtherChargesList = maceReqErOtherChargesList;
    }

    public List<MaceReqErOtherInformation> getMaceReqErOtherInformationList() {
        return maceReqErOtherInformationList;
    }

    public void setMaceReqErOtherInformationList(List<MaceReqErOtherInformation> maceReqErOtherInformationList) {
        this.maceReqErOtherInformationList = maceReqErOtherInformationList;
    }

    public String getRequestByCode() {
        return requestByCode;
    }

    public void setRequestByCode(String requestByCode) {
        this.requestByCode = requestByCode;
    }

    public String getRequestFromHosp() {
        return requestFromHosp;
    }

    public void setRequestFromHosp(String requestFromHosp) {
        this.requestFromHosp = requestFromHosp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReasonRemarks() {
        return reasonRemarks;
    }

    public void setReasonRemarks(String reasonRemarks) {
        this.reasonRemarks = reasonRemarks;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }
}
