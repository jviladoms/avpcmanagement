package com.avpc.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Jordi on 29/10/2016.
 */
@Entity
public class Service {
    @Id
    @GeneratedValue
    @Column(name = "serviceId")
    private Long id;

    @Column(nullable=false)
    private Date startDate;

    @Column(nullable=true)
    private Date finalDate;

    @Column(nullable=true)
    private String comments;

    @Column(nullable=true)
    private String typeOfService;

    @Column(nullable=true)
    private String localization;

    @Column(nullable=true)
    private String serviceDescription;

    @Column(nullable=true)
    private String material;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SERVICE_MEMBERS", joinColumns = { @JoinColumn(name = "serviceId") }, inverseJoinColumns = { @JoinColumn(name = "memberId") })
    private List<Member> membersInService;

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

    public List<Member> getMembersInService() {
        return membersInService;
    }

    public void setMembersInService(List<Member> membersInService) {
        this.membersInService = membersInService;
    }
}
