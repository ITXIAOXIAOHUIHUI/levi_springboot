package com.springboot.levi.netty.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.springboot.levi.netty.serializer.Serializer;
import com.springboot.levi.netty.serializer.SerializerAlgorithm;

/**
 * @author jianghaihui
 * @date 2021/1/27 11:14
 */
public class JSONSerializer implements Serializer {


    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
