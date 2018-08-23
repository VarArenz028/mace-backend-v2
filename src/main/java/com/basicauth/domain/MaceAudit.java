package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by macbookpro on 3/2/18.
 */
public class MaceAudit {

    @JsonProperty("maceRequestId")
    private int maceRequestId;
    @JsonProperty("referenceColumnId")
    private int referenceColumnId;
    @JsonProperty("oldValue")
    private String oldValue;
    @JsonProperty("newValue")
    private String newValue;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("createdDate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date createdDate;


    public int getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(int maceRequestId) {
        this.maceRequestId = maceRequestId;
    }


    public int getReferenceColumnId() {
        return referenceColumnId;
    }

    public void setReferenceColumnId(int referenceColumnId) {
        this.referenceColumnId = referenceColumnId;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


}
