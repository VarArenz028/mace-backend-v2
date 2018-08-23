package com.basicauth.mapper.mace;

import com.basicauth.data.*;
import com.basicauth.data.MaceRequest;
import com.basicauth.domain.*;
import com.basicauth.domain.dups.MaceConsPrescribedtest;
import com.basicauth.domain.MaceReqOpDiag;
import com.basicauth.domain.dups.*;
import com.basicauth.types.ConsultJson;
import org.apache.ibatis.annotations.Param;

import java.util.*;


/**
 * Created by igan_long on 10/6/2016.
 */
public interface MaceMapper {


    int getLockCount();

    HashMap hasExistingMemUserAcct(@Param("username") String username, @Param("memberCode") String memberCode);

    HashMap getMemUserAcct(@Param("username") String username);

    HashMap getMemUserAcctByMemberCode(@Param("memberCode") String memberCode);

    void resetInvalidLoginCount(@Param("username") String username);

    void increaseInvalidLoginCount(@Param("username") String username);

    void lockUser(@Param("username") String username);

    void unlockUser(@Param("memCode") String memCode);

    void saveImageHolder(@Param("imageHolder") ImageHolder imageHolder);

    ImageHolder getImageHolder(@Param("memCode") String memCode);

    void deleteImageHolder(@Param("memCode") String memCode);

    HashMap hasExistingAppUserAcct(@Param("username") String username);

    HashMap getAppUser(@Param("username") String username);

    List getUserAccounts(@Param("userAccountId") int userAccountId);

    List getUserAccountsDependents(@Param("userAccountId") int userAccountId);

    void saveMemAccount(@Param("memberAccount") MemberAccount memberAccount);

    List getVerifiedMembers(@Param("memberCode") String memberCode);

    VerifiedMember getLatestVerifiedMember(@Param("memberCode") String memberCode);

    void saveVerifiedMember(@Param("verifiedMember") VerifiedMember verifiedMember);

    AppUser findAppUserByUsername(@Param("username") String username);

    void saveTransactionCustomerCare(@Param("c") CustomerCare c);

    Boolean isCompanyBlacklisted(@Param("account_code") String account_code, @Param("serviceType") String serviceType);

    HashMap getLastId(@Param("type") String type);

    void updateLastId(@Param("type") String type, @Param("approvaNo") double approvaNo);

    void saveTransactionCustomerCareApproval(@Param("c") CustomerCare c);

    void saveTransactionCustomerCarePhysician(@Param("c") CustomerCare c);

    Boolean checkIfMemberCodeExistingInAccount(@Param("user_account_id") int user_account_id, @Param("depMemberCode") String depMemberCode);

    void changeMemUserAccountPassword(@Param("username") String username, @Param("encoded") String encoded);

    OtherLimit getOtherLimit();

    Double getInnerLimit(@Param("costCenter") String costCenter);

    Map findMemUserByEmailAndMemberCode(@Param("email") String email, @Param("memberCode") String memberCode);

    void changeMemUserAccountPasswordAuto(@Param("username") String username, @Param("encoded") String encoded);

    List<Blacklist> companyBlacklistedServices(@Param("account_code") String account_code);

    SpecialAccount getSpecialAccount(@Param("account_code") String account_code);

    boolean isMemberLocked(@Param("memberCode") String memberCode);

    //void lockMember(@Param("memberCode") String memberCode, @Param("newDate") Date newDate);

    void lockMember(@Param("lockMember") LockMember lockMember);

    void unlockMember(@Param("memberCode") String memberCode);

    List getMessages();

    void changeMemUserAccountPin(@Param("username") String username, @Param("pin") String pin);

    void saveLoaMace(@Param("loaMace") LoaMace loaMace);

    List getLoaByProvider(@Param("doctorCode") String doctorCode);

    List<LoaMace> getLoaByMemberCode(@Param("memberCode") String memberCode);

    List getLoaByProviderAndMemberCode(@Param("doctorCode") String doctorCode, @Param("memberCode") String memberCode);

    List<MaceRequest> getMemberLOAList(@Param("@membercode") String membercode,
                          @Param("@hospitalCode") String hospitalCode,
            @Param("@serviceType") String serviceType,
            @Param("@requestStatus") String requestStatus,
            @Param("@offset") int offset,
            @Param("@count") int count);

