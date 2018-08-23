package com.basicauth.data;

import com.basicauth.domain.MaceRequestConsObj;
import com.basicauth.domain.MaceRequestProcObj;
import com.basicauth.domain.MaceRequestTestObj;

import java.util.List;

/**
 * Created by Jabito on 02/10/2017.
 */
public class MaceInsertOrder {

    private MaceRequest mr;
    private MaceRequestConsObj mrcObj;
    private List<MaceRequestTestObj> mrtObjs;
    private List<MaceRequestProcObj> mrpObjs;
    private List<MaceRequestOpDoctor> mrdObjs;
    private List<MaceRequestOpRoom> mrRoomObjs;
    private List<MaceRequestOpOtherCharge> mrOsObjs;
    private List<MaceRequestOpOtherInformation> mrOiObjs;

    public MaceRequest getMr() {
        return mr;
    }

    public void setMr(MaceRequest mr) {
        this.mr = mr;
    }

    public MaceRequestConsObj getMrcObj() {
        return mrcObj;
    }

    public void setMrcObj(MaceRequestConsObj mrcObj) {
        this.mrcObj = mrcObj;
    }

    public List<MaceRequestTestObj> getMrtObjs() {
        return mrtObjs;
    }

    public void setMrtObjs(List<MaceRequestTestObj> mrtObjs) {
        this.mrtObjs = mrtObjs;
    }

    public List<MaceRequestProcObj> getMrpObjs() {
        return mrpObjs;
    }

    public void setMrpObjs(List<MaceRequestProcObj> mrpObjs) {
        this.mrpObjs = mrpObjs;
    }

    public List<MaceRequestOpDoctor> getMrdObjs() {
        return mrdObjs;
    }

    public void setMrdObjs(List<MaceRequestOpDoctor> mrdObjs) {
        this.mrdObjs = mrdObjs;
    }

    public List<MaceRequestOpRoom> getMrRoomObjs() {
        return mrRoomObjs;
    }

    public void setMrRoomObjs(List<MaceRequestOpRoom> mrRoomObjs) {
        this.mrRoomObjs = mrRoomObjs;
    }

    public List<MaceRequestOpOtherCharge> getMrOsObjs() {
        return mrOsObjs;
    }

    public void setMrOsObjs(List<MaceRequestOpOtherCharge> mrOsObjs) {
        this.mrOsObjs = mrOsObjs;
    }

    public List<MaceRequestOpOtherInformation> getMrOiObjs() {
        return mrOiObjs;
    }

    public void setMrOiObjs(List<MaceRequestOpOtherInformation> mrOiObjs) {
        this.mrOiObjs = mrOiObjs;
    }
}
