package com.avpc.webcontrollers;

import com.avpc.model.Member;
import com.avpc.model.Service;
import com.avpc.model.dao.MemberDAO;
import com.avpc.restfulcontrollers.dto.MemberDTO;
import com.avpc.restfulcontrollers.dto.PasswordDTO;
import com.avpc.services.MemberService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@Controller
public class MemberWebController {

    private static final Logger log = Logger.getLogger(MemberWebController.class);

    @Autowired
    MemberService memberService;

    @Autowired
    MemberDAO memberDAO;

    @Value("${image.storage.folder}")
    private String rootPath;

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

        model.put("fromRegister", true);
        model.put("member", member);
        return "Voluntaris_update";
    }

    @RequestMapping(value = "/admin/update_member/{memberId}", method = RequestMethod.POST)
    public String update_member(@PathVariable(value="memberId",required=false) Long memberId, @ModelAttribute MemberDTO memberParams, ModelMap model){
        Member member = null;

        try{
            member = memberService.updateMember(memberParams,memberId);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
        }

        model.put("fromUpdate", true);
        model.put("member", member);
        return "Voluntaris_update";
    }

    @RequestMapping(value = "/user/update_member", method = RequestMethod.POST)
    public String update_member(@ModelAttribute MemberDTO memberParams, ModelMap model){
        Member member = null;
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            member = memberService.findMemberByDni(auth.getName());
            member = memberService.updateMember(memberParams,member.getId());
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
        }

        model.put("fromUpdate", true);
        model.put("member", member);
        return "Voluntaris_update";
    }

    @RequestMapping(value = "/user/update_member_password", method = RequestMethod.POST)
    public String update_member(@ModelAttribute PasswordDTO passwordDTO, ModelMap model){
        Member member = null;

        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            member = memberService.findMemberByDni(auth.getName());
            memberService.updatePassword(member.getId(), passwordDTO.getOldPassword(),passwordDTO.getNewPassword());
        } catch (Exception e){
            log.error(e.getMessage());
            model.put("success", false);
            return "Voluntaris_password";
        }

        model.put("success", true);
        return "Voluntaris_password";
    }

    @RequestMapping(value = "/user/member_password")
    public String update_password(ModelMap model){
        Member member = null;

        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            member = memberService.findMemberByDni(auth.getName());
            model.put("member", member);
            return "Voluntaris_password";
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
        }

        return "Inici";
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

    @RequestMapping(value = "/user/member_update")
    public String member_update(ModelMap model){
        Member member = null;

        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            member = memberService.findMemberByDni(auth.getName());
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
            model.put("fromDelete",true);
            model.put("members", memberService.findMembers());
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
        }
        return "Voluntaris";
    }

    @RequestMapping(value = "/admin/member/uploadFile", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("member") Long memberId,
                             @RequestParam("file") MultipartFile file, ModelMap model) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                File dir = new File(rootPath + File.separator + "member");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + memberId);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                Member member = memberDAO.findOne(memberId);

                member.setPhotoURL(serverFile.getAbsolutePath());
                memberDAO.save(member);

                log.info("Server File Location="
                        + serverFile.getAbsolutePath());

            } catch (Exception e) {
                log.error("You failed to upload " + memberId + " => " + e.getMessage());
            }
        }

        Member member = null;

        try{
            member = memberService.findMember(memberId);
        } catch (Exception e){
            log.error(e.getMessage());
        }

        model.put("member", member);
        return "Voluntaris_update";
    }

    @RequestMapping(value = "/member/image/display",method=RequestMethod.GET)
    @ResponseBody
    public byte[] memberImageDisplay(@RequestParam String name,HttpServletResponse response)  {
        System.out.println("Show is invoked");
        response.setContentType("image/jpeg");
        File file;
        byte arr[]={};
        try{
            file = new File(rootPath + File.separator + "member" + File.separator + name);
            if(file.isFile()){
                System.out.println("File is found");
            }
            else{
                name = "no-image.jpg";
                file = new File(rootPath+name);
            }
            arr= new byte[(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(arr,0,arr.length);
        }catch(Exception e){
            System.out.println(e);
        }
        return arr;
    }
}
