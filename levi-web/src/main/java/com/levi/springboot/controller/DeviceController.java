package com.levi.springboot.controller;

import com.levi.springboot.model.Request;
import com.levi.springboot.model.Response;
import com.levi.springboot.utils.SoapClientUtils;
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

    private static final String KC_WSDL = "http://localhost:10080/soap/user?wsdl";
    @PostMapping(value = "/cxInfo")
    public Response getGKCXInfo(@RequestBody Request request){
          Response response = new Response();



        return  null;

    }

    @PostMapping(value = "/sendCbsStatus")
    public String sendCbsStatus(@RequestBody String requestBody){
        SoapClientUtils sc = new SoapClientUtils();
        String sendCbsStatus = sc.exchange(KC_WSDL, "SendCbsStatus", requestBody);

        System.out.println(sendCbsStatus);
        Response response = new Response();



        return  sendCbsStatus;

    }

    public static void main(String[] args) {
        System.out.println("1".equals("1"));
    }

}

