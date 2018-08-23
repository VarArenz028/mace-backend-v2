package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jabito on 04/08/2017.
 */
public class LockMember implements Serializable{

    @JsonProperty("memberCode")
    private String memberCode;
    @JsonProperty("hospitalCode")
    private String hospitalCode;
    @JsonProperty("dateTimeBlocked")
    private Date dateTimeBlocked;
    @JsonProperty("hospitalName")
    private String hospitalName;
    @JsonProperty("bdayActual")
    private Date bdayActual;
    @JsonProperty("bdayEntered")
    private Date bdayEntered;
    private String accountCode;
    private String accountName;

    public LockMember(InvalidBdaySearch ibs) {
        this.memberCode = ibs.getMemCode();
        this.hospitalCode = ibs.getHospCode();
        this.hospitalName = ibs.getHospName();
        this.bdayActual = ibs.getMemBday();
        this.bdayEntered = ibs.getBdayEntered();
        this.accountCode = ibs.getAccountCode();
        this.accountName = ibs.getAccountName();
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }


    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Date getDateTimeBlocked() {
        return dateTimeBlocked;
    }

    public void setDateTimeBlocked(Date dateTimeBlocked) {
        this.dateTimeBlocked = dateTimeBlocked;
    }

    public Date getBdayActual() {
        return bdayActual;
    }

    public void setBdayActual(Date bdayActual) {
        this.bdayActual = bdayActual;
    }

    public Date getBdayEntered() {
        return bdayEntered;
    }

    public void setBdayEntered(Date bdayEntered) {
        this.bdayEntered = bdayEntered;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
