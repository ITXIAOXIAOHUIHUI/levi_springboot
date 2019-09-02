package com.levi.springboot.leviservicemanagecms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.levi.springboot"})
public class LeviServiceManageCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeviServiceManageCmsApplication.class, args);
    }

}
