package com.avpc.restfulcontrollers;


import com.avpc.model.Vehicle;
import com.avpc.model.dao.VehicleDAO;
import com.avpc.restfulcontrollers.dto.VehicleDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value ="/vehicle")
public class VehicleController {
    private static final Logger log = Logger.getLogger(MemberController.class);

    @Autowired
    private VehicleDAO vehicleDAO;

    @RequestMapping(value ="/", method = RequestMethod.POST)
    @CrossOrigin
    public void addVehicle(@RequestBody VehicleDTO vehicleParams,
                           HttpServletResponse response) throws IOException {
        try{
            Vehicle vehicle = new Vehicle();
            vehicle.setBrand(vehicleParams.getBrand());
            vehicle.setCredential(vehicleParams.getCredential());
            vehicle.setModel(vehicleParams.getModel());
            vehicle.setRegistrationNumber(vehicleParams.getRegistration_number());
            vehicleDAO.save(vehicle);
        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/{vehicleId}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public Vehicle findVehicle(@PathVariable(value="vehicleId") Long vehicleId,
                                     HttpServletResponse response) throws IOException {

        Vehicle vehicle = null;

        try{
            vehicle =vehicleDAO.findOne(vehicleId);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return vehicle;
    }

    @RequestMapping(value ="/", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public List<Vehicle> findVehicle(HttpServletResponse response) throws IOException {
        List<Vehicle> listVehicles = new ArrayList<>();

        try{
            vehicleDAO.findAll().forEach(member -> listVehicles.add(member));
            return listVehicles;

        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return listVehicles;
    }

    @RequestMapping(value ="/{vehicleId}", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public Vehicle updateVehicle(@PathVariable(value="vehicleId") Long vehicleId,
                                 @RequestBody VehicleDTO vehicleParams,
                                 HttpServletResponse response) throws IOException {

        Vehicle vehicle =  null;

        try{
            vehicle = vehicleDAO.findOne(vehicleId);
            vehicle.setBrand(vehicleParams.getBrand());
            vehicle.setCredential(vehicleParams.getCredential());
            vehicle.setModel(vehicleParams.getModel());
            vehicle.setRegistrationNumber(vehicleParams.getRegistration_number());
            vehicleDAO.save(vehicle);

        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return vehicle;
    }

    @RequestMapping(value ="/{vehicleId}", method = RequestMethod.DELETE)
    @CrossOrigin
    public void deleteMember(@PathVariable(value="vehicleId") Long vehicleId,
                               HttpServletResponse response) throws IOException{
        try{
            vehicleDAO.delete(vehicleId);
        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }
}
