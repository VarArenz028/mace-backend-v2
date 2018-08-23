package com.basicauth.service;

import com.basicauth.config.Constants;
import com.basicauth.data.*;
import com.basicauth.domain.*;
import com.basicauth.domain.dups.MaceConsPrescribedtest;
import com.basicauth.mapper.MemMapper;
import com.basicauth.mapper.mace.DiagProcMapper;
import com.basicauth.mapper.mace.DocHospMapper;
import com.basicauth.mapper.mace.MaceMapper;
import com.basicauth.service.approval.ApprovalEngine;
import com.basicauth.service.approval.ApprovalEngineResult;
import com.basicauth.types.ConsultJson;
import net.incuventure.util.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.basicauth.config.Constants.DEFAULT_GROUP;
import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;

/**
 * Created by angulo on 10/19/2016.
 */
@Service("maceService")
public class MaceService {

    private static final Logger logger = LoggerFactory.getLogger(MaceService.class);

    @Autowired
    private MaceMapper maceMapper;

    @Autowired
    private MemService memService;

    @Autowired
    private LoaService loaService;

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private CustomerServiceService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private MemMapper memMapper;

    @Autowired
    private DocHospMapper docHospMapper;

    @Autowired
    private DiagProcMapper diagProcMapper;

    @Autowired
    private ApprovalEngine approvalEngine;

    @Autowired
    private CustomerServiceService customerServiceService;

    public boolean hasExistingMemUserAcct(String username, String memberCode) {
        logger.info("hasExistingMemUserAcct", username);
        logger.info("hasExistingMemUserAcct", memberCode);
        Map userAccount = maceMapper.hasExistingMemUserAcct(username, memberCode);
        System.out.println(userAccount);
        logger.info("hasExistingMemUserAcct", userAccount);
        return isNotNullOrEmpty(userAccount);
    }

    private boolean isNotNullOrEmpty(Map userAccount) {
        logger.info("isNotNullOrEmpty", userAccount);
        logger.info("isNotNullOrEmpty", (null != userAccount && !userAccount.isEmpty()));
        return null != userAccount && !userAccount.isEmpty();
    }

    public boolean matchUsernameAndPassword(String username, String password) {
        logger.info("matchUsernameAndPassword", username);
        logger.info("matchUsernameAndPassword", password);
        Map user = maceMapper.getMemUserAcct(username);
        String paz = (String) user.get("PASSWORD");
        boolean result = passwordEncoder.matches(password, paz);
        logger.info("matchUsernameAndPassword", result);
        return result;
    }


    public boolean matchUsernameAndPin(String username, String pin) {
        logger.info("matchUsernameAndPin", username);
        logger.info("matchUsernameAndPin", pin);
        Map user = maceMapper.getMemUserAcct(username);
        String oldPin = (String) user.get("PIN");
        logger.info("matchUsernameAndPin", oldPin);
        return pin.equalsIgnoreCase(oldPin);
    }

    public boolean isStatusLocked(String username) {
        logger.info("isStatusLocked", username);
        Map user = maceMapper.getMemUserAcct(username);
        String status = (String) user.get("STATUS");
        logger.info("isStatusLocked", status);
        boolean result = "LOCKED".equalsIgnoreCase(status);
        logger.info("isStatusLocked", result);

        return result;
    }

    public void increaseInvalidLoginCount(String username) {
        logger.info("increaseInvalidLoginCount", username);
        maceMapper.increaseInvalidLoginCount(username);
    }

    public void resetInvalidLoginCount(String username) {
        logger.info("resetInvalidLoginCount", username);
        maceMapper.resetInvalidLoginCount(username);
    }

    public void lockIfNeeded(String username) {
        logger.info("lockIfNeeded", username);
        Map user = maceMapper.getMemUserAcct(username);
        Integer invalidCount = (Integer) user.get("INVLOGINATT");
        logger.info("invalidCount", invalidCount);
        if (null != invalidCount) {
            if (3 <= invalidCount) {
                logger.info("lockUser");
                maceMapper.lockUser(username);
            }
        }
    }

    public void saveImage(ImageHolder imageHolder) {
        logger.info("saveImage", imageHolder);
        maceMapper.saveImageHolder(imageHolder);
    }

    public ImageHolder retrieveImage(String memCode) {
        logger.info("retrieveImage", memCode);
        return maceMapper.getImageHolder(memCode);
    }

    public void saveMaceRequestAttachment(MaceRequestAttachment maceRequestAttachment) {
        logger.info("maceAttachments", maceRequestAttachment);
        maceMapper.saveMaceRequestAttachment(maceRequestAttachment);
    }

    public MaceRequestAttachment retrieveMaceAttachment(int id) {
        logger.info("retrieveMaceAttachment", "id=" + id);

        MaceRequestAttachment attachment = maceMapper.retrieveMaceAttachment(id);
        logger.info("retrieveMaceAttachment", "attachment=" + attachment);

        return attachment;
    }

    public List getMaceAttachmentsByReqCode(String requestCode) {
        return maceMapper.getMaceAttachmentsByReqCode(requestCode);

    }

    public boolean hasExistingAppUserAcct(String username) {
        logger.info("hasExistingAppUserAcct", username);
        Map userAccount = maceMapper.hasExistingAppUserAcct(username);
        System.out.println(userAccount);
        logger.info("hasExistingAppUserAcct", userAccount);
        return isNotNullOrEmpty(userAccount);
    }

    public boolean matchUsernameAndPasswordForAppUser(String username, String password) {
        logger.info("matchUsernameAndPasswordForAppUser", username);
        Map user = maceMapper.getAppUser(username);
        String paz = (String) user.get("APP_PASSWORD");
        boolean result = passwordEncoder.matches(password, paz);
        logger.info("matchUsernameAndPasswordForAppUser", result);
        return result;
    }

    public boolean isAppUserActive(String username) {
        logger.info("isAppUserActive", username);
        Map user = maceMapper.getAppUser(username);
        String status = (String) user.get("STATUS");
        System.out.println("status:" + status);
        logger.info("isAppUserActive", status);
        boolean result = "ACTIVE".equalsIgnoreCase(status);
        logger.info("isAppUserActive", result);
        return result;
    }

    public List getUserAccts(int userAccountId) {
        logger.info("getUserAccts", userAccountId);
        List result = maceMapper.getUserAccounts(userAccountId);
        logger.info("getUserAccts", result);
        return result;
    }

    public List getUserAccountsDependents(int userAccountId) {
        logger.info("getUserAccountsDependents", userAccountId);
        List result = maceMapper.getUserAccountsDependents(userAccountId);
        logger.info("getUserAccountsDependents", result);
        return result;
    }

    public void saveMemAccount(MemberAccount memberAccount) {
        logger.info("saveMemAccount", memberAccount);
        maceMapper.saveMemAccount(memberAccount);
    }

    public Map getMemUserAccount(String username) {
        logger.info("getMemUserAccount", username);
        Map user = maceMapper.getMemUserAcct(username);
        logger.info("getMemUserAccount", user);
        return user;
    }

    public void deleteImage(String memCode) {
        logger.info("deleteImage", memCode);
        maceMapper.deleteImageHolder(memCode);
    }

    public VerifiedMember getLatestVerifiedMember(String memberCode) {
        logger.info("getLatestVerifiedMember", memberCode);
        VerifiedMember verifiedMember = maceMapper.getLatestVerifiedMember(memberCode);
        logger.info("getLatestVerifiedMember", verifiedMember);
        return verifiedMember;
    }

    public AppUser findbyUsername(String username) {
        logger.info("findbyUsername", username);
        AppUser appUser = maceMapper.findAppUserByUsername(username);
        logger.info("findbyUsername", appUser);
        return appUser;
    }

