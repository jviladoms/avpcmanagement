package com.avpc.model.dao;


import com.avpc.model.Member;
import com.avpc.model.Service;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Jordi on 29/10/2016.
 */
@Transactional
public interface ServiceDAO extends CrudRepository<Service, Long> {
    List<Service> findByStartDateBetween(Date start,Date end);
    List<Service> findByMembersInServiceIn(Member member);
}


