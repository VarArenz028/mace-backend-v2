package com.basicauth.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Giancarlo Angulo.
 */
public class UserJson implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("memBday")
    private String birthDate;

    @JsonProperty("memLname")
    private String lname;

    @JsonProperty("memFname")
    private String fname;

    @JsonProperty("memMi")
    private String mi;

    @JsonProperty("memSex")
    private String sex;

    @JsonProperty("dateRegistered")
    private Date dateRegistered;

    @JsonProperty("registerDevice")
    private String registerDevice;

    @JsonProperty("phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;







    public UserJson() {
    }

    public UserJson(String username) {
        this.username = username;
    }

    public UserJson(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserJson(String username, String password, String memberCode) {
        this.username = username;
        this.password = password;
        this.memberCode = memberCode;
    }

    public UserJson(String username, String password, String memberCode, String birthDate, String lname, String fname, String mi, int sex, String registerDevice, String phone, String email) {
        this.username = username;
        this.password = password;
        this.memberCode = memberCode;
        this.birthDate = birthDate;
        this.lname = lname;
        this.fname = fname;
        this.mi = mi;
        if(sex ==0 ){
            this.sex = "F";
        } else {
            this.sex = "M";
        }
        this.registerDevice = registerDevice;
        this.phone = phone;
        this.email = email;
    }


    public UserJson(String username, String password, String memberCode, String birthDate, String lname, String fname, String mi, int sex, String registerDevice, String phone, String email, Date dateRegistered, String status) {
        this.username = username;
        this.password = password;
        this.memberCode = memberCode;
        this.birthDate = birthDate;
        this.lname = lname;
        this.fname = fname;
        this.mi = mi;
        if(sex ==0 ){
            this.sex = "F";
        } else {
            this.sex = "M";
        }

        this.registerDevice = registerDevice;
        this.dateRegistered = dateRegistered;
        this.phone = phone;
        this.email = email;
        this.status = status;
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

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public String getMi() {
        return mi;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public String getSex() {
        return sex;
    }

    public int getSexInt() {
        if("".equalsIgnoreCase(sex)){
            return 0;
        } else {
            return 1;
        }

    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getRegisterDevice() {
        return registerDevice;
    }

    public void setRegisterDevice(String registerDevice) {
        this.registerDevice = registerDevice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserJson userJson = (UserJson) o;

        if (!getUsername().equals(userJson.getUsername())) return false;
        if (getPassword() != null ? !getPassword().equals(userJson.getPassword()) : userJson.getPassword() != null)
            return false;
        if (!getMemberCode().equals(userJson.getMemberCode())) return false;
        if (!getBirthDate().equals(userJson.getBirthDate())) return false;
        if (!getLname().equals(userJson.getLname())) return false;
        if (!getFname().equals(userJson.getFname())) return false;
        if (!getMi().equals(userJson.getMi())) return false;
        if (!getSex().equals(userJson.getSex())) return false;
        if (getDateRegistered() != null ? !getDateRegistered().equals(userJson.getDateRegistered()) : userJson.getDateRegistered() != null)
            return false;
        if (getRegisterDevice() != null ? !getRegisterDevice().equals(userJson.getRegisterDevice()) : userJson.getRegisterDevice() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(userJson.getPhone()) : userJson.getPhone() != null) return false;
        if (getEmail() != null ? !getEmail().equals(userJson.getEmail()) : userJson.getEmail() != null) return false;
        return !(getStatus() != null ? !getStatus().equals(userJson.getStatus()) : userJson.getStatus() != null);

    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + getMemberCode().hashCode();
        result = 31 * result + getBirthDate().hashCode();
        result = 31 * result + getLname().hashCode();
        result = 31 * result + getFname().hashCode();
        result = 31 * result + getMi().hashCode();
        result = 31 * result + getSex().hashCode();
        result = 31 * result + (getDateRegistered() != null ? getDateRegistered().hashCode() : 0);
        result = 31 * result + (getRegisterDevice() != null ? getRegisterDevice().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserJson userJson = (UserJson) o;
//        return Objects.equals(getUsername(), userJson.getUsername()) && Objects.equals(getMemberCode(), userJson.getMemberCode());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getUsername());
//    }
}
