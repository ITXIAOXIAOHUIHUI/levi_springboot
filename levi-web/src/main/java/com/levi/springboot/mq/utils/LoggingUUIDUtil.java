package com.levi.springboot.mq.utils;

import com.google.common.collect.Lists;
import com.levi.springboot.mq.consumer.ConsumerTag;
import com.levi.springboot.mq.consumer.ConsumerTopic;
import com.levi.springboot.mq.model.MessageMetadata;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class LoggingUUIDUtil {
    /**
     * produce a uuid for request
     *
     * @param request
     * @return
     */
    public static String generateUUID(ServletRequest request) {
        List<String> keys = Lists.newArrayList();
        keys.add(request.getServerName());
        keys.add(request.getRemoteHost());
        keys.add(String.valueOf(request.getRemotePort()));
        keys.add(request.getProtocol());
        keys.add(request.getAttribute(Attributes.REQUEST_STARTED_AT).toString());
        // salting
        keys.add(UUID.randomUUID().toString());
        return SecurityUtil.md5sum(StringUtils.join(keys, "_"));
    }

    public static String metadataUUID(MessageMetadata metadata) {
        List<String> keys = Lists.newArrayList(
                metadata.getMessageId(),
                metadata.getTopic().toString(),
                metadata.getTag().toString(),
                String.valueOf(metadata.getPublishedAt()),
                String.valueOf(metadata.getSubscribedAt()));
        // salting
        keys.add(UUID.randomUUID().toString());
        return SecurityUtil.md5sum(StringUtils.join(keys, "_"));
    }

    public static String messageKey(
            @NotNull ConsumerTopic topic,
            @NotNull ConsumerTag tag) {
        return topic.toString().toUpperCase() + "_" + tag.toString().toUpperCase();
    }
}
