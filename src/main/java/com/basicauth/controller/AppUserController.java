package com.basicauth.controller;

/**
 * Created by Giancarlo Angulo.
 */

import com.basicauth.data.*;
import com.basicauth.service.*;
import com.basicauth.types.LoginJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;


@RestController
@RequestMapping(value = "/app")
public class AppUserController {

    private static final Logger logger = LoggerFactory.getLogger(AppUserController.class);

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private MaceService maceService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private LoaService loaService;

    @Autowired
    private MemService memService;

    /**
     * @return
     */
    @RequestMapping(value = "/viewAppUsers/", method = RequestMethod.GET)
    public ResponseEntity<?> viewAppUsers() {
        logger.debug("viewAppUsers");
        List<AppUser> users = appUserService.findAllUsers();
        logger.debug("users", users);
        HashMap<String, Object> response = new HashMap<>();
        response.put("responseCode", "OK");
        response.put("responseDesc", "All users.");
        if (users.isEmpty()) {
            logger.debug("users.isEmpty()");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("users", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAppUser/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AppUser> getAppUser(@PathVariable("id") long id) {
        logger.debug("id", id);
        System.out.println("Fetching User with id " + id);
        AppUser user = appUserService.findById(id);
        if (user == null) {
            logger.debug("User not found", id);
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.debug("User found", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/deactivateAppUser/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deactivateAppUser(@PathVariable("username") String username) {
        System.out.println("Fetching & Deleting User with username " + username);
        logger.debug("deactivateAppUser", username);

        AppUser appUser = appUserService.findByUsername(username);
        if (appUser == null) {
            logger.debug("Unable to delete. User with username ", username);
            System.out.println("Unable to delete. User with username " + username + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        appUser.setStatus("INACTIVE");
        appUserService.saveUser(appUser);
        logger.debug("deactivateAppUser", appUser);
        HashMap<String, Object> response = new HashMap<>();
        response.put("responseCode", "200");
        response.put("responseDesc", "Deactivated User");
        response.put("user", appUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/editAppUser/", method = RequestMethod.PUT)
    public ResponseEntity<?> editAppUser(@RequestBody AppUser appUser, UriComponentsBuilder ucBuilder) {
        logger.debug("editAppUser", appUser);
        System.out.println("Updating User " + appUser.getUsername());
        System.out.println("with password " + appUser.getPassword());
        System.out.println("with fname " + appUser.getFname());
        System.out.println("with lname " + appUser.getLname());
        System.out.println("with mi " + appUser.getMname());
        System.out.println("with phone " + appUser.getPhoneno());
        System.out.println("with birthDate " + appUser.getEmail());
        System.out.println("with birthDate " + appUser.getCostcenter());
        System.out.println("with birthDate " + appUser.getHospital());
        System.out.println("with birthDate " + appUser.getLocation());
        System.out.println("with birthDate " + appUser.getRole());

        //Username cannot be changed
        AppUser appUser1 = appUserService.findByUsername(appUser.getUsername());
        appUser1.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser1.setFname(appUser.getFname());
        appUser1.setLname(appUser.getLname());
        appUser1.setMname(appUser.getMname());
        appUser1.setPhoneno(appUser.getPhoneno());
        appUser1.setEmail(appUser.getEmail());
        appUser1.setCostcenter(appUser.getCostcenter());
        appUser1.setHospital(appUser.getHospital());
        appUser1.setLocation(appUser.getLocation());
        appUser1.setRole(appUser.getRole());

        appUserService.saveUser(appUser1);
        AppUser user = appUserService.findByUsername(appUser.getUsername());

        logger.debug("editAppUser after save", user);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);

        HashMap<String, Object> response = new HashMap<>();
        response.put("responseCode", "200");
        response.put("responseDesc", "Updated User" + appUser.getUsername());
        response.put("user", user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/addAppUser/", method = RequestMethod.POST)
    public ResponseEntity<?> addAppUser(@RequestBody AppUser appUser) {
        System.out.println("Creating User " + appUser.getUsername());
        System.out.println("with password " + appUser.getPassword());
        System.out.println("with fname " + appUser.getFname());
        System.out.println("with lname " + appUser.getLname());
        System.out.println("with mi " + appUser.getMname());
        System.out.println("with phone " + appUser.getPhoneno());
        System.out.println("with email " + appUser.getEmail());
        System.out.println("with costCenter " + appUser.getCostcenter());
        System.out.println("with hospital " + appUser.getHospital());
        System.out.println("with location " + appUser.getLocation());
        System.out.println("with role " + appUser.getRole());

        HashMap<String, Object> response = new HashMap<>();


        if (appUserService.isUsernameUsed(appUser.getUsername())) {
            System.out.println("A User with name " + appUser.getUsername() + " already exist");
            response.put("responseCode", "210");
            response.put("responseDesc", "Account with username exists");
            response.put("user", null);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setStatus("ACTIVE");
        appUserService.saveUser(appUser);
        AppUser user = appUserService.findByUsername(appUser.getUsername());


        response.put("responseCode", "200");
        response.put("responseDesc", "Successfully created user");
        response.put("user", user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO: Fix
    @RequestMapping(value = "/loginAppUser/", method = RequestMethod.POST)
    public ResponseEntity<?> loginAppUser(@RequestBody LoginJson loginJson) {

        System.out.println(loginJson.getUsername());
        System.out.println(loginJson.getPassword());
        System.out.println(loginJson.getDeviceId());
        System.out.println(loginJson.getVersionNo());
        //TODO Log user
        HashMap<String, Object> response = new HashMap<>();

        if (maceService.hasExistingAppUserAcct(loginJson.getUsername())) {
            if (maceService.matchUsernameAndPasswordForAppUser(loginJson.getUsername(), loginJson.getPassword())) {
                if (maceService.isAppUserActive(loginJson.getUsername())) {
                    AppUser appUser = maceService.findbyUsername(loginJson.getUsername());
                    AppVersion appVersion = maceService.getAppVersionByUserType("COORDINATOR");
                    //Check if Appuser is registered
                    List<String> users = maceService.getAppUsersByDeviceId(loginJson.getDeviceId());
                    boolean isAuthorized = true;
//                    if (null == users || users.isEmpty()) {
//                        response.put("responseCode", 260);
//                        response.put("responseDesc", "Device not registered.");
//                        return new ResponseEntity<>(response, HttpStatus.OK);
//                    } else {
                        /**
                         *
                         * */
//                        if (null != users) {
//                            for (String user : users) {
//                                if (loginJson.getUsername().equalsIgnoreCase(user)) {
//                                    isAuthorized = false;
//                                    break;
//                                }
//                            }
//                        }
                        if (!isAuthorized) {
                            response.put("responseCode", 270);
                            response.put("responseDesc", "Device is not assigned to AppUser.");
                            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
                        } else {
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
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Hospital hospital = claimsService.getHospital(appUser.getHospital());
                            response.put("tables", maceService.getLastUpdatesOnTables());
                            response.put("user", appUser);
                            response.put("hospital", hospital);
                            return new ResponseEntity<>(response, HttpStatus.OK);
//                        }
                    }
                } else {
                    response.put("responseCode", 230);
                    response.put("responseDesc", "App User is INACTIVE. Please see Medicard System Administrator.");
                    response.put("hospitalDoctor", null);
                    response.put("user", null);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                response.put("responseCode", 250);
                response.put("responseDesc", "Invalid Password");
                response.put("hospitalDoctor", null);
                response.put("user", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } else {

            response.put("responseCode", 210);
            response.put("responseDesc", "No User Account for entered username");
            response.put("hospitalDoctor", null);
            response.put("user", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/deletePicture/{memCode}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePicture(@PathVariable("memCode") String memCode) {
        maceService.deleteImage(memCode);

        HashMap<String, Object> response = new HashMap<>();
        response.put("responseCode", "200");
        response.put("responseDesc", "Image Deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/validateProvider/", method = RequestMethod.POST)
    public ResponseEntity<?> validateProvider(@RequestBody ValidateProviderJson providerJson) {
        System.out.println("validateProvider:" + providerJson.getFirstName());
        System.out.println("Last Name:" + providerJson.getLastName());
        System.out.println("Middle Name:" + providerJson.getMiddleName());
        System.out.println("Provider Type:" + providerJson.getProviderType());

        HashMap<String, Object> response = new HashMap<>();

        Doctor doctor = claimsService.getDoctor(providerJson.getDoctorCode(), false);
        if (null == doctor) {
            response.put("responseCode", "220");
            response.put("responseDesc", "Doctor Code not found");
            response.put("doctor", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        if (providerJson.getProviderType().equals("DENTIST") || doctor.getSpecDesc().toLowerCase().contains("derma")) {
            response.put("responseCode", "250");
            response.put("responseDesc", "Doctor's Specialization not valid for registration.");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }

        if (appUserService.isDoctorCodeUsed(providerJson.getDoctorCode())) {
            response.put("responseCode", "210");
            response.put("responseDesc", "Doctor Code has already been used");
            response.put("doctor", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        //check doctor code vs names

        String actualFname = doctor.getDocFname();
        String actualLname = doctor.getDocLname();
        Boolean firstNameGood = false;
        String actualName = actualFname.trim().toLowerCase();
        String enteredName = providerJson.getFirstName().trim().toLowerCase();
        String[] names = actualName.split(" ");
        for (int i = 0; i < names.length; i++) {
            if (enteredName.equals(names[i])) {
                firstNameGood = true;
            }
        }
        if (actualFname.equalsIgnoreCase(providerJson.getFirstName()))
            firstNameGood = true;

        if (!providerJson.getLastName().trim().equalsIgnoreCase(actualLname.trim())) {
            response.put("responseCode", "230");
            response.put("responseDesc", "Doctor Lastname Incorrect.");
            response.put("doctor", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (!firstNameGood) {
            response.put("responseCode", "240");
            response.put("responseDesc", "Doctor Firstname Incorrect.");
            response.put("doctor", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("responseCode", "200");
        response.put("responseDesc", "Successfully verified provider");
        response.put("doctor", doctor);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/registerProvider/", method = RequestMethod.POST)
    public ResponseEntity<?> registerProvider(@RequestBody AppUser appUser) {
        System.out.println("Creating User " + appUser.getUsername());
        System.out.println("with password " + appUser.getPassword());
        System.out.println("with fname " + appUser.getFname());
        System.out.println("with lname " + appUser.getLname());
        System.out.println("with mi " + appUser.getMname());
        System.out.println("with phone " + appUser.getPhoneno());
        System.out.println("with email " + appUser.getEmail());
        System.out.println("with costCenter " + appUser.getCostcenter());
        System.out.println("with hospital " + appUser.getHospital());
        System.out.println("with location " + appUser.getLocation());
        System.out.println("with role " + appUser.getRole());
        System.out.println("with doctor code " + appUser.getDoctorCode());

        HashMap<String, Object> response = new HashMap<>();
        if (appUserService.isUsernameUsed(appUser.getUsername())) {
            System.out.println("A User with name " + appUser.getUsername() + " already exist");
            response.put("responseCode", "210");
            response.put("responseDesc", "Account with username exists");
            response.put("user", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setStatus("ACTIVE");
        appUserService.saveUser(appUser);
        AppUser user = appUserService.findByUsername(appUser.getUsername());

        response.put("responseCode", "200");
        response.put("responseDesc", "Successfully created Provider");
        response.put("provider", user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/loginProvider/", method = RequestMethod.POST)
    public ResponseEntity<?> loginProvider(@RequestBody LoginJson loginJson) {

        System.out.println(loginJson.getUsername());
        System.out.println(loginJson.getPassword());
        System.out.println(loginJson.getDeviceId());
        //TODO Log user
        HashMap<String, Object> response = new HashMap<>();

        if (maceService.hasExistingAppUserAcct(loginJson.getUsername())) {
            if (maceService.matchUsernameAndPasswordForAppUser(loginJson.getUsername(), loginJson.getPassword())) {
                if (maceService.isAppUserActive(loginJson.getUsername())) {

                    AppUser appUser = maceService.findbyUsername(loginJson.getUsername());
                    Doctor doctor = null;
                    List hospitalDoctors = null;
                    if (null != appUser.getDoctorCode()) {
                        doctor = claimsService.getDoctor(appUser.getDoctorCode(), false);
                        System.out.println("doctor:" + doctor);
                        hospitalDoctors = claimsService.getHospitalOfDoctors(appUser.getDoctorCode());
                    }

                    response.put("responseCode", "200");
                    response.put("responseDesc", "Login Successful.");
                    response.put("user", appUser);
                    response.put("doctor", doctor);
                    response.put("hospitalDoctors", hospitalDoctors);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    response.put("responseCode", "230");
                    response.put("responseDesc", "App User is INACTIVE. Please see Medicard System Administrator.");
                    response.put("user", null);
                    response.put("doctor", null);
                    response.put("hospitalDoctors", null);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                response.put("responseCode", "250");
                response.put("responseDesc", "Invalid Password");
                response.put("user", null);
                response.put("doctor", null);
                response.put("hospitalDoctors", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } else {

            response.put("responseCode", "210");
            response.put("responseDesc", "No User Account for entered username");
            response.put("user", null);
            response.put("doctor", null);
            response.put("hospitalDoctors", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "getLoaByProviderAndMemberCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getLoaByProviderAndMemberCode(@RequestParam("username") String username, @RequestParam("memberCode") String memberCode) {
        logger.info("getLoaByProviderAndMemberCode");
        logger.info("getLoaByProviderAndMemberCode" + username);
        logger.info("getLoaByProviderAndMemberCode" + memberCode);

        AppUser appUser = maceService.findbyUsername(username);
        String doctorCode = appUser.getDoctorCode();
        logger.info("getLoaByProviderAndMemberCode" + doctorCode);


        List loaList = loaService.getLoaByProviderAndMemberCode(doctorCode, memberCode);
        HashMap<String, Object> response = new HashMap<>();
        response.put("loaList", loaList);
        logger.info("getLoaByProviderAndMemberCode OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "getLoaByApprovalNumber/", method = RequestMethod.GET)
    public ResponseEntity<?> getLoaByApprovalNumber(@RequestParam("username") String username, @RequestParam("approvalNumber") String approvalNumber) {
        logger.info("getLoaByApprovalNumber");
        logger.info("getLoaByApprovalNumber:" + approvalNumber);
        HashMap<String, Object> response = new HashMap<>();
        try {
            AppUser appUser = maceService.findbyUsername(username);
            String doctorCode = appUser.getDoctorCode();
            logger.info("getLoaByProviderAndMemberCode" + doctorCode);

            LoaMace loa = loaService.getLoaByApprovalNumberAndDoctorCode(approvalNumber, doctorCode);
            if (null != loa) {
                loa.setRemarks(loa.getRemarks().replace("APPROVED - ", ""));
                //Check date
                Date approvalDate = loa.getApprovalDate();
                Date currentDate = new Date();
                Calendar calendar = Calendar.getInstance();

                calendar.setTime(approvalDate);
                calendar.add(Calendar.DATE, 3);
                calendar.set(Calendar.HOUR, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                approvalDate = calendar.getTime();
                Map userAccount = null;
                if (approvalDate.compareTo(currentDate) >= 0) {
                    userAccount = maceService.getMemUserAccountByMemberCode(loa.getMemberCode());
                    if (null == userAccount) {
                        userAccount = memService.getMemberFromUWDependent(loa.getMemberCode());
                        if (null == userAccount) {
                            userAccount = memService.getMemberFromUWPrincipal(loa.getMemberCode());
                        }
                    }
                    MemberDetails memberInfo = memService.getMember(loa.getMemberCode());

                    response.put("loa", loa);
                    response.put("responseCode", 200);
                    response.put("userAccount", userAccount);
                    response.put("memberInfo", memberInfo);
                    logger.info("getLoaByProviderAndMemberCode OK");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    response.put("error", "Loa is expired.");
                    response.put("responseCode", 230);
                    response.put("responseDesc", "ApprovalNo or DoctorCode Incorrect");
                    logger.info("getLoaByProviderAndMemberCode OK");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

            } else {
                response.put("error", "No loa of approval number found for current provider.");
                response.put("responseCode", 220);
                response.put("responseDesc", "ApprovalNo or DoctorCode Incorrect");
                logger.info("getLoaByProviderAndMemberCode OK");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }


        } catch (Exception e) {
            response.put("error", e.getMessage());
            response.put("responseCode", 210);
            logger.info("getLoaByProviderAndMemberCode OK");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "getLoaByProvider/", method = RequestMethod.GET)
    public ResponseEntity<?> getLoaByProvider(@RequestParam("username") String username) {
        logger.info("getLoaByProvider");
        logger.info("getLoaByProvider" + username);

        AppUser appUser = maceService.findbyUsername(username);
        String doctorCode = appUser.getDoctorCode();
        logger.info("getLoaByProvider" + doctorCode);

        List loaList = loaService.getLoaByProvider(doctorCode);
        HashMap<String, Object> response = new HashMap<>();
        logger.info("getLoaByProviderAndMemberCode OK");
        response.put("loaList", loaList);
        response.put("responseCode", 200);
        response.put("responseDesc", "Provider Retrieved");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/addConsultationRecord/", method = RequestMethod.POST)
    public ResponseEntity<?> addConsultationRecord(@RequestBody ConsultationRecordJson consultationRecordJson) {

        System.out.println(consultationRecordJson.getUsername());

        HashMap<String, Object> response = new HashMap<>();
        //TODO Existing Approval number
        //TODO Does not exist in consultation
        if (maceService.hasExistingAppUserAcct(consultationRecordJson.getUsername())) {
            loaService.insertConsultationRecord(consultationRecordJson);
            response.put("responseCode", "200");
            response.put("responseDesc", "Consultation Record Successfully Added.");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {

            response.put("responseCode", "210");
            response.put("responseDesc", "Error adding Consultation Record.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/addConsultationRecordVersion02/", method = RequestMethod.POST)
    public ResponseEntity<?> addConsultationRecordVersion02(@RequestBody ConsultationRecordVersion02Json consultationRecordJson) {

        System.out.println(consultationRecordJson.getUsername());
        System.out.println("VERSION 2");
        System.out.println("MEMBER CODE : " + consultationRecordJson.getMemberCode());
        System.out.println("APPROVAL NO : " + consultationRecordJson.getApprovalNumber());
        System.out.println("DISCLAIMERTICKED : " + consultationRecordJson.getDisclaimerTicked());

        HashMap<String, Object> response = new HashMap<>();
        //TODO Existing Approval number
        //TODO Does not exist in consultation
        if (maceService.hasExistingAppUserAcct(consultationRecordJson.getUsername())) {
            loaService.insertConsultationRecordVersion2(consultationRecordJson);
            maceService.checkDisclaimerForChanges(consultationRecordJson.getMemberCode(), consultationRecordJson.getDisclaimerTicked());
            maceService.updateLOADisclaimerByApprovalNo(consultationRecordJson.getApprovalNumber(), consultationRecordJson.getDisclaimerTicked());
            response.put("consultationRecordJson", consultationRecordJson);
            response.put("responseCode", "200");
            response.put("responseDesc", "Consultation Record Successfully Added.");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {

            response.put("responseCode", "210");
            response.put("responseDesc", "Error adding Consultation Record.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getConsultationRecordVersion02/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getConsultationRecordVersion02(@PathVariable int id) {

        HashMap<String, Object> response = new HashMap<>();
        //TODO Existing Approval number
        //TODO Does not exist in consultation
        ConsultationRecordVersion02Json consultationRecordVersion02Json = loaService.getConsultationRecordVersion2(id);
        if (consultationRecordVersion02Json != null) {
            response.put("consultationRecordVersion02Json", consultationRecordVersion02Json);
            response.put("responseCode", "200");
            response.put("responseDesc", "Consultation Record Successfully Added.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("consultationRecordVersion02Json", "");
            response.put("responseCode", "210");
            response.put("responseDesc", "Consultation Record Not Found.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/addConsultationRecordVersion03/", method = RequestMethod.POST)
    public ResponseEntity<?> addConsultationRecordVersion03(@RequestBody ConsultationRecordVersion03Json consultationRecordJson) {

        System.out.println(consultationRecordJson.getUsername());
        System.out.println("VERSION 3");
        System.out.println("MEMBER CODE : " + consultationRecordJson.getMemberCode());
        System.out.println("APPROVAL NO : " + consultationRecordJson.getApprovalNumber());
        System.out.println("DISCLAIMERTICKED : " + consultationRecordJson.getDisclaimerTicked());

        HashMap<String, Object> response = new HashMap<>();
        //TODO Existing Approval number
        //TODO Does not exist in consultation
        if (maceService.hasExistingAppUserAcct(consultationRecordJson.getUsername())) {
            loaService.insertConsultationRecordVersion3(consultationRecordJson);
            maceService.checkDisclaimerForChanges(consultationRecordJson.getMemberCode(), consultationRecordJson.getDisclaimerTicked());
            //maceService.updateLOADisclaimerByApprovalNo(consultationRecordJson.getApprovalNumber(), consultationRecordJson.getDisclaimerTicked());
            response.put("consultationRecordJson", consultationRecordJson);
            response.put("responseCode", "200");
            response.put("responseDesc", "Consultation Record Successfully Added.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("responseCode", "210");
            response.put("responseDesc", "Error adding Consultation Record.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getConsultationRecordVersion03/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getConsultationRecordVersion03(@PathVariable int id) {

        HashMap<String, Object> response = new HashMap<>();
        //TODO Existing Approval number
        //TODO Does not exist in consultation
        ConsultationRecordVersion03Json consultationRecordVersion03Json = loaService.getConsultationRecordVersion03(id);
        if (consultationRecordVersion03Json != null) {
            response.put("consultationRecordVersion03Json", consultationRecordVersion03Json);
            response.put("responseCode", "200");
            response.put("responseDesc", "Consultation Record Successfully Added.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("consultationRecordVersion03Json", "");
            response.put("responseCode", "210");
            response.put("responseDesc", "Consultation Record Not Found.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


}