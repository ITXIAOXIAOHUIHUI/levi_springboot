package com.springboot.levi.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author jianghaihui
 * @date 2021/1/26 11:04
 */
public class NettyServer {

    /**
     * 这么一小段代码就实现了我们前面 NIO 编程中的所有的功能，包括服务端启动，
     * 接受新连接，打印客户端传来的数据，怎么样，是不是比 JDK 原生的 NIO 编程优雅许多？
     *
     * 1、boss 对应 IOServer.java 中的接受新连接线程，主要负责创建新连接
     * 2、worker 对应 IOServer.java 中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理
     * @param args
     */
    public static void main(String[] args) {
        //引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        /**
         * bossGroup表示监听端口，accept 新连接的线程组，
         */
        NioEventLoopGroup boos = new NioEventLoopGroup();
        //workerGroup表示处理每一条连接的数据读写的线程组
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap.childAttr(AttributeKey.newInstance("clientKey"), "clientValue");
        serverBootstrap.group(boos,worker)
                //NioServerSocketChannel指定一些自定义属性
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                //表示是否开启TCP底层心跳机制，true为开启
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //表示是否开启Nagle算法，true表示关闭，false表示开启，通俗地说，
                // 如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
                .childOption(ChannelOption.TCP_NODELAY, true)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println("msg:"+ msg);
                            }
                        });
                    }
                }).bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("绑定成功");
                }else{
                   bind(serverBootstrap,8001);
                }
            }
        });
    }








    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }


















}
