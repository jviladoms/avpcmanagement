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

    @RequestMapping(value = "/admin/mapa")
    public String mapa(ModelMap model){
        return "Mapa";
    }

    @RequestMapping(value = "/admin/missatges")
    public String misatges(ModelMap model){
        return "Missatges";
    }
}
