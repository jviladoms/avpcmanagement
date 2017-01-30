package com.avpc.restfulcontrollers;


import com.avpc.model.Vehicle;
import com.avpc.model.dao.VehicleDAO;
import com.avpc.restfulcontrollers.dto.VehicleDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordi on 31/10/2016.
 */

@RestController
public class VehicleController {
    private static final Logger log = Logger.getLogger(MemberController.class);

    @Autowired
    private VehicleDAO vehicleDAO;

    @RequestMapping(value ="/vehicle", method = RequestMethod.POST)
    public String addVehicle(@RequestBody VehicleDTO vehicleParams) {
        try{
            Vehicle vehicle = new Vehicle();
            vehicle.setBrand(vehicleParams.getBrand());
            vehicle.setCredential(vehicleParams.getCredential());
            vehicle.setModel(vehicleParams.getModel());
            vehicle.setRegistrationNumber(vehicleParams.getRegistration_number());
            vehicleDAO.save(vehicle);

        } catch (Exception e){
            return new StringBuilder().append("ERROR: ").append(e.getMessage()).toString();
        }
        return "OK";
    }

    @RequestMapping(value ="/vehicle", method = RequestMethod.GET)
    @ResponseBody
    public List<Vehicle> findVehicle(@RequestParam(value="id",required=false) Long vehicleId) {
        List<Vehicle> listVehicles = new ArrayList<>();

        try{
            if (vehicleId == null) {
                vehicleDAO.findAll().forEach(member -> listVehicles.add(member));
                return listVehicles;
            } else {
                listVehicles.add(vehicleDAO.findOne(vehicleId));
            }
        } catch (Exception e){
            log.error(new StringBuilder().append("ERROR: ").append(e.getMessage()).toString());
        }

        return listVehicles;
    }

    @RequestMapping(value ="/vehicle", method = RequestMethod.PUT)
    @ResponseBody
    public Vehicle updateVehicle(@RequestBody VehicleDTO vehicleParams) {

        Vehicle vehicle =  null;

        try{
            vehicle = vehicleDAO.findOne(vehicleParams.getId());
            vehicle.setBrand(vehicleParams.getBrand());
            vehicle.setCredential(vehicleParams.getCredential());
            vehicle.setModel(vehicleParams.getModel());
            vehicle.setRegistrationNumber(vehicleParams.getRegistration_number());
            vehicleDAO.save(vehicle);

        } catch (Exception e){
            log.error(new StringBuilder().append("ERROR: ").append(e.getMessage()).toString());
        }

        return vehicle;
    }

    @RequestMapping(value ="/vehicle", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMember(Long vehicleId) {
        try{
            Vehicle vehicle = vehicleDAO.findOne(vehicleId);
            vehicle.setDeleted(true);
            vehicleDAO.save(vehicle);
        } catch (Exception e){
            log.error(new StringBuilder().append("ERROR: ").append(e.getMessage()).toString());
            return new StringBuilder().append("ERROR: ").append(e.getMessage()).toString();
        }
        return "OK";
    }
}
