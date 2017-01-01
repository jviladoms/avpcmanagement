package com.avpc.restfulcontrollers.dto;

import com.avpc.model.Member;

import java.util.Date;
import java.util.List;

/**
 * Created by Jordi on 13/11/2016.
 */
public class MessageDTO {

    private Long id;
    private String message;
    private List<Long> destination_members;
    private Long send_member;
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

    public List<Long> getDestination_members() {
        return destination_members;
    }

    public void setDestination_members(List<Long> destination_members) {
        this.destination_members = destination_members;
    }

    public Long getSend_member() {
        return send_member;
    }

    public void setSend_member(Long send_member) {
        this.send_member = send_member;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
