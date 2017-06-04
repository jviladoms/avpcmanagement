package com.avpc.restfulcontrollers;


import com.avpc.model.Vehicle;
import com.avpc.model.dao.VehicleDAO;
import com.avpc.restfulcontrollers.dto.VehicleDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value ="/vehicle")
public class VehicleController {
    private static final Logger log = Logger.getLogger(MemberController.class);

    @Autowired
    private VehicleDAO vehicleDAO;

    @RequestMapping(method = RequestMethod.POST)
    public void addVehicle(@RequestBody VehicleDTO vehicleParams,
                           HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try {
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
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @RequestMapping(value ="/{vehicleId}", method = RequestMethod.GET)
    @ResponseBody
    public Vehicle findVehicle(@PathVariable(value="vehicleId") Long vehicleId,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {

        Vehicle vehicle = null;

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try {
                vehicle =vehicleDAO.findOne(vehicleId);
            } catch (IllegalArgumentException e){
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        return vehicle;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Vehicle> findVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Vehicle> listVehicles = new ArrayList<>();

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try {
                vehicleDAO.findAll().forEach(member -> listVehicles.add(member));
                return listVehicles;

            } catch (Exception e){
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        return listVehicles;
    }

    @RequestMapping(value ="/{vehicleId}", method = RequestMethod.PUT)
    @ResponseBody
    public Vehicle updateVehicle(@PathVariable(value="vehicleId") Long vehicleId,
                                 @RequestBody VehicleDTO vehicleParams,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {

        Vehicle vehicle =  null;

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try {
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
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        return vehicle;
    }

    @RequestMapping(value ="/{vehicleId}", method = RequestMethod.DELETE)
    public void deleteVehicle(@PathVariable(value="vehicleId") Long vehicleId,
                             HttpServletRequest request, HttpServletResponse response) throws IOException{

        if(request.getSession().getAttribute(Constants.UID) != null) {
            try {
                vehicleDAO.delete(vehicleId);
            } catch (Exception e){
                log.error(e.getMessage());
                response.sendError(HttpStatus.CONFLICT.value());
            }
        }
        else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
