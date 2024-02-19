package com.example.ECommerceXindus.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    String username;
    String password;
    String roles;

    @ManyToMany(mappedBy = "userList",cascade = CascadeType.ALL)
    List<Product>whishList=new ArrayList<>();

    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    List<Product>productList=new ArrayList<>();
}
