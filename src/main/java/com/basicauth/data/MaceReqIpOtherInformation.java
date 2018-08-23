package com.basicauth.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IPC_Server on 5/9/2017.
 */
@Entity
@Table(name = "MACE_REQ_IP_OTHERINFORMATION")
public class MaceReqIpOtherInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IP_OTHERINFORMATION_ID")
    private Integer ipOtherInformationId;

    @Column(name = "MACEREQUEST_ID")
    private long maceRequestId;

    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;

    @Column(name = "INFORMATION_TYPE")
    private String informationType;

    @Column(name = "DETAILS")
    private String details;

    //<editor-fold desc="Getters and Setters">


    public Integer getIpOtherInformationId() {
        return ipOtherInformationId;
    }

    public void setIpOtherInformationId(Integer ipOtherInformationId) {
        this.ipOtherInformationId = ipOtherInformationId;
    }

    public long getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(long maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getInformationType() {
        return informationType;
    }

    public void setInformationType(String informationType) {
        this.informationType = informationType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    //</editor-fold>
}
