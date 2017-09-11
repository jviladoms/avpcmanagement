package com.avpc.webcontrollers;

import com.avpc.model.Vehicle;
import com.avpc.restfulcontrollers.dto.VehicleDTO;
import com.avpc.services.VehicleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VehicleWebController {
    private static final Logger log = Logger.getLogger(VehicleWebController.class);

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(value = "/admin/vehicles")
    public String voluntaris(ModelMap model){
        model.put("vehicles", vehicleService.getAllVehicles());
        return "Vehicles";
    }

    @RequestMapping(value = "/admin/vehicles_registration")
    public String vehicleRegistration(ModelMap model){ return "Vehicles_registration";}


    @RequestMapping(value = "/admin/register_vehicle", method = RequestMethod.POST)
    public String registerVehicle(@ModelAttribute VehicleDTO vehicleDTO, ModelMap model){
        Vehicle vehicle = null;

        try{
            vehicle = vehicleService.addVehicle(vehicleDTO);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        model.put("fromRegister", true);
        model.put("vehicle",vehicle);

        return "Vehicle_update";
    }

    @RequestMapping(value = "/admin/update_vehicle/{vehicleId}")
    public String updateVehicle(@PathVariable(value="vehicleId",required=false) Long vehicleId, @ModelAttribute VehicleDTO vehicleDTO, ModelMap model){
        Vehicle vehicle = null;

        try{
            vehicle = vehicleService.updateVehicle(vehicleId, vehicleDTO);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        model.put("fromUpdate", true);
        model.put("vehicle",vehicle);

        return "Vehicle_update";
    }

    @RequestMapping(value = "/admin/vehicle_update/{vehicleId}")
    public String updateVehicle(@PathVariable(value="vehicleId",required=false) Long vehicleId, ModelMap model){
        Vehicle vehicle = null;

        try{
            vehicle = vehicleService.getVehicle(vehicleId);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        }

        model.put("vehicle",vehicle);

        return "Vehicle_update";
    }

    @RequestMapping(value = "/admin/delete_vehicle/{vehicleId}")
    public String deleteVehicle(@PathVariable(value="vehicleId",required=false) Long vehicleId, ModelMap model){
        try {
            vehicleService.deleteVehicle(vehicleId);
            model.put("fromDelete",true);
            model.put("vehicles", vehicleService.getAllVehicles());
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
        }
        return "Vehicles";
    }

}
