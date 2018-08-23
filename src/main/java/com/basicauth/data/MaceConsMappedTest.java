package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by jjosephmagculang on 4/5/2018.
 */
public class MaceConsMappedTest  implements Serializable {

    @JsonProperty("diagDesc")
    private String diagDesc;

    @JsonProperty("procDesc")
    private String procDesc;

    @JsonProperty("costCenter")
    private String costCenter;

    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }
}
