package com.springboot.levi.leviweb1.mq.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.levi.leviweb1.mq.consumer.ConsumerTag;
import com.springboot.levi.leviweb1.mq.consumer.ConsumerTopic;
import com.springboot.levi.leviweb1.mq.utils.ObjectUpdateUtil;
import org.apache.commons.lang3.mutable.MutableBoolean;

@JsonInclude
public class MessageMetadata {
    // immutable fields
    private String id;
    private String messageId;
    private ConsumerTopic topic;
    private ConsumerTag tag;
    private String target;
    private String data;
    private long publishedAt;

    // mutable fields
    private long subscribedAt;
    private long lastProcessedAt;
    private boolean finished;
    private long finishedAt;
    private String handleResult;

    public boolean updateFrom(MessageMetadata other) {
        MutableBoolean updated = new MutableBoolean();
        subscribedAt = ObjectUpdateUtil.updateField(
                subscribedAt, other.getSubscribedAt(), updated);
        lastProcessedAt = ObjectUpdateUtil.updateField(
                lastProcessedAt, other.getLastProcessedAt(), updated);
        finished = ObjectUpdateUtil.updateField(finished, other.isFinished(), updated);
        finishedAt = ObjectUpdateUtil.updateField(finishedAt, other.getFinishedAt(), updated);
        handleResult = ObjectUpdateUtil.updateField(
                handleResult, other.getHandleResult(), updated);
        return updated.booleanValue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public ConsumerTopic getTopic() {
        return topic;
    }

    public void setTopic(ConsumerTopic topic) {
        this.topic = topic;
    }

    public ConsumerTag getTag() {
        return tag;
    }

    public void setTag(ConsumerTag tag) {
        this.tag = tag;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(long publishedAt) {
        this.publishedAt = publishedAt;
    }

    public long getFinishedAt() {
        return finishedAt;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public long getLastProcessedAt() {
        return lastProcessedAt;
    }

    public void setFinishedAt(long finishedAt) {
        this.finishedAt = finishedAt;
    }

    public long getSubscribedAt() {
        return subscribedAt;
    }

    public void setSubscribedAt(long subscribedAt) {
        this.subscribedAt = subscribedAt;
    }

    public void setLastProcessedAt(long lastProcessedAt) {
        this.lastProcessedAt = lastProcessedAt;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }
}
