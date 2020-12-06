package com.levi.springboot.websocket.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jianghaihui
 * @date 2020/11/18 10:32
 */

@Slf4j
@Data
@Component
@NoArgsConstructor
public class SocketServer  {

    private Integer port = 9090;
    private boolean started;
    private ServerSocket serverSocket;
    // 防止重复创建socket线程链接对象浪费资源
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void start(){
        start(null);
    }

    public void start(Integer port){
        log.info("port: {}, {}", this.port, port);
        try {
            serverSocket =  new ServerSocket(port == null ? this.port : port);
            started = true;
            log.info("Socket服务已启动，占用端口： {}", serverSocket.getLocalPort());
        } catch (IOException e) {
            log.error("端口冲突,异常信息：{}", e);
            System.exit(0);
        }


        while  (true){
            try {
                // 开启socket监听
                Socket socket = serverSocket.accept();
                ClientSocket register = register(socket);
                // 在此判断是否重复创建socket对象线程
                if (register != null){
                    executorService.submit(register);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
