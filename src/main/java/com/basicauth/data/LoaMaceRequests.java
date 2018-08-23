package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by service.incuventure on 4/18/2017.
 */
@Entity
@Table(name = "LOA_MACE_REQUESTS")
public class LoaMaceRequests implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;

    @JsonProperty("type")
    @Column(name = "type")
    private String type;

    @JsonProperty("requestedFrom")
    @Column(name="requested_from")
    private String requestedFrom;

    @JsonProperty("requestedBy")
    @Column(name="requested_by")
    private String requestedBy;

    @JsonProperty("requestedDate")
    @Column(name="requested_date")
    private Date requestedDate;

    @JsonProperty("memCode")
    @Column(name="mem_code")
    private String memCode;

    @JsonProperty("memLname")
    @Column(name="mem_lname")
    private String memLname;

    @JsonProperty("memFname")
    @Column(name="mem_fname")
    private String memFname;

    @JsonProperty("memMi")
    @Column(name="mem_mi")
    private String memMi;

    @JsonProperty("company")
    @Column(name="company")
    private String company;

    @JsonProperty("status")
    @Column(name="status")
    private String status;

    @JsonProperty("approvedBy")
    @Column(name="approved_by")
    private String approvedBy;

    @JsonProperty("approvalDate")
    @Column(name="approval_date")
    private String approvalDate;

    @JsonProperty("approvalRemarks")
    @Column(name="approval_remarks")
    private String approvalRemarks;

    @JsonProperty("disapprovedBy")
    @Column(name="disapproved_by")
    private String disapprovedBy;

    @JsonProperty("disapprovalDate")
    @Column(name="disapproval_date")
    private Date disapprovalDate;

    @JsonProperty("disapprovalRemarks")
    @Column(name="disapproval_remarks")
    private String disapprovalRemarks;

    @JsonProperty("approvalNo")
    @Column(name="approval_no")
    private String approvalNo;

    @JsonProperty("deviceId")
    @Column(name="device_id")
    private String deviceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestedFrom() {
        return requestedFrom;
    }

    public void setRequestedFrom(String requestedFrom) {
        this.requestedFrom = requestedFrom;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public String getMemLname() {
        return memLname;
    }

    public void setMemLname(String memLname) {
        this.memLname = memLname;
    }

    public String getMemFname() {
        return memFname;
    }

    public void setMemFname(String memFname) {
        this.memFname = memFname;
    }

    public String getMemMi() {
        return memMi;
    }

    public void setMemMi(String memMi) {
        this.memMi = memMi;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalRemarks() {
        return approvalRemarks;
    }

    public void setApprovalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks;
    }

    public String getDisapprovedBy() {
        return disapprovedBy;
    }

    public void setDisapprovedBy(String disapprovedBy) {
        this.disapprovedBy = disapprovedBy;
    }

    public Date getDisapprovalDate() {
        return disapprovalDate;
    }

    public void setDisapprovalDate(Date disapprovalDate) {
        this.disapprovalDate = disapprovalDate;
    }

    public String getDisapprovalRemarks() {
        return disapprovalRemarks;
    }

    public void setDisapprovalRemarks(String disapprovalRemarks) {
        this.disapprovalRemarks = disapprovalRemarks;
    }

    public String getApprovalNo() {
        return approvalNo;
    }

    public void setApprovalNo(String approvalNo) {
        this.approvalNo = approvalNo;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
