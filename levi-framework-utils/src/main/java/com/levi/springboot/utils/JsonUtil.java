/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 * 版权所有 翻版必究 --kim.cheng
 *****************************************************************************/
package com.levi.springboot.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Json转换工具类
 *
 * @author kim.cheng
 * @since 2018-12-12
 */
public class JsonUtil {

    public static String toJsonString(Object obj) {
        return toJsonString(obj, true);
    }

    public static String toJsonString(Object obj, boolean onlyNotNull) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (onlyNotNull) {
                objectMapper.setSerializationInclusion(Include.NON_NULL);
                objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            }
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String toPrettyJsonString(Object obj) {
        return toPrettyJsonString(obj, true);
    }

    public static String toPrettyJsonString(Object obj, boolean onlyNotNull) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (onlyNotNull) {
                objectMapper.setSerializationInclusion(Include.NON_NULL);
                objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            }
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T parse(String text, Class<T> clazz, Class<?>... parametricType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType targetType = null;
        if (parametricType == null || parametricType.length == 0) {
            targetType = objectMapper.getTypeFactory().constructType(clazz);
        } else {
            targetType = objectMapper.getTypeFactory().constructParametricType(clazz, parametricType);
        }
        T result = null;
        try {
            result = objectMapper.readValue(text, targetType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    public static <T> T parse(InputStream input, Class<T> clazz, Class<?>... parametricType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType targetType = null;
        if (parametricType == null || parametricType.length == 0) {
            targetType = objectMapper.getTypeFactory().constructType(clazz);
        } else {
            targetType = objectMapper.getTypeFactory().constructParametricType(clazz, parametricType);
        }
        T result = null;
        try {
            result = objectMapper.readValue(input, targetType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    public static <E> List<E> toList(String json, Class<E> eClass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * JSON字符串转map,必定会返回
     *
     * @param text
     * @return
     */
    public static Map<?, ?> parseMap(String text) {
        Map<?, ?> map;
        try {
            map = parse(text, Map.class);
            map = map == null ? new HashMap<>() : map;
        } catch (Exception e) {
            e.printStackTrace();
            map = new HashMap<>();
        }
        return map;
    }
}
