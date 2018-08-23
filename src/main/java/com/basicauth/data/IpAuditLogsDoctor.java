package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by jjosephmagculang on 3/12/2018.
 */
public class IpAuditLogsDoctor implements Serializable {


    @JsonProperty("doctorCode")
    private String doctorCode;
    @JsonProperty("docLname")
    private String docLname;
    @JsonProperty("docFname")
    private String docFname;
    @JsonProperty("docMname")
    private String docMname;
    @JsonProperty("docSpec")
    private String docSpec;
    @JsonProperty("isAccredited")
    private String isAccredited;
    @JsonProperty("addedOn")
    private String addedOn;
    @JsonProperty("addedBy")
    private String addedBy;
    @JsonProperty("fullName")
    private String fullName;

    public String getDocSpec() {
        return docSpec;
    }

    public void setDocSpec(String docSpec) {
        this.docSpec = docSpec;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDocLname() {
        return docLname;
    }

    public void setDocLname(String docLname) {
        this.docLname = docLname;
    }

    public String getDocFname() {
        return docFname;
    }

    public void setDocFname(String docFname) {
        this.docFname = docFname;
    }

    public String getDocMname() {
        return docMname;
    }

    public void setDocMname(String docMname) {
        this.docMname = docMname;
    }

    public String getIsAccredited() {
        return isAccredited;
    }

    public void setIsAccredited(String isAccredited) {
        this.isAccredited = isAccredited;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }
}
