package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Jabito on 11/10/2017.
 */
public class LastUpdatesCoor {

    @JsonProperty("listType")
    private String listType;
    @JsonProperty("lastUpdateDate")
    private String lastUpdateDate;

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
