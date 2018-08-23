package com.basicauth.data;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by angulo on 11/3/2016.
 */
public class MemberDetails implements Serializable{


    private String Mem_OStat_Code;
    private String VAL_DATE;
    private String Plan_Desc;
    private String MEM_TYPE;
    private String MEM_NAME;
    private String MEM_FNAME;
    private String MEM_LNAME;
    private String ID_REM;
    private String ID_REM2;
    private String ID_REM3;
    private String ID_REM4;
    private String ID_REM5;
    private String ID_REM6;
    private String ID_REM7;
    private String OTHER_REM;
    private String CIVIL_STATUS;
    private String ACCOUNT_NAME;
    private BigDecimal DD_Reg_Limits;
    private String PRIN_CODE;
    private Integer MEM_SEX;
    private String EFF_DATE;
    private Integer AGE;
    private BigDecimal ERC_Limits;
    private String BDAY;
    private String ACCT_TYPE;
    private String ACCOUNT_CODE;
    private Integer RSPROOMRATE_ID;

    public MemberDetails() {
    }

    public String getMem_OStat_Code() {
        return Mem_OStat_Code;
    }

    public void setMem_OStat_Code(String mem_OStat_Code) {
        Mem_OStat_Code = mem_OStat_Code;
    }

    public String getVAL_DATE() {
        return VAL_DATE;
    }

    public void setVAL_DATE(String VAL_DATE) {
        this.VAL_DATE = VAL_DATE;
    }

    public String getPlan_Desc() {
        return Plan_Desc;
    }

    public void setPlan_Desc(String plan_Desc) {
        Plan_Desc = plan_Desc;
    }

    public String getMEM_TYPE() {
        return MEM_TYPE;
    }

    public void setMEM_TYPE(String MEM_TYPE) {
        this.MEM_TYPE = MEM_TYPE;
    }

    public String getMEM_NAME() {
        return MEM_NAME;
    }

    public void setMEM_NAME(String MEM_NAME) {
        this.MEM_NAME = MEM_NAME;
    }

    public String getMEM_FNAME() {
        return MEM_FNAME;
    }

    public void setMEM_FNAME(String MEM_FNAME) {
        this.MEM_FNAME = MEM_FNAME;
    }

    public String getMEM_LNAME() {
        return MEM_LNAME;
    }

    public void setMEM_LNAME(String MEM_LNAME) {
        this.MEM_LNAME = MEM_LNAME;
    }

    public String getID_REM() {
        return ID_REM;
    }

    public void setID_REM(String ID_REM) {
        this.ID_REM = ID_REM;
    }

    public String getID_REM2() {
        return ID_REM2;
    }

    public void setID_REM2(String ID_REM2) {
        this.ID_REM2 = ID_REM2;
    }

    public String getID_REM3() {
        return ID_REM3;
    }

    public void setID_REM3(String ID_REM3) {
        this.ID_REM3 = ID_REM3;
    }

    public String getID_REM4() {
        return ID_REM4;
    }

    public void setID_REM4(String ID_REM4) {
        this.ID_REM4 = ID_REM4;
    }

    public String getID_REM5() {
        return ID_REM5;
    }

    public void setID_REM5(String ID_REM5) {
        this.ID_REM5 = ID_REM5;
    }

    public String getID_REM6() {
        return ID_REM6;
    }

    public void setID_REM6(String ID_REM6) {
        this.ID_REM6 = ID_REM6;
    }

    public String getID_REM7() {
        return ID_REM7;
    }

    public void setID_REM7(String ID_REM7) {
        this.ID_REM7 = ID_REM7;
    }

    public String getOTHER_REM() {
        return OTHER_REM;
    }

    public void setOTHER_REM(String OTHER_REM) {
        this.OTHER_REM = OTHER_REM;
    }

    public String getCIVIL_STATUS() {
        return CIVIL_STATUS;
    }

    public void setCIVIL_STATUS(String CIVIL_STATUS) {
        this.CIVIL_STATUS = CIVIL_STATUS;
    }

    public String getACCOUNT_NAME() {
        return ACCOUNT_NAME;
    }

    public void setACCOUNT_NAME(String ACCOUNT_NAME) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
    }

    public BigDecimal getDD_Reg_Limits() {
        return DD_Reg_Limits;
    }

    public void setDD_Reg_Limits(BigDecimal DD_Reg_Limits) {
        this.DD_Reg_Limits = DD_Reg_Limits;
    }

    public String getPRIN_CODE() {
        return PRIN_CODE;
    }

    public void setPRIN_CODE(String PRIN_CODE) {
        this.PRIN_CODE = PRIN_CODE;
    }

    public Integer getMEM_SEX() {
        return MEM_SEX;
    }

    public void setMEM_SEX(Integer MEM_SEX) {
        this.MEM_SEX = MEM_SEX;
    }

    public String getEFF_DATE() {
        return EFF_DATE;
    }

    public void setEFF_DATE(String EFF_DATE) {
        this.EFF_DATE = EFF_DATE;
    }

    public Integer getAGE() {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("MMM dd, yyyy");
            LocalDate date1 = new LocalDate(sf.parse(BDAY.toUpperCase()));
            LocalDate now = new LocalDate();
            Years age = Years.yearsBetween(date1, now);
            return age.getYears();
        }catch (Exception e){ e.printStackTrace(); }
        return AGE;
    }

    public void setAGE(Integer AGE) {
        this.AGE = AGE;
    }

    public BigDecimal getERC_Limits() {
        return ERC_Limits;
    }

    public void setERC_Limits(BigDecimal ERC_Limits) {
        this.ERC_Limits = ERC_Limits;
    }

    public String getBDAY() {
        return BDAY;
    }

    public void setBDAY(String BDAY) {
        this.BDAY = BDAY;
    }

    public String getACCT_TYPE() {
        return ACCT_TYPE;
    }

    public void setACCT_TYPE(String ACCT_TYPE) {
        this.ACCT_TYPE = ACCT_TYPE;
    }

    public String getACCOUNT_CODE() {
        return ACCOUNT_CODE;
    }

    public void setACCOUNT_CODE(String ACCOUNT_CODE) {
        this.ACCOUNT_CODE = ACCOUNT_CODE;
    }

    public Integer getRSPROOMRATE_ID() {
        return RSPROOMRATE_ID;
    }

    public void setRSPROOMRATE_ID(Integer RSPROOMRATE_ID) {
        this.RSPROOMRATE_ID = RSPROOMRATE_ID;
    }
}
