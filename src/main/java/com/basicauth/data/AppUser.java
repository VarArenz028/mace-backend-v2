package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by angulo on 10/31/2016.
 */

@Entity
@Table(name = "APPUSER")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("APPUSER_ID")
    @Column(name = "APPUSER_ID")
    private long id;
    @Column(name = "APP_USERNAME", unique = true)
    @JsonProperty("APP_USERNAME")
    private String username;
    @Column(name = "APP_PASSWORD")
    @JsonProperty("APP_PASSWORD")
    private String password;
    @Column(name = "CNTCT_LNAME")
    @JsonProperty("CNTCT_LNAME")
    private String lname;
    @Column(name = "CNTCT_FNAME")
    @JsonProperty("CNTCT_FNAME")
    private String fname;
    @Column(name = "CNTCT_MNAME")
    @JsonProperty("CNTCT_MNAME")
    private String mname;
    @Column(name = "CNTCT_EMAIL")
    @JsonProperty("CNTCT_EMAIL")
    private String email;
    @Column(name = "CNTCT_PHONENO")
    @JsonProperty("CNTCT_PHONENO")
    private String phoneno;
    @Column(name = "HOSPITAL")
    @JsonProperty("HOSPITAL")
    private String hospital;
    @Column(name = "ROLE")
    @JsonProperty("ROLE")
    private String role;
    @Column(name = "LOCATION")
    @JsonProperty("LOCATION")
    private String location;
    @Column(name = "COSTCENTER")
    @JsonProperty("COSTCENTER")
    private String costcenter;
    @Column(name = "STATUS")
    @JsonProperty("STATUS")
    private String status;

    @Column(name = "DOCTOR_CODE")
    @JsonProperty("DOCTOR_CODE")
    private String doctorCode;

    @Column(name = "DENTIST_CODE")
    private String dentistCode;

    @Column(name = "FORCE_CHANGE_PASSWORD")
    @JsonProperty("FORCE_CHANGE_PASSWORD")
    private Integer forceChangePassword;


    public AppUser() {
    }

    public AppUser(RegisterProviderJson providerJson) {
        this.username = providerJson.getUsername();
        this.lname = providerJson.getLname();
        this.fname = providerJson.getFname();
        this.mname = providerJson.getMname();
        this.email = providerJson.getEmail();
        this.phoneno = providerJson.getPhoneno();
        this.hospital = providerJson.getHospital();
        this.role = providerJson.getRole();
        this.doctorCode = providerJson.getDoctorCode();
        this.status = "ACTIVE";
        this.forceChangePassword = 0;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCostcenter() {
        return costcenter;
    }

    public void setCostcenter(String costcenter) {
        this.costcenter = costcenter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getDentistCode() {
        return dentistCode;
    }

    public void setDentistCode(String dentistCode) {
        this.dentistCode = dentistCode;
    }

    public Integer getForceChangePassword() {
        return forceChangePassword;
    }

    public void setForceChangePassword(Integer forceChangePassword) {
        this.forceChangePassword = forceChangePassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppUser appUser = (AppUser) o;

        if (getId() != appUser.getId()) return false;
        if (getUsername() != null ? !getUsername().equals(appUser.getUsername()) : appUser.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(appUser.getPassword()) : appUser.getPassword() != null)
            return false;
        if (getLname() != null ? !getLname().equals(appUser.getLname()) : appUser.getLname() != null) return false;
        if (getFname() != null ? !getFname().equals(appUser.getFname()) : appUser.getFname() != null) return false;
        if (getMname() != null ? !getMname().equals(appUser.getMname()) : appUser.getMname() != null) return false;
        if (getEmail() != null ? !getEmail().equals(appUser.getEmail()) : appUser.getEmail() != null) return false;
        if (getPhoneno() != null ? !getPhoneno().equals(appUser.getPhoneno()) : appUser.getPhoneno() != null)
            return false;
        if (getHospital() != null ? !getHospital().equals(appUser.getHospital()) : appUser.getHospital() != null)
            return false;
        if (getRole() != null ? !getRole().equals(appUser.getRole()) : appUser.getRole() != null) return false;
        if (getLocation() != null ? !getLocation().equals(appUser.getLocation()) : appUser.getLocation() != null)
            return false;
        if (getCostcenter() != null ? !getCostcenter().equals(appUser.getCostcenter()) : appUser.getCostcenter() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(appUser.getStatus()) : appUser.getStatus() != null) return false;
        if (getDentistCode() != null ? !getDentistCode().equals(appUser.getDentistCode()) : appUser.getDentistCode() != null)
            return false;
        return getDoctorCode() != null ? getDoctorCode().equals(appUser.getDoctorCode()) : appUser.getDoctorCode() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getLname() != null ? getLname().hashCode() : 0);
        result = 31 * result + (getFname() != null ? getFname().hashCode() : 0);
        result = 31 * result + (getMname() != null ? getMname().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPhoneno() != null ? getPhoneno().hashCode() : 0);
        result = 31 * result + (getHospital() != null ? getHospital().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        result = 31 * result + (getCostcenter() != null ? getCostcenter().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getDoctorCode() != null ? getDoctorCode().hashCode() : 0);
        result = 31 * result + (getDentistCode() != null ? getDentistCode().hashCode() : 0);
        return result;
    }
}
