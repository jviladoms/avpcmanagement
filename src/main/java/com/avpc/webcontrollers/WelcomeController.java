package com.avpc.webcontrollers;

import com.avpc.model.Member;
import com.avpc.model.Message;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.MemberController;
import com.avpc.rssreader.Feed;
import com.avpc.rssreader.RssFeedReaderService;
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

    public static final String PC_URL = "http://premsa.gencat.cat/pres_fsvp/AppJava/rss/list.do?pageCount=1&idioma=0&objectType=1&departament=61";
    public static final String MOSSOS_URL = "http://premsa.gencat.cat/pres_fsvp/AppJava/rss/list.do?pageCount=1&idioma=0&objectType=1&departament=40";
    public static final String BOMBERS_URL = "http://premsa.gencat.cat/pres_fsvp/AppJava/rss/list.do?pageCount=1&idioma=0&objectType=1&departament=22";
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
        RssFeedReaderService rssFeedReaderProteccioCivilService = RssFeedReaderService.create(PC_URL);
        RssFeedReaderService rssFeedReaderMossosService = RssFeedReaderService.create(MOSSOS_URL);
        RssFeedReaderService rssFeedReaderBombers = RssFeedReaderService.create(BOMBERS_URL);


        Feed feedProteccioCivil = rssFeedReaderProteccioCivilService.readFeed();
        Feed feedMossos = rssFeedReaderMossosService.readFeed();
        Feed feedBombers = rssFeedReaderBombers.readFeed();

        model.put("feedProteccioCivil",feedProteccioCivil);
        model.put("feedMossos",feedMossos);
        model.put("feedBombers",feedBombers);
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

        /*if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        try {
            List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
            model.addAttribute("tweets", tweets);
            request.getSession().setAttribute("tweets",tweets);
        } catch (Exception e){
            log.error(e.getMessage());
        }*/

        model.addAttribute("numServices",servicesService.getServices().size());
        model.addAttribute("numMembers",memberService.findMembers().size());
        model.addAttribute("numVehicles",vehicleService.getAllVehicles().size());
        model.addAttribute("numMessages",messageService.findAllMessages().size());

        List<Message> messages = messageService.findAllMessages();
        model.addAttribute("messages",messages);
        request.getSession().setAttribute("messages",messages);
        model.addAttribute("member",member);

        return "Inici";
    }

    @RequestMapping(value = "/admin/mapa")
    public String mapa(ModelMap model){

        List<Member> memberList = memberService.findMembers();
        model.addAttribute("members",memberList);
        return "Mapa";
    }

}
