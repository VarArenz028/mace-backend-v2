package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by macbookpro on 3/1/18.
 */
public class ResponseErJSON {

    private String reasonOfER;
    private String requestCode;
    private BigDecimal estimatedAmount;
    private String remarks;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT+8")
    private Date dateAdmitted;
    private String referenceNo;
    private int maceReqId;
    private String coordEmail;
    private String coordContact;
    private String memberContact;


    public String getCoordEmail() {
        return coordEmail;
    }

    public void setCoordEmail(String coordEmail) {
        this.coordEmail = coordEmail;
    }

    public String getCoordContact() {
        return coordContact;
    }

    public void setCoordContact(String coordContact) {
        this.coordContact = coordContact;
    }

    public String getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(String memberContact) {
        this.memberContact = memberContact;
    }

    public String getReasonOfER() {
        return reasonOfER;
    }

    public void setReasonOfER(String reasonOfER) {
        this.reasonOfER = reasonOfER;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public BigDecimal getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(BigDecimal estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(Date dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public int getMaceReqId() {
        return maceReqId;
    }

    public void setMaceReqId(int maceReqId) {
        this.maceReqId = maceReqId;
    }
}
