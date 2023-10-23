package com.springboot.levi.netty.handler;

import com.springboot.levi.netty.client.ClientNettyClient;
import com.springboot.levi.netty.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @program: levi_springboot
 * @description: 心跳检测
 * @author: jhh
 * @create: 2022-07-26 13:41
 */
@Slf4j
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 2;

    private ClientNettyClient clientNettyClient;
    private ChannelHandlerContext channelHandlerContext;



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelHandlerContext = ctx;
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channelRegistered :+"+ctx);
        //ctx.fireChannelRegistered();
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("channel handler context ctx:" + ctx);
        SocketAddress address = ctx.channel().remoteAddress();
        String ip = ((InetSocketAddress) address).getAddress().getHostAddress();
        int port = ((InetSocketAddress) address).getPort();
        log.info("ip:{},port:{}",ip,port);
        ctx.pipeline().remove(this);
        ctx.channel().close();
        reconnection(ctx);
    }

    private void reconnection(ChannelHandlerContext ctx){
        log.info("5s之后重新建立连接");
        SocketAddress address = ctx.channel().remoteAddress();
        String ip = ((InetSocketAddress) address).getAddress().getHostAddress();
        int port = ((InetSocketAddress) address).getPort();
        log.info("ip:{},port:{}",ip,port);

        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                boolean connect = ClientNettyClient.connect(ip,port);
                if (connect) {
                    log.info("重新连接成功");
                } else {
                    reconnection(ctx);
                }
            }
        }, 5, TimeUnit.SECONDS);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        if(cause instanceof java.io.IOException){
            log.warn("exceptionCaught : client close");
        }else{
            cause.printStackTrace();
        }
        ctx.pipeline().remove(this);
        ctx.channel().close();
        reconnection(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            //该事件需要配合 io.netty.handler.timeout.IdleStateHandler使用
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.ALL_IDLE) {
                //向服务端发送心跳检测
                ctx.writeAndFlush("ping");
                log.info("发送心跳数据");
            } else if (idleStateEvent.state() == IdleState.READER_IDLE) {
                //超过指定时间没有读事件,关闭连接
                log.info("超过心跳时间,关闭和服务端的连接:{}", ctx.channel().remoteAddress());
                ctx.channel().close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            log.info("heartBeat isActive:{} heartBeatRequestPacket:{}", ctx.channel().isActive(), new HeartBeatRequestPacket());
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
