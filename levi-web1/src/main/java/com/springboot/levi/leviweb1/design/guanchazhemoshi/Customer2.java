package com.springboot.levi.leviweb1.design.guanchazhemoshi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-10-19 17:35
 */
@Service
@Slf4j
public class Customer2 implements Customer {

    private Subject subject;

    @Override
    public void notifyMessage(String msg) {
        log.info("print notify message:{}",msg);
    }

    @Override
    public void resitrySubject(Subject subject) {
        this.subject = subject;
        this.subject.addCustomer(this);
    }
}
