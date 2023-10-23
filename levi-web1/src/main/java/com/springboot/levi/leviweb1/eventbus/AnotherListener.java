package com.springboot.levi.leviweb1.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-08-29 14:34
 */
public class AnotherListener {
    /**
     * 监听 WorldEvent 类型及其父类型（HelloEvent 和 Object）的事件
     */
    @Subscribe
    public void processAnotherWorldEvent(WorldEvent event) {
        System.out.println("process another world event, no:" + event.getEventNo() + ", name:" + event.getEventName());
    }

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        GeventListener listener = new GeventListener();
        eventBus.register(listener);
        eventBus.post(new HelloEvent("hello"));

        EventBus anotherEventBus = new EventBus();
        AnotherListener anotherListener = new AnotherListener();
        anotherEventBus.register(anotherListener);
        anotherEventBus.post(new WorldEvent("AnotherWorld", 666));
    }
}
