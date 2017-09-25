package com.avpc.services;

import com.avpc.model.Member;
import com.avpc.model.Message;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.MessageDAO;
import com.avpc.restfulcontrollers.dto.MessageDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private static final Logger log = Logger.getLogger(MessageService.class);

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private MemberDAO memberDAO;

    public Message addMessage(MessageDTO messageDTO){
        Message message = new Message();
        message.setMessageString(messageDTO.getMessage());
        message.setSendMember(memberDAO.findOne(messageDTO.getSendMember()));
        message.setDate(messageDTO.getDate());

        List<Member> members = getMembers(messageDTO.getDestinationMembers());
        message.setDestinationMembers(members);

        if(members.size() > 0){
            message.setDestinationMembers(members);
            messageDAO.save(message);
        } else {
            throw new RuntimeException("no valid message");
        }

        return message;
    }

    public Message addMessage(Message message){
        return messageDAO.save(message);
    }

    public List<Message> findUserIdMessages(Long userId){
        return messageDAO.findAllByDestinationMembers(userId);
    }

    public List<Message> findAllMessages(){
        List<Message> listMessage = new ArrayList<>();
        messageDAO.findAll().forEach(message -> listMessage.add(message));

        return listMessage;
    }

    public List<Message> findMessagesBetweenDays(Date start_date, Date end_date){
        return messageDAO.findAllByDateBetween(start_date,end_date);
    }

    public Message updateMessage(MessageDTO messageDTO) throws Exception {
        Message message;

        message = messageDAO.findOne(messageDTO.getId());
        message.setMessageString(messageDTO.getMessage());
        message.setSendMember(memberDAO.findOne(messageDTO.getSendMember()));

        List<Member> members = getMembers(messageDTO.getDestinationMembers());
        message.setDestinationMembers(members);

        if (members.size() > 0) {
            message.setDestinationMembers(members);
            messageDAO.save(message);
        } else {
            throw new Exception("no valid message");
        }

        return message;
    }

    public void deleteMessage(Long messageId){
        messageDAO.delete(messageId);
    }

    private List<Member> getMembers(Iterable<Long> ids){
        List<Member> members = new ArrayList<>();
        ids.forEach(member -> members.add(memberDAO.findOne(member)));
        return members.stream().filter(member -> member != null).collect(Collectors.toList());
    }
}
