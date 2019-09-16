package com.levi.springboot.service;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author jianghaihui
 * @date 2019/9/16 16:38
 */
public interface RocketMqService {
    SendResult openAccountMsg(String msgInfo);
}
