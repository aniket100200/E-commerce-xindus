package com.example.ECommerceXindus.service;

import com.example.ECommerceXindus.dot.requestDto.ProductRequestDto;
import com.example.ECommerceXindus.dot.responseDto.UserResponseDto;
import com.example.ECommerceXindus.model.User;

import java.util.List;

public interface AdminService {
    String deleteUserById(int userId);
    List<UserResponseDto>getAllUsers();
    String addProduct(ProductRequestDto requestDto);

}
