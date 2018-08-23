package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by angulo on 11/3/2016.
 */
@Entity
@Table(name = "MEM_ACCTS")
public class MemberAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "ID")
    private long id;

    @JsonProperty("userAcctId")
    @Column(name = "USER_ACCT_ID")
    private long userAcctId;

    @JsonProperty("memType")
    @Column(name = "MEM_TYPE")
    private int memType;

    @JsonProperty("memCode")
    @Column(name = "MEM_CODE")
    private String memCode;

    @JsonProperty("memLname")
    @Column(name = "MEM_LNAME")
    private String memLname;

    @JsonProperty("memFname")
    @Column(name = "MEM_FNAME")
    private String memFname;

    @JsonProperty("memMname")
    @Column(name = "MEM_MI")
    private String memMname;

    @Column(name = "MEM_BDAY")
    private Date memBday;

    @JsonProperty("memSex")
    @Column(name = "MEM_SEX")
    private int memSex;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserAcctId() {
        return userAcctId;
    }

    public void setUserAcctId(long userAcctId) {
        this.userAcctId = userAcctId;
    }

    public int getMemType() {
        return memType;
    }

    public void setMemType(int memType) {
        this.memType = memType;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public String getMemLname() {
        return memLname;
    }

    public void setMemLname(String memLname) {
        this.memLname = memLname;
    }

    public String getMemFname() {
        return memFname;
    }

    public void setMemFname(String memFname) {
        this.memFname = memFname;
    }

    public String getMemMname() {
        return memMname;
    }

    public void setMemMname(String memMname) {
        this.memMname = memMname;
    }

    public Date getMemBday() {
        return memBday;
    }

    public void setMemBday(Date memBday) {
        this.memBday = memBday;
    }

    public int getMemSex() {
        return memSex;
    }

    public void setMemSex(int memSex) {
        this.memSex = memSex;
    }


    @JsonProperty("memBday")
    public String getMemBdayString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            System.out.println("" + simpleDateFormat.format(memBday));
            return simpleDateFormat.format(memBday);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MemberAccount() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberAccount that = (MemberAccount) o;

        if (getId() != that.getId()) return false;
        if (getUserAcctId() != that.getUserAcctId()) return false;
        if (getMemType() != that.getMemType()) return false;
        if (getMemSex() != that.getMemSex()) return false;
        if (getMemCode() != null ? !getMemCode().equals(that.getMemCode()) : that.getMemCode() != null) return false;
        if (getMemLname() != null ? !getMemLname().equals(that.getMemLname()) : that.getMemLname() != null)
            return false;
        if (getMemFname() != null ? !getMemFname().equals(that.getMemFname()) : that.getMemFname() != null)
            return false;
        if (getMemMname() != null ? !getMemMname().equals(that.getMemMname()) : that.getMemMname() != null)
            return false;
        return getMemBday() != null ? getMemBday().equals(that.getMemBday()) : that.getMemBday() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (getUserAcctId() ^ (getUserAcctId() >>> 32));
        result = 31 * result + getMemType();
        result = 31 * result + (getMemCode() != null ? getMemCode().hashCode() : 0);
        result = 31 * result + (getMemLname() != null ? getMemLname().hashCode() : 0);
        result = 31 * result + (getMemFname() != null ? getMemFname().hashCode() : 0);
        result = 31 * result + (getMemMname() != null ? getMemMname().hashCode() : 0);
        result = 31 * result + (getMemBday() != null ? getMemBday().hashCode() : 0);
        result = 31 * result + getMemSex();
        return result;
    }
}
