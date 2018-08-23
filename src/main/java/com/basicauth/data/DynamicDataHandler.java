package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Jabito on 26/02/2018.
 */
public class DynamicDataHandler implements Serializable{
    @JsonProperty("id")
    public int id;
    @JsonProperty("description")
    public String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
