package com.springboot.levi.leviweb1.retry.eums;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

public enum RetryTaskType {

    MQ_CONSUME("consumer-task");

    public final String value; //compatible with old data. ex: 'consumer-task'

    RetryTaskType(String value) {
        this.value = value;
    }

    private static final Map<String, RetryTaskType> valueEnumMap = Maps.newHashMap();

    static {
        Arrays.stream(values()).forEach(e -> valueEnumMap.put(e.value, e));
    }

    public static RetryTaskType from(String value) {
        return valueEnumMap.get(value);
    }
}
