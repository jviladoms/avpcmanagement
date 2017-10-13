package com.avpc.restfulcontrollers;

import com.avpc.model.Member;

import com.avpc.restfulcontrollers.dto.MemberDTO;
import com.avpc.services.MemberService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jordi on 29/10/2016.
 */
@RestController
@RequestMapping(value ="/members")
public class MemberController {

    private static final Logger log = Logger.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;


    @RequestMapping(value ="/", method = RequestMethod.POST)
    @CrossOrigin
    public void addMember(@RequestBody MemberDTO memberParams, HttpServletResponse response) throws IOException {

        try{
            memberService.addMember(memberParams);
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
            member = memberService.findMember(memberId);
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
        return memberService.findMembers();
    }

    @RequestMapping(value ="/{memberId}", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public Member updateMember(@PathVariable(value="memberId") Long memberId, @RequestBody MemberDTO memberParams, HttpServletResponse response) throws IOException {

        Member member = null;

        try{
            member = memberService.updateMember(memberParams,memberId);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return member;
    }

    @RequestMapping(value ="/{memberId}/location", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public void updateMemberLocation(@PathVariable(value="memberId") Long memberId, @RequestBody Double longitude,@RequestBody Double latitude, HttpServletResponse response) throws IOException {
        try{
            memberService.updateMemberLocation(longitude,latitude, memberId);
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
          member = memberService.updateMemberAvailability(memberParams,memberId);

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
            memberService.deleteMember(memberId);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }
}
