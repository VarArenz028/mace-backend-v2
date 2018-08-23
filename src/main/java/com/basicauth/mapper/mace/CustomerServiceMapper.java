package com.basicauth.mapper.mace;

import com.basicauth.data.*;
import com.basicauth.domain.ImageHolder;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by service.incuventure on 12/29/2016.
 */
public interface CustomerServiceMapper {

    void saveTransactionCustomerCare(@Param("c") CustomerCare c);

    void saveTransactionCustomerCareApproval(@Param("c") CustomerCare c);

    void saveTransactionCustomerCarePhysician(@Param("c") CustomerCare c);

    void cancelLTBLByBatchCode(@Param("batchCode")String batchCode, @Param("user")String user, @Param("updatedBy") String updatedBy);

    void callHistoryLog(@Param("batchCode")String batchCode, @Param("notes")String notes, @Param("type")int type, @Param("updatedBy") String updatedBy);
}
