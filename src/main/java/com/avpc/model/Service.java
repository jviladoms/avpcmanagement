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
    @Column(name = "service_id")
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
    @JoinTable(name = "SERVICE_MEMBERS", joinColumns = { @JoinColumn(name = "service_id") }, inverseJoinColumns = { @JoinColumn(name = "member_id") })
    private List<Member> members_in_service;

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

    public List<Member> getMembers_in_service() {
        return members_in_service;
    }

    public void setMembers_in_service(List<Member> members_in_service) {
        this.members_in_service = members_in_service;
    }
}
