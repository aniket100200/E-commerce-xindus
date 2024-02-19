package com.example.ECommerceXindus.controller;

import com.example.ECommerceXindus.dot.requestDto.JwtRequest;
import com.example.ECommerceXindus.dot.responseDto.JwtResponse;
import com.example.ECommerceXindus.security.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    final UserDetailsService userDetailsService;
    final AuthenticationManager manager;
    final JwtHelper jwtHelper;

    private Logger logger= LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(UserDetailsService userDetailsService, AuthenticationManager manager, JwtHelper jwtHelper) {
        this.userDetailsService = userDetailsService;
        this.manager = manager;
        this.jwtHelper = jwtHelper;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Mr. Khangar";
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
         this.doAuthenticate(request.getUsername(),request.getPassword());

        UserDetails userDetails= userDetailsService.loadUserByUsername(request.getUsername());
        logger.info(userDetails.toString());
        String token=this.jwtHelper.generateToken(userDetails);

      JwtResponse response = JwtResponse.builder().jwtToken(token)
                .username(userDetails.getUsername())
                .build();
      return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username,String password){
        UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(username,password);
        try {
            manager.authenticate(authentication);
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid Username or Password!!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(){
        return "Credentials Invalid!!";
    }
}
