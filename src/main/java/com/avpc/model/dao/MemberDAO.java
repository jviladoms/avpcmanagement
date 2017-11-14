package com.avpc.model.dao;


import javax.transaction.Transactional;

import com.avpc.model.Member;
import org.springframework.data.repository.CrudRepository;
/**
 * Created by Jordi on 29/10/2016.
 */
@Transactional
    public interface MemberDAO extends CrudRepository<Member, Long> {
        Member findByDni(String dni);
    }


