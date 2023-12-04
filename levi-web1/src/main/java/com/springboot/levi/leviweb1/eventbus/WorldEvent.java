package com.springboot.levi.leviweb1.eventbus;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-08-29 14:07
 */
@Data
@NoArgsConstructor
public class WorldEvent extends HelloEvent {
    private int eventNo;

    public WorldEvent(String name, int no){
        setEventName(name);
        setEventNo(no);
    }


}
