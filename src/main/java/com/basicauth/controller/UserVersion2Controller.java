package com.basicauth.controller;


import com.basicauth.data.*;
import com.basicauth.domain.ImageHolder;
import com.basicauth.service.*;
import com.basicauth.types.LoginJson;
import com.basicauth.types.UserJson;
import net.incuventure.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/v2")
public class UserVersion2Controller {

    private static final Logger logger = LoggerFactory.getLogger(UserVersion2Controller.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MaceService maceService;

    @Autowired
    private MemService memService;

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerifiedMemberService verifiedMemberService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LoaService loaService;



    //TODO: FIX
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserJson>> listUsers() {
        List<User> users = userService.findAllUsers();
        List<UserJson> usersJson;
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            usersJson = users.stream()
                    .map(u -> new UserJson(
                            u.getUsername(), null, u.getMemberCode(), u.getBirthDateString(), u.getLname(), u.getFname(), u.getMi(), u.getSex(), u.getRegisterDevice(),
                            u.getPhoneNo(), u.getEmail(), u.getDateRegistered(), u.getStatus()
                    ))
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(usersJson, HttpStatus.OK);
    }

    //TODO: FIX
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserJson> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userToUserJson(user), HttpStatus.OK);
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);

        HashMap<String, Object> response = new HashMap<>();

        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            response.put("responseCode", "404");
            response.put("responseDesc", "Not Found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        userService.deleteUserById(id);
        response.put("responseCode", "200");
        response.put("responseDesc", "Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/registerAccount/", method = RequestMethod.POST)
    public ResponseEntity<?> registerAccount(@RequestBody UserJson userJson) {
        HashMap<String, Object> response = new HashMap<>();

        if (userService.isUsernameUsed(userJson.getUsername())) {
            System.out.println("A User with name " + userJson.getUsername() + " already exist");
            response.put("responseCode", "210");
            response.put("responseDesc", "Username already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        Map memberInfo = memService.getMemberInfo(userJson.getMemberCode());


        userService.saveUser(new User(
                userJson.getUsername(), passwordEncoder.encode(userJson.getPassword()), userJson.getMemberCode(),
                (String) memberInfo.get("MEM_LNAME"), (String) memberInfo.get("MEM_FNAME"),
                userJson.getMi(), userJson.getBirthDate(), userJson.getSexInt(), new Date(),
                userJson.getRegisterDevice(), userJson.getPhone(), userJson.getEmail(), "ACTIVE"
        ));
        //Retrieve user to get ID
        User user = userService.findByUsername(userJson.getUsername());
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setUserAcctId(user.getId());
        memberAccount.setMemType(1); //"What is this??" 0 for primary user
        memberAccount.setMemCode(user.getMemberCode());
        memberAccount.setMemLname(user.getLname());
        memberAccount.setMemFname(user.getFname());
        memberAccount.setMemMname(user.getMi());
        memberAccount.setMemBday(user.getBirthDate());
        memberAccount.setMemSex(user.getSex());
        maceService.saveMemAccount(memberAccount);

        response.put("responseCode", "200");
        response.put("responseDesc", "User successfully registered");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/verifyMember/", method = RequestMethod.GET)
    public ResponseEntity<?> verifyMember(@RequestParam("memberCode") String memberCode, @RequestParam("dob") String date_of_birth) {
        System.out.println("verifyMember");

        HashMap<String, Object> response = new HashMap<>();

        /**
         * Start validating if MemberCode has Existing User Account
         */
        if (maceService.hasExistingMemUserAcct(null, memberCode)) {
            response.put("responseCode", "230");
            response.put("responseDesc", "Member is already registered");
            response.put("MemberInfo", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {

            /**
             * Start validating Member Account
             */
            Map memberInfo = memService.getMemberInfo(memberCode);
            if (null == memberInfo) {
                response.put("responseCode", "210");
                response.put("responseDesc", "Member Account not found");
                response.put("MemberInfo", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                /**
                 * Start validating BDay
                 */
                String bdayString = (String) memberInfo.get("BDAY");
                System.out.println("bdayString:" + bdayString);
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
                SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
                Date bday = null;
                Date bdayParam = null;
                try {
                    bday = sdf.parse(bdayString);
                    System.out.println("bdayParse:" + sdf.format(bday));
                    bdayParam = sdfParam.parse(date_of_birth);
                    if (bdayParam.compareTo(bday) == 0) {
                        /**
                         * Start validating Member Status
                         */

                        String Mem_OStat_Code = (String) memberInfo.get("Mem_OStat_Code");
                        System.out.println("Mem_OStat_Code:" + Mem_OStat_Code);
                        int memStat = memService.validateMemStat(Mem_OStat_Code);
                        System.out.println("memStat:" + memStat);
                        if (220 == memStat || 500 == memStat) {
                            response.put("responseCode", memStat);
                            response.put("responseDesc", "Your account is inactive. Please call 841-8080 to verify account status.");
                            response.put("MemberInfo", null);
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        } else {
                            response.put("responseCode", "200");
                            response.put("responseDesc", "Member is valid");
                            response.put("MemberInfo", memberInfo);
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        }
                    } else {

                        response.put("responseCode", "210");
                        response.put("responseDesc", "Member Code and Date Of Birth don’t match");
                        response.put("MemberInfo", null);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.put("responseCode", "210");
                    response.put("responseDesc", "Problem Parsing Date of Birth");
                    response.put("error", e.getMessage());
                    response.put("MemberInfo", null);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        }
    }


    @RequestMapping(value = "/loginMember/", method = RequestMethod.POST)
    public ResponseEntity<?> loginMember(@RequestBody LoginJson loginJson) {

        System.out.println(loginJson.getUsername());
        System.out.println(loginJson.getPassword());
        System.out.println(loginJson.getDeviceId());
        //TODO LOG REQUEST

        HashMap<String, Object> response = new HashMap<>();

        if (maceService.hasExistingMemUserAcct(loginJson.getUsername(), null)) {
            if (maceService.matchUsernameAndPassword(loginJson.getUsername(), loginJson.getPassword())) {
                if (!maceService.isStatusLocked(loginJson.getUsername())) {
                    maceService.resetInvalidLoginCount(loginJson.getUsername());
                    Map userAccount = maceService.getMemUserAccount(loginJson.getUsername());
                    AppVersion appVersion = maceService.getAppVersionByUserType("MEMBER");
                    Boolean hasMaternity = true;
                    try {
                        if (Double.parseDouble(appVersion.getForceVersion()) > Double.parseDouble(loginJson.getVersionNo())) {
                            response.put("responseCode", 290);
                            response.put("responseDesc", "Update required.");
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        } else if (Double.parseDouble(appVersion.getVersionNo()) > Double.parseDouble(loginJson.getVersionNo())) {
                            response.put("responseCode", 280);
                            response.put("responseDesc", "Optional Update Available.");
                        } else {
                            response.put("responseCode", 200);
                            response.put("responseDesc", "Login Successful.");
                        }
                        hasMaternity = maceService.hasMaternity((String) userAccount.get("MEM_CODE"));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("userAccount:" + userAccount);
                    List userAccounts = maceService.getUserAccts((int) userAccount.get("ID"));
                    response.put("hasMaternity", hasMaternity);
                    response.put("MemberAccounts", userAccounts);
                    response.put("UserAccount", userAccount);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    response.put("responseCode", "220");
                    response.put("responseDesc", "User is locked");
                    response.put("MemberAccounts", null);
                    response.put("UserAccount", null);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                //Update invalid login
                maceService.increaseInvalidLoginCount(loginJson.getUsername());
                maceService.lockIfNeeded(loginJson.getUsername());
                response.put("responseCode", "230");
                response.put("responseDesc", "Invalid Password");
                response.put("MemberAccounts", null);
                response.put("UserAccount", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response.put("responseCode", "210");
            response.put("responseDesc", "No User Account for entered username");
            response.put("MemberAccounts", null);
            response.put("UserAccount", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/viewAccountInfo/{memberCode}", method = RequestMethod.GET)
    public ResponseEntity<?> viewAccountInfo(@PathVariable("memberCode") String memberCode) {

        Map memberInfo = memService.getMemberInfo(memberCode);
        VerifiedMember verifiedMember = maceService.getLatestVerifiedMember(memberCode);
        Map userAccount = maceService.getMemUserAccountByMemberCode(memberCode);
        /*
        These are the dependents of the user account from the user account table
          List dependents = maceService.getUserAccountsDependents((int) userAccount.get("ID"));

          These are the dependents from the mace membership Database
          List dependents = memService.getDependents(memberCode);
         */
        List dependents = null;
        if (userAccount != null) {
            dependents = maceService.getUserAccountsDependents((int) userAccount.get("ID"));

        }

        List utilizations = claimsService.getMemberUtilization(memberCode);


        HashMap<String, Object> response = new HashMap<>();

        if (!memberInfo.isEmpty()) {
            response.put("MemberInfo", memberInfo);

            if (verifiedMember == null) {
                response.put("VerifiedMember", 0);
            } else {
                response.put("VerifiedMember", verifiedMember);
            }

            if (dependents == null) {
                response.put("Dependents", 0);
            } else {
                response.put("Dependents", dependents);
            }

            if (utilizations == null) {
                response.put("Utilization", 0);
            } else {
                response.put("Utilization", utilizations);
            }
            response.put("responseCode", 200);
            response.put("responseDesc", "Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("MemberInfo", 0);
            response.put("Utilization", 0);
            response.put("VerifiedMember", 0);
            response.put("Dependents", 0);
            response.put("responseCode", 404);
            response.put("responseDesc", "NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/searchMember/", method = RequestMethod.GET)
    public ResponseEntity<?> searchMember(@RequestParam("memberCode") String memberCode, @RequestParam(value = "dob", required = false) String date_of_birth,
                                          @RequestParam("deviceID") String deviceID, @RequestParam("appUserName") String appUserName,
                                          @RequestParam("hospitalCode") String hospitalCode, @RequestParam("searchType") String searchType) {
        //TODO Log params

        System.out.println("deviceID:" + deviceID);
        System.out.println("appUserName:" + appUserName);
        System.out.println("Scan Type: " + searchType);
        System.out.println("DOB: " + date_of_birth);

        Map memberInfo = memService.getMemberInfo(memberCode);
        List dependents = memService.getDependents(memberCode);
        List utilizations = claimsService.getMemberUtilization(memberCode);

        //TODO Still not defined by specs
        List<String> serviceTypesList = null;
        List hospitalDoctors = null;
        String sessionId = "";
        VerifiedMember verifiedMember = null;
        Boolean isBlackListed = false;
        Boolean isLocked = maceService.isMemberLocked(memberCode);

        HashMap<String, Object> response = new HashMap<>();
        MemberDetails memberDetails = memService.getMember(memberCode);
        Boolean hasMaternity = loaService.requestApprovalConsultation(memberCode, 1, hospitalCode).equals("41") ? false : true;

        response.put("hasMaternity", hasMaternity);

        if (null != memberInfo) {
            if (!memberInfo.isEmpty()) {

                if ("qrcode".equalsIgnoreCase(searchType) || searchType.toLowerCase() == null) {
                    serviceTypesList = maceService.getServiceTypes(memberCode, hospitalCode, memberDetails);
                    verifiedMember = maceService.getLatestVerifiedMember(memberCode);

                    //before return check if call log is necessary
                    maceService.logCustomerCareDetails(memberCode, serviceTypesList, isBlackListed, isLocked, deviceID, appUserName, searchType, hospitalCode, memberInfo, memberDetails);
                    return getVerifiedMemberResponseEntity(memberInfo, dependents, utilizations, serviceTypesList, hospitalDoctors, sessionId, verifiedMember, isBlackListed, isLocked, response);

                } else {
                    /**
                     * Start validating BDay
                     */
                    String bdayString = (String) memberInfo.get("BDAY");
                    System.out.println("bdayString:" + bdayString);
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
                    SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
                    Date bday = null;
                    Date bdayParam = null;
                    String convert = null;
                    String convert2 = null;

                    try {
                        bday = sdf.parse(bdayString);
                        bdayParam = sdfParam.parse(date_of_birth);
                        convert = sdfParam.format(bday);
                        convert2 = sdfParam.format(bdayParam);
                        if (convert.equals(convert2)) {
                            if(!isLocked)
                                maceService.deleteLogInvalidSearch(memberCode);
                            /**
                             * Empty Check for Verified Member
                             */
                            verifiedMember = maceService.getLatestVerifiedMember(memberCode);

                            serviceTypesList = maceService.getServiceTypes(memberCode, hospitalCode, memberDetails);
                            //before return check if call log is necessary

                            maceService.logCustomerCareDetails(memberCode, serviceTypesList, isBlackListed, isLocked, deviceID, appUserName, searchType, hospitalCode, memberInfo, memberDetails);
                            return getVerifiedMemberResponseEntity(memberInfo, dependents, utilizations, serviceTypesList, hospitalDoctors, sessionId, verifiedMember, isBlackListed, isLocked, response);
                        } else {
                            InvalidBdaySearch ibs = new InvalidBdaySearch();
                            ibs.setAccountCode(memberDetails.getACCOUNT_CODE());
                            ibs.setAccountName(memberDetails.getACCOUNT_NAME());
                            try {
                                ibs.setBdayEntered(bdayParam);
                                ibs.setMemBday(bday);
                            }catch(Exception e){}
                            ibs.setHospCode(hospitalCode);
                            ibs.setHospName(claimsService.getHospital(hospitalCode).getHospitalName());
                            ibs.setMemCode(memberCode);
                            ibs.setCoordinator(appUserName);
                            maceService.logInvalidSearch(ibs);
                            response.put("responseCode", "210");
                            response.put("responseDesc", "Member Code and Date Of Birth don’t match. Please scan your birth certificate and email it to macesupport@incuventure.net.");
                            response.put("MemberInfo", null);
                            response.put("Utilization", null);
                            response.put("Dependents", null);
                            response.put("ServiceType", null);
                            response.put("HospitalDoctor", null);
                            response.put("sessionID", null);
                            response.put("isLocked", isLocked);
                            response.put("isBlackListed", isBlackListed);
                            /**
                             * For Locked Members Log also in Mace SYS_CUST_CARE_LTBL
                             * */
                            //maceService.logCustomerCareDetails(memberCode, serviceTypesList, isBlackListed, isLocked, deviceID, appUserName, searchType, hospitalCode, memberInfo, memberDetails);
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.put("responseCode", "210");
                        response.put("responseDesc", "Problem Parsing Date of Birth");
                        response.put("MemberInfo", null);
                        response.put("Utilization", null);
                        response.put("Dependents", null);
                        response.put("ServiceType", null);
                        response.put("HospitalDoctor", null);
                        response.put("sessionID", null);
                        response.put("isBlackListed", isBlackListed);
                        response.put("isLocked", isLocked);
                        response.put("error", e.getMessage());
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                }
            }
        }
        response.put("MemberInfo", null);
        response.put("Utilization", null);
        response.put("Dependents", null);
        response.put("ServiceType", null);
        response.put("HospitalDoctor", null);
        response.put("sessionID", null);
        response.put("isBlackListed", isBlackListed);
        response.put("isLocked", isLocked);
        response.put("responseCode", "220");
        response.put("responseDesc", "NOT FOUND");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<?> getVerifiedMemberResponseEntity(Map memberInfo, List dependents, List utilizations, List<String> serviceTypesList, List hospitalDoctors, String sessionId, VerifiedMember verifiedMember, Boolean isBlackListed, Boolean isLocked, HashMap<String, Object> response) {
        if (null == verifiedMember) {
            verifiedMember = new VerifiedMember();
            response.put("responseCode", "230");
            response.put("MemberInfo", memberInfo);
            response.put("responseDesc", "Member is not verified");
            response.put("Utilization", utilizations);
            response.put("Dependents", dependents);
            response.put("VerifiedMember", verifiedMember);
            response.put("ServiceType", serviceTypesList);
            response.put("HospitalDoctor", "");
            response.put("sessionID", null);
            response.put("isBlackListed", isBlackListed);
            response.put("isLocked", isLocked);
            response.put("inpatientRequestCode", null);
            response.put("isAdmittedIp", false);
            response.put("erRequestCode", null);
            response.put("isAdmittedEr", false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            /**
             * IsVerified Check for Verified Member
             */
            if (0 == verifiedMember.getIsVerified()) {
                response.put("responseCode", "230");
                response.put("responseDesc", "Member is not verified");
                response.put("MemberInfo", memberInfo);
                response.put("Utilization", utilizations);
                response.put("Dependents", dependents);
                response.put("VerifiedMember", verifiedMember);
                response.put("HospitalDoctor", null);
                response.put("ServiceType", serviceTypesList);
                response.put("sessionID", null);
                response.put("isBlackListed", isBlackListed);
                response.put("isLocked", isLocked);
                response.put("inpatientRequestCode", null);
                response.put("isAdmittedIp", false);
                response.put("erRequestCode", null);
                response.put("isAdmittedEr", false);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                if (1 == verifiedMember.getIsVerified()) {
                    response.put("responseCode", 200);
                    response.put("responseDesc", "Member is valid");
                    response.put("MemberInfo", memberInfo);
                    response.put("Utilization", utilizations);
                    response.put("Dependents", dependents);
                    response.put("ServiceType", serviceTypesList);
                    response.put("HospitalDoctor", hospitalDoctors);
                    response.put("VerifiedMember", verifiedMember);
                    response.put("sessionID", sessionId);
                    response.put("isBlackListed", isBlackListed);
                    response.put("isLocked", isLocked);
                    //Added for inpatient info
                    String requestCode = maceService.getLastReqCodeIpAdmitted(verifiedMember.getMemCode());
                    response.put("inpatientRequestCode", requestCode);
                    response.put("isAdmittedIp", null != requestCode);
                    response.put("erRequestCode", null);
                    response.put("isAdmittedEr", false);
//                    String erRequestCode = maceService.getLastReqCodeErAdmitted(verifiedMember.getMemCode());
//                    response.put("erRequestCode", erRequestCode);
//                    response.put("isAdmittedEr", null != erRequestCode);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else if (1 == verifiedMember.getIsSkipped()) {
                    response.put("responseCode", "230");
                    response.put("responseDesc", "Member is not verified");
                    response.put("Utilization", utilizations);
                    response.put("MemberInfo", memberInfo);
                    response.put("Dependents", dependents);
                    response.put("VerifiedMember", verifiedMember);
                    response.put("HospitalDoctor", null);
                    response.put("ServiceType", serviceTypesList);
                    response.put("sessionID", null);
                    response.put("isBlackListed", isBlackListed);
                    response.put("isLocked", isLocked);
                    response.put("inpatientRequestCode", null);
                    response.put("isAdmittedIp", false);
                    response.put("erRequestCode", null);
                    response.put("isAdmittedEr", false);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    response.put("responseCode", "230");
                    response.put("responseDesc", "Member is not verified");
                    response.put("MemberInfo", memberInfo);
                    response.put("Dependents", dependents);
                    response.put("Utilization", utilizations);
                    response.put("VerifiedMember", verifiedMember);
                    response.put("ServiceType", serviceTypesList);
                    response.put("HospitalDoctor", "");
                    response.put("sessionID", null);
                    response.put("isBlackListed", isBlackListed);
                    response.put("isLocked", isLocked);
                    response.put("inpatientRequestCode", null);
                    response.put("isAdmittedIp", false);
                    response.put("erRequestCode", null);
                    response.put("isAdmittedEr", false);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        }
    }


    @RequestMapping(value = "/updateContactNo", method = RequestMethod.GET)
    public ResponseEntity<?> updateContactNo(@RequestParam("appUsername") String appUsername,
                                             @RequestParam("contactNo") String contactNo) {
        HashMap<String, Object> response = new HashMap<>();

        try {
            maceService.updateContactNo(appUsername, contactNo);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully saved Contact.");
        } catch (Exception e) {
            response.put("responseCode", 210);
            response.put("responseDesc", "Failed to save Contact.");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/getMemberUtilization/{memberCode}", method = RequestMethod.GET)
    public ResponseEntity<?> getMemberUtilization(@PathVariable("memberCode") String memberCode) {
        System.out.println("getMemberUtilization:" + memberCode);
        HashMap<String, Object> response = new HashMap<>();

        List memberUtilization = claimsService.getMemberUtilization(memberCode);
        if (!memberUtilization.isEmpty()) {
            response.put("Dependents", memberUtilization);
            response.put("responseCode", 200);
            response.put("responseDesc", "Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Utilization", null);
            response.put("responseCode", 404);
            response.put("responseDesc", "NOT FOUND");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    //TODO: FIX
    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file, @RequestParam("memCode") String memCode,
                                         @RequestParam("appUsername") String appUsername, @RequestParam(name = "userType", required = false) String userType) {
        String name = file.getName();
        System.out.println(name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String id = sdf.format(new Date());
        String contentType = file.getContentType();
        ImageHolder imageHolder = new ImageHolder();
        imageHolder.setMemCode(memCode);
        imageHolder.setFileId(id);
        imageHolder.setContentType(contentType);
        imageHolder.setOriginalFileName(file.getOriginalFilename());
        imageHolder.setFileNameNoSuffix(file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf(".")));
        imageHolder.setFileSuffix(file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")));

        HashMap<String, Object> response = new HashMap<>();

        if (!file.isEmpty()) {
            try {
                imageHolder.setContent(file.getBytes());
                maceService.saveImage(imageHolder);
                VerifiedMember verifiedMember = maceService.getLatestVerifiedMember(memCode);
                if (null != verifiedMember) {
                    verifiedMember.setAppUsername(appUsername);
                    verifiedMember.setIsSkipped(0);
                    verifiedMember.setIsVerified(0);
                    verifiedMember.setVerifyDate(new Date());
                    verifiedMember.setStatus("UNVERIFIED");
                    verifiedMemberService.saveMember(verifiedMember);
                }

                /**
                 * Check if verified picture and change status and skip
                 *
                 */

                response.put("responseCode", 200);
                response.put("responseDesc", "Success");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } catch (Exception e) {
                response.put("responseCode", 500);
                response.put("responseDesc", "Internal Server Error");
                response.put("error", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response.put("responseCode", 204);
            response.put("responseDesc", "File is empty");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/downloadPicture/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<?> downloadPicture(@PathVariable("fileId") String fileId) {
        ImageHolder image = maceService.retrieveImage(fileId);

        HttpHeaders header = new HttpHeaders();
        header.setContentLength(image.getContent().length);
        header.setContentType(MediaType.parseMediaType(image.getContentType()));
        header.set("Content-Disposition",
                "attachment; filename=" + image.getOriginalFileName());

        return new ResponseEntity<>(image.getContent(), header, HttpStatus.OK);
    }

    @RequestMapping(value = "/deletepicture/{memCode}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePicture(@PathVariable("memCode") String memCode) {
        maceService.deleteImage(memCode);

        return new ResponseEntity<>("Image Deleted.", HttpStatus.OK);
    }


    @RequestMapping(value = "/verifyAccount/", method = RequestMethod.POST)
    public ResponseEntity<?> verifyAccount(@RequestBody VerifiedMember verifiedMember) {

        verifiedMember.setStatus("ACTIVE");
        verifiedMember.setIsVerified(1);
        verifiedMember.setVerifyDate(new Date());
        verifiedMemberService.saveMember(verifiedMember);
        HashMap<String, Object> response = new HashMap<>();
        response.put("responseCode", 200);
        response.put("responseDesc", "Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/skipVerification/", method = RequestMethod.POST)
    public ResponseEntity<?> skipVerification(@RequestBody VerifiedMember verifiedMember) {

        verifiedMember.setStatus("UNVERIFIED");
        verifiedMember.setIdType(null);
        verifiedMember.setIdNumber(null);
        verifiedMember.setIsVerified(0);
        verifiedMember.setIsSkipped(1);
        verifiedMember.setVerifyDate(new Date());
        verifiedMemberService.saveMember(verifiedMember);
        HashMap<String, Object> response = new HashMap<>();
        response.put("responseCode", 200);
        response.put("responseDesc", "Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/addOtherAccount/", method = RequestMethod.POST)
    public ResponseEntity<?> addOtherAccount(@RequestBody AddAccountJson addAccountJson) {
        System.out.println(addAccountJson);
        System.out.println(addAccountJson.getUsername());
        System.out.println(addAccountJson.getDepMemberCode());
        System.out.println(addAccountJson.getMemberCode());

        HashMap<String, Object> response = new HashMap<>();

        if (maceService.hasExistingMemUserAcct(addAccountJson.getUsername(), addAccountJson.getMemberCode())) {
            Map userAccount = maceService.getMemUserAccount(addAccountJson.getUsername());
            System.out.println("userAccount:" + userAccount);
            if (!maceService.checkIfMemberCodeExistingInAccount((int) userAccount.get("ID"), addAccountJson.getDepMemberCode())) {

                MemberDetails memberDetails = memService.getMember(addAccountJson.getDepMemberCode());
                //Check if present in Dependency Table
                if (memService.checkIfDependent(addAccountJson.getMemberCode(), addAccountJson.getDepMemberCode())) {

                    maceService.saveUserAccount((int) userAccount.get("ID"), memberDetails, addAccountJson.getDepMemberCode());
                    response.put("responseCode", "200");
                    response.put("responseDesc", "Account successfully added");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    response.put("responseCode", "231");
                    response.put("responseDesc", "Not a dependent.");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

            } else {
                response.put("responseCode", "230");
                response.put("responseDesc", "Account has already been added.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } else {
            response.put("responseCode", "230");
            response.put("responseDesc", "Member Account was not found");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }


    @RequestMapping(value = "/getMemberInfo/{memberCode}", method = RequestMethod.GET)
    public ResponseEntity<?> getMemberInfo(@PathVariable("memberCode") String memberCode) {
        System.out.println("getMemberInfo:" + memberCode);
        HashMap<String, Object> response = new HashMap<>();

        Map memberInfo = memService.getMemberInfo(memberCode);
        if (!memberInfo.isEmpty()) {
            String memStat = (String) memberInfo.get("Mem_OStat_Code");
            response.put("memStat", memStat);
            response.put("MemberInfo", memberInfo);
            response.put("responseCode", 200);
            response.put("responseDesc", "Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("MemberInfo", null);
            response.put("responseCode", 404);
            response.put("responseDesc", "NOT FOUND");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/requestChangePassword/", method = RequestMethod.POST)
    public ResponseEntity<?> requestChangePassword(@RequestBody RequestChangePasswordJson requestChangePasswordJson) {
        System.out.println(requestChangePasswordJson);
        System.out.println(requestChangePasswordJson.getEmail());
        System.out.println(requestChangePasswordJson.getMemberCode());

        //TODO Inefficient too many calls to the Database
        HashMap<String, Object> response = new HashMap<>();

        Map user = maceService.findMemUserByEmailAndMemberCode(requestChangePasswordJson.getEmail(), requestChangePasswordJson.getMemberCode());
        Map appUser = maceService.getMemUserAccountByMemberCode(requestChangePasswordJson.getMemberCode());
        maceService.unlockUserByMemberCode(appUser.get("MEM_CODE").toString());

        if (user != null) {
            String newPassword = maceService.generateNewPassword();
            String email = requestChangePasswordJson.getEmail();
            maceService.changePasswordAuto(user.get("USERNAME").toString(), newPassword.substring(0, 8));
            try {
                emailService.sendPasswordReset(newPassword.substring(0, 8), email);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.put("responseCode", "200");
            response.put("responseDesc", "Password Change Request Successful.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("responseCode", "230");
            response.put("responseDesc", "Incorrect Username or MemberCode.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/changePassword/", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordJson changePasswordJson) {
        System.out.println(changePasswordJson);
        System.out.println(changePasswordJson.getUsername());
        System.out.println(changePasswordJson.getOldPassword());
        System.out.println(changePasswordJson.getNewPassword());

        HashMap<String, Object> response = new HashMap<>();


        if (maceService.hasExistingMemUserAcct(changePasswordJson.getUsername(), null)) {
            Map userAccount = maceService.getMemUserAccount(changePasswordJson.getUsername());
            System.out.println("userAccount:" + userAccount);
            if (maceService.matchUsernameAndPassword(changePasswordJson.getUsername(), changePasswordJson.getOldPassword())) {
                maceService.changePassword(changePasswordJson.getUsername(), changePasswordJson.getNewPassword());

                response.put("responseCode", "200");
                response.put("responseDesc", "Password Successfully Changed.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("responseCode", "230");
                response.put("responseDesc", "Incorrect username or password.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response.put("responseCode", "230");
            response.put("responseDesc", "Incorrect username or password.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/registerPin/", method = RequestMethod.POST)
    public ResponseEntity<?> registerPin(@RequestBody RegisterPinJson registerPinJson) {
        System.out.println(registerPinJson);
        System.out.println(registerPinJson.getUsername());
        System.out.println(registerPinJson.getPassword());
        System.out.println(registerPinJson.getPassword());

        HashMap<String, Object> response = new HashMap<>();

        if (maceService.hasExistingMemUserAcct(registerPinJson.getUsername(), null)) {
            Map userAccount = maceService.getMemUserAccount(registerPinJson.getUsername());
            System.out.println("userAccount:" + userAccount);
            System.out.println("PIN:" + userAccount.get("PIN"));
            if (maceService.matchUsernameAndPassword(registerPinJson.getUsername(), registerPinJson.getPassword())) {

                if (userAccount.get("PIN") == null) {
                    maceService.changePin(registerPinJson.getUsername(), registerPinJson.getPin());
                    response.put("responseCode", "200");
                    response.put("responseDesc", "PIN Successfully Changed.");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    response.put("responseCode", "230");
                    response.put("responseDesc", "PIN exists use update pin instead.");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }


            } else {
                response.put("responseCode", "230");
                response.put("responseDesc", "Incorrect username or password.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response.put("responseCode", "230");
            response.put("responseDesc", "Incorrect username or password.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/updatePin/", method = RequestMethod.POST)
    public ResponseEntity<?> updatePin(@RequestBody UpdatePinJson updatePinJson) {
        System.out.println(updatePinJson);
        System.out.println(updatePinJson.getUsername());
        System.out.println(updatePinJson.getOldPin());
        System.out.println(updatePinJson.getNewPin());

        HashMap<String, Object> response = new HashMap<>();


        if (maceService.hasExistingMemUserAcct(updatePinJson.getUsername(), null)) {
            Map userAccount = maceService.getMemUserAccount(updatePinJson.getUsername());
            System.out.println("userAccount:" + userAccount);
            if (maceService.matchUsernameAndPin(updatePinJson.getUsername(), updatePinJson.getOldPin())) {
                maceService.changePin(updatePinJson.getUsername(), updatePinJson.getNewPin());

                response.put("responseCode", "200");
                response.put("responseDesc", "PIN Successfully Changed.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("responseCode", "230");
                response.put("responseDesc", "Incorrect username or password.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            response.put("responseCode", "230");
            response.put("responseDesc", "Incorrect username or password.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/validatePin/", method = RequestMethod.POST)
    public ResponseEntity<?> validatePin(@RequestBody ValidatePinJson validatePinJson) {
        HashMap<String, Object> response = new HashMap<>();

        boolean validated = validatePinJson.getPin().equals(maceService.getPinByMemberCode(validatePinJson.getMemberCode()));
        response.put("result", validated);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "getLoaByMemberCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getLoaByMemberCode(@RequestParam("memberCode") String memberCode) {
        logger.info("getLoaByApprovalNumber");
        logger.info("getLoaByApprovalNumber" + memberCode);

        List loaList = maceService.getMemberLOAList(memberCode);
        HashMap<String, Object> response = new HashMap<>();

        response.put("loaList", loaList);
        logger.info("getLoaByProviderAndMemberCode OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/getSortedMemberLoaList", method = RequestMethod.POST)
    public ResponseEntity<?> getSortedMemberLoaList(@RequestBody MemberLoaFilter loaFilter){
        HashMap<String, Object> response  = new HashMap<>();
        List loaList = maceService.getMemberFilteredLOAList(loaFilter);
        if(null != loaList) {
            response.put("loaList", loaList);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully retrieved list.");
        }else{
            response.put("responseCode", 210);
            response.put("responseDesc", "Nothing found.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "getLoaByMemberCode2/", method = RequestMethod.GET)
    public ResponseEntity<?> getLoaByMemberCode2(@RequestParam("memberCode") String memberCode) {
        logger.info("getLoaByApprovalNumber");
        logger.info("getLoaByApprovalNumber" + memberCode);

        List<LoaMaceJson> loaList = loaService.getLoaByMemberCodeJson(memberCode);
        HashMap<String, Object> response = new HashMap<>();

        response.put("loaList", loaList);
        logger.info("getLoaByProviderAndMemberCode OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private static UserJson userToUserJson(User user) {
        // String mi, String sex, String registerDevice, String phone, String email
        return new UserJson(
                user.getUsername(),
                null,
                user.getMemberCode(),
                user.getBirthDateString(),
                user.getLname(),
                user.getFname(),
                user.getMi(),
                user.getSex(),
                user.getRegisterDevice(),
                user.getPhoneNo(),
                user.getEmail()
        );
    }
}


