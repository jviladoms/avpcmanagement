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
            member.setLandPhoneNumber(memberParams.getLandPhoneNumber());
            member.setMobilePhoneNumber(memberParams.getMobilePhoneNumber());
            member.setPostalCode(memberParams.getPostalCode());
            member.setSurname1(memberParams.getSurname1());
            member.setSurname2(memberParams.getSurname2());
            member.setUserGroup(memberParams.getUserGroup());

            memberDAO.save(member);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/members/login", method = RequestMethod.POST)
    @CrossOrigin
    public void loginMember(@RequestBody MemberDTO memberParams) throws IOException {

        try{
            Member member = new Member();

            String email = memberParams.getEmail();
            String password = memberParams.getPassword();

            // TODO:
            System.out.println("email=" + email + ", " + password);

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
            member.setServices(serviceDAO.findByMembersInServiceIn(member).size());
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
                listMember.forEach(member -> member.setServices(serviceDAO.findByMembersInServiceIn(member).size()));
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
            member.setSurname1(memberParams.getSurname1());
            member.setSurname2(memberParams.getSurname2());
            member.setAddress(memberParams.getAddress());
            member.setBirthDate(memberParams.getBirthDate());
            member.setAddress(memberParams.getAddress());
            member.setCity(memberParams.getCity());
            member.setEmail(memberParams.getEmail());
            member.setLandPhoneNumber(memberParams.getLandPhoneNumber());
            member.setMobilePhoneNumber(memberParams.getLandPhoneNumber());
            member.setPostalCode(memberParams.getPostalCode());
            member.setUserGroup(memberParams.getUserGroup());

            memberDAO.save(member);

        } catch (IllegalArgumentException e){
            log.error("ERROR");
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return member;
    }

    @RequestMapping(value ="/members/{memberId}/location", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public void updateMemberLocation(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams) throws IOException {

        Member member = null;

        try{
            member = memberDAO.findOne(memberId);

            member.setLongitude(memberParams.getLongitude());
            member.setLatitude(memberParams.getLatitude());

            memberDAO.save(member);

        } catch (Exception e){
            log.error("ERROR: " + e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/members/{memberId}/availability", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public Member updateMemberAvailability(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams) {

        Member member = null;

        try{
            member = memberDAO.findOne(memberId);

            member.setAvailability(memberParams.getAvailability());

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
