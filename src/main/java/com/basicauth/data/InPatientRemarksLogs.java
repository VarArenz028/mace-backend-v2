package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jjosephmagculang on 3/12/2018.
 */
public class InPatientRemarksLogs implements Serializable {
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("dateTimeAdded")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date dateTimeAdded;
    @JsonProperty("createdBy")
    private String createdBy;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getDateTimeAdded() {
        return dateTimeAdded;
    }

    public void setDateTimeAdded(Date dateTimeAdded) {
        this.dateTimeAdded = dateTimeAdded;
    }

}
