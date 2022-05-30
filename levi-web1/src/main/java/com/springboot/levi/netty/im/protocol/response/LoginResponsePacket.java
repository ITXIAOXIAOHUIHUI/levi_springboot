package com.springboot.levi.netty.im.protocol.response;

import com.springboot.levi.netty.im.protocol.Packet;
import lombok.Data;

import static com.springboot.levi.netty.im.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author jianghaihui
 * @date 2021/1/27 14:50
 */
@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
