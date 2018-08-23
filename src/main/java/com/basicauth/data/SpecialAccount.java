package com.basicauth.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by IPC_Laptop056 on 9/27/2017.
 */
public class SpecialAccount implements Serializable {
    private double id;
    private String accountCode;
    private String statusAssignee;

    public SpecialAccount() {
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

    public String getStatusAssignee() {
        return statusAssignee;
    }

    public void setStatusAssignee(String statusAssignee) {
        this.statusAssignee = statusAssignee;
    }
}
