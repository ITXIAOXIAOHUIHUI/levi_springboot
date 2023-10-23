package com.springboot.levi.leviweb1.design.guanchazhemoshi;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-10-19 17:25
 */
@Service
@Slf4j
public class PrintSubjct implements Subject {

    private String msg;

    List<Customer> customers = Lists.newArrayList();
    @Override
    public void addCustomer(Customer customer) {
        int index = customers.indexOf(customer);
        if(index < 0){
            customers.add(customer);
        }else{
            log.info("addCustomer index:{}",index);
        }

    }

    @Override
    public void removeCustomer(Customer customer) {
        int index = customers.indexOf(customer);
        if(index >= 0){
            customers.remove(customer);
        }
    }

    @Override
    public void notifyCustomer() {
        for(Customer customer : customers){
            customer.notifyMessage(this.msg);
        }
    }

    @Override
    public void setMessage(String msg) {
        this.msg = msg;
    }
}
