package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by macbookpro on 5/31/17.
 */

@Table(name = "MACE_SERVICE_SUBTYPE")
public class MaceServiceSubtype implements Serializable{

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("serviceTypeId")
    private Integer serviceTypeId;

    @JsonProperty("code")
    private String code;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("active")
    private Integer active;

    @JsonProperty("lastUpdateOn")
    private Date lastUpdateOn;

    @JsonProperty("lastUpdateBy")
    private Date lastUpdateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getLastUpdateOn() {
        return lastUpdateOn;
    }

    public void setLastUpdateOn(Date lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }

    public Date getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Date lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
}
