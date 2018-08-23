package com.basicauth.data;

import com.basicauth.domain.MaceReqConsult;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Jabito on 30/08/2017.
 */
public class AvailedConsultObject implements Serializable{

    @JsonProperty("maceRequest")
    private MaceRequest maceRequest;
    @JsonProperty("maceReqConsult")
    private MaceReqConsult maceReqConsult;

    public MaceRequest getMaceRequest() {
        return maceRequest;
    }

    public void setMaceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
    }

    public MaceReqConsult getMaceReqConsult() {
        return maceReqConsult;
    }

    public void setMaceReqConsult(MaceReqConsult maceReqConsult) {
        this.maceReqConsult = maceReqConsult;
    }
}
