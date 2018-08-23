package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Jabito on 19/10/2017.
 */
public class AccountsEntity {
    @JsonProperty("ACCOUNT_CODE")
    private String accountCode;
    @JsonProperty("ACCOUNT_NAME")
    private String accountName;
    @JsonProperty("PROPOSAL_NAME")
    private String proposalName;
    @JsonProperty("EFF_DATE")
    private Date effDate;
    @JsonProperty("AGENT_CODE")
    private String agentCode;
    @JsonProperty("SERIAL_NO")
    private String serialNo;
    @JsonProperty("ACCTTYPE_CODE")
    private String acctType;
    @JsonProperty("BRANCHES")
    private String branches;
    @JsonProperty("MEMBERSHIP_CODE")
    private String membershipCode;
    @JsonProperty("ACCTSTATUS_CODE")
    private String acctStatusCode;
    @JsonProperty("MEMSIZE_CODE")
    private String memSizeCode;
    @JsonProperty("PACKAGE_CODE")
    private String packageCode;
    @JsonProperty("SETUP_CODE")
    private String setupCode;
    @JsonProperty("STREET")
    private String street;
    @JsonProperty("CITY_CODE")
    private String cityCode;
    @JsonProperty("PROVINCE_CODE")
    private String provinceCode;
    @JsonProperty("PHONE_NO")
    private String phoneNo;
    @JsonProperty("FAX_NO")
    private String faxNo;
    @JsonProperty("CONTACT_SALUTATION")
    private String contactSalutation;
    @JsonProperty("CONTACT_FNAME")
    private String contactFname;
    @JsonProperty("CONTACT_LNAME")
    private String contactLname;
    @JsonProperty("CONTACT_MNAME")
    private String contactMname;
    @JsonProperty("CONTACT_POSITION")
    private String contactPos;
    @JsonProperty("FRANCHISE_DATE")
    private Date franchiseDate;
    @JsonProperty("FRANCHISE_EXPIRY")
    private Date franchiseExpiry;
    @JsonProperty("EXTEND60")
    private Boolean extend60;
    @JsonProperty("EXTEND150")
    private Boolean extend150;
    @JsonProperty("INDUSTRY_CODE")
    private String industryCode;
    @JsonProperty("MINDUSTRY_CODE")
    private String mindustryCode;
    @JsonProperty("OLD_HMO_CODE")
    private String oldHmoCode;
    @JsonProperty("OLD_HMO_EXPIRY")
    private Date oldHmoExpiry;
    @JsonProperty("ADD_SALUTATION")
    private String addSalutation;
    @JsonProperty("ADDRESSEE_FNAME")
    private String addresseeFname;
    @JsonProperty("ADDRESSEE_MNAME")
    private String addresseeMname;
    @JsonProperty("ADDRESSEE_LNAME")
    private String addresseeLname;
    @JsonProperty("ADDRESSEE_POSITION")
    private String addresseePos;
    @JsonProperty("REQUIRE_REMARKS")
    private String requireRemarks;
    @JsonProperty("WITH_RC")
    private Boolean withRc;
    @JsonProperty("WITH_MASTERLIST")
    private Boolean withMasterlist;
    @JsonProperty("SUBMIT_DATE_ML")
    private Date submitDate;
    @JsonProperty("WITH_SURVEY_FORM")
    private Boolean withSurveyForm;
    @JsonProperty("SUBMIT_DATE_SF")
    private Date submitDateSf;
    @JsonProperty("EXTENDED_FROM")
    private Date extendedFrom;
    @JsonProperty("EXTENDED_TO")
    private Date extendedTo;
    @JsonProperty("PROGRESS_REM")
    private String progressRem;
    @JsonProperty("DATE_CLOSED")
    private Date dateClosed;
    @JsonProperty("WITH_PROPOSAL")
    private String withProposal;
    @JsonProperty("UPDATED_DATE")
    private Date updatedDate;
    @JsonProperty("UPDATED_BY")
    private String updatedBy;
    @JsonProperty("SOB_APPROVED")
    private Boolean sobApproved;
    @JsonProperty("MOTHER_CODE")
    private String motherCode;
    @JsonProperty("MOTHER_STATUS")
    private Boolean motherStatus;
    @JsonProperty("PROPOSAL_TYPE")
    private Integer proposalType;
    @JsonProperty("ACCT_CATEGORY")
    private String acctCategory;
    @JsonProperty("AGENT_REASON")
    private String agentReason;
    @JsonProperty("ACCOUNT_DISPLAY")
    private String accountDisplay;
    @JsonProperty("SPLCASEID")
    private Integer splCaseId;
    @JsonProperty("ID_TYPE")
    private Integer idType;
    @JsonProperty("TRANSMITTAL_TYPE")
    private Integer transmittalType;
    @JsonProperty("BILLING_CTR_MO")
    private Integer billingCtrMo;
    @JsonProperty("BILLING_CTR_QTR")
    private Integer billingCtrQtr;
    @JsonProperty("BILLING_CTR_SA")
    private Integer billingCtrSa;
    @JsonProperty("BILLING_CTR_AN")
    private Integer billingCtrAn;
    @JsonProperty("Other_Remarks")
    private String otherRemarks;
    @JsonProperty("GRACE_PERIOD")
    private Integer gracePeriod;
    @JsonProperty("GROUP_TOP")
    private String groupTop;
    @JsonProperty("TAG_TOP")
    private String tagTop;
    @JsonProperty("DATE_VALIDITY")
    private Date dateValidity;
    @JsonProperty("ENCODE_TYPE")
    private Boolean encodeType;
    @JsonProperty("TAG")
    private Boolean tag;
    @JsonProperty("FOR_RENEWAL")
    private Boolean forRenewal;
    @JsonProperty("REN_UPDATE_DATE")
    private Date renUpdateDate;
    @JsonProperty("LOGIN")
    private String login;
    @JsonProperty("USER_ASSIGN")
    private String userAssign;
    @JsonProperty("TIN_NO")
    private String tinNo;
    @JsonProperty("FROM_INTRA")
    private Boolean fromIntra;
    @JsonProperty("WITH_RSP")
    private Boolean withRsp;
    @JsonProperty("VATTYPEID")
    private Integer vatTypeId;
    @JsonProperty("rowguid")
    private String rowGuiId;
    @JsonProperty("LAST_COVERED_DATE")
    private Date lastCoveredDate;
    @JsonProperty("EMAIL_ADD_1")
    private String emailAdd1;
    @JsonProperty("EMAIL_ADD_2")
    private String emailAdd2;
    @JsonProperty("corr_days")
    private Integer corrDays;

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getProposalName() {
        return proposalName;
    }

