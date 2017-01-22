package com.avpc.restfulcontrollers;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.dto.MemberDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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

    @Autowired
    private ServiceDAO serviceDAO;

    private HttpServletResponse response;

    @RequestMapping(value ="/members", method = RequestMethod.POST)
    @CrossOrigin
    public void addMember(@RequestBody MemberDTO memberParams) throws IOException {

        try{
            Member member = new Member();
            member.setName(memberParams.getName());
            member.setAddress(memberParams.getAddress());
            member.setBirthDate(memberParams.getBirthDate());
            member.setAddress(memberParams.getAddress());
            member.setCity(memberParams.getCity());
            member.setEmail(memberParams.getEmail());
            member.setLandphoneNumber(memberParams.getLandPhoneNumber());
            member.setMobilephoneNumber(memberParams.getMobilePhoneNumber());
            member.setPostalCode(memberParams.getPostalCode());
            member.setSurname1(memberParams.getSurname1());
            member.setSurname2(memberParams.getSurname2());
            member.setVAT(memberParams.getVat());
            member.setUserGroup(memberParams.getUserGroup());
            memberDAO.save(member);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/members/{memberId}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public Member findMember(@PathVariable(value="memberId",required=false) Long memberId) {

        Member member = null;

        try{
            member = memberDAO.findOne(memberId);
            member.setServices(serviceDAO.findBymembersInServiceIn(member).size());
        } catch (Exception e){
            log.error("ERROR");
        }

        return member;
    }

    @RequestMapping(value ="/members", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public List<Member> findMember() {

        List<Member> listMember = new ArrayList<>();

        try{
                memberDAO.findAll().forEach(member -> listMember.add(member));
                listMember.forEach(member -> member.setServices(serviceDAO.findBymembersInServiceIn(member).size()));
                return listMember;
        } catch (Exception e){
            log.error("ERROR");
        }

        return listMember;
    }

    @RequestMapping(value ="/members/{memberId}", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public Member updateMember(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams) throws IOException {

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
            member.setTip(memberParams.getTip());
            member.setUserGroup(memberParams.getUserGroup());
            memberDAO.save(member);

        } catch (IllegalArgumentException e){
            log.error("ERROR");
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return member;
    }

    @RequestMapping(value ="/members/{memberId}/position", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public void updatePositionMember(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams) throws IOException {

        Member member = null;

        try{
            member = memberDAO.findOne(memberId);
            member.setLongitud(memberParams.getLongitud());
            member.setLattitude(memberParams.getLattitude());
            memberDAO.save(member);

        } catch (Exception e){
            log.error("ERROR: " + e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/members/{memberId}/disponibility", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
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

    @RequestMapping(value ="/members/{memberId}", method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public void deleteMember(@PathVariable(value="memberId") Long memberId) throws IOException{
        try{
            memberDAO.delete(memberId);
        } catch (IllegalArgumentException e){
            log.error("ERROR");
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }
}