    List<MaceRequest> getMemberLOAListNoHospital(@Param("@membercode") String membercode,
                                       @Param("@serviceType") String serviceType,
                                       @Param("@requestStatus") String requestStatus,
                                       @Param("@offset") int offset,
                                       @Param("@count") int count);

    List getLoaByHospitalAndMemberCode(@Param("hospitalCode") String hospitalCode, @Param("memberCode") String memberCode);

    LoaMace getLoaByApprovalNumberAndDoctorCode(@Param("approvalNumber") String approvalNumber, @Param("doctorCode") String doctorCode);

    void saveMaceRequestAttachment(@Param("MaceRequestAttachment") MaceRequestAttachment attachment);



    void insertInpatientRecord(@Param("i") InpatientJson i);

    InpatientJson checkInpatientRecord(@Param("hospitalCode") String hospitalCode, @Param("memberCode") String memberCode);

    void releaseInpatientRecord(@Param("hospitalCode") String hospitalCode, @Param("memberCode") String memberCode);

    void updateInpatientRecord(@Param("i") InpatientJson i);

    void insertConsultationRecord(@Param("c") ConsultationRecordJson c);

    boolean checkIfProviderHasAppUserAccount(@Param("doctorCode") String doctorCode);

    void saveComplaint(@Param("batchCode") String batchCode, @Param("complaint") String complaint);

    int insertConsultationRecordVersion2(@Param("consultationRecordJson") ConsultationRecordVersion02Json consultationRecordJson);

    void insertConsultationRecordVersion2PrescribedTestOrProcedures(@Param("prescribedTestOrProcedures") List<String> prescribedTestOrProcedures, @Param("consultationRecordId") int consultationRecordId);

    void insertConsultationRecordVersion2OtherDiagnosisContributoryToChiefComplaint(@Param("otherDiagnosisContributoryToChiefComplaint") List<String> otherDiagnosisContributoryToChiefComplaint, @Param("consultationRecordId") int consultationRecordId);

    void insertConsultationRecordVersion2OtherDiagnosisNonContributoryToChiefComplaint(@Param("otherDiagnosisNonContributoryToChiefComplaint") List<String> otherDiagnosisNonContributoryToChiefComplaint, @Param("consultationRecordId") int consultationRecordId);

    void insertConsultationRecordVersion2ProceduresDoneInClinic(@Param("proceduresDoneInClinic") ArrayList<String> proceduresDoneInClinic, @Param("consultationRecordId") int consultationRecordId);

    ConsultationRecordVersion02Json getConsultationRecordVersion2(@Param("consultationRecordId") int consultationRecordId);

    List<String> getConsultationRecordVersion2OtherDiagnosisContributoryToChiefComplaint(@Param("consultationRecordId") int consultationRecordId);

    List<String> getConsultationRecordVersion2OtherDiagnosisNonContributoryToChiefComplaint(@Param("consultationRecordId") int consultationRecordId);

    List<String> getConsultationRecordVersion2PrescribedTestOrProcedures(@Param("consultationRecordId") int consultationRecordId);

    ArrayList<String> getConsultationRecordVersion2ProceduresDoneInClinic(@Param("consultationRecordId") int consultationRecordId);

    void cancelLOAByBatchCode(@Param("batchCode") String batchCode, @Param("user") String user, @Param("updatedBy") String updatedBy);

    void cancelLTBLByBatchCode(@Param("batchCode") String batchCode, @Param("user") String user, @Param("updatedBy") String updatedBy);

    int insertConsultationRecordVersion3(@Param("consultationRecordJson") ConsultationRecordVersion03Json consultationRecordJson);

    void insertConsultationRecordVersion3PrescribedTestOrProceduresForPrimaryDiagnosisCodeList(@Param("prescribedTestOrProceduresForPrimaryDiagnosisCodeList") List<String> prescribedTestOrProceduresForPrimaryDiagnosisCodeList, @Param("consultationRecordId") int consultationRecordId);

    void insertConsultationRecordVersion3ProceduresDoneInClinic(@Param("proceduresDoneInClinic") List<String> proceduresDoneInClinic, @Param("consultationRecordId") int consultationRecordId);

