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
    @Column(name="messageId")
    private Long id;

    @Column(nullable = false)
    private String messageString;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "message_members", joinColumns = { @JoinColumn(name = "messageId") }, inverseJoinColumns = { @JoinColumn(name = "memberId") })
    private List<Member> destinationMembers;

    @ManyToOne(cascade = CascadeType.ALL)
    private Member sendMember;

    @Column(nullable=false)
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public List<Member> getDestinationMembers() {
        return destinationMembers;
    }

    public void setDestinationMembers(List<Member> destinationMembers) {
        this.destinationMembers = destinationMembers;
    }

    public Member getSendMember() {
        return sendMember;
    }

    public void setSendMember(Member sendMember) {
        this.sendMember = sendMember;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
