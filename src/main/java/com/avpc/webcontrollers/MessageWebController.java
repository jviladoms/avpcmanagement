package com.avpc.webcontrollers;

import com.avpc.model.Message;
import com.avpc.services.MemberService;
import com.avpc.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class MessageWebController {
    @Autowired
    MessageService messageService;

    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/user/missatges")
    public String missatges(HttpServletRequest request, ModelMap model){
        List<Message> messages = messageService.findUserIdMessages((Long) request.getSession().getAttribute("userid"));
        model.put("messages", messages);
        model.addAttribute("message", new Message());
        return "Missatges";
    }

    @RequestMapping(value = "/user/missatges/add", method = RequestMethod.POST)
    public String missatges(HttpServletRequest request, @ModelAttribute("message") Message message, BindingResult serviceResult, ModelMap model){
        message.setDate(new Date());
        message.setDestinationMembers(memberService.findMembers());
        message.setSendMember(memberService.findMember((Long) request.getSession().getAttribute("userid")));
        messageService.addMessage(message);

        List<Message> messages = messageService.findUserIdMessages((Long) request.getSession().getAttribute("userid"));
        model.put("messages", messages);
        model.addAttribute("message", new Message());
        return "Missatges";
    }
}
