package com.avpc.restfulcontrollers;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.dto.MemberDTO;
import com.google.firebase.auth.FirebaseToken;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import utils.Constants;
import utils.Firebase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value ="/members")
public class MemberController {

    private static final Logger log = Logger.getLogger(MemberController.class);

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private ServiceDAO serviceDAO;

    @RequestMapping(value ="/login", method = RequestMethod.POST)
    public boolean verifyIdToken(@RequestBody String idToken, HttpServletRequest request, HttpServletResponse response) throws IOException {

        boolean callbackResults = false;
        try{
            FirebaseToken token = Firebase.getInstance().verifyIdToken(idToken);
            if(token != null) {
                request.getSession().setAttribute(Constants.UID, token);
                callbackResults = true;
            }
            else {
                request.getSession().invalidate();
            }
        }
        catch (InterruptedException e) {
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return callbackResults;
    }

    @RequestMapping(method = RequestMethod.POST)
    @CrossOrigin
    public void addMember(@RequestBody MemberDTO memberParams, HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try {
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
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @RequestMapping(value ="/{memberId}", method = RequestMethod.GET)
    @ResponseBody
    public Member findMember(@PathVariable(value="memberId",required=false) Long memberId, HttpServletRequest request, HttpServletResponse response) throws IOException{

        Member member = null;

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try{
                member = memberDAO.findOne(memberId);
                member.setServices(serviceDAO.findByMembersInServiceIn(member).size());
            } catch (Exception e){
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        return member;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Member> findMember(HttpServletRequest request, HttpServletResponse response) throws IOException{

        List<Member> listMember = new ArrayList<>();

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try{
                memberDAO.findAll().forEach(member -> listMember.add(member));
                listMember.forEach(member -> member.setServices(serviceDAO.findByMembersInServiceIn(member).size()));
            } catch (Exception e){
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        return listMember;
    }

    @RequestMapping(value ="/{memberId}", method = RequestMethod.PUT)
    @ResponseBody
    public Member updateMember(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Member member = null;

        if(request.getSession().getAttribute(Constants.UID) != null) {
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
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        return member;
    }

    @RequestMapping(value ="/{memberId}/location", method = RequestMethod.PUT)
    public void updateMemberLocation(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Member member = null;

        if(request.getSession().getAttribute(Constants.UID) != null) {
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
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @RequestMapping(value ="/{memberId}/availability", method = RequestMethod.PUT)
    @ResponseBody
    public Member updateMemberAvailability(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams, HttpServletRequest request, HttpServletResponse response) throws IOException{

        Member member = null;

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try{
                member = memberDAO.findOne(memberId);

                member.setAvailability(memberParams.getAvailability());

                memberDAO.save(member);

            } catch (Exception e){
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        return member;
    }

    @RequestMapping(value ="/{memberId}", method = RequestMethod.DELETE)
    public void deleteMember(@PathVariable(value="memberId") Long memberId, HttpServletRequest request, HttpServletResponse response) throws IOException{

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try{
                memberDAO.delete(memberId);
            } catch (IllegalArgumentException e){
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
