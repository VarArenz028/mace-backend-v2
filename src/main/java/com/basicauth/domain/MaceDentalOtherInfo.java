package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class MaceDentalOtherInfo implements Serializable {

    private String id;
    private int dentalReqId;
    private String infoType;
    private String details;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT+8")
    private Date createdOn;
    private String createdBy;

    public MaceDentalOtherInfo(RequestDentalJson.OtherInformation otherInformation) {
        this.infoType = otherInformation.getInfoType();
        this.details = otherInformation.getDetails();
        this.createdOn = new Date();
    }

    //<editor-fold desc="Getters and Setters" defaultState="collapsed">

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDentalReqId() {
        return dentalReqId;
    }

    public void setDentalReqId(int dentalReqId) {
        this.dentalReqId = dentalReqId;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
//</editor-fold>
}
