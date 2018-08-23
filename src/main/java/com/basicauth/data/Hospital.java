package com.basicauth.data;

import java.io.Serializable;

/**
 * Created by angulo on 11/16/2016.
 */
public class Hospital implements Serializable {


    private String hospitalCode;
    private String hospitalName;
    private String street;
    private String keyword;
    private String alias;
    private String category;
    private String coordinator;
    private String streetAddress;
    private boolean isAccredited;
    private boolean isMpiClinic;
    private String mpiClinicCategory;
    private String city;
    private String province;
    private String region;
    private String phoneNo;
    private String faxno;
    private String contactPerson;
    private boolean excludedForMember;

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFaxno() {
        return faxno;
    }

    public void setFaxno(String faxno) {
        this.faxno = faxno;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public boolean isAccredited() {
        return isAccredited;
    }

    public void setAccredited(boolean accredited) {
        isAccredited = accredited;
    }

    public boolean isMpiClinic() {
        return isMpiClinic;
    }

    public void setMpiClinic(boolean mpiClinic) {
        isMpiClinic = mpiClinic;
    }

    public String getMpiClinicCategory() {
        return mpiClinicCategory;
    }

    public void setMpiClinicCategory(String mpiClinicCategory) {
        this.mpiClinicCategory = mpiClinicCategory;
    }

    public boolean isExcludedForMember() {
        return excludedForMember;
    }

    public void setExcludedForMember(boolean excludedForMember) {
        this.excludedForMember = excludedForMember;
    }
}
