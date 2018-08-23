package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by angulo on 11/21/2016.
 */
public class AddAccountJson implements Serializable {

    private static final long serialVersionUID = 1L;



    @JsonProperty("depMemberCode")
    private String depMemberCode;

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("username")
    private String username;

    public String getDepMemberCode() {
        return depMemberCode;
    }

    public void setDepMemberCode(String depMemberCode) {
        this.depMemberCode = depMemberCode;
    }


    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
