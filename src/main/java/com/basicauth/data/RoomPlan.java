package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jabito on 13/06/2017.
 */
public class RoomPlan implements Serializable {

    @JsonProperty("planCode")
    private String planCode;
    @JsonProperty("planDesc")
    private String planDesc;
    @JsonProperty("classCategory")
    private String classCategory;
    @JsonProperty("monthlyPremium")
    private Double monthlyPremium;
    @JsonProperty("quarterlyPremium")
    private Double quarterlyPremium;
    @JsonProperty("semiAnnualPremium")
    private Double semiAnnualPremium;
    @JsonProperty("annualPremium")
    private Double annualPremium;
    @JsonProperty("grpType")
    private Integer grpType;
    @JsonProperty("ddl")
    private String ddl;
    @JsonProperty("wHosp")
    private Boolean wHosp;
    @JsonProperty("updatedBy")
    private String updatedBy;
    @JsonProperty("updatedDate")
    private Date updatedDate;

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    public String getClassCategory() {
        return classCategory;
    }

    public void setClassCategory(String classCategory) {
        this.classCategory = classCategory;
    }

    public Double getMonthlyPremium() {
        return monthlyPremium;
    }

    public void setMonthlyPremium(Double monthlyPremium) {
        this.monthlyPremium = monthlyPremium;
    }

    public Double getQuarterlyPremium() {
        return quarterlyPremium;
    }

    public void setQuarterlyPremium(Double quarterlyPremium) {
        this.quarterlyPremium = quarterlyPremium;
    }

    public Double getSemiAnnualPremium() {
        return semiAnnualPremium;
    }

    public void setSemiAnnualPremium(Double semiAnnualPremium) {
        this.semiAnnualPremium = semiAnnualPremium;
    }

    public Double getAnnualPremium() {
        return annualPremium;
    }

    public void setAnnualPremium(Double annualPremium) {
        this.annualPremium = annualPremium;
    }

    public Integer getGrpType() {
        return grpType;
    }

    public void setGrpType(Integer grpType) {
        this.grpType = grpType;
    }

    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public Boolean getwHosp() {
        return wHosp;
    }

    public void setwHosp(Boolean wHosp) {
        this.wHosp = wHosp;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
