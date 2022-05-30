package com.levi.springboot.email.service.impl;

import com.levi.springboot.email.service.RocketMqService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jianghaihui
 * @date 2019/9/16 16:39
 */
@Service
public class RocketMqServiceImpl implements RocketMqService {

    @Resource
    private DefaultMQProducer defaultMQProducer;
    @Resource
    private ParamConfigService paramConfigService;

    @Override
    public SendResult openAccountMsg(String msgInfo) {
        // 可以不使用Config中的Group
        //defaultMQProducer.setProducerGroup(paramConfigService.rocketGroup);
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message("test",
                    paramConfigService.rocketTag,
                    "open_account_key", msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }
}
