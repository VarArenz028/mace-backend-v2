package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by service.incuventure on 5/3/2017.
 */
public class RequestProceduresClinicJson implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("searchType")
    private String searchType;

    @JsonProperty("consultReferenceNo")
    private String consultReferenceNo;

    @JsonProperty("doctorCode")
    private String doctorCode;

    @JsonProperty("proceduresList")
    private List<ProcedureJson> proceduresList;

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getConsultReferenceNo() {
        return consultReferenceNo;
    }

    public void setConsultReferenceNo(String consultReferenceNo) {
        this.consultReferenceNo = consultReferenceNo;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public List<ProcedureJson> getProceduresList() {
        return proceduresList;
    }

    public void setProceduresList(List<ProcedureJson> proceduresList) {
        this.proceduresList = proceduresList;
    }
}
