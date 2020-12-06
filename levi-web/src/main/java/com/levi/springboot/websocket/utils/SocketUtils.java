package com.levi.springboot.websocket.utils;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author jianghaihui
 * @date 2020/11/18 10:31
 */
public class SocketUtils {

    /**
     * 连接客户端
     */
    public static SocketClient connect(String code,String ip, int port){
        try{
            URI uri = new URI("ws://"+ip+":"+port);
            SocketClient socketClient = new SocketClient(uri);
            socketClient.setParams(code,ip,port);
            socketClient.connect();
            return socketClient;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}
