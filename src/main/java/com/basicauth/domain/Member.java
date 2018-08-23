package com.basicauth.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by igan_long on 10/6/2016.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class Member implements Serializable {

    private String code;
    private String lastname;
    private String firstname;
    private String mi;
    private Integer memberType;
    private String companyCode;
    private String companyDesc;
    private Date birthDate;
    private Integer gender;
    private String roomRateId;
    private String planCode;
    private String planDesc;
    private Date effectDate;
    private Date validDate;
    private String statRemarks;
    private String otherRemarks;
    private String remarks1;
    private String remarks2;
    private String remarks3;
    private String remarks4;
    private String remarks5;
    private String remarks6;
    private String remarks7;
    private Date updateDate;
    private Integer isPrincipal;

    public Member() {
        code = "";
        lastname = "";
        firstname = "";
        mi = "";
        memberType = 0;
        companyCode = "";
        birthDate = null;
        gender = 0;
        roomRateId = "";
        planCode = "";
        effectDate = null;
        validDate = null;
        statRemarks = "";
        otherRemarks = "";
        remarks1 = null;
        remarks2 = null;
        remarks3 = null;
        remarks4 = null;
        remarks5 = null;
        remarks6 = null;
        remarks7 = null;
    }


    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public String getMi() {
        return mi;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public String getOtherRemarks() {
        return otherRemarks;
    }

    public void setOtherRemarks(String otherRemarks) {
        this.otherRemarks = otherRemarks;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }

    public String getRemarks2() {
        return remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }

    public String getRemarks3() {
        return remarks3;
    }

    public void setRemarks3(String remarks3) {
        this.remarks3 = remarks3;
    }

    public String getRemarks4() {
        return remarks4;
    }

    public void setRemarks4(String remarks4) {
        this.remarks4 = remarks4;
    }

    public String getRemarks5() {
        return remarks5;
    }

    public void setRemarks5(String remarks5) {
        this.remarks5 = remarks5;
    }

    public String getRemarks6() {
        return remarks6;
    }

    public void setRemarks6(String remarks6) {
        this.remarks6 = remarks6;
    }

    public String getRemarks7() {
        return remarks7;
    }

    public void setRemarks7(String remarks7) {
        this.remarks7 = remarks7;
    }

    public String getRoomRateId() {
        return roomRateId;
    }

    public void setRoomRateId(String roomRateId) {
        this.roomRateId = roomRateId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getStatRemarks() {
        return statRemarks;
    }

    public void setStatRemarks(String statRemarks) {
        this.statRemarks = statRemarks;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

//    public void fromCSVForInfo(String s) {
//        CsvReader reader = CsvReader.parse(s);
//        try {
//            reader.setDelimiter(Constants.CSV_DELIMETER);
//            if (reader.readRecord()) {
//                code = reader.get(0);
//                firstname = reader.get(1);
//                lastname = reader.get(2);
//                if (company==null) {
//                    company=new Company();
//                }
//                company.setName(reader.get(3));
//                if (plan==null) {
//                    plan=new Plan();
//                }
//                plan.setDescription(reader.get(4));
//                gender=(reader.get(5).trim().equals("M")? 1: 0);
//
//                if (!"".equals(reader.get(6))) {
//                    effectDate = new Date(CommonUtils.stringToDateMDY(reader.get(6)).getTime());
//                }
//                if (!"".equals(reader.get(7))) {
//                    validDate = new Date(CommonUtils.stringToDateMDY(reader.get(7)).getTime());
//                }
//                if (!"".equals(reader.get(8))) {
//                    updateDate = new Date(CommonUtils.stringToDateMDY(reader.get(8)).getTime());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public String toCSVForInfo() {
//
//        String s = null;
//
//        byte[] buffer;
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        CsvWriter writer = new CsvWriter(stream, Constants.CSV_DELIMETER, Charset.forName("ISO-8859-1"));
//        try {
//            writer.write(code);
//            writer.write(firstname);
//            writer.write(lastname);
//            if (company!=null) {
//                writer.write(company.getName());
//            } else {
//                writer.write("");
//            }
//            if (plan!=null) {
//                writer.write(plan.getDescription());
//            } else {
//                writer.write("");
//            }
//            writer.write(gender==1?"M":"F");
//            if (effectDate != null) {
//                writer.write(CommonUtils.dateToStringMDY(effectDate));
//            } else {
//                writer.write("");
//            }
//            if (validDate != null) {
//                writer.write(CommonUtils.dateToStringMDY(validDate));
//            } else {
//                writer.write("");
//            }
//            if (updateDate != null) {
//                writer.write(CommonUtils.dateToStringMDY(updateDate));
//            } else {
//                writer.write("");
//            }
//
//            writer.endRecord();
//            writer.close();
//
//            buffer = stream.toByteArray();
//            stream.close();
//            s = Charset.forName("ISO-8859-1").decode(ByteBuffer.wrap(buffer)).toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return s;
//    }

//    public void fromCSVForCardData(String s) {
//        CsvReader reader = CsvReader.parse(s);
//        try {
//            reader.setDelimiter(Constants.CSV_DELIMETER);
//            if (reader.readRecord()) {
//                code = reader.get(0);
//                memberType = Integer.parseInt(reader.get(1));
//                lastname = reader.get(2);
//                firstname = reader.get(3);
//                if (company==null) {
//                    company=new Company();
//                }
//                company.setName(reader.get(4));
//                if (!"".equals(reader.get(5))) {
//                    birthDate = new Date(CommonUtils.stringToDateMDY(reader.get(5)).getTime());
//                }
//                gender=(reader.get(6).trim().equals("M")? 1: 0);
//                roomRateId = Integer.parseInt(reader.get(7));
//                if (plan==null) {
//                    plan=new Plan();
//                }
//                plan.setDescription(reader.get(8));
//                if (!"".equals(reader.get(9))) {
//                    effectDate = new Date(CommonUtils.stringToDateMDY(reader.get(9)).getTime());
//                }
//                if (!"".equals(reader.get(10))) {
//                    validDate = new Date(CommonUtils.stringToDateMDY(reader.get(10)).getTime());
//                }
//                if (!"".equals(reader.get(11))) {
//                    updateDate = new Date(CommonUtils.stringToDateMDY(reader.get(11)).getTime());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String toCSVForCardData() {
//
//        String s = null;
//
//        byte[] buffer;
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        CsvWriter writer = new CsvWriter(stream, Constants.CSV_DELIMETER, Charset.forName("ISO-8859-1"));
//        try {
//            writer.write(code);
//            writer.write(Integer.toString(memberType));
//            writer.write(lastname);
//            writer.write(firstname);
//            writer.write(companyCode);
//            if (company!=null) {
//                writer.write(company.getName());
//            } else {
//                writer.write("");
//            }
//            if (birthDate != null) {
//                writer.write(CommonUtils.dateToStringMDY(birthDate));
//            } else {
//                writer.write("");
//            }
//            writer.write(gender==1?"M":"F");
//            writer.write(Integer.toString(roomRateId));
//            if (plan!=null) {
//                writer.write(plan.getDescription());
//            } else {
//                writer.write("");
//            }
//            if (effectDate != null) {
//                writer.write(CommonUtils.dateToStringMDY(effectDate));
//            } else {
//                writer.write("");
//            }
//            if (validDate != null) {
//                writer.write(CommonUtils.dateToStringMDY(validDate));
//            } else {
//                writer.write("");
//            }
//            if (updateDate != null) {
//                writer.write(CommonUtils.dateToStringMDY(updateDate));
//            } else {
//                writer.write("");
//            }
//
//            writer.endRecord();
//            writer.close();
//
//            buffer = stream.toByteArray();
//            stream.close();
//            s = Charset.forName("ISO-8859-1").decode(ByteBuffer.wrap(buffer)).toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return s;
//    }

//    public void fromCSVForRemarks(String s) {
//        CsvReader reader = CsvReader.parse(s);
//        try {
//            reader.setDelimiter(Constants.CSV_DELIMETER);
//            if (reader.readRecord()) {
//                this.remarks1 = reader.get(0);
//                this.remarks2 = reader.get(1);
//                this.remarks3 = reader.get(2);
//                this.remarks4 = reader.get(3);
//                this.remarks5 = reader.get(4);
//                this.remarks6 = reader.get(5);
//                this.remarks7 = reader.get(6);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String toCSVForRemarks() {
//
//        String s = null;
//
//        byte[] buffer;
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        CsvWriter writer = new CsvWriter(stream, Constants.CSV_DELIMETER, Charset.forName("ISO-8859-1"));
//        try {
//            writer.write(this.remarks1);
//            writer.write(this.remarks2);
//            writer.write(this.remarks3);
//            writer.write(this.remarks4);
//            writer.write(this.remarks5);
//            writer.write(this.remarks6);
//            writer.write(this.remarks7);
//            writer.endRecord();
//            writer.close();
//
//            buffer = stream.toByteArray();
//            stream.close();
//            s = Charset.forName("ISO-8859-1").decode(ByteBuffer.wrap(buffer)).toString();
//            s.replaceAll("\"", "");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return s;
//    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    public Integer getIsPrincipal() {
        return isPrincipal;
    }

    public void setIsPrincipal(Integer isPrincipal) {
        this.isPrincipal = isPrincipal;
    }

    public String getFullname() {
        String fullname = "";
        fullname += this.getLastname() != null ? this.getLastname() : "";
        fullname += this.getFirstname() != null ? ", " + this.getFirstname() : "";
        fullname += " " + this.getMi() != null ? this.getMi() + "." : "";
        return fullname;
    }

    public Integer getAGE() {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("MMM dd, yyyy");
            LocalDate date1 = new LocalDate(this.birthDate);
            LocalDate now = new LocalDate();
            Years age = Years.yearsBetween(date1, now);
            return age.getYears();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}