package com.example.aq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.aq")
public class AqApplication {
    public static void main(String[] args) {
        SpringApplication.run(AqApplication.class, args);
    }

}
