package com.springboot.levi.leviweb1.event;

import com.springboot.levi.leviweb1.enums.BizType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-20 16:46
 */

public class CreateJobInitEvent extends LeviEvent {


    public CreateJobInitEvent(Object source, String robotCode, List<String> robotOrderIds, Long warehouseId) {
        super(source, robotCode, robotOrderIds, warehouseId);
    }

    @Override
    public String getType() {
        return this.getClass().getName();
    }
}
