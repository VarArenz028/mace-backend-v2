package com.basicauth.mapper.mace;

import com.basicauth.data.*;
import com.basicauth.domain.TestProcObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by service.incuventure on 12/29/2016.
 */
public interface DiagProcMapper {

    List<Diagnosis> getDiagnosisList();

    List<Diagnosis> getAllDiagnosisList();

//    List getDiagnosticProceduresList();

    Diagnosis getDiagnosisByDiagnosisCode(@Param("diagCode") String diagCode);

    ProcedureJson getProcedureByProcedureCode(@Param("code") String code);

    Procedure getProcedureByProcedureCodev2(@Param("code") String code);

    Boolean checkIfDiagCodeAndProcedureCodeMatches(@Param("diagCode") String diagCode, @Param("procedureCode") String procedureCode);

    List<TestAndProceduresEntity> getDiagTestsProceduresList();

    List<TestAndProceduresEntity> getTestsOnlyList();

    DiagnosisClinicProceduresEntity getDiagnosisClinicProcedureEntity(@Param("diagCode") String diagnosisCode, @Param("procCode") String procedureCode);

    List<DiagnosisProceduresViewEntity> getBasicTests();

    List<DiagnosisProceduresViewEntity> getOtherTests();

    List getAllProcedures();

    DiagnosticProceduresEntity getDiagProcedureByProcedureCode(@Param("procCode") String procCode, @Param("dxCode") String dxCode);

    List getAllTests();

    List<String> getTestsProceduresCodesByDiagnosisCode(@Param("diagCode") String diagCode);

    List<String> getAllTestProceduresCodes();

    List<String> getProceduresByDiagnosisCode(@Param("diagCode") String diagCode);

    List<String> getTestsOnlyByDiagnosisCode();

    List<String> getProceduresOnlyByDiagnosisCode(@Param("diagCode") String diagCode);

    TestAndProceduresEntity getDiagTestProceduresByCodes(@Param("procCode") String procedureCode, @Param("diagCode") String diagnosisCode);

    TestProcObject getTestProcObject(@Param("procCode") String procCode);

    BigDecimal getCostOfTestProc(@Param("procCode") String procedureCode);

    List<String> getTestsByDiagnosisCode(@Param("diagCode") String diagCode);

    List<String> getOtherTestsNotByDiagCode(@Param("diagCode") String diagCode);

    String getCostCenterByMRTTransactionId(@Param("transId") long transactionId);

    String getCostCenterByMRPTransactionId(@Param("transId") long transactionId);

    List getAllDiagnosisListByLastUpdateDate(@Param("date") Date date);

    List<TestProcObject> getAllTestProceduresListByLastUpdateDate(@Param("date") Date date);


    //paginated testProc
    List<TestProcObject> getTestProceduresListPaginated(@Param("count") int count,
                                                        @Param("offset") int offset,
                                                        @Param("searchString") String searchString,
                                                        @Param("serviceSubType") int serviceSubType);
    //paginated diag
    List getDiagnosisListPaginated(@Param("count")int count,
                                   @Param("offset")int offset,
                                   @Param("searchString")String searchString);

    List<DiagnosisProceduresViewEntity> getDiagnosisProcedureList();

    List<String> getAllProcedureCodesByDiagnosisCode(@Param("diagCode") String diagCode);
    List<String> getAllProceduresCodes();

    CustHospProcAmount getCustHospProcAmount(@Param("procCode") String procCode, @Param("hospCode") String hospitalCode);

    List<TestAndProceduresEntity> getDiagTestsProceduresListByHospCode(@Param("hospCode") String hospCode, @Param("date") String date);


    TempDiagModel findTempDiag(@Param("diagCode") String diagCode);

    void addTempDiag(@Param("td") TempDiagModel tempDiag);

    TempProcModel findTempProc(@Param("procName") String procName);

    void addTempProcedure(@Param("tpm") TempProcModel tpm);
}
