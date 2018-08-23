package com.basicauth.controller;

/**
 * Created by Giancarlo Angulo.
 */

import com.basicauth.data.Doctor;
import com.basicauth.data.ProcedureJson;
import com.basicauth.domain.Member;
import com.basicauth.mapper.MemMapper;
import com.basicauth.service.ClaimsService;
import com.basicauth.service.MaceService;
import com.basicauth.service.MemService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(value = "/listing/")
public class ListingController {

    private static final Logger logger = LoggerFactory.getLogger(ListingController.class);

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private MaceService maceService;

    @Autowired
    private MemService memService;

    @Autowired
    private MemMapper memMapper;




    private Log log = LogFactory.getLog(ListingController.class);

    //TODO Exclude
    @RequestMapping(value = "getHospitals/", method = RequestMethod.GET)
    public ResponseEntity<?> getHospitals() {
        log.info("getHospitals");

        List list = claimsService.getHospitals();
        HashMap<String,Object> response = new HashMap<>();
        response.put("hospitalList",list);
        log.info("getHospitals OK");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value = "getDoctors/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctors() {

        List list = claimsService.getDoctors();
        HashMap<String,Object> response = new HashMap<>();
        response.put("doctorList",list);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value = "getDiagnosisList/", method = RequestMethod.GET)
    public ResponseEntity<?> getDiagnosisList() {

        List list = claimsService.getAllDiagnosisList();
        HashMap<String,Object> response = new HashMap<>();
        response.put("diagnosisList",list);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value="/getFilteredDiagnosisList", method = RequestMethod.GET)
    public ResponseEntity<?> getFilteredDiagnosisList(){
        HashMap<String, Object> response = new HashMap<>();

        List diags = claimsService.getAllDiagnosisList();
        response.put("diagnosisList", diags);
        response.put("responseCode", 200);
        response.put("responseDesc", "Diagnosis List retrieved.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/getRoomPlans", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomPlans(){
        HashMap<String, Object> response = new HashMap<>();

        List rooms = claimsService.getRoomPlans();
        response.put("rooms", rooms);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully retrieved List.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/getOtherServices", method = RequestMethod.GET)
    private ResponseEntity<?> getOtherServices(){
        HashMap<String, Object> response = new HashMap<>();
        List services = maceService.getOtherServices();
        response.put("services", services);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully retrieved List.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value = "getDoctorsToHospital/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorsToHospital(@RequestParam("hospitalCode") String hospitalCode) {

        List list = claimsService.getDoctorsToHospital("".equals(hospitalCode)? null: hospitalCode);

        HashMap<String,Object> response = new HashMap<>();
        response.put("getDoctorsToHospital",list);



        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value = "getAllDoctorsToHospital/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDoctorsToHospital() {

        List list = claimsService.getAllDoctorsToHospital();

        HashMap<String,Object> response = new HashMap<>();
        response.put("getDoctorsToHospital",list);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value = "getAllDoctorsToHospitalByName/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDoctorsToHospitalByName(@RequestParam("partialDoctorName") String partialDoctorName,
                                                           @RequestParam("max") Integer max) {
        if(null == max){
            max  = Integer.valueOf(1000);
        }
        List list = claimsService.getAllDoctorsToHospitalByName(partialDoctorName, max);

        HashMap<String,Object> response = new HashMap<>();
        response.put("getDoctorsToHospitalCount",list.size());
        response.put("getDoctorsToHospital",list);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value="getAllTestsList", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTestsList(){
        HashMap<String, Object> response = new HashMap<>();
        response.put("testsList", claimsService.getAllTestsList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value = "getProceduresList/", method = RequestMethod.GET)
    public ResponseEntity<?> getProceduresList() {
        HashMap<String,Object> response = new HashMap<>();
        response.put("proceduresList",maceService.getProceduresList());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value="getTestsAndProceduresList", method = RequestMethod.GET)
    public ResponseEntity<?> getTestsAndProceduresList(){

        List testsAndProcedures = claimsService.getTestsAndProceduresList();

        HashMap<String, Object> response = new HashMap<>();
        response.put("testsAndProcedures", testsAndProcedures);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Method returns List of Clinic Procedure Codes List<String> ProcedureCodes
     * */
    @RequestMapping(value = "getProceduresByDiagnosisCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getProceduresByDiagnosisCode(@RequestParam("diagCode") String diagCode){
        HashMap<String, Object> response = new HashMap<>();

        List procedures = claimsService.getProceduresByDiagnosisCode(diagCode);
        if(null != procedures){
            response.put("procedures", procedures);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully Retrieved List");
        }else {
            response.put("procedures", null);
            response.put("responseCode", 210);
            response.put("responseDesc", "No Procedures Retrieved");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value="getRoomCategories", method = RequestMethod.GET)
    public ResponseEntity<?> getRoomCategories(){
        HashMap<String, Object> response = new HashMap<>();

        response.put("categories", memService.getRoomCategories());
        response.put("responseCode", 200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //TODO Exclude
    /**
     * This method returns a List<String> of ProcedureCodes of all Tests and Procedures
     * */
    @RequestMapping(value = "getTestsProceduresByDiagnosisCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getTestsProceduresByDiagnosisCode(@RequestParam("diagCode") String diagCode){
        HashMap<String, Object> response = new HashMap<>();

        List procedures = claimsService.getTestsProceduresByDiagnosisCodes(diagCode);
        if(null != procedures){
            response.put("procedures", procedures);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully Retrieved List");
        }else {
            response.put("procedures", null);
            response.put("responseCode", 210);
            response.put("responseDesc", "No Procedures Retrieved");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value = "getTestsByDiagnosisCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getTestsByDiagnosisCode(@RequestParam("diagCode") String diagCode){
        HashMap<String, Object> response = new HashMap<>();

        List<String> testCodes = claimsService.getTestsByDiagnosisCode(diagCode);
        if(null != testCodes){
            response.put("testCodes", testCodes);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully Retrieved List.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
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

        HashMap<String,Object> response = new HashMap<>();
        response.put("messages", messages);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "getInpatientHospitalExclusionList/", method = RequestMethod.GET)
    public ResponseEntity<?> getInpatientHospitalExclusionList(@RequestParam("memberCode") String memberCode) {
        log.info("getInpatientHospitalExclusionList");

        Member member = memMapper.getMemberSelect(memberCode);

        List list= memService.getInpatientHospitalExclusionList(member.getRoomRateId());
        HashMap<String,Object> response = new HashMap<>();
        response.put("exclusions",list);
        log.info("getInpatientHospitalExclusionList OK");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "getOutpatientHospitalExclusionList/", method = RequestMethod.GET)
    public ResponseEntity<?> getOutpatientHospitalExclusionList(@RequestParam("memberCode") String memberCode) {
        log.info("getOutpatientHospitalExclusionList");

        Member member = memMapper.getMemberSelect(memberCode);

        List list = memService.getOutpatientHospitalExclusionList(member.getRoomRateId());
        HashMap<String,Object> response = new HashMap<>();
        response.put("exclusions",list);
        log.info("getOutpatientHospitalExclusionList OK");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value = "getRegions/", method = RequestMethod.GET)
    public ResponseEntity<?> getRegions() {

        List regions = claimsService.getRegions();

        HashMap<String,Object> response = new HashMap<>();
        response.put("regions", regions);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "getProvinces/", method = RequestMethod.GET)
    public ResponseEntity<?> getProvinces() {

        List provinces = claimsService.getProvinces();

        HashMap<String,Object> response = new HashMap<>();
        response.put("provinces", provinces);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "getCities/", method = RequestMethod.GET)
    public ResponseEntity<?> getCities() {

        List cities = claimsService.getCities();

        HashMap<String,Object> response = new HashMap<>();
        response.put("cities", cities);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "getSpecializations/", method = RequestMethod.GET)
    public ResponseEntity<?> getSpecializations() {

        List specializations = claimsService.getSpecializations();

        HashMap<String,Object> response = new HashMap<>();
        response.put("specializations", specializations);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value = "getDoctorToHospital/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorToHospital(@RequestParam("hospitalCode") String hospitalCode, @RequestParam("doctorCode") String doctorCode) {

        List list = claimsService.getDoctorToHospital(hospitalCode,doctorCode);

        HashMap<String,Object> response = new HashMap<>();
        response.put("getDoctorsToHospital",list);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "getDoctorByCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorByCode(@RequestParam("doctorCode") String doctorCode) {

        //check doctor code vs names

        Doctor doctor = claimsService.getDoctor(doctorCode, false);
        System.out.println("doctor:"+doctor);

        HashMap<String,Object> response = new HashMap<>();

        if(null== doctor){
            response.put("doctor","");
            response.put("responseCode","210");
            response.put("responseDesc","Doctor Code not found");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }



        response.put("doctor",doctor);
        response.put("responseCode","200");
        response.put("responseDesc","Doctor Code Found");
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "getDoctorHospitalByCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorHospitalByCode(@RequestParam("doctorCode") String doctorCode) {

        //check doctor code vs names

        List doctorsToHospital = claimsService.getHospitalOfDoctors(doctorCode);
        System.out.println("doctorsToHospital:"+doctorsToHospital);

        HashMap<String,Object> response = new HashMap<>();

        if(null== doctorsToHospital){
            response.put("doctorsToHospital","");
            response.put("responseCode","210");
            response.put("responseDesc","Doctor Code not found");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }



        response.put("doctorsToHospital",doctorsToHospital);
        response.put("responseCode","200");
        response.put("responseDesc","Doctor Code Found");

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value="/getDoctorCount", method=RequestMethod.GET)
    public ResponseEntity<?> getDoctorCount(){
        HashMap<String, Object> response = new HashMap<>();

        Integer docCount = claimsService.getDoctorCount();
        response.put("docCount", docCount);
        response.put("responseCode", 200);
        response.put("responseDesc", "Doctor count retrieved.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value="/getBasicTests", method=RequestMethod.GET)
    public ResponseEntity<?> getBasicTests(){
        HashMap<String, Object> response = new HashMap<>();

        List<ProcedureJson> tests = maceService.getBasicTests();
        if(null != tests){
            response.put("basicTests", tests);
            response.put("responseCode", 200);
            response.put("responseDesc", "Basic Tests Retrieved.");
        }else{
            response.put("responseCode", 210);
            response.put("responseDesc", "Failed to retrieve list.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //TODO Exclude
    @RequestMapping(value="/getOtherTests", method=RequestMethod.GET)
    public ResponseEntity<?> getOtherTests(){
        HashMap<String, Object> response = new HashMap<>();

        List<ProcedureJson> tests = maceService.getOtherTests();
        if(null != tests){
            response.put("otherTests", tests);
            response.put("responseCode", 200);
            response.put("responseDesc", "Basic Tests Retrieved.");
        }else{
            response.put("responseCode", 210);
            response.put("responseDesc", "Failed to retrieve list.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}