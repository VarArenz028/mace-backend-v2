package com.basicauth.service;

import com.basicauth.domain.TestProcObject;
import com.basicauth.mapper.ProcedureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("procedureService")
public class ProcedureService {
    @Autowired
    private ProcedureMapper myMapper;

    public List<TestProcObject> getProcedure() {
        return myMapper.getProcedure();
    }

    public void delete(Integer procedureId){
        this.myMapper.delete(procedureId);
    }
}
