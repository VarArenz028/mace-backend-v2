package com.basicauth.data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by IPC_Server on 5/9/2017.
 */
@Entity
@Table(name = "MACE_REQ_OP_OTHERINFORMATION")
public class MaceRequestOpOtherInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OP_OTHERINFORMATION_ID")
    private int opOtherInformationId;

    @Column(name = "MACEREQUEST_ID")
    private long maceRequestId;

    @Column(name = "INFORMATION_TYPE")
    private String informationType;

    @Column(name = "DETAILS")
    private String details;

    //<editor-fold desc="Getters and Setters">

    public int getOpOtherInformationId() {
        return opOtherInformationId;
    }

    public void setOpOtherInformationId(int opOtherInformationId) {
        this.opOtherInformationId = opOtherInformationId;
    }

    public long getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(long maceRequestId) {
        this.maceRequestId = maceRequestId;
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
