package com.levi.springboot.mq;

import com.levi.springboot.mq.consumer.ConsumerTag;
import com.levi.springboot.mq.consumer.ConsumerTopic;
import com.levi.springboot.mq.hanler.HandlerFunction;
import com.levi.springboot.mq.utils.LoggingUUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jianghaihui
 * @date 2020/11/5 16:22
 */
public final class FunctionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionManager.class);

    private static volatile FunctionManager manager = new FunctionManager();

    public static FunctionManager getInstance() {
        return manager;
    }

    private final Map<String, HandlerFunction> functions;

    private FunctionManager() {
        this.functions = new LinkedHashMap<>();
    }

    public void register(
            @NotNull ConsumerTopic topic,
            @NotNull ConsumerTag tag,
            HandlerFunction function) {
        synchronized (functions) {
            functions.put(key(topic, tag), function);
        }
    }

    private String key(ConsumerTopic topic, ConsumerTag tag) {
        return LoggingUUIDUtil.messageKey(topic, tag);
    }

}
