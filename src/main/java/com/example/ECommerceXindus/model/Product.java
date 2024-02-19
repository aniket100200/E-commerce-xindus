package com.example.ECommerceXindus.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String img;
    double price;

    @ManyToMany
    @JoinTable(name = "user_list",
    joinColumns = @JoinColumn(name = "Proucts_id"),
    inverseJoinColumns = @JoinColumn(name = "users_id"))
    List<User> userList=new ArrayList<>();

    @ManyToOne
    @JoinColumn
    User admin; //this user is related to Admin..
}
