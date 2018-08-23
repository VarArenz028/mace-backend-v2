package com.basicauth.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IPC_Server on 5/11/2017.
 */
@Entity
@Table(name = "MACE_REQ_AUDIT")
public class MaceRequestAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOGID")
    private int logId;

    @Column(name = "LOGDATETIME")
    private Date logDateTime;

    @Column(name = "HOSTNAME")
    private String hostname;

    @Column(name = "USERID")
    private String userId;

    @Column(name = "DEVICEID")
    private String deviceId;

    @Column(name = "USER_TYPE")
    private String userType;

    @Column(name = "FACILITY")
    private String facility;

    @Column(name = "FUNCTION_NAME")
    private String functionName;

    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "TRANSTYPE")
    private String transtype;

    @Column(name = "MEMBERID")
    private String memberId;

    @Column(name = "REQUESTID")
    private String requestId;

    @Column(name = "OLD_VALUE")
    private String oldValue;

    @Column(name = "NEW_VALUE")
    private String newValue;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public Date getLogDateTime() {
        return logDateTime;
    }

    public void setLogDateTime(Date logDateTime) {
        this.logDateTime = logDateTime;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
