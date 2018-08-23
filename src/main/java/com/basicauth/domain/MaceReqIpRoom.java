package com.basicauth.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by service.incuventure on 5/29/2017.
 */
@Entity
@Table(name = "mace_req_ip_room")
public class MaceReqIpRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ip_room_id")
    private Integer ipRoomId;

    @Column(name = "MACEREQUEST_ID")
    private long maceRequestId;

    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;

    @Column(name = "HOSPITAL_CODE")
    private String hospitalCode;

    @Column(name = "ROOMPLAN")
    private String roomplan;

    @Column(name = "ROOMTYPE")
    private String roomtype;

    @Column(name = "RATE", precision=10, scale=2)
    private BigDecimal rate;

    @Column(name = "DATE_FROM")
    private Date dateFrom;

    @Column(name = "DATE_TO")
    private Date dateTo;

    @Column(name = "ISROOMSHARING")
    private Boolean isRoomSharing;

    @Column(name = "ADDED_ON")
    private Date addedOn;

    @Column(name = "ADDED_BY")
    private String addedBy;

    @Column(name = "LAST_UPDATE_ON")
    private Date lastUpdateOn;

    @Column(name = "LAST_UPDATE_BY")
    private String lastUpdateBy;

    @Column(name = "TRANS_CODE")
    private String transCode;

    //<editor-fold desc="Getters and Setters">

    public Integer getIpRoomId() {
        return ipRoomId;
    }

    public void setIpRoomId(Integer ipRoomId) {
        this.ipRoomId = ipRoomId;
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

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getRoomplan() {
        return roomplan;
    }

    public void setRoomplan(String roomplan) {
        this.roomplan = roomplan;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Boolean getIsRoomSharing() {
        return isRoomSharing;
    }

    public void setIsRoomSharing(Boolean isRoomSharing) {
        this.isRoomSharing = isRoomSharing;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Date getLastUpdateOn() {
        return lastUpdateOn;
    }

    public void setLastUpdateOn(Date lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Boolean getRoomSharing() {
        return isRoomSharing;
    }

    public void setRoomSharing(Boolean roomSharing) {
        isRoomSharing = roomSharing;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    //</editor-fold>

    public void setRoom(MaceInpatientPortalJson.IPRoom room, String hospitalCode) {
        setHospitalCode(hospitalCode);
        setRoomplan(room.getRoomNumber());
        setRoomtype(room.getRoomType());
        setRate(new BigDecimal(room.getRoomRate()));
        setDateFrom(room.getDateFrom());
        setDateTo(room.getDateTo());
        setIsRoomSharing(room.getIsRoomSharing());
    }
}
