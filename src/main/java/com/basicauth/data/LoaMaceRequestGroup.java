package com.basicauth.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IPC_Server on 5/10/2017.
 */
public class LoaMaceRequestGroup implements Serializable {
    private int serviceType;
    private String diagCode;
    private String costCenter;
    private LoaMaceRequests loaMaceRequest;
    private List<MaceReqOpDiagnosisProcedure> maceReqOpDiagnosisProcedures;

    public LoaMaceRequestGroup(int serviceType, String diagCode, String costCenter, List<MaceReqOpDiagnosisProcedure> maceReqOpDiagnosisProcedures) {
        this.serviceType = serviceType;
        this.diagCode = diagCode;
        this.costCenter = costCenter;
        this.maceReqOpDiagnosisProcedures = maceReqOpDiagnosisProcedures;
    }

    public int getServiceType() {
        return serviceType;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public List<MaceReqOpDiagnosisProcedure> getMaceReqOpDiagnosisProcedures() {
        return maceReqOpDiagnosisProcedures;
    }

    public LoaMaceRequests getLoaMaceRequest() {
        return loaMaceRequest;
    }

    public void setLoaMaceRequest(LoaMaceRequests loaMaceRequest) {
        this.loaMaceRequest = loaMaceRequest;
    }
}
