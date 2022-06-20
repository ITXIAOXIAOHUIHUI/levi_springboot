package com.springboot.levi.leviweb1.event.manager;

import com.springboot.levi.leviweb1.event.CreateJobInitEvent;
import com.springboot.levi.leviweb1.event.LeviEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-20 16:44
 */
@Slf4j
@Component
public class TransportOrderStateManager {

    @EventListener
    public void submitEvent(CreateJobInitEvent createJobInitEvent){
        System.out.println("this is create job init event");
    }
}
