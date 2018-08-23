package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jabito on 03/01/2018.
 */
public class MemberLoaFilter implements Serializable{

    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("status")
    private String status;
    @JsonProperty("serviceTypeId")
    private int serviceTypeId;
    @JsonProperty("hospitalCode")
    private String hospitalCode;
    @JsonProperty("doctorCode")
    private String doctorCode;
    @JsonProperty("procCode")
    private String procCode;
    @JsonProperty("diagCode")
    private String diagCode;
    @JsonProperty("startDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT +8")
    private Date startDate;
    @JsonProperty("endDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT +8")
    private Date endDate;

    //<editor-fold desc="Getters and Setters" default="collapsed">

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
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

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
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

    //</editor-fold>
}
