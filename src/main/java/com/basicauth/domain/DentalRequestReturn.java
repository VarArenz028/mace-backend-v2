package com.basicauth.domain;

import com.basicauth.data.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class DentalRequestReturn implements Serializable {

    @JsonProperty("maceRequest")
    private MaceRequest maceRequest;
    @JsonProperty("maceReqTest")
    private MaceRequestTest maceReqTest;
    @JsonProperty("groupedByDiagList")
    private List<GroupedByDiag> groupedByDiagList;
    @JsonProperty("maceReqOpOtherInfoList")
    private MaceRequestOpOtherInformation[] maceReqOpOtherInfoList;


    //<editor-fold desc="Getters and Setters" defaultstate="collapsed">
    public MaceRequest getMaceRequest() {
        return maceRequest;
    }

    public void setMaceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
    }

    public MaceRequestTest getMaceReqTest() {
        return maceReqTest;
    }

    public void setMaceReqTest(MaceRequestTest maceReqTest) {
        this.maceReqTest = maceReqTest;
    }

    public List<GroupedByDiag> getGroupedByDiagList() {
        return groupedByDiagList;
    }

    public void setGroupedByDiagList(List<GroupedByDiag> groupedByDiagList) {
        this.groupedByDiagList = groupedByDiagList;
    }

    public MaceRequestOpOtherInformation[] getMaceReqOpOtherInfoList() {
        return maceReqOpOtherInfoList;
    }

    public void setMaceReqOpOtherInfoList(MaceRequestOpOtherInformation[] maceReqOpOtherInfoList) {
        this.maceReqOpOtherInfoList = maceReqOpOtherInfoList;
    }

    //</editor-fold>

    public static class GroupedByDiag implements Serializable {

        @JsonProperty("maceReqOpDiag")
        private MaceRequestOpDiag maceReqOpDiag;
        @JsonProperty("maceReqOpTests")
        private List<MaceRequestOpTest> maceReqOpTests;

        //<editor-fold desc="Getters and Setters" defaultstate="collapsed">

        public MaceRequestOpDiag getMaceReqOpDiag() {
            return maceReqOpDiag;
        }

        public void setMaceReqOpDiag(MaceRequestOpDiag maceReqOpDiag) {
            this.maceReqOpDiag = maceReqOpDiag;
        }

        public List<MaceRequestOpTest> getMaceReqOpTests() {
            return maceReqOpTests;
        }

        public void setMaceReqOpTests(List<MaceRequestOpTest> maceReqOpTests) {
            this.maceReqOpTests = maceReqOpTests;
        }


        //</editor-fold>

    }
}
