package com.springboot.levi.dobbo.netty.server;

import com.google.common.collect.Lists;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.List;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-07-26 10:44
 */
public class NettyServer {

    public static void main(String[] args) {


        List<String> result = Lists.newArrayList("1","2","3");
        List<String> strings = result.subList(0, 1);
        System.out.println(strings);
        /*//创建两个现场组bosssGroup和workerGroup,含有的子线程的NioEventLoop的个数默认为cpu核数的两倍
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            //创建服务器端的启动对象
            ServerBootstrap bootstrap = new ServerBootstrap();
            //使用链式变成来配置参数
            bootstrap.group(bossGroup,workerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class)//使用NioServerSocketChannel作为服务器的通道实现

        .option(ChannelOption.SO_BACKLOG,1024).childHandler(new C)

        }*/
       // returnJob(1,0);

        System.out.println("111111111");
    }


    public static boolean returnJob(int a ,int b) {
        boolean flag  = false;
        try{
            flag = test(a,b);
           // return true;
        }finally {

        }
        return  flag;
    }

    public  static  boolean test(int a ,int b){
        try{
            int i = a/ b;
        }finally {

        }
        return true;
    }
}