    void insertConsultationRecordVersion3OtherDiagnosisContributoryToChiefComplaintListDiagnosisCode(@Param("diagnosisCode") String diagnosisCode, @Param("consultationRecordId") int consultationRecordId);

    void insertConsultationRecordVersion3OtherDiagnosisContributoryToChiefComplaintListPerDiagnosisCode(@Param("diagnosisCode") String diagnosisCode, @Param("consultationRecordId") int consultationRecordId, @Param("prescribedTestOrProcedureCodeList") List<String> prescribedTestOrProcedureCodeList);

    void insertConsultationRecordVersion3OtherDiagnosisNonContributoryToChiefComplaintListDiagnosisCode(@Param("diagnosisCode") String diagnosisCode, @Param("consultationRecordId") int consultationRecordId);

    void insertConsultationRecordVersion3OtherDiagnosisNonContributoryToChiefComplaintListPerDiagnosisCode(@Param("diagnosisCode") String diagnosisCode, @Param("consultationRecordId") int consultationRecordId, @Param("prescribedTestOrProcedureCodeList") List<String> prescribedTestOrProcedureCodeList);

    ConsultationRecordVersion03Json getConsultationRecordVersion3(@Param("consultationRecordId") int consultationRecordId);

    List<String> getConsultationRecordVersion3PrescribedTestOrProcedures(@Param("consultationRecordId") int consultationRecordId);

    List<String> getConsultationRecordVersion3ProceduresDoneInClinic(@Param("consultationRecordId") int consultationRecordId);

    List<String> getConsultationRecordVersion3OtherDiagnosisNonContributoryToChiefComplaintList(@Param("consultationRecordId") int consultationRecordId);

    List<String> getConsultationRecordVersion3OtherDiagnosisNonContributoryToChiefComplaintListPerDiagnosisCode(@Param("consultationRecordId") int consultationRecordId, @Param("diagnosisCode") String diagnosisCode);

    List<String> getConsultationRecordVersion3OtherDiagnosisContributoryToChiefComplaintList(@Param("consultationRecordId") int consultationRecordId);

    List<String> getConsultationRecordVersion3OtherDiagnosisContributoryToChiefComplaintListPerDiagnosisCode(@Param("consultationRecordId") int consultationRecordId, @Param("diagnosisCode") String diagnosisCode);

    String getAppUserEmailByUsername(@Param("username") String username);

    BlockingMessages getBlockingMessageByCode(@Param("msgCode") String msgCode);

    Double getCostByTransactionType(@Param("transaction") String transaction);

    PrescribedTest getTestByTestCode(@Param("testCode") String testCode);

    Integer getRequestByComparison(@Param("c") CustomerCare c);

    void storeInTempTransaction(@Param("c") CustomerCare c);

    void setLoaStatusByApprovalNo(@Param("approvalNo") String approvalNumber, @Param("status") String status, @Param("disclaimerTicked") Integer disclaimerTicked);

    AppUserDevice getAppUserDeviceByDeviceId(@Param("deviceId") String deviceId);

    AppVersion getAppVersionByUserType(@Param("type") String type);

    String getPinByMemberCode(@Param("memberCode") String memberCode);

    void updateLOADisclaimerByApprovalNo(@Param("approvalNo") String approvalNumber, @Param("disclaimerTicked") Integer disclaimerTicked);

    Integer getDisclaimerTickedByMemberCode(@Param("memberCode") String memberCode);

    void updateDisclaimerTickedByMemberCode(@Param("memberCode") String memberCode, @Param("disclaimerTicked") Integer disclaimerTicked, @Param("date") Date date);

    AppUser getAppUserByEmailAndDoctorOrDentistCode(@Param("email") String email, @Param("code") String doctorCode);

    void changeAppUserAccountPasswordAuto(@Param("username") String username, @Param("password") String password);

    void changeAppUserAccountPassword(@Param("username") String username, @Param("password") String password);

    Boolean checkProcedureAvailability(@Param("doctorCode") String doctorCode, @Param("hospitalCode") String hospitalCode, @Param("diagnosisCode") String diagnosisCode);

    Integer saveMaceRequest(@Param("maceRequest") MaceRequest maceRequest);

    void saveMaceReqProcedure(@Param("maceRequest") MaceRequest maceRequest, @Param("maceRequestProcedure") MaceRequestProcedure maceRequestProcedure);

