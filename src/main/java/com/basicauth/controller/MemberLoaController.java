package com.basicauth.controller;

/**
 * Created by Giancarlo Angulo.
 */

import com.basicauth.data.*;
import com.basicauth.domain.MaceReqConsult;
import com.basicauth.service.*;
import com.basicauth.types.MemberConsultJson;
import net.incuventure.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(value = "/memberloa")
public class MemberLoaController {

    private static final Logger logger = LoggerFactory.getLogger(MemberLoaController.class);

    @Autowired
    private MaceService maceService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private MemService memService;

    @Autowired
    private LoaService loaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private Environment env;


    @RequestMapping(value = "/mossConsultTester/", method = RequestMethod.GET)
    public ResponseEntity<?> mossConsultTester(@RequestParam("memberCode") String memberCode,
                                               @RequestParam("maternityType") double maternityType,
                                               @RequestParam("hospitalCode") String hospitalCode) {
        System.out.println("mossConsultTester:" + memberCode);
        System.out.println("memberCode:" + memberCode);
        System.out.println("maternityType:" + maternityType);
        System.out.println("hospitalCode:" + hospitalCode);
        HashMap<String, Object> response = new HashMap<>();

        String msgCode = loaService.requestApprovalConsultation(memberCode, maternityType, hospitalCode);
        response.put("msgCode", msgCode);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/generateIdTester/", method = RequestMethod.GET)
    public ResponseEntity<?> generateIdTester(@RequestParam("type") String type) {
        System.out.println("generateIdTester:" + type);
        HashMap<String, Object> response = new HashMap<>();
        response.put("generatedID", maceService.generateID(type));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/getPrescribedTestForConsult/", method = RequestMethod.GET)
    public ResponseEntity<?> getPrescribedTestForConsult(@RequestParam("testCode") String testCode) {
        HashMap<String, Object> response = new HashMap<>();

        PrescribedTest test = maceService.getTestByTestCode(testCode);

        if (null == test) {
            response.put("PrescribedTest", null);
            response.put("responseCode", 210);
            response.put("responseDesc", "No Test Found");
        } else {
            response.put("PrescribedTest", test);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully Retrieved Prescribed Test");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAvailedConsultWithTests/", method = RequestMethod.GET)
    public ResponseEntity<?> getAvailedConsultWithTests(@RequestParam("memberCode") String memberCode) {
        HashMap<String, Object> response = new HashMap<>();

        List<MaceReqConsult> availedConsults = maceService.getAvailedConsultWithTests(memberCode, "AVAILED", null, null, "3,4", "0");
        if (null != availedConsults) {
            response.put("availedConsults", availedConsults);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully retrieved Availed Consultations with tests.");
        } else {
            response.put("responseCode", 210);
            response.put("responseDesc", "No List found.");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAvailedConsultWithProcedures/", method = RequestMethod.GET)
    public ResponseEntity<?> getAvailedConsultWithProcedures(@RequestParam("memberCode") String memberCode) {
        HashMap<String, Object> response = new HashMap<>();

        List<MaceReqConsult> availedConsults = maceService.getAvailedConsultWithTests(memberCode, "AVAILED", null, null, "5", "0");
        if (null != availedConsults) {
            response.put("availedConsults", availedConsults);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully retrieved Availed Consultations with tests.");
        } else {
            response.put("responseCode", 210);
            response.put("responseDesc", "No List found.");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/requestLoaForTests/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLoaForTests(@RequestParam("memberCode") String memberCode, @RequestParam("hospitalCode") String hospitalCode) {
        HashMap<String, Object> response = new HashMap<>();

        MemberDetails member = memService.getMember(memberCode);
        Hospital hospital = claimsService.getHospital(hospitalCode);

        try {
            MaceRequest mr = new MaceRequest();
            mr.setMemberDetails(member);
            mr.setServiceTypeId(2);
            mr.setServiceType("TEST");
            mr.setRequestFrommem(memberCode);
            mr.setRequestFromhosp(hospitalCode);
            mr.setRequestDatetime(new Date());
            mr.setRequestBy(memberCode);
            mr.setRequestOrigin("MEMBER");
            mr.setStatus("PENDING");
            mr.setStatusAssignee("CMG");
            maceService.saveMaceRequest(mr);

            String requestCode = maceService.getRequestCodeByRequestId(mr.getRequestId());
            response.put("requestCode", requestCode);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully Requested.");
        } catch (Exception e) {
            response.put("requestCode", "");
            response.put("responseCode", 210);
            response.put("responseDesc", "Failed to request for Test.");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/requestLOAForSelectedTests/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLOAForSelectedTests(@RequestBody MemberRequestTestsJson consultJson) {
        HashMap<String, Object> response = new HashMap<>();
        MemberDetails memberDetails = memService.getMember(consultJson.getMemberCode());

        Integer msgCode = loaService.requestApprovalProcedure(consultJson.getMemberCode(), consultJson.getHospitalCode());
        System.out.println(msgCode);

        String responseCode = "";
        String responseDesc = "";
        String remarks = "";
        String approvalNo = "";

        String proceduresList = "";
        String proceduresDescList = "";
        Double proceduresAmount = 0.0;
        if (null != consultJson.getDiagnosisProcedures()) {
            for (int x = 0; x < consultJson.getDiagnosisProcedures().length; x++) {
                proceduresList += consultJson.getDiagnosisProcedures()[x].getProcedureCode() + "|";
                proceduresAmount += consultJson.getDiagnosisProcedures()[x].getAmount();
            }
        }
        CustomerCare c = maceService.initCustomerCare(memberDetails, consultJson.getHospitalCode(), consultJson.getDoctorCode(),
                consultJson.getPrimaryDiagnosis(), proceduresList, BigDecimal.valueOf(proceduresAmount), null,
                consultJson.getUsername(), null, consultJson.getMemberCode(), consultJson.getPrimaryDiagnosisDesc(),
                null, null, null, null, null,
                consultJson.getDeviceId(),
                consultJson.getSearchType(),
                consultJson.getReasonForConsult(), null);
        c.setRequestOrigin("MEMBER");

        boolean isBlacklisted = maceService.checkifCompanyIsBlacklisted(memberDetails.getACCOUNT_CODE(), "Consultation");
        System.out.println("isBlacklisted:" + isBlacklisted);

        if (isBlacklisted) {
            return getResponseEntityForBlacklist(response, c);
        }

        if (Integer.valueOf("0").compareTo(msgCode) != 0) {

            GetResponseEntityValues getResponseEntityValues = new GetResponseEntityValues(String.valueOf(msgCode)).invoke();
            remarks = getResponseEntityValues.getRemarks();

            c.setActionTaken(1);
            c.setType(2);
            c.setRemarks(remarks);
            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setCallerId(maceService.generateID("CALLERID"));

            Integer requestHasSimilar = maceService.checkForDuplicateRequest(c);
            maceService.saveTransaction(c, memberDetails);
            customerServiceService.saveTransaction(c, memberDetails);
            loaService.saveLoa(c, consultJson.getUsername(), consultJson.getReasonForConsult());
            maceService.saveComplaint(c.getBatchCode(), consultJson.getReasonForConsult());
            responseCode = "200";
            responseDesc = "Approved";

            String withProvider = "";
            if (maceService.checkIfProviderHasAppUserAccount(c.getDoctorCode())) {
                withProvider = "withProvider";
            }
            response.put("batchCode", c.getBatchCode());
            response.put("withProvider", withProvider);
            response.put("responseCode", responseCode);
            response.put("responseDesc", responseDesc);
            response.put("remarks", c.getRemarks());

        } else {

            BigDecimal remainingLimit = new BigDecimal(claimsService.getRemainingLimit(consultJson.getMemberCode()));
            OtherLimit otherLimit = maceService.getOtherLimit();
            //BigDecimal innerLimit = maceService.getInnerLimit(otherTestJson.getProcedureCode());
            Boolean isPecEqualToDdl = memService.isPecEqualToDdl(consultJson.getMemberCode());
            //Inner Limit is retrieved here
            boolean withinLimit = loaService.validateLimit(BigDecimal.valueOf(proceduresAmount), otherLimit, remainingLimit, isPecEqualToDdl);
            if (withinLimit) {
                String callerId = maceService.generateID("CALLERID");
                String batchCode = maceService.generateID("BATCHNO");
                approvalNo = maceService.generateID("APPROVALNO");

                remarks = "PROCEDURES";

                String diagnosisCodeListConcatenate = consultJson.getPrimaryDiagnosis();
                if (null != consultJson.getOtherDiagnosisCode()) {
                    for (int x = 0; x < consultJson.getOtherDiagnosisCode().length; x++) {
                        diagnosisCodeListConcatenate += consultJson.getOtherDiagnosisCode()[x] + "|";
                    }
                }
                String procedureCodeListConcatenate = "";
                String procedureDescListContatenate = consultJson.getPrimaryDiagnosisDesc();
                if (null != consultJson.getDiagnosisProcedures()) {
                    for (int y = 0; y < consultJson.getDiagnosisProcedures().length; y++) {
                        procedureCodeListConcatenate += consultJson.getDiagnosisProcedures()[y].getProcedureCode() + "|";
                    }
                }
                if (null != consultJson.getGetOtherDiagnosisDesc()) {
                    for (int z = 0; z < consultJson.getGetOtherDiagnosisDesc().length; z++) {
                        procedureDescListContatenate += consultJson.getGetOtherDiagnosisDesc()[z] + "|";
                    }
                }

                c = maceService.initCustomerCare(memberDetails, consultJson.getHospitalCode(),
                        consultJson.getDoctorCode(), diagnosisCodeListConcatenate, procedureCodeListConcatenate,
                        BigDecimal.valueOf(proceduresAmount), null, consultJson.getUsername(), "",
                        consultJson.getMemberCode(),
                        consultJson.getPrimaryDiagnosisDesc(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        consultJson.getReasonForConsult(),
                        null);

                //Call Service to save transaction for call log
                c.setRemarks(remarks);
                c.setActionTaken(0);
                c.setType(2);
                c.setCallerId(callerId);
                c.setBatchCode(batchCode);
                c.setApprovalNo(approvalNo);
                System.out.println(c.getApprovalNo());
                Integer requestHasSimilar = maceService.checkForDuplicateRequest(c);
                switch (requestHasSimilar) {
                    case 0:
                        MaceRequest mr = new MaceRequest(c, memberDetails, 3, "MEMBER", consultJson.getUsername());
                        maceService.saveMaceRequest(mr);
                        //TODO insert in MaceRequest, MaceOpDiag, MaceOpProcedures, MaceOpTests
                        List<MaceReqOpDiagnosisProcedure> mrodpList = new ArrayList<>();
                        List<DiagnosticProceduresEntity> tests = new ArrayList<>();

                        for (int x = 0; x < consultJson.getDiagnosisProcedures().length; x++) {
                            DiagnosticProceduresEntity dpe = maceService.getDiagProcedureByProcedureCode(consultJson.getDiagnosisProcedures()[x].getProcedureCode(), consultJson.getDiagnosisProcedures()[x].getDiagnosisCode());
                            dpe.setMaceRequest(mr);
                            if (consultJson.getDiagnosisProcedures()[x].getServiceType() == 2) {
                                MaceRequestTest mrt = new MaceRequestTest();
                                mrt.setHospitalCode(consultJson.getHospitalCode());
                                mrt.setDoctorCode(consultJson.getDoctorCode());
                                mrt.setConsultReason(consultJson.getReasonForConsult());
                                mrt.setDxRemarks(consultJson.getPrimaryDiagnosisDesc());
                                mrt.setTransCode(maceService.generateID("TESTREQUEST"));

                                maceService.saveMaceReqTest(mr, mrt);
                            } else if (consultJson.getDiagnosisProcedures()[x].getServiceType() == 3) {
                                //MaceRequestOpDiag maceRequestOpDiag, MaceRequestOpProcedure maceRequestOpProcedure, MaceRequestProcedure maceRequestProcedure
                                MaceRequestOpDiag mrod = new MaceRequestOpDiag();
                                DiagnosisEntity de = maceService.getDiagnosisEntity(consultJson.getPrimaryDiagnosis());
                                mrod.setDiagnosisEntity(de);

                                MaceRequestOpProcedure mrop = new MaceRequestOpProcedure();
                                mrop.setDiagCode(consultJson.getPrimaryDiagnosis());
                                mrop.setStatus(0);
                                mrop.setMaceSubtype(0);
                                mrop.setProcCode(consultJson.getDiagnosisProcedures()[x].getProcedureCode());

                                MaceRequestProcedure mrp = new MaceRequestProcedure();
                                mrp.setMaceRequest(mr);
                                mrp.setApprovalNo(approvalNo);
                                mrp.setApprovedBy(consultJson.getUsername());
                                mrp.setApprovedOn(new Date());
                                mrp.setDoctorCode(consultJson.getDoctorCode());
                                mrp.setHospitalCode(consultJson.getHospitalCode());
                                mrodpList.add(new MaceReqOpDiagnosisProcedure(mrod, mrop, mrp));
                            }
                        }

                        maceService.saveMaceReqOpDiagnosisProcedures(mr, mrodpList);
                        maceService.saveTransaction(c, memberDetails);


                        customerServiceService.saveTransaction(c, memberDetails);
                        loaService.saveLoa(c, consultJson.getUsername(), consultJson.getReasonForConsult());
                        maceService.saveComplaint(c.getBatchCode(), consultJson.getReasonForConsult());
                        responseCode = "200";
                        responseDesc = "Approved";

                        break;
                    case 1:
                    case 2:
                        maceService.storeInTempTransaction(c);
                        responseCode = "210";
                        responseDesc = "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?";
                        response.put("batchCode", c.getBatchCode());
                        break;
//                    case 2:
//                        responseCode = "220";
//                        responseDesc = "Request Denied. There is an existing transaction with the same details.";
//                        break;
                    default:
                        break;
                }
                String withProvider = "";
                if (maceService.checkIfProviderHasAppUserAccount(c.getDoctorCode())) {
                    withProvider = "withProvider";
                }
                response.put("batchCode", c.getBatchCode());
                response.put("withProvider", withProvider);
                response.put("responseCode", responseCode);
                response.put("responseDesc", responseDesc);
                response.put("remarks", c.getRemarks());

            } else {
                remarks = "Please call 841-8080 for approval";

                c.setRemarks(remarks);
                c.setActionTaken(1);
                c.setBatchCode(maceService.generateID("BATCHNO"));
                c.setCallerId(maceService.generateID("CALLERID"));
                Integer requestHasSimilar = maceService.checkForDuplicateRequest(c);
                switch (requestHasSimilar) {
                    case 0:
                        maceService.saveTransactionForCall(c);
                        customerServiceService.saveTransactionForCall(c);
                        loaService.saveLoa(c, consultJson.getUsername(), consultJson.getReasonForConsult());
                        responseDesc = "Beyond Limit.";
                        responseCode = "204";
                        break;
                    case 1:
                    case 2:
                        maceService.storeInTempTransaction(c);
                        responseCode = "210";
                        responseDesc = "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?";
                        response.put("batchCode", c.getBatchCode());
                        break;
//                    case 2:
//                        responseCode = "220";
//                        responseDesc = "Request Denied. There is an existing transaction with the same details.";
//                        break;
                    default:
                        break;
                }
            }
        }
        response.put("responseCode", responseCode);
        response.put("responseDesc", responseDesc);
        response.put("remarks", c.getRemarks());
        response.put("approvalNo", c.getApprovalNo());
        response.put("batchCode", c.getBatchCode());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/requestLOAConsult/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLOAConsult(@RequestBody MemberConsultJson consultJson) {
        String remarks = "CONSULTATION";
        return processConsultation(consultJson, remarks, 2);
    }

    @RequestMapping(value = "/requestLOAMaternity/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLOAMaternity(@RequestBody MemberConsultJson consultJson) {
        String remarks = "MATERNITY CONSULTATION";
        return processConsultation(consultJson, remarks, 1);
    }


    @RequestMapping(value = "/requestLOABasicTest/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLOABasicTest(@RequestBody MemberConsultJson consultJson) {
        HashMap<String, Object> response = new HashMap<>();

        response.put("responseCode", "200");
        response.put("responseDesc", "Login Successful.");


        MemberDetails memberDetails = memService.getMember(consultJson.getMemberCode());
        CustomerCare c = maceService.initCustomerCare(memberDetails, consultJson.getHospitalCode(),
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
                null);
        c.setType(2);
        c.setRequestOrigin("MEMBER");
        /**
         * Call Service to request loa for consultation
         */
        Integer msgCode = loaService.requestApprovalProcedure(consultJson.getMemberCode(), consultJson.getHospitalCode());
        System.out.println(msgCode);

        String responseCode = "";
        String responseDesc = "";
        String remarks = "";
        //"0".equalsIgnoreCase(msgCode)
        if (Integer.valueOf("0").compareTo(msgCode) == 0) {

            c.setApprovalNo(maceService.generateID("APPROVALNO"));
            c.setCallerId(maceService.generateID("CALLERID"));
            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setActionTaken(0);
            c.setMemberCode(consultJson.getMemberCode());
            c.setType(2);
            remarks = "BASIC TEST";

            c.setRemarks(remarks);
            Integer requestHasSimilar = maceService.checkForDuplicateRequest(c);
            switch (requestHasSimilar) {
                case 0:
                    maceService.saveTransaction(c, memberDetails);
                    customerServiceService.saveTransaction(c, memberDetails);
                    loaService.saveLoa(c, consultJson.getUsername(), consultJson.getPrimaryComplaint());
                    maceService.saveComplaint(c.getBatchCode(), c.getPrimaryComplaint());
                    responseCode = "200";
                    responseDesc = "Approved";
                    response.put("approvalNo", c.getApprovalNo());
                    break;
                case 1:
                    maceService.storeInTempTransaction(c);
                    responseCode = "210";
                    responseDesc = "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?";
                    response.put("batchCode", c.getBatchCode());
                    break;
                case 2:
                    responseCode = "220";
                    responseDesc = "Request Denied. There is an existing transaction with the same details.";
                    break;
                default:
                    break;
            }
            String withProvider = "";
            if (maceService.checkIfProviderHasAppUserAccount(c.getDoctorCode())) {
                withProvider = "withProvider";
            }
            response.put("withProvider", withProvider);
            response.put("responseCode", responseCode);
            response.put("responseDesc", responseDesc);
            response.put("remarks", remarks);

        } else {

            MemberLoaController.GetResponseEntityValuesInteger getResponseEntityValuesInteger = new MemberLoaController.GetResponseEntityValuesInteger(msgCode).invoke();
            remarks = getResponseEntityValuesInteger.getRemarks();


            //Call Service to save transaction for call log
            c.setRemarks(remarks);
            c.setActionTaken(1);
            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setCallerId(maceService.generateID("CALLERID"));
            c.setType(2);
            Integer requestHasSimilar = maceService.checkForDuplicateRequest(c);
            switch (requestHasSimilar) {
                case 0:
                    maceService.saveTransactionForCall(c);
                    customerServiceService.saveTransactionForCall(c);
                    maceService.saveComplaint(c.getBatchCode(), consultJson.getPrimaryComplaint());
                    loaService.saveLoa(c, consultJson.getUsername(), consultJson.getPrimaryComplaint());
                    responseCode = getResponseEntityValuesInteger.getResponseCode();
                    responseDesc = getResponseEntityValuesInteger.getResponseDesc();
                    break;
                case 1:
                    maceService.storeInTempTransaction(c);
                    responseCode = "210";
                    responseDesc = "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?";
                    response.put("batchCode", c.getBatchCode());
                    break;
                case 2:
                    responseCode = "220";
                    responseDesc = "Request Denied. There is an existing transaction with the same details.";
                    break;
                default:
                    break;
            }
            String withProvider = "";
            if (maceService.checkIfProviderHasAppUserAccount(c.getDoctorCode())) {
                withProvider = "withProvider";
            }
            response.put("batchCode", c.getBatchCode());
            response.put("withProvider", withProvider);
            response.put("responseCode", responseCode);
            response.put("responseDesc", responseDesc);
            response.put("remarks", remarks);
        }


        //Check if company is blacklisted, call service to check blacklisted company
        boolean isBlacklisted = maceService.checkifCompanyIsBlacklisted(memberDetails.getACCOUNT_CODE(), "Consultation");
        System.out.println("isBlacklisted:" + isBlacklisted);

        if (isBlacklisted) {
            return getResponseEntityForBlacklist(response, c);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/inquireInPatient/", method = RequestMethod.POST)
    public ResponseEntity<?> inquireInPatient(@RequestBody InpatientJson inpatientJson) {
        System.out.println(inpatientJson);
        System.out.println(inpatientJson.getUsername());
        System.out.println(inpatientJson.getMemberCode());
        System.out.println(inpatientJson.getDeviceId());
        System.out.println(inpatientJson.getDiagnosisCode());
        System.out.println(inpatientJson.getDoctorCode());
        System.out.println(inpatientJson.getHospitalCode());
        System.out.println(inpatientJson.getRoomType());
        System.out.println(inpatientJson.getProcedureCode());

        HashMap<String, Object> response = new HashMap<>();
        try {

            MemberDetails memberDetails = memService.getMember(inpatientJson.getMemberCode());
            System.out.println("Inpatient: " + memberDetails);
            CustomerCare c = maceService.processInpatient(
                    memberDetails,
                    inpatientJson.getHospitalCode(),
                    inpatientJson.getDoctorCode(),
                    inpatientJson.getDiagnosisCode(),
                    inpatientJson.getRoomType(),
                    inpatientJson.getRoomNumber(),
                    inpatientJson.getRoomPrice(),
                    inpatientJson.getUsername(),
                    inpatientJson.getProcedureCode(),
                    inpatientJson.getDateAdmitted(),
                    inpatientJson.getCategory()
            );
            c.setType(1);
            c.setActionTaken(4);
            Integer retCode = memService.validateInpatient(inpatientJson.getMemberCode(), inpatientJson.getHospitalCode());
            String remarks = "";
            String responseCode = "";
            String responseDesc = "";

            if (retCode.compareTo(Integer.valueOf("0")) == 0) {
                remarks = "Inpatient";

                c.setRemarks(remarks);
                c.setBatchCode(maceService.generateID("BATCHNO"));
                c.setCallerId(maceService.generateID("CALLERID"));
                c.setType(1);
                maceService.saveTransactionForCall(c);
                customerServiceService.saveTransactionForCall(c);

                response.put("responseCode", "200");
                response.put("responseDesc", remarks);
                response.put("remarks", remarks);

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {

                if (Integer.valueOf("37").compareTo(retCode) == 0) {
                    remarks = "Member is Resigned";
                    responseCode = "201";
                    responseDesc = "Disapproved. Member no longer a MEDICard member";
                } else if (Integer.valueOf("58").compareTo(retCode) == 0) {
                    remarks = "Disapproved. Member has no inpatient access.";
                    responseCode = "202";
                    responseDesc = "Member has no outpatient access";
                } else if (Integer.valueOf("39").compareTo(retCode) == 0) {
                    remarks = "Disapproved. Member has no access to inpatient benefit.";
                    responseCode = "203";
                    responseDesc = "Member has no access to consultation";
                } else if (Integer.valueOf("42").compareTo(retCode) == 0) {
                    remarks = "Membership is on hold. Please call 841-8080 for approval";
                    responseCode = "204";
                    responseDesc = "Member is on hold";
                } else if (Integer.valueOf("38").compareTo(retCode) == 0) {
                    remarks = "No access to hospital. Please call 841-8080 for approval";
                    responseCode = "206";
                    responseDesc = "No access to hospital";
                } else if (Integer.valueOf("41").compareTo(retCode) == 0) {
                    remarks = "No Maternity Benefits. Please call 841-8080 for approval";
                    responseCode = "203";
                    responseDesc = "Member has no access to maternity consultation";
                } else {
                    remarks = "Unhandled Message. Please call 841-8080 for approval";
                    responseCode = "250";
                    responseDesc = "Unhandled Message Code";
                }

                //Call Service to save transaction for call log
                c.setRemarks(remarks);
                c.setBatchCode(maceService.generateID("BATCHNO"));
                c.setCallerId(maceService.generateID("CALLERID"));
                c.setType(1);
                maceService.saveTransactionForCall(c);
                customerServiceService.saveTransactionForCall(c);
            }
            response.put("responseCode", responseCode);
            response.put("responseDesc", responseDesc);
            response.put("remarks", remarks);


            //Check if company is blacklisted, call service to check blacklisted company
            boolean isBlacklisted = maceService.checkifCompanyIsBlacklisted(memberDetails.getACCOUNT_CODE(), "Inpatient");
            System.out.println("isBlacklisted:" + isBlacklisted);

            if (isBlacklisted) {
                return getResponseEntityForBlacklist(response, c);
            }


            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            e.printStackTrace();

            response.put("responseCode", "230");
            response.put("responseDesc", "Incorrect username or password.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }


    }

    @RequestMapping(value = "/cancelLOA/", method = RequestMethod.POST)
    public ResponseEntity<?> cancelLOA(@RequestParam("requestCode") String requestCode) {
        HashMap<String, Object> response = new HashMap<>();

        maceService.cancelLOAByRequestCode(requestCode, "Member", "MEMBER");
        response.put("LOA Batch Code", requestCode);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully Cancelled LOA");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/requestLOAOtherTest/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLOAOtherTest(@RequestBody OtherTestJson otherTestJson) {
        System.out.println(otherTestJson);
        System.out.println("getHospitalCode" + otherTestJson.getHospitalCode());
        System.out.println("getTotalProcAmount" + otherTestJson.getTotalProcAmount());
        System.out.println("getDiagnosisCode" + otherTestJson.getDiagnosisCode());
        System.out.println("getDoctorCode" + otherTestJson.getDoctorCode());
        System.out.println("getLocationCode" + otherTestJson.getLocationCode());
        System.out.println("getMemberCode" + otherTestJson.getMemberCode());
        System.out.println("getProcedureCode" + otherTestJson.getProcedureCode());
        System.out.println("getProcedureDesc" + otherTestJson.getProcedureDesc());
        System.out.println("getUsername" + otherTestJson.getUsername());
        System.out.println("getDiagnosisList" + otherTestJson.getDiagnosisList());
        System.out.println("getProcedureList" + otherTestJson.getProcedureList());

        HashMap<String, Object> response = new HashMap<>();

        MemberDetails memberDetails = memService.getMember(otherTestJson.getMemberCode());

        /**
         * Call Service to request loa for consultation
         */
        //Call Service to request loa for procedure
        Integer msgCode = loaService.requestApprovalProcedure(otherTestJson.getMemberCode(), otherTestJson.getHospitalCode());
        System.out.println(msgCode);

        String responseCode = "";
        String responseDesc = "";
        String remarks = "";
        String approvalNo = "";

        CustomerCare c = maceService.initCustomerCare(memberDetails, otherTestJson.getHospitalCode(),
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
        c.setType(2);
        c.setRequestOrigin("MEMBER");

        if (Integer.valueOf("0").compareTo(msgCode) != 0) {
            MemberLoaController.GetResponseEntityValuesInteger getResponseEntityValuesInteger = new MemberLoaController.GetResponseEntityValuesInteger(msgCode).invoke();
            remarks = getResponseEntityValuesInteger.getRemarks();


            c.setActionTaken(1);
            c.setRemarks(remarks);
            c.setType(2);
            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setCallerId(maceService.generateID("CALLERID"));
            Integer requestHasSimilar = maceService.checkForDuplicateRequest(c);
            switch (requestHasSimilar) {
                case 0:
                    maceService.saveTransactionForCall(c);
                    customerServiceService.saveTransactionForCall(c);
                    loaService.saveLoa(c, otherTestJson.getUsername(), otherTestJson.getPrimaryComplaint());
                    responseCode = getResponseEntityValuesInteger.getResponseCode();
                    responseDesc = getResponseEntityValuesInteger.getResponseDesc();
                    break;
                case 1:
                    maceService.storeInTempTransaction(c);
                    responseCode = "210";
                    responseDesc = "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?";
                    response.put("batchCode", c.getBatchCode());
                    break;
                case 2:
                    responseCode = "220";
                    responseDesc = "Request Denied. There is an existing transaction with the same details.";
                    break;
                default:
                    break;
            }

            response.put("responseCode", responseCode);
            response.put("responseDesc", responseDesc);
            response.put("remarks", remarks);
        } else {

            //Check if company is blacklisted, call service to check blacklisted company
            boolean isBlacklisted = maceService.checkifCompanyIsBlacklisted(memberDetails.getACCOUNT_CODE(), "Consultation");
            System.out.println("isBlacklisted:" + isBlacklisted);


            if (isBlacklisted) {
                return getResponseEntityForBlacklist(response, c);
            }

            System.out.println(otherTestJson.getProcedureList() != null);
            System.out.println(otherTestJson.getProcedureList().size());

            BigDecimal remainingLimit = new BigDecimal(claimsService.getRemainingLimit(otherTestJson.getMemberCode()));
            OtherLimit otherLimit = maceService.getOtherLimit();
            //BigDecimal innerLimit = maceService.getInnerLimit(otherTestJson.getProcedureCode());
            Boolean isPecEqualToDdl = memService.isPecEqualToDdl(otherTestJson.getMemberCode());
            //Inner Limit is retrieved here
            boolean withinLimit = loaService.validateLimit(otherTestJson.getTotalProcAmount(), otherLimit, remainingLimit, isPecEqualToDdl);
            if (withinLimit) {
                String callerId = maceService.generateID("CALLERID");
                String batchCode = maceService.generateID("BATCHNO");
                approvalNo = maceService.generateID("APPROVALNO");

                remarks = "OTHER TESTS";

                for (String diagnosisCode : otherTestJson.getDiagnosisList()) {
                    for (ProcedureJson procedureJson : otherTestJson.getProcedureList()) {
                        c = maceService.initCustomerCare(memberDetails, otherTestJson.getHospitalCode(),
                                otherTestJson.getDoctorCode(), diagnosisCode, procedureJson.getProcedureCode(),
                                procedureJson.getProcedureAmount(), otherTestJson.getLocationCode(), otherTestJson.getUsername(),
                                procedureJson.getProcedureDesc(),
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
                        c.setType(2);
                        //Call Service to save transaction for call log
                        c.setRemarks(remarks);
                        c.setActionTaken(0);

                        c.setCallerId(callerId);
                        c.setBatchCode(batchCode);
                        c.setApprovalNo(approvalNo);
                        System.out.println(c.getApprovalNo());
                        Integer requestHasSimilar = maceService.checkForDuplicateRequest(c);
                        switch (requestHasSimilar) {
                            case 0:
                                c = maceService.saveTransaction(c, memberDetails);
                                c = customerServiceService.saveTransaction(c, memberDetails);
                                loaService.saveLoa(c, otherTestJson.getUsername(), otherTestJson.getPrimaryComplaint());
                                maceService.saveComplaint(c.getBatchCode(), c.getPrimaryComplaint());
                                responseCode = "200";
                                responseDesc = "Approved";
                                response.put("approvalNo", approvalNo);
                                break;
                            case 1:
                                maceService.storeInTempTransaction(c);
                                responseCode = "210";
                                responseDesc = "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?";
                                response.put("batchCode", c.getBatchCode());
                                break;
                            case 2:
                                responseCode = "220";
                                responseDesc = "Request Denied. There is an existing transaction with the same details.";
                                break;
                            default:
                                break;
                        }
                        String withProvider = "";
                        if (maceService.checkIfProviderHasAppUserAccount(c.getDoctorCode())) {
                            withProvider = "withProvider";
                        }
                        response.put("withProvider", withProvider);
                        response.put("batchCode", c.getBatchCode());
                        response.put("responseCode", responseCode);
                        response.put("responseDesc", responseDesc);
                        response.put("remarks", remarks);
                    }
                }

            } else {

                remarks = "Please call 841-8080 for approval";
                c.setRemarks(remarks);
                c.setType(2);
                c.setActionTaken(1);
                c.setBatchCode(maceService.generateID("BATCHNO"));
                c.setCallerId(maceService.generateID("CALLERID"));
                Integer requestHasSimilar = maceService.checkForDuplicateRequest(c);
                switch (requestHasSimilar) {
                    case 0:
                        maceService.saveTransactionForCall(c);
                        customerServiceService.saveTransactionForCall(c);
                        maceService.saveTransaction(c, memberDetails);
                        loaService.saveLoa(c, otherTestJson.getUsername(), otherTestJson.getPrimaryComplaint());
                        responseDesc = "Request Processed.";
                        responseCode = "200";
                        break;
                    case 1:
                        maceService.storeInTempTransaction(c);
                        responseCode = "210";
                        responseDesc = "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?";
                        response.put("batchCode", c.getBatchCode());
                        break;
                    case 2:
                        responseCode = "220";
                        responseDesc = "Request Denied. There is an existing transaction with the same details.";
                        break;
                    default:
                        break;
                }
                String withProvider = "";
                if (maceService.checkIfProviderHasAppUserAccount(c.getDoctorCode())) {
                    withProvider = "withProvider";
                }
                response.put("withProvider", withProvider);
                response.put("batchCode", c.getBatchCode());
                response.put("responseCode", responseCode);
                response.put("responseDesc", responseDesc);
                response.put("remarks", remarks);
            }

        }


        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private ResponseEntity<?> getResponseEntityForBlacklist(HashMap<String, Object> response, CustomerCare c) {
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
        c.setBatchCode(maceService.generateID("BATCHNO"));
        c.setCallerId(maceService.generateID("CALLERID"));
        maceService.saveTransactionForCall(c);
        customerServiceService.saveTransactionForCall(c);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/inquireInPatientNoValidation/", method = RequestMethod.POST)
    public ResponseEntity<?> inquireInPatientNoValidation(@RequestBody InpatientJson inpatientJson) {
        System.out.println(inpatientJson);
        System.out.println(inpatientJson.getUsername());
        System.out.println(inpatientJson.getMemberCode());
        System.out.println(inpatientJson.getDeviceId());
        System.out.println(inpatientJson.getDiagnosisCode());
        System.out.println(inpatientJson.getDoctorCode());
        System.out.println(inpatientJson.getHospitalCode());
        System.out.println(inpatientJson.getRoomType());
        System.out.println(inpatientJson.getProcedureCode());

        HashMap<String, Object> response = new HashMap<>();
        try {

            MemberDetails memberDetails = memService.getMember(inpatientJson.getMemberCode());

            CustomerCare c = maceService.processInpatient(
                    memberDetails,
                    inpatientJson.getHospitalCode(),
                    inpatientJson.getDoctorCode(),
                    inpatientJson.getDiagnosisCode(),
                    inpatientJson.getRoomType(),
                    inpatientJson.getRoomNumber(),
                    inpatientJson.getRoomPrice(),
                    inpatientJson.getUsername(),
                    inpatientJson.getProcedureCode(),
                    inpatientJson.getDateAdmitted(),
                    inpatientJson.getCategory()
            );
            c.setType(1);
            c.setActionTaken(4);

            Integer retCode = memService.validateInpatient(inpatientJson.getMemberCode(), inpatientJson.getHospitalCode());
            String remarks = "InpatientNoValidation";
            String responseCode = "200";
            String responseDesc = remarks;

            c.setRemarks(remarks);
            c.setActionTaken(4);
            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setCallerId(maceService.generateID("CALLERID"));
            maceService.saveTransactionForCall(c);
            customerServiceService.saveTransactionForCall(c);


            response.put("responseCode", responseCode);
            response.put("responseDesc", responseDesc);
            response.put("remarks", remarks);
            response.put("retCode", retCode);

            String email = env.getProperty("inpatient.email");
            String userEmail = maceService.getAppUserEmailByUsername(inpatientJson.getUsername());
            System.out.println("email:" + email);
            emailService.sendInPatientNotification(
                    email,
                    userEmail,
                    inpatientJson.getMemberCode(),
                    inpatientJson.getMemberName(),
                    inpatientJson.getHospitalName(),
                    inpatientJson.getDoctorCode(), //free text
                    inpatientJson.getDiagnosisCode(),   //free text
                    inpatientJson.getRoomNumber(),        //free text
                    inpatientJson.getRoomPrice(),       //free text
                    inpatientJson.getCategory(),       //free text
                    inpatientJson.getProcedureCode(),
                    inpatientJson.getDateAdmitted()
            );
            //Duplicate Email for testing.
            String email2 = env.getProperty("inpatient.email2");
            emailService.sendInPatientNotification(
                    email2,
                    userEmail,
                    inpatientJson.getMemberCode(),
                    inpatientJson.getMemberName(),
                    inpatientJson.getHospitalName(),
                    inpatientJson.getDoctorCode(), //free text
                    inpatientJson.getDiagnosisCode(),   //free text
                    inpatientJson.getRoomNumber(),        //free text
                    inpatientJson.getRoomPrice(),       //free text
                    inpatientJson.getCategory(),       //free text
                    inpatientJson.getProcedureCode(),
                    inpatientJson.getDateAdmitted()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            response.put("responseCode", "230");
            response.put("responseDesc", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }


    }

//    @RequestMapping(value = "/lockMember/", method = RequestMethod.POST)
//    public ResponseEntity<?> lockMember(@RequestParam("memberCode") String memberCode) {
//        System.out.println(memberCode);
//
//        HashMap<String, Object> response = new HashMap<>();
//        try {
//
//            maceService.lockMember(memberCode);
//            response.put("responseCode", "200");
//            response.put("responseDesc", "Member is locked.");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            response.put("responseCode", "230");
//            response.put("responseDesc", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//    }

    @RequestMapping(value = "/inquireEmergencyRoomNoValidation/", method = RequestMethod.POST)
    public ResponseEntity<?> inquireEmergencyRoomNoValidation(@RequestBody EmergencyRoomInquiryJson erInquiryJson) {
        System.out.println(erInquiryJson);
        System.out.println(erInquiryJson.getUsername());
        System.out.println(erInquiryJson.getMemberCode());
//        System.out.println(erInquiryJson.getDeviceId());
//        System.out.println(erInquiryJson.getDiagnosisCode());
//        System.out.println(erInquiryJson.getDoctorCode());
        System.out.println(erInquiryJson.getHospitalCode());
//        System.out.println(erInquiryJson.getProcedureCode());

        HashMap<String, Object> response = new HashMap<>();
        try {

            MemberDetails memberDetails = memService.getMember(erInquiryJson.getMemberCode());

            CustomerCare c = maceService.processEmergencyRoomInquiry(
                    memberDetails,
                    erInquiryJson.getHospitalCode(),
                    erInquiryJson.getUsername(),
                    erInquiryJson.getErReason(),
                    erInquiryJson.getDateAdmitted()
            );
            c.setType(-1);
            String remarks = "INQUIRY FOR ER";
            String responseCode = "200";

            c.setRemarks(remarks);
            c.setActionTaken(4);
            c.setMemberCode(erInquiryJson.getMemberCode());

            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setCallerId(maceService.generateID("CALLERID"));
            maceService.saveTransactionForCall(c);
            customerServiceService.saveTransactionForCall(c);


            response.put("memberDetails", memberDetails);
            response.put("responseCode", responseCode);
            response.put("responseDesc", remarks);
            response.put("remarks", remarks);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            response.put("memberDetails", "");
            response.put("responseCode", "230");
            response.put("responseDesc", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/addAttachmentByRequestCode/", method = RequestMethod.POST)
    public ResponseEntity<?> addAttachmentByRequestCode(@RequestBody MultipartFile file,
                                                        @RequestParam("requestCode") String requestCode,
                                                        @RequestParam(value = "transactionId", required =  false) String transactionId) {

        Integer reqId = maceService.getreqIdByRequestCode(requestCode);
        HashMap<String, Object> responseMap = new HashMap<>();

        if (reqId == null) {
            responseMap.put("responseCode", 404);
            responseMap.put("responseDesc",
                    String.format("The MACE REQUEST  with an approval number of %s was not found.", requestCode));
            return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileId = simpleDateFormat.format(new Date());

        MaceRequestAttachment attachment = new MaceRequestAttachment();
        attachment.setRequestId(reqId);
        attachment.setRequestCode(requestCode);
        attachment.setTransactionId(transactionId);
        attachment.setFileId(fileId);
        attachment.setContentType(file.getContentType());
        attachment.setOriginalFileName(file.getOriginalFilename());
        attachment.setFileNameNoSuffix(file.getOriginalFilename());
        attachment.setFileSuffix(file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")));

        if (!file.isEmpty()) {
            try {
                attachment.setContent(file.getBytes());
                maceService.saveMaceRequestAttachment(attachment);

                responseMap.put("responseCode", 200);
                responseMap.put("responseDesc", "Success.");
                responseMap.put("attachmentID", attachment.getId());

                return new ResponseEntity<>(responseMap, HttpStatus.OK);

            } catch (IOException e) {
                responseMap.put("responseCode", 500);
                responseMap.put("responseDesc", "Internal Server Error");
                responseMap.put("error", e.getMessage());

                return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            responseMap.put("responseCode", 204);
            responseMap.put("responseDesc", "File is empty");

            return new ResponseEntity<>(responseMap, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/getAttachmentById/", method = RequestMethod.GET)
    public ResponseEntity<?> getAttachmentById(@RequestParam("id") int id) {
        MaceRequestAttachment attachment = maceService.retrieveMaceAttachment(id);

        if (attachment == null) {
            HashMap<String, Object> responseMap = new HashMap<>();

            responseMap.put("responseCode", 404);
            responseMap.put("responseDesc", String.format("Cannot find the attachment with id=%d.", id));

            return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
        }

        HttpHeaders header = new HttpHeaders();
        header.setContentLength(attachment.getContent().length);
        header.setContentType(MediaType.parseMediaType(attachment.getContentType()));
        header.set("Content-Disposition", "attachment; filename=" + attachment.getOriginalFileName());

        return new ResponseEntity<>(attachment.getContent(), header, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAttachmentsByRequestCode/", method = RequestMethod.GET)
    public ResponseEntity<?> getAttachmentsByRequestCode(@RequestParam("requestCode") String requestCode) {

        HashMap<String, Object> responseMap = new HashMap<>();

        List<MaceRequestAttachment> attachments = maceService.getMaceAttachmentsByReqCode(requestCode);
        if (attachments == null || attachments.isEmpty()) {
            responseMap.put("responseCode", 404);
            responseMap.put("responseDesc",
                    String.format("The LOA with an approval number of %s was not found.", requestCode));
            return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
        }

        List<Integer> attachmentIdList = new ArrayList<>();
        for (MaceRequestAttachment attachment : attachments) {
            attachmentIdList.add(attachment.getId());
        }

        responseMap.put("attachments", attachmentIdList);

        responseMap.put("responseCode", 200);
        responseMap.put("responseDesc", "Success.");

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteAttachmentById/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAttachmentById(@RequestParam("id") Integer id) {

        maceService.deleteMaceAttachment(id);

        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("responseCode", 200);
        responseMap.put("responseDesc", "Success.");

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteAttachmentsByRequestCode/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAttachmentsByRequestCode(@RequestParam("requestCode") String requestCode) {

        maceService.deleteMaceAttachmentByRequestCode(requestCode);

        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("responseCode", 200);
        responseMap.put("responseDesc", "Success.");

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    private class GetResponseEntityValues {
        private String msgCode;
        private String responseCode;
        private String responseDesc;
        private String remarks;

        public GetResponseEntityValues(String msgCode) {
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

        public MemberLoaController.GetResponseEntityValues invoke() {
            try {
                BlockingMessages blocker = maceService.getBlockingMessageByCode(msgCode);
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

        public MemberLoaController.GetResponseEntityValuesInteger invoke() {
            try {
                BlockingMessages blocker = maceService.getBlockingMessageByCode(String.valueOf(msgCode));
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

    private ResponseEntity<?> processConsultation(MemberConsultJson consultJson, String remarks, Integer requestApprovalConsultCode) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("responseCode", "200");
        response.put("responseDesc", "Login Successful.");

        String withProvider = "";

        MemberDetails memberDetails = memService.getMember(consultJson.getMemberCode());
        CustomerCare c = maceService.initCustomerCare(memberDetails, consultJson.getHospitalCode(),
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
                null);
        c.setRequestOrigin("MEMBER");
        //Check if company is blacklisted, call service to check blacklisted company
        boolean isBlacklisted = maceService.checkifCompanyIsBlacklisted(memberDetails.getACCOUNT_CODE(), "Consultation");
        System.out.println("isBlacklisted:" + isBlacklisted);

        if (isBlacklisted) {
            return getResponseEntityForBlacklist(response, c);
        }

        /**
         * Call Service to request loa for consultation
         */
        String msgCode = loaService.requestApprovalConsultation(consultJson.getMemberCode(), requestApprovalConsultCode, consultJson.getHospitalCode());
        System.out.println(msgCode);

        String responseCode = "";
        String responseDesc = "";
        String requestCode = "";
        String status = "";

        if ("0".equalsIgnoreCase(msgCode) && memberDetails.getMem_OStat_Code().equalsIgnoreCase("active")) {

            c.setApprovalNo(maceService.generateID("APPROVALNO"));
            c.setCallerId(maceService.generateID("CALLERID"));
            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setActionTaken(0);
            try {
                //String outpatientAmount = env.getProperty("consultation.amount");
                BigDecimal procAmount = BigDecimal.valueOf(maceService.getCostByTransactionType("CONSULTATION"));
                c.setProcedureAmount(procAmount);
                c.setProcedureAmt(procAmount);
            } catch (Exception e) {
            }
            c.setRemarks(remarks);
            Integer requestHasSimilar = maceService.checkForDuplicateRequest(c);
            switch (requestHasSimilar) {
                case 0:
                    maceService.saveTransaction(c, memberDetails);
                    customerServiceService.saveTransaction(c, memberDetails);
                    loaService.saveLoa(c, consultJson.getUsername(), consultJson.getPrimaryComplaint());
                    maceService.saveComplaint(c.getBatchCode(), consultJson.getPrimaryComplaint());
                    responseCode = "200";
                    responseDesc = "Approved";
                    //START INSERT MACE_REQ TABLES
                    requestCode = maceService.saveMaceRequestForConsult(memberDetails, c);
                    status = "APPROVED";
                    response.put("requestCode", requestCode);
                    //END INSERT MACE_REQ TABLES
                    break;
                case 1:
                    maceService.storeInTempTransaction(c);
                    responseCode = "210";
                    responseDesc = "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?";
                    response.put("batchCode", c.getBatchCode());
                    break;
                case 2:
                    responseCode = "220";
                    responseDesc = "Request Denied. There is an existing transaction with the same details.";
                    break;
                default:
                    break;
            }
            if (maceService.checkIfProviderHasAppUserAccount(c.getDoctorCode())) {
                withProvider = "withProvider";
            }
            response.put("approvalNo", c.getApprovalNo());
        } else {

            MemberLoaController.GetResponseEntityValues getResponseEntityValues = new MemberLoaController.GetResponseEntityValues(msgCode).invoke();
            if (requestApprovalConsultCode == 2) {
                remarks = msgCode.equalsIgnoreCase("0") ||
                        msgCode.equalsIgnoreCase("42") ? "CONSULTATION" : getResponseEntityValues.getRemarks();
            } else {
                remarks = msgCode.equalsIgnoreCase("0") ||
                        msgCode.equalsIgnoreCase("42") ? "MATERNITY" : getResponseEntityValues.getRemarks();
            }
            //Call Service to save transaction for call log
            c.setRemarks(remarks);
            c.setActionTaken(1);
            c.setCallerId(maceService.generateID("CALLERID"));
            c.setBatchCode(maceService.generateID("BATCHNO"));
            Integer requestHasSimilar = maceService.checkForDuplicateRequest(c);
            switch (requestHasSimilar) {
                case 0:
                    maceService.saveTransactionForCall(c);
                    customerServiceService.saveTransactionForCall(c);
                    maceService.saveComplaint(c.getBatchCode(), consultJson.getPrimaryComplaint());
                    loaService.saveLoa(c, consultJson.getUsername(), consultJson.getPrimaryComplaint());
                    responseCode = msgCode.equals("0") ? "200" : getResponseEntityValues.getResponseCode();
                    responseDesc = msgCode.equals("0") ? remarks : getResponseEntityValues.getResponseDesc();
                    //START INSERT MACE_REQ TABLES
                    requestCode = maceService.saveMaceRequestForConsult(memberDetails, c);
                    response.put("requestCode", requestCode);
                    status = "PENDING";
                    //END INSERT MACE_REQ TABLES
                    break;
                case 1:
                    maceService.storeInTempTransaction(c);
                    responseCode = "210";
                    responseDesc = "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?";
                    response.put("batchCode", c.getBatchCode());
                    break;
                case 2:
                    responseCode = "220";
                    responseDesc = "Request Denied. There is an existing transaction with the same details.";
                    break;
                default:
                    break;
            }
        }
        response.put("batchCode", c.getBatchCode());
        response.put("withProvider", withProvider);
        response.put("responseCode", responseCode);
        response.put("responseDesc", responseDesc);
        response.put("remarks", remarks);
        response.put("status", status);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}