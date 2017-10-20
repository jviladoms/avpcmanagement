package com.avpc.services;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.dto.MemberDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private static final Logger log = Logger.getLogger(MemberService.class);

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Member> findMembers(){
        List<Member> listMembers = new ArrayList<>();

        try{
            memberDAO.findAll().forEach(member -> listMembers.add(member));
            listMembers.forEach(member -> member.setServices(serviceDAO.findByMembersInServiceIn(member).size()));
        } catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("member not found");
        }

        return listMembers;
    }

    public Member addMember(MemberDTO memberParams){
        Member member = new Member();

        member.setName(memberParams.getName());
        member.setTip(memberParams.getTip());
        member.setAddress(memberParams.getAddress());
        member.setBirthDate(memberParams.getBirthDate());
        member.setAddress(memberParams.getAddress());
        member.setCity(memberParams.getCity());
        member.setEmail(memberParams.getEmail());
        member.setLandPhoneNumber(memberParams.getLandPhoneNumber());
        member.setMobilePhoneNumber(memberParams.getMobilePhoneNumber());
        member.setPostalCode(memberParams.getPostalCode());
        member.setSurname1(memberParams.getSurname1());
        member.setSurname2(memberParams.getSurname2());
        member.setUserGroup(memberParams.getUserGroup());
        member.setDni(memberParams.getDni());
        member.setRole(memberParams.getRole());
        member.setPassword(passwordEncoder.encode("hola"));

        return memberDAO.save(member);
    }

    public Member addMember(Member member){
        return memberDAO.save(member);
    }

    public Member findMember(Long memberId){
        Member member;
        member = memberDAO.findOne(memberId);
        member.setServices(serviceDAO.findByMembersInServiceIn(member).size());

        return member;
    }

    public Member updateMember(MemberDTO memberParams, Long memberId){

        Member member;
        member = memberDAO.findOne(memberId);

        member.setName(memberParams.getName());
        member.setTip(memberParams.getTip());
        member.setSurname1(memberParams.getSurname1());
        member.setSurname2(memberParams.getSurname2());
        member.setAddress(memberParams.getAddress());
        member.setBirthDate(memberParams.getBirthDate());
        member.setAddress(memberParams.getAddress());
        member.setCity(memberParams.getCity());
        member.setEmail(memberParams.getEmail());
        member.setLandPhoneNumber(memberParams.getLandPhoneNumber());
        member.setMobilePhoneNumber(memberParams.getMobilePhoneNumber());
        member.setPostalCode(memberParams.getPostalCode());
        member.setUserGroup(memberParams.getUserGroup());
        member.setDni(memberParams.getDni());
        member.setRole(memberParams.getRole());

        memberDAO.save(member);

        return member;
    }

    public void updatePassword(Long memberId, String oldpassword, String newPassword){
        Member member;
        member = memberDAO.findOne(memberId);

        if(passwordEncoder.matches(oldpassword,member.getPassword())){
            member.setPassword(passwordEncoder.encode(newPassword));
            memberDAO.save(member);
        } else {
            throw new RuntimeException();
        }
    }


    public Member updateMemberLocation(Double longitude, Double latitude, Long memberId){
        Member member;
        member = memberDAO.findOne(memberId);

        member.setLongitude(longitude);
        member.setLatitude(latitude);

        return memberDAO.save(member);
    }

    public Member updateMemberAvailability(Boolean availability, Long memberId){
        Member member;
        member = memberDAO.findOne(memberId);

        member.setAvailability(availability);

        return memberDAO.save(member);
    }

    public void deleteMember(Long memberId){
        memberDAO.delete(memberId);
    }

    public Member findMemberByDni(String dni){
        return memberDAO.findByDni(dni);
    }
}
