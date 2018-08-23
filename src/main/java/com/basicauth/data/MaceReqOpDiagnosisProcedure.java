package com.basicauth.data;

import java.io.Serializable;

/**
 * Created by Bryan on 5/10/2017.
 */
public class MaceReqOpDiagnosisProcedure implements Serializable {
    private MaceRequestOpDiag maceRequestOpDiag;
    private MaceRequestOpProcedure maceRequestOpProcedure;
    private MaceRequestProcedure maceRequestProcedure;

    public MaceReqOpDiagnosisProcedure(MaceRequestOpDiag maceRequestOpDiag, MaceRequestOpProcedure maceRequestOpProcedure, MaceRequestProcedure maceRequestProcedure) {
        this.maceRequestOpDiag = maceRequestOpDiag;
        this.maceRequestOpProcedure = maceRequestOpProcedure;
        this.maceRequestProcedure = maceRequestProcedure;
    }

    public MaceRequestOpDiag getMaceRequestOpDiag() {
        return maceRequestOpDiag;
    }

    public MaceRequestOpProcedure getMaceRequestOpProcedure() {
        return maceRequestOpProcedure;
    }

    public MaceRequestProcedure getMaceRequestProcedure() {
        return maceRequestProcedure;
    }
}
