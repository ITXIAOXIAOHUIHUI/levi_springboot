package com.springboot.levi.leviweb1.mq.hanler;


import com.springboot.levi.leviweb1.mq.model.MessageMetadata;
import com.springboot.levi.leviweb1.mq.model.Result;

public interface HandlerFunction {
    Result apply(MessageMetadata metadata);
}
