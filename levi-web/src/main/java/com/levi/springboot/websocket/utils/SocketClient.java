package com.levi.springboot.websocket.utils;

import com.levi.springboot.websocket.service.CorePrinterService;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * @author jianghaihui
 * @date 2020/11/17 20:56
 */
@Slf4j
public class SocketClient extends WebSocketClient {

    /**
     *
     */
    private String led;

    private String ip;

    private Integer port;

    /**
     * 操作数据库的bean
     */
    private CorePrinterService corePrinterService = null;


    public SocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        log.info("【success】Socket连接成功！Message:{}", handshake.getHttpStatusMessage());

    }

    @Override
    public void onMessage(String message) {

        log.info("【message】Socket接收到消息：{}", message);
        if(message.contains("printers")){
            log.info("收到打印机列表信息：{}",message);
            try{
                //todo 操作DB
                //PrinterList printerList = JSONObject.parseObject(message, PrinterList.class);
                //log.info("打印列表解析后的结果：{}",JsonUtils.toJsonMsg(printerList));
               /* if(printerList!=null&&printerList.getPrinters()!=null&&printerList.getPrinters().size()>0) {
                    getCorePrinterService();
                    log.info("清空旧的打印列表！");
                    corePrinterService.deleteByCode(code);
                    log.info("入库新的打印列表！");
                    List<Printer> printers = printerList.getPrinters();
                    printers.forEach(p->p.setCode(code));
                    corePrinterService.saveAll(printers);
                }*/
            }catch (Exception e){
                log.info("打印列表消息json解析失败！");
            }
        }

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Socket连接关闭：code:{},reason:{},remote:{}", code, reason, remote);
    }

    @Override
    public void onError(Exception e) {
        log.error("Socket连接出现问题：{}", e.getMessage());
    }

    /**
     * 设置配置参数
     */
    public void setParams(String led,String ip, int port){
        this.led = led;
        this.ip = ip;
        this.port = port;
    }

    public String getLed() {
        return led;
    }

    public void setLed(String led) {
        this.led = led;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
