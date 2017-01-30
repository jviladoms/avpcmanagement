package com.avpc.restfulcontrollers.dto;

import com.avpc.model.Member;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Jordi on 13/11/2016.
 */
public class MessageDTO implements Serializable {

    private Long id;
    private String message;
    private List<Long> destinationMembers;
    private Long sendMember;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Long> getDestinationMembers() {
        return destinationMembers;
    }

    public void setDestinationMembers(List<Long> destinationMembers) {
        this.destinationMembers = destinationMembers;
    }

    public Long getSendMember() {
        return sendMember;
    }

    public void setSendMember(Long sendMember) {
        this.sendMember = sendMember;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
