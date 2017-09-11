package com.avpc.webcontrollers;

import com.avpc.model.Member;
import com.avpc.model.Service;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.dto.ServiceDTO;
import com.avpc.services.MemberService;
import com.avpc.services.ServicesService;
import com.avpc.services.VehicleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ServicesWebController {
    private static final Logger log = Logger.getLogger(ServicesWebController.class);

    @Autowired
    ServicesService servicesService;

    @Autowired
    MemberService memberService;

    @Autowired
    ServiceDAO serviceDAO;

    @RequestMapping(value = "/admin/serveis")
    public String services(ModelMap model){
        model.put("services", servicesService.getServices());
        return "Serveis";
    }

    @RequestMapping(value = "/admin/serveis_registration")
    public String serveisRegistration(ModelMap model){
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("members", memberList);
        model.addAttribute("service", new Service());
        return "Serveis_registration";
    }

    @RequestMapping(value = "/admin/serveis_update/{serviceId}")
    public String serveisUpdate(@PathVariable(value="serviceId",required=false) Long serviceId, ModelMap model){
        Service service = servicesService.getService(serviceId);
        model.addAttribute("service", service);
        return "Serveis_update";
    }

    @RequestMapping(value = "/admin/update_serveis", method = RequestMethod.POST)
    public String updateServei(@ModelAttribute("service") Service service, BindingResult serviceResult, ModelMap model){
        servicesService.updateService(service);
        model.addAttribute("fromUpdate", true);
        model.addAttribute("service", service);
        return "Serveis_update";
    }

    @RequestMapping(value ="/admin/addService", method = RequestMethod.POST)
    public String addServices(@ModelAttribute("service") Service service, BindingResult serviceResult, ModelMap model ){
        try{
            serviceDAO.save(service);
            //service = servicesService.addService(serviceDTO);
        } catch (IllegalArgumentException e ){
            log.error(e.getMessage());
        }

        model.put("service", service);

        return "Serveis_update";
    }

    @ModelAttribute("membersList")
    public List<Member> getMembersList()
    {
        return memberService.findMembers();
    }
}
