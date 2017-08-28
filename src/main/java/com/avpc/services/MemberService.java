package com.avpc.services;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import com.avpc.model.dao.ServiceDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private static final Logger log = Logger.getLogger(MemberService.class);

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private ServiceDAO serviceDAO;

    public List<Member> findMember(){
        List<Member> listMember = new ArrayList<>();

        try{
            memberDAO.findAll().forEach(member -> listMember.add(member));
            listMember.forEach(member -> member.setServices(serviceDAO.findByMembersInServiceIn(member).size()));
        } catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("member not found");
        }

        return listMember;
    }
}
