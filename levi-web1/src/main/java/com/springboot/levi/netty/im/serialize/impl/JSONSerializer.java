package com.springboot.levi.netty.im.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.springboot.levi.netty.im.serialize.Serializer;
import com.springboot.levi.netty.im.serialize.SerializerAlogrithm;

/**
 * @author jianghaihui
 * @date 2021/1/27 14:59
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
