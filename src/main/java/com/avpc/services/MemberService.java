package com.avpc.services;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.dto.MemberDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

        return memberDAO.save(member);
    }

    public Member updateMemberLocation(MemberDTO memberParams, Long memberId){
        Member member;
        member = memberDAO.findOne(memberId);

        member.setLongitude(memberParams.getLongitude());
        member.setLatitude(memberParams.getLatitude());

        return memberDAO.save(member);
    }

    public Member updateMemberAvailability(MemberDTO memberParams, Long memberId){
        Member member;
        member = memberDAO.findOne(memberId);

        member.setAvailability(memberParams.getAvailability());

        return memberDAO.save(member);
    }

    public void deleteMember(Long memberId){
        memberDAO.delete(memberId);
    }
}
