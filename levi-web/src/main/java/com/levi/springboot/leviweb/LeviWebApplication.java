package com.levi.springboot.leviweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.levi.springboot", "com.levi.springboot.levicommon"})
public class LeviWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeviWebApplication.class, args);
    }

}
