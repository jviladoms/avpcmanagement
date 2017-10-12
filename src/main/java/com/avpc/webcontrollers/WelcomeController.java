package com.avpc.webcontrollers;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.MemberController;
import com.avpc.services.MemberService;
import com.avpc.services.MessageService;
import com.avpc.services.ServicesService;
import com.avpc.services.VehicleService;
import java.util.List;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jviladoms on 11/8/17.
 */
@Controller
public class WelcomeController {

    @Inject
    private Twitter twitter;
    @Inject
    private ConnectionRepository connectionRepository;

    private static final Logger log = Logger.getLogger(MemberController.class);

    @Autowired
    ServicesService servicesService;

    @Autowired
    MemberService memberService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    MessageService messageService;

    @Autowired
    MemberDAO memberDAO;

    @Autowired
    ServiceDAO serviceDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getName();
        model.put("name", auth.getName());
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(ModelMap model){
        return "login";
    }

    @RequestMapping(value = "/user/inici")
    public String inici(HttpServletRequest request, ModelMap model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberDAO.findByDni(auth.getName());

        request.getSession().setAttribute("username",member.getFullName());
        request.getSession().setAttribute("userid",member.getId());
        model.put("name", member.getFullName());
        model.put("member", member);

        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        try {
            List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
            model.addAttribute("tweets", tweets);
        } catch (Exception e){
            log.error(e.getMessage());
        }

        model.addAttribute("numServices",servicesService.getServices().size());
        model.addAttribute("numMembers",memberService.findMembers().size());
        model.addAttribute("numVehicles",vehicleService.getAllVehicles().size());
        model.addAttribute("numMessages",messageService.findAllMessages().size());

        model.addAttribute("messages",messageService.findAllMessages());
        model.addAttribute("member",member);

        return "Inici";
    }

    @RequestMapping(value = "/admin/mapa")
    public String mapa(ModelMap model){
        return "Mapa";
    }

}
