package com.springboot.levi.leviweb1.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-11-24 11:29
 */
@RestController
@RequestMapping(value = "/levi/netty")
@Api(tags = "JVM分析")
@Slf4j
public class NettyController {

    @RequestMapping(value = "/getCache", method = RequestMethod.GET)
    public void getServerCache() throws InterruptedException {

    }
}
