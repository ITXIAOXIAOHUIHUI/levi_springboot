package com.levi.springboot.websocket.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jianghaihui
 * @date 2020/11/17 21:06
 */
@Slf4j
@Component
public class MultiSocket {

    private Map<String,SocketClient> socketMap = new ConcurrentHashMap<>();

    //@Autowired
    //private SocketService socketService;

    /**
     * 连接所有的打印器
   /*  *//*
    public void connectAllServer(){
        //todo
        List<Config> configList = new ArrayList<>();
        if(configList!=null&&configList.size()>0){
            configList.forEach(c-> addSocketMap(c.getCode(),c.getIp(),c.getPort()));
        }else {
            log.error("【Socket连接】没有需要连接的打印服务器!");
        }
    }*/

    /**
     * 新增加一个连接
     * @param code
     * @param ip
     * @param port
     */
    /*private synchronized void addSocketMap(String code,String ip,Integer port){
        log.info("【Socket正在连接】code:{},ip:{},port:{}正在连接！",code,ip,port);
        SocketClient socketClient = new SocketClient(code,ip,port);
        if(socketClient!=null){
            socketMap.put(code, socketClient);
        }else {
            log.error("【Socket连接失败】code:{},ip:{},port:{}socketClient为空！！！", code, ip, port);
        }
    }*/
}
