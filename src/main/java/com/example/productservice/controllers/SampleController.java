package com.example.productservice.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {

    @GetMapping("/hello/{PathParameter}")
    public String SayHello(@PathVariable("PathParameter") int PrintNo ){

     StringBuilder sb = new StringBuilder();
     for(int i=0;i<PrintNo;i++){
         sb.append("  hello  ");
     }
         return sb.toString();
    }
}
