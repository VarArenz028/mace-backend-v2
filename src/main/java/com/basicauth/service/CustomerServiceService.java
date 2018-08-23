package com.basicauth.service;

import com.basicauth.data.CustomerCare;
import com.basicauth.data.MemberDetails;
import com.basicauth.mapper.mace.CustomerServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by service.incuventure on 12/29/2016.
 */
@Service("customerServiceService")
public class CustomerServiceService {

    private static final Logger logger = LoggerFactory.getLogger(MaceService.class);

    @Autowired
    private CustomerServiceMapper customerServiceMapper;

    public CustomerCare saveTransaction(CustomerCare c, MemberDetails memberDetails) {
        logger.info("saveTransaction",memberDetails);
        logger.info("saveTransactionCustomerCare",c);
        customerServiceMapper.saveTransactionCustomerCare(c);
        logger.info("saveTransactionCustomerCareApproval",c);
        customerServiceMapper.saveTransactionCustomerCareApproval(c);

        //02/28 - GMeneses - Call service only if the type is 0 or 2
        if (c.getType() == 0 || c.getType() ==2) {
            logger.info("saveTransactionCustomerCarePhysician", c);
            customerServiceMapper.saveTransactionCustomerCarePhysician(c);
        }
        try {
            customerServiceMapper.callHistoryLog(c.getBatchCode(), c.getNotes(), 0, c.getUpdatedBy());
        }catch(Exception e){}

        return c;
    }

    public void saveTransactionForCall(CustomerCare c) {
        logger.info("saveTransactionCustomerCare",c);
        customerServiceMapper.saveTransactionCustomerCare(c);
        customerServiceMapper.saveTransactionCustomerCareApproval(c);
        try {
            customerServiceMapper.callHistoryLog(c.getBatchCode(), c.getNotes(), 0, c.getUpdatedBy());
        }catch(Exception e){}
    }

    public void callHistoryLog(String status, CustomerCare loa, int type) {
        logger.info("callHistoryLog");
        try {
            customerServiceMapper.callHistoryLog(loa.getBatchCode(), status + " " + loa.getRemarks() + " " + loa.getNotes(), type, loa.getUpdatedBy());
        }catch(Exception e){}
    }
}
