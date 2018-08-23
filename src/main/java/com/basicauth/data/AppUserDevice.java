package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by service.incuventure on 4/4/2017.
 */
@Entity
@Table(name = "APPUSER_DEVICE")
public class AppUserDevice implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "ID")
    private long id;
    @Column(name = "APPUSER_ID")
    @JsonProperty("appuserId")
    private String appuserId;
    @Column(name = "DEVICE_ID")
    @JsonProperty("deviceId")
    private String deviceId;
    @Column(name = "VALID_FROM")
    @JsonProperty("validFrom")
    private String validFrom;
    @Column(name = "VALID_TO")
    @JsonProperty("validTo")
    private String validTo;
    @Column(name = "VERSION_NO")
    @JsonProperty("versionNo")
    private String versionNo;

    public AppUserDevice() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppuserId() {
        return appuserId;
    }

    public void setAppuserId(String appuserId) {
        this.appuserId = appuserId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }
}