    void saveMaceReqOpProcedure(@Param("maceRequest") MaceRequest maceRequest, @Param("maceRequestOpProcedure") MaceRequestOpProcedure maceRequestOpProcedure);

    void saveMaceReqOpDiag(@Param("maceRequest") MaceRequest maceRequest, @Param("maceRequestOpDiag") MaceRequestOpDiag maceRequestOpDiag);

    void saveMaceReqOpTest(@Param("maceRequestOpTest") MaceRequestOpTest maceRequestOpTest);

    void saveMaceReqTest(@Param("maceRequestTest") MaceRequestTest maceRequestTest);

    void saveMaceReqOpDoctor(@Param("maceRequestOpDoctor") MaceRequestOpDoctor maceRequestOpDoctor);

    void saveMaceReqOpRoom(@Param("maceRequestOpRoom") MaceRequestOpRoom maceRequestOpRoom);

    void saveMaceReqOpOtherCharge(@Param("maceRequestOpOtherCharge") MaceRequestOpOtherCharge maceRequestOpOtherCharge);

    void saveMaceReqOpOtherInformation(@Param("maceRequestOpOtherInformation") MaceRequestOpOtherInformation maceRequestOpOtherInformation);

    List<LoaMace> getAvailedConsultationListByDoctorCodeAndMemberCode(@Param("doctorCode") String doctorCode, @Param("memberCode") String memberCode);

    ConsultationRecordVersion03Json getConsultationRecordVersion3ByApprovalNo(@Param("approvalNo") String approvalNo);

    List<LoaMaceJson> getLoaByMemberCodeJson(@Param("memberCode") String memberCode);

    CustomerCare getLoaLTBLByBatchCode(@Param("batchCode") String batchCode);

    void addToLoaHistory(@Param("c") CustomerCare loa);

    List<MaceRequest> getFilteredLoaByMemberCode(@Param("memberCode") String memberCode, @Param("filterType") Integer filterType);

    LoaMace getAvailedConsultationByDoctorCodeAndMemberCode(@Param("referenceNo") String referenceNo);

    LoaMace getAvailedConsultationById(@Param("referenceNo") int referenceNo);

    LoaMace getAvailedConsultationByApprovalNo(String approvalNo);

    MaceRequestProcedure getMaceRequestProcedureByMaceRequestId(@Param("requestId") long requestId);

    void saveMaceRequestAuditLog(@Param("maceRequest") MaceRequest maceRequest, @Param("maceRequestAudit") MaceRequestAudit maceRequestAudit);

    boolean checkIfMemberExistsByMemberCode(@Param("memberCode") String memberCode);

    void changeAppUserDeviceVersionNo(@Param("deviceId") String deviceId, @Param("versionNo") String versionNo);

    DiagnosisEntity getDiagnosisEntity(@Param("diagnosisCode") String diagnosisCode);

    void updateContactNo(@Param("appUsername") String appUsername, @Param("contactNo") String contactNo);

    void saveMaceRequestInpatient(@Param("mrip") MaceReqInpatient mrip);

    void saveMaceReqIpOtherService(@Param("mrios") MaceReqIpOtherservices mrios);

    void saveMaceRequestIpRoom(@Param("mrir") MaceReqIpRoom mrir);

    void saveMaceReqIpDoctor(@Param("mrid") MaceReqIpDoctor mrid);

    void saveMaceReqErDoctor(@Param("mred") MaceReqErDoctor mred);

    void saveMaceReqIpDiag(@Param("mrid") MaceReqIpDiag mrid);

    void saveMaceReqErDiag(@Param("mred") MaceReqErDiag mred); //TODO

    void saveMaceReqIpDiagProc(@Param("mridp") MaceReqIpDiagproc mridp);

    void saveMaceReqErProc(@Param("mrerp") MaceReqErProcedure mrerp); //TODO

    void saveMaceRequestIpOtherCharge(@Param("mriOc") MaceReqIpOtherCharge mriOc);

    void saveMaceRequestIpOtherInformation(@Param("mriOi") MaceReqIpOtherInformation mriOi);

    List<MaceRequestOpDiag> getMaceReqOpDiagByMaceReqId(@Param("requestId") Integer requestId);

