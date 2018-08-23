package com.basicauth.controller;

import com.basicauth.config.Constants;
import com.basicauth.data.*;
import com.basicauth.domain.*;
import com.basicauth.service.*;
import com.basicauth.service.approval.ApprovalEngine;
import com.basicauth.service.approval.ApprovalEngineResult;
import com.basicauth.types.ConsultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.basicauth.config.Constants.DEFAULT_GROUP;

/**
 * Created by IPC_Laptop056 on 9/21/2017.
 */
@RestController
@RequestMapping(value = "/coordinator/v3")
public class CoordinatorVersion3Controller {

    private static final Logger logger = LoggerFactory.getLogger(CoordinatorVersion3Controller.class);
    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private MaceService maceService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private MemService memService;

    @Autowired
    private LoaService loaService;

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private ApprovalEngine approvalEngine;

    @RequestMapping(value = "/getRecentTransactionsForHosp", method = RequestMethod.GET)
    public ResponseEntity<?> getRecentTransactionsForHosp(@RequestParam String hospCode, @RequestParam(required = false, defaultValue = "") String memberCode) {

        List<RecentTransObj> mrList = maceService.getRecentTransactionsHospCode(hospCode, memberCode);
        Map<String, List<RecentTransObj>> groupedByStatus = mrList.stream()
                .collect(Collectors.groupingBy(RecentTransObj::getStatus, Collectors.toList()));

        return new ResponseEntity<>(groupedByStatus, HttpStatus.OK);
    }

