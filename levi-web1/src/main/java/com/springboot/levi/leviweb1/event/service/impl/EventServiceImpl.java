package com.springboot.levi.leviweb1.event.service.impl;

import com.springboot.levi.leviweb1.event.CreateJobInitEvent;
import com.springboot.levi.leviweb1.event.EventDto;
import com.springboot.levi.leviweb1.event.LeviEvent;
import com.springboot.levi.leviweb1.event.manager.TransportOrderStateManager;
import com.springboot.levi.leviweb1.event.service.EventService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-20 16:38
 */
@Service
public class EventServiceImpl implements EventService {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void submit(List<EventDto> eventDtos) {
        for(EventDto eventDto :eventDtos){
            applicationEventPublisher.publishEvent(new CreateJobInitEvent(this,eventDto.getRobotJobId(), Arrays.asList(eventDto.getWarehouseId()),eventDto.getAgvCode()));
        }
    }
}
