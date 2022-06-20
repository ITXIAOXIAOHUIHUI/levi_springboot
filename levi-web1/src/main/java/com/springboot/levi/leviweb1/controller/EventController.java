package com.springboot.levi.leviweb1.controller;

import com.springboot.levi.leviweb1.event.EventDto;
import com.springboot.levi.leviweb1.event.service.EventService;
import com.springboot.levi.leviweb1.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-20 16:36
 */
@RestController
@RequestMapping(value = "/levi/event")
@Api(tags = "spring的事件触发")
@Slf4j
public class EventController {

    @Resource
    private EventService eventService;
    @PostMapping(value = "/submit/event")
    @ApiOperation(value = "事件的提交")
    public Response submitEvent(@RequestBody List<EventDto> eventDtos) {
        eventService.submit(eventDtos);
        return new Response();
    }
}
