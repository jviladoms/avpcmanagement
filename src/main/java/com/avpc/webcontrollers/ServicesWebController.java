package com.avpc.webcontrollers;

import com.avpc.model.Member;
import com.avpc.model.Service;
import com.avpc.model.Vehicle;
import com.avpc.services.MemberService;
import com.avpc.services.ServicesService;
import com.avpc.services.VehicleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ServicesWebController {
    private static final Logger log = Logger.getLogger(ServicesWebController.class);

    @Autowired
    ServicesService servicesService;

    @Autowired
    MemberService memberService;

    @Autowired
    VehicleService vehicleService;

    @Value("${image.storage.folder}")
    private String rootPath;

    @RequestMapping(value = "/admin/serveis")
    public String services(@RequestParam(value="initDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initDate,
                           @RequestParam(value="endDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                           ModelMap model){

        if (initDate == null) {
            initDate = LocalDate.now().minusMonths(3);
        }

        if (endDate == null) {
            endDate = LocalDate.now().plusDays(1);
        }

        model.put("services", servicesService.getServicesBetweenDays(initDate,endDate));
        model.addAttribute("initDate",initDate);
        model.addAttribute("endDate",endDate);
        return "Serveis";
    }

    @RequestMapping(value = "/admin/serveis_registration")
    public String serveisRegistration(ModelMap model){
        List<Member> memberList = memberService.findMembers();
        model.addAttribute("members", memberList);
        model.addAttribute("service", new Service());
        return "Serveis_registration";
    }

    @RequestMapping(value = "/admin/serveis_update/{serviceId}")
    public String serveisUpdate(@PathVariable(value="serviceId",required=false) Long serviceId, ModelMap model){
        Service service = servicesService.getService(serviceId);
        model.addAttribute("service", service);
        return "Serveis_update";
    }

    @RequestMapping(value = "/admin/update_serveis", method = RequestMethod.POST)
    public String updateServei(@ModelAttribute("service") Service service, BindingResult serviceResult, ModelMap model){
        servicesService.updateService(service);
        model.addAttribute("fromUpdate", true);
        model.addAttribute("service", service);
        return "Serveis_update";
    }

    @RequestMapping(value ="/admin/addService", method = RequestMethod.POST)
    public String addServices(@ModelAttribute("service") Service service, BindingResult serviceResult, ModelMap model ){
        try{
            servicesService.addService(service);
        } catch (IllegalArgumentException e ){
            log.error(e.getMessage());
        }

        model.put("service", service);

        return "Serveis_update";
    }

    @RequestMapping(value = "/admin/serveis/uploadFile", method = RequestMethod.POST)
    public @ResponseBody String uploadFileHandler(@RequestParam("service") Long serviceId,
                             @RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            BufferedOutputStream stream = null;
            try {
                byte[] bytes = file.getBytes();

                File dir = new File(rootPath + File.separator + "serveis");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + serviceId);
                stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                Service service = servicesService.getService(serviceId);
                service.setPhotoURL(serverFile.getAbsolutePath());
                servicesService.updateService(service);

                log.info("Server File Location="
                        + serverFile.getAbsolutePath());

            } catch (Exception e) {
                log.error("You failed to upload " + serviceId + " => " + e.getMessage());
            } finally {
                stream.close();
            }
        }

        return "Serveis_update";
    }

    @RequestMapping(value = "/serveis/image/display",method=RequestMethod.GET)
    @ResponseBody
    public byte[] memberImageDisplay(@RequestParam String name,HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        File file;
        byte arr[]={};
        FileInputStream fis = null;
        try{
            file = new File(rootPath + File.separator + "serveis" + File.separator + name);
            if(!file.isFile()){
                name = "no-image.jpg";
                file = new File(rootPath+name);
            }
            arr= new byte[(int)file.length()];
            fis = new FileInputStream(file);
            fis.read(arr,0,arr.length);
        }catch(Exception e){
            System.out.println(e);
        } finally {
            fis.close();
        }
        return arr;
    }

    @ModelAttribute("membersList")
    public List<Member> getMembersList()
    {
        return memberService.findMembers();
    }

    @ModelAttribute("vehiclesList")
    public List<Vehicle> getVehicleList()
    {
        return vehicleService.getAllVehicles();
    }
}
