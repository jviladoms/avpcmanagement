package com.avpc.restfulcontrollers.dto;

import com.avpc.model.Member;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Jordi on 29/10/2016.
 */
public class ServiceDTO implements Serializable {

    private Long id;

    private Date startDate;

    private Date finalDate;

    private String comments;

    private String typeOfService;

    private String localization;

    private String serviceDescription;

    private String material;

    private Iterable<Long> membersInService;

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

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Iterable<Long> getMembersInService() {
        return membersInService;
    }

    public void setMembersInService(Iterable<Long> membersInService) {
        this.membersInService = membersInService;
    }
}