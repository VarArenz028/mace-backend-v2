package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by service.incuventure on 3/23/2017.
 */
public class BlockingMessages implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;
    @Column(name = "blockingCode", unique = true)
    @JsonProperty("blockingCode")
    private String blockingCode;
    @Column(name = "remarks")
    @JsonProperty("remarks")
    private String remarks;
    @Column(name = "responseCode")
    @JsonProperty("responseCode")
    private String responseCode;
    @Column(name = "responseDesc")
    @JsonProperty("responseDesc")
    private String responseDesc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBlockingCode() {
        return blockingCode;
    }

    public void setBlockingCode(String blockingCode) {
        this.blockingCode = blockingCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }
}
