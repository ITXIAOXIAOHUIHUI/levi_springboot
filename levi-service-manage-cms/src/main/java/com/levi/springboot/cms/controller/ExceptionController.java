package com.levi.springboot.cms.controller;

import com.levi.springboot.utils.ResourceNotFoundExceptionExt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianghaihui
 * @date 2019/9/15 17:15
 */
@RestController
@RequestMapping("/api")
public class ExceptionController {

    @GetMapping("/illegalArgumentException")
    public void throwException(){
        throw  new IllegalArgumentException();
    }

    @GetMapping("/resourceNotFoundException")
    public void throwException2(){
        throw new ResourceNotFoundExceptionExt("没有权限");
    }
    @GetMapping
    public void exception2(){
        int a = 10/0;
    }
}
