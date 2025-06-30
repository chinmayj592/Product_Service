package com.example.productservice.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, Object> getredisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> RedisTemplate = new RedisTemplate<>();
        RedisTemplate.setConnectionFactory(redisConnectionFactory);


        return RedisTemplate;
    }

}
