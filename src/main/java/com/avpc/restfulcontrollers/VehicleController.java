package com.avpc.restfulcontrollers;


import com.avpc.model.Vehicle;
import com.avpc.restfulcontrollers.dto.VehicleDTO;
import com.avpc.services.VehicleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordi on 31/10/2016.
 */

@RestController
public class VehicleController {
    private static final Logger log = Logger.getLogger(MemberController.class);

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value ="/vehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody VehicleDTO vehicleDTO, HttpServletResponse response) throws IOException {
        Vehicle vehicle = null;
        try{
            vehicle = vehicleService.addVehicle(vehicleDTO);
        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
        return vehicle;
    }

    @RequestMapping(value ="/vehicle", method = RequestMethod.GET)
    @ResponseBody
    public List<Vehicle> findVehicle(@RequestParam(value="id",required=false) Long vehicleId, HttpServletResponse response) throws IOException {
        List<Vehicle> listVehicles = new ArrayList<>();

        try{
            if (vehicleId == null) {
                listVehicles = vehicleService.getAllVehicles();
            } else {
                listVehicles.add(vehicleService.getVehicle(vehicleId));
            }
        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return listVehicles;
    }

    @RequestMapping(value ="/vehicle", method = RequestMethod.PUT)
    @ResponseBody
    public Vehicle updateVehicle(@RequestBody VehicleDTO vehicleParams, HttpServletResponse response) throws IOException {

        Vehicle vehicle =  null;

        try{
            vehicle = vehicleService.updateVehicle(vehicleParams);
        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return vehicle;
    }

    @RequestMapping(value ="/vehicle", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMember(Long vehicleId, HttpServletResponse response) throws IOException {
        try{
            vehicleService.deleteVehicle(vehicleId);
        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
        return "OK";
    }
}
