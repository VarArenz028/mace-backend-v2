package com.basicauth.service;

import com.basicauth.data.Doctor;
import com.basicauth.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("doctorService")
public class DoctorService
{

    @Autowired
    private DoctorMapper doctorMapper;

    public List<Doctor> getDoctorType()
    {
        return doctorMapper.getDoctorType();
    }

    public void delete(Integer docTypeId)
    {
        doctorMapper.delete(docTypeId);
    }

}
