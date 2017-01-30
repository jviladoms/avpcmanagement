package com.avpc.restfulcontrollers;

import com.avpc.model.Message;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.MessageDAO;
import com.avpc.restfulcontrollers.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.avpc.model.Member;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jordi on 13/11/2016.
 */
@RestController
public class MessageController {
    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private MemberDAO memberDAO;

    private static final Logger log = Logger.getLogger(MemberController.class);

    @RequestMapping(value ="/message", method = RequestMethod.POST)
    public String addMessage(@RequestBody MessageDTO messageDTO) {

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
                throw new Exception("no valid message");
            }

        } catch (Exception e){
            log.error(e.getMessage());
            return "ERROR: " + e.getMessage();
        }
        return "OK";
    }

    @RequestMapping(value ="/message", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getMessage(@RequestParam(value="user_id",required=false) Long userId) {

        final List<Message> listMessage = new ArrayList<>();

        try{
            if (userId == null){
                messageDAO.findAll().forEach(message -> listMessage.add(message));
                return listMessage;
            } else {
                return messageDAO.findAllByDestinationMembers(userId);
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }

        return listMessage;
    }

    @RequestMapping(value ="/message/find_between_dates", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getBetweenDays(@RequestParam(value="start_date") Date start_date,
                                        @RequestParam(value="end_date") Date end_date) {

        List<Message> listMessage = messageDAO.findAllByDateBetween(start_date,end_date);

        return listMessage;
    }

    @RequestMapping(value ="/message", method = RequestMethod.PUT)
    @ResponseBody
    public Message updateMessage(@RequestBody MessageDTO messageDTO) {

        Message message = null;

        try {
            message = messageDAO.findOne(messageDTO.getId());
            message.setMessage(messageDTO.getMessage());
            message.setSendMember(memberDAO.findOne(messageDTO.getSendMember()));

            List<Member> members = getMembers(messageDTO.getDestinationMembers());
            message.setDestinationMembers(members);

            if (members.size() > 0) {
                message.setDestinationMembers(members);
                messageDAO.save(message);
            } else {
                throw new Exception("no valid message");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return message;
    }

    @RequestMapping(value ="/message", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteMessage(Long messageId) {

        try{
            messageDAO.delete(messageId);
        } catch (Exception e){
            return "ERROR";
        }

        return "OK";
    }

    private List<Member> getMembers(Iterable<Long> ids){
        List<Member> members = new ArrayList<>();
        ids.forEach(member -> members.add(memberDAO.findOne(member)));
        return members.stream().filter(member -> member != null).collect(Collectors.toList());
    }
}
