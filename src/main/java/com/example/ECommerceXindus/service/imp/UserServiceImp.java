package com.example.ECommerceXindus.service.imp;

import com.example.ECommerceXindus.customExceptions.ProductNotFoundException;
import com.example.ECommerceXindus.customExceptions.UserNotFoundException;
import com.example.ECommerceXindus.dot.requestDto.UserRequestDto;
import com.example.ECommerceXindus.dot.responseDto.UserResponseDto;
import com.example.ECommerceXindus.model.Product;
import com.example.ECommerceXindus.model.User;
import com.example.ECommerceXindus.reposirtory.ProductRepository;
import com.example.ECommerceXindus.reposirtory.UserRepository;
import com.example.ECommerceXindus.service.UserService;
import com.example.ECommerceXindus.transformer.UserTransfomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    final UserRepository userRepository;
    final ProductRepository productRepository;
    final BCryptPasswordEncoder passwordEncoder;

    @Autowired
     public UserServiceImp(UserRepository userRepository, ProductRepository productRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto add(UserRequestDto userDto) {

        User user= UserTransfomer.userFromUserDto(userDto);
        //Id will be auto generated..  it has username and password just Define Roles for it..

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        System.out.println(userDto.isAdmin());
        if(userDto.isAdmin()){
            user.setRoles("ROLE_USER,ROLE_ADMIN");
        }
        else {
            user.setRoles("ROLE_USER");
        }

        //all parameters are setted for the User..
        //let save the user to the repository..

        User savedUser=userRepository.save(user);
        UserResponseDto responseDto=UserTransfomer.userResponseDtoFromUser(savedUser);
        responseDto.setMessage("Your id id:"+savedUser.getId()+" Your Roles are"+savedUser.getRoles());

        return responseDto;
    }

    @Override
    public String addProductToWhishlist(int productId, int userId) {
        //first find the User with with userId..
        Optional<User>optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty())throw new UserNotFoundException("Unable to Find User with userId:"+userId);

        User user=optionalUser.get();

        //let's get this Product as well..
        Optional<Product>optionalProduct=productRepository.findById(productId);

        if(optionalProduct.isEmpty())throw new ProductNotFoundException("Unable to find the Product:"+productId);

        Product product=optionalProduct.get();

        //You have product as  well as user...
        List<Product>wishList=user.getWhishList();
        wishList.add(product);

        //product is setted..
        product.getUserList().add(user);

        userRepository.save(user);

        return "Product Has been added to the wishlist successfully";
    }
}
