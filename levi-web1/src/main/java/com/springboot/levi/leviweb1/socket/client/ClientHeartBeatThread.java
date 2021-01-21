package com.springboot.levi.leviweb1.socket.client;

import com.springboot.levi.leviweb1.socket.constant.ReceiveDataFrameConstant;
import com.springboot.levi.leviweb1.socket.util.SocketInfo;
import com.springboot.levi.leviweb1.socket.util.SocketUtil;
import com.springboot.levi.leviweb1.socket.util.StreamUtil;
import com.springboot.levi.leviweb1.socket.vo.SocketMsgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端心跳检测、保持长连接状态
 */
public class ClientHeartBeatThread implements Runnable {

    private final static Logger log = LoggerFactory.getLogger(ClientHeartBeatThread.class);

    private Socket socket;
    private Object lockObject = new Object(); //锁对象，用于线程通讯，唤醒重试线程

    //3间隔多长时间发送一次心跳检测
    private int socketHeartIntervalTime;

    private SocketInfo socketInfo;

    private volatile boolean isStop = false;

    public ClientHeartBeatThread(Socket socket, int socketHeartIntervalTime, SocketInfo socketInfo) {
        this.socket = socket;
        this.socketHeartIntervalTime = socketHeartIntervalTime;
        this.socketInfo = socketInfo;
    }

    @Override
    public void run() {
        OutputStream outputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            log.info("心跳检测1111");
            //客户端心跳检测
            while (!this.isStop && !socket.isClosed()) {
                log.info("心跳检测2222");
                SocketMsgVo msgDataVo = new SocketMsgVo();
                msgDataVo.setFrameHeader(ReceiveDataFrameConstant.RECEIVE_FRAME_HEADER);
                msgDataVo.setLen(0);
                msgDataVo.setBody(null);
                msgDataVo.setFrameTail(ReceiveDataFrameConstant.RECEIVE_FRAME_TAIL);
                if (msgDataVo != null) {
                    SocketUtil.writeMsgData(dataOutputStream, msgDataVo);
                }
                try {
                    Thread.sleep(socketHeartIntervalTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            log.error("客户端心跳消息发送异常");
        } finally {
            this.isStop = true;
            log.info("客户端旧心跳线程已摧毁 ip:{} port :{}", socket.getInetAddress(), socket.getPort());
            StreamUtil.closeOutputStream(dataOutputStream);
            StreamUtil.closeOutputStream(outputStream);
            SocketUtil.closeSocket(socket);
            //最后唤醒线程、重建连接
            synchronized (socketInfo) {
                socketInfo.notify();
            }
        }
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}
