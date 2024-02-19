package com.example.ECommerceXindus.service;


import com.example.ECommerceXindus.dot.requestDto.UserRequestDto;
import com.example.ECommerceXindus.dot.responseDto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
   UserResponseDto add(UserRequestDto requestDto);
   String addProductToWhishlist(int productId,int userId);
}
