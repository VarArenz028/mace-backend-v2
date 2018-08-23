package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by angulo on 11/23/2016.
 */
public class ValidatePinJson implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("pin")
    private String pin;

    public ValidatePinJson() {
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
