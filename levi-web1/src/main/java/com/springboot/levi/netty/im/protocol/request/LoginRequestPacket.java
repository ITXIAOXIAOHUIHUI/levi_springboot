package com.springboot.levi.netty.im.protocol.request;

import com.springboot.levi.netty.im.protocol.Packet;
import lombok.Data;

import static com.springboot.levi.netty.im.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author jianghaihui
 * @date 2021/1/27 14:48
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;
    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }

}
