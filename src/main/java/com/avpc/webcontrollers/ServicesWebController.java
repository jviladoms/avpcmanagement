package com.avpc.webcontrollers;

import com.avpc.model.Member;
import com.avpc.model.Service;
import com.avpc.model.dao.ServiceDAO;
import com.avpc.restfulcontrollers.dto.ServiceDTO;
import com.avpc.services.MemberService;
import com.avpc.services.ServicesService;
import com.avpc.services.VehicleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ServicesWebController {
    private static final Logger log = Logger.getLogger(ServicesWebController.class);

    @Autowired
    ServicesService servicesService;

    @Autowired
    MemberService memberService;

    @Autowired
    ServiceDAO serviceDAO;

    @RequestMapping(value = "/admin/serveis")
    public String services(ModelMap model){
        model.put("services", servicesService.getServices());
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
            serviceDAO.save(service);
            //service = servicesService.addService(serviceDTO);
        } catch (IllegalArgumentException e ){
            log.error(e.getMessage());
        }

        model.put("service", service);

        return "Serveis_update";
    }

    @RequestMapping(value = "/admin/serveis/uploadFile", method = RequestMethod.POST)
    public @ResponseBody String uploadFileHandler(@RequestParam("service") Long serviceId,
                             @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("image.storage.folder");
                File dir = new File(rootPath + File.separator + "serveis");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + serviceId);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                Service service = serviceDAO.findOne(serviceId);

                service.setPhotoURL(serverFile.getAbsolutePath());
                serviceDAO.save(service);

                log.info("Server File Location="
                        + serverFile.getAbsolutePath());

            } catch (Exception e) {
                log.error("You failed to upload " + serviceId + " => " + e.getMessage());
            }
        }

        return "Serveis_update";
    }

    @RequestMapping(value = "/serveis/image/display",method=RequestMethod.GET)
    @ResponseBody
    public byte[] memberImageDisplay(@RequestParam String name,HttpServletResponse response)  {
        System.out.println("Show is invoked");
        response.setContentType("image/jpeg");
        File file;
        byte arr[]={};
        try{
            String rootPath = System.getProperty("image.storage.folder");
            file = new File(rootPath + File.separator + "serveis" + File.separator + name);
            if(file.isFile()){
                System.out.println("File is found");
            }
            else{
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

    @ModelAttribute("membersList")
    public List<Member> getMembersList()
    {
        return memberService.findMembers();
    }
}
