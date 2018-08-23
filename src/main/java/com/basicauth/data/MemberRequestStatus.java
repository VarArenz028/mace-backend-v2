package com.basicauth.data;

/**
 * Created by jjosephmagculang on 5/8/2018.
 */
public class MemberRequestStatus {

    private String memberStatus;
    private String requestStatus;
    private Boolean isAllowed;


    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Boolean getAllowed() {
        return isAllowed;
    }

    public void setAllowed(Boolean allowed) {
        isAllowed = allowed;
    }
}
