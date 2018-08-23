package com.basicauth.data;

import java.io.Serializable;

/**
 * Created by angulo on 11/16/2016.
 */
public class Specialization implements Serializable {

    private String specializationCode;
    private String specializationDescription;

    public Specialization() {
    }

    public String getSpecializationCode() {
        return specializationCode;
    }

    public void setSpecializationCode(String specializationCode) {
        this.specializationCode = specializationCode;
    }

    public String getSpecializationDescription() {
        return specializationDescription;
    }

    public void setSpecializationDescription(String specializationDescription) {
        this.specializationDescription = specializationDescription;
    }
}
