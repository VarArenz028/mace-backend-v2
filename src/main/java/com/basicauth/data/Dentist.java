package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jabito on 28/07/2017.
 */
public class Dentist implements Serializable{

    @JsonProperty("dentistCode")
    private String dentistCode;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("middleName")
    private String middleName;
    @JsonProperty("dentistAddress")
    private String dentistAddress;
    @JsonProperty("contactNo")
    private String contactNo;
    @JsonProperty("schedule")
    private String schedule;
    @JsonProperty("clinic")
    private String clinic;
    @JsonProperty("provinceCode")
    private String provinceCode;
    @JsonProperty("regionCode")
    private String regionCode;
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty("faxNo")
    private String faxNo;
    @JsonProperty("oldCode")
    private String oldCode;
    @JsonProperty("gracePeriod")
    private Integer gracePeriod;
    @JsonProperty("effDate")
    private Date effDate;
    @JsonProperty("isAccredited")
    private String isAccredited;
    @JsonProperty("effDateRa")
    private Date effDateRa;
    @JsonProperty("effDateNap")
    private Date effDateNap;
    @JsonProperty("vat")
    private String vat;
    @JsonProperty("tinNo")
    private String tinNo;
    @JsonProperty("taxable")
    private Integer taxable;
    @JsonProperty("wTax")
    private String wTax;
    @JsonProperty("specialRem")
    private String specialRem;
    @JsonProperty("email")
    private String email;
    @JsonProperty("otherSpecialty")
    private String otherSpecialty;
    @JsonProperty("remarks")
    private String remarks;
    @JsonProperty("createdDate")
    private Date createdDate;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("updatedDate")
    private Date updatedDate;
    @JsonProperty("updatedBy")
    private String updatedBy;
    @JsonProperty("withPRC")
    private Boolean withPRC;
    @JsonProperty("withDiploma")
    private Boolean withDiploma;
    @JsonProperty("withPermit")
    private Boolean withPermit;
    @JsonProperty("oldDentistCode")
    private String oldDentistCode;


    public String getDentistCode() {
        return dentistCode;
    }

    public void setDentistCode(String dentistCode) {
        this.dentistCode = dentistCode;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getDentistAddress() {
        return dentistAddress;
    }

    public void setDentistAddress(String dentistAddress) {
        this.dentistAddress = dentistAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }

    public Integer getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(Integer gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public String getIsAccredited() {
        return isAccredited;
    }

    public void setIsAccredited(String isAccredited) {
        this.isAccredited = isAccredited;
    }

    public Date getEffDateRa() {
        return effDateRa;
    }

    public void setEffDateRa(Date effDateRa) {
        this.effDateRa = effDateRa;
    }

    public Date getEffDateNap() {
        return effDateNap;
    }

    public void setEffDateNap(Date effDateNap) {
        this.effDateNap = effDateNap;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public Integer getTaxable() {
        return taxable;
    }

    public void setTaxable(Integer taxable) {
        this.taxable = taxable;
    }

    public String getwTax() {
        return wTax;
    }

    public void setwTax(String wTax) {
        this.wTax = wTax;
    }

    public String getSpecialRem() {
        return specialRem;
    }

    public void setSpecialRem(String specialRem) {
        this.specialRem = specialRem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtherSpecialty() {
        return otherSpecialty;
    }

    public void setOtherSpecialty(String otherSpecialty) {
        this.otherSpecialty = otherSpecialty;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getWithPRC() {
        return withPRC;
    }

    public void setWithPRC(Boolean withPRC) {
        this.withPRC = withPRC;
    }

    public Boolean getWithDiploma() {
        return withDiploma;
    }

    public void setWithDiploma(Boolean withDiploma) {
        this.withDiploma = withDiploma;
    }

    public Boolean getWithPermit() {
        return withPermit;
    }

    public void setWithPermit(Boolean withPermit) {
        this.withPermit = withPermit;
    }

    public String getOldDentistCode() {
        return oldDentistCode;
    }

    public void setOldDentistCode(String oldDentistCode) {
        this.oldDentistCode = oldDentistCode;
    }
}
