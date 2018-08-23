package com.basicauth.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by angulo on 11/16/2016.
 */
public class Doctor implements Serializable {

    private String doctorCode;
    private String docLname;
    private String docFname;
    private String docMname;
    private String specDesc;
    private String specCode;
    private String wtax;
    private Integer gracePeriod;
    private String vat;
    private String contactNumber;
    private String city;
    private String province;
    private String region;
    private String prc;
    private String streetAddress;
    private String roomNo;
    private String schedule;
    private ArrayList<Hospital> hospitals;

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDocLname() {
        return docLname;
    }

    public void setDocLname(String docLname) {
        this.docLname = docLname;
    }

    public String getDocFname() {
        return docFname;
    }

    public void setDocFname(String docFname) {
        this.docFname = docFname;
    }

    public String getDocMname() {
        return docMname;
    }

    public void setDocMname(String docMname) {
        this.docMname = docMname;
    }

    public String getSpecDesc() {
        return specDesc;
    }

    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc;
    }

    public String getWtax() {
        return wtax;
    }

    public void setWtax(String wtax) {
        this.wtax = wtax;
    }

    public Integer getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(Integer gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPrc() {
        return prc;
    }

    public void setPrc(String prc) {
        this.prc = prc;
    }


    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getFullName(){
        String fullName = this.getDocLname() == null ? "" : this.getDocLname() + ", ";
        fullName += this.getDocFname() == null ? "" : this.getDocFname() + " ";
        fullName += this.getDocMname() == null ? "" : this.getDocMname() + ".";
        return fullName;
    }
}
