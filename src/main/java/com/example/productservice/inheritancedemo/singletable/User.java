package com.example.productservice.inheritancedemo.singletable;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//to distinguish between multiple users wee need a column of type user
//that column is called Discriminator_column
@DiscriminatorColumn(
        name = "userType",
        discriminatorType = DiscriminatorType.INTEGER
)

@DiscriminatorValue(value= "0")
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
