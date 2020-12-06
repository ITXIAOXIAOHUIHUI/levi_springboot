package com.levi.springboot.socket.client;

import com.levi.springboot.socket.util.SocketUtil;
import com.levi.springboot.socket.util.StreamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端发送，服务端消息接收线程
 */
public class ClientRecvThread implements Runnable {

    private final static Logger log = LoggerFactory.getLogger(ClientRecvThread.class);

    private Socket socket;

    private String ip;

    private int port;
    private volatile boolean isStop = false;
    //锁对象，用于线程通讯，唤醒重试线程
    public static Map<String, Socket> socketMap = new ConcurrentHashMap<>();


    public ClientRecvThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //线程终止条件： 设置标志位为 true or socket 已关闭
        InputStream inputStream = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);
            dataInputStream = new DataInputStream(inputStream);
            //SocketMsgVo msgDataVo = SocketUtil.readMsgData(dataInputStream);

            //log.info("msg data vo :{}",msgDataVo.getLen());
            while (!isStop && !socket.isClosed()) {

                byte[] bytes = new byte[1];
                StringBuffer sb = new StringBuffer();
                System.out.println("********");
                while ((dataInputStream.read(bytes)) != -1) {
                    System.out.println("+++++");
                    String tempStr = ByteArrayToHexStr(bytes);
                    sb.append(tempStr);
                    System.out.println("=========");
                    //返回下次调用可以不受阻塞地从此流读取或跳过的估计字节数,如果等于0则表示已经读完
                    if (dataInputStream.available() == 0) {
                        System.out.println(">>>终端信息读取完毕,最后一位:" + tempStr);
                        break;
                    }
                }
                int a = 1 / 0;
                System.out.println("收到的数据信息：" + sb.toString());
                System.out.println("收到的数据信息：" + sb.toString().length());
                String mesage = "10 06 00 00 00 00 00 00";
                //sendServer(dataOutputStream, socket, mesage);
                // SocketMsgVo msgDataVo = SocketUtil.readMsgData(dataInputStream);
                //log.info("客户端ip:{} port :{}收到消息:{}",socket.getInetAddress().toString(),socket.getPort(),msgDataVo.toString());
                //相对耗时，可以开线程来处理消息，否则影响后续消息接收处理速率
                //方便测试：接收到的数据转换成16进制字符串表示法，好对比服务端发送的原始数据
                //byte[] revByteArr = ByteTransformUtil.byteMerger(msgDataVo.getFrameHeader(), ByteTransformUtil.toHH(msgDataVo.getLen()));
                //revByteArr = ByteTransformUtil.byteMerger(revByteArr, msgDataVo.getBody());
                //revByteArr = ByteTransformUtil.byteMerger(revByteArr, msgDataVo.getFrameTail());
                //log.info("len:{}",msgDataVo.getLen());
                 /*dataInputStream.read();
                byte[] bytes = new byte[3];

                 String hexStr =  ByteTransformUtil.bytesToHex(bytes);
                 System.out.println(hexStr+"===");
                String mesage = "10 06 00 00 00 00 00 00";*/
                //sendServer(dataOutputStream, socket, mesage);
                //log.info("客户端收到服务端原样16进制字符串:{}", hexStr.toUpperCase());
            }
        } catch (Exception e) {
            log.error("客户端接收消息发生异常 socket ip:{}port:{}", e, socket.getInetAddress(), socket.getPort());
            //throw  new RuntimeException("run time exception");
        } finally {
            this.isStop = true;
            log.info("客户端旧接收线程已摧毁");
            StreamUtil.closeInputStream(dataInputStream);
            StreamUtil.closeInputStream(inputStream);
            SocketUtil.closeSocket(socket);

        }
    }


    private synchronized void remove(List<Socket> socketList) {
        Iterator<Socket> iterable = socketList.iterator();

        while (iterable.hasNext()) {
            Socket p = iterable.next();
            if (p.getInetAddress().toString().replace("/", "").equals(ip)
                    && p.getPort() == port) {
                log.info("剔除了 ip:{} port :{}", ip, port);
                iterable.remove();
            }
        }
    }


    private synchronized void removeMap(Map<String, Socket> mapSocket) throws InterruptedException {
        Thread.sleep(1000);
        mapSocket.remove(ip);
    }

    public boolean getStop() {
        return isStop;
    }

    public void sendServer(OutputStream os, Socket socket, String message) throws IOException {
        //截取等号1009025100
        //发送的16进制字符串
        byte[] bytes = hexStringToByteArray(message);
        os.write(bytes);
    }

    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param hexString 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }

    /**
     * 普通byte[]转16进制Str
     *
     * @param array
     */
    public static String ByteArrayToHexStr(byte[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            String hex = Integer.toHexString(array[i] & 0xFF);
            if (hex.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public static void main(String[] args) {
        String str = "100902600000000041C100";
        System.out.println(str.length());
    }
}
