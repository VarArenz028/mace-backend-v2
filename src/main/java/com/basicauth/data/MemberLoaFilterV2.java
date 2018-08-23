package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jjosepmagculang on 5/2/2018.
 */
public class MemberLoaFilterV2 implements Serializable {

    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("findBy")
    private String findBy;
    @JsonProperty("sortBy")
    private int sortBy;
    @JsonProperty("status")
    private String status;
    @JsonProperty("serviceTypeId")
    private int serviceTypeId;
    @JsonProperty("hospitalCode")
    private List<String> hospitalCodes;
    @JsonProperty("doctorCode")
    private List<String> doctorCodes;
    @JsonProperty("procIds")
    private List<String> procIds;
    @JsonProperty("diagIds")
    private List<String> diagIds;
    @JsonProperty("startDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT +8")
    private Date startDate;
    @JsonProperty("endDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT +8")
    private Date endDate;
    @JsonProperty("count")
    private int count;
    @JsonProperty("offset")
    private int offset;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getFindBy() {
        return findBy;
    }

    public void setFindBy(String findBy) {
        this.findBy = findBy;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public List<String> getHospitalCodes() {
        return hospitalCodes;
    }

    public void setHospitalCodes(List<String> hospitalCodes) {
        this.hospitalCodes = hospitalCodes;
    }

    public List<String> getDoctorCodes() {
        return doctorCodes;
    }

    public void setDoctorCodes(List<String> doctorCodes) {
        this.doctorCodes = doctorCodes;
    }

    public List<String> getProcIds() {
        return procIds;
    }

    public void setProcIds(List<String> procIds) {
        this.procIds = procIds;
    }

    public List<String> getDiagIds() {
        return diagIds;
    }

    public void setDiagIds(List<String> diagIds) {
        this.diagIds = diagIds;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
