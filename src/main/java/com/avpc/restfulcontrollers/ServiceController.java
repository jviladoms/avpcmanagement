package com.avpc.restfulcontrollers;

import com.avpc.model.Member;
import com.avpc.model.Service;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.dto.ServiceDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    private HttpServletResponse response;

    private static final Logger log = Logger.getLogger(MemberController.class);

    @RequestMapping(value ="/service", method = RequestMethod.POST)
    @CrossOrigin
    public void addService(@RequestBody ServiceDTO serviceDTO) throws IOException {

        try{
            Service service = new Service();
            service.setStartDate(serviceDTO.getStartDate());
            service.setFinalDate(serviceDTO.getFinalDate());
            service.setComments(serviceDTO.getComments());
            service.setServiceDescription(serviceDTO.getServiceDescription());
            service.setLocalization(serviceDTO.getLocalization());
            service.setMaterial(serviceDTO.getMaterial());
            service.setTypeOfService(serviceDTO.getTypeOfService());

            List<Member> members = getMembers(serviceDTO.getMembersInService());

            if(members.size() > 0){
                service.setMembersInService(members);
                serviceDAO.save(service);
            } else {
                throw new Exception("no valid members");
            }

        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/service", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
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
    @CrossOrigin
    public List<Service> getBetweenDays(@RequestParam(value="start_date") Date start_date,
                                         @RequestParam(value="end_date") Date end_date) {

        List<Service> listService = serviceDAO.findByStartDateBetween(start_date,end_date);

        return listService;
    }

    @RequestMapping(value ="/service/{serviceId}", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public Service updateMember(@PathVariable(value="serviceId") Long serviceId, @RequestBody ServiceDTO serviceDTO) {

        Service service = null;

        try{
            service = serviceDAO.findOne(serviceId);
            service.setStartDate(serviceDTO.getStartDate());
            service.setFinalDate(serviceDTO.getFinalDate());
            service.setComments(serviceDTO.getComments());
            service.setServiceDescription(serviceDTO.getServiceDescription());
            service.setLocalization(serviceDTO.getLocalization());
            service.setMaterial(serviceDTO.getMaterial());
            service.setTypeOfService(serviceDTO.getTypeOfService());

            List<Member> members = getMembers(serviceDTO.getMembersInService());

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

    @RequestMapping(value ="/service/{serviceId}", method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public String deleteMember(@PathVariable(value="serviceId") Long serviceId) {

        try{
            serviceDAO.delete(serviceId);
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
