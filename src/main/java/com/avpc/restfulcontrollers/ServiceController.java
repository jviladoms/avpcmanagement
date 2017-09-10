package com.avpc.restfulcontrollers;


import com.avpc.model.Service;
import com.avpc.restfulcontrollers.dto.ServiceDTO;
import com.avpc.services.ServicesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value ="/service")
public class ServiceController {

    @Autowired
    private ServicesService servicesService;

    private static final Logger log = Logger.getLogger(MemberController.class);

    @RequestMapping(value ="/", method = RequestMethod.POST)
    @CrossOrigin
    public void addService(@RequestBody ServiceDTO serviceDTO, HttpServletResponse response) throws IOException {

        try{
            servicesService.addService(serviceDTO);
        } catch (IllegalArgumentException e ){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public List<Service> getService(@RequestParam(value="id",required=false) Long serviceId,
                                   HttpServletResponse response) throws IOException {

        List<Service> listService = new ArrayList<>();

        try{
            listService = servicesService.getService(serviceId);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return listService;
    }

    @RequestMapping(value ="/find_between_dates", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public List<Service> getServicesBetweenDays(@RequestParam(value="start_date") Date start_date,
                                         @RequestParam(value="end_date") Date end_date,
                                        HttpServletResponse response) throws IOException {

        List<Service> listService = null;
        try{
            listService = servicesService.getServicesBetweenDays(start_date,end_date);
        } catch(IllegalArgumentException e) {
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return listService;
    }

    @RequestMapping(value ="/{serviceId}", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public Service updateService(@PathVariable(value="serviceId") Long serviceId,
                                @RequestBody ServiceDTO serviceDTO,
                                HttpServletResponse response) throws IOException {

        Service service = null;

        try{
           service = servicesService.updateService(serviceId,serviceDTO);
        }  catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return service;
    }

    @RequestMapping(value ="/{serviceId}", method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public void deleteService(@PathVariable(value="serviceId") Long serviceId,
                             HttpServletResponse response) throws IOException{

        try{
            servicesService.deleteService(serviceId);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

    }
}
