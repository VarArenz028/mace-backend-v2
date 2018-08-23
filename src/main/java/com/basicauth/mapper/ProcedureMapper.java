package com.basicauth.mapper;

import com.basicauth.domain.TestProcObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProcedureMapper {

    List<TestProcObject> getProcedure();

    void delete(@Param("procedureId") Integer procedureId);
}
