package com.basicauth.controller;

import com.basicauth.data.*;
import com.basicauth.domain.TestProcObject;
import com.basicauth.service.*;
import com.basicauth.service.approval.ApprovalEngine;
import com.basicauth.service.approval.ApprovalEngineResult;
import com.basicauth.types.ConsultJson;
import com.basicauth.utils.StringUtils;
import net.incuventure.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.basicauth.config.Constants.DEFAULT_GROUP;
import static com.basicauth.config.Constants.REQUEST_AUTOMATIC_APPROVED;

/**
 * Created by IPC_Laptop056 on 9/21/2017.
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping(value = "/provider/v2")
public class ProviderVersion2Controller {

    private static final Logger log = LoggerFactory.getLogger(ProviderVersion2Controller.class);
    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private UserService userService;

    @Autowired
    private MaceService maceService;

    @Autowired
    private MemService memService;

    @Autowired
    private AppUserService appUserService;

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

    @Autowired
    private ProviderService providerService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private ApprovalEngine approvalEngine;

    @RequestMapping(value = "requestForProceduresInClinic/", method = RequestMethod.POST)
    public ResponseEntity<?> requestForProceduresInClinic(@RequestBody MaceRequestRequestJson mrrj) {
        HashMap<String, Object> response = new HashMap<>();

        //Check if there are procedures to approve
        if (null == mrrj.getProcedures() || mrrj.getProcedures().length == 0) {
            response.put("responseCode", 200);
            response.put("responseDesc", "Empty request. Nothing to approve.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        //Retrieve user of the app
        AppUser appUser = appUserService.findByUsername(mrrj.getAppUsername());
        if (appUser == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The app user (username: %s) " +
                    "was not found in the database.", mrrj.getAppUsername()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //Retrieve Doctor
        Doctor doctor = claimsService.getDoctor(mrrj.getMaceRequest().getDoctorCode(), false);
        if (doctor == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The doctor (code: %s) " +
                    "was not found in the database.", mrrj.getMaceRequest().getDoctorCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //Retrieve Hospital
        Hospital hospital = claimsService.getHospital(mrrj.getMaceRequest().getHospitalCode());
        if (hospital == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The hospital (code: %s) " +
                    "was not found in the database.", mrrj.getMaceRequest().getHospitalCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //Retrieve Member Details
        MemberDetails memberDetails = memService.getMember(mrrj.getMaceRequest().getMemberCode());
        if (memberDetails == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The member (code: %s) " +
                    "was not found in the database.", mrrj.getMaceRequest().getMemberCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //Prior consultation is required
        AvailedConsult availedConsult = maceService.getAvailedConsultationByApprovalNo(mrrj.getMaceRequest().getReferenceNo());
        if ((mrrj.getMaceRequest().getReferenceNo() != null && mrrj.getMaceRequest().getReferenceNo().isEmpty()) && availedConsult == null) {
            if (mrrj.getAppType() != null || mrrj.getAppType().isEmpty()
                    || mrrj.getAppType().equalsIgnoreCase("provider")
                    || mrrj.getAppType().equalsIgnoreCase("coordinator")) {
                response.put("responseCode", 412);
                response.put("responseDesc", "A valid reference number to a consultation is required, " +
                        "when request is from a mobile application.");
                return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
            } else {
                response.put("responseCode", 404);
                response.put("responseDesc", String.format("The specified reference number for a consultation (code: %s) " +
                        "was not found in the database.", mrrj.getMaceRequest().getReferenceNo()));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }

        //DoctorToHospital is required
        @SuppressWarnings("unchecked")
        List<DoctorToHospital> doctorToHospitalList = claimsService.getDoctorToHospital(
                mrrj.getMaceRequest().getHospitalCode(), mrrj.getMaceRequest().getDoctorCode());

        if (doctorToHospitalList.size() == 0) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The doctor (code: %s) mapped to " +
                            "hospital (code: %s) was not found in the database.",
                    mrrj.getMaceRequest().getDoctorCode(), mrrj.getMaceRequest().getHospitalCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else if (doctorToHospitalList.size() > 1) {
            response.put("responseCode", 500);
            response.put("responseDesc", String.format("Multiple mapping for doctor (code: %s) to " +
                            "hospital (code: %s) was found in the database.",
                    mrrj.getMaceRequest().getDoctorCode(), mrrj.getMaceRequest().getHospitalCode()));
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        DoctorToHospital doctorToHospital = doctorToHospitalList.get(0);

        //Initialize CustomerCare
        CustomerCare c = maceService.initCustomerCare(memberDetails,
                mrrj.getMaceRequest().getHospitalCode(), mrrj.getMaceRequest().getDoctorCode(),
                null, null, null,
                null, mrrj.getAppUsername(), null,
                mrrj.getMaceRequest().getMemberCode(), null, null,
                null, null, null, null,
                mrrj.getDeviceID(), null, null, null);
        c.setCallerId(maceService.generateID("CALLERID"));
        c.setBatchCode(maceService.generateID("BATCHNO"));

        List<String> pendingProcedures = new ArrayList<>();
        List<MaceReqOpDiagnosisProcedure> maceReqOpDiagnosisProcedures = new ArrayList<>();
        HashMap<String, MaceRequestProcedure> maceRequestProcedures = new HashMap<>();
        HashMap<Integer, HashMap<String, HashMap<String, List<MaceReqOpDiagnosisProcedure>>>> mrodpServiceTypeGroups = new HashMap<>();

        String finalAssignee = DEFAULT_GROUP;

        //Loop for prescribed Procedure subrequests
        for (MaceRequestProcedureJson mrpj : mrrj.getProcedures()) {
            Procedure procedure = claimsService.getProcedureByProcedureCode(mrpj.getProcCode());
            if (procedure == null) {
                response.put("responseCode", 404);
                response.put("responseDesc", String.format("The procedure (code: %s) " +
                        "was not found in the database.", mrpj.getProcCode()));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            Diagnosis diagnosis = claimsService.getDiagnosisByDiagnosisCode(mrpj.getDiagCode());
            if (diagnosis == null) {
                response.put("responseCode", 404);
                response.put("responseDesc", String.format("The diagnosis (code: %s) " +
                        "was not found in the database.", mrpj.getDiagCode()));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            MaceRequestProcedure mrp;
            MaceRequestOpDiag mrod = new MaceRequestOpDiag();
            MaceRequestOpProcedure mrop = new MaceRequestOpProcedure();

            mrod.setDiagCode(diagnosis.getDiagCode());
            mrod.setDiagDesc(diagnosis.getDiagDesc());
            mrod.setDiagType(diagnosis.getType());
            mrod.setDiagRemarks(diagnosis.getDiagRemarks());
            mrod.setIcd10Code(diagnosis.getIcd10Code());

            mrop.setProcActualAmount(null != procedure.getProcedureRate()? procedure.getProcedureRate(): BigDecimal.ZERO);
            mrop.setDiagCode(mrpj.getDiagCode());
            mrop.setProcCode(mrpj.getProcCode());

            boolean autoApprovedRequest = false;

            // check if the diagnosis is unique (when diagnosis code is not in the set)
            if (!maceRequestProcedures.containsKey(mrpj.getDiagCode())) {
                mrp = new MaceRequestProcedure();
                maceRequestProcedures.put(mrpj.getDiagCode(), mrp);

                mrp.setPrimaryDiagnosisCode(diagnosis.getDiagCode());
                mrp.setPrimaryDiagnosisDesc(diagnosis.getDiagDesc());
                mrp.setPrimaryDiagnosisICD10(diagnosis.getIcd10Code());

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_YEAR, 2);
                mrp.setValidFrom(new Date());
                mrp.setValidTo(calendar.getTime()); // 2 days from now

                mrp.setNotes("");
                mrp.setDxRemarks("");
                mrp.setTestSubtype(0);
                mrp.setMaceTestGroup("");
                mrp.setConsultReason("");
                mrp.setPlanOfManagement("");
                mrp.setRequestFrom(mrrj.getAppUsername());

                mrp.setHospitalCode(mrrj.getMaceRequest().getHospitalCode());
                mrp.setDoctorCode(mrrj.getMaceRequest().getDoctorCode());
                mrp.setAvailHospId(mrp.getHospitalCode());

                //Get TestProcObject for approval engine execution
                TestProcObject tpo = claimsService.getTestProcObject(mrpj.getProcCode());
                //Set service type to 3 (Procedures)
                tpo.setServiceType(3);
                //Set procedures subtype = 0(Procedures has no subtype)
                tpo.setSubType(0);
                //Set amount for approval engine execution
                tpo.setAmount(BigDecimal.valueOf(mrpj.getAmount()));

                //Execute V2 Approval Engine
                ApprovalEngineResult approvalEngineResult = approvalEngine.executeApprovalEngine(memberDetails, diagnosis, hospital, doctor, tpo, tpo.getAmount().doubleValue());

                autoApprovedRequest = approvalEngineResult.getFinalRequestStatus().equals(REQUEST_AUTOMATIC_APPROVED);

                //Update final status assignee
                finalAssignee = approvalEngine.updateFinalStatusAssignee(finalAssignee, approvalEngineResult.getFinalStatusAssignee(), hospital);

                if (autoApprovedRequest) {
                    mrp.setApprovedOn(new Date());
                    mrp.setApprovalRemarks("Procedures - Approved");
                    mrp.setApprovedBy(maceService.generateID("BATCHNO"));
                    mrp.setApprovalNo(maceService.generateID("APPROVALNO"));
                    mrp.setStatus("APPROVED");
                    mrp.setStatusRemarks("Approved automatically by the system.");
                } else {
                    mrp.setStatus("PENDING");
                    mrp.setStatusRemarks("Pending - Waiting for approval.");
                }
                mrp.setTransCode(maceService.generateID("PROCEDUREREQUEST"));
                mrp.setTransamount(null != procedure.getProcedureRate()? procedure.getProcedureRate(): BigDecimal.ZERO);
                mrp.setDocHospId(doctorToHospital.getDocHospId());
                mrp.setRefRefNo(mrrj.getMaceRequest().getReferenceNo());
            } else {
                mrp = maceRequestProcedures.get(mrpj.getDiagCode());
            }

            if (autoApprovedRequest) {
                mrop.setRemarks("Automatically approved by the system.");
            } else {
                pendingProcedures.add(mrop.getProcCode());
            }

            HashMap<String, HashMap<String, List<MaceReqOpDiagnosisProcedure>>> mrodpDiagnosisCodeGrouping;
            if (!mrodpServiceTypeGroups.containsKey(mrpj.getServiceType())) {
                mrodpDiagnosisCodeGrouping = new HashMap<>();
                mrodpServiceTypeGroups.put(mrpj.getServiceType(), mrodpDiagnosisCodeGrouping);
            } else {
                mrodpDiagnosisCodeGrouping = mrodpServiceTypeGroups.get(mrpj.getServiceType());
            }

            HashMap<String, List<MaceReqOpDiagnosisProcedure>> mrodpCostCenterGrouping;
            if (!mrodpDiagnosisCodeGrouping.containsKey(mrpj.getDiagCode())) {
                mrodpCostCenterGrouping = new HashMap<>();
                mrodpDiagnosisCodeGrouping.put(mrpj.getDiagCode(), mrodpCostCenterGrouping);
            } else {
                mrodpCostCenterGrouping = mrodpDiagnosisCodeGrouping.get(mrpj.getDiagCode());
            }

            List<MaceReqOpDiagnosisProcedure> mrodpServiceTypeGroup;
            if (!mrodpCostCenterGrouping.containsKey(appUser.getCostcenter())) {
                mrodpServiceTypeGroup = new ArrayList<>();
                mrodpCostCenterGrouping.put(appUser.getCostcenter(), mrodpServiceTypeGroup);
            } else {
                mrodpServiceTypeGroup = mrodpCostCenterGrouping.get(appUser.getCostcenter());
            }

            MaceReqOpDiagnosisProcedure mrodp = new MaceReqOpDiagnosisProcedure(mrod, mrop, mrp);
            mrodpServiceTypeGroup.add(mrodp);
            maceReqOpDiagnosisProcedures.add(mrodp);
        }

        // the request's status is approved if all of the procedures are approved,
        // otherwise, the request's status is set to pending
        boolean allProceduresAreApproved = pendingProcedures.size() == 0;
        if (allProceduresAreApproved) {
            maceService.saveTransaction(c, memberDetails);
            customerServiceService.saveTransaction(c, memberDetails);
        } else {
            maceService.saveTransactionForCall(c);
            customerServiceService.saveTransactionForCall(c);
        }

        MaceRequest maceRequest = new MaceRequest();
        maceRequest.setRequestDatetime(new Date());
        maceRequest.setStatus(allProceduresAreApproved ? "APPROVED" : "PENDING");
        maceRequest.setStatusAssignee(!finalAssignee.equals(DEFAULT_GROUP) ? finalAssignee : null);
        maceRequest.setRequestFromhosp(mrrj.getMaceRequest().getHospitalCode());
        maceRequest.setRequestFrommem(mrrj.getMaceRequest().getMemberCode());
        maceRequest.setServiceTypeId(3);

        maceRequest.setMemCode(mrrj.getMaceRequest().getMemberCode());
        maceRequest.setMemLname(memberDetails.getMEM_LNAME());
        maceRequest.setMemFname(memberDetails.getMEM_FNAME());
        maceRequest.setMemMi("");

        maceRequest.setMemGender(memberDetails.getMEM_SEX() == 1 ? "MALE" :
                memberDetails.getMEM_SEX() == 1 ? "FEMALE" : "UNKNOWN");

        LocalDate birthday = LocalDate.parse(memberDetails.getBDAY(), DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        maceRequest.setMemBdate(Date.from(birthday.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Period period = Period.between(birthday, LocalDate.now());
        maceRequest.setMemAge(period.getYears());

        maceRequest.setMemType(memberDetails.getACCT_TYPE());
        maceRequest.setMemCompany(memberDetails.getACCOUNT_NAME());
        maceRequest.setMemAcct(memberDetails.getACCOUNT_CODE());

        maceService.saveMaceRequest(maceRequest);
        if (maceReqOpDiagnosisProcedures.size() > 0) {
            maceService.saveMaceReqOpDiagnosisProcedures(maceRequest, maceReqOpDiagnosisProcedures);
        }

        for (Map.Entry<Integer, HashMap<String, HashMap<String, List<MaceReqOpDiagnosisProcedure>>>> mrodpServiceTypeGroupSet : mrodpServiceTypeGroups.entrySet()) {
            for (Map.Entry<String, HashMap<String, List<MaceReqOpDiagnosisProcedure>>> mrodpDiagnosisCodeGroupSet : mrodpServiceTypeGroupSet.getValue().entrySet()) {
                for (Map.Entry<String, List<MaceReqOpDiagnosisProcedure>> mrodpCostCenterGroupSet : mrodpDiagnosisCodeGroupSet.getValue().entrySet()) {
                    CustomerCare c2 = maceService.initCustomerCare(memberDetails,
                            mrrj.getMaceRequest().getHospitalCode(), mrrj.getMaceRequest().getDoctorCode(),
                            null, null, null,
                            null, mrrj.getAppUsername(), null,
                            mrrj.getMaceRequest().getMemberCode(), null, null,
                            null, null, null, null,
                            mrrj.getDeviceID(), null, null, null);
                    c2.setCallerId(maceService.generateID("CALLERID"));
                    c2.setBatchCode(maceService.generateID("BATCHNO"));

                    log.debug(String.format("requestForProceduresInClinic - UNIQUE GROUP: serviceType: %d, diagnosisCode: %s, costCenter: %s",
                            mrodpServiceTypeGroupSet.getKey(), mrodpDiagnosisCodeGroupSet.getKey(), mrodpCostCenterGroupSet.getKey()));

                    LoaMaceRequests loaMaceRequest = new LoaMaceRequests();
                    loaMaceRequest.setMemCode(mrrj.getMaceRequest().getMemberCode());
                    loaMaceRequest.setMemFname(memberDetails.getMEM_FNAME());
                    loaMaceRequest.setMemLname(memberDetails.getMEM_LNAME());
                    loaMaceRequest.setMemMi("");

                    loaMaceRequest.setCompany(memberDetails.getACCOUNT_NAME());
                    loaMaceRequest.setRequestedDate(new Date());
                    loaMaceRequest.setDeviceId(mrrj.getDeviceID());

                    BigDecimal totalProcAmount = BigDecimal.ZERO;

                    String procCode = StringUtils.concatenateStringListUsing("|",
                            mrodpCostCenterGroupSet.getValue().stream()
                                    .map(m -> m.getMaceRequestOpProcedure().getProcCode())
                                    .collect(Collectors.toList()));
                    String procDesc = StringUtils.concatenateStringListUsing("|",
                            mrodpCostCenterGroupSet.getValue().stream()
                                    .map(m -> m.getMaceRequestOpProcedure().getProcDesc())
                                    .collect(Collectors.toList()));
                    String diagCode = StringUtils.concatenateStringListUsing("|",
                            mrodpCostCenterGroupSet.getValue().stream()
                                    .map(m -> m.getMaceRequestOpDiag().getDiagCode())
                                    .collect(Collectors.toList()));
                    String diagDesc = StringUtils.concatenateStringListUsing("|",
                            mrodpCostCenterGroupSet.getValue().stream()
                                    .map(m -> m.getMaceRequestOpDiag().getDiagDesc())
                                    .collect(Collectors.toList()));

                    ConsultJson consultJson;
                    boolean isRequestPending = false;
                    if (mrodpServiceTypeGroupSet.getKey() == 1) {
                        // FOR BASIC TESTS
                        consultJson = new ConsultJson();
                        consultJson.setUsername(mrrj.getAppUsername());
                        consultJson.setDeviceId(mrrj.getDeviceID());
                        consultJson.setMemberCode(mrrj.getMaceRequest().getMemberCode());
                        consultJson.setHospitalCode(mrrj.getMaceRequest().getHospitalCode());
                        consultJson.setPrimaryComplaint(mrrj.getMaceRequest().getPrimaryComplaint());
                        consultJson.setSearchType(mrrj.getMaceRequest().getSearchType());
                        consultJson.setLocationCode(""); // blank

                        consultJson.setProcedureAmount(totalProcAmount);
                        consultJson.setProcedureCode(procCode);
                        consultJson.setProcedureDesc(procDesc);
                        consultJson.setDiagnosisCode(diagCode);
                        consultJson.setDiagnosisDesc(diagDesc);

                        maceService.requestLOABasicTest(memberDetails, consultJson);
                    } else if (mrodpServiceTypeGroupSet.getKey() == 2) {
                        // FOR OTHER TESTS
                        OtherTestJson othertestJson = new OtherTestJson();
                        othertestJson.setUsername(mrrj.getAppUsername());
                        othertestJson.setDeviceId(mrrj.getDeviceID());
                        othertestJson.setMemberCode(mrrj.getMaceRequest().getMemberCode());
                        othertestJson.setHospitalCode(mrrj.getMaceRequest().getHospitalCode());
                        othertestJson.setPrimaryComplaint(mrrj.getMaceRequest().getPrimaryComplaint());
                        othertestJson.setLocationCode(""); // blank

                        for (MaceReqOpDiagnosisProcedure mrodp : mrodpCostCenterGroupSet.getValue()) {
                            totalProcAmount = totalProcAmount.add(mrodp.getMaceRequestOpProcedure().getProcActualAmount());
                        }

                        othertestJson.setProcedureCode(procCode);
                        othertestJson.setProcedureDesc(procDesc);
                        othertestJson.setDiagnosisCode(diagCode);
                        othertestJson.setDiagnosisDesc(diagDesc);

                        maceService.requestLOAOtherTest(memberDetails, othertestJson);
                    } else if (mrodpServiceTypeGroupSet.getKey() == 3) {
                        for (MaceReqOpDiagnosisProcedure mrodp : mrodpCostCenterGroupSet.getValue()) {
                            totalProcAmount = totalProcAmount.add(mrodp.getMaceRequestOpProcedure().getProcActualAmount());
                        }
                    }

                    isRequestPending = totalProcAmount.compareTo(BigDecimal.valueOf(1000)) > 0;

                    loaMaceRequest.setApprovalNo(maceService.generateID("APPROVALNO"));
                    loaService.saveLoa(c, mrrj.getAppUsername(), "");

                    if (isRequestPending) {
                        maceService.saveTransactionForCall(c2);
                        customerServiceService.saveTransactionForCall(c2);
                    } else {
                        maceService.saveTransaction(c2, memberDetails);
                        customerServiceService.saveTransaction(c2, memberDetails);
                    }
                }
            }
        }

        MaceRequestResponseJson responseJson = new MaceRequestResponseJson();
        responseJson.setMemCode(mrrj.getMaceRequest().getMemberCode());
        responseJson.setMaceRequestId(maceRequest.getRequestId());
        responseJson.setStatus(maceRequest.getStatus());

        List<MaceRequestResponseJson.MaceRequestProcedureResponseJson> responseProcedureJsons = new ArrayList<>();
        for (MaceReqOpDiagnosisProcedure mrodp : maceReqOpDiagnosisProcedures) {
            MaceRequestResponseJson.MaceRequestProcedureResponseJson responseProcedureJson = new MaceRequestResponseJson.MaceRequestProcedureResponseJson();
            responseProcedureJson.setTransactionId(mrodp.getMaceRequestOpDiag().getTransactionId());
            responseProcedureJson.setDiagProcId(mrodp.getMaceRequestOpProcedure().getDiagProcId());
            responseProcedureJson.setReqDiagId(mrodp.getMaceRequestOpDiag().getReqDiagId());
            responseProcedureJson.setDiagCode(mrodp.getMaceRequestOpProcedure().getDiagCode());
            responseProcedureJson.setProcCode(mrodp.getMaceRequestOpProcedure().getProcCode());
            responseProcedureJson.setProcActualAmount(mrodp.getMaceRequestOpProcedure().getProcActualAmount());
            responseProcedureJson.setStatus(mrodp.getMaceRequestOpProcedure().getStatus());
            responseProcedureJson.setValidFromDateString(
                    dateTimeFormatter.format(mrodp.getMaceRequestProcedure().getValidTo()));
            responseProcedureJson.setValidToDateString(
                    dateTimeFormatter.format(mrodp.getMaceRequestProcedure().getValidTo()));
            responseProcedureJsons.add(responseProcedureJson);
        }

        responseJson.setProcedures(responseProcedureJsons.stream().toArray(MaceRequestResponseJson.MaceRequestProcedureResponseJson[]::new));

        response.put("requestStatus", responseJson);
        response.put("responseCode", 200);
        response.put("responseDesc", "The request for procedures was successfully processed.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
