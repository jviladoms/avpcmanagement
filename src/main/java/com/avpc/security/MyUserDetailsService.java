package com.avpc.security;

import com.avpc.model.Member;
import com.avpc.model.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public UserDetails loadUserByUsername(String dni) {
        Member member = memberDAO.findByDni(dni);
        if (member == null) {
            throw new UsernameNotFoundException(dni);
        }
        return new MyUserPrincipal(member);
    }
}
