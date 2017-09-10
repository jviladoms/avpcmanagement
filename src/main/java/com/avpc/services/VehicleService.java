package com.avpc.services;

import com.avpc.model.Vehicle;
import com.avpc.model.dao.VehicleDAO;
import com.avpc.restfulcontrollers.dto.VehicleDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private static final Logger log = Logger.getLogger(VehicleService.class);

    @Autowired
    private VehicleDAO vehicleDAO;

    public Vehicle addVehicle(VehicleDTO vehicleDTO){
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setCredential(vehicleDTO.getCredential());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setRegistrationNumber(vehicleDTO.getRegistration_number());
        return vehicleDAO.save(vehicle);
    }

    public Vehicle getVehicle(Long vehicleId){
        return vehicleDAO.findOne(vehicleId);
    }

    public List<Vehicle> getAllVehicles(){
        List<Vehicle> vehicleList = null;
        vehicleDAO.findAll().forEach(member -> vehicleList.add(member));

        return vehicleList;
    }

    public Vehicle updateVehicle(VehicleDTO vehicleDTO){
        Vehicle vehicle;
        vehicle = vehicleDAO.findOne(vehicleDTO.getId());
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setCredential(vehicleDTO.getCredential());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setRegistrationNumber(vehicleDTO.getRegistration_number());
        return vehicleDAO.save(vehicle);
    }

    public void deleteVehicle(Long vehicleId){
        vehicleDAO.delete(vehicleId);
    }
}
