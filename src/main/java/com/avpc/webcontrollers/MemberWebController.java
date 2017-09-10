package com.avpc.webcontrollers;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import com.avpc.restfulcontrollers.dto.MemberDTO;
import com.avpc.services.MemberService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberWebController {

    private static final Logger log = Logger.getLogger(MemberWebController.class);

    @Autowired
    MemberService memberService;

    @Autowired
    MemberDAO memberDAO;

    @RequestMapping(value = "/admin/voluntaris")
    public String voluntaris(ModelMap model){
        model.put("members", memberService.findMembers());
        return "Voluntaris";
    }

    @RequestMapping(value = "/admin/voluntaris_registration")
    public String voluntaris_registration(ModelMap model){
        return "Voluntaris_registration";
    }

    @RequestMapping(value = "/admin/register_member", method = RequestMethod.POST)
    public String voluntaris_register(@ModelAttribute MemberDTO memberParams, ModelMap model){
        Member member = null;
        try{
            member = memberService.addMember(memberParams);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        model.put("member", member);
        return "Voluntaris_update";
    }

    @RequestMapping(value = "/admin/update_member/{memberId}", method = RequestMethod.POST)
    public String update_member(@PathVariable(value="memberId",required=false) Long memberId, @ModelAttribute MemberDTO memberParams, ModelMap model){
        Member member = null;

        try{
            memberService.updateMember(memberParams,memberId);

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
            member = memberService.findMember(memberId);
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
            model.put("members", memberService.findMembers());
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
        }
        return "Voluntaris";
    }
}