    public void setProposalName(String proposalName) {
        this.proposalName = proposalName;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getBranches() {
        return branches;
    }

    public void setBranches(String branches) {
        this.branches = branches;
    }

    public String getMembershipCode() {
        return membershipCode;
    }

    public void setMembershipCode(String membershipCode) {
        this.membershipCode = membershipCode;
    }

    public String getAcctStatusCode() {
        return acctStatusCode;
    }

    public void setAcctStatusCode(String acctStatusCode) {
        this.acctStatusCode = acctStatusCode;
    }

    public String getMemSizeCode() {
        return memSizeCode;
    }

    public void setMemSizeCode(String memSizeCode) {
        this.memSizeCode = memSizeCode;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getSetupCode() {
        return setupCode;
    }

    public void setSetupCode(String setupCode) {
        this.setupCode = setupCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getContactSalutation() {
        return contactSalutation;
    }

    public void setContactSalutation(String contactSalutation) {
        this.contactSalutation = contactSalutation;
    }

    public String getContactFname() {
        return contactFname;
    }

    public void setContactFname(String contactFname) {
        this.contactFname = contactFname;
    }

    public String getContactLname() {
        return contactLname;
    }

    public void setContactLname(String contactLname) {
        this.contactLname = contactLname;
    }

    public String getContactMname() {
        return contactMname;
    }

    public void setContactMname(String contactMname) {
        this.contactMname = contactMname;
    }

    public String getContactPos() {
        return contactPos;
    }

    public void setContactPos(String contactPos) {
        this.contactPos = contactPos;
    }

    public Date getFranchiseDate() {
        return franchiseDate;
    }

    public void setFranchiseDate(Date franchiseDate) {
        this.franchiseDate = franchiseDate;
    }

    public Date getFranchiseExpiry() {
        return franchiseExpiry;
    }

    public void setFranchiseExpiry(Date franchiseExpiry) {
        this.franchiseExpiry = franchiseExpiry;
    }

    public Boolean getExtend60() {
        return extend60;
    }

    public void setExtend60(Boolean extend60) {
        this.extend60 = extend60;
    }

    public Boolean getExtend150() {
        return extend150;
    }

    public void setExtend150(Boolean extend150) {
        this.extend150 = extend150;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getMindustryCode() {
        return mindustryCode;
    }

    public void setMindustryCode(String mindustryCode) {
        this.mindustryCode = mindustryCode;
    }

    public String getOldHmoCode() {
        return oldHmoCode;
    }

    public void setOldHmoCode(String oldHmoCode) {
        this.oldHmoCode = oldHmoCode;
    }

    public Date getOldHmoExpiry() {
        return oldHmoExpiry;
    }

    public void setOldHmoExpiry(Date oldHmoExpiry) {
        this.oldHmoExpiry = oldHmoExpiry;
    }

    public String getAddSalutation() {
        return addSalutation;
    }

    public void setAddSalutation(String addSalutation) {
        this.addSalutation = addSalutation;
    }

    public String getAddresseeFname() {
        return addresseeFname;
    }

    public void setAddresseeFname(String addresseeFname) {
        this.addresseeFname = addresseeFname;
    }

    public String getAddresseeMname() {
        return addresseeMname;
    }

    public void setAddresseeMname(String addresseeMname) {
        this.addresseeMname = addresseeMname;
    }

    public String getAddresseeLname() {
        return addresseeLname;
    }

    public void setAddresseeLname(String addresseeLname) {
        this.addresseeLname = addresseeLname;
    }

    public String getAddresseePos() {
        return addresseePos;
    }

    public void setAddresseePos(String addresseePos) {
        this.addresseePos = addresseePos;
    }

    public String getRequireRemarks() {
        return requireRemarks;
    }

    public void setRequireRemarks(String requireRemarks) {
        this.requireRemarks = requireRemarks;
    }

    public Boolean getWithRc() {
        return withRc;
    }

    public void setWithRc(Boolean withRc) {
        this.withRc = withRc;
    }

    public Boolean getWithMasterlist() {
        return withMasterlist;
    }

    public void setWithMasterlist(Boolean withMasterlist) {
        this.withMasterlist = withMasterlist;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Boolean getWithSurveyForm() {
        return withSurveyForm;
    }

    public void setWithSurveyForm(Boolean withSurveyForm) {
        this.withSurveyForm = withSurveyForm;
    }

    public Date getSubmitDateSf() {
        return submitDateSf;
    }

    public void setSubmitDateSf(Date submitDateSf) {
        this.submitDateSf = submitDateSf;
    }

    public Date getExtendedFrom() {
        return extendedFrom;
    }

    public void setExtendedFrom(Date extendedFrom) {
        this.extendedFrom = extendedFrom;
    }

    public Date getExtendedTo() {
        return extendedTo;
    }

    public void setExtendedTo(Date extendedTo) {
        this.extendedTo = extendedTo;
    }

    public String getProgressRem() {
        return progressRem;
    }

    public void setProgressRem(String progressRem) {
        this.progressRem = progressRem;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getWithProposal() {
        return withProposal;
    }

    public void setWithProposal(String withProposal) {
        this.withProposal = withProposal;
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

    public Boolean getSobApproved() {
        return sobApproved;
    }

    public void setSobApproved(Boolean sobApproved) {
        this.sobApproved = sobApproved;
    }

    public String getMotherCode() {
        return motherCode;
    }

    public void setMotherCode(String motherCode) {
        this.motherCode = motherCode;
    }

    public Boolean getMotherStatus() {
        return motherStatus;
    }

    public void setMotherStatus(Boolean motherStatus) {
        this.motherStatus = motherStatus;
    }

    public Integer getProposalType() {
        return proposalType;
    }

    public void setProposalType(Integer proposalType) {
        this.proposalType = proposalType;
    }

    public String getAcctCategory() {
        return acctCategory;
    }

    public void setAcctCategory(String acctCategory) {
        this.acctCategory = acctCategory;
    }

    public String getAgentReason() {
        return agentReason;
    }

    public void setAgentReason(String agentReason) {
        this.agentReason = agentReason;
    }

    public String getAccountDisplay() {
        return accountDisplay;
    }

    public void setAccountDisplay(String accountDisplay) {
        this.accountDisplay = accountDisplay;
    }

    public Integer getSplCaseId() {
        return splCaseId;
    }

    public void setSplCaseId(Integer splCaseId) {
        this.splCaseId = splCaseId;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public Integer getTransmittalType() {
        return transmittalType;
    }

    public void setTransmittalType(Integer transmittalType) {
        this.transmittalType = transmittalType;
    }

    public Integer getBillingCtrMo() {
        return billingCtrMo;
    }

    public void setBillingCtrMo(Integer billingCtrMo) {
        this.billingCtrMo = billingCtrMo;
    }

    public Integer getBillingCtrQtr() {
        return billingCtrQtr;
    }

    public void setBillingCtrQtr(Integer billingCtrQtr) {
        this.billingCtrQtr = billingCtrQtr;
    }

    public Integer getBillingCtrSa() {
        return billingCtrSa;
    }

    public void setBillingCtrSa(Integer billingCtrSa) {
        this.billingCtrSa = billingCtrSa;
    }

    public Integer getBillingCtrAn() {
        return billingCtrAn;
    }

    public void setBillingCtrAn(Integer billingCtrAn) {
        this.billingCtrAn = billingCtrAn;
    }

    public String getOtherRemarks() {
        return otherRemarks;
    }

    public void setOtherRemarks(String otherRemarks) {
        this.otherRemarks = otherRemarks;
    }

    public Integer getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(Integer gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public String getGroupTop() {
        return groupTop;
    }

    public void setGroupTop(String groupTop) {
        this.groupTop = groupTop;
    }

    public String getTagTop() {
        return tagTop;
    }

    public void setTagTop(String tagTop) {
        this.tagTop = tagTop;
    }

    public Date getDateValidity() {
        return dateValidity;
    }

    public void setDateValidity(Date dateValidity) {
        this.dateValidity = dateValidity;
    }

    public Boolean getEncodeType() {
        return encodeType;
    }

    public void setEncodeType(Boolean encodeType) {
        this.encodeType = encodeType;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    public Boolean getForRenewal() {
        return forRenewal;
    }

    public void setForRenewal(Boolean forRenewal) {
        this.forRenewal = forRenewal;
    }

    public Date getRenUpdateDate() {
        return renUpdateDate;
    }

    public void setRenUpdateDate(Date renUpdateDate) {
        this.renUpdateDate = renUpdateDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserAssign() {
        return userAssign;
    }

    public void setUserAssign(String userAssign) {
        this.userAssign = userAssign;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public Boolean getFromIntra() {
        return fromIntra;
    }

    public void setFromIntra(Boolean fromIntra) {
        this.fromIntra = fromIntra;
    }

    public Boolean getWithRsp() {
        return withRsp;
    }

    public void setWithRsp(Boolean withRsp) {
        this.withRsp = withRsp;
    }

    public Integer getVatTypeId() {
        return vatTypeId;
    }

    public void setVatTypeId(Integer vatTypeId) {
        this.vatTypeId = vatTypeId;
    }

    public String getRowGuiId() {
        return rowGuiId;
    }

    public void setRowGuiId(String rowGuiId) {
        this.rowGuiId = rowGuiId;
    }

    public Date getLastCoveredDate() {
        return lastCoveredDate;
    }

    public void setLastCoveredDate(Date lastCoveredDate) {
        this.lastCoveredDate = lastCoveredDate;
    }

    public String getEmailAdd1() {
        return emailAdd1;
    }

    public void setEmailAdd1(String emailAdd1) {
        this.emailAdd1 = emailAdd1;
    }

    public String getEmailAdd2() {
        return emailAdd2;
    }

    public void setEmailAdd2(String emailAdd2) {
        this.emailAdd2 = emailAdd2;
    }

    public Integer getCorrDays() {
        return corrDays;
    }

    public void setCorrDays(Integer corrDays) {
        this.corrDays = corrDays;
    }
}
