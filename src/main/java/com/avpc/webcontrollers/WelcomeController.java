package com.avpc.webcontrollers;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.MemberController;
import com.avpc.restfulcontrollers.dto.MemberDTO;
import com.avpc.services.MemberService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jviladoms on 11/8/17.
 */
@Controller
public class WelcomeController {

    private static final Logger log = Logger.getLogger(MemberController.class);

    @Autowired
    MemberService memberService;

    @Autowired
    MemberDAO memberDAO;

    @Autowired
    ServiceDAO serviceDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(ModelMap model) {
        model.put("name", "jviladoms");
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(ModelMap model){
        return "login";
    }

    @RequestMapping(value = "/admin/inici")
    public String inici(ModelMap model){
        return "Inici";
    }

    @RequestMapping(value = "/admin/voluntaris")
    public String voluntaris(ModelMap model){
        model.put("members", memberService.findMember());
        return "Voluntaris";
    }

    @RequestMapping(value = "/admin/voluntaris_registration")
    public String voluntaris_registration(ModelMap model){
        return "Voluntaris_registration";
    }

    @RequestMapping(value = "/admin/register_member", method = RequestMethod.POST)
    public String voluntaris_register(@ModelAttribute MemberDTO memberParams){
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
        }
        return "Voluntaris_registration";
    }

    @RequestMapping(value = "/admin/update_member/{memberId}", method = RequestMethod.POST)
    public String update_member(@PathVariable(value="memberId",required=false) Long memberId,@ModelAttribute MemberDTO memberParams, ModelMap model){
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
        }

        model.put("member", member);
        return "Voluntaris_update";
    }

    @RequestMapping(value = "/admin/member_update/{memberId}")
    public String member_update(@PathVariable(value="memberId",required=false) Long memberId, ModelMap model){
        Member member = null;

        try{
            member = memberDAO.findOne(memberId);
            member.setServices(serviceDAO.findByMembersInServiceIn(member).size());
        } catch (Exception e){
            log.error(e.getMessage());
        }

        model.put("member", member);
        return "Voluntaris_update";
    }

    @RequestMapping(value = "/admin/delete_member/{memberId}")
    public String delete_member(@PathVariable(value="memberId",required=false) Long memberId, ModelMap model){
        try{
            memberDAO.delete(memberId);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
        }
        return "Voluntaris";
    }

    @RequestMapping(value = "/admin/vehicles")
    public String vehicles(ModelMap model){
        return "Vehicles";
    }

    @RequestMapping(value = "/admin/serveis")
    public String serveis(ModelMap model){
        return "Serveis";
    }

    @RequestMapping(value = "/admin/mapa")
    public String mapa(ModelMap model){
        return "Mapa";
    }

    @RequestMapping(value = "/admin/missatges")
    public String misatges(ModelMap model){
        return "Missatges";
    }
}
