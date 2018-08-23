package com.basicauth.controller;

import com.basicauth.config.JHipsterProperties;
import com.basicauth.data.Hospital;
import com.basicauth.domain.Member;
import com.basicauth.mapper.MemMapper;
import com.basicauth.mapper.mace.DocHospMapper;
import com.basicauth.service.ClaimsService;
import com.basicauth.service.MaceService;
import com.basicauth.service.MemService;
import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;
import org.hibernate.criterion.AggregateProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/syncListing")
public class ListingSyncController {

    private static final Logger logger = LoggerFactory.getLogger(ListingSyncController.class);

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private MaceService maceService;

    @Autowired
    private MemMapper memMapper;


    @Autowired
    private DocHospMapper docHospMapper;

    @Autowired
    private ListingController listingController;

    @Autowired
    private MemService memService;


    //DoctorsToHospital Paginated
    @RequestMapping(value = "/getDoctorsToHospitalPaginated/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorsToHospitalPaginated(@RequestParam("hospitalCode") String hospitalCode,
                                                           @RequestParam(value = "count", defaultValue = "30") int count,
                                                           @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                           @RequestParam(value = "searchString", defaultValue = "", required = false) String searchString,
                                                           @RequestParam(value = "includeAccredited", defaultValue = "true") boolean includeAccredited) {

        HashMap<String, Object> response = new HashMap<>(); //collecting the list of objects
        try {
            List list = claimsService.getDoctorsToHospitalPaginated(hospitalCode, count, offset, searchString, includeAccredited);
            response.put("getDoctorsToHospital", list); //putting the list.
            response.put("getDoctorsToHospital Count", list.size());
            return new ResponseEntity<>(response, HttpStatus.OK); //getting the server response

        } catch (Exception e) {
//            e.printStackTrace();
            System.err.print(e);
            logger.info("getHospitals BAD REQUEST");
            response.put("responseDesc", "Error in Getting the list");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //DentistPaginated
    @RequestMapping(value = "/getDentistListPaginated/", method = RequestMethod.GET)
    public ResponseEntity<?> getDentistListPaginated(@RequestParam(value = "count", defaultValue = "30") int count,
                                                     @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                     @RequestParam(value = "searchString", defaultValue = "", required = false) String searchString) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("dentistList", claimsService.getDentistListPaginated(count, offset, searchString));
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully retrieved list.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Diagnosis Paginated
    @RequestMapping(value = "/getDiagnosisListPaginated/", method = RequestMethod.GET)
    public ResponseEntity<?> getDiagnosisListPaginated(@RequestParam(value = "count", defaultValue = "30") int count,
                                                       @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                       @RequestParam(value = "searchString", defaultValue = "", required = false) String searchString) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("diagnosisList", claimsService.getDiagnosisListPaginated(count, offset, searchString));
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully retrieved list.");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //TestAndProcedure Paginated
    @RequestMapping(value = "/getTestProceduresListPaginated/", method = RequestMethod.GET)
    public ResponseEntity<?> getTestProceduresListPaginated(@RequestParam(value = "count", defaultValue = "30") int count,
                                                            @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                            @RequestParam(value = "searchString", defaultValue = "", required = false) String searchString,
                                                            @RequestParam(value = "serviceSubType", defaultValue = "0") int serviceSubType) {

        HashMap<String, Object> response = new HashMap<>();
        try {
            response.put("TestProceduresList", claimsService.getTestProceduresListPaginated(count, offset, searchString, serviceSubType));
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully retrieved list.");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("getTestProceduresList BAD REQUEST");
            response.put("responseDesc", "Error in Getting the list");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //HospitalsPaginated remove the hospital contains excluded
    @RequestMapping(value = "/getHospitalsPaginated/", method = RequestMethod.GET)
    public ResponseEntity<?> getHospitalsPaginated(@RequestParam(value = "count", defaultValue = "30") int count,
                                                   @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                   @RequestParam(value = "searchString", defaultValue = "", required = false) String searchString,
                                                   @RequestParam(value = "memberCode", defaultValue = "", required = false) String memberCode) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Member member = memMapper.getMemberSelect(memberCode);
            List<String> exList = new ArrayList<>();
            if (null != member) {
                exList = memService.getOutpatientHospitalExclusionList(member.getRoomRateId());
            } //if member is not null or empty get exclution list hospital.
            List list = claimsService.getHospitalsPaginated(count, offset, searchString, exList);
            response.put("hospitalList", list);
            //get the list
            response.put("hospitalCount", list.size());
            response.put("responseDesc", "Listing Success.");
            logger.info("getHospitalsPaginated OK");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("getHospitals BAD REQUEST");
            response.put("responseDesc", "Error in Getting the excluded list");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    //TODO Create services for listing of (Hospitals, Diagnosis, TestProcedures)
    //Parameters from original(Add lastUpdateDate)
    //Query mo select all where last updateDate >= #{date}

    @RequestMapping(value = "/getHospitalsListByUpdateDate/", method = RequestMethod.GET)
    public ResponseEntity<?> getHospitalsListByUpdateDate(@RequestParam("last_update_date") String lastUpdateDateString) {
        logger.info("getHospitals:" + lastUpdateDateString);
        HashMap<String, Object> response = new HashMap<>();

        try {
            SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
            Date lastUpdateDate = sdfParam.parse(lastUpdateDateString);

            List list = claimsService.getHospitalsByLastUpdateDate(lastUpdateDate);
            response.put("hospitalList", list);
            response.put("hospitalCount", list.size());
            logger.info("getHospitals OK");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ParseException e) {
            e.printStackTrace();
            logger.info("getHospitals BAD REQUEST");
            response.put("responseDesc", "Error parsing date");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value = "/getDiagnosisListByUpdateDate/", method = RequestMethod.GET)
    public ResponseEntity<?> getDiagnosisListByUpdateDate(@RequestParam("last_update_date") String lastUpdateDateString) {
        logger.info("getDiagnosisList:" + lastUpdateDateString);
        HashMap<String, Object> response = new HashMap<>();
        try {
            SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
            Date lastUpdateDate = sdfParam.parse(lastUpdateDateString);

            List list = claimsService.getAllDiagnosisListByLastUpdateDate(lastUpdateDate);
            response.put("diagnosisList", list);
            response.put("diagnosisCount", list.size());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ParseException e) {
            e.printStackTrace();
            logger.info("getDoctors BAD REQUEST");
            response.put("responseDesc", "Error parsing date");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


    //TestAndProcedureList by update date
    @RequestMapping(value = "/getAllTestAndProceduresListByUpdateDate/", method = RequestMethod.GET)
    public ResponseEntity<?> getTestAndProceduresListByUpdateDate(@RequestParam("last_update_date") String lastUpdateDateString) {
        logger.info("getAllTestProceduresListByUpdate" + lastUpdateDateString);
        HashMap<String, Object> response = new HashMap<>();
        try {
            SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
            Date lastUpdateDate = sdfParam.parse(lastUpdateDateString);
            List list = claimsService.getAllTestProceduresListByLastUpdate(lastUpdateDate);

            response.put("testsAndProcedures", list);
            response.put("testAndProcedures count", list.size());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ParseException e) {
            e.printStackTrace();
            response.put("Error", lastUpdateDateString);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


    //DocHospital by LastUpdateDate
    @RequestMapping(value = "/getDoctorHospitalListByUpdateDate/", method = RequestMethod.GET)
    public ResponseEntity<?> getDoctorsHospitalListByLastUpdateDate(@RequestParam("hospitalCode") String hospitalCode, @RequestParam("last_update_date") String lastUpdateDateString) {
        logger.info("getDoctorHospitalListByUpdateDate" + lastUpdateDateString);
        HashMap<String, Object> response = new HashMap<>();
        try {
            SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
            Date lastUpdateDate = sdfParam.parse(lastUpdateDateString);
            List list = claimsService.getDoctorsHospitalListByLastUpdateDate(hospitalCode, lastUpdateDate);
            response.put("DocHospital", list);
            response.put("DocHospital Count", list.size());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (ParseException e) {
            e.printStackTrace();
            response.put("Error", lastUpdateDateString);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
    }
}




