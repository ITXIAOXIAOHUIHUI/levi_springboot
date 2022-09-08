package com.springboot.levi.netty.client;

import com.springboot.levi.netty.dto.PosttingObject;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-09-07 18:50
 */
public class NettyClientSendMsg {


    public void sendMsg(String userId){
        PosttingObject posttingObject = NettyClient.concurrentHashMap.get(userId);
        //posttingObject.getNettyClient().cl
    }

}
