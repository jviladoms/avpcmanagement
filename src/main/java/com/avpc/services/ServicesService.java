package com.avpc.services;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.dto.ServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import com.avpc.model.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServicesService {
    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private ServicesService servicesService;

    public Service addService(ServiceDTO serviceDTO){
        Service service = new com.avpc.model.Service();
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
            throw new RuntimeException("Error saving new service");
        }

        return service;
    }

    public Service getService(Long serviceId) throws IllegalArgumentException{
        return serviceDAO.findOne(serviceId);
    }

    public List<Service> getServices() throws IllegalArgumentException{
        List<Service> listService = new ArrayList<>();
        serviceDAO.findAll().forEach(service -> listService.add(service));
        return listService;
    }

    public List<Service> getServicesBetweenDays(Date start_date, Date end_date){
        return serviceDAO.findByStartDateBetween(start_date,end_date);
    }

    public Service updateService(Long serviceId, ServiceDTO serviceDTO){

        Service service;

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
            throw new RuntimeException("No service found");
        }

        return serviceDAO.save(service);
    }

    public Service updateService(Service modifiedService){

        Service service;

        service = serviceDAO.findOne(modifiedService.getId());
        service.setStartDate(modifiedService.getStartDate());
        service.setFinalDate(modifiedService.getFinalDate());
        service.setComments(modifiedService.getComments());
        service.setServiceDescription(modifiedService.getServiceDescription());
        service.setLocalization(modifiedService.getLocalization());
        service.setMaterial(modifiedService.getMaterial());
        service.setTypeOfService(modifiedService.getTypeOfService());
        service.setMembersInService(modifiedService.getMembersInService());

        return serviceDAO.save(service);
    }

    public void deleteService(Long serviceId){
        serviceDAO.delete(serviceId);
    }

    private List<Member> getMembers(Iterable<Long> ids){
        List<Member> members = new ArrayList<>();
        ids.forEach(member -> members.add(memberDAO.findOne(member)));
        return members.stream().filter(member -> member != null).collect(Collectors.toList());
    }
}