    List<MaceRequestOpProcedure> getMaceReqOpProcByMaceReqId(@Param("requestId") Integer requestId);

    List<MaceRequestOpTest> getMaceReqOpTestsByMaceReqId(@Param("requestId") Integer requestId);

    void saveMaceRequestOpDiag(@Param("maceRequestOpDiag") MaceRequestOpDiag mrod);


    // FOR MIGRATION

    List<MaceRequest> getMaceRequestsByMemberCode(@Param("memberCode") String memberCode,
                                                  @Param("status") String status,
                                                  @Param("offset") int offset,
                                                  @Param("count") int count);

    List<MaceRequest> getServiceTypeOnlyFilteredMaceRequestsByMemberCode(@Param("memberCode") String memberCode,
                                                                         @Param("serviceType") Integer serviceType,
                                                                         @Param("status") String status,
                                                                         @Param("offset") int offset,
                                                                         @Param("count") int count);

    List<MaceRequest> getServiceTypeOnlyFilteredMaceRequestsByMemberCodeForConsult(@Param("memberCode") String memberCode,
                                                                                   @Param("serviceType") Integer serviceType,
                                                                                   @Param("status") String status,
                                                                                   @Param("offset") int offset,
                                                                                   @Param("count") int count);

    List<MaceRequest> getServiceTypeOnlyFilteredMaceRequestsByMemberCodeForTest(@Param("memberCode") String memberCode,
                                                                                @Param("serviceType") Integer serviceType,
                                                                                @Param("status") String status,
                                                                                @Param("offset") int offset,
                                                                                @Param("count") int count);

    List<MaceRequest> getServiceTypeFilteredMaceRequestsByMemberCode(@Param("memberCode") String memberCode,
                                                                     @Param("serviceType") Integer serviceType,
                                                                     @Param("subServiceType") Integer subServiceType,
                                                                     @Param("status") String status,
                                                                     @Param("offset") int offset,
                                                                     @Param("count") int count);

    List<MaceRequest> getServiceTypeFilteredMaceRequestsByMemberCodeForConsult(@Param("memberCode") String memberCode,
                                                                               @Param("serviceType") Integer serviceType,
                                                                               @Param("subServiceType") Integer subServiceType,
                                                                               @Param("status") String status,
                                                                               @Param("offset") int offset,
                                                                               @Param("count") int count);

    List<MaceRequest> getServiceTypeFilteredMaceRequestsByMemberCodeForTest(@Param("memberCode") String memberCode,
                                                                            @Param("serviceType") Integer serviceType,
                                                                            @Param("subServiceType") Integer subServiceType,
                                                                            @Param("status") String status,
                                                                            @Param("offset") int offset,
                                                                            @Param("count") int count);


    List<MaceReqConsult> getMaceRequestConsult(@Param("@membercode") String memberCode,
                                               @Param("@consStatus") String consultStatus,
                                                @Param("@constype") String consultType,
                                            @Param("@doctorCode") String doctorCode,
                                            @Param("@serviceType") String subServiceType,
                                            @Param("@testStatus") String status,
                                            @Param("@offset") int offset,
                                            @Param("@count") int count);

    MaceRequest getMaceRequestByRequestID (@Param("request_id") int requestId);

    List<MaceReqOpDiag> getConsultDiagnosis(@Param("transaction_id") int transId,
                                            @Param("request_id") int requestId);

    List<MaceConsPrescribedtest> getConsultPrescribedTests(@Param("transaction_id") int transId,
                                                           @Param("request_id") int reqId,
                                                           @Param("testStatus") String status,
                                                           @Param("servicetype") String type);


    MaceReqConsult getMaceReqConsult(@Param("maceRequestId") Integer maceRequestId);

    List getOtherServices();

    MaceRequest getMaceRequestByRequestCode(@Param("requestCode") String requestCode);

    MaceReqInpatient getMaceReqInpatientByReqId(@Param("requestId") Integer requestId);

    List getMaceReqIpDiagByReqId(@Param("requestId") Integer requestId);

    String getRequestCodeByRequestId(@Param("requestId") Integer requestId);

    List getMaceReqIpDoctors(@Param("requestId") Integer requestId);

    void saveMaceReqConsult(@Param("mrc") MaceReqConsult mrc);

