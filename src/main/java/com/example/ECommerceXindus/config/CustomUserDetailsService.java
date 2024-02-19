package com.example.ECommerceXindus.config;

import com.example.ECommerceXindus.config.UserDetailsCreator;
import com.example.ECommerceXindus.model.User;
import com.example.ECommerceXindus.reposirtory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user==null)throw new UsernameNotFoundException("Invalid Username");

        return new UserDetailsCreator(user);
    }
}