    @RequestMapping(value = "/checkForDuplicateRequestConsult/", method = RequestMethod.POST)
    public ResponseEntity<?> checkForDuplicateRequestConsult(@RequestBody ConsultJson consultJson) {
        HashMap<String, Object> response = new HashMap<>();
        boolean hasDuplicate = maceService.checkForDuplicateConsult(consultJson);
        if (hasDuplicate) {
            response.put("responseCode", 210);
            response.put("responseDesc", "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?");
        } else {
            response.put("responseCode", 200);
            response.put("responseDesc", "Approved");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/checkForDuplicateRequestBasicTest/", method = RequestMethod.POST)
    public ResponseEntity<?> checkForDuplicateRequestBasicTest(@RequestBody BasicOrOtherTestRequestJson bootrj) {
        HashMap<String, Object> response = new HashMap<>();
        boolean hasDuplicate = maceService.checkForDuplicateBasicTest(bootrj);
        if (hasDuplicate) {
            response.put("responseCode", 210);
            response.put("responseDesc", "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?");
        } else {
            response.put("responseCode", 200);
            response.put("responseDesc", "Approved");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/checkForDuplicateRequestTestProc/", method = RequestMethod.POST)
    public ResponseEntity<?> checkForDuplicateRequestTestProc(@RequestBody BasicOrOtherTestRequestJson bootrj) {
        HashMap<String, Object> response = new HashMap<>();
        boolean hasDuplicate = bootrj.getServiceType() == 2 ? maceService.checkForDuplicateTest(bootrj) : maceService.checkForDuplicateProc(bootrj);
        if (hasDuplicate) {
            response.put("responseCode", 210);
            response.put("responseDesc", "There is an existing entry from the same hospital. Submitting this may cause duplicate request and may affect your existing utilization limits. Are you sure you want to submit this?");
        } else {
            response.put("responseCode", 200);
            response.put("responseDesc", "Approved");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/requestLOAConsult/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLOAConsult(@RequestBody ConsultJson consultJson) {
        String remarks = "CONSULTATION";
        consultJson.setSubmitForApproval(true);
        ResponseEntity<?> response = maceService.processConsultationV2(consultJson, 1, "COORDINATOR", remarks);
        HashMap<String, Object> responseBody = (HashMap) response.getBody();
        //Remove instead of get so that the MaceInsertObject won't be part of the final response
        if (responseBody.containsKey("maceInsertOrder")) {
            maceService.maceInsertProcessTests((MaceInsertOrder) responseBody.remove("maceInsertOrder"));
        }

        return response;

    }

    @RequestMapping(value = "/requestLOAMaternity/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLOAMaternity(@RequestBody ConsultJson consultJson) {
        String remarks = "MATERNITY CONSULTATION";
        consultJson.setSubmitForApproval(true);
        ResponseEntity<?> response = maceService.processConsultationV2(consultJson, 2, "COORDINATOR", remarks);
        HashMap<String, Object> responseBody = (HashMap) response.getBody();
        //Remove instead of get so that the MaceInsertObject won't be part of the final response
        if (responseBody.containsKey("maceInsertOrder")) {
            maceService.maceInsertProcessTests((MaceInsertOrder) responseBody.remove("maceInsertOrder"));
        }

        return response;
    }

    @RequestMapping(value = "/requestLoaForBasicTest", method = RequestMethod.POST)
    private ResponseEntity<?> requestLoaForBasicTest(@RequestBody BasicOrOtherTestRequestJson rbtJson) {
        HashMap<String, Object> response;

        response = maceService.processBasicTestRequestV2(rbtJson);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/requestBasicOrOtherTest", method = RequestMethod.POST)
    public ResponseEntity<?> requestBasicOrOtherTest(@RequestBody BasicOrOtherTestRequestJson bortj) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> validationErrors = validateBasicOrOtherTestRequestJson(bortj);

        if (validationErrors != null) {
            response.put("validationErrors", validationErrors);
            response.put("responseCode", 412);
            response.put("responseDesc", "The request body contains validation errors.");

            return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
        }

        //TODO: Get from cache and not the database
        Doctor doctor = claimsService.getDoctor(bortj.getDoctorCode(), false);
        if (doctor == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The doctor (code: %s) " +
                    "was not found in the database.", bortj.getDoctorCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //TODO: Get from cache and not the database
        Hospital hospital = claimsService.getHospital(bortj.getHospitalCode());
        if (hospital == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The hospital (code: %s) " +
                    "was not found in the database.", bortj.getHospitalCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        MemberDetails memberDetails = memService.getMember(bortj.getMemberCode());
        if (memberDetails == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The member (code: %s) " +
                    "was not found in the database.", bortj.getMemberCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //TODO: Get from cache and not the database
        Diagnosis primaryDiagnosis = claimsService.getDiagnosisByDiagnosisCode(bortj.getPrimaryDiagnosisCode());
        if (primaryDiagnosis == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The primary diagnosis (code: %s) " +
                    "was not found in the database.", bortj.getPrimaryDiagnosisCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //TODO: Get from cache and not the database
        DoctorToHospital requestingDocHosp = claimsService.getDoctorToHospitalObject(bortj.getRequestingHospCode(), bortj.getDoctorCode(), false);
        //Mapping of Doctor to Hospital is not yet strict, this may be null

        String finalStatus = "APPROVED";
        String finalDesc = "";
        String finalAssignee = Constants.DEFAULT_GROUP;

        String responseCode = "";
        String responseDesc = "";
        String remarks = "";

        /**
         * Get the Collated cost of all tests/procedures of request
         * */
        BigDecimal collatedCost = BigDecimal.ZERO;
        for (BasicOrOtherTestRequestJson.DiagnosisProcedureJson dpj : bortj.getDiagnosisProcedures()) {
            collatedCost = collatedCost.add(null == dpj.getAmount() || dpj.getAmount() == 0 ? claimsService.getCostOfTestProc(dpj.getProcedureCode()) : BigDecimal.valueOf(dpj.getAmount()));
        }
        System.out.println("COLLATED " + String.valueOf(collatedCost));
        //Initialize CustomerCare
        CustomerCare c = maceService.initCustomerCare(memberDetails, hospital.getHospitalCode(),
                bortj.getDoctorCode(), bortj.getPrimaryDiagnosisCode(), bortj.getDiagnosisProcedures()[0].getProcedureCode(),
                collatedCost, "", bortj.getAppUsername(),
                "",
                memberDetails.getPRIN_CODE(),
                primaryDiagnosis.getDiagDesc(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                bortj.getPrimaryComplaint(),
                bortj.getRequestBy());

        //Initialize header request data. Overriding of status and status assignee will be done after V2 Approval Engine executions
        HashMap<String, List<String>> reasonMessagesMap = new HashMap<>();
        LocalDate birthday = LocalDate.parse(memberDetails.getBDAY(), DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        Period period = Period.between(birthday, LocalDate.now());

        MaceRequest mr = new MaceRequest();
        mr.setServiceTypeId(2);
        mr.setMemCode(bortj.getMemberCode());
        mr.setMemLname(memberDetails.getMEM_LNAME());
        mr.setMemFname(memberDetails.getMEM_FNAME());
        mr.setMemMi("");
        mr.setMemCompany(memberDetails.getACCOUNT_NAME());
        mr.setMemAcct(memberDetails.getACCOUNT_CODE());
        mr.setMemStat(memberDetails.getMem_OStat_Code());
        mr.setMemGender(memberDetails.getMEM_SEX() == 1 ? "MALE" : memberDetails.getMEM_SEX() == 0 ? "FEMALE" : "UNKNOWN");
        mr.setMemAge(period.getYears());
        mr.setMemType(memberDetails.getACCT_TYPE());
        mr.setIdremarks(memberDetails.getID_REM());
        mr.setHospContact(bortj.getCoorContact());
        mr.setHospEmail(bortj.getCoorEmail());
        try {
            mr.setMemBdate(new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getBDAY()));
            mr.setAcctValidity(new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getVAL_DATE()));
            mr.setAcctEffectivity(new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getEFF_DATE()));
        } catch (Exception e) {
        }
        mr.setRequestOrigin(bortj.getRequestOrigin());
        mr.setRequestFrommem(bortj.getMemberCode());
        mr.setRequestFromhosp(bortj.getHospitalCode());
        mr.setRequestByCode(bortj.getHospitalCode());
        mr.setRequestBy(bortj.getRequestBy());
        mr.setRequestDevice(bortj.getRequestDevice());
        mr.setRequestDatetime(new Date());
        mr.setDisclaimerTicked(bortj.getDisclaimerTicked());
        mr.setLastupdateOn(new Date());
        mr.setLastupdateBy(bortj.getRequestBy());
        mr.setOverride(false);
        mr.setParRequestId(0);
        mr.setMbasCode(null);
        mr.setMbasApprover(null);
        mr.setMbasupdateOn(null);
        //Save MaceRequest Audit Log
        MaceRequestAudit maceRequestAudit = new MaceRequestAudit();
        maceRequestAudit.setLogDateTime(new Date());
        maceRequestAudit.setHostname("");
        maceRequestAudit.setUserId(bortj.getRequestBy());
        maceRequestAudit.setDeviceId(bortj.getRequestDevice());
        maceRequestAudit.setUserType(bortj.getRequestOrigin());
        maceRequestAudit.setFacility("");
        maceRequestAudit.setFunctionName("requestBasicOrOtherTest");
        maceRequestAudit.setDetail("Created MaceRequest for requestBasicOrOtherTest.");
        maceRequestAudit.setTranstype("");
        maceRequestAudit.setMemberId(bortj.getMemberCode());
        maceRequestAudit.setOldValue("");
        maceRequestAudit.setNewValue("");
        maceService.saveMaceRequestAuditLog(mr, maceRequestAudit);

        //Loop through the list of DiagnosisProcedureJson and process MaceReqTest or MaceReqProc
        List<BasicOrOtherTestResponseJson.DiagnosisProcedureJson> diagProcJson = new ArrayList<>();

        //Flag for all test approved
        boolean allApproved = true;

        for (BasicOrOtherTestRequestJson.DiagnosisProcedureJson diagnosisProcedureJson : bortj.getDiagnosisProcedures()) {
            TestProcObject tpo = claimsService.getTestProcObject(diagnosisProcedureJson.getProcedureCode());
            diagnosisProcedureJson.setDiagType(diagnosisProcedureJson.getDiagnosisCode().equals(primaryDiagnosis.getDiagCode()) ? 1 : 2);
            diagnosisProcedureJson.setCostCenter(tpo.getCostCenter());
        }
        //Group by Diagnosis
        /** ===================================
         *  Major revision in insertion process
         *  ===================================*/
        MaceRequestReturn mrr = new MaceRequestReturn(mr);
        mrr.setServiceType("OTHER TEST");
        //MaceInsertOrder holds all Mace Objects for an ordered insertion later on for arranged dependencies(PK/FK).
        MaceInsertOrder mio = new MaceInsertOrder();
        BigDecimal totalAmount = BigDecimal.ZERO;
        /**
         * Group by Cost Center First.
         * */
        Map<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> byCostCenter = Arrays.stream(bortj.getDiagnosisProcedures())
                .collect(Collectors.groupingBy(BasicOrOtherTestRequestJson.DiagnosisProcedureJson::getCostCenter, Collectors.toList()));
        List<MaceRequestTestObj> mrtObjs = new ArrayList<>();
        List<MaceRequestReturn.GroupedByCostCenter> gbccArrayList = new ArrayList<>();
        //Iterate through the list grouped by CostCenter and process and further group.
        //Adding CostCenter(MACEREQ_TEST) insert inside diagnosis iteration to separate ApprovalNumbers for different diagnoses
        for (Map.Entry<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> perCostCenter : byCostCenter.entrySet()) {
            MaceRequestReturn.GroupedByCostCenter gbcc = new MaceRequestReturn.GroupedByCostCenter();
            gbcc.setCostCenter(perCostCenter.getKey());
            /**
             * Group by DiagType within Cost Center.
             * */
            Map<Integer, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> byDiagType = perCostCenter.getValue().stream()
                    .collect(Collectors.groupingBy(BasicOrOtherTestRequestJson.DiagnosisProcedureJson::getDiagType, Collectors.toList()));
            for (Map.Entry<Integer, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> perDiagtype : byDiagType.entrySet()) {
                /**
                 * Group futher by DiagCode. Generate ApprovalNo per Diagnosis
                 * */
                Map<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> byDiagCode = perDiagtype.getValue().stream()
                        .collect(Collectors.groupingBy(BasicOrOtherTestRequestJson.DiagnosisProcedureJson::getDiagnosisCode, Collectors.toList()));

                List<MaceRequestTestObj.MaceRequestOpDiagObj> mrodObjs = new ArrayList<>();
                String costCenterStatus = "APPROVED";
                MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[] gbdList = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag[byDiagCode.size()];
                int x = 0;
                for (Map.Entry<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> perDiagCode : byDiagCode.entrySet()) {
                    MaceRequestTestObj mrtObj = new MaceRequestTestObj();
                    MaceRequestReturn.GroupedByCostCenter.GroupedByDiag gbd = new MaceRequestReturn.GroupedByCostCenter.GroupedByDiag();
                    gbd.setDiagType(perDiagtype.getKey());
                    //Generate ApprovalNo for each Diagnosis
                    String requestCode = maceService.generateID("APPROVALNO");
                    gbd.setApprovalNo(requestCode);
                    mrr.setApprovalNo(byCostCenter.size() == 1 ? requestCode : "Multiple Approvals");
                    BigDecimal subTotal = BigDecimal.ZERO;
                    MaceRequestTest mrt = new MaceRequestTest();
                    mrt.setApprovalNo(requestCode);
                    mrt.setConsultReason(bortj.getPrimaryComplaint());
                    mrt.setDoctorCode(bortj.getDoctorCode());
                    mrt.setHospitalCode(bortj.getHospitalCode());
                    mrt.setDocHospId(null == requestingDocHosp ? 0 : Long.parseLong(String.valueOf(requestingDocHosp.getDocHospId())));
                    DiagnosisEntity de = maceService.getDiagnosisEntity(bortj.getPrimaryDiagnosisCode());
                    if (null != de) {
                        mrt.setPrimaryDiagnosisCode(de.getDiagCode());
                        mrt.setPrimaryDiagnosisICD10(de.getIcd10Code());
                        mrt.setPrimaryDiagnosisDesc(de.getDiagDesc());
                        mrt.setDxRemarks(de.getDiagRemarks());
                    }
                    mrt.setAvailHospId(hospital.getHospitalCode());
                    //Generate transCode and map reason messages to this transCode
                    String transCode = maceService.generateID("TESTREQUEST");
                    mrt.setTransCode(transCode);

                    List<String> reasonsList = new ArrayList<>();

                    List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson> currentList = perDiagCode.getValue();
                    if (byCostCenter.size() == 1 && byDiagType.size() == 1) {
                        mr.setApprovalNo(requestCode);
                        mrr.setApprovalNo(requestCode);
                    }

                    DiagnosisEntity groupDiag = maceService.getDiagnosisEntity(currentList.get(0).getDiagnosisCode());
                    MaceRequestTestObj.MaceRequestOpDiagObj mrodObj = new MaceRequestTestObj.MaceRequestOpDiagObj();

                    MaceRequestOpDiag mrod = new MaceRequestOpDiag();
                    mrod.setDiagnosisEntity(maceService.getDiagnosisEntity(groupDiag.getDiagCode()));
                    gbd.setDiagType(primaryDiagnosis.getDiagCode().equals(groupDiag.getDiagCode()) ? 1 : 2);
                    gbd.setDiagDesc(mrod.getDiagDesc());
                    mrod.setMaceDiagType(groupDiag.getDiagCode().equals(primaryDiagnosis.getDiagCode()) ? 1 : 2);
                    mrod.setTransCode(transCode);
                    List<MaceRequestOpTest> mrots = new ArrayList<>();
                    MaceRequestReturn.MappedTest[] mtList = new MaceRequestReturn.MappedTest[currentList.size()];
                    int y = 0;
                    /**
                     * Iterate through each test inside Diagnosis
                     * */
                    for (BasicOrOtherTestRequestJson.DiagnosisProcedureJson dpj : currentList) {
                        MaceRequestReturn.MappedTest mt = new MaceRequestReturn.MappedTest();
                        TestProcObject testProcObject = claimsService.getTestProcObject(dpj.getProcedureCode());
                        HospitalProcedureAmountView hpav = claimsService.getHospProcAmount(hospital.getHospitalCode(), testProcObject.getProcCode());
                        DiagnosticProceduresEntity dpe = maceService.getDiagProcedureByProcedureCode(testProcObject.getProcCode(), groupDiag.getDiagCode());
                        Diagnosis diag = claimsService.getDiagnosisByDiagnosisCode(groupDiag.getDiagCode());

                        MaceRequestOpTest mrot = new MaceRequestOpTest();
                        mrot.setProcHospAmount(null != hpav ? hpav.getHospAmount() : null);
                        mrot.setProcDefAmount(testProcObject != null ? testProcObject.getAmount() : null);
                        mrot.setProcActualAmount(null != dpj.getAmount() ? BigDecimal.valueOf(dpj.getAmount()) : null);
                        mrot.setDiagCode(groupDiag.getDiagCode());
                        mrot.setProcCode(testProcObject.getProcCode());
                        mrot.setProcDesc(testProcObject.getProcName());
                        testProcObject.setServiceType(2);
                        testProcObject.setSubType(4);
                        mrot.setMaceSubtype(4);
                        mrot.setStatus(0);
                        mrot.setTransCode(transCode);
                        BigDecimal procAmount = mrot.getProcActualAmount() != null && !mrot.getProcActualAmount().equals(BigDecimal.ZERO) ? mrot.getProcActualAmount() :
                                mrot.getProcHospAmount() != null && !mrot.getProcHospAmount().equals(BigDecimal.ZERO) ? mrot.getProcHospAmount() :
                                        mrot.getProcDefAmount() != null && !mrot.getProcDefAmount().equals(BigDecimal.ZERO) ? mrot.getProcDefAmount() : BigDecimal.ZERO;
                        //For Return
                        mt.setAmount(procAmount);
                        mt.setCostCenter(perCostCenter.getKey());
                        mt.setProcCode(testProcObject.getProcCode());
                        mt.setProcDesc(testProcObject.getProcName());
                        mt.setDiagType(perDiagtype.getKey());
                        //Execute V2 approval engine and process results per execution
                        ApprovalEngineResult approvalEngineResult = approvalEngine.executeApprovalEngine(memberDetails,
                                diag, hospital, doctor, testProcObject, Double.valueOf(String.valueOf(collatedCost)));
                        //Set allApproved to false if V2 Approval Engine does not return a request status of "APPROVED"
                        if (!approvalEngineResult.getFinalRequestStatus().equals(Constants.REQUEST_AUTOMATIC_APPROVED)) {
                            allApproved = false;
                            costCenterStatus = "PENDING";
                            reasonsList.addAll(approvalEngineResult.getReasonMessages());
                            //Update final status assignee
                            finalAssignee = approvalEngine.updateFinalStatusAssignee(finalAssignee, approvalEngineResult.getFinalStatusAssignee(), hospital);
                            mrodObj.setMraObjs(approvalEngineResult.getMaceRequestApprovals());
                        }
                        subTotal = subTotal.add(procAmount);
                        mrots.add(mrot);
                        BasicOrOtherTestResponseJson.DiagnosisProcedureJson dpjObj = new BasicOrOtherTestResponseJson.DiagnosisProcedureJson();
                        dpjObj.setMaceRequestTest(mrt);
                        dpjObj.setMaceRequestOpDiag(mrod);
                        dpjObj.setMaceRequestOpTest(mrot);
                        diagProcJson.add(dpjObj);
                        mtList[y++] = mt;
                    }
                    mrodObj.setMrod(mrod);
                    mrodObj.setMrots(mrots);
                    mrodObjs.add(mrodObj);
                    mrtObj.setMrodObjs(mrodObj);
                    System.out.println("REASONS : ");
                    for (String s : reasonsList) {
                        System.out.println(s);
                    }
                    reasonMessagesMap.put(transCode, reasonsList);
                    mrt.setTransamount(subTotal);
                    mrt.setStatus(costCenterStatus);
                    mrt.setTestSubtype(4);
                    totalAmount = totalAmount.add(subTotal);
                    mrtObj.setMrt(mrt);
                    mrtObjs.add(mrtObj);
                    gbd.setMappedTests(mtList);
                    gbcc.setSubTotal(subTotal);
                    gbdList[x++] = gbd;
                }
                gbcc.setGroupedByDiag(gbdList);
                gbcc.setStatus(costCenterStatus);
                gbccArrayList.add(gbcc);
            }
        }
        MaceRequestReturn.GroupedByCostCenter[] gbccList = new MaceRequestReturn.GroupedByCostCenter[gbccArrayList.size()];
        for (int i = 0; i < gbccArrayList.size(); i++) {
            gbccList[i] = gbccArrayList.get(i);
        }
        mrr.setGroupedByCostCenters(gbccList);
        mrr.setTotalAmount(totalAmount);
        mr.setStatus(allApproved ? "APPROVED" : "PENDING");
        mr.setStatusAssignee(!finalAssignee.equals(DEFAULT_GROUP) ? finalAssignee : null);
        mio.setMrtObjs(mrtObjs);
        mio.setMr(mr);
        maceService.maceInsertProcessTests(mio);

        /** ============
         *  End Revision
         *  ============*/

        BasicOrOtherTestResponseJson bootrj = new BasicOrOtherTestResponseJson();
        //Set Doctor for return only
        String fullName = doctor.getDocLname() == null ? "" : doctor.getDocLname() + ", ";
        fullName += doctor.getDocFname() == null ? "" : doctor.getDocFname() + " ";
        fullName += doctor.getDocMname() == null ? "" : doctor.getDocMname() + ".";
        mr.setDoctorName(fullName);
        mrr.setDoctorName(fullName);
        mrr.setDoctorSpec(doctor.getSpecDesc());
        bootrj.setMaceRequest(mr);
        bootrj.setDiagnosisProcedures(diagProcJson.toArray(
                new BasicOrOtherTestResponseJson.DiagnosisProcedureJson[diagProcJson.size()]));
        bootrj.setDiagnosisClinicProcedures(new BasicOrOtherTestResponseJson.DiagnosisClinicProcedureJson[]{});

        //Remarks to be returned to app should be the final status of the header request
        remarks = mr.getStatus();
        responseCode = "200";

        //Response and notes will be the list of transCodes
        String transCodeString = String.join(", ", reasonMessagesMap.entrySet().stream().map(entry -> entry.getKey())
                .collect(Collectors.toList()));
        responseDesc = transCodeString;

        //Save customer care data
        c.setType(2);
        c.setActionTaken(allApproved ? 1 : 0);
        c.setRemarks("Tests - Please call 841-8080 for approval");
        c.setNotes(transCodeString);
        c.setBatchCode(maceService.generateID("BATCHNO"));
        c.setCallerId(maceService.generateID("CALLERID"));

        if (!allApproved) {
            maceService.saveTransactionForCall(c);
            customerServiceService.saveTransactionForCall(c);
        }

        //Set response data
        response.put("icd10Code", primaryDiagnosis.getIcd10Code());
        response.put("totalAmount", totalAmount);
        response.put("data", bootrj);
        response.put("loaList", mrr);
        response.put("approvalNo", mr.getApprovalNo());
        response.put("allApproved", allApproved);
        response.put("responseCode", responseCode);
        response.put("reasonMessagesMap", reasonMessagesMap);
        response.put("responseDesc", responseDesc);
        response.put("remarks", remarks);
        response.put("status", finalStatus);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private HashMap<String, Object> validateBasicOrOtherTestRequestJson(BasicOrOtherTestRequestJson bortj) {
        HashMap<String, Object> validationErrors = new HashMap<>();

        // TODO: validate json

        return validationErrors.size() > 0 ? validationErrors : null;
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/requestLoaForProcedures/", method = RequestMethod.POST)
    public ResponseEntity<?> requestLoaForProcedures(@RequestBody BasicOrOtherTestRequestJson bortj) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> validationErrors = validateBasicOrOtherTestRequestJson(bortj);

        if (validationErrors != null) {
            response.put("validationErrors", validationErrors);
            response.put("responseCode", 412);
            response.put("responseDesc", "The request body contains validation errors.");

            return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
        }

        //TODO: Get from cache and not the database
        Doctor doctor = claimsService.getDoctor(bortj.getDoctorCode(), false);
        if (doctor == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The doctor (code: %s) " +
                    "was not found in the database.", bortj.getDoctorCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //TODO: Get from cache and not the database
        Hospital hospital = claimsService.getHospital(bortj.getHospitalCode());
        if (hospital == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The hospital (code: %s) " +
                    "was not found in the database.", bortj.getHospitalCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        MemberDetails memberDetails = memService.getMember(bortj.getMemberCode());
        if (memberDetails == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The member (code: %s) " +
                    "was not found in the database.", bortj.getMemberCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //TODO: Get from cache and not the database
        Diagnosis primaryDiagnosis = claimsService.getDiagnosisByDiagnosisCode(bortj.getPrimaryDiagnosisCode());
        if (primaryDiagnosis == null) {
            response.put("responseCode", 404);
            response.put("responseDesc", String.format("The primary diagnosis (code: %s) " +
                    "was not found in the database.", bortj.getPrimaryDiagnosisCode()));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        //TODO: Get from cache and not the database
        DoctorToHospital requestingDocHosp = claimsService.getDoctorToHospitalObject(bortj.getRequestingHospCode(), bortj.getDoctorCode(), false);
        //Mapping of Doctor to Hospital is not yet strict, this may be null

        String finalStatus = "APPROVED";
        String finalDesc = "";
        String finalAssignee = Constants.DEFAULT_GROUP;


        String responseCode = "";
        String responseDesc = "";
        String remarks = "";

        /**
         * Get the Collated cost of all tests/procedures of request
         * */
        BigDecimal collatedCost = BigDecimal.ZERO;
        for (BasicOrOtherTestRequestJson.DiagnosisProcedureJson dpj : bortj.getDiagnosisProcedures()) {
            collatedCost = collatedCost.add(null == dpj.getAmount() || dpj.getAmount() == 0 ? claimsService.getCostOfTestProc(dpj.getProcedureCode()) : BigDecimal.valueOf(dpj.getAmount()));
        }

        //Initialize CustomerCare
        CustomerCare c = maceService.initCustomerCare(memberDetails, hospital.getHospitalCode(),
                bortj.getDoctorCode(), bortj.getPrimaryDiagnosisCode(), bortj.getDiagnosisProcedures()[0].getProcedureCode(),
                collatedCost, "", bortj.getAppUsername(),
                "",
                memberDetails.getPRIN_CODE(),
                primaryDiagnosis.getDiagDesc(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                bortj.getPrimaryComplaint(),
                bortj.getRequestBy());

        //Initialize header request data. Overriding of status and status assignee will be done after V2 Approval Engine executions
        HashMap<String, List<String>> reasonMessagesMap = new HashMap<>();
        LocalDate birthday = LocalDate.parse(memberDetails.getBDAY(), DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        Period period = Period.between(birthday, LocalDate.now());

        MaceInsertOrder mio = new MaceInsertOrder();
        MaceRequest mr = new MaceRequest();
        mr.setServiceTypeId(3);
        mr.setMemCode(bortj.getMemberCode());
        mr.setMemLname(memberDetails.getMEM_LNAME());
        mr.setMemFname(memberDetails.getMEM_FNAME());
        mr.setMemMi("");
        mr.setStatus(finalStatus);
        mr.setMemCompany(memberDetails.getACCOUNT_NAME());
        mr.setMemAcct(memberDetails.getACCOUNT_CODE());
        mr.setMemStat(memberDetails.getMem_OStat_Code());
        mr.setMemGender(memberDetails.getMEM_SEX() == 1 ? "MALE" : memberDetails.getMEM_SEX() == 0 ? "FEMALE" : "UNKNOWN");
        mr.setMemAge(period.getYears());
        mr.setMemType(memberDetails.getACCT_TYPE());
        mr.setIdremarks(memberDetails.getID_REM());
        mr.setHospContact(bortj.getCoorContact());
        mr.setHospEmail(bortj.getCoorEmail());
        try {
            mr.setMemBdate(Date.from(birthday.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            mr.setAcctValidity(new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getVAL_DATE()));
            mr.setAcctEffectivity(new SimpleDateFormat("MMM dd, yyyy").parse(memberDetails.getEFF_DATE()));
        } catch (Exception e) {
        }
        mr.setRequestOrigin(bortj.getRequestOrigin());
        mr.setRequestFrommem(bortj.getMemberCode());
        mr.setRequestFromhosp(bortj.getHospitalCode());
        mr.setRequestBy(bortj.getRequestBy());
        mr.setRequestDevice(bortj.getRequestDevice());
        mr.setRequestDatetime(new Date());
        mr.setDisclaimerTicked(bortj.getDisclaimerTicked());
        mr.setLastupdateOn(new Date());
        mr.setLastupdateBy(bortj.getRequestBy());
        mr.setOverride(false);
        mr.setParRequestId(0);
        mr.setMbasCode(null);
        mr.setMbasApprover(null);
        mr.setMbasupdateOn(null);
        //Save MaceRequest Audit Log
        MaceRequestAudit maceRequestAudit = new MaceRequestAudit();
        maceRequestAudit.setLogDateTime(new Date());
        maceRequestAudit.setHostname("");
        maceRequestAudit.setUserId(bortj.getRequestBy());
        maceRequestAudit.setDeviceId(bortj.getRequestDevice());
        maceRequestAudit.setUserType(bortj.getRequestOrigin());
        maceRequestAudit.setFacility("");
        maceRequestAudit.setFunctionName("requestLoaForProcedures");
        maceRequestAudit.setDetail("Created MaceRequest for requestLoaForProcedures.");
        maceRequestAudit.setTranstype("");
        maceRequestAudit.setMemberId(bortj.getMemberCode());
        maceRequestAudit.setOldValue("");
        maceRequestAudit.setNewValue("");
        maceService.saveMaceRequestAuditLog(mr, maceRequestAudit);

        //Loop through the list of DiagnosisProcedureJson and process MaceReqTest or MaceReqProc
        List<BasicOrOtherTestResponseJson.DiagnosisClinicProcedureJson> diagClinicProcJson = new ArrayList<>();

        //Flag for all test approved
        boolean allApproved = true;
        //Group by diagnosis
        /**
         * Pointless as Procedures Only Have One Diagnosis.
         * */
        Map<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> dpjGroupedByDiag = Arrays.stream(bortj.getDiagnosisProcedures())
                .collect(Collectors.groupingBy(BasicOrOtherTestRequestJson.DiagnosisProcedureJson::getDiagnosisCode, Collectors.toList()));

        DiagnosisEntity de = new DiagnosisEntity();
        BigDecimal total = BigDecimal.ZERO;
        List<MaceRequestProcObj> mrpObjs = new ArrayList<>();
        String requestCode = "";
        for (Map.Entry<String, List<BasicOrOtherTestRequestJson.DiagnosisProcedureJson>> groupedDpj : dpjGroupedByDiag.entrySet()) {
            Diagnosis groupDiag = claimsService.getDiagnosisByDiagnosisCode(groupedDpj.getValue().get(0).getDiagnosisCode());
            MaceRequestProcObj mrpObj = new MaceRequestProcObj();
            //Process Procedures
            List<TestProcObject> procObjects = new ArrayList<>();
            for (BasicOrOtherTestRequestJson.DiagnosisProcedureJson procs : groupedDpj.getValue()) {
                TestProcObject tpo = claimsService.getTestProcObject(procs.getProcedureCode());
                if (tpo != null) {
                    //Set service type to 3 (Procedures)
                    tpo.setServiceType(3);
                    //Set procedures subtype = 0(Procedures has no subtype)
                    tpo.setSubType(0);
                    //If amount passed from DiagProcJson for this specific procedure is not null, override value of testProc
                    if (procs.getAmount() != null) {
                        tpo.setAmount(BigDecimal.valueOf(procs.getAmount()));
                    }
                    procObjects.add(tpo);
                }
            }
            requestCode = maceService.generateID("APPROVALNO");
            MaceRequestProcedure mrp = new MaceRequestProcedure();
            mrp.setApprovalNo(requestCode);
            mrp.setConsultReason(bortj.getPrimaryComplaint());
            mrp.setDoctorCode(bortj.getDoctorCode());
            mrp.setHospitalCode(bortj.getHospitalCode());
            //If doctorToHospitalObject is null(Doctor not mapped to Hospital), set docHospId value to 0
            mrp.setDocHospId(null == requestingDocHosp ? 0 : Long.parseLong(String.valueOf(requestingDocHosp.getDocHospId())));
            mrp.setPrimaryDiagnosisCode(groupDiag.getDiagCode());
            mrp.setPrimaryDiagnosisDesc(groupDiag.getDiagDesc());
            mrp.setPrimaryDiagnosisICD10(groupDiag.getIcd10Code());
            mrp.setDxRemarks(groupDiag.getDiagRemarks());
            mrp.setAvailHospId(hospital.getHospitalCode());
            mrp.setNotes(bortj.getRemarks());
            //Generate transCode and map reason messages to this transCode
            String transCode = maceService.generateID("PROCEDUREREQUEST");
            mrp.setTransCode(transCode);
            //Procedures has no equivalent MaceReqSubtype
            mrp.setTestSubtype(0);

            List<MaceRequestProcObj.MaceRequestOpDiagObj> mrodObjs = new ArrayList<>();
            MaceRequestOpDiag mrod = new MaceRequestOpDiag();
            de = maceService.getDiagnosisEntity(groupDiag.getDiagCode());
            mrod.setDiagnosisEntity(de);
            mrod.setTransCode(transCode);
            //MaceDiagnosisType 1 is the Primary Diagnosis, 2 is Other Diagnosis
            mrod.setMaceDiagType(groupDiag.getDiagCode().equals(primaryDiagnosis.getDiagCode()) ? 1 : 2);
            List<String> reasonsList = new ArrayList<>();

            //Group Procedures By Cost Center
            Map<String, List<TestProcObject>> tpoGroupedByCostCenter = procObjects.stream()
                    .collect(Collectors.groupingBy(TestProcObject::getCostCenter, Collectors.toList()));
            List<MaceRequestOpProcedure> mrops = new ArrayList<>();
            //Process Approvals for Procedures
            for (Map.Entry<String, List<TestProcObject>> groupedTpo : tpoGroupedByCostCenter.entrySet()) {
                BigDecimal subTotal = BigDecimal.ZERO;
                for (TestProcObject testProcObject : groupedTpo.getValue()) {
                    //Try to get matching of DiagnosisClinicEntity by DiagCode and ProcCode
                    DiagnosisClinicProceduresEntity dcpe = claimsService.getDiagnosisClinicProcedureEntity(groupDiag.getDiagCode(), testProcObject.getProcCode());
                    //Checks if there is a Matching of DiagnosisClinicProcedure


                    //Execute V2 approval engine and process results per execution
                    ApprovalEngineResult approvalEngineResult = approvalEngine.executeApprovalEngine(memberDetails,
                            groupDiag, hospital, doctor, testProcObject, testProcObject.getAmount().doubleValue());

                    //Set allApproved to false if V2 Approval Engine does not return a request status of "APPROVED"
                    if (!approvalEngineResult.getFinalRequestStatus().equals(Constants.REQUEST_AUTOMATIC_APPROVED)) {
                        allApproved = false;
                        mrpObj.setOrAppendMraObjs(approvalEngineResult.getMaceRequestApprovals());
                    }
                    reasonsList.addAll(approvalEngineResult.getReasonMessages());

                    //Save MaceReqProc with generated transCode
                    mrp.setTransCode(transCode);

                    //Update final status assignee
                    finalAssignee = approvalEngine.updateFinalStatusAssignee(finalAssignee, approvalEngineResult.getFinalStatusAssignee(), hospital);

                    TestProcObject proc = claimsService.getTestProcObject(testProcObject.getProcCode());
                    MaceRequestOpProcedure mrop = new MaceRequestOpProcedure();
                    mrop.setProcActualAmount(testProcObject.getAmount());
                    subTotal = subTotal.add(mrop.getProcActualAmount());
                    mrop.setProcDefAmount(proc.getAmount());
                    mrop.setDiagCode(groupDiag.getDiagCode());
                    mrop.setProcCode(testProcObject.getProcCode());
                    mrop.setProcDesc(testProcObject.getProcName());
                    mrop.setProcClass(""); //TODO
                    mrop.setRuv(testProcObject.getRuv());
                    mrop.setArea(testProcObject.getArea());
                    //If diagnosis is not mapped to procedure, auto 0(PENDING), else if approvalType = "auto", 1(REQUESTED), else 0(PENDING)
                    mrop.setStatus(0);
                    //Populate return Json Object
                    BasicOrOtherTestResponseJson.DiagnosisClinicProcedureJson dcpj = new BasicOrOtherTestResponseJson.DiagnosisClinicProcedureJson();
                    dcpj.setMaceRequestProcedure(mrp);
                    dcpj.setMaceRequestOpDiag(mrod);
                    dcpj.setMaceRequestOpProcedure(mrop);
                    diagClinicProcJson.add(dcpj);
                    mrops.add(mrop);
                }
                total = total.add(subTotal);
            }
            reasonMessagesMap.put(transCode, reasonsList);
            mrp.setTransamount(total);
            mrp.setStatus(allApproved ? "APPROVED" : "PENDING");
            MaceRequestProcObj.MaceRequestOpDiagObj mrodObj = new MaceRequestProcObj.MaceRequestOpDiagObj();
            mrodObj.setMrod(mrod);
            mrodObj.setMrops(mrops);
            mrpObj.setMrp(mrp);
            mrpObj.setMrodObjs(mrodObj);
            mrpObjs.add(mrpObj);
        }

        //Flag for allApproved for all tests and procedures, if all tests/procedures were approved, set MaceRequest status to "APPROVED"
        if (allApproved && finalStatus.equals("APPROVED")) {
            mr.setStatus("APPROVED");
        } else {
            //If a test/procedure become pending, set header MaceRequest status to "PENDING"
            mr.setStatus("PENDING");
            mr.setStatusAssignee(finalAssignee);
            mr.setStatusRemarks(finalDesc.equals("") ? "Automatic Pending." : finalDesc);
        }
        mio.setMr(mr);
        mio.setMrpObjs(mrpObjs);
        maceService.maceInsertProcessTests(mio);

        BasicOrOtherTestResponseJson bootrj = new BasicOrOtherTestResponseJson();
        //Set Doctor for return only
        String fullName = doctor.getDocLname() == null ? "" : doctor.getDocLname() + ", ";
        fullName += doctor.getDocFname() == null ? "" : doctor.getDocFname() + " ";
        fullName += doctor.getDocMname() == null ? "" : doctor.getDocMname() + ".";
        mr.setDoctorName(fullName);
        bootrj.setMaceRequest(mr);
        bootrj.setTotalAmount(total);
        bootrj.setIcd10Code(de.getIcd10Code());
        bootrj.setDiagnosisProcedures(new BasicOrOtherTestResponseJson.DiagnosisProcedureJson[]{});
        bootrj.setDiagnosisClinicProcedures(diagClinicProcJson.toArray(
                new BasicOrOtherTestResponseJson.DiagnosisClinicProcedureJson[diagClinicProcJson.size()]));

        //Remarks to be returned to app should be the final status of the header request
        remarks = mr.getStatus();
        responseCode = "200";

        //Response and notes will be the list of transCodes
        String transCodeString = String.join(", ", reasonMessagesMap.entrySet().stream().map(entry -> entry.getKey())
                .collect(Collectors.toList()));
        responseDesc = transCodeString;

        //Save customer care data
        c.setType(2);
        c.setActionTaken(allApproved ? 1 : 0);
        c.setRemarks("Procedures - Please call 841-8080 for approval");
        c.setNotes(transCodeString);
        c.setBatchCode(maceService.generateID("BATCHNO"));
        c.setCallerId(maceService.generateID("CALLERID"));

        if (!allApproved) {
            maceService.saveTransactionForCall(c);
            customerServiceService.saveTransactionForCall(c);
        }

        //Set response data
        response.put("data", bootrj);
        response.put("icd10Code", bootrj.getIcd10Code());
        response.put("totalAmount", total);
        response.put("approvalNo", requestCode);
        response.put("allApproved", allApproved);
        response.put("responseCode", responseCode);
        response.put("reasonMessagesMap", reasonMessagesMap);
        response.put("responseDesc", responseDesc);
        response.put("remarks", remarks);
        response.put("status", finalStatus);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/requestForDischargeIP", method = RequestMethod.POST)
    public ResponseEntity<?> requestForDischargeIP(@RequestParam("maceRequestId") int maceRequestId) {
        HashMap<String, Object> response = new HashMap<>();

        maceService.requestForDischargeIP(maceRequestId);
        response.put("responseCode", 200);
        response.put("reseponseDesc", "Successfully requested discharge.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/updateInpatient", method = RequestMethod.POST)
    private ResponseEntity<?> updateInpatient(@RequestBody MaceInpatientJson mij) {
        HashMap<String, Object> response = new HashMap<>();
        String auditRemarks = "";

        MaceRequest mr = maceService.getMaceRequestByRequestId(mij.getMaceRequestId());
        if (null == mr) {
            response.put("responseCode", HttpStatus.NOT_FOUND);
            response.put("responseDesc", "MaceRequestID Not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if (!mr.getHospContact().equals(mij.getCoorContact())
                || !mr.getHospEmail().equals(mij.getCoorEmail())
                || !mr.getMemContact().equals(mij.getMemberContact())) {
            auditRemarks += "Edited contact info. ";
            mr.setHospContact(mij.getCoorContact());
            mr.setHospEmail(mij.getCoorEmail());
            mr.setMemContact(mij.getMemberContact());
            maceService.updateMaceRequest(mr);
        }

        MaceReqInpatient mri = maceService.getMaceReqInpatientByReqId(mr.getRequestId());
        if (null != mij.getRemarks() && !mij.getRemarks().equals("")) {
            auditRemarks += "Updated IP Remarks. ";
            mri.setLastupdateBy(mij.getAppUsername());
            mri.setLastupdateOn(new Date());
            mri.setIpReasonRemarks(mij.getRemarks());
            maceService.updateMaceReqInpatient(mri);
        }

        if (mij.getDoctorCodes() != null) {
            auditRemarks += "Added Doctors - ";
            for (String docCode : mij.getDoctorCodes()) {
                MaceReqIpDoctor mrid = new MaceReqIpDoctor();
                Doctor doc = claimsService.getDoctor(docCode, true);
                DoctorToHospital docHosp = claimsService.getDoctorToHospitalObject(mij.getHospitalCode(), docCode, true);
                if (null == doc) {
                    mrid.setDoctorCode("");
                    mrid.setFullName(docCode);
                    auditRemarks += mrid.getFullName() + "/";
                } else {
                    mrid.setDoctorCode(docCode);
                    mrid.setDocLname(doc.getDocLname());
                    mrid.setDocFname(doc.getDocFname());
                    mrid.setDocMname(doc.getDocMname());
                    auditRemarks += mrid.getDoctorName() + "/";
                }
                mrid.setDocHospId(null != docHosp ? docHosp.getDocHospId() : 0L);
                mrid.setHospitalCode(mij.getHospitalCode());

                mrid.setAddedBy(mij.getAppUsername());
                mrid.setAddedOn(new Date());
                mrid.setIsAccredited(null != docHosp ? Boolean.valueOf(docHosp.getIsAccredited()) : false);
                mrid.setTransCode(mri.getTransCode());
                maceService.saveMaceReqIpDoctor(mr, mri, mrid);
            }
            auditRemarks.substring(0, auditRemarks.length());
            auditRemarks += ". ";
        }
        int lastReqDiagId = 0;
        if (null != mij.getDiagCodes()) {
            auditRemarks += "Added Diag - ";
            for (String diagCode : mij.getDiagCodes()) {
                MaceReqIpDiag mridg = new MaceReqIpDiag();
                DiagnosisEntity diagnosisEntity = maceService.getDiagnosisEntity(diagCode);
                if (null == diagnosisEntity) {
                    mridg.setDiagCode("N/A");
                    mridg.setDiagDesc(diagCode);
                } else {
                    mridg.setDiagnosisEntity(diagnosisEntity, 1);
                    mridg.setDiagCode(diagCode);
                    mridg.setDiagDesc(diagnosisEntity.getDiagDesc());
                }
                mridg.setTransCode(mri.getTransCode());
                maceService.saveMaceReqIpDiag(mr, mri, mridg);
                lastReqDiagId = mridg.getIpReqdiagId();
            }
        }

        for (MaceInpatientJson.ProcJson procJson : mij.getProcCodes()) {
            MaceReqIpDiagproc mridp = new MaceReqIpDiagproc();
            mridp.setMaceRequestId(mr.getRequestId());
            mridp.setDiagCode(null == mij.getDiagCodes() ? "998" : mij.getDiagCodes().get(0));
            mridp.setReqDiagId(lastReqDiagId);

            TestProcObject tpo = claimsService.getTestProcObject(procJson.getProcCode());
            if (null == tpo) {
                TempProcModel tpm = claimsService.findTempProc(procJson.getProcCode());
                if (null == tpm) {
                    tpm = maceService.createTempProc(procJson.getProcCode(), procJson.getProcAmount(), mij.getAppUsername());
                }
                mridp.setProcDesc(tpm.getProcName());
                mridp.setProcCode(tpm.getProcCode());
                mridp.setActualAmount(tpm.getProcAmount());
                claimsService.addTempProcedure(tpm);
            } else {
                mridp.setProcCode(tpo.getProcCode());
                mridp.setProcDesc(tpo.getProcName());
                mridp.setActualAmount(tpo.getAmount());
            }
            mridp.setTransCode(mri.getTransCode());
            maceService.saveMaceReqIpDiagProc(mri, mridp);
        }

        MaceReqIpRoom mrir = new MaceReqIpRoom();
        mrir.setRoomtype(mij.getRoomType());
        mrir.setRate(mij.getRoomRate());
        mrir.setHospitalCode(mij.getHospitalCode());
        mrir.setAddedBy(mij.getAppUsername());
        mrir.setAddedOn(new Date());
        mrir.setLastUpdateBy(mij.getAppUsername());
        mrir.setTransCode(mri.getTransCode());
        maceService.saveMaceRequestIpRoom(mri, mrir);

        MaceInpatientAudit mia = new MaceInpatientAudit();
        mia.setRequestedBy(mij.getAppUsername());
        mia.setAuditDate(new Date());
        mia.setMaceRequestId(mr.getRequestId());
        mia.setTransactionId(mri.getTransactionId());
        mia.setAuditRemarks(auditRemarks);
        mia.setRequestOrigin(mij.getRequestOrigin());
        maceService.insertMaceInpatientAudit(mia);

        response.put("responseCode", 200);
        response.put("requestCode", mr.getRequestCode());
        response.put("responseDesc", "Successfully updated inpatient request.");
        response.put("maceRequest", mr);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Inquire Inpatient Service for Coordinator App
     * This service cannot be shared as there are no mappings in requests coming from the app.
     */
    @RequestMapping(value = "/inquireInpatient", method = RequestMethod.POST)
    private ResponseEntity<?> inquireInpatient(@RequestBody MaceInpatientJson mij) {
        HashMap<String, Object> response = new HashMap<>();

        MemberDetails member = memService.getMember(mij.getMemberCode());
        if (null == member) {
            response.put("responseCode", HttpStatus.NOT_FOUND);
            response.put("responseDesc", "Member ID Invalid.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Hospital hospital = claimsService.getHospital(mij.getHospitalCode());
        if (null == hospital) {
            response.put("responseCode", HttpStatus.NOT_FOUND);
            response.put("responseDesc", "Invalid Hospital Code.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        MaceRequest mr = new MaceRequest();
        mr.setMemberDetails(member);
        mr.setRequestOrigin(mij.getRequestOrigin());
        mr.setHospContact(mij.getCoorContact());
        mr.setRequestFromhosp(mij.getHospitalCode());
        mr.setRequestFrommem(mij.getMemberCode());
        mr.setRequestBy(mij.getAppUsername());
        mr.setRequestDatetime(new Date());
        mr.setHospEmail(mij.getCoorEmail());
        mr.setServiceType("INPATIENT");
        mr.setServiceTypeId(4);
        mr.setStatus("PENDING");
        mr.setAdmissionDate(mij.getDateTimeAdmitted());
        mr.setAdmissionType(mij.getAdmissionType());
        mr.setMemContact(mij.getMemberContact());
        mr.setRequestDevice(mij.getDeviceId());
        mr.setReasonForConsult(mij.getReasonForAdmission());
        String requestCode = maceService.generateID("REQUESTCODE");
        mr.setRequestCode(requestCode);
        maceService.saveMaceRequest(mr);

        MaceReqInpatient mri = new MaceReqInpatient(mij);
        DoctorToHospital dth = claimsService.getDoctorToHospitalObject(mij.getHospitalCode(), mri.getDoctorCode(), false);
        String approvalNo = maceService.generateID("APPROVALNO");
        mri.setLoaNo(approvalNo);
        mri.setIpReasonRemarks(mij.getRemarks());
        mri.setRequestCode(mr.getRequestCode());
        mri.setDocHospId(null == dth ? 0 : dth.getDocHospId());
        mri.setRequestFrom("COORDINATOR");
        mri.setRecordSubmittedOn(new Date());
        String transCode = maceService.generateID("INPATIENT");
        mri.setTransCode(transCode);
        mri.setDdLimit(String.valueOf(member.getDD_Reg_Limits()));
        Diagnosis diag = claimsService.getDiagnosisByDiagnosisCode(mij.getDiagCodes().get(0));
        if (null != diag) {
            mri.setDiagnosis(diag);
        }
        maceService.saveMaceRequestInpatient(mr, mri);
        if (mij.getDoctorCodes() != null) {
            for (String docCode : mij.getDoctorCodes()) {
                MaceReqIpDoctor mrid = new MaceReqIpDoctor();
                Doctor doc = claimsService.getDoctor(docCode, true);
                DoctorToHospital docHosp = claimsService.getDoctorToHospitalObject(hospital.getHospitalCode(), docCode, true);
                if (null == doc) {
                    mrid.setDoctorCode("");
                    mrid.setFullName(docCode);
                } else {
                    mrid.setDoctorCode(docCode);
                    mrid.setDocLname(doc.getDocLname());
                    mrid.setDocFname(doc.getDocFname());
                    mrid.setDocMname(doc.getDocMname());
                }
                mrid.setDocHospId(null != docHosp ? docHosp.getDocHospId() : 0L);
                mrid.setHospitalCode(mij.getHospitalCode());

                mrid.setAddedBy(mij.getAppUsername());
                mrid.setAddedOn(new Date());
                mrid.setIsAccredited(null != docHosp ? Boolean.valueOf(docHosp.getIsAccredited()) : false);
                mrid.setTransCode(transCode);
                maceService.saveMaceReqIpDoctor(mr, mri, mrid);
            }
        }
        int lastReqDiagId = 0;
        if (null != mij.getDiagCodes()) {
            for (String diagCode : mij.getDiagCodes()) {
                MaceReqIpDiag mridg = new MaceReqIpDiag();
                DiagnosisEntity diagnosisEntity = maceService.getDiagnosisEntity(diagCode);
                if (null == diagnosisEntity) {
                    TempDiagModel tempDiag = claimsService.findTempDiag(diagCode);
                    if (null == tempDiag) {
                        tempDiag = maceService.createTempDiag(diagCode, mij.getAppUsername());
                        claimsService.addTempDiag(tempDiag);
                    }
                    mridg.setDiagCode(tempDiag.getDiagCode());
                    mridg.setDiagDesc(tempDiag.getDiagDesc());
                } else {
                    mridg.setDiagnosisEntity(diagnosisEntity, 1);
                    mridg.setDiagCode(diagCode);
                    mridg.setDiagDesc(diagnosisEntity.getDiagDesc());
                }
                mridg.setTransCode(transCode);
                maceService.saveMaceReqIpDiag(mr, mri, mridg);
                lastReqDiagId = mridg.getIpReqdiagId();
            }
        }

        for (MaceInpatientJson.ProcJson procJson : mij.getProcCodes()) {
            MaceReqIpDiagproc mridp = new MaceReqIpDiagproc();
            mridp.setMaceRequestId(mr.getRequestId());
            mridp.setDiagCode(null == mij.getDiagCodes() ? "998" : mij.getDiagCodes().get(0));
            mridp.setReqDiagId(lastReqDiagId);

            TestProcObject tpo = claimsService.getTestProcObject(procJson.getProcCode());
            if (null == tpo) {
                TempProcModel tpm = claimsService.findTempProc(procJson.getProcCode());
                if (null == tpm) {
                    tpm = maceService.createTempProc(procJson.getProcCode(), procJson.getProcAmount(), mij.getAppUsername());
                }
                mridp.setProcDesc(tpm.getProcName());
                mridp.setProcCode(tpm.getProcCode());
                mridp.setActualAmount(tpm.getProcAmount());
                claimsService.addTempProcedure(tpm);
            } else {
                mridp.setProcCode(tpo.getProcCode());
                mridp.setProcDesc(tpo.getProcName());
                mridp.setActualAmount(tpo.getAmount());
            }
            mridp.setTransCode(transCode);
            maceService.saveMaceReqIpDiagProc(mri, mridp);
        }

        MaceReqIpRoom mrir = new MaceReqIpRoom();
        mrir.setHospitalCode(mij.getHospitalCode());
        mrir.setRoomtype(mij.getRoomType());
        mrir.setRate(mij.getRoomRate());
        mrir.setRoomplan(mij.getCategory());
        mrir.setAddedBy(mij.getAppUsername());
        mrir.setAddedOn(new Date());
        mrir.setDateFrom(mij.getDateFrom());
        mrir.setLastUpdateBy(mij.getAppUsername());
        mrir.setTransCode(transCode);
        maceService.saveMaceRequestIpRoom(mri, mrir);

        MaceInpatientAudit mia = new MaceInpatientAudit();
        mia.setRequestedBy(mij.getAppUsername());
        mia.setAuditDate(new Date());
        mia.setMaceRequestId(mr.getRequestId());
        mia.setTransactionId(mri.getTransactionId());
        mia.setAuditRemarks("Initial Admission of Inpatient.");
        mia.setRequestOrigin(mij.getRequestOrigin());
        maceService.insertMaceInpatientAudit(mia);


        response.put("responseCode", 200);
        response.put("requestCode", mr.getRequestCode());
        response.put("responseDesc", "Successfully sent inquiry.");
        response.put("responseDesc", "Successfully sent inpatient request.");
        response.put("maceRequest", mr);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/inquireER", method = RequestMethod.POST)
    public ResponseEntity<?> inquireER(@RequestBody MaceErJSON mej) {

        HashMap<String, Object> response = new HashMap<>();
        try {

            MemberDetails memberDetails = memService.getMember(mej.getMemberCode());

            Hospital hospital = claimsService.getHospital(mej.getHospitalCode());
            if (null == hospital) {
                response.put("responseCode", HttpStatus.NOT_FOUND);
                response.put("responseDesc", "Invalid Hospital Code.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            //TODO Add Reason for Emergency
            //TODO Add Date Admitted
            CustomerCare c = maceService.processEmergencyRoomInquiry(
                    memberDetails,
                    mej.getHospitalCode(),
                    mej.getAppUsername(),
                    mej.getReasonChiefComplaint(),
                    mej.getDateTimeSubmitted().toString()
            );

            String remarks = "INQUIRY FOR ER";
            String responseCode = "200";

            c.setRemarks(remarks);
            c.setActionTaken(5);
            c.setMemberCode(mej.getMemberCode());

            c.setBatchCode(maceService.generateID("BATCHNO"));
            c.setCallerId(maceService.generateID("CALLERID"));


            maceService.saveTransactionForCall(c);
            customerServiceService.saveTransactionForCall(c);

            //TODO Save Mace Request
            //TODO Save Mace Request Er

            MaceRequest mr = new MaceRequest(c, memberDetails, 5, "COORDINATOR", mej.getAppUsername());
            MaceReqEr mrer = new MaceReqEr(c, memberDetails, mej);
            String transCode = maceService.generateID("EMERGENCY_ROOM");
            maceService.saveMaceRequest(mr);
            maceService.saveMaceRequestER(mr, mrer);


            //TODO

            if (mej.getDoctorCodes() != null) {
                for (String docCode : mej.getDoctorCodes()) {
                    MaceReqErDoctor mred = new MaceReqErDoctor();
                    Doctor doc = claimsService.getDoctor(docCode, true);
                    DoctorToHospital docHosp = claimsService.getDoctorToHospitalObject(hospital.getHospitalCode(), docCode, true);
                    if (null == doc) {
                        mred.setDoctorCode("");
                        mred.setFullName(docCode);
                    } else {
                        mred.setDoctorCode(docCode);
                        mred.setDocLName(doc.getDocLname());
                        mred.setDocFName(doc.getDocFname());
                        mred.setDocMName(doc.getDocMname());
                    }
                    mred.setDocHospId(null != docHosp ? docHosp.getDocHospId() : 0L);
                    mred.setHospitalCode(mej.getHospitalCode());

                    mred.setAddedBy(mej.getAppUsername());
                    mred.setAddedOn(new Date());
                    mred.setIsAccredited(null != docHosp ? Boolean.valueOf(docHosp.getIsAccredited()) : false);
                    mred.setTransCode(transCode);

                    maceService.saveMaceReqErDoctor(mr, mrer, mred);
                }
            }
            int lastReqDiagId = 0;
            if (null != mej.getDiagCodes()) {
                for (String diagCode : mej.getDiagCodes()) {
                    MaceReqErDiag mredg = new MaceReqErDiag();
                    DiagnosisEntity diagEntity = maceService.getDiagnosisEntity(diagCode);
                    if (null == diagEntity) {
                        TempDiagModel tempDiag = claimsService.findTempDiag(diagCode);
                        if (null == tempDiag) {
                            tempDiag = maceService.createTempDiag(diagCode, mej.getAppUsername());
                            claimsService.addTempDiag(tempDiag);
                        }
                        mredg.setDiagCode(tempDiag.getDiagCode());
                        mredg.setDiagDesc(tempDiag.getDiagDesc());
                    } else {
                        mredg.setDiagnosisEntity(diagEntity, 1);
                        mredg.setDiagCode(diagCode);
                        mredg.setDiagDesc(diagEntity.getDiagDesc());
                    }
                    mredg.setTransCode(transCode);
                    maceService.saveMaceReqErDiag(mr, mrer, mredg);
                    lastReqDiagId = mredg.getReqDiagId();
                }
            }

            for (MaceErJSON.ProcJson procJson : mej.getProcCodes()) {
                MaceReqErProcedure mrerp = new MaceReqErProcedure();
                mrerp.setMaceRequestId(mr.getRequestId());
                mrerp.setDiagCode(null == mej.getDiagCodes()?"998":mej.getDiagCodes().get(0));
                mrerp.setReqDiagId(lastReqDiagId);

                TestProcObject tpo = claimsService.getTestProcObject(procJson.getProcCode());
                if (null == tpo) {
                    TempProcModel tpm = claimsService.findTempProc(procJson.getProcCode());
                    if (null == tpm) {
                        tpm = maceService.createTempProc(procJson.getProcCode(), procJson.getProcAmount(), mej.getAppUsername());
                    }
                    mrerp.setProcDesc(tpm.getProcName());
                    mrerp.setProcCode(tpm.getProcCode());
                    mrerp.setProcActualAmount(tpm.getProcAmount());
                    claimsService.addTempProcedure(tpm);
                } else {
                    mrerp.setProcCode(tpo.getProcCode());
                    mrerp.setProcDesc(tpo.getProcName());
                    mrerp.setProcActualAmount(tpo.getAmount());
                }
                mrerp.setTransCode(transCode);
                maceService.saveMaceReqErProc(mrer, mrerp);
            }

            MaceRequestErReturned maceRequestErReturned  = new MaceRequestErReturned();

            //Getting the hospital of the Request ER
            Hospital hospital1 = claimsService.getHospital(mej.getHospitalCode());
            if (null == hospital1) {
                response.put("responseCode", 404);
                response.put("responseDesc", "Hospital not found.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            //Getting all doctors of the Request from the Database
            try{
                List<MaceRequestErReturned.DoctorList> maceErDocList= new ArrayList<>();
                if (mej.getDoctorCodes() != null) {
                    for (String docCode : mej.getDoctorCodes()) {
                        MaceRequestErReturned.DoctorList merDoc = new MaceRequestErReturned.DoctorList();
                        Doctor doc = claimsService.getDoctor(docCode, true);
                        merDoc.setDoctorName(doc.getFullName());
                        merDoc.setDoctorSpec(doc.getSpecDesc());
                        maceErDocList.add(merDoc);
                    }
                    maceRequestErReturned.setDocList(maceErDocList);
                }
            }catch (Exception e){
                response.put("responseDesc", "Error in MaceReqErDoctor processing.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            //Getting all diagnosis of the Request from the Database
            try{
                List<MaceRequestErReturned.DiagnosisList> maceErDiagList= new ArrayList<>();
                if (mej.getDiagCodes() != null) {
                    for (String diagCode : mej.getDiagCodes()) {
                        MaceRequestErReturned.DiagnosisList merdiag = new MaceRequestErReturned.DiagnosisList();
                        DiagnosisEntity diagEntity = maceService.getDiagnosisEntity(diagCode);
                        merdiag.setDiagDesc(diagEntity.getDiagDesc());
                        merdiag.setIcd10Desc(diagEntity.getIcd10Desc());
                        maceErDiagList.add(merdiag);
                    }
                    maceRequestErReturned.setDiagnoses(maceErDiagList);
                }
            }catch (Exception e){
                response.put("responseDesc", "Error in MaceReqErDiag processing.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            //getting all Procedures of the Request from the Database
            try{
                List<MaceRequestErReturned.ProcList> maceErProcList = new ArrayList<>();
                if(mej.getProcCodes() != null){
                    for(MaceErJSON.ProcJson procCode : mej.getProcCodes()) {
                        MaceRequestErReturned.ProcList merProc = new MaceRequestErReturned.ProcList();
                        TestProcObject tpo = claimsService.getTestProcObject(procCode.getProcCode());
                        merProc.setProcDesc(tpo.getProcName());
                        merProc.setProcAmount(tpo.getAmount());
                        maceErProcList.add(merProc);
                    }
                    maceRequestErReturned.setProcList(maceErProcList);
                }
            }catch (Exception e){
                response.put("responseDesc", "Error in MaceReqErProc processing.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


            response.put("ErRetuned", maceRequestErReturned);
            response.put("hospital",hospital1);
            response.put("memberDetails", memberDetails);
            response.put("referenceNo", c.getBatchCode());
            response.put("responseCode", responseCode);
            response.put("reasonOfER", mej.getReasonChiefComplaint());
            response.put("dateSubmitted", sdf.format(mej.getDateTimeSubmitted()));
            response.put("responseDesc", remarks);
            response.put("remarks", remarks);
            response.put("maceReqId", mr.getRequestId());
            String requestCode = maceService.generateID("REQUESTCODE");
            if (null != requestCode) {
                response.put("requestCode", requestCode);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            response.put("responseCode", 230);
            response.put("responseDesc", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
