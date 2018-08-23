package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jabito on 05/02/2018.
 */
public class MaceIPRequestLogs implements Serializable{

    @JsonProperty("maceRequestId")
    private int maceRequestId;

    @JsonProperty("transactionId")
    private int transactionId;

    @JsonProperty("requestDateTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date requestDateTime;

    @JsonProperty("updatedBy")
    private String updatedBy;

    public int getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(int maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
