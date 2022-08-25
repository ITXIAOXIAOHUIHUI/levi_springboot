package com.springboot.levi.netty.protocol;

import static com.springboot.levi.netty.protocol.command.Command.HEARTBEAT_REQUEST;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-26 13:47
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
