package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by angulo on 11/4/2016.
 */
@Entity
@Table(name = "VERIFIED_MEMBER")
public class VerifiedMember implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("ID")
    @Column(name = "ID")
    private long id;

    @Column(name = "MEM_CODE")
    @JsonProperty("MEM_CODE")
    private String memCode;

    @Column(name = "IS_SKIPPED")
    @JsonProperty("IS_SKIPPED")
    private int isSkipped;

    @Column(name = "IS_VERIFIED")
    @JsonProperty("IS_VERIFIED")
    private int isVerified;

    @Column(name = "APP_USERNAME")
    @JsonProperty("APP_USERNAME")
    private String appUsername;


    @Column(name = "VERIFY_DATE")
    @JsonProperty("VERIFY_DATE")
    private Date verifyDate;

    @Column(name = "ID_TYPE")
    @JsonProperty("ID_TYPE")
    private String idType;

    @Column(name = "ID_NUMBER")
    @JsonProperty("ID_NUMBER")
    private String idNumber;

    @Column(name = "STATUS")
    @JsonProperty("STATUS")
    private String status;


    public VerifiedMember() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMemCode() {
        return memCode;
    }

    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    public int getIsSkipped() {
        return isSkipped;
    }

    public void setIsSkipped(int isSkipped) {
        this.isSkipped = isSkipped;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public String getAppUsername() {
        return appUsername;
    }

    public void setAppUsername(String appUsername) {
        this.appUsername = appUsername;
    }

    public Date getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerifiedMember that = (VerifiedMember) o;

        if (getId() != that.getId()) return false;
        if (getIsSkipped() != that.getIsSkipped()) return false;
        if (getIsVerified() != that.getIsVerified()) return false;
        if (getMemCode() != null ? !getMemCode().equals(that.getMemCode()) : that.getMemCode() != null) return false;
        if (getAppUsername() != null ? !getAppUsername().equals(that.getAppUsername()) : that.getAppUsername() != null)
            return false;
        if (getVerifyDate() != null ? !getVerifyDate().equals(that.getVerifyDate()) : that.getVerifyDate() != null)
            return false;
        if (getIdType() != null ? !getIdType().equals(that.getIdType()) : that.getIdType() != null) return false;
        if (getIdNumber() != null ? !getIdNumber().equals(that.getIdNumber()) : that.getIdNumber() != null)
            return false;
        return getStatus() != null ? getStatus().equals(that.getStatus()) : that.getStatus() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getMemCode() != null ? getMemCode().hashCode() : 0);
        result = 31 * result + getIsSkipped();
        result = 31 * result + getIsVerified();
        result = 31 * result + (getAppUsername() != null ? getAppUsername().hashCode() : 0);
        result = 31 * result + (getVerifyDate() != null ? getVerifyDate().hashCode() : 0);
        result = 31 * result + (getIdType() != null ? getIdType().hashCode() : 0);
        result = 31 * result + (getIdNumber() != null ? getIdNumber().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }
}
