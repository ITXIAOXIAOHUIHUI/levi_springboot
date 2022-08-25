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
        //boosGroup只是处理连接请求，真正的和客户端业务处理，会交给workerGroup完成
        NioEventLoopGroup boos = new NioEventLoopGroup();
        //workerGroup表示处理每一条连接的数据读写的线程组
        NioEventLoopGroup worker = new NioEventLoopGroup();
        //传教服务端的启动对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.childAttr(AttributeKey.newInstance("clientKey"), "clientValue");
        //使用链式编程来配置参数
        serverBootstrap.group(boos,worker) //设置两个线程组
                //NioServerSocketChannel指定一些自定义属性
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                //表示是否开启TCP底层心跳机制，true为开启
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //表示是否开启Nagle算法，true表示关闭，false表示开启，通俗地说，
                // 如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
                .childOption(ChannelOption.TCP_NODELAY, true)
                .channel(NioServerSocketChannel.class) //使用NioServerSocketChannel作为服务器的通道实现
                //// 初始化服务器连接队列大小，服务端处理客户端连接请求是顺序处理的,所以同一时间只能处理一个客户端连接。16 // 多个客户端同时来的时候,服务端将不能处理的客户端连接请求放在队列中等待处理
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<NioSocketChannel>() { ////创建通道初始化对象，设置初始化参数
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ////对workerGroup的SocketChannel设置处理器
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println("msg:"+ msg);
                            }
                        });
                    }
                    ///绑定一个端口并且同步, 生成了一个ChannelFuture异步对象，通过isDone()等方法可以判断异步事件的执行情况28 //启动服务器(并绑定端口)，bind是异步操作，sync方法是等待异步操作执行完毕
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
