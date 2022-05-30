package com.springboot.levi.leviweb1.schuder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author jianghaihui
 * @date 2021/5/23 17:36
 */
@Slf4j
@Service
@DependsOn({"springBeanFactory"})
@Order(0)
public class MyStartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
      //  System.out.println("111111111111111");
    }
}
