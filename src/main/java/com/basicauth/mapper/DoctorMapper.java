package com.basicauth.mapper;

import com.basicauth.data.Doctor;

import java.util.List;

public interface DoctorMapper {

    List<Doctor> getDoctorType();

    void delete(Integer doctorCode);
}
