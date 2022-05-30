package com.levi.springboot.email.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author jianghaihui
 * @date 2019/9/16 16:59
 */
@Service
public class ParamConfigService {
    @Value("${rocket.group}")
    public String rocketGroup;
    @Value("${rocket.topic}")
    public String rocketTopic;
    @Value("${rocket.tag}")
    public String rocketTag;
}
