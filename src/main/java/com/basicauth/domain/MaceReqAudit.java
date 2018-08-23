package com.basicauth.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Created by service.incuventure on 5/29/2017.
 */
@Entity
@Table(name = "mace_req_audit")
public class MaceReqAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "logid")
    private Integer logid;

    @Column(name = "logdatetime")
    private ZonedDateTime logdatetime;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "userid")
    private String userid;

    @Column(name = "deviceid")
    private String deviceid;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "facility")
    private String facility;

    @Column(name = "function_name")
    private String functionName;

    @Column(name = "detail")
    private String detail;

    @Column(name = "transtype")
    private String transtype;

    @Column(name = "memberid")
    private String memberid;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLogid() {
        return logid;
    }

    public MaceReqAudit logid(Integer logid) {
        this.logid = logid;
        return this;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public ZonedDateTime getLogdatetime() {
        return logdatetime;
    }

    public MaceReqAudit logdatetime(ZonedDateTime logdatetime) {
        this.logdatetime = logdatetime;
        return this;
    }

    public void setLogdatetime(ZonedDateTime logdatetime) {
        this.logdatetime = logdatetime;
    }

    public String getHostname() {
        return hostname;
    }

    public MaceReqAudit hostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getUserid() {
        return userid;
    }

    public MaceReqAudit userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public MaceReqAudit deviceid(String deviceid) {
        this.deviceid = deviceid;
        return this;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getUserType() {
        return userType;
    }

    public MaceReqAudit userType(String userType) {
        this.userType = userType;
        return this;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFacility() {
        return facility;
    }

    public MaceReqAudit facility(String facility) {
        this.facility = facility;
        return this;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getFunctionName() {
        return functionName;
    }

    public MaceReqAudit functionName(String functionName) {
        this.functionName = functionName;
        return this;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getDetail() {
        return detail;
    }

    public MaceReqAudit detail(String detail) {
        this.detail = detail;
        return this;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTranstype() {
        return transtype;
    }

    public MaceReqAudit transtype(String transtype) {
        this.transtype = transtype;
        return this;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype;
    }

    public String getMemberid() {
        return memberid;
    }

    public MaceReqAudit memberid(String memberid) {
        this.memberid = memberid;
        return this;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getOldValue() {
        return oldValue;
    }

    public MaceReqAudit oldValue(String oldValue) {
        this.oldValue = oldValue;
        return this;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public MaceReqAudit newValue(String newValue) {
        this.newValue = newValue;
        return this;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MaceReqAudit maceReqAudit = (MaceReqAudit) o;
        if (maceReqAudit.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, maceReqAudit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MaceReqAudit{" +
                "id=" + id +
                ", logid='" + logid + "'" +
                ", logdatetime='" + logdatetime + "'" +
                ", hostname='" + hostname + "'" +
                ", userid='" + userid + "'" +
                ", deviceid='" + deviceid + "'" +
                ", userType='" + userType + "'" +
                ", facility='" + facility + "'" +
                ", functionName='" + functionName + "'" +
                ", detail='" + detail + "'" +
                ", transtype='" + transtype + "'" +
                ", memberid='" + memberid + "'" +
                ", oldValue='" + oldValue + "'" +
                ", newValue='" + newValue + "'" +
                '}';
    }
}
