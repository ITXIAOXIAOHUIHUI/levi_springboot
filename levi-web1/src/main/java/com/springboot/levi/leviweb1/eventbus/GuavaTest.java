package com.springboot.levi.leviweb1.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-08-29 14:12
 */
public class GuavaTest {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        GeventListener listener = new GeventListener();
        eventBus.register(listener);
        eventBus.post(new HelloEvent("hello"));
        eventBus.post(new WorldEvent("world",123));
    }
}
