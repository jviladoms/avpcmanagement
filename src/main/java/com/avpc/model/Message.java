package com.avpc.model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Jordi on 12/11/2016.
 */
@Entity
public class Message {
    @Id
    @GeneratedValue
    @Column(name="message_id")
    private Long id;

    @Column(nullable = false)
    private String message;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MESSAGE_MEMBERS", joinColumns = { @JoinColumn(name = "message_id") }, inverseJoinColumns = { @JoinColumn(name = "member_id") })
    private List<Member> destination_members;

    @OneToOne(cascade = CascadeType.ALL)
    private Member send_member;

    @Column(nullable=false)
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

    public List<Member> getDestination_members() {
        return destination_members;
    }

    public void setDestination_members(List<Member> destination_members) {
        this.destination_members = destination_members;
    }

    public Member getSend_member() {
        return send_member;
    }

    public void setSend_member(Member send_member) {
        this.send_member = send_member;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
