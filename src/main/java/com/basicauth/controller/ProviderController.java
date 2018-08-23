package com.basicauth.controller;

import com.basicauth.data.*;
import com.basicauth.domain.*;
import com.basicauth.domain.dups.MaceConsPrescribedtest;
import com.basicauth.service.*;
import com.basicauth.types.ConsultJson;
import com.basicauth.types.LoginJson;
import com.basicauth.utils.StringUtils;
import net.incuventure.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.basicauth.data.MaceRequestResponseJson.*;

/**
 * Created by Jabito on 4/6/2017.
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping(value = "/provider/")
public class ProviderController {

    private static final Logger log = LoggerFactory.getLogger(ProviderController.class);
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

    //<editor-fold desc="Listing Controllers" defaultstate="collapsed">
    @RequestMapping(value = "diagTestMapList/", method = RequestMethod.GET)
    public ResponseEntity<?> diagTestMapList() {
        HashMap<String, Object> response = new HashMap<>();
        String object = "text";

        List<DiagnosisProceduresViewEntity> list = claimsService.getDiagnosisProcedureList();
        if (null != list) {
            response.put("diagTestMapList", list);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully retrieved list.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("diagTestMapList", null);
            response.put("responseCode", 404);
            response.put("responseDesc", "Failed to retrieve list.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * These methods are duplicates from ListingVersion2Controller
     */

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
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully retrieved List.");
            log.info("getHospitals OK");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
            log.info("getHospitals BAD REQUEST");
            HashMap<String, Object> response = new HashMap<>();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "getDiagnosisList/", method = RequestMethod.GET)
    public ResponseEntity<?> getDiagnosisList(@RequestParam("last_update_date") String lastUpdateDateString) {
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "getProceduresList/", method = RequestMethod.GET)
    public ResponseEntity<?> getProceduresList() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("responseCode", 200);
        response.put("proceduresList", maceService.getProceduresList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "getBasicTests/", method = RequestMethod.GET)
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

    @RequestMapping(value = "getOtherTests/", method = RequestMethod.GET)
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

    /**
     * End Duplicates from ListingVerison2Controller
     */
    //</editor-fold>
    @RequestMapping(value = "validateProvider/", method = RequestMethod.POST)
    public ResponseEntity<?> validateProvider(@RequestBody ValidateProviderJson providerJson) {
        return providerService.validateProvider(providerJson);
    }

    @RequestMapping(value = "registerProvider/", method = RequestMethod.POST)
    public ResponseEntity<?> registerProvider(@RequestBody RegisterProviderJson providerJson) {
        return providerService.registerProvider(providerJson);
    }

    @RequestMapping(value = "loginProvider/", method = RequestMethod.POST)
    public ResponseEntity<?> loginProvider(@RequestBody LoginJson loginJson) {
        return providerService.loginProvider(loginJson);
    }

    @RequestMapping(value = "forgotPasswordProvider/", method = RequestMethod.POST)
    public ResponseEntity<?> forgotPasswordProvider(@RequestParam("email") String email, @RequestParam("doctorCode") String doctorCode) {
        return providerService.forgotPasswordProvider(email, doctorCode);
    }

    @RequestMapping(value = "changePasswordProvider/", method = RequestMethod.POST)
    public ResponseEntity<?> changePasswordProvider(@RequestBody ChangePasswordJson changePasswordJson) {
        return providerService.changePasswordProvider(changePasswordJson);
    }

    @RequestMapping(value = "getAvailedConsultFromDoctor/", method = RequestMethod.GET)
    public ResponseEntity<?> getAvailedConsultFromDoctor(@RequestParam("memberCode") String memberCode,
                                                         @RequestParam(value = "birthDate", defaultValue = "", required = false) String birthDate,
                                                         @RequestParam("searchType") String searchType,
                                                         @RequestParam("doctorCode") String doctorCode) {

        HashMap<String, Object> response = new HashMap<>();
        //Validate Birthday with MemberCode
        if ((searchType.equalsIgnoreCase("manual") && (birthDate == null || (!birthDate.isEmpty() && maceService.getBdayByMemberCodeAndCompare(memberCode, birthDate))))
                || (searchType.equalsIgnoreCase("qrcode") && maceService.checkIfMemberExistsByMemberCode(memberCode))) {
            try {
                List<AvailedConsultObject> availedConsultObjects = new ArrayList<>();
                List<MaceRequest> maceRequests = maceService.getAvailedMaceRequestsByDocCodeAndMemCode(doctorCode, memberCode);
                for (MaceRequest mr : maceRequests) {
                    AvailedConsultObject aco = new AvailedConsultObject();
                    MaceReqConsult mrc = maceService.getMaceReqConsult(mr.getRequestId());
                    aco.setMaceRequest(mr);
                    aco.setMaceReqConsult(mrc);
                    availedConsultObjects.add(aco);
                }

                response.put("availedConsultations", availedConsultObjects);
                response.put("responseCode", 200);
                response.put("responseDesc", "Successfully retrieved Consultations.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.put("availedConsultations", null);
            response.put("responseCode", 404);
            response.put("responseDesc", "No Availed Consultations Found.");
        }

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "requestForProceduresInClinic/", method = RequestMethod.POST)
    public ResponseEntity<?> requestForProceduresInClinic(@RequestBody MaceRequestRequestJson mrrj) {
        HashMap<String, Object> response = new HashMap<>();

        if (null == mrrj.getProcedures() || mrrj.getProcedures().length == 0) {
            response.put("responseCode", 200);
            response.put("responseDesc", "Empty request. Nothing to approve.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        AppUser appUser = appUserService.findByUsername(mrrj.getAppUsername());
        if (appUser == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The app user (username: %s) " +
                    "was not found in the database.", mrrj.getAppUsername()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Doctor doctor = claimsService.getDoctor(mrrj.getMaceRequest().getDoctorCode(), false);
        if (doctor == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The doctor (code: %s) " +
                    "was not found in the database.", mrrj.getMaceRequest().getDoctorCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Hospital hospital = claimsService.getHospital(mrrj.getMaceRequest().getHospitalCode());
        if (hospital == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The hospital (code: %s) " +
                    "was not found in the database.", mrrj.getMaceRequest().getHospitalCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        MemberDetails memberDetails = memService.getMember(mrrj.getMaceRequest().getMemberCode());
        if (memberDetails == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The member (code: %s) " +
                    "was not found in the database.", mrrj.getMaceRequest().getMemberCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

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

            mrop.setProcActualAmount(procedure.getProcedureRate());
            mrop.setDiagCode(mrpj.getDiagCode());
            mrop.setProcCode(mrpj.getProcCode());

            // check if the doctor is available to perform the procedure at the hospital on a specific date.
            boolean isPending = !maceService.isProcedureAvailable(mrrj.getMaceRequest().getDoctorCode(),
                    mrrj.getMaceRequest().getHospitalCode(), mrop.getDiagCode());

            // check if diagnosis and procedure match
            isPending |= claimsService.checkIfDiagCodeAndProcedureCodeMatches(
                    mrop.getDiagCode(), mrop.getProcCode());

            // check if transaction amount is above PHP 1000
            isPending |= procedure.getProcedureRate().compareTo(BigDecimal.valueOf(1000)) > 0;

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

                if (!isPending) {
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
                mrp.setTransamount(procedure.getProcedureRate());
                mrp.setDocHospId(doctorToHospital.getDocHospId());
                mrp.setRefRefNo(mrrj.getMaceRequest().getReferenceNo());
            } else {
                mrp = maceRequestProcedures.get(mrpj.getDiagCode());
            }

            if (isPending) {
                mrop.setStatus(0);
                pendingProcedures.add(mrop.getProcCode());
            } else {
                mrop.setStatus(1);
                mrop.setRemarks("Automatically approved by the system.");
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
        maceRequest.setRequestFromhosp(mrrj.getMaceRequest().getHospitalCode());
        maceRequest.setRequestFrommem(mrrj.getMaceRequest().getMemberCode());
        maceRequest.setServiceTypeId(mrrj.getMaceRequest().getServiceType());

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

        List<MaceRequestProcedureResponseJson> responseProcedureJsons = new ArrayList<>();
        for (MaceReqOpDiagnosisProcedure mrodp : maceReqOpDiagnosisProcedures) {
            MaceRequestProcedureResponseJson responseProcedureJson = new MaceRequestProcedureResponseJson();
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

        responseJson.setProcedures(responseProcedureJsons.stream().toArray(MaceRequestProcedureResponseJson[]::new));

        response.put("requestStatus", responseJson);
        response.put("responseCode", 200);
        response.put("responseDesc", "The request for procedures was successfully processed.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "getRequestByReferenceNo/", method = RequestMethod.GET)
    public ResponseEntity<?> getRequestByReferenceNo(@RequestParam("referenceNo") String referenceNo) {
        HashMap<String, Object> response = new HashMap<>();

        response = providerService.getRequestByReferenceNo(referenceNo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "saveConsultDetails/", method = RequestMethod.POST)
    public ResponseEntity<?> saveConsultDetails(@RequestBody ConsultDetails request) {
        HashMap<String, Object> response = new HashMap<>();

        MaceReqConsult mrc = maceService.getMaceReqConsultByReferenceNo(request.getReferenceNo());
        if (null == mrc) {
            response.put("responseCode", 404);
            response.put("responseDesc", "Invalid MaceReqConsult ReferenceNo");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        MaceRequest mr = maceService.getMaceRequestByRequestId(mrc.getMaceRequestId());
        if (null == mr) {
            response.put("responseCode", 404);
            response.put("responseDesc", "Cannot retrieve MaceRequest.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        MemberDetails member = memService.getMember(request.getMemberCode());
        Hospital hospital = claimsService.getHospital(request.getHospitalCode());

        mrc.setHistoryOfPresentIllness(request.getHistoryOfPresentIllness());
        mrc.setPastOrFamilyHistory(request.getPastOrFamilyHistory());
        mrc.setReviewOfSystems(request.getReviewOfSystems());
        mrc.setVitalsBp(request.getVitalsBp());
        mrc.setVitalsHr(request.getVitalsHr());
        mrc.setVitalsRr(request.getVitalsRr());
        mrc.setVitalsTemp(request.getVitalsTemp());
        mrc.setPhysicalExamination(request.getPhysicalExamination());
        mrc.setDxRemarks(request.getDxRemarks());
        mrc.setCongenital(request.getIsCongenital());
        mrc.setMaternity(request.getIsMaternity());
        mrc.setMedicolegal(request.getIsMedicolegal());
        mrc.setPlanOfManagement(request.getPlanOfManagement());

        Map<String, List<ConsultDetails.PrescribedTestJson>> groupedByDiagCode = request.getPrescribedTests().stream()
                .collect(Collectors.groupingBy(ConsultDetails.PrescribedTestJson::getDiagCode, Collectors.toList()));

        List<BasicOrOtherTestResponseJson.DiagnosisProcedureJson> dpjReturn = new ArrayList<>();

        for (Map.Entry<String, List<ConsultDetails.PrescribedTestJson>> ptj : groupedByDiagCode.entrySet()) {
            //Save Diagnosis
            ConsultDetails.PrescribedTestJson ptjObj = ptj.getValue().get(0);
            String groupDiagCode = "";
            if (null != ptjObj.getDiagCode())
                groupDiagCode = ptjObj.getDiagCode();
            DiagnosisEntity de = maceService.getDiagnosisEntity(groupDiagCode);
            String requestCode = maceService.generateID("APPROVALNO");

            List<ConsultDetails.PrescribedTestJson> ptjList = ptj.getValue();
            for (ConsultDetails.PrescribedTestJson prescribedTestJson : ptjList) {
                switch (prescribedTestJson.getServiceSubType()) {
                    case 3://Basic Test
                    case 4://Other Test
                        /**
                         * Adding grouping by DiagType and CostCenter before transacting.
                         * */
                        //Try to get match of Diagnosis and Procedure
                        DiagnosticProceduresEntity dpe = maceService.getDiagProcedureByProcedureCode(prescribedTestJson.getProcCode(), groupDiagCode);
                        //Checks if there is a Matching of DiagnosisProcedure
                        MaceRequestTest mrt = new MaceRequestTest();
                        //TODO Complete request
                        mrt.setApprovalNo(requestCode);
                        mrt.setConsultReason(mrc.getConsultReason());
                        mrt.setDoctorCode(mrc.getDoctorCode());
                        mrt.setHospitalCode(hospital.getHospitalCode());
                        DoctorToHospital docHosp = claimsService.getDoctorToHospitalObject(hospital.getHospitalCode(), mrc.getDoctorCode(), false);
                        mrt.setDocHospId(null == docHosp ? 0 : Long.parseLong(String.valueOf(docHosp.getDocHospId())));
                        mrt.setPrimaryDiagnosisCode(de.getDiagCode());
                        mrt.setPrimaryDiagnosisDesc(de.getDiagDesc());
                        mrt.setPrimaryDiagnosisICD10(de.getIcd10Code());
                        mrt.setDxRemarks(de.getDiagRemarks());
                        mrt.setAvailHospId(hospital.getHospitalCode());
                        mrt.setTransamount(prescribedTestJson.getAmount());
                        mrt.setTestSubtype(4);
                        maceService.saveMaceReqTest(mr, mrt);

                        MaceRequestOpDiag mrod = new MaceRequestOpDiag();
                        mrod.setDiagnosisEntity(maceService.getDiagnosisEntity(de.getDiagCode()));
                        mrod.setMaceDiagType(1);//IDK if there is Other Diag for this service
                        mrod.setMaceRequestId(mr.getRequestId());
                        mrod.setDiagnosisEntity(de);
                        mrod.setTransactionId(mrt.getTransactionId());
                        maceService.saveMaceRequestOpDiag(mrod);

                        MaceRequestOpTest mrot = new MaceRequestOpTest();
                        mrot.setDiagCode(de.getDiagCode());
                        TestProcObject tpo = claimsService.getTestProcObject(prescribedTestJson.getProcCode());
                        mrot.setProcCode(tpo.getProcCode());
                        mrot.setProcDesc(tpo.getProcName());
                        mrot.setMaceSubtype(tpo.getSubType());
                        mrot.setStatus(0);
                        mrot.setProcDefAmount(tpo.getAmount());
                        mrot.setProcActualAmount(prescribedTestJson.getAmount());

                        maceService.saveMaceRequestOpTest(mr, mrt, mrod, mrot);

                        BasicOrOtherTestResponseJson.DiagnosisProcedureJson dpj = new BasicOrOtherTestResponseJson.DiagnosisProcedureJson();
                        dpj.setMaceRequestTest(mrt);
                        dpj.setMaceRequestOpDiag(mrod);
                        dpj.setMaceRequestOpTest(mrot);
                        dpjReturn.add(dpj);
                        break;
                    case 5://Procedure
                        break;
                    default:
                        break;
                }

                if(ptjObj.getDiagType() == 1){
                    mrc.setPrimaryDiagnosisEntity(de);
                }
            }
        }

        maceService.updateMaceReqConsult(mrc);
        response.put("data", dpjReturn);
        response.put("maceRequest", mr);
        response.put("maceReqConsult", mrc);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully Saved Consult Details.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "saveConsult/", method = RequestMethod.POST)
    public ResponseEntity<?> saveConsult(@RequestBody ConsultDetails request) {
        HashMap<String, Object> response = new HashMap<>();

        MaceReqConsult mrc = maceService.getMaceReqConsultByReferenceNo(request.getReferenceNo());
        if (null == mrc) {
            response.put("responseCode", 404);
            response.put("responseDesc", "Invalid MaceReqConsult ReferenceNo");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        MaceRequest mr = maceService.getMaceRequestByRequestId(mrc.getMaceRequestId());
        if (null == mr) {
            response.put("responseCode", 404);
            response.put("responseDesc", "Cannot retrieve MaceRequest.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        MemberDetails member = memService.getMember(request.getMemberCode());
        Hospital hospital = claimsService.getHospital(request.getHospitalCode());

        mrc.setHistoryOfPresentIllness(request.getHistoryOfPresentIllness());
        mrc.setPastOrFamilyHistory(request.getPastOrFamilyHistory());
        mrc.setReviewOfSystems(request.getReviewOfSystems());
        mrc.setVitalsBp(request.getVitalsBp());
        mrc.setVitalsHr(request.getVitalsHr());
        mrc.setVitalsRr(request.getVitalsRr());
        mrc.setVitalsTemp(request.getVitalsTemp());
        mrc.setPhysicalExamination(request.getPhysicalExamination());
        mrc.setDxRemarks(request.getDxRemarks());
        mrc.setCongenital(request.getIsCongenital());
        mrc.setMaternity(request.getIsMaternity());
        mrc.setMedicolegal(request.getIsMedicolegal());
        mrc.setPlanOfManagement(request.getPlanOfManagement());
        mrc.setStatus("AVAILED");
        mrc.setAvailedOn(new Date());

        Map<String, List<ConsultDetails.PrescribedTestJson>> groupedByDiagCode = request.getPrescribedTests().stream()
                .collect(Collectors.groupingBy(ConsultDetails.PrescribedTestJson::getDiagCode, Collectors.toList()));

        for (Map.Entry<String, List<ConsultDetails.PrescribedTestJson>> ptj : groupedByDiagCode.entrySet()) {
            //Save Diagnosis
            MaceRequestOpDiag mrod = new MaceRequestOpDiag();
            ConsultDetails.PrescribedTestJson ptjObj = ptj.getValue().get(0);
            String groupDiagCode = "";
            if (null != ptjObj.getDiagCode())
                groupDiagCode = ptjObj.getDiagCode();
            DiagnosisEntity de = maceService.getDiagnosisEntity(groupDiagCode);
            String requestCode = maceService.generateID("APPROVALNO");

            List<ConsultDetails.PrescribedTestJson> ptjList = ptj.getValue();
            for (ConsultDetails.PrescribedTestJson prescribedTestJson : ptjList) {

                if(ptjObj.getDiagType() == 1){
                    mrc.setPrimaryDiagnosisEntity(de);
                }

                mrod.setTransactionId(mrc.getTransactionId());
                mrod.setMaceRequestId(mr.getRequestId());
                mrod.setDiagnosisEntity(de);
                mrod.setMaceDiagType(ptjObj.getDiagType());
                mrod.setDiagRemarks(ptjObj.getDiagRemarks());
                maceService.saveMaceRequestOpDiag(mrod);

                //Create MaceConsPrescribedTest after saving the MaceRequestOpDiag entry
                MaceConsPrescribedtest mcpt = new MaceConsPrescribedtest();
                //Set required ID fields
                mcpt.setMacerequestId(Integer.parseInt(String.valueOf(mrod.getMaceRequestId())));
                mcpt.setReqdiagId(mrod.getReqDiagId());
                mcpt.setConsTransactionId(Integer.parseInt(String.valueOf(mrc.getTransactionId())));

                //Set other fields based on TestProcObject data
                mcpt.setStatus(0);
                TestProcObject tpo = claimsService.getTestProcObject(ptjObj.getProcCode());
                mcpt.setTestProcObject(tpo);

                maceService.saveMacePrescribedTest(mcpt);
            }
        }

        //todo Process ProcedureDiagnosis
        //todo Diagnosis/Other Diagnosis

        maceService.updateMaceReqConsult(mrc);
        response.put("maceRequest", mr);
        response.put("maceReqConsult", mrc);
        response.put("responseCode", 200);
        response.put("responseDesc", "Successfully Saved Consult Details.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}