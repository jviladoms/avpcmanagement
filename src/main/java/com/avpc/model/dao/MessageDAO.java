package com.avpc.model.dao;

/**
 * Created by Jordi on 13/11/2016.
 */

import com.avpc.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
public interface MessageDAO extends CrudRepository<Message, Long> {
    @Query(value = "SELECT * FROM message m where message_id in (Select message_id from message_members where member_id = ?1)", nativeQuery = true)
    List<Message> findAllByDestinationMembers(Long member);
    List<Message> findAllByDateBetween(Date start, Date end);
}

