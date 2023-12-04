package com.springboot.levi.leviweb1.eventbus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-08-29 14:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloEvent {
    private String eventName;
}
