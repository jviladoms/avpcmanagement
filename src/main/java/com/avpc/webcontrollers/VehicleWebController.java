package com.avpc.webcontrollers;

import com.avpc.model.Member;
import com.avpc.model.Vehicle;
import com.avpc.restfulcontrollers.dto.VehicleDTO;
import com.avpc.services.VehicleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@Controller
public class VehicleWebController {
    private static final Logger log = Logger.getLogger(VehicleWebController.class);

    @Autowired
    VehicleService vehicleService;

    @Value("${image.storage.folder}")
    private String rootPath;

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

    @RequestMapping(value = "/admin/vehicles/uploadFile", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("vehicle") Long vehicleId,
                                    @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                File dir = new File(rootPath + File.separator + "vehicle");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + vehicleId);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                log.error("You failed to upload " + vehicleId + " => " + e.getMessage());
            }
        }

        return "Vehicle_update";
    }

    @RequestMapping(value = "/vehicle/image/display",method=RequestMethod.GET)
    @ResponseBody
    public byte[] vehicleImageDisplay(@RequestParam String name,HttpServletResponse response)  {
        response.setContentType("image/jpeg");
        File file;
        byte arr[]={};
        try{
            file = new File(rootPath + File.separator + "vehicle" + File.separator + name);
            if(!file.isFile()){
                name = "no-image.jpg";
                file = new File(rootPath+name);
            }
            arr= new byte[(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(arr,0,arr.length);
        }catch(Exception e){
            System.out.println(e);
        }
        return arr;
    }

}
