package com.springboot.levi.netty.common;

import lombok.Data;

import static com.springboot.levi.netty.common.Command.LOGIN_REQUEST;

/**
 * 登录请求数据包,
 */
@Data
public class LoginRequestPacket extends Packet {
    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
