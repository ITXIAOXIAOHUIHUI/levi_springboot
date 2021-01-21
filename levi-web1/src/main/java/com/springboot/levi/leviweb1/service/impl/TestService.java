package com.springboot.levi.leviweb1.service.impl;

import com.springboot.levi.leviweb1.wcs.WorkMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author jianghaihui
 * @date 2021/1/5 16:39
 */
@Component
@ConditionalOnProperty(value = WorkMode.SI_CARRY, havingValue = WorkMode.value)
public class TestService {

    public void getStation() {
        System.out.println("station ok okok");
    }
}
