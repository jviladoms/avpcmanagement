package com.avpc.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jviladoms on 11/8/17.
 */
@Controller
public class WelcomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(ModelMap model) {
        model.put("name", "jviladoms");
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(ModelMap model){
        return "login";
    }

    @RequestMapping(value = "/admin/inici")
    public String inici(ModelMap model){
        return "Inici";
    }

    @RequestMapping(value = "/admin/voluntaris")
    public String voluntaris(ModelMap model){
        return "Voluntaris";
    }

    @RequestMapping(value = "/admin/vehicles")
    public String vehicles(ModelMap model){
        return "Vehicles";
    }

    @RequestMapping(value = "/admin/serveis")
    public String serveis(ModelMap model){
        return "Serveis";
    }

    @RequestMapping(value = "/admin/mapa")
    public String mapa(ModelMap model){
        return "Mapa";
    }

    @RequestMapping(value = "/admin/missatges")
    public String misatges(ModelMap model){
        return "Missatges";
    }
}
