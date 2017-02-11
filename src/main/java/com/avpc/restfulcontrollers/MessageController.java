package com.avpc.restfulcontrollers;

import com.avpc.model.Message;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.MessageDAO;
import com.avpc.restfulcontrollers.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import com.avpc.model.Member;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jordi on 13/11/2016.
 */
@RestController
@RequestMapping(value ="/message")
public class MessageController {
    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private MemberDAO memberDAO;

    private static final Logger log = Logger.getLogger(MemberController.class);

    @RequestMapping(value ="/", method = RequestMethod.POST)
    @CrossOrigin
    public void addMessage(@RequestBody MessageDTO messageDTO,
                             HttpServletResponse response) throws IOException {

        try{
            Message message = new Message();
            message.setMessage(messageDTO.getMessage());
            message.setSendMember(memberDAO.findOne(messageDTO.getSendMember()));
            message.setDate(messageDTO.getDate());

            List<Member> members = getMembers(messageDTO.getDestinationMembers());
            message.setDestinationMembers(members);

            if(members.size() > 0){
                message.setDestinationMembers(members);
                messageDAO.save(message);
            } else {
                response.sendError(HttpStatus.BAD_REQUEST.value());
            }

        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/find_by_user/{userId}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public List<Message> getMessage(@PathVariable(value="userId") Long userId,
                                    HttpServletResponse response) throws IOException {

        List<Message> listMessages = new ArrayList<>();

        try{
            listMessages = messageDAO.findAllByDestinationMembers(userId);

        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return listMessages;
    }

    @RequestMapping(value ="/{messageId}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public Message getMessageWithId(@PathVariable(value="messageId") Long messageId,
                              HttpServletResponse response) throws IOException {

        Message message = null;

        try{
            message = messageDAO.findOne(messageId);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return message;
    }

    @RequestMapping(value ="/find_between_dates", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public List<Message> getBetweenDays(@RequestParam(value="start_date") Date start_date,
                                        @RequestParam(value="end_date") Date end_date,
                                        HttpServletResponse response) throws IOException{
        List<Message> listMessage = null;

        try{
            listMessage = messageDAO.findAllByDateBetween(start_date,end_date);
        }catch(IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }

        return listMessage;
    }

    @RequestMapping(value ="/{messageId}", method = RequestMethod.PUT)
    @CrossOrigin
    public void updateMessage(@PathVariable(value="messageId") Long messageId,
                              @RequestBody MessageDTO messageDTO,
                              HttpServletResponse response) throws IOException {
        Message message = null;

        try {
            message = messageDAO.findOne(messageId);
            message.setMessage(messageDTO.getMessage());
            message.setSendMember(memberDAO.findOne(messageDTO.getSendMember()));

            List<Member> members = getMembers(messageDTO.getDestinationMembers());
            message.setDestinationMembers(members);

            if (members.size() > 0) {
                message.setDestinationMembers(members);
                messageDAO.save(message);
            } else {
                response.sendError(HttpStatus.BAD_REQUEST.value());
            }
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    @RequestMapping(value ="/{messageId}", method = RequestMethod.DELETE)
    @CrossOrigin
    public void deleteMessage(@PathVariable(value="messageId") Long messageId,
                              HttpServletResponse response) throws IOException {
        try{
            messageDAO.delete(messageId);
        } catch (IllegalArgumentException e){
            log.error(e.getMessage());
            response.sendError(HttpStatus.CONFLICT.value());
        }
    }

    private List<Member> getMembers(Iterable<Long> ids){
        List<Member> members = new ArrayList<>();
        ids.forEach(member -> members.add(memberDAO.findOne(member)));
        return members.stream().filter(member -> member != null).collect(Collectors.toList());
    }
}