    MaceReqIpRoom getMaceReqIpRoom(@Param("maceRequestId") Integer maceRequestId, @Param("transactionId") Integer transactionId);

    MaceReqInpatient getLastReqCodeIp(@Param("memCode") String memCode);

    List<MaceRequestTest> getMaceRequestTests(@Param("requestId") Integer requestId);

//    List<MaceRequestAttachment> getMaceRequestTestsForWithAttachment(@Param("requestId") Integer requestId);

    List<MaceRequestProcedure> getMaceRequestProcedures(@Param("requestId") Integer requestId);

    void updateMaceRequest(@Param("maceRequest") MaceRequest maceRequest);

    void updateMaceReqInpatient(@Param("mri") MaceReqInpatient mri);

    void updateMaceReqEr(@Param("mrer") MaceReqEr mrer);

    List<MaceReqIpOtherservices> getMaceReqIpServices(@Param("requestId") Integer requestId);

    void updateMaceReqIpDiag(@Param("mrid") MaceReqIpDiag mrid);

    void deleteMaceReqIpDiag(@Param("ipReqDiagId") Integer ipReqdiagId);

    void deleteMaceReqIpDoctor(@Param("ipReqdocId") Integer ipReqdocId);

    List<MaceReqIpDiagproc> getMaceReqIpDiagProcs(@Param("requestId") Integer requestId);

    String getMaceReqCodeByMaceReqId(@Param("requestId") Integer requestId);

    Integer getreqIdByRequestCode(@Param("requestCode")String requestCode);

    MacePrescribedProcedure getPrescribedProcedure(@Param("procedureCode") String procedureCode);

    MaceRequestAttachment retrieveMaceAttachment(@Param("id")Integer id);

    MacePrescribedTest getPrescribedTest(@Param("procedureCode")String procedureCode);

    List<MaceRequestAttachment> getMaceAttachmentsByReqCode(@Param("requestCode")String requestCode);

    void saveMacePrescribedTest(@Param("mcpt") com.basicauth.domain.dups.MaceConsPrescribedtest mcpt);

    void deleteMaceAttachment(@Param("id") Integer id);

    void deleteMaceAttachmentByRequestCode(@Param("requestCode") String requestCode);

    void updateMaceRequestStatus(@Param("status") String approved, @Param("id") Integer id);

    void updateMaceRequestStatusAndAssignee(@Param("status") String approved, @Param("id") Integer id, @Param("statusAssignee") String statusAssignee);

    void overrideParentRequest(@Param("isOverriden") Boolean isOverriden, @Param("parRequestId") Integer parRequestId);

    void deleteMaceReqIpDiagproc(@Param("reqDiagId")Integer reqDiagId);

    List<MaceConfig> getMaceConfigObjects(@Param("valueType") String valueType);

    List<ApprovalRulesDao> getApprovalRules(@Param("serviceTypeId") Integer serviceTypeId, @Param("subtypeId") Integer subtypeId);

    void saveMaceRequestApprovalLog(@Param("mra") MaceRequestApproval maceRequestApproval);

    SpecialDiagnosis getSpecialDiagnosis(@Param("diagCode") String diagCode);

    MaceReqEr getLastReqCodeEr(@Param("memCode") String memCode);

    void saveMaceRequestER(@Param("mrer") MaceReqEr mrer);

    MaceRequest getMaceRequestByRequestId(@Param("requestId") Integer requestId);

    MaceReqEr getMaceRequestErByRequestId(String requestId);

    MaceReqConsult getMaceReqConsultByRefNo(@Param("referenceNo") String referenceNo);

    void updateMaceReqConsult(@Param("mrc") MaceReqConsult mrc);

    MaceRequestOpDiag getMaceReqOpDiagByTestTransactionId(@Param("transId") Integer transId);

    List<MaceRequestOpTest> getMaceReqOpTestByReqDiagId(@Param("reqdiagId") Integer reqdiagId);

    MaceRequestOpProcedure getMaceReqOpProcByReqDiagId(@Param("reqDiagId") Integer reqDiagId);

    List<String> getAppUsersByDeviceId(@Param("deviceId") String deviceId);

    InvalidBdaySearch getLastInvalidAttempt(@Param("memberCode") String memberCode);

    void deleteLogInvalidSearch(@Param("memberCode") String memberCode);

