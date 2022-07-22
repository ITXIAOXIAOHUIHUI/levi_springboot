package com.springboot.levi.leviweb1.design.qiaojie;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-05 16:33im
 */
public class NormalAbstractMessage extends AbstractionMessage {

    public NormalAbstractMessage( Imessage message) {
        super(message);
    }
}
