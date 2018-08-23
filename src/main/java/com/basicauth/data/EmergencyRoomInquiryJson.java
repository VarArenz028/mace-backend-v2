package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by angulo on 11/23/2016.
 */
public class EmergencyRoomInquiryJson implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("memberName")
    private String memberName;

    @JsonProperty("hospitalCode")
    private String hospitalCode;

    @JsonProperty("username")
    private String username;


    @JsonProperty("erReason")
    private String erReason;

    @JsonProperty("dateAdmitted")
    private String dateAdmitted;

    @JsonProperty("searchType")
    private String searchType;



    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   public String getErReason() { return erReason; }

    public void setErReason(String erReason) { this.erReason = erReason; }

    public String getDateAdmitted() { return dateAdmitted; }

    public void setDateAdmitted(String admittedDate) { this.dateAdmitted = admittedDate; }

    public String getSearchType() { return searchType;}

    public void setSearchType(String searchType) { this.searchType = searchType; }
}
