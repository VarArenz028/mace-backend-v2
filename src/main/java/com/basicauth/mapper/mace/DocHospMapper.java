package com.basicauth.mapper.mace;

import com.basicauth.data.*;
import com.basicauth.domain.HospitalProcedureAmountView;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by service.incuventure on 12/29/2016.
 */
public interface DocHospMapper {

    List getHospitals();

    List getDoctors();

    List getDoctorsToHospital(@Param("hospitalCode") String hospitalCode);

    List getAllDoctorsToHospital();

    Hospital getHospital(@Param("hospitalCode") String hospitalCode);//

    Doctor getDoctor(@Param("doctorCode") String doctorCode, @Param("getUnaccreditedDoctors") Boolean getUnaccreditedDoctors);

    List getRegions();

    List getProvinces();

    List getCities();

    List getSpecializations();

    List getDoctorToHospital(@Param("hospitalCode") String hospitalCode, @Param("doctorCode") String doctorCode);

    //Do not get unaccredited doctors if request will come from apps or the request is consultation (only accredited doctors may perform consultations)
    //However, unaccredited doctors may be retrieved otherwise as long as the request came from approval portal
    DoctorToHospital getDoctorToHospitalObject(@Param("hospitalCode") String hospitalCode, @Param("doctorCode") String doctorCode, @Param("getUnaccreditedDoctors") Boolean getUnaccreditedDoctors);

    List getHospitalOfDoctors(@Param("doctorCode") String doctorCode);//

    Integer getDoctorCount();

    String getCostCenterByHospitalCode(@Param("hospitalCode") String hospitalCode);

    List getAllDoctorsToHospitalByName(@Param("partialDoctorName") String partialDoctorName, @Param("max") Integer max);

    HospitalProcedureAmountView getHospProcAmount(@Param("hospitalCode") String hospitalCode, @Param("procedureCode") String procedureCode);

    List<HospitalProcedureAmountView> getHospProcListByHospital(@Param("hospitalCode") String hospitalCode);

    Double getProcAmountByHospitalCode(@Param("hospitalCode") String hospitalCode);



    //by last update
    List getHospitalsByLastUpdateDate(@Param("date") Date date);

    List getDoctorsByLastUpdateDate(@Param("date") Date date);

    List getDoctorsHospitalListByLastUpdateDate(@Param("hospCode")String hospitalCode,@Param("lastUpdateDate")Date date);

    List getDoctorsToHospitalV2(@Param("hospCode") String hospitalCode, @Param("lastUpdateDate") String lastUpdateDate);

    List<Dentist> getDentistList();

    List<Dentist> getDentistListPaginated(@Param("count")int count,
                                         @Param("offset")int offset,
                                         @Param("searchString")String searchString);

    List getDoctorsToHospitalPaginated(@Param("hospitalCode") String hospitalCode,
                                       @Param("count")int count,
                                       @Param("offset")int offset,
                                       @Param("searchString")String searchString,
                                       @Param("includeAccredited") boolean includeAccredited);

    List getHospitalsPaginated(@Param("count")int count,
                               @Param("offset")int offset,
                               @Param("searchString")String searchString);

    void addTempDoctor(@Param("tempDoc") TempDoctorModel tempDoctorModel);

    TempDoctorModel findTempDoc(@Param("docName") String docName);
}
