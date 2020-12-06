package com.levi.springboot.socket.constant;

/**
 * 接收帧常量
 * 数据包格式： 帧头（4Byte）| 长度（4Byte）| 数据（xxByte）| 帧尾（4Byte）
 */
public class ReceiveDataFrameConstant {


    //10 09 02 20 00 00 00 00 5A AB 50
    public static int RECEIVE_HEAR = 1; //首字节
    public static int RECEIVE_LENGTH = 2; //第二个字节length


    public static int RECEIVE_FRAME_HEADER_LENGTH = 1;  //帧头字节长度
    public static int RECEIVE_FRAME_TAIL_LENGTH = 4;  //帧尾字节长度
    public static int RECEIVE_FRAME_LENGTH_LENGTH = 4;  //帧长度长度

    public static byte[] RECEIVE_FRAME_HEADER = {(byte)0xEA, (byte)0x4B, (byte)0x70, (byte)0x93};  //固定帧头
    public static byte[] RECEIVE_FRAME_TAIL = {(byte)0xDE, (byte)0x3C, (byte)0x2E, (byte)0xAA}; //固定帧尾

}
