package com.example.ECommerceXindus.config;

import com.example.ECommerceXindus.security.JwtAuthenticationEntryPoint;
import com.example.ECommerceXindus.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFil;

    @Autowired
    UserDetailServiceConfig config;

    @Bean
    @SuppressWarnings("removal")
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
            httpSecurity
                    .cors(cors->cors.disable())
                    .csrf(csrf->csrf.disable())
                    .authorizeHttpRequests(req->req.requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                            .requestMatchers("/public/**").permitAll()
                            .requestMatchers("/auth/**").permitAll().anyRequest().authenticated())
                    .exceptionHandling(exp->exp.authenticationEntryPoint(point))
                    .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

            httpSecurity.addFilterBefore(this.jwtAuthenticationFil, UsernamePasswordAuthenticationFilter.class);

            return httpSecurity.build();
    }


    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(getPasswordEncoder());
        provider.setUserDetailsService(config.getUserDetailsService());
        return provider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder)throws Exception{
        return builder.getAuthenticationManager();
    }
}
