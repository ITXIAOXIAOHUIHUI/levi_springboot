package com.springboot.levi.leviweb1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.springboot.levi.leviweb1.mapper")
@ComponentScan(basePackages = {"com.springboot.levi"})
@EnableScheduling
@EnableAsync
public class LeviWeb1Application {

    public static void main(String[] args) {
        SpringApplication.run(LeviWeb1Application.class, args);
    }

}
