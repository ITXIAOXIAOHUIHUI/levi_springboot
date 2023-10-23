package com.springboot.levi.leviweb1.init;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Service;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-28 14:00
 */
@Service
public class SpringInitMethod implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("11111");
    }
}
