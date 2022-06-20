package com.springboot.levi.leviweb1.event;

import lombok.Data;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-20 16:59
 */
@Data
public class EventDto {

    private String robotJobId;

    private String warehouseId;

    private long  agvCode;
}
