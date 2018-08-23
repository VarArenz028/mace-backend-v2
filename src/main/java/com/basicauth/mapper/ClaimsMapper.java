package com.basicauth.mapper;

import com.basicauth.data.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by igan_long on 10/6/2016.
 */
public interface ClaimsMapper {

    List getMemberUtilization(@Param("@MemCode") String memberCode);

    Double getRemainingLimit(@Param("memberCode") String memberCode);

    List getRoomPlans();

    Dentist getDentist(@Param("dentistCode") String dentistCode);
}
