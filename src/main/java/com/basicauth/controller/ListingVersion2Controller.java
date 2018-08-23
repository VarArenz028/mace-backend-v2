package com.basicauth.controller;

/**
 * Created by Giancarlo Angulo.
 */

import com.basicauth.data.*;
import com.basicauth.domain.*;
import com.basicauth.service.ClaimsService;
import com.basicauth.service.MaceService;
import com.basicauth.service.MemService;
import net.incuventure.service.EmailService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(value = "/v2/listing/")
public class ListingVersion2Controller {

    private static final Log log = LogFactory.getLog(ListingVersion2Controller.class);

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private MaceService maceService;

    @Autowired
    private MemService memService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "getAnnouncements/", method = RequestMethod.GET)
    public ResponseEntity<?> getAnnouncements() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("responseCode", 200);
        response.put("announcements", maceService.getAnnouncements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "getHospitals/", method = RequestMethod.GET)
    public ResponseEntity<?> getHospitals(@RequestParam("last_update_date") String lastUpdateDateString) {
        log.info("getHospitals:" + lastUpdateDateString);

        try {
            SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
            Date lastUpdateDate = sdfParam.parse(lastUpdateDateString);

            List list = claimsService.getHospitalsByLastUpdateDate(lastUpdateDate);
            HashMap<String, Object> response = new HashMap<>();
            response.put("hospitalList", list);
            response.put("hospitalCount", list.size());
            log.info("getHospitals OK");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
            log.info("getHospitals BAD REQUEST");
            HashMap<String, Object> response = new HashMap<>();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    //TODO Exclude
    @RequestMapping(value = "getDoctors/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctors(@RequestParam("last_update_date") String lastUpdateDateString) {
        log.info("getDoctors:" + lastUpdateDateString);
        try {
            SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
            Date lastUpdateDate = sdfParam.parse(lastUpdateDateString);

            List list = claimsService.getDoctorsByLastUpdateDate(lastUpdateDate);
            HashMap<String, Object> response = new HashMap<>();
            response.put("doctorCount", list.size());
            response.put("doctorList", list);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
            log.info("getDoctors BAD REQUEST");
            HashMap<String, Object> response = new HashMap<>();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //TODO Determine if for removal or for use with HazelCast?
    @RequestMapping(value = "getDiagnosisList/", method = RequestMethod.GET)
    public ResponseEntity<?> getDiagnosisList(@RequestParam("last_update_date") String lastUpdateDateString) {
        log.info("getDiagnosisList:" + lastUpdateDateString);
        try {
            SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
            Date lastUpdateDate = sdfParam.parse(lastUpdateDateString);

            List list = claimsService.getAllDiagnosisListByLastUpdateDate(lastUpdateDate);
            HashMap<String, Object> response = new HashMap<>();
            response.put("diagnosisList", list);
            response.put("diagnosisCount", list.size());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
            log.info("getDoctors BAD REQUEST");
            HashMap<String, Object> response = new HashMap<>();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    //TODO Exclude
    @RequestMapping(value = "/getFilteredDiagnosisList", method = RequestMethod.GET)
    public ResponseEntity<?> getFilteredDiagnosisList(@RequestParam("last_update_date") String lastUpdateDateString) {
        log.info("getDiagnosisList:" + lastUpdateDateString);
        try {
            SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
            Date lastUpdateDate = sdfParam.parse(lastUpdateDateString);

            HashMap<String, Object> response = new HashMap<>();
            List diags = claimsService.getAllDiagnosisListByLastUpdateDate(lastUpdateDate);
            response.put("diagnosisList", diags);
            response.put("diagnosisCount", diags.size());
            response.put("responseCode", 200);
            response.put("responseDesc", "Diagnosis List retrieved.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
            log.info("getDiagnosis BAD REQUEST");
            HashMap<String, Object> response = new HashMap<>();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //TODO Exclude
    //TODO Determine if for removal or for use with HazelCast?
    @RequestMapping(value = "/getRoomPlans", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomPlans() {
        HashMap<String, Object> response = new HashMap<>();

        List rooms = claimsService.getRoomPlans();
        response.put("rooms", rooms);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully retrieved List.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    //TODO Determine if for removal or for use with HazelCast?
    @RequestMapping(value = "/getOtherServices", method = RequestMethod.GET)
    private ResponseEntity<?> getOtherServices() {
        HashMap<String, Object> response = new HashMap<>();
        List services = maceService.getOtherServices();
        response.put("services", services);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully retrieved List.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getMaceFaqs", method = RequestMethod.GET)
    public ResponseEntity<?> getMaceFaqs() {
        HashMap<String, Object> response = new HashMap<>();
        response.putAll(maceService.getMaceFaqs());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Delete later or comment in if listing version 1 will be deleted
    @RequestMapping(value = "getDoctorsToHospital/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorsToHospital(@RequestParam("hospitalCode") String hospitalCode, @RequestParam("last_update_date") String lastUpdateDate) {

        List list = claimsService.getDoctorsToHospitalV2(hospitalCode, lastUpdateDate);

        HashMap<String, Object> response = new HashMap<>();
        response.put("getDoctorsToHospital", list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Delete later or comment in if listing version 1 will be deleted
    @RequestMapping(value = "getDoctorsToHospitalPaginated/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorsToHospitalPaginated(@RequestParam("hospitalCode") String hospitalCode,
                                                           @RequestParam(value = "count", defaultValue = "30") int count,
                                                           @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                           @RequestParam(value = "searchString", defaultValue = "", required = false) String searchString) {

        List list = claimsService.getDoctorsToHospitalPaginated(hospitalCode, count, offset, searchString, true); //with @Requestparameters count && offset && searchString
        HashMap<String, Object> response = new HashMap<>(); //collecting the list of objects
        response.put("getDoctorsToHospital", list); //putting the list.

        return new ResponseEntity<>(response, HttpStatus.OK); //getting the server response
    }

    @RequestMapping(value = "getDentistList", method = RequestMethod.GET)
    public ResponseEntity<?> getDentistList() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("dentistList", claimsService.getDentistList());
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully retrieved list.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getAllDoctorsToHospital/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDoctorsToHospital() {

        List list = claimsService.getAllDoctorsToHospital();

        HashMap<String, Object> response = new HashMap<>();
        response.put("getDoctorsToHospital", list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getAllDoctorsToHospitalByName/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDoctorsToHospitalByName(@RequestParam("partialDoctorName") String partialDoctorName,
                                                           @RequestParam("max") Integer max) {
        if (null == max) {
            max = Integer.valueOf(1000);
        }
        List list = claimsService.getAllDoctorsToHospitalByName(partialDoctorName, max);

        HashMap<String, Object> response = new HashMap<>();
        response.put("getDoctorsToHospitalCount", list.size());
        response.put("getDoctorsToHospital", list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getAllTestsList", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTestsList() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("testsList", claimsService.getAllTestsList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getProceduresList/", method = RequestMethod.GET)
    public ResponseEntity<?> getProceduresList() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("proceduresList", maceService.getProceduresList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getTestsOnlyList", method = RequestMethod.GET)
    public ResponseEntity<?> getTestsOnlyList() {
        List testsAndProcedures = claimsService.getTestsOnlyList();

        HashMap<String, Object> response = new HashMap<>();
        response.put("testsAndProcedures", testsAndProcedures);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getTestsAndProceduresList", method = RequestMethod.GET)
    public ResponseEntity<?> getTestsAndProceduresList() {

        List testsAndProcedures = claimsService.getTestsAndProceduresList();

        HashMap<String, Object> response = new HashMap<>();
        response.put("testsAndProcedures", testsAndProcedures);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "getTestsAndProceduresListByHosp", method = RequestMethod.GET)
    public ResponseEntity<?> getTestsAndProceduresListByHosp(@RequestParam("hospCode") String hospCode, @RequestParam("last_update_date") String lastUpdateDate) {

        List testsAndProcedures = claimsService.getTestsAndProceduresList(hospCode, lastUpdateDate);

        HashMap<String, Object> response = new HashMap<>();
        response.put("testsAndProcedures", testsAndProcedures);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getAllAccounts", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAccounts() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("accounts", memService.getAllAccounts());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Method returns List of Clinic Procedure Codes List<String> ProcedureCodes
     */
    //TODO Exclude
    @RequestMapping(value = "getProceduresByDiagnosisCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getProceduresByDiagnosisCode(@RequestParam("diagCode") String diagCode) {
        HashMap<String, Object> response = new HashMap<>();

        List procedures = claimsService.getProceduresByDiagnosisCode(diagCode);
        if (null != procedures) {
            response.put("procedures", procedures);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully Retrieved List");
        } else {
            response.put("procedures", null);
            response.put("responseCode", 210);
            response.put("responseDesc", "No Procedures Retrieved");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getRoomCategories", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomCategories() {
        HashMap<String, Object> response = new HashMap<>();

        response.put("categories", memService.getRoomCategories());
        response.put("responseCode", 200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This method returns a List<String> of ProcedureCodes of all Tests and Procedures
     */
    @RequestMapping(value = "getTestsProceduresByDiagnosisCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getTestsProceduresByDiagnosisCode(@RequestParam("diagCode") String diagCode) {
        HashMap<String, Object> response = new HashMap<>();

        List procedures = claimsService.getTestsProceduresByDiagnosisCodes(diagCode);
        if (null != procedures) {
            response.put("procedures", procedures);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully Retrieved List");
        } else {
            response.put("procedures", null);
            response.put("responseCode", 210);
            response.put("responseDesc", "No Procedures Retrieved");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "getProceduresOnlyByDiagnosisCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getProceduresOnlyByDiagnosisCode(@RequestParam("diagCode") String diagCode) {
        HashMap<String, Object> response = new HashMap<>();

        List procedures = claimsService.getProceduresOnlyByDiagnosisCode(diagCode);
        if (null != procedures) {
            response.put("procedures", procedures);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully Retrieved List");
        } else {
            response.put("procedures", null);
            response.put("responseCode", 210);
            response.put("responseDesc", "No Procedures Retrieved");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "getTestsOnlyByDiagnosisCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getTestsOnlyByDiagnosisCode(@RequestParam("diagCode") String diagCode) {
        HashMap<String, Object> response = new HashMap<>();

        List procedures = claimsService.getTestsOnlyByDiagnosisCode(diagCode);
        if (null != procedures) {
            response.put("procedures", procedures);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully Retrieved List");
        } else {
            response.put("procedures", null);
            response.put("responseCode", 210);
            response.put("responseDesc", "No Procedures Retrieved");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getTestsByDiagnosisCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getTestsByDiagnosisCode(@RequestParam("diagCode") String diagCode) {
        HashMap<String, Object> response = new HashMap<>();

        List<String> testCodes = claimsService.getTestsByDiagnosisCode(diagCode);
        if (null != testCodes) {
            response.put("testCodes", testCodes);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully Retrieved List.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("testCodes", null);
            response.put("responseCode", 404);
            response.put("responseDesc", "No Test Codes Retrieved for Diagnosis");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    //TODO Exclude
    @RequestMapping(value = "getMessages/", method = RequestMethod.GET)
    public ResponseEntity<?> getMessages() {

        List messages = maceService.getMessages();

        HashMap<String, Object> response = new HashMap<>();
        response.put("messages", messages);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @RequestMapping(value = "getInpatientHospitalExclusionList/", method = RequestMethod.GET)
//    public ResponseEntity<?> getInpatientHospitalExclusionList(@RequestParam("memberCode") String memberCode) {
//        log.info("getInpatientHospitalExclusionList");
//
//        Member member = memMapper.getMemberSelect(memberCode);
//
//        List list= memService.getInpatientHospitalExclusionList(member.getRoomRateId());
//        HashMap<String,Object> response = new HashMap<>();
//        response.put("exclusions",list);
//        log.info("getInpatientHospitalExclusionList OK");
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }

    //    @RequestMapping(value = "getOutpatientHospitalExclusionList/", method = RequestMethod.GET)
//    public ResponseEntity<?> getOutpatientHospitalExclusionList(@RequestParam("memberCode") String memberCode) {
//        log.info("getOutpatientHospitalExclusionList");
//
//
//        Member member = memMapper.getMemberSelect(memberCode);
//
//        List list = memService.getOutpatientHospitalExclusionList(member.getRoomRateId());
//        HashMap<String,Object> response = new HashMap<>();
//        response.put("exclusions",list);
//        log.info("getOutpatientHospitalExclusionList OK");
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
    //TODO Exclude
    @RequestMapping(value = "getRegions/", method = RequestMethod.GET)
    public ResponseEntity<?> getRegions() {

        List regions = claimsService.getRegions();

        HashMap<String, Object> response = new HashMap<>();
        response.put("regions", regions);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getProvinces/", method = RequestMethod.GET)
    public ResponseEntity<?> getProvinces() {

        List provinces = claimsService.getProvinces();

        HashMap<String, Object> response = new HashMap<>();
        response.put("provinces", provinces);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getCities/", method = RequestMethod.GET)
    public ResponseEntity<?> getCities() {

        List cities = claimsService.getCities();

        HashMap<String, Object> response = new HashMap<>();
        response.put("cities", cities);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getSpecializations/", method = RequestMethod.GET)
    public ResponseEntity<?> getSpecializations() {
        try {
            List specializations = claimsService.getSpecializations();

            HashMap<String, Object> response = new HashMap<>();
            response.put("specializations", specializations);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            //Send EMAIL
            emailService.emailMoAko("jdzon23@gmail.com", "jdzon23", "@jdzon23", "jdzon23@gmail.com", "Error", emailService.messageError);
            throw e;
        }
    }

    //TODO Exclude
    @RequestMapping(value = "getDoctorToHospital/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorToHospital(@RequestParam("hospitalCode") String hospitalCode, @RequestParam("doctorCode") String doctorCode) {

        List list = claimsService.getDoctorToHospital(hospitalCode, doctorCode);

        HashMap<String, Object> response = new HashMap<>();
        response.put("getDoctorsToHospital", list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getDoctorByCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorByCode(@RequestParam("doctorCode") String doctorCode) {

        //check doctor code vs names

        Doctor doctor = claimsService.getDoctor(doctorCode, false);
        System.out.println("doctor:" + doctor);

        HashMap<String, Object> response = new HashMap<>();

        if (null == doctor) {
            response.put("doctor", "");
            response.put("responseCode", "210");
            response.put("responseDesc", "Doctor Code not found");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("doctor", doctor);
        response.put("responseCode", "200");
        response.put("responseDesc", "Doctor Code Found");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "getDoctorHospitalByCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorHospitalByCode(@RequestParam("doctorCode") String doctorCode) {

        //check doctor code vs names

        List doctorsToHospital = claimsService.getHospitalOfDoctors(doctorCode);
        System.out.println("doctorsToHospital:" + doctorsToHospital);

        HashMap<String, Object> response = new HashMap<>();

        if (null == doctorsToHospital) {
            response.put("doctorsToHospital", "");
            response.put("responseCode", "210");
            response.put("responseDesc", "Doctor Code not found");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("doctorsToHospital", doctorsToHospital);
        response.put("responseCode", "200");
        response.put("responseDesc", "Doctor Code Found");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "/getDoctorCount", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorCount() {
        HashMap<String, Object> response = new HashMap<>();

        Integer docCount = claimsService.getDoctorCount();
        response.put("docCount", docCount);
        response.put("responseCode", 200);
        response.put("responseDesc", "Doctor count retrieved.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getBasicTests", method = RequestMethod.GET)
    public ResponseEntity<?> getBasicTests() {
        HashMap<String, Object> response = new HashMap<>();

        List<ProcedureJson> tests = maceService.getBasicTests();
        if (null != tests) {
            response.put("basicTests", tests);
            response.put("responseCode", 200);
            response.put("responseDesc", "Basic Tests Retrieved.");
        } else {
            response.put("responseCode", 210);
            response.put("responseDesc", "Failed to retrieve list.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO Exclude
    @RequestMapping(value = "/getOtherTests", method = RequestMethod.GET)
    public ResponseEntity<?> getOtherTests() {
        HashMap<String, Object> response = new HashMap<>();

        List<ProcedureJson> tests = maceService.getOtherTests();
        if (null != tests) {
            response.put("otherTests", tests);
            response.put("responseCode", 200);
            response.put("responseDesc", "Basic Tests Retrieved.");
        } else {
            response.put("responseCode", 210);
            response.put("responseDesc", "Failed to retrieve list.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getInpatientLogs", method = RequestMethod.GET)
    public ResponseEntity<?> getInpatientLogs(@RequestParam("memCode") String memCode) {
        HashMap<String, Object> response = new HashMap<>();
        List<MaceInpatientAudit> requests = maceService.getIPAuditLogs(memCode);
        if (null == requests) {
            response.put("responseCode", 404);
            response.put("responseDesc", "Member is not admitted to this hospital.");
            response.put("ipLogs", new ArrayList<>());
        } else {
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully retrieved data.");
            response.put("ipLogs", requests);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/pullIPRecord", method = RequestMethod.GET)
    public ResponseEntity<?> pullIPRecord(@RequestParam("maceRequestId") int maceRequestId, @RequestParam("transactionId") int transactionId) {
        HashMap<String, Object> response = new HashMap<>();
        InpatientRequestResponseJson irrj = new InpatientRequestResponseJson();
        MaceRequest mr = maceService.getMaceRequestByRequestId(maceRequestId);
        if (null == mr) {
            response.put("responseCode", 404);
            response.put("responseDesc", "Invalid maceReqId");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        MaceReqInpatient mri = maceService.getMaceReqInpatientByReqIdTransId(mr.getRequestId(), transactionId);
        if (null == mri) {
            response.put("responseCode", 404);
            response.put("responseDesc", "Request not found. Invalid transaction ID.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Hospital hosp = claimsService.getHospital(mr.getRequestFromhosp());
        if (null != hosp)
            irrj.setHospitalName(hosp.getHospitalName());
        irrj.setAdmissionType(mr.getAdmissionType());
        irrj.setDateSubmitted(mri.getRecordSubmittedOn());
        irrj.setDateAdmitted(mri.getAdmittedOn());
        irrj.setReasonForAdmission(mr.getReasonForConsult());
        irrj.setRemarks(mri.getIpReasonRemarks());
        irrj.setCoorEmail(mr.getHospEmail());
        irrj.setCoorNumber(mr.getHospContact());
        irrj.setMemberContact(mr.getMemContact());
        irrj.setRoomNo(mri.getRoomNo());

        List<MaceReqIpDoctor> ipDoctorList = maceService.getMaceReqIpDoctorsByReqIdTransId(mr.getRequestId(), mri.getTransactionId());
        if (null != ipDoctorList) {
            List<InpatientRequestResponseJson.AttendingPhysician> attendingPhysicians = new ArrayList<>();
            for (MaceReqIpDoctor maceReqIpDoctor : ipDoctorList) {
                InpatientRequestResponseJson.AttendingPhysician attendingPhysician = new InpatientRequestResponseJson.AttendingPhysician();
                attendingPhysician.setDoctorName(maceReqIpDoctor.getDoctorName());
                attendingPhysician.setDoctorCode(maceReqIpDoctor.getDoctorCode());
                Doctor doc = claimsService.getDoctor(maceReqIpDoctor.getDoctorCode(), true);
                if (null != doc)
                    attendingPhysician.setDoctorSpec(null != doc ? doc.getSpecDesc() : "");
                else
                    attendingPhysician.setDoctorName(maceReqIpDoctor.getFullName());
                attendingPhysicians.add(attendingPhysician);
            }
            irrj.setPhysicianList(attendingPhysicians);
        }
        List<MaceReqIpDiag> ipDiagList = maceService.getMaceReqIpDiagByReqIdTransId(mr.getRequestId(), mri.getTransactionId());
        if (null != ipDiagList) {
            List<InpatientRequestResponseJson.DiagnosisList> diagnoses = new ArrayList<>();
            for (MaceReqIpDiag maceReqIpDiag : ipDiagList) {
                InpatientRequestResponseJson.DiagnosisList dl = new InpatientRequestResponseJson.DiagnosisList();
                dl.setDiagCode(maceReqIpDiag.getDiagCode());
                dl.setDiagDesc(maceReqIpDiag.getDiagDesc());
                diagnoses.add(dl);
            }
            irrj.setDiagnoses(diagnoses);
        }
        List<MaceReqIpDiagproc> diagProcList = maceService.getMaceReqIpDiagProcsByReqIdTransId(mr.getRequestId(), mri.getTransactionId());
        if (null != diagProcList) {
            List<InpatientRequestResponseJson.ProcedureList> procList = new ArrayList<>();
            for (MaceReqIpDiagproc maceReqIpDiagproc : diagProcList) {
                InpatientRequestResponseJson.ProcedureList pl = new InpatientRequestResponseJson.ProcedureList();
                pl.setProcCode(maceReqIpDiagproc.getProcCode());
                pl.setProcedureName(maceReqIpDiagproc.getProcDesc());
                pl.setProcedureAmount(maceReqIpDiagproc.getActualAmount());
                procList.add(pl);
            }
            irrj.setProcedureList(procList);
        }

        MaceReqIpRoom mrir = maceService.getMaceReqIpRoom(mr.getRequestId(), mri.getTransactionId());
        if (null != mrir) {
            irrj.setRoomCategory(mrir.getRoomplan());
            irrj.setRoomPrice(mrir.getRate());
            irrj.setDateFrom(mrir.getDateFrom());
        }
        //TODO Need to add transactionId to attachments so we can filter retrieval and insertion per group.
        irrj.setAttachments(maceService.getMaceAttachmentsByReqCodeTransId(mr.getRequestCode(), mri.getTransactionId()));
        response.put("requestDetails", irrj);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successful retrieval.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/pullIPSummary", method = RequestMethod.GET)
    public ResponseEntity<?> pullIPSummary(@RequestParam("maceRequestId") int maceRequestId) {
        HashMap<String, Object> response = new HashMap<>();

        InpatientSummaryReturnJson isrj = new InpatientSummaryReturnJson();
        MaceRequest mr = maceService.getMaceRequestByRequestId(maceRequestId);
        if (null == mr) {
            response.put("responseCode", 404);
            response.put("responseDesc", "Invalid maceRequestId");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        isrj.setMaceRequest(mr);

        Member member = memService.getMemberSelect(mr.getMemCode());
        if (null == member) {
            response.put("responseCode", 404);
            response.put("responseDesc", "Member not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        isrj.setMember(member);

        Hospital hospital = claimsService.getHospital(mr.getRequestFromhosp());
        if (null == hospital) {
            response.put("responseCode", 404);
            response.put("responseDesc", "Hospital not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        isrj.setHospital(hospital);
        try {
            List<MaceReqInpatient> maceReqInpatients = maceService.getMaceReqInpatientsByReqId(mr.getRequestId());
            if (null != maceReqInpatients) {
                List<InpatientSummaryReturnJson.ContactList> contactList = new ArrayList<>();
                List<InpatientSummaryReturnJson.RemarksList> remarksList = new ArrayList<>();
                for (MaceReqInpatient maceReqInpatient : maceReqInpatients) {
                    InpatientSummaryReturnJson.ContactList cl = new InpatientSummaryReturnJson.ContactList();
                    cl.setCoorContact(maceReqInpatient.getCoorContact());
                    cl.setCoorEmail(maceReqInpatient.getCoorEmail());
                    cl.setMemberContact(maceReqInpatient.getMemContact());
                    cl.setDateTimeAdded(maceReqInpatient.getRecordSubmittedOn());
                    contactList.add(cl);

                    InpatientSummaryReturnJson.RemarksList rl = new InpatientSummaryReturnJson.RemarksList();
                    rl.setRemarks(maceReqInpatient.getIpReasonRemarks());
                    rl.setDateTimeAdded(maceReqInpatient.getRecordSubmittedOn());
                    remarksList.add(rl);
                }
                isrj.setContactList(contactList);
                isrj.setRemarksLists(remarksList);
            }
        } catch (Exception e) {
            response.put("responseDesc", "Error in MaceReqInpatient processing.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        try {
            List<MaceReqIpDoctor> docList = maceService.getMaceReqIpDoctors(mr.getRequestId());
            if (null != docList) {
                List<InpatientSummaryReturnJson.DoctorList> doctorList = new ArrayList<>();
                for (MaceReqIpDoctor mrid : docList) {
                    InpatientSummaryReturnJson.DoctorList dl = new InpatientSummaryReturnJson.DoctorList();
                    dl.setDoctorName(mrid.getDoctorName());
                    Doctor doc = claimsService.getDoctor(mrid.getDoctorCode(), true);
                    dl.setDoctorSpec(null != doc ? doc.getSpecDesc() : "");
                    dl.setDateTimeAdded(mrid.getAddedOn());
                    doctorList.add(dl);
                }
                isrj.setDocList(doctorList);
            }
        } catch (Exception e) {
            response.put("responseDesc", "Error in MaceReqIPDoctor processing.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        try {
            List<MaceReqIpDiag> diagList = maceService.getMaceReqIpDiagByReqId(mr.getRequestId());
            if (null != diagList) {
                List<InpatientSummaryReturnJson.DiagnosisList> diagnoses = new ArrayList<>();
                for (MaceReqIpDiag maceReqIpDiag : diagList) {
                    InpatientSummaryReturnJson.DiagnosisList dl = new InpatientSummaryReturnJson.DiagnosisList();
                    dl.setAdmittingDiagnosis(maceReqIpDiag.getDiagDesc());
                    dl.setDateTimeAdded(maceReqIpDiag.getDateAdded());
                    diagnoses.add(dl);
                }
                isrj.setDiagnoses(diagnoses);
            }
        } catch (Exception e) {
            response.put("responseDesc", "Error in MaceReqIPDiag processing.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        try {
            List<MaceReqIpDiagproc> diagProcList = maceService.getMaceReqIpDiagProcs(mr.getRequestId());
            if (null != diagProcList) {
                List<InpatientSummaryReturnJson.ProcList> procList = new ArrayList<>();
                for (MaceReqIpDiagproc maceReqIpDiagproc : diagProcList) {
                    InpatientSummaryReturnJson.ProcList pl = new InpatientSummaryReturnJson.ProcList();
                    pl.setProcDesc(maceReqIpDiagproc.getProcDesc());
                    pl.setProcAmount(maceReqIpDiagproc.getActualAmount());
                    pl.setDateTimeAdded(maceReqIpDiagproc.getAddedOn());
                    procList.add(pl);
                }
                isrj.setProcList(procList);
            }
        } catch (Exception e) {
            response.put("responseDesc", "Error in MaceReqIPDiagProc processing.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        try {
            List<MaceReqIpRoom> roomsList = maceService.getMaceReqIPRooms(mr.getRequestId());
            if (null != roomsList) {
                List<InpatientSummaryReturnJson.RoomList> roomList = new ArrayList<>();
                for (MaceReqIpRoom maceReqIpRoom : roomsList) {
                    InpatientSummaryReturnJson.RoomList rl = new InpatientSummaryReturnJson.RoomList();
                    rl.setDateFrom(maceReqIpRoom.getDateFrom());
                    rl.setRoomCategory(maceReqIpRoom.getRoomplan());
                    rl.setRoomRate(maceReqIpRoom.getRate());
                    rl.setRoomNo(maceReqIpRoom.getRoomtype());
                    rl.setDateTimeAdded(maceReqIpRoom.getAddedOn());
                    roomList.add(rl);
                }
                isrj.setRoomList(roomList);
            }
        } catch (Exception e) {
            response.put("responseDesc", "Error in MaceReqIPRooms processing.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        isrj.setAttachments(maceService.getMaceAttachmentsByReqCode(mr.getRequestCode()));

        response.put("summaryDetails", isrj);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successful retrieval.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}