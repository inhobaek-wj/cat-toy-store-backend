package com.jake.cattoystore;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CatToyStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatToyStoreApplication.class, args);
    }

    @Bean
    public Mapper modelMapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }
}
