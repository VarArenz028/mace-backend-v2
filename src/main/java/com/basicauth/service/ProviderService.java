package com.basicauth.service;

import com.basicauth.data.*;
import com.basicauth.domain.dups.MaceConsPrescribedtest;
import com.basicauth.domain.MacePrescribedTestObject;
import com.basicauth.domain.MaceReqConsult;
import com.basicauth.domain.MaceReqOpDiag;
import com.basicauth.mapper.mace.MaceMapper;
import com.basicauth.types.LoginJson;
import net.incuventure.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jabito on 26/07/2017.
 */
@SuppressWarnings("ALL")
@Service
public class ProviderService {

    @Autowired
    private MaceService maceService;

    @Autowired
    private MaceMapper maceMapper;

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserService appUserService;

    /**
     * Login User gets the information from APPUSER table from MACE.
     */
    public ResponseEntity<?> loginProvider(LoginJson loginJson) {
        HashMap<String, Object> response = new HashMap<>();
        //TODO Device ID Not doing anything yet. Do we need to map Device ID to Doctor's Devices?

        AppUser appUser = maceService.findbyUsername(loginJson.getUsername());
        if (null != appUser) {
            if (maceService.matchUsernameAndPasswordForAppUser(loginJson.getUsername(), loginJson.getPassword())) {
                if (appUser.getStatus().equalsIgnoreCase("active")) {
                    AppVersion appVersion = maceMapper.getAppVersionByUserType("PROVIDER");
                    //Verify if App needs an update
                    try {
                        //If Required Version is greater than App Version return update required.
                        if (Double.parseDouble(appVersion.getForceVersion()) > Double.parseDouble(loginJson.getVersionNo())) {
                            response.put("responseCode", 290);
                            response.put("responseDesc", "Update required.");
                            return new ResponseEntity<>(response, HttpStatus.OK);
                            //If Stored version is greater than App Version send optional update.
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
                    response.put("user", appUser);
                    response.put("hospital", claimsService.getHospital(appUser.getHospital()));
                    response.put("docHosp", claimsService.getHospitalOfDoctors(appUser.getDoctorCode()));
                } else {//User is not "Active"
                    response.put("responseCode", 230);
                    response.put("responseDesc", "App User is INACTIVE. Please see Medicard System Administrator.");
                    response.put("hospitalDoctor", null);
                    response.put("user", null);
                }
            } else {//Invalid Password return
                response.put("responseCode", 250);
                response.put("responseDesc", "Invalid Password");
                response.put("hospitalDoctor", null);
                response.put("user", null);
            }
        } else {//AppUser is null return
            response.put("responseCode", 210);
            response.put("responseDesc", "No User Account for entered username");
            response.put("hospitalDoctor", null);
            response.put("user", null);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Forgot password. Generates a random password for the Provider
     * and sends an email to notify user of the new password.
     */
    public ResponseEntity<?> forgotPasswordProvider(String email, String doctorCode) {
        HashMap<String, Object> response = new HashMap<>();

        AppUser user = maceMapper.getAppUserByEmailAndDoctorOrDentistCode(email, doctorCode);
        if (user != null) {
            String newPassword = maceService.generateNewPassword().substring(0, 8);
            maceService.changePasswordProviderAuto(1, user.getUsername(), newPassword);
            try {//Try to send email
                emailService.sendPasswordReset(newPassword.substring(0, 8), email);
                response.put("EmailSent", true);
            } catch (Exception e) {
                response.put("EmailSent", false);
                e.printStackTrace();
            }
            response.put("responseCode", 200);
            response.put("responseDesc", "Password Change Request Successful.");
        } else {//Doctor Object is null
            response.put("responseCode", 210);
            response.put("responseDesc", "Email and Code does not match.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Change Provider Password in APPUSER. Needs old Password.
     */
    public ResponseEntity<?> changePasswordProvider(ChangePasswordJson changePasswordJson) {
        HashMap<String, Object> response = new HashMap<>();

        AppUser appUser = maceService.findbyUsername(changePasswordJson.getUsername());
        if (null != appUser) {//Appuser exists
            if (maceService.matchUsernameAndPasswordForAppUser(changePasswordJson.getUsername(), changePasswordJson.getOldPassword())) {
                maceService.changePasswordProvider(changePasswordJson.getUsername(), changePasswordJson.getNewPassword());
                response.put("responseCode", 200);
                response.put("responseDesc", "Password Successfully Changed.");
            } else {//Username and Password did not match
                response.put("responseCode", 250);
                response.put("responseDesc", "Incorrect username or password.");
            }
        } else {//Appuser does not exist
            response.put("responseCode", 210);
            response.put("responseDesc", "AppUser does not exist.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Verify provider information. Check if he is a doctor or dentist and return correspondingly.
     */
    public ResponseEntity<?> validateProvider(ValidateProviderJson providerJson) {
        HashMap<String, Object> response = new HashMap<>();
        //Check if doctor code is already in APPUSER table
        if (null != maceService.getAppUserByDoctorCode(providerJson.getDoctorCode())) {
            response.put("responseCode", 210);
            response.put("responseDesc", "Doctor Code is already registered.");
            response.put("doctor", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        //Validation of Doctor
        String actualFname;
        String actualLname;
        if (providerJson.getProviderType().equalsIgnoreCase("doctor")) {
            Doctor doctor = claimsService.getDoctor(providerJson.getDoctorCode(), false);
            if (null == doctor) {
                response.put("responseCode", 220);
                response.put("responseDesc", "Doctor Code does not exist.");
                response.put("doctor", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            actualFname = doctor.getDocFname();
            actualLname = doctor.getDocLname();
            response.put("doctor", doctor);
        } else if (providerJson.getProviderType().equalsIgnoreCase("dentist")) {
            Dentist dentist = claimsService.getDentist(providerJson.getDoctorCode());
            if (null == dentist) {
                response.put("responseCode", 220);
                response.put("responseDesc", "Dentist Code does not exist.");
                response.put("dentist", null);
            }
            actualFname = dentist.getFirstName();
            actualLname = dentist.getLastName();
            response.put("dentist", dentist);
        } else {
            response.put("responseCode", 250);
            response.put("responseDesc", "Doctor's Specialization not valid for registration.");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }

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
            response.put("responseCode", 230);
            response.put("responseDesc", "Lastname Incorrect.");
            response.put("doctor", null);
            response.put("dentist", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else if (!firstNameGood) {
            response.put("responseCode", 240);
            response.put("responseDesc", "Firstname Incorrect.");
            response.put("doctor", null);
            response.put("dentist", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully verified provider");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Register Provider service. Should be validated first through /validateProvider
     */
    public ResponseEntity<?> registerProvider(RegisterProviderJson providerJson) {
        HashMap<String, Object> response = new HashMap<>();
        if (appUserService.isUsernameUsed(providerJson.getUsername())) {
            response.put("responseCode", 210);
            response.put("responseDesc", "Account with username exists.");
            response.put("user", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        AppUser appUser = new AppUser(providerJson);
        appUser.setPassword(passwordEncoder.encode(providerJson.getPassword()));
        appUserService.saveUser(appUser);

        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully created Provider");
        response.put("provider", appUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public HashMap<String,Object> getRequestByReferenceNo(String referenceNo) {
        HashMap<String, Object> response = new HashMap<>();

        MaceReqConsult mrc = maceMapper.getMaceReqConsultByRefNo(referenceNo);
        if(null == mrc){
            response.put("responseCode", 210);
            response.put("responseDesc", "Failed to retrieve MaceReqConsult.");
        }else{
            MaceRequest mr = maceMapper.getMaceRequestByRequestID(mrc.getMaceRequestId());
            List<MaceRequestOpDiag> mrod = maceMapper.getMaceReqOpDiagByMaceReqId(mr.getRequestId());
            List<MaceConsPrescribedtest> mcpt = maceService.getMaceConsPrescribedTestsByReqId(mr.getRequestId());

            response.put("responseCode", 200);
            response.put("MaceRequest", mr);
            response.put("MaceReqConsult", mrc);
            response.put("MaceReqOpDiagList", mrod);
            response.put("MaceConsPrescribedTestList", mcpt);
            response.put("responseDesc", "Retrieved Information.");
        }

        return response;
    }
}
