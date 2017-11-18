package com.avpc.model.dao;


import com.avpc.model.Member;
import com.avpc.model.Service;
import org.springframework.data.jpa.repository.Query;
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
    @Query(value = "SELECT COALESCE(SUM(TIMESTAMPDIFF(HOUR, start_date, final_date)),0)  " +
            "FROM avpc.service a inner join avpc.service_members b on a.service_id = b.service_id " +
            "where b.member_id = ?1", nativeQuery = true)
    Integer findHoursOfServiceByMemberID(Long memberID);

}


