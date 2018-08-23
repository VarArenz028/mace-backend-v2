package com.basicauth.data;

import com.basicauth.domain.MaceReqConsult;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jjosephmagculang on 4/5/2018.
 */
public class PrescribedTestReturn implements Serializable {

    @JsonProperty("maceRequest")
    private MaceRequest maceRequest;

    @JsonProperty("groupedByCostCenter")
    private List<GroupedByCostCenter> groupedByCostCenter;

    @JsonProperty("maceReqConsult")
    private MaceReqConsult maceReqConsult;

    @JsonProperty("hospital")
    private Hospital hospital;

    @JsonProperty("doctor")
    private Doctor doctor;

    @JsonProperty("requestSubType")
    private String requestSubType;


    public String getRequestSubType() {
        return requestSubType;
    }

    public void setRequestSubType(String requestSubType) {
        this.requestSubType = requestSubType;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public MaceRequest getMaceRequest() {
        return maceRequest;
    }

    public void setMaceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
    }

    public List<GroupedByCostCenter> getGroupedByCostCenter() {
        return groupedByCostCenter;
    }

    public void setGroupedByCostCenter(List<GroupedByCostCenter> groupedByCostCenter) {
        this.groupedByCostCenter = groupedByCostCenter;
    }

    public MaceReqConsult getMaceReqConsult() {
        return maceReqConsult;
    }

    public void setMaceReqConsult(MaceReqConsult maceReqConsult) {
        this.maceReqConsult = maceReqConsult;
    }

    public static class GroupedByCostCenter {
        @JsonProperty("diagDesc")
        private String diagDesc;

        @JsonProperty("diagnosisId")
        private int diagnosisId;

        @JsonProperty("diagType")
        private int diagType;

        @JsonProperty("icd10Desc")
        private String icd10Desc;

        @JsonProperty("mappedTest")
        private List<MappedTest> mappedTest;

        public String getIcd10Desc() {
            return icd10Desc;
        }

        public void setIcd10Desc(String icd10Desc) {
            this.icd10Desc = icd10Desc;
        }

        public int getDiagnosisId() {
            return diagnosisId;
        }

        public void setDiagnosisId(int diagnosisId) {
            this.diagnosisId = diagnosisId;
        }

        public int getDiagType() {
            return diagType;
        }

        public void setDiagType(int diagType) {
            this.diagType = diagType;
        }

        public String getDiagDesc() {
            return diagDesc;
        }

        public void setDiagDesc(String diagDesc) {
            this.diagDesc = diagDesc;
        }

        public List<MappedTest> getMappedTest() {
            return mappedTest;
        }

        public void setMappedTest(List<MappedTest> mappedTest) {
            this.mappedTest = mappedTest;
        }


        public static class MappedTest {

            @JsonProperty("procedureId")
            private Integer procedureId;

            @JsonProperty("procedureDesc")
            private String procedureDesc;

            @JsonProperty("procedureName")
            private String procedureName;

            @JsonProperty("serviceType")
            private String serviceType;

            @JsonProperty("costCenter")
            private String costCenter;

            @JsonProperty("amount")
            private BigDecimal amount;

            public String getProcedureName() {
                return procedureName;
            }

            public void setProcedureName(String procedureName) {
                this.procedureName = procedureName;
            }

            public String getServiceType() {
                return serviceType;
            }

            public void setServiceType(String serviceType) {
                this.serviceType = serviceType;
            }

            public String getCostCenter() {
                return costCenter;
            }

            public void setCostCenter(String costCenter) {
                this.costCenter = costCenter;
            }

            public Integer getProcedureId() {
                return procedureId;
            }

            public void setProcedureId(Integer procedureId) {
                this.procedureId = procedureId;
            }

            public String getProcedureDesc() {
                return procedureDesc;
            }

            public void setProcedureDesc(String procedureDesc) {
                this.procedureDesc = procedureDesc;
            }

            public BigDecimal getAmount() {
                return amount;
            }

            public void setAmount(BigDecimal amount) {
                this.amount = amount;
            }
        }
    }
}
