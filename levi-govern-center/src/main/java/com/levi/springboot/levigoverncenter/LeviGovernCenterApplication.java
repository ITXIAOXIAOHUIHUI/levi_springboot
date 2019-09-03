package com.levi.springboot.levigoverncenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class LeviGovernCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeviGovernCenterApplication.class, args);
    }

}
