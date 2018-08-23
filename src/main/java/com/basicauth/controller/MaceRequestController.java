package com.basicauth.controller;

/**
 * Created by Giancarlo Angulo.
 */

import com.basicauth.data.*;
import com.basicauth.data.macerequest.member.TestRequestApprovalRequestJson;
import com.basicauth.data.macerequest.member.TestRequestApprovalRequestJson.DiagnosisTestJson;
import com.basicauth.data.MaceRequestProcedure;
import com.basicauth.data.MaceRequestTest;
import com.basicauth.domain.*;
import com.basicauth.domain.dups.MaceConsPrescribedtest;
import com.basicauth.service.ClaimsService;
import com.basicauth.service.MaceRequestService;
import com.basicauth.service.MaceService;
import com.basicauth.service.MemService;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/mace/request")
public class MaceRequestController {

    private static final Logger logger = LoggerFactory.getLogger(MaceRequestController.class);

    @Autowired
    private MaceService maceService;

    @Autowired
    private MemService memService;

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private MaceRequestService maceRequestService;

    @RequestMapping(value = "/filtered", method = RequestMethod.GET)
    public ResponseEntity<?> getList(@RequestParam("memberCode") String memberCode,
                                     @RequestParam(value = "serviceType", defaultValue = "0", required = false) Integer serviceType,
                                     @RequestParam(value = "serviceSubType", defaultValue = "0", required = false) Integer serviceSubType,
                                     @RequestParam(value = "status", defaultValue = "0", required = false) Integer status,
                                     @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                     @RequestParam(value = "count", defaultValue = "10", required = false) Integer count) {

        HashMap<String, Object> response = new HashMap<>();

        List<Object> validationErrors = new ArrayList<>();
        if (memberCode.isEmpty())
            validationErrors.add(ImmutableMap.of("memberCode", "Member code is null or an empty string."));

        if (serviceType < 0 || serviceType > 5)
            validationErrors.add(ImmutableMap.of("serviceType", "Service type is not valid. " +
                    "Valid service types: [0 - No filter, 1 - Consultation, 2 - Test, 3 - Procedure, " +
                    "4 - Inpatient, 5 - Emergency Room]"));

        if (serviceSubType < 0 || serviceSubType > 4)
            validationErrors.add(ImmutableMap.of("serviceType", "Sub-service type is not valid. " +
                    "Valid sub-service types: [0 - No filter, 1 - Non-maternity consultation, " +
                    "2 - Maternity consultation, 3 - Basic Test, 4 - Other Test]"));

        if (page <= 0)
            validationErrors.add(ImmutableMap.of("page", "Page should be greater than or equal to 1."));
        if (count <= 0)
            validationErrors.add(ImmutableMap.of("count", "Count should be greater than or equal to 1."));

        if (!validationErrors.isEmpty()) {
            response.put("validationErrors", validationErrors);
            return ResponseEntity.status(412).body(response);
        }

        response.put("maceRequests", maceService.getServiceTypeFilteredMaceRequestsByMemberCode(
                memberCode, serviceType, serviceSubType, status, page, count));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/createTestRequest", method = RequestMethod.POST)
    public ResponseEntity<?> createTestRequest(@RequestBody TestRequestApprovalRequestJson trarj) {
        trarj.setConsultationDate(new Date());
        List<Map<String, String>> validationErrors = trarj.validate();
        if (!validationErrors.isEmpty()) {
            return new ResponseEntity<>(validationErrors, HttpStatus.PRECONDITION_FAILED);
        }

        List<Map<String, String>> notFound = new ArrayList<>();

        MemberDetails member = memService.getMember(trarj.getMemberCode());
        if (member == null)
            notFound.add(ImmutableMap.of("memberCode", trarj.getMemberCode()));

        DiagnosisEntity primaryDiagnosis = maceService.getDiagnosisEntity(trarj.getPrimaryDiagnosisCode());
        if (primaryDiagnosis == null)
            notFound.add(ImmutableMap.of("primaryDiagnosisCode", trarj.getPrimaryDiagnosisCode()));

        Doctor doctor = claimsService.getDoctor(trarj.getDoctorCode(), false);
        if (doctor == null)
            notFound.add(ImmutableMap.of("doctorCode", trarj.getDoctorCode()));

        Hospital hospital = claimsService.getHospital(trarj.getHospitalCode());
        if (hospital == null)
            notFound.add(ImmutableMap.of("hospitalCode", trarj.getHospitalCode()));

        DoctorToHospital doctorToHospital = claimsService.getDoctorToHospitalObject(
                trarj.getHospitalCode(), trarj.getDoctorCode(), false);

        //TODO Fix DiagProc Entity (This process should allow non-matching DiagProc)
        Map<String, List<SimpleImmutableEntry<DiagnosisTestJson, DiagnosticProceduresEntity>>> dpeByDiagnosisCodeMap =
                Arrays.stream(trarj.getDiagnosisTests())
                        .map(diagnosisTestJson -> {
                            DiagnosticProceduresEntity dpe = maceService.getDiagProcedureByProcedureCode(
                                    diagnosisTestJson.getTestCode(),
                                    diagnosisTestJson.getDiagnosisCode());
                            return new SimpleImmutableEntry<>(diagnosisTestJson, dpe);
                        })
                        .filter(entry -> entry.getValue() != null)
                        .collect(Collectors.groupingBy(f -> f.getKey().getDiagnosisCode(), Collectors.toList()));

        if (!notFound.isEmpty())
            return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);

        assert (member != null);
        assert (primaryDiagnosis != null);
        assert (doctor != null);
        assert (hospital != null);

        HashMap<String, Object> response = new HashMap<>();

        MaceRequest maceRequest = new MaceRequest();
        maceRequest.setServiceTypeId(1);
        maceRequest.setMemCode(trarj.getMemberCode());
        maceRequest.setMemberDetails(member);

        maceRequest.setRequestFromhosp("");
        maceRequest.setRequestOrigin("MEMBER");
        maceRequest.setRequestDatetime(new Date());
        maceRequest.setRequestBy(trarj.getMemberCode());
        maceRequest.setRequestFrommem(trarj.getMemberCode());
        maceRequest.setRequestDevice(trarj.getRequestDeviceId());

        maceRequest.setStatus("PENDING");
        maceRequest.setStatusRemarks("");

        maceService.saveMaceRequest(maceRequest);
        response.put("maceRequest", maceRequest);

        MaceReqConsult macereqConsult = new MaceReqConsult();
        macereqConsult.setMaceRequestId(maceRequest.getRequestId());
        macereqConsult.setRequestFrom(trarj.getMemberCode());
        macereqConsult.setConsultSubtype(1);
        macereqConsult.setConsultationDate(trarj.getConsultationDate());

        macereqConsult.setPrimaryDiagnosisCode(primaryDiagnosis.getDiagCode());
        macereqConsult.setPrimaryDiagnosisDesc(primaryDiagnosis.getDiagDesc());
        macereqConsult.setPrimaryDiagnosisIcd10(primaryDiagnosis.getIcd10Code());

        macereqConsult.setDoctorCode(trarj.getDoctorCode());
        macereqConsult.setHospitalCode(trarj.getHospitalCode());
        //TODO Verify if it's okay to use 0 if doctorToHospital does not match.
        macereqConsult.setDocHospId(null != doctorToHospital ? Integer.parseInt(String.valueOf(doctorToHospital.getDocHospId())) : 0);

        macereqConsult.setStatus("PENDING");
        macereqConsult.setStatusRemarks("");

        Calendar calendar = Calendar.getInstance();
        macereqConsult.setValidfrom(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        macereqConsult.setValidto(calendar.getTime());

        maceService.saveMaceReqConsult(maceRequest, macereqConsult);
        response.put("details", macereqConsult);

        List<Map<String, Object>> diagnosisTests = new ArrayList<>();
        response.put("diagnosisTests", diagnosisTests);

        //TODO Fix DiagProc Entity (This process should allow non-matching DiagProc)
        for (Map.Entry<String, List<SimpleImmutableEntry<DiagnosisTestJson, DiagnosticProceduresEntity>>> dpeByDiagnosisCodeEntry : dpeByDiagnosisCodeMap.entrySet()) {
            DiagnosisEntity diagnosisEntity = maceService.getDiagnosisEntity(dpeByDiagnosisCodeEntry.getKey());
            if (diagnosisEntity == null) {
                notFound.add(ImmutableMap.of("diagnosisCode", dpeByDiagnosisCodeEntry.getKey()));
            } else if (notFound.isEmpty()) {
                MaceRequestOpDiag mrod = new MaceRequestOpDiag();
                mrod.setDiagnosisEntity(diagnosisEntity);
                mrod.setMaceRequestId(maceRequest.getRequestId());
                mrod.setTransactionId(Integer.parseInt(String.valueOf(macereqConsult.getTransactionId())));

                maceService.saveMaceRequestOpDiag(mrod);

                for (SimpleImmutableEntry<DiagnosisTestJson, DiagnosticProceduresEntity> dpeEntry : dpeByDiagnosisCodeEntry.getValue()) {
                    MaceConsPrescribedtest mcpt = new MaceConsPrescribedtest();
                    mcpt.setMacerequestId(Integer.parseInt(String.valueOf(mrod.getMaceRequestId())));
                    mcpt.setReqdiagId(mrod.getReqDiagId());
                    mcpt.setConsTransactionId(Integer.parseInt(String.valueOf(macereqConsult.getTransactionId())));
                    mcpt.setDiagnosisProcedureEntity(dpeEntry.getValue());
                    mcpt.setStatus(0);

                    maceRequestService.save(mcpt);
                    diagnosisTests.add(ImmutableMap.of(
                            "maceReqOpDiag", mrod,
                            "maceConsPrescribedTest", mcpt));
                }
            }
        }
        response.put("maceRequestCode", maceService.getMaceReqCodeByMaceReqId(maceRequest.getRequestId()));
        response.put("responseDesc", "Successfully Created Request.");
        response.put("responseCode", 200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getMaceRequestOpInfo", method = RequestMethod.GET)
    public ResponseEntity<?> getMaceRequestOpInfo(@RequestParam("maceRequestCode") String maceRequestCode) {
        HashMap<String, Object> response = new HashMap<>();
        //Get MaceRequest
        MaceRequest mr = maceService.getMaceRequestByRequestCode(maceRequestCode);
        if (null == mr) {
            response.put("responseDesc", "No data found for this RequestCode.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        //Check ServiceType and get appropriate MaceReq_Object
        List<MaceRequestTest> mrt;
        List<MaceRequestProcedure> mrp;
        if (mr.getServiceTypeId() == 1) {//Cons
            MaceReqConsult mrc = maceService.getMaceReqConsult(mr.getRequestId());
            if (null == mrc) {
                response.put("responseDesc", "No MaceReqConsult Record for the MaceReq ID");
                response.put("responseCode", 404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("MaceRequestInfo", mrc);
        } else if (mr.getServiceTypeId() == 2) {//Tests
            mrt = maceService.getMaceRequestTests(mr.getRequestId());
            if (null == mrt) {
                response.put("responseDesc", "No MaceReqConsult Record for the MaceReq ID");
                response.put("responseCode", 404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("MaceRequestInfo", mrt);
        } else if (mr.getServiceTypeId() == 3) {//Proc
            mrp = maceService.getMaceRequestProcedures(mr.getRequestId());
            if (null == mrp) {
                response.put("responseDesc", "No MaceReqConsult Record for the MaceReq ID");
                response.put("responseCode", 404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("MaceRequestInfo", mrp);
        } else {
            response.put("MaceRequestInfo", null);
            response.put("responseCode", 404);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        //If OP Get MaceReqOpDiag first
        List<MaceRequestOpDiag> mrod = maceService.getMaceReqOpDiagByMaceReqId(mr.getRequestId());
        if (null == mrod) {
            response.put("reponseDesc", "Failed to get Diagnosis List.");
            response.put("responseCode", 404);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        //If OP and it has Tests/Procedures Get from Mace_Req_Op_Proc/Test
        if (mr.getServiceTypeId() == 2) {
            List<MaceRequestOpTest> mrot = maceService.getMaceReqOpTestsByMaceReqId(mr.getRequestId());
            if (null != mrot)
                response.put("TestsOrProcedures", mrot);
        } else if (mr.getServiceTypeId() == 3) {
            List<MaceRequestOpProcedure> mrop = maceService.getMaceReqOpProcByMaceReqId(mr.getRequestId());
            if (null != mrop)
                response.put("TestsOrProcedures", mrop);
        }

        response.put("MaceRequest", mr);
        response.put("DiagnosisList", mrod);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully retrieved info.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/addPrescribedTest", method = RequestMethod.POST)
    public ResponseEntity<?> addPrescribedTest(@RequestBody AddPrescribedTestJson aptj) {
        HashMap<String, Object> response = new HashMap<>();

        MaceRequest mr = maceService.getMaceRequestByRequestCode(aptj.getRequestCode());
        if (null == mr) {
            response.put("responseDesc", "Failed to retrieve Mace Request.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        MaceReqConsult mrc = maceService.getMaceReqConsult(mr.getRequestId());
        if (null == mrc) {
            response.put("responseDesc", "Consultation not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        DoctorToHospital dth = claimsService.getDoctorToHospitalObject(aptj.getHospitalCode(), aptj.getDoctorCode(), false);

        HashMap<String, Object> primary = maceService.addPrescribedTest(aptj, mr, mrc, dth, 1);
        if (null != aptj.getOtherDiagnosisCode() && !aptj.getOtherDiagnosisCode().equals("")) {
            HashMap<String, Object> other = maceService.addPrescribedTest(aptj, mr, mrc, dth, 2);
            response.put("otherDiag", other);
        }

        response.put("responseCode", 200);
        response.put("primary", primary);
        response.put("responseDesc", "Successfully added prescribed test/s.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}