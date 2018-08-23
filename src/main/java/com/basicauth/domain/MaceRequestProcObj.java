package com.basicauth.domain;

import com.basicauth.data.MaceRequestApproval;
import com.basicauth.data.MaceRequestOpDiag;
import com.basicauth.data.MaceRequestOpProcedure;
import com.basicauth.data.MaceRequestProcedure;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jabito on 23/10/2017.
 */
public class MaceRequestProcObj implements Serializable{
    private MaceRequestProcedure mrp;
    private MaceRequestOpDiagObj mrodObjs;
    private List<MaceRequestApproval> mraObjs;

    public MaceRequestProcedure getMrp() {
        return mrp;
    }

    public void setMrp(MaceRequestProcedure mrp) {
        this.mrp = mrp;
    }

    public MaceRequestOpDiagObj getMrodObjs() {
        return mrodObjs;
    }

    public void setMrodObjs(MaceRequestOpDiagObj mrodObjs) {
        this.mrodObjs = mrodObjs;
    }

    public List<MaceRequestApproval> getMraObjs() {
        return mraObjs;
    }

    public void setOrAppendMraObjs(List<MaceRequestApproval> mraObjs) {
        if(this.mraObjs == null){
            this.mraObjs = mraObjs;
        }
        else{
            this.mraObjs.addAll(mraObjs);
        }
    }

    public static class MaceRequestOpDiagObj {
        private MaceRequestOpDiag mrod;
        private List<MaceRequestOpProcedure> mrops;

        public MaceRequestOpDiag getMrod() {
            return mrod;
        }

        public void setMrod(MaceRequestOpDiag mrod) {
            this.mrod = mrod;
        }

        public List<MaceRequestOpProcedure> getMrops() {
            return mrops;
        }

        public void setMrops(List<MaceRequestOpProcedure> mrops) {
            this.mrops = mrops;
        }
    }
}
