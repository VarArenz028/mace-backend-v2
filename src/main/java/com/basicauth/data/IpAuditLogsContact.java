package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by jjosephmagculang on 3/12/2018.
 */
public class IpAuditLogsContact implements Serializable {

    @JsonProperty("hospEmail")
    private String hospEmail;
    @JsonProperty("hospContact")
    private String hospContact;
    @JsonProperty("memberContact")
    private String memberContact;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("createdDate")
    private String createdDate;


    public String getHospEmail() {
        return hospEmail;
    }

    public void setHospEmail(String hospEmail) {
        this.hospEmail = hospEmail;
    }

    public String getHospContact() {
        return hospContact;
    }

    public void setHospContact(String hospContact) {
        this.hospContact = hospContact;
    }

    public String getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(String memberContact) {
        this.memberContact = memberContact;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}




