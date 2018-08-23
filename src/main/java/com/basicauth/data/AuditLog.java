package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by service.incuventure on 4/18/2017.
 */
@Entity
@Table(name = "Audit_Log")
public class AuditLog implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;

    @JsonProperty("loaMaceRequestsId")
    @Column(name = "LOA_MACE_REQUESTS_ID")
    private String loaMaceRequestsId;

    @JsonProperty("loggedOn")
    @Column(name="Logged_On")
    private Date loggedOn;

    @JsonProperty("loggedBy")
    @Column(name="Logged_By")
    private String loggedBy;

    @JsonProperty("description")
    @Column(name="description")
    private String description;

    @JsonProperty("status")
    @Column(name="status")
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoaMaceRequestsId() {
        return loaMaceRequestsId;
    }

    public void setLoaMaceRequestsId(String loaMaceRequestsId) {
        this.loaMaceRequestsId = loaMaceRequestsId;
    }

    public Date getLoggedOn() {
        return loggedOn;
    }

    public void setLoggedOn(Date loggedOn) {
        this.loggedOn = loggedOn;
    }

    public String getLoggedBy() {
        return loggedBy;
    }

    public void setLoggedBy(String loggedBy) {
        this.loggedBy = loggedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
