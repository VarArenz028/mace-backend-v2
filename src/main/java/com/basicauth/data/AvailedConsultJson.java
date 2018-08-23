package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by service.incuventure on 4/10/2017.
 */
public class AvailedConsultJson implements Serializable {

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("birthDate")
    private String birthDate;

    @JsonProperty("searchType")
    private String searchType;

    @JsonProperty("doctorCode")
    private String doctorCode;

    public AvailedConsultJson(){}

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }
}