package com.basicauth.domain;

import com.basicauth.data.OtherServiceJson;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by service.incuventure on 5/29/2017.
 */
@Entity
@Table(name = "mace_req_ip_otherservices")
public class MaceReqIpOtherservices implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "otherservice_id")
    private Integer otherserviceId;

    @Column(name = "macerequest_id")
    private Integer maceRequestId;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "service_hospamount", precision=10, scale=2)
    private BigDecimal serviceHospamount;

    @Column(name = "service_defamount", precision=10, scale=2)
    private BigDecimal serviceDefamount;

    @Column(name = "service_actualamount", precision=10, scale=2)
    private BigDecimal serviceActualamount;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "status")
    private Integer status;


    public MaceReqIpOtherservices() { }

    public MaceReqIpOtherservices(OtherServiceJson osj) {
        setServiceType(osj.getServiceType());
        setRemarks(osj.getRemarks());
        status(osj.getStatus()); // TODO: perform approval process for each services
        setServiceHospamount(new BigDecimal(osj.getAmount()));
    }

    public Integer getOtherserviceId() {
        return otherserviceId;
    }

    public MaceReqIpOtherservices otherserviceId(Integer otherserviceId) {
        this.otherserviceId = otherserviceId;
        return this;
    }

    public void setOtherserviceId(Integer otherserviceId) {
        this.otherserviceId = otherserviceId;
    }

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getServiceHospamount() {
        return serviceHospamount;
    }

    public MaceReqIpOtherservices serviceHospamount(BigDecimal serviceHospamount) {
        this.serviceHospamount = serviceHospamount;
        return this;
    }

    public void setServiceHospamount(BigDecimal serviceHospamount) {
        this.serviceHospamount = serviceHospamount;
    }

    public BigDecimal getServiceDefamount() {
        return serviceDefamount;
    }

    public MaceReqIpOtherservices serviceDefamount(BigDecimal serviceDefamount) {
        this.serviceDefamount = serviceDefamount;
        return this;
    }

    public void setServiceDefamount(BigDecimal serviceDefamount) {
        this.serviceDefamount = serviceDefamount;
    }

    public BigDecimal getServiceActualamount() {
        return serviceActualamount;
    }

    public MaceReqIpOtherservices serviceActualamount(BigDecimal serviceActualamount) {
        this.serviceActualamount = serviceActualamount;
        return this;
    }

    public void setServiceActualamount(BigDecimal serviceActualamount) {
        this.serviceActualamount = serviceActualamount;
    }

    public String getServiceType() {
        return serviceType;
    }

    public MaceReqIpOtherservices serviceType(String serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getRemarks() {
        return remarks;
    }

    public MaceReqIpOtherservices remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public MaceReqIpOtherservices status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}