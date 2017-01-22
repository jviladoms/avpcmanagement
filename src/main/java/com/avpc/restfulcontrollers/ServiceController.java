package com.avpc.restfulcontrollers;

import com.avpc.model.Member;
import com.avpc.model.Service;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.dto.ServiceDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jordi on 29/10/2016.
 */

@RestController
public class ServiceController {

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private MemberDAO memberDAO;

    private static final Logger log = Logger.getLogger(MemberController.class);

    @RequestMapping(value ="/service", method = RequestMethod.POST)
    public String addMember(@RequestBody ServiceDTO serviceDTO) {

        try{
            Service service = new Service();
            service.setStartDate(serviceDTO.getStartDate());
            service.setFinalDate(serviceDTO.getEndDate());
            service.setComments(serviceDTO.getComments());

            List<Member> members = getMembers(serviceDTO.getMembers());

            if(members.size() > 0){
                service.setMembersInService(members);
                serviceDAO.save(service);
            } else {
                throw new Exception("no valid members");
            }

        } catch (Exception e){
            log.error(e.getMessage());
            return "ERROR: " + e.getMessage();
        }
        return "OK";
    }

    @RequestMapping(value ="/service", method = RequestMethod.GET)
    @ResponseBody
    public List<Service> getMember(@RequestParam(value="id",required=false) Long serviceId) {

        List<Service> listService = new ArrayList<>();

        try{
            if (serviceId == null){
                serviceDAO.findAll().forEach(service -> listService.add(service));
                return listService;
            } else {
                listService.add(serviceDAO.findOne(serviceId));
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }

        return listService;
    }

    @RequestMapping(value ="/service/find_between_dates", method = RequestMethod.GET)
    @ResponseBody
    public List<Service> getBetweenDays(@RequestParam(value="start_date") Date start_date,
                                         @RequestParam(value="end_date") Date end_date) {

        List<Service> listService = serviceDAO.findByStartDateBetween(start_date,end_date);

        return listService;
    }

    @RequestMapping(value ="/service", method = RequestMethod.PUT)
    @ResponseBody
    public Service updateMember(@RequestBody ServiceDTO serviceDTO) {

        Service service = null;

        try{
            service = serviceDAO.findOne(serviceDTO.getId());
            service.setStartDate(serviceDTO.getStartDate());
            service.setFinalDate(serviceDTO.getEndDate());
            service.setComments(serviceDTO.getComments());

            List<Member> members = getMembers(serviceDTO.getMembers());

            if(members.size() > 0){
                service.setMembersInService(members);
                serviceDAO.save(service);
            } else {
                throw new Exception("no valid members");
            }

            serviceDAO.save(service);

        } catch (Exception e){
            log.error(e.getMessage());
        }

        return service;
    }

    @RequestMapping(value ="/service", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMember(Long memberId) {

        try{
            serviceDAO.delete(memberId);
        } catch (Exception e){
            return "ERROR";
        }

        return "OK";
    }

    private List<Member> getMembers(Iterable<Long> ids){
        List<Member> members = new ArrayList<>();
        ids.forEach(member -> members.add(memberDAO.findOne(member)));
        return members.stream().filter(member -> member != null).collect(Collectors.toList());
    }
}
