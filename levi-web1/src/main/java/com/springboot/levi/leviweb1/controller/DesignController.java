package com.springboot.levi.leviweb1.controller;

import com.springboot.levi.leviweb1.design.guanchazhemoshi.Customer;
import com.springboot.levi.leviweb1.design.guanchazhemoshi.Customer1;
import com.springboot.levi.leviweb1.design.guanchazhemoshi.PrintSubjct;
import com.springboot.levi.leviweb1.design.guanchazhemoshi.Subject;
import com.springboot.levi.leviweb1.event.EventDto;
import com.springboot.levi.leviweb1.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-10-19 17:53
 */

@RestController
@RequestMapping(value = "/levi/design")
@Api(tags = "设计模式测试")
@Slf4j
public class DesignController {

    @Resource
    private Subject subject;

    @Resource
    private List<Customer> customers;

    @PostMapping(value = "/guanchazhe/test")
    @ApiOperation(value = "观察者模式的的观察者通知")
    public Response submitEvent(@RequestParam("msg") String msg) {
        subject.setMessage(msg);
        for(Customer customer : customers){
            customer.resitrySubject(subject);
        }
        subject.notifyCustomer();
        return new Response();
    }


    @PostMapping(value = "/guanchazhe/remove")
    @ApiOperation(value = "观察者模式的的观察者移除")
    public Response removeCustomer() {
        for(Customer customer : customers){
            subject.removeCustomer(customer);
        }
        return new Response();
    }




}
