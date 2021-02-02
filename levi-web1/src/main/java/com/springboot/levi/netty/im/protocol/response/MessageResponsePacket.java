package com.springboot.levi.netty.im.protocol.response;

import com.springboot.levi.netty.im.protocol.Packet;
import lombok.Data;

import static com.springboot.levi.netty.im.protocol.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {

        return MESSAGE_RESPONSE;
    }
}
