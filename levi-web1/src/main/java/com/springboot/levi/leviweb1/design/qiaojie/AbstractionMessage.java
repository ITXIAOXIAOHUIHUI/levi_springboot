package com.springboot.levi.leviweb1.design.qiaojie;

import org.springframework.stereotype.Service;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-05 16:32
 */
public abstract  class AbstractionMessage {
     Imessage imessage;

    public AbstractionMessage(Imessage imessage){
        this.imessage = imessage;
    }

     public void sendMesage(String user, String message){
        this.imessage.sendMsg(user,message);
    }
}
