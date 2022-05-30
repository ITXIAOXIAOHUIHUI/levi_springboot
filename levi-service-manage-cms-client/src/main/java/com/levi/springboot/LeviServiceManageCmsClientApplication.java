package com.levi.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms")//扫描实体类
@ComponentScan(basePackages = {"com.levi.springboot.client"})//扫描common下的所有类
public class LeviServiceManageCmsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeviServiceManageCmsClientApplication.class, args);
    }

}
