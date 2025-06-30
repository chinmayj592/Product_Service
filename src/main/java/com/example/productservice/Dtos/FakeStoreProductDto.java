package com.example.productservice.Dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;
}
//the data which we will get from FakeStore
//
// that will Mapped to this dto
