package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Jabito on 31/01/2018.
 */
public class TempDoctorModel {
    private String tempDocCode;
    private String doctorName;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT +8")
    private Date createdOn;

    public String getTempDocCode() {
        return tempDocCode;
    }

    public void setTempDocCode(String tempDocCode) {
        this.tempDocCode = tempDocCode;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
