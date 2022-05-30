package com.springboot.levi.netty.im.protocol.request;

import com.springboot.levi.netty.im.protocol.Packet;
import lombok.Data;

import static com.springboot.levi.netty.im.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author jianghaihui
 * @date 2021/1/27 14:50
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
