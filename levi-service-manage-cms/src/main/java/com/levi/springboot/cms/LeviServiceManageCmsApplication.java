package com.levi.springboot.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

@ComponentScan(basePackages={"com.levi.springboot"})//扫描common包下的类
@EnableEurekaClient
public class LeviServiceManageCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeviServiceManageCmsApplication.class, args);
    }

}
