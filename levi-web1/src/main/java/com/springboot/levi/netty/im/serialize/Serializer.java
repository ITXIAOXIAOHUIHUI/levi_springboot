package com.springboot.levi.netty.im.serialize;

import com.springboot.levi.netty.im.serialize.impl.JSONSerializer;

/**
 * @author jianghaihui
 * @date 2021/1/27 14:55
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();
    /**
     * 序列化算法
     */
    byte getSerializerAlogrithm();

    /**
     * java对象转化为二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转化为java对象
     */
    <T> T deserialize(Class<T> clazz,byte[] bytes);
}
