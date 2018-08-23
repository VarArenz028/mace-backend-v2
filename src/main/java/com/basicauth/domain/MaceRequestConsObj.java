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
public class MaceRequestConsObj implements Serializable {
    private MaceReqConsult mrc;
    private List<MaceRequestApproval> mraObjs;
    private List<String> reasonMessages; //For use in Approval Portal Consultation

    public MaceReqConsult getMrc() {
        return mrc;
    }

    public void setMrc(MaceReqConsult mrc) {
        this.mrc = mrc;
    }

    public List<MaceRequestApproval> getMraObjs() {
        return mraObjs;
    }

    public void setMraObjs(List<MaceRequestApproval> mraObjs) {
        this.mraObjs = mraObjs;
    }

    public List<String> getReasonMessages() {
        return reasonMessages;
    }

    public void setReasonMessages(List<String> reasonMessages) {
        this.reasonMessages = reasonMessages;
    }
}
