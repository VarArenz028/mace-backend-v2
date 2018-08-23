package com.basicauth.data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IPC_Server on 5/9/2017.
 */
@Entity
@Table(name = "MACE_REQ_OP_DOCTOR")
public class MaceRequestOpRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OP_ROOM_ID")
    private int opReqRoomId;

    @Column(name = "MACEREQUEST_ID")
    private long maceRequestId;

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

    //<editor-fold desc="Getters and Setters">


    public int getOpReqRoomId() {
        return opReqRoomId;
    }

    public void setOpReqRoomId(int opReqRoomId) {
        this.opReqRoomId = opReqRoomId;
    }

    public long getMaceRequestId() {
        return maceRequestId;
    }

    public void setMaceRequestId(long maceRequestId) {
        this.maceRequestId = maceRequestId;
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


    //</editor-fold>

    public void setRoom(OutpatientRequestJson.RoomJson room, String hospitalCode) {
        setHospitalCode(hospitalCode);
        setRoomplan(room.getRoomNumber());
        setRoomtype(room.getRoomType());
        setRate(new BigDecimal(room.getRoomRate()));
        setDateFrom(room.getDateFrom());
        setDateTo(room.getDateTo());
        setIsRoomSharing(room.getIsRoomSharing());
    }
}
