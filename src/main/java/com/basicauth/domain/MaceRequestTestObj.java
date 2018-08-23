package com.basicauth.domain;

import com.basicauth.data.MaceRequestApproval;
import com.basicauth.data.MaceRequestOpDiag;
import com.basicauth.data.MaceRequestOpTest;
import com.basicauth.data.MaceRequestTest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jabito on 23/10/2017.
 */
public class MaceRequestTestObj implements Serializable {
    private MaceRequestTest mrt;
    private MaceRequestOpDiagObj mrodObj;

    public MaceRequestTest getMrt() {
        return mrt;
    }

    public void setMrt(MaceRequestTest mrt) {
        this.mrt = mrt;
    }

    public MaceRequestOpDiagObj getMrodObjs() {
        return mrodObj;
    }

    public void setMrodObjs(MaceRequestOpDiagObj mrodObj) {
        this.mrodObj = mrodObj;
    }

    public static class MaceRequestOpDiagObj {
        private MaceRequestOpDiag mrod;
        private List<MaceRequestOpTest> mrots;
        private List<MaceRequestApproval> mraObjs;

        public MaceRequestOpDiag getMrod() {
            return mrod;
        }

        public void setMrod(MaceRequestOpDiag mrod) {
            this.mrod = mrod;
        }

        public List<MaceRequestOpTest> getMrots() {
            return mrots;
        }

        public void setMrots(List<MaceRequestOpTest> mrots) {
            this.mrots = mrots;
        }

        public List<MaceRequestApproval> getMraObjs() {
            return mraObjs;
        }

        public void setMraObjs(List<MaceRequestApproval> mraObjs) {
            this.mraObjs = mraObjs;
        }
    }
}
