package com.example.ecommerce.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class MapperUtil {

    @Bean
    public ModelMapper getMapper(){
        return new ModelMapper();
    }
}
