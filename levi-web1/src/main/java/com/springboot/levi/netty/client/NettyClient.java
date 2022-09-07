package com.springboot.levi.netty.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springboot.levi.netty.codec.PacketDecoder;
import com.springboot.levi.netty.codec.Spliter;
import com.springboot.levi.netty.dto.ConnectDto;
import com.springboot.levi.netty.handler.HeartBeatTimerHandler;
import com.springboot.levi.netty.handler.IMIdleStateHandler;
import com.springboot.levi.netty.handler.MessageResponseHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import scala.Int;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jianghaihui
 * @date 2021/1/26 11:12
 */
public class NettyClient {

    private static final int MAX_RETRY = 10;

    private static final String ip ="172.31.254.157";
    static List<Integer> list = Lists.newArrayList();

    static {
        list.add(6001);
        list.add(6002);
        list.add(6003);
        list.add(6000);
    }


    public static void main(String[] args) throws InterruptedException {
        //客户端代码需要要一个事件循环组
        //创建客户端启动对象
        list.forEach(t->{
            System.out.println(t+"port port");
            connect(ip,t);
        });
    }
    public static boolean connect(String ip,int port){
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = createBootstrap(group);
        // 4.建立连接(查出多个ip进行链接)
        Channel channel = bootstrap.connect(ip, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            } else {
                connect1(bootstrap, ip, port);
                //如果连接失败的话，就重新连接了
                System.out.println("连接失败");
            }
        }).channel();
       return true;
    }

    private static Bootstrap createBootstrap(NioEventLoopGroup group) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1.指定线程模型
                .group(group)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                //表示连接的超时时间，超过这个时间还是建立不上的话则代表连接失败
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                // 表示是否开启 TCP 底层心跳机制，true 为开启
                .option(ChannelOption.SO_KEEPALIVE, true)
                //表示是否开始 Nagle 算法，true 表示关闭，false 表示开启，通俗地说，如果要求高实时性，
                // 有数据发送时就马上发送，就设置为 true 关闭，如果需要减少发送次数减少网络交互，就设置为 false 开启
                .option(ChannelOption.TCP_NODELAY, true)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new IMIdleStateHandler());

                        //ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        // 登录响应处理器
                        // ch.pipeline().addLast(new LoginResponseHandler());
                        // 收消息处理器
                        ch.pipeline().addLast(new MessageResponseHandler());
                        // 心跳定时器
                        ch.pipeline().addLast(new HeartBeatTimerHandler());
                    }
                });
        return bootstrap;
    }


   /* private static void connect(Bootstrap bootstrap, String host, int port) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else {
                System.err.println("连接失败，开始重连");
                connect(bootstrap, host, port);
            }
        }
        );
    }*/

    public static void connect1(Bootstrap bootstrap, String host, int port) throws Exception {
        System.out.println("netty client start。。");
        //启动客户端去连接服务器端
        ChannelFuture cf = bootstrap.connect(host, port);
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    //重连交给后端线程执行
                    future.channel().eventLoop().schedule(() -> {
                        System.err.println("重连服务端...");
                        try {
                            connect1(bootstrap,host,port);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, 3000, TimeUnit.MILLISECONDS);
                } else {
                    System.out.println("服务端连接成功...");
                }
            }
        });
        //对通道关闭进行监听
        cf.channel().closeFuture().sync();
    }


    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }
}
