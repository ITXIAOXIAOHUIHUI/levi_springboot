package com.springboot.levi.leviweb1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springboot.levi.leviweb1.mapper")
public class LeviWeb1Application {

    public static void main(String[] args) {
        SpringApplication.run(LeviWeb1Application.class, args);
    }

}
