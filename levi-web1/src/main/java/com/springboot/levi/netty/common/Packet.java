package com.springboot.levi.netty.common;

import lombok.Data;

/**
 * @author jianghaihui
 * @date 2021/1/26 17:32
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令的抽象方法，所有的指令数据包都必须实现这个方法，这样我们就可以知道某种指令的含义
     * @return
     */
    public abstract Byte getCommand();

}
