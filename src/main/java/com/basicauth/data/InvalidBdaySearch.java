package com.basicauth.data;

import java.util.Date;

/**
 * Created by Jabito on 23/08/2017.
 */
public class InvalidBdaySearch {

    private String memCode;
    private String hospCode;
    private String hospName;
    private String coordinator;
    private String accountCode;
    private String accountName;
    private Date memBday;
    private Date bdayEntered;
    private Date logDate;

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public String getHospCode() {
        return hospCode;
    }

    public void setHospCode(String hospCode) {
        this.hospCode = hospCode;
    }

    public String getHospName() {
        return hospName;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
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

    public Date getMemBday() {
        return memBday;
    }

    public void setMemBday(Date memBday) {
        this.memBday = memBday;
    }

    public Date getBdayEntered() {
        return bdayEntered;
    }

    public void setBdayEntered(Date bdayEntered) {
        this.bdayEntered = bdayEntered;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}
