package com.springboot.levi.netty.dto;

import lombok.Data;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-09-07 17:31
 */
@Data
public class ConnectDto {

    private String ip;
    private int port;
}
