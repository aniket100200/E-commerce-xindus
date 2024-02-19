package com.example.ECommerceXindus.controller;

import com.example.ECommerceXindus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public ResponseEntity sayHello(){
        return ResponseEntity.ok("Hello User");
    }

    @PostMapping("/add-product")
    public ResponseEntity addProductToWishList(@RequestParam int productId,@RequestParam int userId){
       try{
           String resp=userService.addProductToWhishlist(productId, userId);

           return new ResponseEntity(resp, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/curr-user")
    public String getCurrentUser(Principal principal){
        return principal.getName();
    }
}
