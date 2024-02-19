package com.example.ECommerceXindus.transformer;

import com.example.ECommerceXindus.dot.requestDto.ProductRequestDto;
import com.example.ECommerceXindus.dot.requestDto.UserRequestDto;
import com.example.ECommerceXindus.dot.responseDto.UserResponseDto;
import com.example.ECommerceXindus.model.Product;
import com.example.ECommerceXindus.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserTransfomer {


    public static User userFromUserDto(UserRequestDto requestDto){
        return User.builder()
                .username(requestDto.getUsername())
                .build();
    };

    public static UserResponseDto userResponseDtoFromUser(User user){
        return UserResponseDto.builder()
                .Username(user.getUsername())
                .userId(user.getId())
                .build();
    }

    public static Product productFromProductReqeustDto(ProductRequestDto requestDto){
        return Product.builder()
                .img(requestDto.getImg())
                .title(requestDto.getTitle())
                .price(requestDto.getPrice()).build();
    }
}
