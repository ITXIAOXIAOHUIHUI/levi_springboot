package com.levi.springboot.service.impl;

import com.levi.springboot.wcs.WorkMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author jianghaihui
 * @date 2021/1/5 16:39
 */
@Component
@ConditionalOnProperty(value= WorkMode.SI_CARRY, havingValue = WorkMode.value )
public class TestService {

    public void getStation(){
        System.out.println("station ok okok");
    }
}
