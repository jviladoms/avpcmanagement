package com.avpc.restfulcontrollers;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import com.avpc.restfulcontrollers.dto.MemberDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordi on 29/10/2016.
 */
@RestController
public class MemberController {

    private static final Logger log = Logger.getLogger(MemberController.class);

    @Autowired
    private MemberDAO memberDAO;

    @RequestMapping(value ="/members", method = RequestMethod.POST)
    public String addMember(@RequestBody MemberDTO memberParams) {

        try{
            Member member = new Member();
            member.setName(memberParams.getName());
            member.setAddress(memberParams.getAddress());
            member.setBirthDate(memberParams.getBirthDate());
            member.setAddress(memberParams.getAddress());
            member.setCity(memberParams.getCity());
            member.setEmail(memberParams.getEmail());
            member.setLandphoneNumber(memberParams.getLandPhoneNumber());
            member.setMobilephoneNumber(memberParams.getLandPhoneNumber());
            member.setPostalCode(memberParams.getPostalCode());
            member.setSurname1(memberParams.getSurname1());
            member.setSurname2(memberParams.getSurname2());
            member.setVAT(memberParams.getVat());
            member.setLattitude(memberParams.getLattitude());
            member.setLongitud(memberParams.getLongitud());
            member.setDisponibility(memberParams.getDisponibility());
            memberDAO.save(member);
        } catch (Exception e){
            return "ERROR" ;
        }
        return "OK";
    }

    @RequestMapping(value ="/members/{memberId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Member> findMember(@PathVariable(value="memberId",required=false) Long memberId) {

        List<Member> listMember = new ArrayList<>();

        try{
            listMember.add(memberDAO.findOne(memberId));
        } catch (Exception e){
            log.error("ERROR");
        }

        return listMember;
    }

    @RequestMapping(value ="/members", method = RequestMethod.GET)
    @ResponseBody
    public List<Member> findMember() {

        List<Member> listMember = new ArrayList<>();

        try{
                memberDAO.findAll().forEach(member -> listMember.add(member));
                return listMember;
        } catch (Exception e){
            log.error("ERROR");
        }

        return listMember;
    }

    @RequestMapping(value ="/members/{memberId}", method = RequestMethod.PUT)
    @ResponseBody
    public Member updateMember(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams) {

        Member member = null;

        try{
            member = memberDAO.findOne(memberId);
            member.setName(memberParams.getName());
            member.setAddress(memberParams.getAddress());
            member.setBirthDate(memberParams.getBirthDate());
            member.setAddress(memberParams.getAddress());
            member.setCity(memberParams.getCity());
            member.setEmail(memberParams.getEmail());
            member.setLandphoneNumber(memberParams.getLandPhoneNumber());
            member.setMobilephoneNumber(memberParams.getLandPhoneNumber());
            member.setPostalCode(memberParams.getPostalCode());
            member.setSurname1(memberParams.getSurname1());
            member.setSurname2(memberParams.getSurname2());
            member.setVAT(memberParams.getVat());
            member.setLattitude(memberParams.getLattitude());
            member.setLongitud(memberParams.getLongitud());
            member.setDisponibility(memberParams.getDisponibility());
            memberDAO.save(member);

        } catch (Exception e){
            log.error("ERROR");
        }

        return member;
    }

    @RequestMapping(value ="/members/{memberId}/position", method = RequestMethod.PUT)
    @ResponseBody
    public String updatePositionMember(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams) {

        Member member = null;

        try{
            member = memberDAO.findOne(memberId);
            member.setLongitud(memberParams.getLongitud());
            member.setLattitude(memberParams.getLattitude());
            memberDAO.save(member);

        } catch (Exception e){
            log.error("ERROR: " + e.getMessage());
            return "ERROR";
        }

        return "OK";
    }

    @RequestMapping(value ="/members/{memberId}/disponibility", method = RequestMethod.PUT)
    @ResponseBody
    public Member updateAvailabilityMember(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams) {

        Member member = null;

        try{
            member = memberDAO.findOne(memberId);
            member.setDisponibility(memberParams.getDisponibility());
            memberDAO.save(member);

        } catch (Exception e){
            log.error("ERROR");
        }

        return member;
    }

    @RequestMapping(value ="/members", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMember(Long memberId) {

        try{
            Member member = memberDAO.findOne(memberId);
            member.setDeleted(true);
            memberDAO.save(member);
        } catch (Exception e){
            log.error("ERROR");
            return "ERROR";
        }

        return "OK";
    }
}
