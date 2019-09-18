package com.levi.springboot.controller;

import com.levi.springboot.service.RocketMqService;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.tomcat.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jianghaihui
 * @date 2019/9/16 16:33
 */
@RestController
public class RocketController {
    private static  final Logger LOGGER = LoggerFactory.getLogger(RocketMqService.class);
    @Resource
    private RocketMqService rocketMqService ;

    @RequestMapping("/sendMsg")
    public SendResult sendMsg (){
        String msg = "OpenAccount Msg";
        SendResult sendResult = null;
        try {
            sendResult = rocketMqService.openAccountMsg(msg) ;
        } catch (Exception e) {
            LOGGER.error("exception:{}", e);
            e.printStackTrace();
        }
        return sendResult ;
    }
}
