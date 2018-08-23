package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by service.incuventure on 4/4/2017.
 */
@Entity
@Table(name = "APP_VERSION")
public class AppVersion implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;
    @Column(name = "USER_TYPE")
    @JsonProperty("userType")
    private String userType;
    @Column(name = "VERSION_NO")
    @JsonProperty("versionNo")
    private String versionNo;
    @Column(name = "FORCE_VERSION")
    @JsonProperty("forceVersion")
    private String forceVersion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getForceVersion() {
        return forceVersion;
    }

    public void setForceVersion(String forceVersion) {
        this.forceVersion = forceVersion;
    }
}
