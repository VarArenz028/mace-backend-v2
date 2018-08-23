package com.basicauth.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jabito on 14/08/2017.
 */
public class CustHospProcAmount implements Serializable{

    private String hospitalCode;
    private BigDecimal procAmount;
    private String procCode;
    private String updatedBy;
    private Date updatedDate;
    private String rec_id;

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public BigDecimal getProcAmount() {
        return procAmount;
    }

    public void setProcAmount(BigDecimal procAmount) {
        this.procAmount = procAmount;
    }

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getRec_id() {
        return rec_id;
    }

    public void setRec_id(String rec_id) {
        this.rec_id = rec_id;
    }
}
