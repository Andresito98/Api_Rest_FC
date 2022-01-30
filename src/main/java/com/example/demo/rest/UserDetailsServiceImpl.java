package com.example.demo.rest;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;

public class UserDetailsServiceImpl implements UserDetailsService {

    //userdao es userreposito
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users = userRepository.findByUsername(username);
        UserBuilder builder = null;

        if (users != null){
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.disabled(false);
            builder.password(users.getPassword());
            builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));

        }else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return builder.build();
    }


}
