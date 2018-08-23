package com.basicauth.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 *
 */
@Entity
public class MaceReqErOtherInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ER_OTHERINFORMATION_ID")
    private Integer erOtherInformationId;

    @Column(name = "MACEREQUEST_ID")
    private Integer maceRequestId;

    @Column(name = "INFORMATION_TYPE")
    private String informationType;

    @Column(name = "DETAILS")
    private String details;

    public Integer getErOtherInformationId() {
        return erOtherInformationId;
    }

    public void setErOtherInformationId(Integer erOtherInformationId) {
        this.erOtherInformationId = erOtherInformationId;
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
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

//    @OneToOne(optional = false)
//    @NotNull
//    @JoinColumn(unique = true)
//    private MaceReqEr maceReqEr;

}
