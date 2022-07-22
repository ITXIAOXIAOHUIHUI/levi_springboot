package com.springboot.levi.leviweb1.design.qiaojie;

import org.springframework.stereotype.Service;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-05 16:31
 */
@Service
public class EmailMessage implements Imessage {

    @Override
    public void sendMsg(String user, String message) {
        System.out.println(user+"发送了信息："+message);
    }
}
