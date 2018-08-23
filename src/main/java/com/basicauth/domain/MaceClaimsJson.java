package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Jabito on 12/01/2018.
 */
public class MaceClaimsJson implements Serializable{

    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("loaNumber")
    private String loaNumber;
    @JsonProperty("visitDate")
    private String visitDate;
    @JsonProperty("saAmount")
    private BigDecimal saAmount;
    @JsonProperty("disapproved")
    private BigDecimal disapproved;
    @JsonProperty("hospitalClinic")
    private String hospitalClinic;

}
