package com.basicauth.domain;

import java.util.Date;

/**
 * Created by angulo on 12/29/2016.
 */
public class Exclusion {
    private double id;
    private String accountCode;
    private String roomRateId;
    private String hospitalCode;
    private String updateBy;
    private Date updateDate;

    public Exclusion() {
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getRoomRateId() {
        return roomRateId;
    }

    public void setRoomRateId(String roomRateId) {
        this.roomRateId = roomRateId;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