    void logInvalidSearch(@Param("ibs") InvalidBdaySearch ibs);

    MaceRequestOpTest getMaceReqOpTestByTestTransId(@Param("transId") Integer transactionId);

    MaceRequestOpProcedure getMaceReqOpProcedureByProcedureTransId(@Param("transId") Integer integer);

    List<MaceRequest> getAvailedMaceRequestsByDocCodeAndMemCode(@Param("docCode")String doctorCode, @Param("memCode") String memberCode);

    void cancelMaceReqByRequestCode(@Param("requestCode") String requestCode);

    List<MaceRequestTest> getMaceRequestTestsMrIdAndDiagCode(@Param("requestId")Integer requestId, @Param("diagCode") String diagCode);

    List<MaceRequestOpTest> getMaceReqOpTestsByMaceReqIdAndApprovalNo(@Param("requestId") Integer requestId, @Param("approvalNo") String approvalNo);

    boolean checkForDuplicateConsult(@Param("cj") ConsultJson consultJson);

    boolean checkForDuplicateBasicTest(@Param("bootrj") BasicOrOtherTestRequestJson bootrj, @Param("procCodes") List<String> procCodes);

    boolean checkForDuplicateTest(@Param("bootrj") BasicOrOtherTestRequestJson bootrj, @Param("procCodes") List<String> procCodes);

    boolean checkForDuplicateProc(@Param("bootrj") BasicOrOtherTestRequestJson bootrj, @Param("procCodes") List<String> procCodes);

    List<MaceAnnouncements> getAnnouncements();

    List<MaceRequestOpDiag> getMaceReqOpDiagByTransId(@Param("transId") long transactionId);

    List<LastUpdatesCoor> getLastUpdatesOnTables();

    List<MaceRequest> getRecentTransactionHospCode(@Param("hospCode") String hospCode, @Param("memberCode") String memberCode);

    String getReferenceNoByReqId(@Param("reqId") Integer requestId);

    List<String> getApprovalNosByReqId(@Param("reqId") Integer requestId);

    List<MaceFaqs> getMaceFaqs();

    List<MaceRequest> getMemberFilteredLoaList(@Param("loaFilter") MemberLoaFilter loaFilter);

    AppUser getAppUserByDoctorCode(@Param("doctorCode") String doctorCode);

    List<MaceRequest> getMaceRequestsByRequestCodes(@Param("requestCodes") List<String> requestCodes,
                                                    @Param("serviceTypeId") int serviceTypeId);

    void addClaim(@Param("claim") MaceClaims claim);

    void deleteAllSubrequestData(@Param("parRequestId") Integer parRequestId);

    void saveAdditionIPHeader(@Param("iaj") MaceInpatientJson iaj);

    List<MaceIPRequestLogs> getIPLogsByMemCode(@Param("memCode") String memCode);

    MaceReqInpatient getMaceReqInpatientByReqIdTransId(@Param("maceReqId") int maceRequestId, @Param("transId") int transactionId);

    List<MaceReqIpDoctor> getMaceReqIpDoctorsByReqIdTransId(@Param("requestId") Integer requestId, @Param("transactionId") Integer transactionId);

    List<MaceReqIpDiag> getMaceReqIpDiagByReqIdTransId(@Param("requestId") Integer requestId, @Param("transactionId") Integer transactionId);

    List<MaceReqIpDiagproc> getMaceReqIpDiagProcsByReqIdTransId(@Param("requestId") Integer requestId, @Param("transactionId") Integer transactionId);

    List<MaceReqInpatient> getMaceReqInpatientsByReqId(@Param("requestId") Integer requestId);

    List<MaceReqIpRoom> getMaceReqIpRooms(@Param("requestId") Integer requestId);

    List<MaceConsPrescribedtest> getMaceConsPrescribedTestsByReqId(@Param("requestId") Integer requestId);

    void requestForDischargeIP(@Param("requestId") int maceRequestId);

    List<MaceRequestAttachment> getMaceAttachmentsByReqCodeTransId(@Param("requestCode") String requestCode, @Param("transactionId") Integer transactionId);

    void insertMaceInpatientAudit(@Param("mia") MaceInpatientAudit mia);

    List<MaceInpatientAudit> getIPAuditLogs(@Param("memCode") String memCode);
}
