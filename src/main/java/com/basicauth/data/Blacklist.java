package com.basicauth.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by angulo on 12/8/2016.
 */
public class Blacklist implements Serializable {
    private double id;
    private String companyCode;
    private String service;
    private Date asOfDate;

    public Blacklist() {
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Date asOfDate) {
        this.asOfDate = asOfDate;
    }
}
