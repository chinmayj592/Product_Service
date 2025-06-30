package com.example.productservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    //create a bean of RestTemplate

    @Bean
    public RestTemplate createRestTemplate(){
     return new RestTemplate();
    }
}
