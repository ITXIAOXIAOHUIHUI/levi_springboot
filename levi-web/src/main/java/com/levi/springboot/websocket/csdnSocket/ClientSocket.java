package com.levi.springboot.websocket.csdnSocket;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * @author jianghaihui
 * @date 2020/11/18 11:47
 */

@Slf4j
@Data
public class ClientSocket implements Runnable {



    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String key;
    private String message;

    @Override
    public void run() {
        while (true){
            try {
                onMessage(this);
                log.info(LocalDateTime.now()+"当前设备:"+this.key+" 接收到数据: <<<<<<" + this.message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isSocketClosed(this)){
                log.info("客户端已关闭,其Key值为：{}", this.getKey());
                //关闭对应的服务端资源
                close(this);
                break;
            }
        }
    }

}
