package service;

import dal.UserDal;
import dal.UserDalImpl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pojo.User;


import java.util.HashSet;

import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {


    private UserDal userDal = new UserDalImpl();


    @Override
   //@Transactional(readOnly = true)
    public UserDetails loadUserByUserName(String email) {
        User user = userDal.readUserByEmail(email);
        if (user == null) throw new UsernameNotFoundException(email);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (UserToCompany role : userToCompany.getRole()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));


        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassWord(),grantedAuthorities );
    }

}

