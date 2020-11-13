package com.levi.springboot.mq.hanler;


import com.levi.springboot.mq.model.MessageMetadata;
import com.levi.springboot.mq.model.Result;

public interface HandlerFunction {
    Result apply(MessageMetadata metadata);
}
