package com.basicauth.data;

/**
 * Created by IPC_Server on 8/1/2017.
 */
public class DischargeErResponse {

    private String dateAdmitted;

    private String erReason;

    private String hospitalCode;

    private String id;

    private String memberCode;

    private String memberName;

    private String username;

    public String getDateAdmitted() {
        return dateAdmitted;
    }

    public void setDateAdmitted(String dateAdmitted) {
        this.dateAdmitted = dateAdmitted;
    }

    public String getErReason() {
        return erReason;
    }

    public void setErReason(String erReason) {
        this.erReason = erReason;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
