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
import utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value ="/service")
public class ServiceController {

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private MemberDAO memberDAO;

    private static final Logger log = Logger.getLogger(MemberController.class);

    @RequestMapping(method = RequestMethod.POST)
    public void addService(@RequestBody ServiceDTO serviceDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try {
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
                    response.sendError(HttpStatus.BAD_REQUEST.value());
                }

            } catch (IllegalArgumentException e ){
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Service> getService(@RequestParam(value="id",required=false) Long serviceId,
                                    HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Service> listService = new ArrayList<>();

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try {
                if (serviceId == null) {
                    serviceDAO.findAll().forEach(service -> listService.add(service));
                    return listService;
                } else {
                    listService.add(serviceDAO.findOne(serviceId));
                }
            } catch (IllegalArgumentException e){
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        return listService;
    }

    @RequestMapping(value ="/find_between_dates", method = RequestMethod.GET)
    @ResponseBody
    public List<Service> getBetweenDays(@RequestParam(value="start_date") Date start_date,
                                         @RequestParam(value="end_date") Date end_date,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Service> listService = null;

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try {
                listService = serviceDAO.findByStartDateBetween(start_date,end_date);
            } catch(IllegalArgumentException e) {
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        return listService;
    }

    @RequestMapping(value ="/{serviceId}", method = RequestMethod.PUT)
    @ResponseBody
    public Service updateService(@PathVariable(value="serviceId") Long serviceId,
                                @RequestBody ServiceDTO serviceDTO,
                                HttpServletRequest request, HttpServletResponse response) throws IOException {

        Service service = null;

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try {
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
                    response.sendError(HttpStatus.BAD_REQUEST.value());
                }

                serviceDAO.save(service);

            }  catch (IllegalArgumentException e){
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        return service;
    }

    @RequestMapping(value ="/{serviceId}", method = RequestMethod.DELETE)
    public void deleteService(@PathVariable(value="serviceId") Long serviceId,
                             HttpServletRequest request, HttpServletResponse response) throws IOException{

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try {
                serviceDAO.delete(serviceId);
            } catch (IllegalArgumentException e){
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }

    private List<Member> getMembers(Iterable<Long> ids) {
        List<Member> members = new ArrayList<>();
        ids.forEach(member -> members.add(memberDAO.findOne(member)));
        return members.stream().filter(member -> member != null).collect(Collectors.toList());
    }
}
