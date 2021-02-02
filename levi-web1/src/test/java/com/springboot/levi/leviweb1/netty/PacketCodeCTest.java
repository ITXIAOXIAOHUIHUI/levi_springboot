package com.springboot.levi.leviweb1.netty;

import com.springboot.levi.netty.common.JSONSerializer;

import com.springboot.levi.netty.common.Packet;
import com.springboot.levi.netty.common.Serializer;
import com.springboot.levi.netty.common.LoginRequestPacket;
import com.springboot.levi.netty.common.PacketCodeC;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author jianghaihui
 * @date 2021/1/27 11:10
 */
public class PacketCodeCTest {

    @Test
    public void encode() {

        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId(123);
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("password");

        PacketCodeC packetCodeC = new PacketCodeC();
        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
        System.out.println("包装二进制的内容:"+ byteBuf);
        Packet decodedPacket = packetCodeC.decode(byteBuf);
        System.out.println("解除二进制的内容:"+decodedPacket);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));

    }
}
