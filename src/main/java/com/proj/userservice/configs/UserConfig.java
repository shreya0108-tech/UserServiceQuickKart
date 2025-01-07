package com.proj.userservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserConfig {
    @Bean
    BCryptPasswordEncoder getBcryptEncoder()
    {
        return new BCryptPasswordEncoder(16);
    }
}
