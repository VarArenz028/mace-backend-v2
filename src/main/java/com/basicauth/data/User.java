package com.basicauth.data;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Giancarlo Angulo.
 */

@Entity
@Table(name = "MEM_USERACCT")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "memCode")
    private String memberCode;

    @Column(name = "memLname")
    private String lname;

    @Column(name = "memFname")
    private String fname;

    @Column(name = "memMi")
    private String mi;

    @Column(name = "memBday")
    private Date birthDate;

    @Column(name = "memSex")
    private int sex;

    @Column(name = "dateRegistered")
    private Date dateRegistered;

    @Column(name = "regDevice")
    private String registerDevice;

    @Column(name = "phone")
    private String phoneNo;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;



    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User(String username, String password, String memberCode, String lname, String fname, String mi, Date birthDate, int sex, Date dateRegistered, String registerDevice, String phoneNo, String email, String status) {
        this.username = username;
        this.password = password;
        this.memberCode = memberCode;
        this.lname = lname;
        this.fname = fname;
        this.mi = mi;
        this.birthDate = birthDate;
        this.sex = sex;
        this.dateRegistered = dateRegistered;
        this.registerDevice = registerDevice;
        this.phoneNo = phoneNo;
        this.email = email;
        this.status = status;
    }

    public User(String username, String password, String memberCode, String lname, String fname, String mi, String birthDate, int sex, Date dateRegistered, String registerDevice, String phoneNo, String email, String status) {
        this.username = username;
        this.password = password;
        this.memberCode = memberCode;
        this.lname = lname;
        this.fname = fname;
        this.mi = mi;
        SimpleDateFormat simpleDateFormat = new  SimpleDateFormat("yyyyMMdd");
        try {
            System.out.println(""+simpleDateFormat.parse(birthDate));
            this.birthDate = simpleDateFormat.parse(birthDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.sex = sex;
        this.dateRegistered = dateRegistered;
        this.registerDevice = registerDevice;
        this.phoneNo = phoneNo;
        this.email = email;
        this.status = status;
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

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {

        this.birthDate = birthDate;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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

    public String getBirthDateString() {
        SimpleDateFormat simpleDateFormat = new  SimpleDateFormat("yyyyMMdd");
        try {
            System.out.println(""+simpleDateFormat.format(birthDate));
            return simpleDateFormat.format(birthDate);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getSex() != user.getSex()) return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getMemberCode() != null ? !getMemberCode().equals(user.getMemberCode()) : user.getMemberCode() != null)
            return false;
        if (getLname() != null ? !getLname().equals(user.getLname()) : user.getLname() != null) return false;
        if (getFname() != null ? !getFname().equals(user.getFname()) : user.getFname() != null) return false;
        if (getMi() != null ? !getMi().equals(user.getMi()) : user.getMi() != null) return false;
        if (getBirthDate() != null ? !getBirthDate().equals(user.getBirthDate()) : user.getBirthDate() != null)
            return false;
        if (getDateRegistered() != null ? !getDateRegistered().equals(user.getDateRegistered()) : user.getDateRegistered() != null)
            return false;
        if (getRegisterDevice() != null ? !getRegisterDevice().equals(user.getRegisterDevice()) : user.getRegisterDevice() != null)
            return false;
        if (getPhoneNo() != null ? !getPhoneNo().equals(user.getPhoneNo()) : user.getPhoneNo() != null) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        return getStatus() != null ? getStatus().equals(user.getStatus()) : user.getStatus() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getMemberCode() != null ? getMemberCode().hashCode() : 0);
        result = 31 * result + (getLname() != null ? getLname().hashCode() : 0);
        result = 31 * result + (getFname() != null ? getFname().hashCode() : 0);
        result = 31 * result + (getMi() != null ? getMi().hashCode() : 0);
        result = 31 * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
        result = 31 * result + getSex();
        result = 31 * result + (getDateRegistered() != null ? getDateRegistered().hashCode() : 0);
        result = 31 * result + (getRegisterDevice() != null ? getRegisterDevice().hashCode() : 0);
        result = 31 * result + (getPhoneNo() != null ? getPhoneNo().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }
}
