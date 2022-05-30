package com.springboot.levi.leviweb1.socket.client;

import com.springboot.levi.leviweb1.socket.util.SocketInfo;
import com.springboot.levi.leviweb1.socket.util.SocketUtil;
import com.springboot.levi.leviweb1.socket.vo.JxSocketConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Desc socket客户端服务
 * @Author ws
 * @Time 2020/2/21
 */
@Component
public class ClientSocketService implements InitializingBean {

    private final static Logger log = LoggerFactory.getLogger(ClientSocketService.class);

    //private final SocketInfo lockObject = new SocketInfo(); //锁对象，用于线程通讯，唤醒重试线程

    private final static int THREAD_SLEEP_MILLS = 30000;

    public static List<Socket> socketList = new ArrayList<>();

    private List<JxSocketConfig> list = Stream.of(new JxSocketConfig("192.168.89.1", 8000)
    ).collect(Collectors.toList());

    // @Value("${qxts.socket.host}")
    private String host;

    // @Value("${qxts.socket.port}")
    private int port;

    //30s 间隔多少秒发送一次心跳检测
    //   @Value("${qxts.socket.heart.interval.time}")
    private int socketHeartIntervalTime;

    public static Map<String, Socket> socketMap = new ConcurrentHashMap<>();


    //在该类的依赖注入完毕之后，会自动调用afterPropertiesSet方法,否则外部tomcat部署会无法正常启动socket

    //jar包的启动时直接由项目的主函数开始启动，此时会先初始化IOC容器，然后才会进行内置Servlet环境（一般为Tomcat）的启动。
    //war包通常使用Tomcat进行部署启动，在tomcat启动war应用时，会先进行Servlet环境的初始化，之后才会进行到IOC容器的初始化，也就是说，在servlet初始化过程中是不能使用IOC依赖注入的
    @Override
    public void afterPropertiesSet() throws Exception {
        for (JxSocketConfig config : list) {
            String ip = config.getLedIp();
            int port = config.getPort();
            start(config.getLedIp(), config.getPort());
            //ClientRecvThread recvThread = new ClientRecvThread(config.getLedIp(),config.getPort());
            /*ClientThreadPooll recvThread = new ClientThreadPooll(ip,port);
            new Thread(recvThread).start();
            ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("client-thread"+ip).build();
            ThreadPoolExecutor jobCompleteReportExecutor = new ThreadPoolExecutor(2, 2,0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue(1),namedThreadFactory);
            jobCompleteReportExecutor.submit(recvThread);*/
        }
    }

    /**
     * 启动服务
     */
    public void start(String ip, int port) {
        Thread socketServiceThread = new Thread(() -> {
            SocketInfo socketInfo;
            Socket socket;
            while (true) {
                try {
                    socketInfo = new SocketInfo(ip, port);
                    socket = SocketUtil.createClientSocket(socketInfo.getIp(), socketInfo.getPort());
                    log.info("客户端 socket 在[{}]连接正常", port);
                    socketMap.put(ip + "_" + port, socket);
                    ClientRecvThread recvThread = new ClientRecvThread(socket, socketInfo);
                    new Thread(recvThread).start();
                    // ClientHeartBeatThread heartBeatThread = new ClientHeartBeatThread(socket, socketHeartIntervalTime, socketInfo);
                    // new Thread(heartBeatThread).start();
                    //1、连接成功后阻塞，由心跳检测异常唤醒
                    //方式1
                    synchronized (socketInfo) {
                        try {
                            socketInfo.wait();
                            int realPort = socketInfo.getPort();
                            String realIp = socketInfo.getIp();
                            String convertIp = realIp + "_" + realPort;
                            socketInfo.setIp(realIp);
                            socketInfo.setPort(socket.getPort());
                            log.info("ip [{}]  port [{}]**************重连重连*****************", ip, port);
                            removeMap(socketMap, convertIp);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    //方式2
                    /*while (!heartBeatThread.isStop()) {
                        //进行空循环, 掉线休眠,防止损耗过大， 随即重连
                        try {
                            Thread.sleep(ClientSocketService.THREAD_SLEEP_MILLS);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }*/
                    //旧的、接收线程、心跳线程摧毁，准备重建连接、接收线程、心跳线程
                } catch (Exception e) {
                    log.error("socket客户端进行连接发生异常 service exception");
                    //2、第一次启动时连接异常发生，休眠, 重建连接
                    try {
                        Thread.sleep(ClientSocketService.THREAD_SLEEP_MILLS);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        socketServiceThread.start();
    }

    private synchronized void removeMap(Map<String, Socket> mapSocket, String ip) throws InterruptedException {
        Thread.sleep(1000);
        mapSocket.remove(ip);
    }

    public static void main(String[] args) {
        String str = "100910121021212";
        System.out.println(str.startsWith("16"));
    }

}
