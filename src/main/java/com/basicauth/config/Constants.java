package com.basicauth.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 7/10/2017.
 */
public class Constants {

    //Not part of the approval engine execution
    public static final String REQUEST_DRAFT = "DRAFT";

    public static final String REQUEST_AUTOMATIC_APPROVED = "APPROVED";
    public static final String REQUEST_AUTOMATIC_DISAPPROVED = "DISAPPROVED";
    public static final String REQUEST_MANUAL = "PENDING";
    public static final String REQUEST_BLOCKED = "BLOCKED";

    public static final String OPTHALMOLOGY_SPECIALIZATION_CODE = "000006";
    public static final String SLIT_LAMP_PROCEDURE_CODE = "MACE00006";

    public static final String CMG_GROUP = "CMG";
    public static final String HSC_GROUP = "HSC";
    public static final String SBD_GROUP = "SBD";
    public static final String RMD_GROUP = "RMD";
    public static final String URG_GROUP = "URG";
    public static final String MBAS_GROUP = "MBAS";
    public static final String DEFAULT_GROUP = "NONE";

    public enum RequestStatusPriority{
        APPROVED(REQUEST_AUTOMATIC_APPROVED, 0),
        MANUAL(REQUEST_MANUAL, 1),
        DISAPPROVED(REQUEST_AUTOMATIC_DISAPPROVED, 2),
        BLOCKED(REQUEST_BLOCKED, 3);

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        String statusName;
        Integer priority;

        RequestStatusPriority(String statusName, Integer priority) {
            this.statusName = statusName;
            this.priority = priority;
        }

        public static RequestStatusPriority getByStatusName(String statusName) {
            for (RequestStatusPriority s : values()) {
                if (s.statusName.equals(statusName) )return s;
            }
            return null;
        }

        public static List<String> getAllStatusNames(){
            List<String> returnList = new ArrayList<>();

            for (RequestStatusPriority s : values()) {
                returnList.add(s.getStatusName());
            }

            return returnList;
        }
    }

    public enum StatusAssigneePriority {
        DEFAULT(DEFAULT_GROUP, -1),
        RMD(RMD_GROUP, 0),
        URG(URG_GROUP, 0),
        SBD(SBD_GROUP, 0),
        CMG(CMG_GROUP, 1),
        HSC(HSC_GROUP, 1),
        MBAS(MBAS_GROUP, 2);

        public String getStatusAssignee() {
            return statusAssignee;
        }

        public void setStatusAssignee(String statusAssignee) {
            this.statusAssignee = statusAssignee;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        String statusAssignee;
        Integer priority;

        StatusAssigneePriority(String statusAssignee, Integer priority) {
            this.statusAssignee = statusAssignee;
            this.priority = priority;
        }

        public static StatusAssigneePriority getByStatusAssignee(String statusAssignee) {
            for (StatusAssigneePriority s : values()) {
                if (s.statusAssignee.equals(statusAssignee) )return s;
            }
            return null;
        }
    }

    public enum SubService {
        NONMAT(1L, "Non-Maternity Consult"),
        MAT(2L, "Maternity Consult"),
        BASIC(3L, "Basic Test"),
        OTHER(4L, "Other Test");


        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        String value;
        Long id;

        SubService(Long id, String value) {
            this.id = id;
            this.value = value;
        }

        public static SubService getById(Long id) {
            for (SubService s : values()) {
                if (s.id.equals(id) )return s;
            }
            return null;
        }
    }

}