    public CustomerCare initCustomerCare(MemberDetails memberDetails, String hospitalCode, String doctorCode, String diagnosisCode, String procedureCode,
                                         BigDecimal procedureAmount,
                                         String locationCode, String appUserName,
                                         String procedureDesc,
                                         String memberCode,
                                         String diagnosisDesc,
                                         String roomType,
                                         String roomNo,
                                         String roomPrice,
                                         Date dateAdmitted,
                                         String category,
                                         String deviceId,
                                         String searchType,
                                         String primaryComplaint,
                                         String requestBy) {
        CustomerCare c = new CustomerCare();
        c.setStartTime(new Date());
        c.setEndTime(new Date());
        c.setCallTypeId(1);
        c.setCallDate(new Date());
        if (primaryComplaint != null)
            c.setPrimaryComplaint(primaryComplaint);
        else
            c.setPrimaryComplaint("");
        if (deviceId != null)
            c.setDeviceId(deviceId);
        else
            c.setDeviceId("DEVICE ID NOT SENT");
        if (requestBy != null)
            c.setCaller(requestBy);
        else
            c.setCaller(appUserName);
        //c.setCaller(memberDetails.getPRIN_CODE());
        System.out.println("SearchType = " + searchType);
        System.out.println("AppUsername = " + appUserName);
        if (searchType != null) {
            c.setSearchType("MACE - " + searchType.toUpperCase());
            c.setUpdatedBy(c.getSearchType());
        } else
            c.setUpdatedBy(appUserName);

        c.setMemberType(memberDetails.getMEM_TYPE());
        if (null == dateAdmitted)
            c.setDateAdmitted(new Date());
        else
            c.setDateAdmitted(dateAdmitted);
        c.setType(0);
        c.setHospitalCode(hospitalCode);
        c.setLocation(locationCode);
        c.setCompanyCode(memberDetails.getACCOUNT_CODE());
        c.setApprovalNo("");

        //TODO: Updated By should be search type - leave as is for now
        c.setUpdatedDate(new Date());
        c.setDiagnosisCode(diagnosisCode);
        c.setProcedureCode(procedureCode);
        c.setProcedureAmount(procedureAmount);
        c.setProcedureAmt(procedureAmount);
        c.setDoctorCode(doctorCode);
        c.setMemCompany(memberDetails.getACCOUNT_NAME());
        c.setMemFname(memberDetails.getMEM_FNAME());
        c.setMemLname(memberDetails.getMEM_LNAME());
        //Rooms Setup
        if (null != category)
            c.setCategory(category);
        if (null != roomNo && null != roomPrice)
            c.setRoom(roomNo + " P" + roomPrice + "/per day");
        else if (null != roomNo && (null == roomPrice || roomPrice.equals("")))
            c.setRoom(roomNo);
        else if (null == roomNo && (null != roomPrice && !roomPrice.equals("")))
            c.setRoom("P" + roomPrice + "/per day");
        else
            c.setRoom("");
        //End Rooms setup
        // c.setCategory("");
        c.setClassification(0);
        c.setSpecialization("");
        c.setAccredited(1);
        if (null != procedureCode) {
            if (procedureCode.contains("|")) {
                procedureCode.join(" ");
                System.out.println("JOIN: " + procedureCode);
                String[] procedures = procedureCode.split("\\|");
                System.out.println(procedures);
                String retVal = "";
                for (int x = 0; x < procedures.length; x++) {
                    if (!procedures[x].equals("|")) {
                        try {
                            System.out.println("ProcedureCode: " + procedures[x]);
                            ProcedureJson procedure = diagProcMapper.getProcedureByProcedureCode(procedures[x]);
                            if (null != procedure) {
                                System.out.println("Procedure: " + procedure.getProcedureDesc());
                                if (x != procedures.length)
                                    retVal += procedure.getProcedureDesc() + " | ";
                                else
                                    retVal += procedure.getProcedureDesc();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                c.setProcedureDesc(retVal);
            } else {
                try {
                    System.out.println("ProcedureCodeSingle: " + procedureCode);
                    ProcedureJson procedure = diagProcMapper.getProcedureByProcedureCode(procedureCode);
                    if (null != procedure) {
                        System.out.println("DescriptionSingle: " + procedure.getProcedureDesc());
                        c.setProcedureDesc(procedure.getProcedureDesc());
                        c.setProcedureCode(procedureCode);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (null != procedureDesc)
                c.setProcedureDesc(procedureDesc);
            else
                c.setProcedureDesc("");
            c.setProcedureCode("");
        }
        if (null != diagnosisCode) {
            if (diagnosisCode.contains("|")) {
                diagnosisCode.join(" ");
                String[] diags = diagnosisCode.split("\\|");
                String retVal = "";
                for (int x = 0; x < diags.length; x++) {
                    if (!diags[x].equals("|")) {
                        try {
                            System.out.println("DiagnosisCode: " + diags[x]);
                            Diagnosis diag = diagProcMapper.getDiagnosisByDiagnosisCode(diags[x]);
                            if (null != diag) {
                                System.out.println("Description: " + diag.getDiagDesc());
                                if (x != diags.length)
                                    retVal += diag.getDiagDesc() + " | ";
                                else
                                    retVal += diag.getDiagDesc();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                c.setDiagnosis(retVal);
            } else {
                try {
                    System.out.println("DiagnosisCodeSingle: " + diagnosisCode);
                    Diagnosis diag = diagProcMapper.getDiagnosisByDiagnosisCode(diagnosisCode);
                    if (null != diag) {
                        System.out.println("DescriptionSingle: " + diag.getDiagDesc());
                        c.setDiagnosis(diagnosisDesc);
                        c.setDiagnosisCode(diagnosisCode);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            c.setDiagnosis(diagnosisDesc);
            c.setDiagnosisCode("");
        }


        //check if doctorCode is null
        if (c.getDoctorCode() != null) {
            Doctor doc = new Doctor();
            try {
                doc = docHospMapper.getDoctor(c.getDoctorCode(), false);
                if (doc != null) {
                    logger.info(doc.getDoctorCode());
                    logger.info(doc.getDocLname() + " ," + doc.getDocFname() + " " + doc.getDocMname());
                    logger.info(doc.getSpecDesc());
                    String doctorName = doc.getDocLname() + " ," + doc.getDocFname() + " " + doc.getDocMname();
                    c.setDoctorName(doctorName);
                    if (doc.getSpecDesc() != null) {
                        c.setSpecialization(doc.getSpecDesc());
                    }

                }

                System.out.println("DoctorName : " + c.getDoctorName());
                System.out.println("DoctorSpecialization : " + c.getSpecialization());
            } catch (Exception e) {
                logger.info(c.getDoctorCode());
                c.setDoctorName(c.getDoctorCode());
                c.setDoctorCode("");
            }
        } else {
            logger.info(c.getDoctorCode());
            c.setDoctorCode("");
            c.setDoctorName("");
            c.setSpecialization("");
        }
        //Date Format

        //Removes Nulls
        if (null == c.getDiagnosis()) c.setDiagnosis("");
        if (null == c.getProcedureDesc()) c.setProcedureDesc("");
        if (null == c.getProcedureCode()) c.setProcedureCode("");
        if (null == c.getDiagnosisCode()) c.setDiagnosisCode("");
        if (null == c.getCategory()) c.setCategory("");
        if (null == c.getClassification()) c.setClassification(0);
        if (null == c.getApprovalNo()) c.setApprovalNo("");
        if (null == c.getLocation()) c.setLocation("");
        if (null == c.getProcedureAmount()) c.setProcedureAmount(BigDecimal.ZERO);
        if (null == c.getProcedureAmt()) c.setProcedureAmt(BigDecimal.ZERO);
        if (null == c.getRunningBill()) c.setRunningBill(BigDecimal.ZERO);
        if (null == c.getNotes()) c.setNotes("");
        if (null == c.getRemarks()) c.setRemarks("");
        if (null == c.getReason()) c.setReason("");

        c.setMemberCode(memberCode);
        c.setMemMi("");

        logger.info("initCustomerCare", c);
        return c;

    }

    //TODO: Check if Customer Care log for Inquiry is necessary
    public void logCustomerCareDetails(String memCode, List<String> serviceTypesList, Boolean isBlackListed, Boolean isLocked, String deviceID, String appUserName, String searchType, String hospitalCode, Map memInfo, MemberDetails memberDetails) {
        logger.info("LOG CUSTOMER DETAILS");
        //  MemberDetails memberDetails = memService.getMember(memCode);
        CustomerCare c = initCustomerCare(memberDetails, hospitalCode,
                null, null, null, null, hospitalCode, appUserName, null, memCode, null, null, null, null, null, null, deviceID, searchType, null,
                null);
        c.setActionTaken(4);
        String remarks = "";
        String notes = "";
        boolean forCall = false;
        boolean disapproved = false;

        //first validation - member status

        String memStat = memberDetails.getMem_OStat_Code();

        Boolean isBlackListedForOP = checkifCompanyIsBlacklisted(memberDetails.getACCOUNT_CODE(), "Consultation");
        Boolean isBlacklistedForIP = checkifCompanyIsBlacklisted(memberDetails.getACCOUNT_CODE(), "Inpatient");

        isBlackListed = isBlackListedForOP && isBlacklistedForIP;
        //disapproved status
        if (memStat.equalsIgnoreCase("RESIGNED")) {
            remarks = "Resigned";
            notes = "Patient is no longer a MediCard Member";
            disapproved = true;
        } else if (memStat.equalsIgnoreCase("CANCELLED")) {
            remarks = "Membership Cancelled";
            notes = "Patient is no longer a MediCard Member";
            disapproved = true;
        } else if (memStat.equalsIgnoreCase("ON HOLD")) {
            remarks = "Membership On Hold";
            notes = "Your Account is on hold. Please contact your MediCard Account Officer.";
//            forCall = true;
        } else if (memStat.equalsIgnoreCase("FOR REACTIVATION")) {
            remarks = "For Reactivation";
            notes = "Your account is for Reactivation. Please contact your MediCard Account Officer.";
//            forCall = true;
        } else if (memStat.equalsIgnoreCase("VERIFY MEMBERSHIP")) {
            remarks = "Verify with URG";
            notes = "Your request cannot be processed at this time. Please call 841-8080";
//            forCall = true;
        } else if (memStat.equalsIgnoreCase("VERIFY PAYMENT WITH RMD")) {
            remarks = "Verify with RMD";
            notes = "Your request cannot be processed at this time. Please call 841-8080";
//            forCall = true;
        } else if (memStat.equalsIgnoreCase("VERIFY RENEWAL FROM MARKETING / SALES")) {
            remarks = "Verify with SBD1/SBD2";
            notes = "Your request cannot be processed at this time. Please call 841-8080";
//            forCall = true;
        } else if (memStat.equalsIgnoreCase("FOR ENCODING")) {
            remarks = "For Encoding";
            notes = "Please call 841-8080 to verify validity.";
            forCall = true;
        } else if (memStat.equalsIgnoreCase("MEDICAL EVALUATION")) {
            remarks = "Medical Evaluation";
            notes = "Please call 841-8080 to verify validity.";
            forCall = true;
        } else if (memStat.equalsIgnoreCase("LAPSE (NON RENEW)")) {
            remarks = "Lapse (Non Renew)";
            notes = "Please call 841-8080 to verify validity.";
            forCall = true;
        } else if (memStat.equalsIgnoreCase("FOR APPROVAL")) {
            remarks = "For Approval";
            notes = "Please call 841-8080 to verify validity.";
            forCall = true;
        }

        if (forCall || disapproved) {
            c.setRemarks("Member Inquiry - " + remarks);
            c.setNotes(notes);
            c.setDefaults(c);
            saveTransactionForInquiry(c);
            logger.info("FOR CALL");
            return;
        } else if (serviceTypesList.isEmpty() && isBlackListed.equals(Boolean.FALSE)) {
            //member has no access to hospital
            c.setRemarks("Member Inquiry - No Access to Hospital");
            c.setNotes("Member has no access to hospital/clinic");
            c.setDefaults(c);
            saveTransactionForInquiry(c);
            logger.info("NO ACCESS");
            return;
        } else if (isBlackListed.equals(Boolean.TRUE)) {
            //check if blacklisted
            c.setRemarks("Member Inquiry - Company Blacklisted");
            c.setNotes("Please call 841-8080.");
            c.setDefaults(c);
            saveTransactionForInquiry(c);
            logger.info("BLACKLISTED");
            return;
        } else if (isLocked.equals(Boolean.TRUE)) {
            //check if user is locked - three invalid attempts
            c.setRemarks("Member Inquiry - Member's account is locked.");
            c.setNotes("Member has entered incorrect Date of Birth. Please call 841-8080.");
            c.setDefaults(c);
            saveTransactionForInquiry(c);
            logger.info("LOCKED");
            return;
        }
        return;
    }

    public void saveTransactionForInquiry(CustomerCare c) {
        //for confirmation if type = 2
        c.setCallTypeId(2);
        //for confirmation what transaction type
        c.setType(0);
        //action taken for disapproved
        c.setActionTaken(1);

        c.setBatchCode(generateID("BATCHNO"));
        c.setCallerId(generateID("CALLERID"));

        //02/28 - GMeneses - for call should only be saved in For Call table in MACE db
        logger.info("saveTransactionForInquiry", c);
        maceMapper.saveTransactionCustomerCare(c);
        maceMapper.saveTransactionCustomerCareApproval(c);
        customerService.saveTransactionForCall(c);
        customerService.callHistoryLog("INQUIRY ", c, 0);
    }

    public void saveTransactionForCall(CustomerCare c) {
        if (null == c.getRoom() || c.getRoom().equals("0"))
            c.setRoom("");
        if (null == c.getRemarks())
            c.setRemarks("");
        if (null == c.getProcedureAmount()) {
            c.setProcedureAmount(BigDecimal.ZERO);
            c.setProcedureAmt(BigDecimal.ZERO);
        }
        if (null == c.getRunningBill())
            c.setRunningBill(BigDecimal.ZERO);
        if (null == c.getNotes())
            c.setNotes("");
        if (null == c.getReason())
            c.setReason("");

        //02/28 - GMeneses - for call should only be saved in For Call table in MACE db
        logger.info("saveTransactionForCall", c);
        maceMapper.saveTransactionCustomerCare(c);
        maceMapper.saveTransactionCustomerCareApproval(c);
        //customerService.callHistoryLog("APPROVED", c, 0);
    }

    public boolean checkifCompanyIsBlacklisted(String account_code, String serviceType) {
        logger.info("checkifCompanyIsBlacklisted", account_code);
        logger.info("checkifCompanyIsBlacklisted", serviceType);
        boolean result = maceMapper.isCompanyBlacklisted(account_code, serviceType);
        logger.info("checkifCompanyIsBlacklisted", result);
        return result;
    }

    public void cancelLOAByRequestCode(String requestCode, String user, String updatedBy) {
        logger.info("cancelLOAByRequestCode");
        maceMapper.cancelMaceReqByRequestCode(requestCode);
//        try {
//            CustomerCare loa = maceMapper.getLoaLTBLByBatchCode(batchCode);
//            if (null != loa) {
//                try {
//                    historyMapper.addToLoaHistory(loa);
//                    historyMapper.addToLoaLTBLHistory(loa);
//                    if (null == loa.getDoctorCode())
//                        loa.setDoctorCode("");
//                    historyMapper.addToLoaPFHistory(loa);
//                    customerService.callHistoryLog("CANCELLED", loa, 1);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                maceMapper.cancelLOAByBatchCode(batchCode, "Cancelled by " + user, updatedBy.equals("MEMBER") ? loa.getMemberCode() : "Coordinator");
//                maceMapper.cancelLTBLByBatchCode(batchCode, "Cancelled by " + user, updatedBy.equals("MEMBER") ? loa.getMemberCode() : "Coordinator");
//                customerServiceMapper.cancelLTBLByBatchCode(batchCode, "Cancelled by" + user, updatedBy.equals("MEMBER") ? loa.getMemberCode() : "Coordinator");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public String generateID(String type) {
        logger.info("generateID", type);
        //get last id
        HashMap lastid = maceMapper.getLastId(type);
        System.out.println("lastid:" + lastid);
        logger.info("lastid", lastid);
        int approvalNo = (int) lastid.get("NO") + 1;
        logger.info("approvalNo", approvalNo);
        String prefix = (String) lastid.get("PREFIX");
        logger.info("prefix", prefix);
        int padding = 0;
        if (prefix != null) {
            padding += prefix.length();
        }
        padding += (int) lastid.get("LENGTH");
        maceMapper.updateLastId(type, approvalNo);
        String approvalNumString = String.valueOf(approvalNo);
        padding = padding - approvalNumString.length();
        logger.info("approvalNumString", approvalNumString);

        String result = "";
        if (padding > 0) {
            logger.info("padding", padding);
            result = String.format("%1$-" + padding + "s", prefix).replace(' ', '0') + approvalNumString;
            return result;
        } else {
            return approvalNumString;
        }
    }

    public CustomerCare saveTransaction(CustomerCare c, MemberDetails memberDetails) {
        logger.info("saveTransaction", memberDetails);
        logger.info("saveTransactionCustomerCare", c);
        maceMapper.saveTransactionCustomerCare(c);
        logger.info("saveTransactionCustomerCareApproval", c);
        maceMapper.saveTransactionCustomerCareApproval(c);
        logger.info("saveTransactionCustomerCarePhysician", c);
        maceMapper.saveTransactionCustomerCarePhysician(c);

        return c;

    }

    public boolean checkIfMemberCodeExistingInAccount(int id, String depMemberCode) {
        logger.info("checkIfMemberCodeExistingInAccount", id);
        logger.info("checkIfMemberCodeExistingInAccount", depMemberCode);
        boolean result = maceMapper.checkIfMemberCodeExistingInAccount(id, depMemberCode);
        logger.info("checkIfMemberCodeExistingInAccount", result);
        return result;
    }

    public void saveUserAccount(int id, MemberDetails memberDetails, String memberCode) {
        logger.info("saveUserAccount", id);
        logger.info("saveUserAccount", memberDetails);
        logger.info("saveUserAccount", memberCode);
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setUserAcctId(id);
        memberAccount.setMemCode(memberCode);

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        try {
            logger.info("memberDetails.getBDAY()", memberDetails.getBDAY());
            Date bday = sdf.parse(memberDetails.getBDAY());
            logger.info("bday", bday);
            memberAccount.setMemBday(bday);
        } catch (Exception e) {
            logger.info("e", e);
            memberAccount.setMemBday(null);
        }

        memberAccount.setMemFname(memberDetails.getMEM_FNAME());
        memberAccount.setMemLname(memberDetails.getMEM_LNAME());
        memberAccount.setMemMname("");//TODO Ask alvin to modify
        memberAccount.setMemSex(memberDetails.getMEM_SEX());
        memberAccount.setMemType(0);
        logger.info("saveUserAccount", memberAccount);
        saveMemAccount(memberAccount);
    }

    public Map getMemUserAccountByMemberCode(String memberCode) {
        logger.info("getMemUserAccountByMemberCode", memberCode);
        Map user = maceMapper.getMemUserAcctByMemberCode(memberCode);
        logger.info("getMemUserAccountByMemberCode", user);
        return user;

    }

    public void changePassword(String username, String newPassword) {
        logger.info("changePassword", username);
        maceMapper.changeMemUserAccountPassword(username, passwordEncoder.encode(newPassword));
    }

    public void changePasswordProvider(String username, String newPassword) {
        logger.info("changePassword", username);
        maceMapper.changeAppUserAccountPassword(username, passwordEncoder.encode(newPassword));
    }

    public void changePin(String username, String pin) {
        logger.info("changePin", username);
        maceMapper.changeMemUserAccountPin(username, pin);
    }

    public String generateNewPassword() {
        return passwordGenerator.nextPassword();
    }

    public CustomerCare processInpatient(MemberDetails memberDetails, String hospitalCode, String doctorCode,
                                         String diagnosis, String roomType, String roomNo, String roomPrice, String appUserName, String procedure,
                                         Date dateAdmitted, String category) {
        logger.info("processInpatient", memberDetails);
        logger.info("processInpatient", hospitalCode);
        logger.info("processInpatient", doctorCode);
        logger.info("processInpatient", diagnosis);
        logger.info("processInpatient", roomType);
        logger.info("processInpatient", roomNo);
        logger.info("processInpatient", roomPrice);
        logger.info("processInpatient", appUserName);
        logger.info("processInpatient", procedure);
        logger.info("processInpatient", dateAdmitted);

        CustomerCare c = initCustomerCare(memberDetails, hospitalCode, doctorCode, null,
                null,
                BigDecimal.ZERO,
                null,
                appUserName,
                procedure,
                memberDetails.getPRIN_CODE(),
                diagnosis,
                roomType,
                roomNo,
                roomPrice,
                dateAdmitted,
                category,
                null,
                null,
                null,
                null);
        c.setType(1);
        c.setActionTaken(4);
        logger.info("processInpatient", c);
        return c;
    }

    public OtherLimit getOtherLimit() {
        OtherLimit otherLimit = maceMapper.getOtherLimit();
        logger.info("getOtherLimit", otherLimit);
        return otherLimit;
    }

    public Double getInnerLimit(String costCenter) {
        logger.info("getInnerLimit", costCenter);
        Double result = maceMapper.getInnerLimit(costCenter);
        logger.info("result", result);
        return result;
    }

    public Map findMemUserByEmailAndMemberCode(String email, String memberCode) {
        logger.info("findMemUserByEmailAndMemberCode", email);
        logger.info("findMemUserByEmailAndMemberCode", memberCode);
        Map result = maceMapper.findMemUserByEmailAndMemberCode(email, memberCode);
        logger.info("findMemUserByEmailAndMemberCode", result);
        return result;
    }

    public List getProceduresList() {
        List<DiagnosisClinicProceduresEntity> result = diagProcMapper.getAllProcedures();
        List<ProcedureJson> procedures = new ArrayList<>();
        for (DiagnosisClinicProceduresEntity procedure : result) {
            ProcedureJson proc = new ProcedureJson();
            proc.setProcedureAmount(BigDecimal.valueOf(procedure.getAmount()));
            proc.setProcedureCode(procedure.getProcID());
            proc.setProcedureDesc(procedure.getProcedureName());
            proc.setServiceClassCode(procedure.getProClassCode());
            procedures.add(proc);
        }
        logger.info("getProceduresList", result);
        return procedures;
    }

    public List getMessages() {
        List result = maceMapper.getMessages();
        logger.info("getMessage", result);
        return result;
    }


    public void changePasswordAuto(String username, String newPassword) {
        logger.info("changePasswordAuto", username);
        maceMapper.changeMemUserAccountPasswordAuto(username, passwordEncoder.encode(newPassword));
    }

    public List<String> getServiceTypes(String memberCode, String hospitalCode, MemberDetails memberDetails) {
        logger.info("getServiceTypes", memberCode);
        logger.info("getServiceTypes", hospitalCode);
        logger.info("getServiceTypes", memberDetails);

        Member member = memMapper.getMemberSelect(memberCode);

        List<String> serviceList = new ArrayList<>(5);
        /**
         * Check Consultation
         */

        String msgCode = memMapper.mossConsultation(memberCode, 2, hospitalCode);
        if (msgCode.equalsIgnoreCase("0") || msgCode.equalsIgnoreCase("21") ||
                msgCode.equalsIgnoreCase("42")) {
            serviceList.add("Consultation");
        }

        /**
         * Check OtherTest
         */

        Integer retCode = memMapper.mossProcessInpx(memberCode, hospitalCode);
        if (Integer.valueOf("0").compareTo(retCode) == 0 || msgCode.equalsIgnoreCase("21") ||
                msgCode.equalsIgnoreCase("42")) {
            serviceList.add("OtherTest");
        }

        System.out.println();
        if (memMapper.hasInPatientAccess(hospitalCode, member.getRoomRateId())) {
            serviceList.add("Inpatient");
        }

        System.out.println();
        if (memMapper.hasOutPatientAccess(hospitalCode, member.getRoomRateId())) {
            serviceList.add("Outpatient");
        }
        //forced ER
        //TODO : ER VALIDATION
        serviceList.add("Er");


        List<Blacklist> blacklistedService = companyBlacklistedServices(memberDetails.getACCOUNT_CODE());
        System.out.println("serviceList:" + serviceList);
        System.out.println("blacklistedService:" + blacklistedService);

        for (Blacklist s : blacklistedService) {
            serviceList.remove(s.getService());
        }
        System.out.println("serviceList:" + serviceList);
        logger.info("serviceList", serviceList);


        return serviceList;
    }

    public String getServiceTypeBlock(String memberCode, String hospitalCode, MemberDetails memberDetails, String transaction) {
        logger.info("getServiceTypes", memberCode);
        logger.info("getServiceTypes", hospitalCode);
        logger.info("getServiceTypes", memberDetails);

        Member member = memMapper.getMemberSelect(memberCode);

        Integer msgCode = -1;
        //Filter of transaction
        switch (transaction.toLowerCase()) {
            case "consultation":
                msgCode = Integer.valueOf(memMapper.mossConsultation(memberCode, 2, hospitalCode));
                break;
            case "maternity":
                msgCode = Integer.valueOf(memMapper.mossConsultation(memberCode, 1, hospitalCode));
                break;
            case "basictest":
                msgCode = Integer.valueOf(memMapper.mossConsultation(memberCode, 2, hospitalCode));
                break;
            case "othertest":
                msgCode = memMapper.mossProcessInpx(memberCode, hospitalCode);
                break;
            case "inpatient":
                msgCode = memMapper.mossProcessInpx(memberCode, hospitalCode);
                break;
            case "er":
                msgCode = memMapper.mossProcessInpx(memberCode, hospitalCode);
                break;
            case "outpatient":
                msgCode = Integer.valueOf(memMapper.mossConsultation(memberCode, 2, hospitalCode));
                break;
            default:
                break;
        }
        return String.valueOf(msgCode);
    }

    public List<Blacklist> companyBlacklistedServices(String account_code) {
        logger.info("companyBlacklistedServices", account_code);
        List result = maceMapper.companyBlacklistedServices(account_code);
        logger.info("companyBlacklistedServices", result);
        return result;
    }

    public SpecialAccount getSpecialAccount(String account_code) {
        logger.info("getSpecialAccount", account_code);
        SpecialAccount result = maceMapper.getSpecialAccount(account_code);
        logger.info("getSpecialAccount", result);
        return result;
    }

    public boolean hasMaternity(String memberCode) {
        logger.info("hasMaternity", memberCode);
        if (Integer.valueOf("0").compareTo(memMapper.getMember(memberCode).getMEM_SEX()) == 0) {
            boolean result = memMapper.hasMaternityFromRiderCode(memberCode);
            logger.info("hasMaternity", result);
            return result;
        } else {
            logger.info("hasMaternity man");
            return false;
        }
    }

//    public void lockMember(String memberCode) {
//        logger.info("lockMember", memberCode);
//        maceMapper.lockMember(memberCode, new Date());
//    }

    public void lockMember(LockMember lockMember) {
        lockMember.setDateTimeBlocked(new Date());
        maceMapper.lockMember(lockMember);
    }

    public boolean isMemberLocked(String memberCode) {
        logger.info("isMemberLocked", memberCode);
        boolean result = maceMapper.isMemberLocked(memberCode);
        logger.info("isMemberLocked", result);
        return result;
    }

//    public CustomerCare processEmergencyRoomInquiry(MemberDetails memberDetails, String hospitalCode, String doctorCode,
//                                                    String diagnosisCode, String appUserName, String procedureCode, String erReason, String dateAdmitted) {

    public CustomerCare processEmergencyRoomInquiry(MemberDetails memberDetails, String hospitalCode, String appUserName, String erReason, String dateAdmitted) {
        logger.info("processEmergencyRoomInquiry", memberDetails);
        logger.info("processEmergencyRoomInquiry", hospitalCode);
//        logger.info("processEmergencyRoomInquiry", doctorCode);
//        logger.info("processEmergencyRoomInquiry", diagnosisCode);
        logger.info("processEmergencyRoomInquiry", appUserName);
//        logger.info("processEmergencyRoomInquiry", procedureCode);
        logger.info("processEmergencyRoomInquiry", erReason);
        logger.info("processEmergencyRoomInquiry", dateAdmitted);

        CustomerCare c = initCustomerCare(memberDetails, hospitalCode,
                null,
                null,
                null,
                BigDecimal.ZERO,
                null,
                appUserName,
                "",
                memberDetails.getPRIN_CODE(),
                "",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        c.setType(-1);
        //GMeneses: This was already set int the controller
        // c.setActionTaken(4);
        logger.info("processEmergencyRoomInquiry", c);

        return c;
    }


    public boolean checkIfProviderHasAppUserAccount(String doctorCode) {
        return maceMapper.checkIfProviderHasAppUserAccount(doctorCode);
    }


    public void saveComplaint(String batchCode, String complaint) {
        if (complaint == null)
            complaint = "";
        maceMapper.saveComplaint(batchCode, complaint);
    }

    public String getAppUserEmailByUsername(String username) {
        String email = maceMapper.getAppUserEmailByUsername(username);
        return email;
    }

    public BlockingMessages getBlockingMessageByCode(String msgCode) {
        logger.info("getBlockingMessageByCode");
        BlockingMessages blocker = maceMapper.getBlockingMessageByCode(msgCode);
        return blocker;
    }

    public Double getCostByTransactionType(String transaction) {
        System.out.println("Transaction Type: " + transaction);
        Double amount = maceMapper.getCostByTransactionType(transaction);
        System.out.println("Return Amount: " + amount);
        return amount;
    }

    public void unlockUserByMemberCode(String mem_code) {
        logger.info("unlockUserByMemberCode", mem_code);
        maceMapper.unlockUser(mem_code);
    }

    public PrescribedTest getTestByTestCode(String testCode) {
        logger.info("getTestByTestCode", testCode);
        PrescribedTest test = maceMapper.getTestByTestCode(testCode);

        return test;
    }

    public Integer checkForDuplicateRequest(CustomerCare c) {
        logger.info("checkForDuplicateRequest");
        return maceMapper.getRequestByComparison(c);
    }

    public void storeInTempTransaction(CustomerCare c) {
        logger.info("storeInTempTransaction");
        maceMapper.storeInTempTransaction(c);
    }

    public AppUserDevice getAppUserDeviceByDeviceId(String deviceId) {
        AppUserDevice device = maceMapper.getAppUserDeviceByDeviceId(deviceId);
        return null == device ? new AppUserDevice() : device;
    }

    public AppVersion getAppVersionByUserType(String type) {
        AppVersion version = maceMapper.getAppVersionByUserType(type);
        return version;
    }

    public String getPinByMemberCode(String memberCode) {
        String pin = maceMapper.getPinByMemberCode(memberCode);
        return pin;
    }

    public void updateLOADisclaimerByApprovalNo(String approvalNumber, Integer disclaimerTicked) {
        maceMapper.updateLOADisclaimerByApprovalNo(approvalNumber, disclaimerTicked);
    }

    public void checkDisclaimerForChanges(String memberCode, Integer disclaimerTicked) {
        Integer ticked = maceMapper.getDisclaimerTickedByMemberCode(memberCode);
        if (ticked != null) {
            if (ticked != disclaimerTicked) {
                maceMapper.updateDisclaimerTickedByMemberCode(memberCode, disclaimerTicked, new Date());
            }
        }
    }

    public Integer getHasDisclaimerByMemberCode(String memberCode) {
        Integer hasDisclaimer = maceMapper.getDisclaimerTickedByMemberCode(memberCode);
        if (null == hasDisclaimer)
            return 0;
        return hasDisclaimer;
    }

    public void changePasswordProviderAuto(Integer forceChange, String username, String newPassword) {
        logger.info("changePasswordAuto", username);
        if (forceChange == 1)
            maceMapper.changeAppUserAccountPasswordAuto(username, passwordEncoder.encode(newPassword));
        else
            maceMapper.changeAppUserAccountPassword(username, passwordEncoder.encode(newPassword));
    }

    public Boolean getBdayByMemberCodeAndCompare(String memberCode, String bday) {
        logger.info("getBdayByMemberCode");
        Member member = memMapper.getMemberSelect(memberCode);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
        Date birthday = null;
        try {
            birthday = sdfParam.parse(bday);
            System.out.println("Bday1:" + birthday);
            System.out.println("Bday2:" + member.getBirthDate());
            System.out.println(birthday.compareTo(member.getBirthDate()) == 0);
            return birthday.compareTo(member.getBirthDate()) == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean isProcedureAvailable(String doctorCode, String hospitalCode, String diagnosisCode) {
        return maceMapper.checkProcedureAvailability(doctorCode, hospitalCode, diagnosisCode);
    }

    public void saveMaceRequest(MaceRequest maceRequest) {
        maceMapper.saveMaceRequest(maceRequest);
    }

    public void saveMaceRequestOpDiag(MaceRequestOpDiag mrod) {
        maceMapper.saveMaceRequestOpDiag(mrod);
    }

    public List<MaceRequest> getFilteredLoaByMemberCode(String memberCode, String filterType) {
        logger.info("getFilteredLoaByMemberCode");
        //2-OpTest 3-OpProc
        List<MaceRequest> loaList;
        if (filterType.equals("OTHER TEST") || filterType.equals("PROCEDURE"))
            loaList = maceMapper.getFilteredLoaByMemberCode(memberCode, 2);
        else if (filterType.equals("PROCEDURES"))
            loaList = maceMapper.getFilteredLoaByMemberCode(memberCode, 3);
        else
            loaList = null;

        return loaList;
    }

    //TODO: Return list of MaceReqCosnult
    public List<MaceReqConsult> getAvailedConsultWithTests(String membercode, String consStatus, String consType, String doctor, String serviceType, String serviceStatus) {
        List<MaceReqConsult> availedConsults = new ArrayList<>();
        availedConsults = maceMapper.getMaceRequestConsult(membercode, consStatus, consType, doctor, serviceType, serviceStatus, 10, 10);
        if (availedConsults.size() > 0) {
            for (MaceReqConsult cons : availedConsults) {
                cons.setMaceRequest(maceMapper.getMaceRequestByRequestID(cons.getMaceRequestId()));
                cons.setMaceConsultDiagnosis(maceMapper.getConsultDiagnosis(cons.getTransactionId().intValue(), cons.getMaceRequestId()));
                cons.setMaceConsultPrescribed(maceMapper.getConsultPrescribedTests(cons.getTransactionId().intValue(), cons.getMaceRequestId(), serviceStatus, serviceType));
            }
        }
        return availedConsults;
    }

    public AvailedConsult getAvailedConsultationByApprovalNo(String approvalNo) {
        LoaMace consultation = maceMapper.getAvailedConsultationByApprovalNo(approvalNo);
        if (consultation == null) {
            return null;
        }

        AvailedConsult availedConsult = new AvailedConsult(consultation);

        ConsultationRecordVersion03Json consultRecord = maceMapper.getConsultationRecordVersion3ByApprovalNo(availedConsult.getApprovalNo());
        if (consultRecord != null) {
            availedConsult.addConsultRecord(consultRecord);
            availedConsult.setPrescribedTestOrProcedures(maceMapper.getConsultationRecordVersion3PrescribedTestOrProcedures(consultRecord.getId()));
            availedConsult.setOtherDiagnosisContributoryToChiefComplaint(maceMapper.getConsultationRecordVersion3OtherDiagnosisContributoryToChiefComplaintList(consultRecord.getId()));
            availedConsult.setOtherDiagnosisNonContributoryToChiefComplaint(maceMapper.getConsultationRecordVersion3OtherDiagnosisNonContributoryToChiefComplaintList(consultRecord.getId()));
            availedConsult.setProceduresDoneInClinic(maceMapper.getConsultationRecordVersion3ProceduresDoneInClinic(consultRecord.getId()));
        }

        return availedConsult;
    }

    public void saveMaceReqTest(MaceRequest maceRequest, MaceRequestTest maceRequestTest) {
        maceRequestTest.setMaceRequestId(maceRequest.getRequestId());
        maceMapper.saveMaceReqTest(maceRequestTest);
    }

    public void saveMaceRequestOpTest(MaceRequest mr, MaceRequestTest mrt, MaceRequestOpDiag mrod, MaceRequestOpTest mrot) {
        mrot.setMaceRequestId(mr.getRequestId());
        mrot.setReqDiagId(mrod.getReqDiagId());
        mrot.setTransactionId(mrt.getTransactionId());

        maceMapper.saveMaceReqOpTest(mrot);
    }

    public void saveMaceReqOpDiagnosisProcedures(MaceRequest maceRequest, List<MaceReqOpDiagnosisProcedure> maceReqOpDiagnosisProcedures) {
        MaceRequestOpDiag mrod;
        MaceRequestProcedure mrp;
        MaceRequestOpProcedure mrop;

        for (MaceReqOpDiagnosisProcedure mrodp : maceReqOpDiagnosisProcedures) {
            mrod = mrodp.getMaceRequestOpDiag();
            mrop = mrodp.getMaceRequestOpProcedure();
            mrp = mrodp.getMaceRequestProcedure();

            if (mrp == null) {
                mrp = maceMapper.getMaceRequestProcedureByMaceRequestId(maceRequest.getRequestId());
            } else {
                maceMapper.saveMaceReqProcedure(maceRequest, mrp);
            }

            mrod.setMaceRequestId(maceRequest.getRequestId());
            mrod.setTransactionId(mrp.getTransactionId());
            maceMapper.saveMaceReqOpDiag(maceRequest, mrod);

            mrop.setReqDiagId(mrod.getReqDiagId());
            mrop.setTransactionId(mrp.getTransactionId());
            mrop.setMaceRequestId(maceRequest.getRequestId());
            maceMapper.saveMaceReqOpProcedure(maceRequest, mrop);
        }
    }

    public void saveMaceRequestAuditLog(MaceRequest maceRequest, MaceRequestAudit maceRequestAudit) {
        maceMapper.saveMaceRequestAuditLog(maceRequest, maceRequestAudit);
    }

    public CustomerCare requestLOABasicTest(MemberDetails memberDetails, ConsultJson consultJson) {
        CustomerCare c = initCustomerCare(memberDetails, consultJson.getHospitalCode(),
                consultJson.getDoctorCode(), consultJson.getDiagnosisCode(), consultJson.getProcedureCode(),
                consultJson.getProcedureAmount(), consultJson.getLocationCode(), consultJson.getUsername(),
                consultJson.getProcedureDesc(), consultJson.getMemberCode(), consultJson.getDiagnosisDesc(),
                null, null, null, null, null,
                null, null, consultJson.getPrimaryComplaint(),
                null);
        c.setDiagnosis("BASIC TEST");

        Integer msgCode = loaService.requestApprovalProcedure(consultJson.getMemberCode(), consultJson.getHospitalCode());
        System.out.println(msgCode);

        String remarks;
        String responseDesc;
        if (Integer.valueOf("0").compareTo(msgCode) == 0) {
            c.setApprovalNo(generateID("APPROVALNO"));
            c.setCallerId(generateID("CALLERID"));
            c.setBatchCode(generateID("BATCHNO"));
            c.setMemberCode(consultJson.getMemberCode());
            c.setActionTaken(0);
            c.setType(2);
            remarks = "BASIC TESTS";

            //gmeneses - 02/28 -added procedure amount for consultation
            try {
                /*String outpatientAmount = env.getProperty("basictest.amount");*/
                BigDecimal procAmount = BigDecimal.valueOf(getCostByTransactionType("BASIC TEST"));
                c.setProcedureAmount(procAmount);
                c.setProcedureAmt(procAmount);
                //end edit
            } catch (Exception e) {
            }

            c.setRemarks(remarks);
            Integer requestHasSimilar = checkForDuplicateRequest(c);
            switch (requestHasSimilar) {
                case 0:
                    saveTransactionForCall(c);
                    customerService.saveTransactionForCall(c);
                    break;
                case 1:
                    storeInTempTransaction(c);
                    break;
                case 2:
                    break;
            }
        } else {
            GetResponseEntityValuesInteger getResponseEntityValuesInteger = new GetResponseEntityValuesInteger(msgCode).invoke();
            responseDesc = getResponseEntityValuesInteger.getResponseDesc();

            //Call Service to save transaction for call log
            remarks = "Please call 841-8080 for approval.";
            c.setRemarks("Basic Tests - " + remarks);
            c.setNotes(responseDesc);
            c.setActionTaken(1);
            c.setType(2);
            c.setBatchCode(generateID("BATCHNO"));
            c.setCallerId(generateID("CALLERID"));
            Integer requestHasSimilar = checkForDuplicateRequest(c);
            switch (requestHasSimilar) {
                case 0:
                    saveTransactionForCall(c);
                    customerService.saveTransactionForCall(c);
                    break;
                case 1:
                    storeInTempTransaction(c);
                    break;
                case 2:
                    break;
            }
        }

        //Check if company is blacklisted, call service to check blacklisted company
        boolean isBlacklisted = checkifCompanyIsBlacklisted(memberDetails.getACCOUNT_CODE(), "Consultation");
        System.out.println("isBlacklisted:" + isBlacklisted);

        if (isBlacklisted) {
            c.setRemarks("Basic Tests - ");
            return getResponseEntityForBlacklist(c);
        }
        return c;
    }

    public CustomerCare requestLOAOtherTest(MemberDetails memberDetails, OtherTestJson otherTestJson) {
        Integer msgCode = loaService.requestApprovalProcedure(otherTestJson.getMemberCode(), otherTestJson.getHospitalCode());

        String responseDesc = "";
        String approvalNo = "";
        String remarks = "";

        CustomerCare c = initCustomerCare(memberDetails, otherTestJson.getHospitalCode(),
                otherTestJson.getDoctorCode(), otherTestJson.getDiagnosisCode(), otherTestJson.getProcedureCode(),
                otherTestJson.getTotalProcAmount(), otherTestJson.getLocationCode(), otherTestJson.getUsername(),
                otherTestJson.getProcedureDesc(),
                otherTestJson.getMemberCode(),
                otherTestJson.getDiagnosisDesc(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                otherTestJson.getPrimaryComplaint(),
                null);

        if (Integer.valueOf("0").compareTo(msgCode) != 0) {
            GetResponseEntityValuesInteger getResponseEntityValuesInteger = new GetResponseEntityValuesInteger(msgCode).invoke();
            responseDesc = getResponseEntityValuesInteger.getResponseDesc();

            c.setType(2);
            c.setActionTaken(1);
            c.setNotes(responseDesc);
            c.setRemarks("Other Tests - Please call 841-8080 for approval");
            c.setBatchCode(generateID("BATCHNO"));
            c.setCallerId(generateID("CALLERID"));
            Integer requestHasSimilar = checkForDuplicateRequest(c);
            switch (requestHasSimilar) {
                case 0:
                    saveTransactionForCall(c);
                    customerService.saveTransactionForCall(c);
                    break;
                case 1:
                    storeInTempTransaction(c);
                    break;
                case 2:
                    break;
            }
        } else {
            //Check if company is blacklisted, call service to check blacklisted company
            boolean isBlacklisted = checkifCompanyIsBlacklisted(memberDetails.getACCOUNT_CODE(), "Consultation");
            System.out.println("isBlacklisted:" + isBlacklisted);

            if (isBlacklisted) {
                c.setRemarks("Other Tests - ");
                return getResponseEntityForBlacklist(c);
            }

            System.out.println(otherTestJson.getProcedureList() != null);
            System.out.println(otherTestJson.getProcedureList().size());

            BigDecimal remainingLimit = new BigDecimal(claimsService.getRemainingLimit(otherTestJson.getMemberCode()));
            OtherLimit otherLimit = getOtherLimit();
            //BigDecimal innerLimit = maceService.getInnerLimit(otherTestJson.getProcedureCode());
            Boolean isPecEqualToDdl = memService.isPecEqualToDdl(otherTestJson.getMemberCode());
            //Inner Limit is retrieved here
            boolean withinLimit = loaService.validateLimit(otherTestJson.getTotalProcAmount(), otherLimit, remainingLimit, isPecEqualToDdl);
            if (withinLimit) {
                String callerId = generateID("CALLERID");
                String batchCode = generateID("BATCHNO");
                approvalNo = generateID("APPROVALNO");
                remarks = "OTHER TESTS";

                String diagnosisCodeListConcatenate = otherTestJson.getDiagnosisCode() + "|" + concatenateDiagnosisCodeStringList(otherTestJson.getDiagnosisList());
                String procedureCodeListConcatenate = otherTestJson.getProcedureCode() + "|" + concatenateProcedureCodeStringList(otherTestJson.getProcedureList());
                String procedureDescListConcatenate = otherTestJson.getDiagnosisDesc() + "|" + concatenateProcedureDescStringList(otherTestJson.getProcedureList());

                c = initCustomerCare(memberDetails, otherTestJson.getHospitalCode(),
                        otherTestJson.getDoctorCode(), diagnosisCodeListConcatenate, procedureCodeListConcatenate,
                        otherTestJson.getTotalProcAmount(), otherTestJson.getLocationCode(), otherTestJson.getUsername(),
                        procedureDescListConcatenate,
                        otherTestJson.getMemberCode(),
                        otherTestJson.getDiagnosisDesc(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        otherTestJson.getPrimaryComplaint(),
                        null);

                //Call Service to save transaction for call log
                c.setRemarks(remarks);
                c.setActionTaken(0);
                c.setType(2);
                c.setCallerId(callerId);
                c.setBatchCode(batchCode);
                c.setApprovalNo(approvalNo);
                Integer requestHasSimilar = checkForDuplicateRequest(c);
                switch (requestHasSimilar) {
                    case 0:
                        c = saveTransaction(c, memberDetails);
                        c = customerService.saveTransaction(c, memberDetails);
                        break;
                    case 1:
                        storeInTempTransaction(c);
                        break;
                    case 2:
                        break;
                }
            } else {
                remarks = "Other Tests - Please call 841-8080 for approval";
                responseDesc = "Limit Exceeded. Please call 841-8080 for approval.";
                c.setRemarks(remarks);
                c.setActionTaken(1);
                c.setType(2);
                c.setNotes(responseDesc);
                c.setBatchCode(generateID("BATCHNO"));
                c.setCallerId(generateID("CALLERID"));
                Integer requestHasSimilar = checkForDuplicateRequest(c);
                switch (requestHasSimilar) {
                    case 0:
                        saveTransactionForCall(c);
                        customerService.saveTransactionForCall(c);
                        break;
                    case 1:
                        storeInTempTransaction(c);
                        break;
                    case 2:
                        break;
                }
            }
        }
        return c;
    }

    private CustomerCare getResponseEntityForBlacklist(CustomerCare c) {
        String remarks = "Please call 841-8080 for approval";
        String responseDesc = "Company is blacklisted.";

        c.setActionTaken(1);
        c.setRemarks(c.getRemarks().concat(remarks));
        c.setNotes(responseDesc);
        c.setBatchCode(generateID("BATCHNO"));
        c.setCallerId(generateID("CALLERID"));

        saveTransactionForCall(c);
        customerService.saveTransactionForCall(c);

        return c;
    }

    private HashMap<String, Object> getResponseEntityForBlacklist(HashMap<String, Object> response, CustomerCare c) {
        String remarks;
        String responseCode;
        String responseDesc;
        remarks = "Please call 841-8080 for approval";
        responseCode = "205";
        responseDesc = "Company is blacklisted";

        response.put("remarks", remarks);
        response.put("responseCode", responseCode);
        response.put("responseDesc", responseDesc);
        c.setActionTaken(1);
        c.setRemarks(remarks);
        c.setBatchCode(this.generateID("BATCHNO"));
        c.setCallerId(this.generateID("CALLERID"));
        this.saveTransactionForCall(c);
        customerService.saveTransactionForCall(c);
        return response;
    }

    private String concatenateProcedureDescStringList(ArrayList<ProcedureJson> procedureJsonArrayList) {
        String procedureDescConcatenate = "";
        for (ProcedureJson procedureJson : procedureJsonArrayList) {
            procedureDescConcatenate = procedureDescConcatenate + "|" + procedureJson.getProcedureDesc();
        }
        return procedureDescConcatenate;
    }

    private String concatenateProcedureCodeStringList(ArrayList<ProcedureJson> procedureJsonArrayList) {
        String procedureCodeConcatenate = "";
        for (ProcedureJson procedureJson : procedureJsonArrayList) {
            procedureCodeConcatenate = procedureCodeConcatenate + "|" + procedureJson.getProcedureCode();
        }
        return procedureCodeConcatenate;
    }

    private String concatenateDiagnosisCodeStringList(ArrayList<String> diagnosisList) {
        String diagnosisCodeConcatenate = "";
        for (String s : diagnosisList) {
            diagnosisCodeConcatenate = diagnosisCodeConcatenate + "|" + s;
        }
        return diagnosisCodeConcatenate;
    }

    public boolean checkIfMemberExistsByMemberCode(String memberCode) {
        return maceMapper.checkIfMemberExistsByMemberCode(memberCode);
    }

    public DiagnosisEntity getDiagnosisEntity(String diagnosisCode) {
        return maceMapper.getDiagnosisEntity(diagnosisCode);
    }

    public void updateContactNo(String appUsername, String contactNo) {
        logger.info("updateContactNo");
        maceMapper.updateContactNo(appUsername, contactNo);
    }

    public void saveMaceReqIpOtherService(MaceReqInpatient mrip, MaceReqIpOtherservices mrios) {
        mrios.setMaceRequestId(mrip.getMaceRequestId());
        mrios.setTransactionId(mrip.getTransactionId());
        maceMapper.saveMaceReqIpOtherService(mrios);
    }

    public List<MaceRequestOpDiag> getMaceReqOpDiagByMaceReqId(Integer requestId) {
        logger.info("getMaceReqOpDiagByMaceReqId");
        return maceMapper.getMaceReqOpDiagByMaceReqId(requestId);
    }

    public List<MaceRequestOpProcedure> getMaceReqOpProcByMaceReqId(Integer requestId) {
        logger.info("getMaceReqOpProcByMaceReqId");
        return maceMapper.getMaceReqOpProcByMaceReqId(requestId);
    }

    public List<MaceRequestOpTest> getMaceReqOpTestsByMaceReqId(Integer requestId) {
        logger.info("getMaceReqOpTestsByMaceReqId");
        return maceMapper.getMaceReqOpTestsByMaceReqId(requestId);
    }

    public List getMemberLOAList(String hospitalCode, String membercode) {
        List servicesList = new ArrayList();
        List<MaceRequest> requestList = maceMapper.getMemberLOAList(membercode, hospitalCode, null, null, 1, 10);
        System.out.println("Requests : " + requestList.size());
        //Check if LoaList is empty and return empty List
        if (null == requestList || requestList.isEmpty())
            return new ArrayList<>();
        for (MaceRequest req : requestList) {
            MaceRequestReturn reqType = new MaceRequestReturn(req);
            if (reqType.getServiceTypeId() == 1) {
                MaceRequestReturn request = new MaceRequestReturn(req);
                MaceReqConsult consult = maceMapper.getMaceReqConsult(request.getRequestId());
                if (consult != null) {
                    consult.setServiceSubType(Constants.SubService.getById(consult.getConsultSubtype().longValue()).getValue());
//                    consult.setMaceRequest(request);
                    request.setApprovalNo(consult.getReferenceNo());
                    request.setRequestType(consult.getConsultSubtype() == 1 ? "CONSULTATION" : "MATERNITY CONSULTATION");
                    request.setPrimaryDiag(consult.getPrimaryDiagnosisDesc());
                    request.setReasonForConsult(consult.getConsultReason());
                    request.setRequestTypeDetail02(consult.getPrimaryDiagnosisDesc());
                    if (consult.getConsultationDate() != null) {
                        request.setRequestTypeDetail03(new SimpleDateFormat("MMM dd, yyyy hh:mm").format(consult.getConsultationDate()));//consult.getConsultationDate().toString());
                    }
                    Doctor doc = docHospMapper.getDoctor(consult.getDoctorCode(), false);
                    request.setRequestTypeDetail01(consult.getDoctorName());
                    request.setDoctorName(doc.getFullName());
                    request.setDoctorSpec(doc.getSpecDesc());
                    servicesList.add(request);
                }
            } else if (reqType.getServiceTypeId() == 2) {
                /**
                 * Jabito - 08/16/2017
                 * Fixed return for request Loa with fixed list of diagnosis to procedure mapping.
                 * */
                //Collection of MaceReqTests for one MaceRequest Code
                MaceRequestReturn request = new MaceRequestReturn(req);
                List<MaceRequestTest> mrts = maceMapper.getMaceRequestTests(request.getRequestId());
                //Group Tests By Cost Center
                Map<String, List<MaceRequestTest>> mrtList = mrts.stream()
                        .collect(Collectors.groupingBy(MaceRequestTest::getCostCenter, Collectors.toList()));
                BigDecimal totalAmount = BigDecimal.ZERO;
                MaceRequestReturn.GroupedByCostCenter[] gbccList = new MaceRequestReturn.GroupedByCostCenter[mrtList.size()];
                request.setRequestType("FILE UPLOAD");
                List<MaceRequestAttachment> attachments = maceMapper.getMaceAttachmentsByReqCode(request.getRequestCode());
                request.setAttachments(attachments);

                int indX = 0;
                //Looping through Cost Center List
                for (Map.Entry<String, List<MaceRequestTest>> byMrt : mrtList.entrySet()) {
                    MaceRequestReturn.GroupedByCostCenter gbcc = new MaceRequestReturn.GroupedByCostCenter();
                    MaceRequestTest mrtMain = byMrt.getValue().get(0);
                    request.setRequestType(mrtMain.getTestSubtype() == 3 ? "BASIC TEST" : "OTHER TEST");
//                    gbcc.setApprovalNo(mrtMain.getApprovalNo());
                    gbcc.setStatus(mrtMain.getStatus());
                    gbcc.setCostCenter(mrtMain.getCostCenter());
                    MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[] gbdList = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[byMrt.getValue().size()];
                    int indY = 0;
                    //Looping through Each Entry in MaceReq_Test
                    for (MaceRequestTest mrt : byMrt.getValue()) {
                        MaceRequestReturn.GroupedByCostCenter.GroupedByDiag gbd = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag();
                        MaceRequestOpDiag mrod = maceMapper.getMaceReqOpDiagByTestTransactionId(Integer.valueOf(String.valueOf(mrt.getTransactionId())));
                        gbd.setApprovalNo(mrt.getApprovalNo());
                        gbd.setDiagType(mrod.getMaceDiagType());
                        gbd.setDiagDesc(mrod.getDiagDesc());
                        if (mrod.getMaceDiagType() == 1) {
                            request.setPrimaryDiag(mrod.getDiagDesc());
                        }

                        List<MaceRequestOpTest> mrots = maceMapper.getMaceReqOpTestByReqDiagId(mrod.getReqDiagId());
                        MaceRequestReturn.MappedTest[] mtList = new MaceRequestReturn.MappedTest[mrots.size()];
                        int indZ = 0;
                        //Loop through Each Test
                        for (MaceRequestOpTest mrot : mrots) {
                            MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                            mt.setProcCode(mrot.getProcCode());
                            mt.setProcDesc(mrot.getProcDesc());
                            mt.setCostCenter(mrt.getCostCenter());
                            mt.setDiagType(mrod.getMaceDiagType());
                            mt.setAmount(mrot.getAmount());
                            mtList[indZ++] = mt;
                        }
                        gbd.setMappedTests(mtList);
                        gbdList[indY++] = gbd;
                        totalAmount = totalAmount.add(mrt.getTransamount());
                    }
                    gbcc.setStatus(mrtMain.getStatus());
                    gbcc.setSubTotal(mrtMain.getTransamount());
                    gbcc.setGroupedByDiag(gbdList);
                    gbccList[indX++] = gbcc;
                }
                if (mrts.size() > 0) {
                    Doctor doc = docHospMapper.getDoctor(mrts.get(0).getDoctorCode(), false);
                    if (doc != null) {
                        request.setDoctorName(doc.getFullName());
                        request.setDoctorSpec(doc.getSpecDesc());
                    }
                }
                request.setTotalAmount(totalAmount);
                request.setGroupedByCostCenters(gbccList);
                servicesList.add(request);
            } else if (reqType.getServiceTypeId() == 3) {
                /**
                 * Jabito - 08/16/2017
                 * Fixed return for request Loa with fixed list of diagnosis to procedure mapping.
                 * */
                MaceRequestReturn request = new MaceRequestReturn(req);
                List<MaceRequestProcedure> proc = maceMapper.getMaceRequestProcedures(reqType.getRequestId().intValue());
                BigDecimal total = BigDecimal.ZERO;
                request.setRequestType("PROCEDURE");
                if (proc.size() > 0) {
                    MaceRequestProcedure mrpOrig = proc.get(0);
//                        mrpOrig.setMaceRequest(request);
                    request.setRequestTypeDetail01(mrpOrig.getApprovalNo());
                    request.setApprovalNo(mrpOrig.getApprovalNo());
                    request.setPrimaryDiag(mrpOrig.getPrimaryDiagnosisDesc());
                    request.setReasonForConsult(mrpOrig.getConsultReason());
                    request.setDiagType(1);
                    //Add all tests and get Type
                    String tests = "";
                    MaceRequestOpDiag mrod = maceMapper.getMaceReqOpDiagByTestTransactionId(Integer.valueOf(String.valueOf(mrpOrig.getTransactionId())));
                    request.setDiagnosis(mrod.getDiagDesc());
                    request.setPrimaryDiag(mrod.getDiagType().equals("1") ? mrod.getDiagDesc() : "");
                    MaceRequestReturn.MappedTest[] mappedTestList = new MaceRequestReturn.MappedTest[proc.size()];
                    int x = 0;
                    for (MaceRequestProcedure mrt : proc) {
                        MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                        mt.setProcDesc(mrt.getProcDesc());
                        mt.setNotes(mrt.getNotes());
                        mt.setDiagType(1);
                        mt.setCostCenter(mrt.getCostCenter());
                        MaceRequestOpProcedure mrot = maceMapper.getMaceReqOpProcedureByProcedureTransId(Integer.valueOf(String.valueOf(mrt.getTransactionId())));
                        mt.setProcCode(mrot.getProcCode());
                        mt.setAmount(mrot.getAmount());
                        tests += mrt.getProcDesc() + "|";
                        total = total.add(mt.getAmount());
                        mappedTestList[x++] = mt;
                    }

                    request.setMappedTest(mappedTestList);
                    request.setRequestTypeDetail01(diagProcMapper.getCostCenterByMRPTransactionId(mrpOrig.getTransactionId()));
                    request.setRequestTypeDetail03(tests);
                    Doctor doc = docHospMapper.getDoctor(mrpOrig.getDoctorCode(), false);
                    if (doc != null) {
                        request.setDoctorName(doc.getFullName());
                        request.setDoctorSpec(doc.getSpecDesc());
                    }
                }
                request.setTotalAmount(total);
                servicesList.add(request);
            }
        }
        return servicesList;

    }

    public List getMemberFilteredLOAList(MemberLoaFilter loaFilter) {
        List servicesList = new LinkedList();
        if (null != loaFilter.getHospitalCode() && loaFilter.getHospitalCode().equalsIgnoreCase(""))
            loaFilter.setHospitalCode(null);
        if (null != loaFilter.getStatus() && loaFilter.getStatus().equalsIgnoreCase(""))
            loaFilter.setStatus(null);
        if (null != loaFilter.getDoctorCode() && loaFilter.getDoctorCode().equalsIgnoreCase(""))
            loaFilter.setDoctorCode(null);
//        if(loaFilter.getStartDate().equals(""))
//            loaFilter.setStartDate(null);
//        if(loaFilter.getEndDate().equals(""))
//            loaFilter.setEndDate(null);
        if (null != loaFilter.getDiagCode() && loaFilter.getDiagCode().equalsIgnoreCase(""))
            loaFilter.setDiagCode(null);
        if (null != loaFilter.getProcCode() && loaFilter.getProcCode().equalsIgnoreCase(""))
            loaFilter.setProcCode(null);
        List<MaceRequest> requestList = maceMapper.getMemberFilteredLoaList(loaFilter);
        MaceRequestReturn request;
        for (MaceRequest req : requestList) {
            request = new MaceRequestReturn(req);
            Hospital hosp = docHospMapper.getHospital(request.getRequestFromhosp());
            request.setHospitalName(null != hosp ? hosp.getHospitalName() : "");
            request.setHospitalAddress(null != hosp ? hosp.getStreetAddress() : "");
            request.setHospitalContact(null != hosp ? hosp.getPhoneNo() : "");

            if (!request.getStatus().equalsIgnoreCase("draft")) {
                switch (request.getServiceTypeId()) {
                    case 1:
                        MaceReqConsult consult = maceMapper.getMaceReqConsult(request.getRequestId());
                        if (consult != null) {
                            consult.setServiceSubType(Constants.SubService.getById(consult.getConsultSubtype().longValue()).getValue());
//                    consult.setMaceRequest(request);
                            request.setApprovalNo(consult.getReferenceNo());
                            request.setRequestType(consult.getConsultSubtype() == 1 ? "CONSULTATION" : "MATERNITY CONSULTATION");
                            request.setPrimaryDiag(consult.getPrimaryDiagnosisDesc());
                            request.setReasonForConsult(consult.getConsultReason());
                            request.setRequestTypeDetail02(consult.getPrimaryDiagnosisDesc());
                            if (consult.getConsultationDate() != null) {
                                request.setRequestTypeDetail03(new SimpleDateFormat("MMM dd, yyyy hh:mm").format(consult.getConsultationDate()));//consult.getConsultationDate().toString());
                            }
                            Doctor doc = docHospMapper.getDoctor(consult.getDoctorCode(), true);
                            if (null != doc) {
                                request.setRequestTypeDetail01(doc.getFullName());
                                request.setDoctorName(doc.getFullName());
                                request.setDoctorSpec(doc.getSpecDesc());
                            }
                            servicesList.add(request);
                        }
                        break;
                    case 2:
                        /**
                         * Jabito - 08/16/2017
                         * Fixed return for request Loa with fixed list of diagnosis to procedure mapping.
                         * */
                        //Collection of MaceReqTests for one MaceRequest Code
                        request = new MaceRequestReturn(req);
                        List<MaceRequestTest> mrts = maceMapper.getMaceRequestTests(request.getRequestId());
                        //Group Tests By Cost Center
                        Map<String, List<MaceRequestTest>> mrtList = mrts.stream()
                                .collect(Collectors.groupingBy(MaceRequestTest::getCostCenter, Collectors.toList()));
                        BigDecimal totalAmount = BigDecimal.ZERO;
                        MaceRequestReturn.GroupedByCostCenter[] gbccList = new MaceRequestReturn.GroupedByCostCenter[mrtList.size()];
                        int indX = 0;
                        //Looping through Cost Center List
                        request.setRequestType("FILE UPLOAD");
                        List<MaceRequestAttachment> attachments = maceMapper.getMaceAttachmentsByReqCode(request.getRequestCode());
                        request.setAttachments(attachments);
                        for (Map.Entry<String, List<MaceRequestTest>> byMrt : mrtList.entrySet()) {
                            MaceRequestReturn.GroupedByCostCenter gbcc = new MaceRequestReturn.GroupedByCostCenter();
                            MaceRequestTest mrtMain = byMrt.getValue().get(0);
                            request.setRequestType(mrtMain.getTestSubtype() == 3 ? "BASIC TEST" : "OTHER TEST");
//                    gbcc.setApprovalNo(mrtMain.getApprovalNo());
                            gbcc.setStatus(mrtMain.getStatus());
                            gbcc.setCostCenter(mrtMain.getCostCenter());
                            MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[] gbdList = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[byMrt.getValue().size()];
                            int indY = 0;
                            //Looping through Each Entry in MaceReq_Test
                            for (MaceRequestTest mrt : byMrt.getValue()) {
                                MaceRequestReturn.GroupedByCostCenter.GroupedByDiag gbd = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag();
                                MaceRequestOpDiag mrod = maceMapper.getMaceReqOpDiagByTestTransactionId(Integer.valueOf(String.valueOf(mrt.getTransactionId())));
                                gbd.setApprovalNo(mrt.getApprovalNo());
                                gbd.setDiagType(mrod.getMaceDiagType());
                                gbd.setDiagDesc(mrod.getDiagDesc());
                                if (mrod.getMaceDiagType() == 1) {
                                    request.setPrimaryDiag(mrod.getDiagDesc());
                                }

                                List<MaceRequestOpTest> mrots = maceMapper.getMaceReqOpTestByReqDiagId(mrod.getReqDiagId());
                                MaceRequestReturn.MappedTest[] mtList = new MaceRequestReturn.MappedTest[mrots.size()];
                                int indZ = 0;
                                //Loop through Each Test
                                for (MaceRequestOpTest mrot : mrots) {
                                    MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                                    mt.setProcCode(mrot.getProcCode());
                                    mt.setProcDesc(mrot.getProcDesc());
                                    mt.setCostCenter(mrt.getCostCenter());
                                    mt.setDiagType(mrod.getMaceDiagType());
                                    mt.setAmount(mrot.getAmount());
                                    mtList[indZ++] = mt;
                                }
                                gbd.setMappedTests(mtList);
                                gbdList[indY++] = gbd;
                                totalAmount = totalAmount.add(mrt.getTransamount());
                            }
                            gbcc.setStatus(mrtMain.getStatus());
                            gbcc.setSubTotal(mrtMain.getTransamount());
                            gbcc.setGroupedByDiag(gbdList);
                            gbccList[indX++] = gbcc;
                        }
                        if (mrts.size() > 0) {
                            Doctor doc = docHospMapper.getDoctor(mrts.get(0).getDoctorCode(), true);
                            if (doc != null) {
                                request.setDoctorName(doc.getFullName());
                                request.setDoctorSpec(doc.getSpecDesc());
                            }
                        }
                        request.setTotalAmount(totalAmount);
                        request.setGroupedByCostCenters(gbccList);
                        servicesList.add(request);
                        break;
                    case 3:
                        /**
                         * Jabito - 08/16/2017
                         * Fixed return for request Loa with fixed list of diagnosis to procedure mapping.
                         * */
                        request = new MaceRequestReturn(req);
                        List<MaceRequestProcedure> proc = maceMapper.getMaceRequestProcedures(request.getRequestId().intValue());
                        BigDecimal total = BigDecimal.ZERO;
                        request.setRequestType("PROCEDURE");
                        if (proc.size() > 0) {
                            MaceRequestProcedure mrpOrig = proc.get(0);
//                        mrpOrig.setMaceRequest(request);
                            request.setRequestTypeDetail01(mrpOrig.getApprovalNo());
                            request.setApprovalNo(mrpOrig.getApprovalNo());
                            request.setPrimaryDiag(mrpOrig.getPrimaryDiagnosisDesc());
                            request.setReasonForConsult(mrpOrig.getConsultReason());
                            request.setDiagType(1);
                            //Add all tests and get Type
                            String tests = "";
                            MaceRequestOpDiag mrod = maceMapper.getMaceReqOpDiagByTestTransactionId(Integer.valueOf(String.valueOf(mrpOrig.getTransactionId())));
                            request.setDiagnosis(mrod.getDiagDesc());
                            MaceRequestReturn.MappedTest[] mappedTestList = new MaceRequestReturn.MappedTest[proc.size()];
                            int x = 0;
                            for (MaceRequestProcedure mrt : proc) {
                                MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                                mt.setProcDesc(mrt.getProcDesc());
                                mt.setNotes(mrt.getNotes());
                                mt.setDiagType(1);
                                mt.setCostCenter(mrt.getCostCenter());
                                MaceRequestOpProcedure mrot = maceMapper.getMaceReqOpProcedureByProcedureTransId(Integer.valueOf(String.valueOf(mrt.getTransactionId())));
                                mt.setProcCode(mrot.getProcCode());
                                mt.setAmount(mrot.getAmount());
                                tests += mrt.getProcDesc() + "|";
                                total = total.add(mt.getAmount());
                                mappedTestList[x++] = mt;
                            }
                            request.setMappedTest(mappedTestList);
                            request.setRequestTypeDetail01(diagProcMapper.getCostCenterByMRPTransactionId(mrpOrig.getTransactionId()));
                            request.setRequestTypeDetail03(tests);
                            Doctor doc = docHospMapper.getDoctor(mrpOrig.getDoctorCode(), true);
                            if (doc != null) {
                                request.setDoctorName(doc.getFullName());
                                request.setDoctorSpec(doc.getSpecDesc());
                            }
                        }
                        request.setTotalAmount(total);
                        servicesList.add(request);
                        break;
                    case 6: // OP/OR requests
                        request = new MaceRequestReturn(req);
                        List<MaceRequestTest> tests = maceMapper.getMaceRequestTests(request.getRequestId());
                        List<MaceRequestProcedure> procs = maceMapper.getMaceRequestProcedures(request.getRequestId());

                        Map<String, List<MaceRequestTest>> opTestList = tests.stream()
                                .collect(Collectors.groupingBy(MaceRequestTest::getCostCenter, Collectors.toList()));
                        Map<String, List<MaceRequestProcedure>> opProcList = procs.stream()
                                .collect(Collectors.groupingBy(MaceRequestProcedure::getCostCenter, Collectors.toList()));
                        System.out.println("SIZE" + tests.size() + " " + procs.size());
                        MaceRequestReturn.GroupedByCostCenter[] costCenterList = new MaceRequestReturn.GroupedByCostCenter[opTestList.size() + opProcList.size()];
                        request.setRequestType("OP/OR");
                        totalAmount = BigDecimal.ZERO;
                        int x = 0;
                        for (Map.Entry<String, List<MaceRequestTest>> byCostCenterMrt : opTestList.entrySet()) {
                            MaceRequestReturn.GroupedByCostCenter gbcc = new MaceRequestReturn.GroupedByCostCenter();
                            MaceRequestTest mrtMain = byCostCenterMrt.getValue().get(0);
                            gbcc.setStatus(mrtMain.getStatus());
                            gbcc.setCostCenter(byCostCenterMrt.getKey());
                            MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[] gbdList = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[byCostCenterMrt.getValue().size()];
                            int y = 0;
                            for (MaceRequestTest mrTest : byCostCenterMrt.getValue()) {
                                MaceRequestReturn.GroupedByCostCenter.GroupedByDiag gbd = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag();
                                MaceRequestOpDiag mrod = maceMapper.getMaceReqOpDiagByTestTransactionId((int) mrTest.getTransactionId());
                                gbd.setApprovalNo(mrTest.getApprovalNo());
                                gbd.setDiagType(mrod.getMaceDiagType());
                                gbd.setDiagDesc(mrod.getDiagDesc());
                                if (mrod.getMaceDiagType() == 1)
                                    request.setPrimaryDiag(mrod.getDiagDesc());

                                List<MaceRequestOpTest> mrots = maceMapper.getMaceReqOpTestByReqDiagId(mrod.getReqDiagId());
                                MaceRequestReturn.MappedTest[] mtList = new MaceRequestReturn.MappedTest[mrots.size()];
                                int z = 0;
                                for (MaceRequestOpTest mrot : mrots) {
                                    MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                                    mt.setProcCode(mrot.getProcCode());
                                    mt.setProcDesc(mrot.getProcDesc());
                                    mt.setCostCenter(mrTest.getCostCenter());
                                    mt.setDiagType(mrod.getMaceDiagType());
                                    mt.setAmount(mrot.getAmount());
                                    mtList[z++] = mt;
                                }
                                gbd.setMappedTests(mtList);
                                gbdList[y++] = gbd;
                                totalAmount = totalAmount.add(mrTest.getTransamount());
                            }
                            gbcc.setStatus(mrtMain.getStatus());
                            gbcc.setSubTotal(mrtMain.getTransamount());
                            gbcc.setGroupedByDiag(gbdList);
                            costCenterList[x++] = gbcc;
                        }
                        for (Map.Entry<String, List<MaceRequestProcedure>> byCostCenterMrp : opProcList.entrySet()) {
                            MaceRequestReturn.GroupedByCostCenter gbcc = new MaceRequestReturn.GroupedByCostCenter();
                            MaceRequestProcedure mrtMain = byCostCenterMrp.getValue().get(0);
                            gbcc.setStatus(mrtMain.getStatus());
                            gbcc.setCostCenter(byCostCenterMrp.getKey());
                            MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[] gbdList = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[byCostCenterMrp.getValue().size()];
                            int y = 0;
                            for (MaceRequestProcedure mrProc : byCostCenterMrp.getValue()) {
                                MaceRequestReturn.GroupedByCostCenter.GroupedByDiag gbd = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag();
                                MaceRequestOpDiag mrod = maceMapper.getMaceReqOpDiagByTestTransactionId((int) mrProc.getTransactionId());
                                gbd.setApprovalNo(mrProc.getApprovalNo());
                                gbd.setDiagType(mrod.getMaceDiagType());
                                gbd.setDiagDesc(mrod.getDiagDesc());
                                if (mrod.getMaceDiagType() == 1)
                                    request.setPrimaryDiag(mrod.getDiagDesc());

                                List<MaceRequestOpTest> mrots = maceMapper.getMaceReqOpTestByReqDiagId(mrod.getReqDiagId());
                                MaceRequestReturn.MappedTest[] mpList = new MaceRequestReturn.MappedTest[mrots.size()];
                                int z = 0;
                                for (MaceRequestOpTest mrot : mrots) {
                                    MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                                    mt.setProcCode(mrot.getProcCode());
                                    mt.setProcDesc(mrot.getProcDesc());
                                    mt.setCostCenter(mrProc.getCostCenter());
                                    mt.setDiagType(mrod.getMaceDiagType());
                                    mt.setAmount(mrot.getAmount());
                                    mpList[z++] = mt;
                                }
                                gbd.setMappedTests(mpList);
                                gbdList[y++] = gbd;
                                totalAmount = totalAmount.add(mrProc.getTransamount());
                            }
                            gbcc.setStatus(mrtMain.getStatus());
                            gbcc.setSubTotal(mrtMain.getTransamount());
                            gbcc.setGroupedByDiag(gbdList);
                            costCenterList[x++] = gbcc;
                        }
                        request.setGroupedByCostCenters(costCenterList);
                        request.setTotalAmount(totalAmount);
                        servicesList.add(request);
                        break;
                    default:
                        break;
                }
            }
        }
        return servicesList;
    }

    public List getMemberLOAList(String membercode) {
        List servicesList = new LinkedList();
        List<MaceRequest> requestList = maceMapper.getMemberLOAListNoHospital(membercode, null, null, 1, 10);
        MaceRequestReturn request;
        for (MaceRequest req : requestList) {
            request = new MaceRequestReturn(req);
            Hospital hosp = docHospMapper.getHospital(request.getRequestFromhosp());
            request.setHospitalName(null != hosp ? hosp.getHospitalName() : "");
            request.setHospitalAddress(null != hosp ? hosp.getStreetAddress() : "");
            request.setHospitalContact(null != hosp ? hosp.getPhoneNo() : "");

            switch (request.getServiceTypeId()) {
                case 1:
                    MaceReqConsult consult = maceMapper.getMaceReqConsult(request.getRequestId());
                    if (consult != null) {
                        consult.setServiceSubType(Constants.SubService.getById(consult.getConsultSubtype().longValue()).getValue());
//                    consult.setMaceRequest(request);
                        request.setApprovalNo(consult.getReferenceNo());
                        request.setRequestType(consult.getConsultSubtype() == 1 ? "CONSULTATION" : "MATERNITY CONSULTATION");
                        request.setPrimaryDiag(consult.getPrimaryDiagnosisDesc());
                        request.setReasonForConsult(consult.getConsultReason());
                        request.setRequestTypeDetail02(consult.getPrimaryDiagnosisDesc());
                        if (consult.getConsultationDate() != null) {
                            request.setRequestTypeDetail03(new SimpleDateFormat("MMM dd, yyyy hh:mm").format(consult.getConsultationDate()));//consult.getConsultationDate().toString());
                        }
                        Doctor doc = docHospMapper.getDoctor(consult.getDoctorCode(), false);
                        if (null != doc) {
                            request.setRequestTypeDetail01(doc.getFullName());
                            request.setDoctorName(doc.getFullName());
                            request.setDoctorSpec(doc.getSpecDesc());
                        }
                        servicesList.add(request);
                    }
                    break;
                case 2:
                    /**
                     * Jabito - 08/16/2017
                     * Fixed return for request Loa with fixed list of diagnosis to procedure mapping.
                     * */
                    //Collection of MaceReqTests for one MaceRequest Code
                    request = new MaceRequestReturn(req);
                    List<MaceRequestTest> mrts = maceMapper.getMaceRequestTests(request.getRequestId());
                    //Group Tests By Cost Center
                    Map<String, List<MaceRequestTest>> mrtList = mrts.stream()
                            .collect(Collectors.groupingBy(MaceRequestTest::getCostCenter, Collectors.toList()));
                    BigDecimal totalAmount = BigDecimal.ZERO;
                    MaceRequestReturn.GroupedByCostCenter[] gbccList = new MaceRequestReturn.GroupedByCostCenter[mrtList.size()];
                    int indX = 0;
                    //Looping through Cost Center List
                    request.setRequestType("FILE UPLOAD");
                    List<MaceRequestAttachment> attachments = maceMapper.getMaceAttachmentsByReqCode(request.getRequestCode());
                    request.setAttachments(attachments);
                    for (Map.Entry<String, List<MaceRequestTest>> byMrt : mrtList.entrySet()) {
                        MaceRequestReturn.GroupedByCostCenter gbcc = new MaceRequestReturn.GroupedByCostCenter();
                        MaceRequestTest mrtMain = byMrt.getValue().get(0);
                        request.setRequestType(mrtMain.getTestSubtype() == 3 ? "BASIC TEST" : "OTHER TEST");
//                    gbcc.setApprovalNo(mrtMain.getApprovalNo());
                        gbcc.setStatus(mrtMain.getStatus());
                        gbcc.setCostCenter(mrtMain.getCostCenter());
                        MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[] gbdList = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[byMrt.getValue().size()];
                        int indY = 0;
                        //Looping through Each Entry in MaceReq_Test
                        for (MaceRequestTest mrt : byMrt.getValue()) {
                            MaceRequestReturn.GroupedByCostCenter.GroupedByDiag gbd = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag();
                            MaceRequestOpDiag mrod = maceMapper.getMaceReqOpDiagByTestTransactionId(Integer.valueOf(String.valueOf(mrt.getTransactionId())));
                            gbd.setApprovalNo(mrt.getApprovalNo());
                            gbd.setDiagType(mrod.getMaceDiagType());
                            gbd.setDiagDesc(mrod.getDiagDesc());
                            if (mrod.getMaceDiagType() == 1) {
                                request.setPrimaryDiag(mrod.getDiagDesc());
                            }

                            List<MaceRequestOpTest> mrots = maceMapper.getMaceReqOpTestByReqDiagId(mrod.getReqDiagId());
                            MaceRequestReturn.MappedTest[] mtList = new MaceRequestReturn.MappedTest[mrots.size()];
                            int indZ = 0;
                            //Loop through Each Test
                            for (MaceRequestOpTest mrot : mrots) {
                                MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                                mt.setProcCode(mrot.getProcCode());
                                mt.setProcDesc(mrot.getProcDesc());
                                mt.setCostCenter(mrt.getCostCenter());
                                mt.setDiagType(mrod.getMaceDiagType());
                                mt.setAmount(mrot.getAmount());
                                mtList[indZ++] = mt;
                            }
                            gbd.setMappedTests(mtList);
                            gbdList[indY++] = gbd;
                            totalAmount = totalAmount.add(mrt.getTransamount());
                        }
                        gbcc.setStatus(mrtMain.getStatus());
                        gbcc.setSubTotal(mrtMain.getTransamount());
                        gbcc.setGroupedByDiag(gbdList);
                        gbccList[indX++] = gbcc;
                    }
                    if (mrts.size() > 0) {
                        Doctor doc = docHospMapper.getDoctor(mrts.get(0).getDoctorCode(), false);
                        if (doc != null) {
                            request.setDoctorName(doc.getFullName());
                            request.setDoctorSpec(doc.getSpecDesc());
                        }
                    }
                    request.setTotalAmount(totalAmount);
                    request.setGroupedByCostCenters(gbccList);
                    servicesList.add(request);
                    break;
                case 3:
                    /**
                     * Jabito - 08/16/2017
                     * Fixed return for request Loa with fixed list of diagnosis to procedure mapping.
                     * */
                    request = new MaceRequestReturn(req);
                    List<MaceRequestProcedure> proc = maceMapper.getMaceRequestProcedures(request.getRequestId().intValue());
                    BigDecimal total = BigDecimal.ZERO;
                    request.setRequestType("PROCEDURE");
                    if (proc.size() > 0) {
                        MaceRequestProcedure mrpOrig = proc.get(0);
//                        mrpOrig.setMaceRequest(request);
                        request.setRequestTypeDetail01(mrpOrig.getApprovalNo());
                        request.setApprovalNo(mrpOrig.getApprovalNo());
                        request.setPrimaryDiag(mrpOrig.getPrimaryDiagnosisDesc());
                        request.setReasonForConsult(mrpOrig.getConsultReason());
                        request.setDiagType(1);
                        //Add all tests and get Type
                        String tests = "";
                        MaceRequestOpDiag mrod = maceMapper.getMaceReqOpDiagByTestTransactionId(Integer.valueOf(String.valueOf(mrpOrig.getTransactionId())));
                        request.setDiagnosis(mrod.getDiagDesc());
                        MaceRequestReturn.MappedTest[] mappedTestList = new MaceRequestReturn.MappedTest[proc.size()];
                        int x = 0;
                        for (MaceRequestProcedure mrt : proc) {
                            MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                            mt.setProcDesc(mrt.getProcDesc());
                            mt.setNotes(mrt.getNotes());
                            mt.setDiagType(1);
                            mt.setCostCenter(mrt.getCostCenter());
                            MaceRequestOpProcedure mrot = maceMapper.getMaceReqOpProcedureByProcedureTransId(Integer.valueOf(String.valueOf(mrt.getTransactionId())));
                            mt.setProcCode(mrot.getProcCode());
                            mt.setAmount(mrot.getAmount());
                            tests += mrt.getProcDesc() + "|";
                            total = total.add(mt.getAmount());
                            mappedTestList[x++] = mt;
                        }
                        request.setMappedTest(mappedTestList);
                        request.setRequestTypeDetail01(diagProcMapper.getCostCenterByMRPTransactionId(mrpOrig.getTransactionId()));
                        request.setRequestTypeDetail03(tests);
                        Doctor doc = docHospMapper.getDoctor(mrpOrig.getDoctorCode(), false);
                        if (doc != null) {
                            request.setDoctorName(doc.getFullName());
                            request.setDoctorSpec(doc.getSpecDesc());
                        }
                    }
                    request.setTotalAmount(total);
                    servicesList.add(request);
                    break;
                case 6: // OP/OR requests
                    request = new MaceRequestReturn(req);
                    List<MaceRequestTest> tests = maceMapper.getMaceRequestTests(request.getRequestId());
                    List<MaceRequestProcedure> procs = maceMapper.getMaceRequestProcedures(request.getRequestId());

                    Map<String, List<MaceRequestTest>> opTestList = tests.stream()
                            .collect(Collectors.groupingBy(MaceRequestTest::getCostCenter, Collectors.toList()));
                    Map<String, List<MaceRequestProcedure>> opProcList = procs.stream()
                            .collect(Collectors.groupingBy(MaceRequestProcedure::getCostCenter, Collectors.toList()));
                    System.out.println("SIZE" + tests.size() + " " + procs.size());
                    MaceRequestReturn.GroupedByCostCenter[] costCenterList = new MaceRequestReturn.GroupedByCostCenter[opTestList.size() + opProcList.size()];
                    request.setRequestType("OP/OR");
                    totalAmount = BigDecimal.ZERO;
                    int x = 0;
                    for (Map.Entry<String, List<MaceRequestTest>> byCostCenterMrt : opTestList.entrySet()) {
                        MaceRequestReturn.GroupedByCostCenter gbcc = new MaceRequestReturn.GroupedByCostCenter();
                        MaceRequestTest mrtMain = byCostCenterMrt.getValue().get(0);
                        gbcc.setStatus(mrtMain.getStatus());
                        gbcc.setCostCenter(byCostCenterMrt.getKey());
                        MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[] gbdList = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[byCostCenterMrt.getValue().size()];
                        int y = 0;
                        for (MaceRequestTest mrTest : byCostCenterMrt.getValue()) {
                            MaceRequestReturn.GroupedByCostCenter.GroupedByDiag gbd = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag();
                            MaceRequestOpDiag mrod = maceMapper.getMaceReqOpDiagByTestTransactionId((int) mrTest.getTransactionId());
                            gbd.setApprovalNo(mrTest.getApprovalNo());
                            gbd.setDiagType(mrod.getMaceDiagType());
                            gbd.setDiagDesc(mrod.getDiagDesc());
                            if (mrod.getMaceDiagType() == 1)
                                request.setPrimaryDiag(mrod.getDiagDesc());

                            List<MaceRequestOpTest> mrots = maceMapper.getMaceReqOpTestByReqDiagId(mrod.getReqDiagId());
                            MaceRequestReturn.MappedTest[] mtList = new MaceRequestReturn.MappedTest[mrots.size()];
                            int z = 0;
                            for (MaceRequestOpTest mrot : mrots) {
                                MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                                mt.setProcCode(mrot.getProcCode());
                                mt.setProcDesc(mrot.getProcDesc());
                                mt.setCostCenter(mrTest.getCostCenter());
                                mt.setDiagType(mrod.getMaceDiagType());
                                mt.setAmount(mrot.getAmount());
                                mtList[z++] = mt;
                            }
                            gbd.setMappedTests(mtList);
                            gbdList[y++] = gbd;
                            totalAmount = totalAmount.add(mrTest.getTransamount());
                        }
                        gbcc.setStatus(mrtMain.getStatus());
                        gbcc.setSubTotal(mrtMain.getTransamount());
                        gbcc.setGroupedByDiag(gbdList);
                        costCenterList[x++] = gbcc;
                    }
                    for (Map.Entry<String, List<MaceRequestProcedure>> byCostCenterMrp : opProcList.entrySet()) {
                        MaceRequestReturn.GroupedByCostCenter gbcc = new MaceRequestReturn.GroupedByCostCenter();
                        MaceRequestProcedure mrtMain = byCostCenterMrp.getValue().get(0);
                        gbcc.setStatus(mrtMain.getStatus());
                        gbcc.setCostCenter(byCostCenterMrp.getKey());
                        MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[] gbdList = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[byCostCenterMrp.getValue().size()];
                        int y = 0;
                        for (MaceRequestProcedure mrProc : byCostCenterMrp.getValue()) {
                            MaceRequestReturn.GroupedByCostCenter.GroupedByDiag gbd = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag();
                            MaceRequestOpDiag mrod = maceMapper.getMaceReqOpDiagByTestTransactionId((int) mrProc.getTransactionId());
                            gbd.setApprovalNo(mrProc.getApprovalNo());
                            gbd.setDiagType(mrod.getMaceDiagType());
                            gbd.setDiagDesc(mrod.getDiagDesc());
                            if (mrod.getMaceDiagType() == 1)
                                request.setPrimaryDiag(mrod.getDiagDesc());

                            List<MaceRequestOpTest> mrots = maceMapper.getMaceReqOpTestByReqDiagId(mrod.getReqDiagId());
                            MaceRequestReturn.MappedTest[] mpList = new MaceRequestReturn.MappedTest[mrots.size()];
                            int z = 0;
                            for (MaceRequestOpTest mrot : mrots) {
                                MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                                mt.setProcCode(mrot.getProcCode());
                                mt.setProcDesc(mrot.getProcDesc());
                                mt.setCostCenter(mrProc.getCostCenter());
                                mt.setDiagType(mrod.getMaceDiagType());
                                mt.setAmount(mrot.getAmount());
                                mpList[z++] = mt;
                            }
                            gbd.setMappedTests(mpList);
                            gbdList[y++] = gbd;
                            totalAmount = totalAmount.add(mrProc.getTransamount());
                        }
                        gbcc.setStatus(mrtMain.getStatus());
                        gbcc.setSubTotal(mrtMain.getTransamount());
                        gbcc.setGroupedByDiag(gbdList);
                        costCenterList[x++] = gbcc;
                    }
                    request.setGroupedByCostCenters(costCenterList);
                    request.setTotalAmount(totalAmount);
                    servicesList.add(request);
                    break;
                default:
                    break;
            }
        }
        return servicesList;

    }

    public List<MaceRequest> getServiceTypeFilteredMaceRequestsByMemberCode(String memberCode, int serviceType,
                                                                            int subServiceType, int status,
                                                                            int page, int count) {
        if (page <= 0)
            throw new IllegalArgumentException("The parameter `page` should " +
                    "be greater than or equal to 1.");
        if (count <= 0)
            throw new IllegalArgumentException("The parameter `count` should " +
                    "be greater than or equal to 1.");

        String statusStr;
        switch (status) {
            case -1:
                statusStr = "%DISAPPROVED%";
                break;
            case 0:
                statusStr = "%PENDING%";
                break;
            case 1:
                statusStr = "%APPROVED%";
                break;
            case 2:
                statusStr = "%%";
                break;
            default:
                throw new IllegalArgumentException("The parameter `status` should " +
                        "only be equal to -1 (disapproved), 0 (pending), 1 (approved), " +
                        "or 2 (no status filter).");
        }

        int offset = (page - 1) * count;

        if (serviceType == 1) {
            if (subServiceType == 1 || subServiceType == 2)
                return maceMapper.getServiceTypeFilteredMaceRequestsByMemberCodeForConsult(
                        memberCode, serviceType, subServiceType, statusStr, offset, count);
            else
                return maceMapper.getServiceTypeOnlyFilteredMaceRequestsByMemberCodeForConsult(
                        memberCode, serviceType, statusStr, offset, count);
        } else if (serviceType == 2) {
            if (subServiceType == 3 || subServiceType == 4)
                return maceMapper.getServiceTypeFilteredMaceRequestsByMemberCodeForTest(
                        memberCode, serviceType, subServiceType, statusStr, offset, count);
            else
                return maceMapper.getServiceTypeOnlyFilteredMaceRequestsByMemberCodeForTest(
                        memberCode, serviceType, statusStr, offset, count);
        } else if (serviceType != 0) {
            return maceMapper.getServiceTypeOnlyFilteredMaceRequestsByMemberCode(
                    memberCode, serviceType, statusStr, offset, count);
        } else
            return maceMapper.getMaceRequestsByMemberCode(memberCode, statusStr, offset, count);
    }

    public MaceReqConsult getMaceReqConsult(Integer maceRequestId) {
        return maceMapper.getMaceReqConsult(maceRequestId);
    }

    public List getOtherServices() {
        logger.info("getOtherServices");
        return maceMapper.getOtherServices();
    }

    public MaceRequest getMaceRequestByRequestCode(String requestCode) {
        return maceMapper.getMaceRequestByRequestCode(requestCode);
    }

    public MaceRequest getMaceRequestByRequestId(Integer requestId) {
        return maceMapper.getMaceRequestByRequestId(requestId);
    }

    public MaceReqInpatient getMaceReqInpatientByReqId(Integer requestId) {
        return maceMapper.getMaceReqInpatientByReqId(requestId);
    }

    public List getMaceReqIpDiagByReqId(Integer requestId) {
        return maceMapper.getMaceReqIpDiagByReqId(requestId);
    }

    public MaceReqIpRoom getMaceReqIpRoom(Integer maceRequestId, Integer transactionId) {
        return maceMapper.getMaceReqIpRoom(maceRequestId, transactionId);
    }

    public String getRequestCodeByRequestId(Integer requestId) {
        return maceMapper.getRequestCodeByRequestId(requestId);
    }

    public List getMaceReqIpDoctors(Integer requestId) {
        return maceMapper.getMaceReqIpDoctors(requestId);
    }

    public void saveMaceReqConsult(MaceRequest maceRequest, MaceReqConsult mrc) {
        mrc.setMaceRequestId(maceRequest.getRequestId());
        maceMapper.saveMaceReqConsult(mrc);
    }

    public String getLastReqCodeIpAdmitted(String memCode) {
        //Get last mace request by order of approval date
        MaceReqInpatient mri = maceMapper.getLastReqCodeIp(memCode);
        //check if status is discharged
        if (null == mri || "DISCHARGED".equalsIgnoreCase(mri.getStatus())) {
            return null;
        } else {
            if (mri.getDischargedOn() == null) {
                return mri.getRequestCode();
            } else {
                return null;
            }
        }
    }

    public List<MaceRequestTest> getMaceRequestTests(Integer requestId) {
        return maceMapper.getMaceRequestTests(requestId);
    }

    public List<MaceRequestProcedure> getMaceRequestProcedures(Integer requestId) {
        return maceMapper.getMaceRequestProcedures(requestId);
    }

    //Status 0 = Approved 1 = Pending
    public String saveMaceRequestForConsult(MemberDetails memberDetails, CustomerCare c) {
        MaceRequest mr = new MaceRequest(c, memberDetails, 1, c.getRequestOrigin(), c.getUpdatedBy());
        maceMapper.saveMaceRequest(mr);

        //Save MaceRequest Audit Log
        MaceRequestAudit maceRequestAudit = new MaceRequestAudit();
        maceRequestAudit.setLogDateTime(new Date());
        maceRequestAudit.setHostname("");
        maceRequestAudit.setUserId(c.getUpdatedBy());
        maceRequestAudit.setDeviceId(c.getDeviceId());
        maceRequestAudit.setUserType("COORDINATOR");
        maceRequestAudit.setFacility("");
        maceRequestAudit.setFunctionName("requestForConsultation");
        maceRequestAudit.setDetail("Created MaceRequest for requestForConsultation.");
        maceRequestAudit.setTranstype("");
        maceRequestAudit.setMemberId(c.getMemberCode());
        maceRequestAudit.setOldValue("");
        maceRequestAudit.setNewValue("");
        this.saveMaceRequestAuditLog(mr, maceRequestAudit);

        DoctorToHospital doctorToHospital = docHospMapper.getDoctorToHospitalObject(c.getHospitalCode(), c.getDoctorCode(), false);

        MaceReqConsult mrc = new MaceReqConsult(c);
        mrc.setDocHospId(null == doctorToHospital ? 0 : Integer.parseInt(String.valueOf(doctorToHospital.getDocHospId())));
        mrc.setMaceRequestId(mr.getRequestId());
        mrc.setStatus(mr.getStatus());
        mrc.setTransCode(this.generateID("CONSULTREQUEST"));
        maceMapper.saveMaceReqConsult(mrc);
        return maceMapper.getRequestCodeByRequestId(mr.getRequestId());
    }

    //Include results from V2 Approval Engine. Set status assignee of header request, and set status of Consult subrequest.
    //Also, setting consultSubtype of MaceReqConsult should not be based on remarks for V2.
    public MaceInsertOrder saveMaceRequestForConsult(MemberDetails memberDetails, CustomerCare c, ApprovalEngineResult approvalEngineResult, Integer consultSubtype, ConsultJson consultJson) {
        MaceRequest mr = new MaceRequest(c, memberDetails, 1, c.getRequestOrigin(), c.getUpdatedBy());
        //If status assignee is DEFAULT_GROUP, pass null to DB instead.
        mr.setStatusAssignee(!approvalEngineResult.getFinalStatusAssignee().equals(DEFAULT_GROUP) ? approvalEngineResult.getFinalStatusAssignee() : null);
        mr.setHospContact(consultJson.getCoorContact());
        mr.setHospEmail(consultJson.getCoorEmail());
        mr.setRequestByCode(mr.getRequestOrigin().equalsIgnoreCase("MEMBER") ? mr.getRequestFrommem() : mr.getRequestFromhosp());

        DoctorToHospital doctorToHospital = docHospMapper.getDoctorToHospitalObject(c.getHospitalCode(), c.getDoctorCode(), false);

        MaceReqConsult mrc = new MaceReqConsult(c);
        mrc.setConsultSubtype(consultSubtype);
        //If subtype is not 1, it is a maternity consultation request
        mrc.setMaternity((consultSubtype != 1));
        mrc.setDocHospId(null == doctorToHospital ? 0 : Integer.parseInt(String.valueOf(doctorToHospital.getDocHospId())));
        mrc.setStatus(approvalEngineResult.getFinalRequestStatus());
        mrc.setTransCode(this.generateID("CONSULTREQUEST"));

        MaceRequestConsObj mrcObj = new MaceRequestConsObj();
        mrcObj.setMrc(mrc);
        mrcObj.setMraObjs(approvalEngineResult.getMaceRequestApprovals());
        mrcObj.setReasonMessages(approvalEngineResult.getReasonMessages());

        //MaceInsertOrder holds all Mace Objects for an ordered insertion later on for arranged dependencies(PK/FK).
        MaceInsertOrder mio = new MaceInsertOrder();
        mio.setMr(mr);
        mio.setMrcObj(mrcObj);

        return mio;
    }

    public void updateMaceRequest(MaceRequest mr) {
        maceMapper.updateMaceRequest(mr);
    }

    public void updateMaceReqInpatient(MaceReqInpatient mri) {
        maceMapper.updateMaceReqInpatient(mri);
    }

    public List<MaceReqIpOtherservices> getMaceReqIpServices(Integer requestId) {
        List<MaceReqIpOtherservices> services = maceMapper.getMaceReqIpServices(requestId);
        if (null == services) {
            services = new ArrayList<>();
        }
        return services;
    }

    public void updateMaceReqIpDiag(MaceReqIpDiag mrid) {
        maceMapper.updateMaceReqIpDiag(mrid);
    }

    public void deleteMaceReqIpDiag(Integer ipReqdiagId) {
        maceMapper.deleteMaceReqIpDiag(ipReqdiagId);
    }

    public void deleteMaceReqIpDoctor(Integer ipReqdocId) {
        maceMapper.deleteMaceReqIpDoctor(ipReqdocId);
    }

    public void updateMaceReqIpDoctor(MaceReqIpDoctor maceReqIpDoctor) {
    }

    public List<MaceReqIpDiagproc> getMaceReqIpDiagProcs(Integer requestId) {
        return maceMapper.getMaceReqIpDiagProcs(requestId);
    }

    public TestAndProceduresEntity getDiagTestProceduresByCodes(String procedureCode, String diagnosisCode) {
        return diagProcMapper.getDiagTestProceduresByCodes(procedureCode, diagnosisCode);
    }

    public String getMaceReqCodeByMaceReqId(Integer requestId) {
        return maceMapper.getMaceReqCodeByMaceReqId(requestId);
    }

    public Integer getreqIdByRequestCode(String requestCode) {
        return maceMapper.getreqIdByRequestCode(requestCode);
    }

    public MacePrescribedProcedure getPrescribedProcedure(String procedureCode) {
        logger.info("getPrescribedProcedure " + procedureCode);
        return maceMapper.getPrescribedProcedure(procedureCode);
    }

    public void deleteMaceAttachment(Integer id) {
        logger.info("deleteMaceAttachment", "id=" + id);
        maceMapper.deleteMaceAttachment(id);
    }

    public void deleteMaceAttachmentByRequestCode(String requestCode) {
        logger.info("deleteMaceAttachmentByRequestCode", "requestCode=" + requestCode);
        maceMapper.deleteMaceAttachmentByRequestCode(requestCode);
    }

    public MacePrescribedTest getPrescribedTest(String procedureCode) {
        return maceMapper.getPrescribedTest(procedureCode);
    }

    public void saveMacePrescribedTest(MaceConsPrescribedtest mcpt) {
        maceMapper.saveMacePrescribedTest(mcpt);
    }

    public void saveMaceReqOpProcedure(MaceRequest mr, MaceRequestOpProcedure mrop) {
        maceMapper.saveMaceReqOpProcedure(mr, mrop);
    }

    public void saveMaceReqOpTest(MaceRequest mr, MaceRequestOpTest mrot) {
        mrot.setMaceRequestId(mr.getRequestId());
        maceMapper.saveMaceReqOpTest(mrot);
    }

    public void saveMaceRequestProcedure(MaceRequest mr, MaceRequestProcedure mrp) {
        maceMapper.saveMaceReqProcedure(mr, mrp);
    }

    public HashMap<String, Object> addPrescribedTest(AddPrescribedTestJson aptj, MaceRequest mr, MaceReqConsult mrc, DoctorToHospital dth, int diagType) {
        HashMap<String, Object> response = new HashMap<>();
        MaceRequestOpDiag mrod = new MaceRequestOpDiag();
        DiagnosisEntity de = this.getDiagnosisEntity(diagType == 1 ? aptj.getPrimaryDiagnosisCode() : aptj.getOtherDiagnosisCode());
        mrod.setDiagnosisEntity(de);
        mrod.setMaceRequestId(mr.getRequestId());
        mrod.setTransactionId(mrc.getTransactionId());
        mrod.setMaceDiagType(diagType);
        this.saveMaceRequestOpDiag(mrod);

        for (String procedureCode : diagType == 1 ? aptj.getPrimaryDiagProcedures() : aptj.getOtherDiagProcedures()) {
            MacePrescribedProcedure pp = this.getPrescribedProcedure(procedureCode);
            MacePrescribedTest pt = this.getPrescribedTest(procedureCode);
            MaceConsPrescribedtest mcpt = new MaceConsPrescribedtest();
            mcpt.setMacerequestId(mr.getRequestId());
            mcpt.setConsTransactionId(Integer.parseInt(String.valueOf(mrc.getTransactionId())));
            if (null != pp) {
                mcpt.setProcCode(pp.getProcedureCode());
                mcpt.setProcDesc(pp.getProcedureDesc());
                mcpt.setProcClass(pp.getProclassCode());
                mcpt.setMaceSubtype(0);
            } else if (null != pt) {
                mcpt.setProcCode(pt.getProcedureCode());
                mcpt.setProcDesc(pt.getProcedureDesc());
                mcpt.setGroup(pt.getProcedureGroup());
                mcpt.setMaceSubtype(pt.getApprovalType().equalsIgnoreCase("auto") ? 3 : 4);
            }
            this.saveMacePrescribedTest(mcpt);
            if (null != pp) {
                MaceRequestProcedure mrp = new MaceRequestProcedure();
                mrp.setRequestFrom(mr.getMemCode());
                mrp.setStatus("Approved automatically.");
                mrp.setConsultReason(mrc.getConsultReason());
                mrp.setDoctorCode(aptj.getDoctorCode());
                mrp.setHospitalCode(aptj.getHospitalCode());
                mrp.setPrimaryDiagnosisCode(de.getDiagCode());
                mrp.setPrimaryDiagnosisDesc(de.getDiagDesc());
                mrp.setPrimaryDiagnosisICD10(de.getIcd10Code());
                mrp.setRefRequestId(mr.getRequestId());
                mrp.setRefRefNo(mr.getRequestCode());
                mrp.setDxRemarks(de.getDiagRemarks());
                mrp.setAvailHospId(aptj.getHospitalCode());
                mrp.setTestSubtype(0);
                mrp.setStatus(pp.getApprovalType().equalsIgnoreCase("auto") ? "APPROVED" : "PENDING");
                response.put(pp.getProcedureCode(), mrp.getStatus());

                if (dth != null)
                    mrp.setDocHospId(dth.getDocHospId());
                else
                    mrp.setDocHospId(0);
                this.saveMaceRequestProcedure(mr, mrp);

                MaceRequestOpProcedure mrop = new MaceRequestOpProcedure();
                mrop.setReqDiagId(mrod.getReqDiagId());
                mrop.setPrescribedTestId(mcpt.getPrescribedtestId());
                mrop.setTransactionId(mrp.getTransactionId());
                mrop.setDiagCode(mrod.getDiagCode());
                mrop.setProcCode(pp.getProcedureCode());
                mrop.setProcDesc(pp.getProcedureDesc());
                mrop.setMaceSubtype(0);
                mrop.setStatus(0);
                this.saveMaceReqOpProcedure(mr, mrop);
            } else if (null != pt) {
                MaceRequestTest mrt = new MaceRequestTest();
                mrt.setRequestFrom(mr.getMemCode());
                mrt.setStatus("Approved automatically.");
                mrt.setConsultReason(mrc.getConsultReason());
                mrt.setDoctorCode(aptj.getDoctorCode());
                mrt.setHospitalCode(aptj.getHospitalCode());
                mrt.setPrimaryDiagnosisCode(de.getDiagCode());
                mrt.setPrimaryDiagnosisDesc(de.getDiagDesc());
                mrt.setPrimaryDiagnosisICD10(de.getIcd10Code());
                mrt.setRefRequestId(mr.getRequestId());
                mrt.setRefRefNo(mr.getRequestCode());
                mrt.setDxRemarks(de.getDiagRemarks());
                mrt.setAvailHospId(aptj.getHospitalCode());
                mrt.setTestSubtype(pt.getApprovalType().equalsIgnoreCase("auto") ? 3 : 4);
                mrt.setStatus(pt.getApprovalType().equalsIgnoreCase("auto") ? "APPROVED" : "PENDING");
                response.put(pt.getProcedureCode(), mrt.getStatus());
                if (dth != null)
                    mrt.setDocHospId(dth.getDocHospId());
                else
                    mrt.setDocHospId(0);
                this.saveMaceReqTest(mr, mrt);

                MaceRequestOpTest mrot = new MaceRequestOpTest();
                mrot.setReqDiagId(mrod.getReqDiagId());
                mrot.setPrescribedTestId(mcpt.getPrescribedtestId());
                mrot.setTransactionId(mrt.getTransactionId());
                mrot.setDiagCode(mrod.getDiagCode());
                mrot.setProcCode(pt.getProcedureCode());
                mrot.setProcDesc(pt.getProcedureDesc());
                mrot.setGroup(pt.getProcedureGroup());
                mrot.setMaceSubtype(0);
                mrot.setStatus(0);
                this.saveMaceReqOpTest(mr, mrot);
            }
        }
        return response;
    }

    public void deleteMaceReqIpDiagproc(Integer reqDiagId) {
        maceMapper.deleteMaceReqIpDiagproc(reqDiagId);
    }

    public String getLastReqCodeErAdmitted(String memCode) {
        //TODO: Update this
        //maceER joind with maceRequest
        //Get last mace request by order of approval date
        MaceReqEr mre = maceMapper.getLastReqCodeEr(memCode);
        //check if status is discharged
        if (null == mre || "DISCHARGED".equalsIgnoreCase(mre.getStatus())) {
            return null;
        } else {
            if (mre.getDischargedOn() == null) {
                return mre.getMaceRequestId().toString();
            } else {
                return null;
            }
        }

    }

    public MaceReqEr getMaceRequestErByRequestId(String requestId) {
        return maceMapper.getMaceRequestErByRequestId(requestId);
    }

    public void updateMaceReqEr(MaceReqEr mrer) {
        maceMapper.updateMaceReqEr(mrer);
    }

    public MaceReqConsult getMaceReqConsultByReferenceNo(String referenceNo) {
        return maceMapper.getMaceReqConsultByRefNo(referenceNo);
    }

    public void updateMaceReqConsult(MaceReqConsult mrc) {
        maceMapper.updateMaceReqConsult(mrc);
    }

    //Create Basic Test Request for V2 Approval Engine integration
    public HashMap<String, Object> processBasicTestRequestV2(BasicOrOtherTestRequestJson rbtJson) {
        HashMap<String, Object> response = new HashMap<>();
        MemberDetails member = memMapper.getMember(rbtJson.getMemberCode());
        Hospital hospital = docHospMapper.getHospital(rbtJson.getHospitalCode());
        if (hospital == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The hospital (code: %s) " +
                    "was not found in the database.", rbtJson.getHospitalCode()));
            return response;
        }

        String procCodes = "";
        String procDescs = "";
        BigDecimal totalAmount = BigDecimal.ZERO;

        String finalAssignee = Constants.DEFAULT_GROUP;
        String responseCode = "";
        String responseDesc = "";
        String remarks = "";
        String approvalNo = "";
        String finalStatus = "";
        String finalDesc = "";

        for (BasicOrOtherTestRequestJson.DiagnosisProcedureJson testObj : rbtJson.getDiagnosisProcedures()) {
            TestProcObject tpo = diagProcMapper.getTestProcObject(testObj.getProcedureCode());
            procCodes += tpo.getProcCode() + "|";
            procDescs += tpo.getProcName() + "|";
            if (null == testObj.getAmount() || testObj.getAmount().equals(BigDecimal.ZERO))
                totalAmount = totalAmount.add(tpo.getAmount());
            else
                totalAmount = totalAmount.add(BigDecimal.valueOf(testObj.getAmount()));
            testObj.setCostCenter(tpo.getCostCenter());
        }

        CustomerCare c = this.initCustomerCare(member, rbtJson.getHospitalCode(),
                "", "", procCodes,
                totalAmount, "", rbtJson.getAppUsername(),
                procDescs,
                rbtJson.getMemberCode(),
                "",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                "",
                rbtJson.getRequestBy());

        //Initialize header request data. Overriding of status and status assignee will be done after V2 Approval Engine executions
        HashMap<String, List<String>> reasonMessagesMap = new HashMap<>();

        MaceRequest mr = new MaceRequest(c, member, 2, "COORDINATOR", rbtJson.getAppUsername());
        mr.setStatus(finalStatus);
        mr.setStatusRemarks(finalDesc);
        mr.setStatusAssignee("");
        mr.setHospEmail(rbtJson.getCoorEmail());
        mr.setHospContact(rbtJson.getCoorContact());
        mr.setRequestByCode(rbtJson.getHospitalCode());
        /**
         * Generate Approval No regardless if Approved/Disapproved/Pending for reference
         * */
//        approvalNo = this.generateID("APPROVALNO");
//        c.setApprovalNo(approvalNo);

        //Flag for all test approved
        boolean allApproved = true;
        /**
         * Group tests by cost center before processing.
         * */
        Map<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> groupedByCostCenter = Arrays.stream(rbtJson.getDiagnosisProcedures())
                .collect(Collectors.groupingBy(BasicOrOtherTestRequestJson.DiagnosisProcedureJson::getCostCenter, Collectors.toList()));

        MaceInsertOrder mio = new MaceInsertOrder();
        List<MaceRequestTestObj> mrtObjs = new ArrayList<>();
        for (Map.Entry<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> perCC : groupedByCostCenter.entrySet()) {
            MaceRequestTestObj mrtObj = new MaceRequestTestObj();
            List<MaceRequestTestObj.MaceRequestOpDiagObj> mrodObjs = new ArrayList<>();
            String ccStatus = "APPROVED";
            approvalNo = this.generateID("APPROVALNO");
            String transCode = this.generateID("TESTREQUEST");
            MaceRequestTest mrt = new MaceRequestTest();
            mrt.setApprovalNo(approvalNo);
            mrt.setDoctorCode("0");
            mrt.setTestSubtype(3);
            mrt.setHospitalCode(hospital.getHospitalCode());
            mrt.setAvailHospId(hospital.getHospitalCode());
            mrt.setTransCode(transCode);

            MaceRequestTestObj.MaceRequestOpDiagObj mrodObj = new MaceRequestTestObj.MaceRequestOpDiagObj();
            MaceRequestOpDiag mrod = new MaceRequestOpDiag();
            mrod.setDiagnosisEntity(maceMapper.getDiagnosisEntity("998"));
            mrod.setMaceDiagType(1);
            mrod.setTransCode(transCode);
            List<MaceRequestOpTest> mrots = new ArrayList<>();
            List<String> reasonsList = new ArrayList<>();
            BigDecimal subTotal = BigDecimal.ZERO;
            for (BasicOrOtherTestRequestJson.DiagnosisProcedureJson testObj : perCC.getValue()) {
                MaceRequestOpTest mrot = new MaceRequestOpTest();
                CustHospProcAmount chpa = diagProcMapper.getCustHospProcAmount(testObj.getProcedureCode(), hospital.getHospitalCode());
                TestProcObject tpo = diagProcMapper.getTestProcObject(testObj.getProcedureCode());
                tpo.setServiceType(2);
                tpo.setSubType(3);
                //Execute V2 approval engine and process results per execution
                //Basic tests do no have a diagnosis, doctor, and totalCosts. Ensure that the approval rules that rely on these 3 parameters are not executed for Basic Tests!
                ApprovalEngineResult approvalEngineResult = approvalEngine.executeApprovalEngine(member, null, hospital, null, tpo, 0.0);
                mrt.setStatus(approvalEngineResult.getFinalRequestStatus());
                //Generate transCode and map reason messages to this transCode

                reasonsList.addAll(approvalEngineResult.getReasonMessages());
                //Set allApproved to false if V2 Approval Engine does not return a request status of "APPROVED"
                if (!approvalEngineResult.getFinalRequestStatus().equals(Constants.REQUEST_AUTOMATIC_APPROVED)) {
                    ccStatus = "PENDING";
                    allApproved = false;
                    mrodObj.setMraObjs(approvalEngineResult.getMaceRequestApprovals());
                }
                mrot.setReqDiagId(mrod.getReqDiagId());
                mrot.setTransactionId(mrt.getTransactionId());
                mrot.setProcCode(tpo.getProcCode());
                mrot.setProcDesc(tpo.getProcName());
                mrot.setProcDefAmount(tpo.getAmount());
                mrot.setProcActualAmount(BigDecimal.valueOf(testObj.getAmount()));
                mrot.setProcHospAmount(null == chpa ? BigDecimal.ZERO : chpa.getProcAmount());
                BigDecimal procAmount = mrot.getProcActualAmount() != null && !mrot.getProcActualAmount().equals(BigDecimal.ZERO) ? mrot.getProcActualAmount() :
                        mrot.getProcHospAmount() != null && !mrot.getProcHospAmount().equals(BigDecimal.ZERO) ? mrot.getProcHospAmount() :
                                mrot.getProcDefAmount() != null && !mrot.getProcDefAmount().equals(BigDecimal.ZERO) ? mrot.getProcDefAmount() : BigDecimal.ZERO;

                subTotal = subTotal.add(procAmount);
                mrot.setDiagCode(mrod.getDiagCode());
                mrot.setStatus(0);
                mrot.setMaceSubtype(3);
                mrots.add(mrot);
                //Update final status assignee
                finalAssignee = approvalEngine.updateFinalStatusAssignee(finalAssignee, approvalEngineResult.getFinalStatusAssignee(), hospital);
            }
            for (String s : reasonsList) {
                System.out.println(s);
            }
            mrodObj.setMrod(mrod);
            mrodObj.setMrots(mrots);
            mrodObjs.add(mrodObj);
            reasonMessagesMap.put(transCode, reasonsList);
            mrt.setTransamount(subTotal);
            mrt.setStatus(ccStatus);
            mrt.setStatusRemarks(finalDesc);
            mrtObj.setMrt(mrt);
            mrtObj.setMrodObjs(mrodObj);
            mrtObjs.add(mrtObj);
        }
        //Flag for allApproved for all tests and procedures, if all tests/procedures were approved, set MaceRequest status to "APPROVED"
        //If status assignee is DEFAULT_GROUP, pass null to DB instead.
        if (allApproved) {
            mr.setStatus("APPROVED");
        } else {
            //If a test/procedure did not become approved, set header MaceRequest status to "PENDING"
            mr.setStatus("PENDING");
            finalStatus = "PENDING";
            mr.setStatusRemarks(finalDesc.equals("") ? "Automatic Pending." : finalDesc);
        }
        mr.setStatusAssignee(!finalAssignee.equals(DEFAULT_GROUP) ? finalAssignee : null);
        mio.setMr(mr);
        mio.setMrtObjs(mrtObjs);
        this.maceInsertProcessTests(mio);

        //Remarks to be returned to app should be the final status of the header request
        remarks = mr.getStatus();
        responseCode = "200";

        //Response and notes will be the list of transCodes
        String transCodeString = String.join(", ", reasonMessagesMap.entrySet().stream().map(entry -> entry.getKey())
                .collect(Collectors.toList()));
        responseDesc = transCodeString;

        //Save customer care data
        c.setType(2);
        c.setActionTaken(allApproved ? 1 : 0);
        c.setRemarks(allApproved ? "TESTS" : "Tests - Please call 841-8080 for approval");
        c.setNotes(transCodeString);
        c.setBatchCode(this.generateID("BATCHNO"));
        c.setCallerId(this.generateID("CALLERID"));

        if (allApproved) {
            this.saveTransaction(c, member);
            customerServiceService.saveTransaction(c, member);
            loaService.saveLoa(c, c.getUpdatedBy(), c.getPrimaryComplaint());
            this.saveComplaint(c.getBatchCode(), c.getPrimaryComplaint());
        } else {
            this.saveTransactionForCall(c);
            customerService.saveTransactionForCall(c);
            loaService.saveLoa(c, c.getUpdatedBy(), c.getPrimaryComplaint());
        }
        MaceRequestReturn mrr = new MaceRequestReturn(mr);
        BasicOrOtherTestResponseJson bootrj = new BasicOrOtherTestResponseJson();
        bootrj.setMaceRequest(mr);

        //From Other Test Response
        response.put("loaList", mrr);
        response.put("data", bootrj);
        //Set response data
        response.put("totalAmount", totalAmount);
        response.put("approvalNo", approvalNo);
        response.put("allApproved", allApproved);
        response.put("responseCode", responseCode);
        response.put("reasonMessagesMap", reasonMessagesMap);
        response.put("responseDesc", responseDesc);
        response.put("remarks", remarks);
        response.put("status", finalStatus);
        return response;
    }

    //Create Basic Test Request for V2 Approval Engine integration
    public ResponseEntity<?> processConsultationV2(ConsultJson consultJson, Integer consultSubtype, String requestOrigin, String remarks) {

        HashMap<String, Object> response = new HashMap<>();
        response.put("responseCode", "200");
        response.put("responseDesc", "Login Successful.");

        String withProvider = "";

        //TODO: Get from cache and not the database
        Hospital hospital = claimsService.getHospital(consultJson.getHospitalCode());
        if (hospital == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The hospital (code: %s) " +
                    "was not found in the database.", consultJson.getHospitalCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //Pulls details regarding member using memberCode.
        MemberDetails memberDetails = memService.getMember(consultJson.getMemberCode());
        if (memberDetails == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The member (code: %s) " +
                    "was not found in the database.", consultJson.getMemberCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //Initializes Customer Object and set requestOrigin as MEMBER
        CustomerCare c = this.initCustomerCare(memberDetails, consultJson.getHospitalCode(),
                consultJson.getDoctorCode(), consultJson.getDiagnosisCode(), consultJson.getProcedureCode(),
                consultJson.getProcedureAmount(), consultJson.getLocationCode(), consultJson.getUsername(),
                consultJson.getProcedureDesc(),
                consultJson.getMemberCode(),
                consultJson.getDiagnosisDesc(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                consultJson.getPrimaryComplaint(),
                consultJson.getRequestBy());
        c.setRequestOrigin(requestOrigin);

        //Run Engine
        //Do not execute Approval Engine if data will be sent as draft
        ApprovalEngineResult approvalEngineResult = new ApprovalEngineResult();
        if (consultJson.getSubmitForApproval()) {
            approvalEngineResult = approvalEngine.executeApprovalEngine(memberDetails, hospital, 1, consultSubtype);
        }

        //Set initial response variables based on results of approval engine
        String responseCode = "";
        String responseDesc = approvalEngineResult.getReasonMessagesString();
        //Set status
        String status = approvalEngineResult.getFinalRequestStatus();

        boolean autoApprovedRequest = approvalEngineResult.getFinalRequestStatus().equals(REQUEST_AUTOMATIC_APPROVED);
        c.setActionTaken(autoApprovedRequest ? 0 : 1);

        String requestCode = "";

        //If request comes from coordinator and approval engine did not return an approved request, set remarks to resulting status from engine.
        //Otherwise, set remarks to the one passed by the controllers.
        if (requestOrigin.equals("COORDINATOR") && !autoApprovedRequest) {
            c.setRemarks(status);
        } else {
            c.setRemarks(remarks);
        }

        //Call Service to save transaction for call log
        //Always generate callerid and batchno
        c.setCallerId(this.generateID("CALLERID"));
        c.setBatchCode(this.generateID("BATCHNO"));
        //Generate ApprovalNo if engine result is APPROVED
        String approvalNo = this.generateID("APPROVALNO");
        c.setApprovalNo(approvalNo);
        if (autoApprovedRequest) {
            try {
                BigDecimal procAmount = BigDecimal.valueOf(this.getCostByTransactionType("CONSULTATION"));
                c.setProcedureAmount(procAmount);
                c.setProcedureAmt(procAmount);
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.saveTransaction(c, memberDetails);
            customerServiceService.saveTransaction(c, memberDetails);
            loaService.saveLoa(c, consultJson.getUsername(), consultJson.getPrimaryComplaint());
            this.saveComplaint(c.getBatchCode(), consultJson.getPrimaryComplaint());
            responseCode = "200";
            responseDesc = "Approved";
            status = "APPROVED";
        } else {
            this.saveTransactionForCall(c);
            customerServiceService.saveTransactionForCall(c);
            loaService.saveLoa(c, consultJson.getUsername(), consultJson.getPrimaryComplaint());
            responseCode = "220";
            responseDesc = "PENDING";
        }
        //START INSERT MACE_REQ TABLES
        //This only returns a MaceInsertOrder object. Insertion to DB is done through a separate service that accepts this MaceInsertOrder object
        MaceInsertOrder mio = this.saveMaceRequestForConsult(memberDetails, c, approvalEngineResult, consultSubtype, consultJson);
        //END INSERT MACE_REQ TABLES

        if (autoApprovedRequest)
            if (this.checkIfProviderHasAppUserAccount(c.getDoctorCode()))
                withProvider = "withProvider";

        //Checking Approval Logs
        for (String s : approvalEngineResult.getReasonMessages()) {
            System.out.println(s);
        }

        response.put("withProvider", withProvider);
        response.put("requestCode", requestCode);
        response.put("approvalNo", approvalNo);
        response.put("batchCode", c.getBatchCode());
        response.put("responseCode", responseCode);
        response.put("responseDesc", responseDesc);
        response.put("remarks", c.getRemarks());
        response.put("status", status);
        response.put("maceInsertOrder", mio);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public List<String> getAppUsersByDeviceId(String deviceId) {
        return maceMapper.getAppUsersByDeviceId(deviceId);
    }

    public LockMember getLastLoginAttempt(String memberCode) {

        InvalidBdaySearch ibs = maceMapper.getLastInvalidAttempt(memberCode);
        if (null != ibs) {
            LockMember lm = new LockMember(ibs);
            return lm;
        }
        return null;
    }

    public void deleteLogInvalidSearch(String memberCode) {
        maceMapper.deleteLogInvalidSearch(memberCode);
    }

    public void logInvalidSearch(InvalidBdaySearch ibs) {
        maceMapper.logInvalidSearch(ibs);
    }

    public String processMsgCode(String msgCode, String serviceType, String memOStatCode) {
        if (memOStatCode.equalsIgnoreCase("0"))
            return serviceType;
        switch (memOStatCode) {
            case "ON HOLD":
            case "FOR REACTIVATION":
            case "VERIFY PAYMENT WITH RMD":
            case "VERIFY RENEWAL FROM MARKETING / SALES":
            case "VERIFY MEMBERSHIP":
            case "CONSULTATION":
                return serviceType;
            default:
                return memOStatCode;
        }
    }

    public List<MaceRequest> getAvailedMaceRequestsByDocCodeAndMemCode(String doctorCode, String memberCode) {
        return maceMapper.getAvailedMaceRequestsByDocCodeAndMemCode(doctorCode, memberCode);
    }

    public boolean checkForDuplicateConsult(ConsultJson consultJson) {
        return maceMapper.checkForDuplicateConsult(consultJson);
    }

    public boolean checkForDuplicateTest(BasicOrOtherTestRequestJson bootrj) {
        List<String> procCodes = new ArrayList<>();
        for (BasicOrOtherTestRequestJson.DiagnosisProcedureJson diagnosisProcedureJson : bootrj.getDiagnosisProcedures()) {
            procCodes.add(diagnosisProcedureJson.getProcedureCode());
        }
        return maceMapper.checkForDuplicateTest(bootrj, procCodes);
    }

    public boolean checkForDuplicateProc(BasicOrOtherTestRequestJson bootrj) {
        List<String> procCodes = new ArrayList<>();
        for (BasicOrOtherTestRequestJson.DiagnosisProcedureJson diagnosisProcedureJson : bootrj.getDiagnosisProcedures()) {
            procCodes.add(diagnosisProcedureJson.getProcedureCode());
        }
        return maceMapper.checkForDuplicateProc(bootrj, procCodes);
    }

    public boolean checkForDuplicateBasicTest(BasicOrOtherTestRequestJson bootrj) {
        List<String> procCodes = new ArrayList<>();
        for (BasicOrOtherTestRequestJson.DiagnosisProcedureJson testObj : bootrj.getDiagnosisProcedures()) {
            procCodes.add(testObj.getProcedureCode());
        }
        return maceMapper.checkForDuplicateBasicTest(bootrj, procCodes);
    }

    public List<MaceAnnouncements> getAnnouncements() {
        return maceMapper.getAnnouncements();
    }

    public HashMap<String, String> getLastUpdatesOnTables() {
        HashMap<String, String> response = new HashMap<>();
        for (LastUpdatesCoor lastUpdatesCoor : maceMapper.getLastUpdatesOnTables()) {
            response.put(lastUpdatesCoor.getListType(), lastUpdatesCoor.getLastUpdateDate());
        }
        return response;
    }

    public List<RecentTransObj> getRecentTransactionsHospCode(String hospCode, String memberCode) {
        List<RecentTransObj> trans = new ArrayList<>();
        List<MaceRequest> reqs = maceMapper.getRecentTransactionHospCode(hospCode, memberCode);
        for (MaceRequest mr : reqs) {
            RecentTransObj rto = new RecentTransObj();
            try {
                MemberDetails member = memService.getMember(mr.getMemCode());
                rto.setMemName(member.getMEM_NAME());
            } catch (Exception e) {
                e.printStackTrace();
            }
            rto.setMemCode(mr.getMemCode());
            rto.setMaceReqId(mr.getRequestId());
            rto.setStatus(mr.getStatus());
            rto.setRequestDateTime(mr.getRequestDatetime());
            String[] ret = new String[0];
            List<String> approvalNos;
            switch (mr.getServiceTypeId()) {
                case 1://Consult and Maternity Consult
                    rto.setServiceType("CONSULTATION");
                    ret = new String[1];
                    ret[0] = maceMapper.getReferenceNoByReqId(mr.getRequestId());
                    break;
                case 2://Basic and Other Test
                    rto.setServiceType("TESTS");
                    approvalNos = maceMapper.getApprovalNosByReqId(mr.getRequestId());
                    ret = new String[approvalNos.size()];
                    for (int i = 0; i < approvalNos.size(); i++)
                        ret[i] = approvalNos.get(i);
                    break;
                case 3://Procedures
                    rto.setServiceType("PROCEDURES");
                    approvalNos = maceMapper.getApprovalNosByReqId(mr.getRequestId());
                    ret = new String[approvalNos.size()];
                    for (int i = 0; i < approvalNos.size(); i++)
                        ret[i] = approvalNos.get(i);
                    break;
                default:
                    break;
            }
            rto.setApprovalNo(ret);
            trans.add(rto);
        }
        return trans;
    }

    public HashMap<String, Object> getMaceFaqs() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("maceFaqs", maceMapper.getMaceFaqs());
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully retrieved list.");
        return response;
    }

    public AppUser getAppUserByDoctorCode(String doctorCode) {
        return maceMapper.getAppUserByDoctorCode(doctorCode);
    }

    public List<MaceRequest> getMaceRequestsByRequestCodes(List<String> requestCodes, int serviceTypeId) {
        return maceMapper.getMaceRequestsByRequestCodes(requestCodes, serviceTypeId);
    }

    public void addClaim(MaceClaims claim) {
        maceMapper.addClaim(claim);
    }

    public TempDoctorModel createTempDoc(String doctorName, String appUsername) {
        TempDoctorModel tempDoc = new TempDoctorModel();
        tempDoc.setDoctorName(doctorName);
        tempDoc.setTempDocCode(generateID("TEMP_DOCTOR"));
        tempDoc.setCreatedBy(appUsername);
        tempDoc.setCreatedOn(new Date());
        return tempDoc;
    }

    public TempDiagModel createTempDiag(String diagName, String appUsername) {
        TempDiagModel tempDiag = new TempDiagModel();
        tempDiag = new TempDiagModel();
        tempDiag.setDiagCode(generateID("TEMP_DIAGNOSIS"));
        tempDiag.setDiagDesc(diagName);
        tempDiag.setCreatedBy(appUsername);
        tempDiag.setCreatedOn(new Date());
        tempDiag.setDiseaseType("");
        return tempDiag;
    }

    public TempProcModel createTempProc(String procName, BigDecimal procAmount, String appUsername) {
        TempProcModel tpm = new TempProcModel();
        tpm = new TempProcModel();
        tpm.setProcCode(generateID("TEMP_PROCEDURE"));
        tpm.setProcName(procName);
        tpm.setCostCenter("TEMP");
        tpm.setProcAmount(procAmount);
        tpm.setCreatedBy(appUsername);
        tpm.setCreatedOn(new Date());
        return tpm;
    }

    public List<MaceIPRequestLogs> getIPLogsByMemCode(String memCode) {
        return maceMapper.getIPLogsByMemCode(memCode);
    }

    public MaceReqInpatient getMaceReqInpatientByReqIdTransId(int maceRequestId, int transactionId) {
        return maceMapper.getMaceReqInpatientByReqIdTransId(maceRequestId, transactionId);
    }

    public List<MaceReqIpDoctor> getMaceReqIpDoctorsByReqIdTransId(Integer requestId, Integer transactionId) {
        return maceMapper.getMaceReqIpDoctorsByReqIdTransId(requestId, transactionId);
    }

    public List<MaceReqIpDiag> getMaceReqIpDiagByReqIdTransId(Integer requestId, Integer transactionId) {
        return maceMapper.getMaceReqIpDiagByReqIdTransId(requestId, transactionId);
    }

    public List<MaceReqIpDiagproc> getMaceReqIpDiagProcsByReqIdTransId(Integer requestId, Integer transactionId) {
        return maceMapper.getMaceReqIpDiagProcsByReqIdTransId(requestId, transactionId);
    }

    public List<MaceReqInpatient> getMaceReqInpatientsByReqId(Integer requestId) {
        return maceMapper.getMaceReqInpatientsByReqId(requestId);
    }

    public List<MaceReqIpRoom> getMaceReqIPRooms(Integer requestId) {
        return maceMapper.getMaceReqIpRooms(requestId);
    }

    public List<MaceConsPrescribedtest> getMaceConsPrescribedTestsByReqId(Integer requestId) {
        return maceMapper.getMaceConsPrescribedTestsByReqId(requestId);
    }

    public void requestForDischargeIP(int maceRequestId) {
        maceMapper.requestForDischargeIP(maceRequestId);
    }

    public List<MaceRequestAttachment> getMaceAttachmentsByReqCodeTransId(String requestCode, Integer transactionId) {
        return maceMapper.getMaceAttachmentsByReqCodeTransId(requestCode, transactionId);
    }

    public void insertMaceInpatientAudit(MaceInpatientAudit mia) {
        maceMapper.insertMaceInpatientAudit(mia);
    }

    public List<MaceInpatientAudit> getIPAuditLogs(String memCode) {
        return maceMapper.getIPAuditLogs(memCode);
    }

    private class GetResponseEntityValuesInteger {
        private Integer msgCode;
        private String responseCode;
        private String responseDesc;
        private String remarks;

        public GetResponseEntityValuesInteger(Integer msgCode) {
            this.msgCode = msgCode;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public String getResponseDesc() {
            return responseDesc;
        }

        public String getRemarks() {
            return remarks;
        }

        public GetResponseEntityValuesInteger invoke() {

            try {
                BlockingMessages blocker = getBlockingMessageByCode(String.valueOf(msgCode));
                remarks = blocker.getRemarks();
                responseCode = blocker.getResponseCode().toString();
                responseDesc = blocker.getResponseDesc();
                return this;
            } catch (Exception e) {
                remarks = "Unhandled Message. Please call 841-8080 for approval";
                responseCode = "250";
                responseDesc = "Unhandled Message Code";
                return this;
            }
        }

    }

    public List<ProcedureJson> getBasicTests() {
        List<DiagnosisProceduresViewEntity> tests = diagProcMapper.getBasicTests();
        List<ProcedureJson> json = new ArrayList<>();
        for (DiagnosisProceduresViewEntity test : tests) {
            test.setAmount(test.getAmount() == 0.0 ? 777.50 : test.getAmount());
            json.add(new ProcedureJson(test));
        }
        return json;
    }

    public List<ProcedureJson> getOtherTests() {
        List<DiagnosisProceduresViewEntity> tests = diagProcMapper.getOtherTests();
        List<ProcedureJson> json = new ArrayList<>();
        for (DiagnosisProceduresViewEntity test : tests) {
            json.add(new ProcedureJson(test));
        }
        return json;
    }

    public DiagnosticProceduresEntity getDiagProcedureByProcedureCode(String procCode, String dxCode) {
        return diagProcMapper.getDiagProcedureByProcedureCode(procCode, dxCode);
    }

    public void saveMaceRequestInpatient(MaceRequest maceRequest, MaceReqInpatient mrip) {
        mrip.setMaceRequestId(maceRequest.getRequestId());
        maceMapper.saveMaceRequestInpatient(mrip);
    }

    public void saveMaceRequestIpRoom(MaceReqInpatient mrip, MaceReqIpRoom mrir) {
        mrir.setMaceRequestId(mrip.getMaceRequestId());
        mrir.setTransactionId(mrip.getTransactionId());
        maceMapper.saveMaceRequestIpRoom(mrir);
    }

    public void saveMaceReqIpDoctor(MaceRequest mr, MaceReqInpatient mri, MaceReqIpDoctor mrid) {
        mrid.setMaceRequestId(mr.getRequestId());
        mrid.setTransactionId(mri.getTransactionId());
        maceMapper.saveMaceReqIpDoctor(mrid);
    }

    public void saveMaceReqErDoctor(MaceRequest mr, MaceReqEr mrer, MaceReqErDoctor mred) {
        mred.setMaceRequestId(mr.getRequestId());
        mred.setTransactionId(mrer.getTransactionId());
        maceMapper.saveMaceReqErDoctor(mred);
    }

    public void saveMaceReqIpDoctor(MaceReqInpatient mri, Integer refDiagProcId, MaceReqIpDoctor mrid) {
        mrid.setMaceRequestId(mri.getMaceRequestId());
        mrid.setTransactionId(mri.getTransactionId());
        mrid.setRefDiagProcId(refDiagProcId);
        maceMapper.saveMaceReqIpDoctor(mrid);
    }

    public void saveMaceReqIpDiag(MaceRequest mr, MaceReqInpatient mri, MaceReqIpDiag mrid) {
        mrid.setMaceRequestId(mr.getRequestId());
        mrid.setTransactionId(mri.getTransactionId());
        maceMapper.saveMaceReqIpDiag(mrid);
    }

    public void saveMaceReqErDiag(MaceRequest mr, MaceReqEr mrer, MaceReqErDiag mred) {
        mred.setMaceRequestId(mr.getRequestId());
        mred.setTransactionId(mrer.getTransactionId());
        maceMapper.saveMaceReqErDiag(mred);
    }

    public void saveMaceReqIpDiagProc(MaceReqInpatient mrip, MaceReqIpDiagproc mridp) {
        mridp.setTransactionId(mrip.getTransactionId());
        maceMapper.saveMaceReqIpDiagProc(mridp);
    }
    public void saveMaceReqErProc(MaceReqEr mrer, MaceReqErProcedure mrerp) {
        mrerp.setTransactionId(mrer.getTransactionId());
        maceMapper.saveMaceReqErProc(mrerp);
    }

    public void saveMaceRequestIpOtherCharge(MaceReqInpatient mrip, MaceReqIpOtherCharge mriOc) {
        mriOc.setMaceRequestId(mrip.getMaceRequestId());
        mriOc.setTransactionId(mrip.getTransactionId());
        maceMapper.saveMaceRequestIpOtherCharge(mriOc);
    }

    public void saveMaceRequestIpOtherInformation(MaceReqInpatient mrip, MaceReqIpOtherInformation mriOi) {
        mriOi.setMaceRequestId(mrip.getMaceRequestId());
        mriOi.setTransactionId(mrip.getTransactionId());
        maceMapper.saveMaceRequestIpOtherInformation(mriOi);
    }

    public List<MaceConfig> getMaceConfigObjects(String valueType) {
        return maceMapper.getMaceConfigObjects(valueType);
    }

    public List<ApprovalRulesDao> getApprovalRules(Integer serviceTypeId, Integer subtypeId) {
        logger.info("getApprovalRules");
        return maceMapper.getApprovalRules(serviceTypeId, subtypeId);
    }

    public SpecialDiagnosis getSpecialDiagnosis(String diagCode) {

        return maceMapper.getSpecialDiagnosis(diagCode);
    }

    public List<DiagnosisEntity> getDiagnosisInRequest(List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson> diagProcList) {
        List<DiagnosisEntity> refDiagList = new LinkedList<DiagnosisEntity>();

        List<String> diagCodeList = diagProcList.stream()
                .map(BasicOrOtherTestRequestJson.DiagnosisProcedureJson::getDiagnosisCode)
                .distinct()
                .collect(Collectors.toList());

        for (String diagnosisCode : diagCodeList) {
            DiagnosisEntity ent = getDiagnosisEntity(diagnosisCode);
            refDiagList.add(ent); //useRefDiagListForAdding Elements later
        }
        return refDiagList;
    }

    //ER

    public void saveMaceRequestER(MaceRequest maceRequest, MaceReqEr mrer) {
        mrer.setMaceRequestId(maceRequest.getRequestId());
        maceMapper.saveMaceRequestER(mrer);
    }

    public static Map<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> sortByValue(Map<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>>> list =
                new LinkedList<>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, Comparator.comparing(o -> (o.getKey())));

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }

    //Replaced service type checking with null checking to accommodate for OP/OR requests and Consultation requests via Portal, which can be a mix of Tests and Procedures
    public void maceInsertProcessTests(MaceInsertOrder mio) {
        logger.info("maceInsertProcessTests");
        if (mio != null && mio.getMr() != null) {
            //Handle insertions on table for Tests and Procedures
            MaceRequest mr = mio.getMr();
            //Update instead of insert if header request already has a request id
            if (mr.getRequestId() != null) {
                maceMapper.updateMaceRequest(mr);
                //Delete all subrequest data. New subrequest data will be saved
                maceMapper.deleteAllSubrequestData(mr.getRequestId());
            } else {
                //Header request will obtain a request id after insertion is performed
                maceMapper.saveMaceRequest(mr);
            }

            //Save Consultation
            if (mio.getMrcObj() != null) {
                MaceReqConsult mrc = mio.getMrcObj().getMrc();
                mrc.setMaceRequestId(mr.getRequestId());
                maceMapper.saveMaceReqConsult(mrc);

                for (MaceRequestApproval mra : mio.getMrcObj().getMraObjs()) {
                    mra.setRequestId(mr.getRequestId());
                    mra.setTransactionId(mrc.getTransactionId());
                    mra.setTransCode(mrc.getTransCode());
                    maceMapper.saveMaceRequestApprovalLog(mra);
                }
            }

            //Save Tests
            if (mio.getMrtObjs() != null) {
                for (MaceRequestTestObj mrtObj : mio.getMrtObjs()) {
                    MaceRequestTest mrt = mrtObj.getMrt();
                    mrt.setMaceRequestId(mr.getRequestId());
                    System.out.println("COLLATED FINAL " + String.valueOf(mrt.getTransamount()));
                    maceMapper.saveMaceReqTest(mrt);
                    MaceRequestTestObj.MaceRequestOpDiagObj mrodObj = mrtObj.getMrodObjs();
                    MaceRequestOpDiag mrod = mrodObj.getMrod();
                    mrod.setTransactionId(mrt.getTransactionId());
                    mrod.setMaceRequestId(mr.getRequestId());
                    maceMapper.saveMaceRequestOpDiag(mrod);
                    for (MaceRequestOpTest mrot : mrodObj.getMrots()) {
                        mrot.setTransactionId(mrt.getTransactionId());
                        mrot.setReqDiagId(mrod.getReqDiagId());
                        mrot.setMaceRequestId(mr.getRequestId());
                        mrot.setTransCode(mrt.getTransCode());
                        maceMapper.saveMaceReqOpTest(mrot);
                    }
                    if (null != mrodObj.getMraObjs()) {
                        for (MaceRequestApproval mra : mrodObj.getMraObjs()) {
                            mra.setRequestId(mr.getRequestId());
                            mra.setTransactionId(mrt.getTransactionId());
                            mra.setTransCode(mrt.getTransCode());
                            maceMapper.saveMaceRequestApprovalLog(mra);
                        }
                    }
                }
            }

            //Save Procedures
            if (mio.getMrpObjs() != null) {
                //Procedures
                for (MaceRequestProcObj mrpObj : mio.getMrpObjs()) {
                    MaceRequestProcedure mrp = mrpObj.getMrp();
                    mrp.setMaceRequestId(mr.getRequestId());
                    maceMapper.saveMaceReqProcedure(mr, mrp);
                    MaceRequestProcObj.MaceRequestOpDiagObj mrodObj = mrpObj.getMrodObjs();
                    MaceRequestOpDiag mrod = mrodObj.getMrod();
                    mrod.setTransactionId(mrp.getTransactionId());
                    mrod.setMaceRequestId(mr.getRequestId());
                    maceMapper.saveMaceRequestOpDiag(mrod);
                    for (MaceRequestOpProcedure mrop : mrodObj.getMrops()) {
                        mrop.setTransactionId(mrp.getTransactionId());
                        mrop.setReqDiagId(mrod.getReqDiagId());
                        mrop.setMaceRequestId(mr.getRequestId());
                        mrop.setTransCode(mrp.getTransCode());
                        maceMapper.saveMaceReqOpProcedure(mr, mrop);
                    }
                    if (null != mrpObj.getMraObjs()) {
                        for (MaceRequestApproval mra : mrpObj.getMraObjs()) {
                            mra.setRequestId(mr.getRequestId());
                            mra.setTransactionId(mrp.getTransactionId());
                            mra.setTransCode(mrp.getTransCode());
                            maceMapper.saveMaceRequestApprovalLog(mra);
                        }
                    }
                }
            }

            //Save Doctors
            if (mio.getMrdObjs() != null) {
                for (MaceRequestOpDoctor mrdObj : mio.getMrdObjs()) {
                    mrdObj.setMaceRequestId(mr.getRequestId());
                    maceMapper.saveMaceReqOpDoctor(mrdObj);
                }
            }

            //Save Rooms
            if (mio.getMrRoomObjs() != null) {
                for (MaceRequestOpRoom mrRoomObj : mio.getMrRoomObjs()) {
                    mrRoomObj.setMaceRequestId(mr.getRequestId());
                    maceMapper.saveMaceReqOpRoom(mrRoomObj);
                }
            }

            //Save Other Services
            if (mio.getMrOsObjs() != null) {
                for (MaceRequestOpOtherCharge mrOsObj : mio.getMrOsObjs()) {
                    mrOsObj.setMaceRequestId(mr.getRequestId());
                    maceMapper.saveMaceReqOpOtherCharge(mrOsObj);
                }
            }

            //Save Other Information
            if (mio.getMrOiObjs() != null) {
                for (MaceRequestOpOtherInformation mrOiObj : mio.getMrOiObjs()) {
                    mrOiObj.setMaceRequestId(mr.getRequestId());
                    maceMapper.saveMaceReqOpOtherInformation(mrOiObj);
                }
            }
        } else {
            //THROW OR LOG EXCEPTION HERE?
            System.out.println("Invalid MaceInsertOrder data!");
        }
    }

    public void overrideParentRequest(Integer parRequestId) {
        maceMapper.overrideParentRequest(true, parRequestId);
    }

    public MaceRequest retrieveHeaderRequest(Integer parRequestId) {
        MaceRequest returnMaceRequest = new MaceRequest();
        if (parRequestId != null) {
            MaceRequest checkHeaderRequest = maceMapper.getMaceRequestByRequestId(parRequestId);
            if (checkHeaderRequest != null) {
                returnMaceRequest = checkHeaderRequest;
            }
        }
        return returnMaceRequest;
    }
}
