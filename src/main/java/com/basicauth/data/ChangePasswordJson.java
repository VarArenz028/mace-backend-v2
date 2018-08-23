package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by angulo on 11/23/2016.
 */
public class ChangePasswordJson implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("username")
    private String username;

    @JsonProperty("newPassword")
    private String newPassword;

    @JsonProperty("oldPassword")
    private String oldPassword;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
