package com.basicauth.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */
@Entity
public class MaceReqErRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ER_ROOM_ID")
    private Integer erRoomId;

    @Column(name = "MACEREQUEST_ID")
    private Integer maceRequestId;

    @Column(name = "HOSPITAL_CODE")
    private String hospitalCode;

    @Column(name = "ROOMPLAN")
    private String roomPlan;

    @Column(name = "ROOMTYPE")
    private String roomType;

    @Column(name = "RATE", precision=18, scale=0)
    private BigDecimal rate;

    @Column(name = "DATE_FROM")
    private Date dateFrom;

    @Column(name = "DATE_TO")
    private Date dateTo;

    @Column(name = "ISROOMSHARING")
    private Boolean isRoomSharing;

    @Column(name = "ADDED_ON")
    private String addedOn;

    @Column(name = "ADDED_BY")
    private String addedBy;

    @Column(name = "LAST_UPDATE_ON")
    private Date lastUpdateOn;

    @Column(name = "LAST_UPDATE_BY")
    private String lastUpdateBy;

    public Integer getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(Integer maceRequestId) {
        this.maceRequestId = maceRequestId;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getRoomPlan() {
        return roomPlan;
    }

    public void setRoomPlan(String roomPlan) {
        this.roomPlan = roomPlan;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
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

    public Integer getErRoomId() {
        return erRoomId;
    }

    public void setErRoomId(Integer erRoomId) {
        this.erRoomId = erRoomId;
    }

}
