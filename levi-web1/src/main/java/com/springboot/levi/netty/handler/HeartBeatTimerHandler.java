package com.springboot.levi.netty.handler;

import com.springboot.levi.netty.protocol.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @program: levi_springboot
 * @description: 心跳检测
 * @author: jhh
 * @create: 2022-07-26 13:41
 */
@Slf4j
public class HeartBeatTimerHandler  extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 2;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }


    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(()->{
            log.info("heartBeat isActive:{} heartBeatRequestPacket:{}",ctx.channel().isActive(),new HeartBeatRequestPacket());
            if(ctx.channel().isActive()){
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        },HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }


}
