package com.avpc.restfulcontrollers.dto;

import com.avpc.model.Member;

import java.util.Date;
import java.util.List;

/**
 * Created by Jordi on 29/10/2016.
 */
public class ServiceDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private String comments;
    private Iterable<Long> members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Iterable<Long> getMembers() {
        return members;
    }

    public void setMembers(Iterable<Long> members) {
        this.members = members;
    }
}
