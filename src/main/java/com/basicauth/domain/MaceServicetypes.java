package com.basicauth.domain;

import com.basicauth.data.MaceRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A MaceServicetypes.
 */
@Entity
@Table(name = "mace_servicetypes")
public class MaceServicetypes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @JsonProperty("serviceTypeId")
    @Column(name = "servicetype_id")
    private Integer serviceTypeId;

    @Column(name = "servicetype_code")
    private String servicetypeCode;

    @Column(name = "service_desc")
    private String serviceDesc;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "lastupdate_on")
    private Date lastupdateOn;

    @Column(name = "lastupdate_by")
    private String lastupdateBy;

    @ManyToOne
    private MaceRequest maceRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServicetypeCode() {
        return servicetypeCode;
    }

    public MaceServicetypes servicetypeCode(String servicetypeCode) {
        this.servicetypeCode = servicetypeCode;
        return this;
    }

    public void setServicetypeCode(String servicetypeCode) {
        this.servicetypeCode = servicetypeCode;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public MaceServicetypes serviceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
        return this;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public Boolean isActive() {
        return active;
    }

    public MaceServicetypes active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getLastupdateOn() {
        return lastupdateOn;
    }

    public MaceServicetypes lastupdateOn(Date lastupdateOn) {
        this.lastupdateOn = lastupdateOn;
        return this;
    }

    public void setLastupdateOn(Date lastupdateOn) {
        this.lastupdateOn = lastupdateOn;
    }

    public String getLastupdateBy() {
        return lastupdateBy;
    }

    public MaceServicetypes lastupdateBy(String lastupdateBy) {
        this.lastupdateBy = lastupdateBy;
        return this;
    }

    public void setLastupdateBy(String lastupdateBy) {
        this.lastupdateBy = lastupdateBy;
    }

    public MaceRequest getMaceRequest() {
        return maceRequest;
    }

    public MaceServicetypes maceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
        return this;
    }

    public void setMaceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MaceServicetypes maceServicetypes = (MaceServicetypes) o;
        if (maceServicetypes.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, maceServicetypes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MaceServicetypes{" +
            "id=" + id +
            ", servicetypeId='" + serviceTypeId + "'" +
            ", servicetypeCode='" + servicetypeCode + "'" +
            ", serviceDesc='" + serviceDesc + "'" +
            ", active='" + active + "'" +
            ", lastupdateOn='" + lastupdateOn + "'" +
            ", lastupdateBy='" + lastupdateBy + "'" +
            '}';
    }
}
