package com.example.productservice.inheritancedemo.joinedtable;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}

//whenever there are multiple ways of doing something that is called as strategy
//here there are multiple ways of representing inheritance so this is called as strategy
//here we are using joined table strategy