package com.springboot.levi.netty.common;

/**
 * @author jianghaihui
 * 接下来我们就需要定义一种规则，如何把一个 Java 对象转换成二进制数据，
 * 这个规则叫做 Java 对象的序列化。
 * @date 2021/1/27 11:12
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
