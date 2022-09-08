package com.springboot.levi.netty.dto;

import com.springboot.levi.netty.client.NettyClient;
import com.springboot.levi.netty.handler.HeartBeatTimerHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.Data;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-09-07 18:45
 */
@Data
public class PosttingObject {

    private HeartBeatTimerHandler nettyClient;

    private NioEventLoopGroup nioEventLoopGroup;
}
