package com.basicauth.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Giancarlo Angulo.
 */
public class MemberInfoJson implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("memberCode")
    private String memberCode;

    @JsonProperty("birthDate")
    private String birthDate;

    @JsonProperty("name")
    private String name;

    @JsonProperty("contactNumber")
    private String contactNumber;



    public MemberInfoJson() {
    }

    public MemberInfoJson(String username) {
        this.username = username;
    }

    public MemberInfoJson(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MemberInfoJson(String username, String password, String memberCode) {
        this.username = username;
        this.password = password;
        this.memberCode = memberCode;
    }


    public MemberInfoJson(String username, String password, String memberCode, String birthDate, String name, String contactNumber) {
        this.username = username;
        this.password = password;
        this.memberCode = memberCode;
        this.birthDate = birthDate;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberInfoJson userJson = (MemberInfoJson) o;

        if (!getUsername().equals(userJson.getUsername())) return false;
        if (!getPassword().equals(userJson.getPassword())) return false;
        if (!getMemberCode().equals(userJson.getMemberCode())) return false;
        if (!getBirthDate().equals(userJson.getBirthDate())) return false;
        if (!getName().equals(userJson.getName())) return false;
        return getContactNumber().equals(userJson.getContactNumber());

    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getMemberCode().hashCode();
        result = 31 * result + getBirthDate().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getContactNumber().hashCode();
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
