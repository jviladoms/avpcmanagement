package com.avpc.restfulcontrollers;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.dto.MemberDTO;
import com.avpc.services.MemberService;
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
@RequestMapping(value ="/members")
public class MemberController {

    private static final Logger log = Logger.getLogger(MemberController.class);

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private MemberService memberService;


    @RequestMapping(value ="/", method = RequestMethod.POST)
    @CrossOrigin
    public void addMember(@RequestBody MemberDTO memberParams, HttpServletResponse response) throws IOException {

        try{
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

            memberDAO.save(member);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/login", method = RequestMethod.POST)
    @CrossOrigin
    public void loginMember(@RequestBody MemberDTO memberParams, HttpServletResponse response) throws IOException {

        try{
            Member member = new Member();

            String email = memberParams.getEmail();
            String password = memberParams.getPassword();

            // TODO:
            System.out.println("email=" + email + ", " + password);

        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/{memberId}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public Member findMember(@PathVariable(value="memberId",required=false) Long memberId, HttpServletResponse response) throws IOException{

        Member member = null;

        try{
            member = memberDAO.findOne(memberId);
            member.setServices(serviceDAO.findByMembersInServiceIn(member).size());
        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return member;
    }

    @RequestMapping(value ="/", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public List<Member> findMember(HttpServletResponse response){
        return memberService.findMember();
    }

    @RequestMapping(value ="/{memberId}", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public Member updateMember(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams, HttpServletResponse response) throws IOException {

        Member member = null;

        try{
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

            memberDAO.save(member);

        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return member;
    }

    @RequestMapping(value ="/{memberId}/location", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public void updateMemberLocation(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams, HttpServletResponse response) throws IOException {

        Member member = null;

        try{
            member = memberDAO.findOne(memberId);

            member.setLongitude(memberParams.getLongitude());
            member.setLatitude(memberParams.getLatitude());

            memberDAO.save(member);

        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/{memberId}/availability", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public Member updateMemberAvailability(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams, HttpServletResponse response) throws IOException{

        Member member = null;

        try{
            member = memberDAO.findOne(memberId);

            member.setAvailability(memberParams.getAvailability());

            memberDAO.save(member);

        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return member;
    }

    @RequestMapping(value ="/{memberId}", method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public void deleteMember(@PathVariable(value="memberId") Long memberId, HttpServletResponse response) throws IOException{
        try{
            memberDAO.delete(memberId);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }
}
