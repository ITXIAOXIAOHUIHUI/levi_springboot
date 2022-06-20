package com.springboot.levi.leviweb1.event.service;

import com.springboot.levi.leviweb1.event.EventDto;

import java.util.List;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-20 16:38
 */
public interface EventService {

    public void submit(List<EventDto> eventDtos);
}
