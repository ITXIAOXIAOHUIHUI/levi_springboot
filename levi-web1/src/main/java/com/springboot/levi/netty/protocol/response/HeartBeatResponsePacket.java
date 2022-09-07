package com.springboot.levi.netty.protocol.response;

import com.springboot.levi.netty.protocol.Packet;

import static com.springboot.levi.netty.protocol.command.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
