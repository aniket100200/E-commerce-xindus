package com.example.ECommerceXindus.controller;

import com.example.ECommerceXindus.dot.requestDto.UserRequestDto;
import com.example.ECommerceXindus.dot.responseDto.UserResponseDto;
import com.example.ECommerceXindus.reposirtory.UserRepository;
import com.example.ECommerceXindus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
public class PublicController {

    final UserService userService;

    @Autowired
    public PublicController( UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto userDto){

        UserResponseDto responseDto=userService.add(userDto);

        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/welcome")
    public String sayWelcome(){
        return "Welcome to the World of Backend";
    }
}
