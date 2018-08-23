package com.basicauth.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jabito on 11/04/2018.
 */
public class AccountNoticeType implements Serializable{

    private String noticeDesc;
    private String accountCode;
    private String accountName;
    private Date renewedEffective;
    private Date extensionDateFrom;
    private Date extensionDateTo;
    private String noticeInfo;
    private String extensionOptions;

    public String getNoticeDesc() {
        return noticeDesc;
    }

    public void setNoticeDesc(String noticeDesc) {
        this.noticeDesc = noticeDesc;
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

    public Date getRenewedEffective() {
        return renewedEffective;
    }

    public void setRenewedEffective(Date renewedEffective) {
        this.renewedEffective = renewedEffective;
    }

    public Date getExtensionDateFrom() {
        return extensionDateFrom;
    }

    public void setExtensionDateFrom(Date extensionDateFrom) {
        this.extensionDateFrom = extensionDateFrom;
    }

    public Date getExtensionDateTo() {
        return extensionDateTo;
    }

    public void setExtensionDateTo(Date extensionDateTo) {
        this.extensionDateTo = extensionDateTo;
    }

    public String getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(String noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public String getExtensionOptions() {
        return extensionOptions;
    }

    public void setExtensionOptions(String extensionOptions) {
        this.extensionOptions = extensionOptions;
    }
}
