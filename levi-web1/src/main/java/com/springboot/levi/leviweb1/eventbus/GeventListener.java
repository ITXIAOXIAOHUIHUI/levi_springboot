package com.springboot.levi.leviweb1.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @program: levi_springboot
 * @description: 事件监听器，可以监听多个事件。处理方法添加@subsribe注解即可
 * @author: jhh
 * @create: 2023-08-29 14:08
 */
public class GeventListener {

    /**
     * 监听HelloEvent 类型及其父类型（Object）的事件
     * @param event
     */
    @Subscribe
    public void processEvent(HelloEvent event){
        System.out.println("process hello event, name:" + event.getEventName());
    }

    /**
     * 监听 WorldEvent 类型及其父类型（HelloEvent 和 Object）的事件
     */
    @Subscribe
    public void processWorldEvent(WorldEvent event){
        System.out.println("process world eventV1, no:" + event.getEventNo() + ", name:" + event.getEventName());
    }

    /**
     * 注册多个监听器 监听同一事件
     * @param event
     */
    @Subscribe
    public void processWorldEventV2(WorldEvent event) {
        System.out.println("process world eventV2, no:" + event.getEventNo() + ", name:" + event.getEventName());
    }

    @Subscribe
    public void processObject(Object object) {
        System.out.println("process common event, class:" + object.getClass().getSimpleName());
    }
}
