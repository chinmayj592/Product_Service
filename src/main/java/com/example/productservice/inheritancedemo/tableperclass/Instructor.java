package com.example.productservice.inheritancedemo.tableperclass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_instructors")
public class Instructor extends User {
    private String specialization;
    private Double avgRating;
}
