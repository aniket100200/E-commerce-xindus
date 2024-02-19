package com.example.ECommerceXindus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserDetailServiceConfig {
    @Bean
    public UserDetailsService getUserDetailsService(){
        return new CustomUserDetailsService();
        //Db Based Authentication...
    }
}
