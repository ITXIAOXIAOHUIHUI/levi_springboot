package com.levi.springboot.controller;

import com.levi.springboot.model.Request;
import com.levi.springboot.model.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianghaihui
 * @date 2020/10/30 15:19
 */
@RestController
@RequestMapping(value =  "/device/interface")
public class DeviceController {

    @PostMapping(value = "/cxInfo")
    public Response getGKCXInfo(@RequestBody Request request){
        Response response = new Response();




        return  null;

    }

}

