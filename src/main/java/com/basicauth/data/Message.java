package com.basicauth.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by service.incuventure on 12/29/2016.
 */
@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    @Column(name = "ID")
    private long id;

    @JsonProperty("messageType")
    @Column(name = "MESSAGE_TYPE")
    private String messageType;

    @JsonProperty("message")
    @Column(name = "MESSAGE")
    private String message;


    public Message() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }


}
