package com.avpc.restfulcontrollers;

import com.avpc.model.Message;

import com.avpc.restfulcontrollers.dto.MessageDTO;
import com.avpc.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    private static final Logger log = Logger.getLogger(MemberController.class);

    @RequestMapping(value ="/message", method = RequestMethod.POST)
    public Message addMessage(@RequestBody MessageDTO messageDTO, HttpServletResponse response) throws IOException {
        Message message = null;

        try{
          message = messageService.addMessage(messageDTO);
        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
        return message;
    }

    @RequestMapping(value ="/message", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getMessage(@RequestParam(value="user_id",required=false) Long userId, HttpServletResponse response) throws IOException {

        List<Message> listMessage = new ArrayList<>();

        try{
            if (userId == null){
                listMessage = messageService.findAllMessages();
            } else {
                listMessage = messageService.findUserIdMessages(userId);
            }
        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return listMessage;
    }

    @RequestMapping(value ="/message/find_between_dates", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getBetweenDays(@RequestParam(value="start_date") Date start_date,
                                        @RequestParam(value="end_date") Date end_date, HttpServletResponse response) throws IOException {

        List<Message> listMessage = null;

        try{
            listMessage = messageService.findMessagesBetweenDays(start_date,end_date);
        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return listMessage;
    }

    @RequestMapping(value ="/message", method = RequestMethod.PUT)
    @ResponseBody
    public Message updateMessage(@RequestBody MessageDTO messageDTO, HttpServletResponse response) throws IOException {

        Message message = null;

        try {
            messageService.updateMessage(messageDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return message;
    }

    @RequestMapping(value ="/message", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteMessage(Long messageId, HttpServletResponse response) throws IOException {

        try{
            messageService.deleteMessage(messageId);
        } catch (Exception e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }
}
