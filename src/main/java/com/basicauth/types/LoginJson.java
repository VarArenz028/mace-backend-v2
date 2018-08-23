package com.basicauth.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Giancarlo Angulo.
 */
public class LoginJson implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;


    @JsonProperty("deviceID")
    private String deviceId;

    @JsonProperty("versionNo")
    private String versionNo;

    public LoginJson() {
    }

    public LoginJson(String username) {
        this.username = username;
    }

    public LoginJson(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginJson loginJson = (LoginJson) o;

        if (getUsername() != null ? !getUsername().equals(loginJson.getUsername()) : loginJson.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(loginJson.getPassword()) : loginJson.getPassword() != null)
            return false;
        if (getVersionNo() != null ? !getVersionNo().equals(loginJson.getVersionNo()) : loginJson.getVersionNo() != null)
            return false;
        return getDeviceId() != null ? getDeviceId().equals(loginJson.getDeviceId()) : loginJson.getDeviceId() == null;

    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getDeviceId() != null ? getDeviceId().hashCode() : 0);
        result = 31 * result + (getVersionNo() != null ? getVersionNo().hashCode() : 0);
        return result;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserJson userJson = (UserJson) o;
//        return Objects.equals(getUsername(), userJson.getUsername()) && Objects.equals(getMemberCode(), userJson.getMemberCode());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getUsername());
//    }
}
