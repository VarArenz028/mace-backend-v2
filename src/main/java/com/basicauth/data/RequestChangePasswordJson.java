package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by angulo on 11/23/2016.
 */
public class RequestChangePasswordJson  implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("email")
    private String email;

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("deviceId")
    private String deviceId;

    @JsonProperty("forceChange")
    private Integer forceChange;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getForceChange() {
        return forceChange;
    }

    public void setForceChange(Integer forceChange) {
        this.forceChange = forceChange;
    }
}
