package com.example.ECommerceXindus.service.imp;

import com.example.ECommerceXindus.customExceptions.UserNotFoundException;
import com.example.ECommerceXindus.dot.requestDto.ProductRequestDto;
import com.example.ECommerceXindus.dot.responseDto.UserResponseDto;
import com.example.ECommerceXindus.model.Product;
import com.example.ECommerceXindus.model.User;
import com.example.ECommerceXindus.reposirtory.UserRepository;
import com.example.ECommerceXindus.service.AdminService;
import com.example.ECommerceXindus.transformer.UserTransfomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImp implements AdminService {
    final UserRepository userRepository;

    @Autowired
    public AdminServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String deleteUserById(int userId) {
        Optional<User>optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty())throw new UserNotFoundException("Invalid UserId");
        User user=optionalUser.get();
        userRepository.delete(user);
        return "User with username:"+user.getUsername()+" has been deleted Successfully";
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User>userList=userRepository.findAll();
       List<UserResponseDto>userResponseDtos= new ArrayList<>();
        for (User user:userList) {
            userResponseDtos.add(UserTransfomer.userResponseDtoFromUser(user));
        }
        return userResponseDtos;
    }

    @Override
    public String addProduct(ProductRequestDto requestDto) {
        //let's find the User on the basis of userId..
        Optional<User>optionalUser=userRepository.findById(requestDto.getUserId());
        if(optionalUser.isEmpty())throw  new UserNotFoundException("unable to find the User");
        User user=optionalUser.get();

        //I've the User..
        Product product=UserTransfomer.productFromProductReqeustDto(requestDto);

        //Just I've to save the  User Again...
        //before that Add this product in the whishlist of the User as this is admin..
        product.setAdmin(user);

        List<Product>productList=user.getProductList();

        productList.add(product);

        //just save your User will save your Product to due to cascading..

      User  savedUser=  userRepository.save(user);


        return savedUser.toString();
    }
}
