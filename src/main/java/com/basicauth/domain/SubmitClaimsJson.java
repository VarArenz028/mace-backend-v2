package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jabito on 16/01/2018.
 */
public class SubmitClaimsJson implements Serializable{

    @JsonProperty("doctorCode")
    private String doctorCode;
    @JsonProperty("serviceType")
    private String serviceType;
    @JsonProperty("requestCodes")
    private List<String> requestCodes;

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<String> getRequestCodes() {
        return requestCodes;
    }

    public void setRequestCodes(List<String> requestCodes) {
        this.requestCodes = requestCodes;
    }
}
