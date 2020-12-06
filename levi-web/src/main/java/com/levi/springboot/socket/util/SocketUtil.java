package com.levi.springboot.socket.util;

import com.levi.springboot.socket.constant.ReceiveDataFrameConstant;
import com.levi.springboot.socket.vo.SocketMsgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketUtil {

    private static Logger log = LoggerFactory.getLogger(SocketUtil.class);

    public static Socket createClientSocket(String host, int port) throws IOException {
        Socket socket = new Socket(host,port);
        return socket;
    }

    public static void closeSocket(Socket socket) {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeMsgData(DataOutputStream dataOutputStream, SocketMsgVo msgDataVo) throws IOException {
        /*msgDataVo.setFrameHeader(SocketMsgVo.RECEIVE_FRAME_HEADER);
        msgDataVo.setLen(0);
        msgDataVo.setBody(null);
        msgDataVo.setFrameTail(SocketMsgVo.RECEIVE_FRAME_TAIL);*/
        dataOutputStream.write(msgDataVo.getFrameHeader());
        dataOutputStream.writeInt(msgDataVo.getLen());
        if (msgDataVo.getBody() != null) {
            dataOutputStream.write(msgDataVo.getBody());
        }
        dataOutputStream.write(msgDataVo.getFrameTail());
        dataOutputStream.flush();
    }

    public static SocketMsgVo readMsgData(DataInputStream dataInputStream) throws IOException {
        byte[] frameHeader = new byte[ReceiveDataFrameConstant.RECEIVE_LENGTH];
        dataInputStream.read(frameHeader,0,1);
        String tempStr = ByteArrayToHexStr(frameHeader);
        log.info("temStr :{}",tempStr);
        SocketMsgVo msgDataVo = new SocketMsgVo();
       // byte[] frameHeader = new byte[ReceiveDataFrameConstant.RECEIVE_HEAR];
/*
        int len = dataInputStream.readInt();
        byte[] body = new byte[len];
        dataInputStream.read(body);
        byte[] frameTail = new byte[ReceiveDataFrameConstant.RECEIVE_FRAME_TAIL_LENGTH];
        dataInputStream.read(frameTail);
        System.out.println("获取的数据长度为：" + len);
        SocketMsgVo msgDataVo = new SocketMsgVo();
        //msgDataVo.setFrameHeader(frameHeader);*/
        msgDataVo.setLen(tempStr.length()+2);
       // msgDataVo.setLen(len);
       // msgDataVo.setFrameTail(frameTail);
        return msgDataVo;
    }

    /**
     * 普通byte[]转16进制Str
     *
     * @param array
     */
    public static String ByteArrayToHexStr(byte[] array)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++)
        {
            String hex = Integer.toHexString(array[i] & 0xFF);
            if (hex.length() == 1)
            {
                stringBuilder.append("0");
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }
}
