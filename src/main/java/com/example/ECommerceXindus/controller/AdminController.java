package com.example.ECommerceXindus.controller;

import com.example.ECommerceXindus.customExceptions.UserNotFoundException;
import com.example.ECommerceXindus.dot.requestDto.ProductRequestDto;
import com.example.ECommerceXindus.dot.responseDto.UserResponseDto;
import com.example.ECommerceXindus.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    //Admin has right to manipulate user like adding user and deleting user
   final AdminService adminService;

   @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public ResponseEntity sayHello(){
        return ResponseEntity.ok("Hello!! Admin");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id){
        try{
            String resp=adminService.deleteUserById(id);

            return new ResponseEntity(resp, HttpStatus.OK);
        }catch (UserNotFoundException userNotFoundException){
            return new ResponseEntity(userNotFoundException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/get-all")
    public ResponseEntity getAllUsers(){

       List<UserResponseDto> list=adminService.getAllUsers();

       return ResponseEntity.ok(list);
    }

    @PostMapping("/add-product")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto requestDto){
       try{
           String resp=adminService.addProduct(requestDto);
           return new ResponseEntity(resp,HttpStatus.CREATED);
       }
       catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }
    }

}
