package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sourceforge.jtds.jdbc.DateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jabito on 05/10/2017.
 */
public class MaceAnnouncements implements Serializable {

    @JsonProperty("messageHeader")
    private String messageHeader;
    @JsonProperty("messageContent")
    private String messageContent;
    @JsonProperty("datePosted")
    @JsonFormat(pattern = "yyyy-MM-dd HH:00", timezone = "GMT +8")
    private Date datePosted;
    @JsonProperty("postedBy")
    private String postedBy;
    @JsonProperty("updatedOn")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT +8")
    private Date updatedOn;
    @JsonProperty("updatedBy")
    private String updatedBy;
    @JsonProperty("messageType")
    private String messageType;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(String messageHeader) {
        this.messageHeader = messageHeader;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
