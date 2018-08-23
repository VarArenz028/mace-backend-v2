package com.basicauth.domain;

import com.basicauth.data.MaceRequestOpDiag;
import com.basicauth.data.MaceRequestOpProcedure;
import com.basicauth.data.MaceRequestOpTest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jabito on 03/06/2017.
 */
public class MaceRequestListsJson {

    List<MaceRequestOpDiag> diagnosisList;
    List<MaceRequestOpProcedure> proceduresList;
    List<MaceRequestOpTest> testList;

    public MaceRequestListsJson() {
    }

    public List<MaceRequestOpDiag> getDiagnosisList() {
        return diagnosisList;
    }

    public void setDiagnosisList(List<MaceRequestOpDiag> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    public List<MaceRequestOpProcedure> getProceduresList() {
        return proceduresList;
    }

    public void setProceduresList(List<MaceRequestOpProcedure> proceduresList) {
        this.proceduresList = proceduresList;
    }

    public List<MaceRequestOpTest> getTestList() {
        return testList;
    }

    public void setTestList(List<MaceRequestOpTest> testList) {
        this.testList = testList;
    }
}
