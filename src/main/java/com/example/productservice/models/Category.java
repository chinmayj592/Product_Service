package com.example.productservice.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter


@Entity(name = "categories")
public class Category extends BaseModel{
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category",cascade = {CascadeType.REMOVE})
    private List<Product> products;
}
