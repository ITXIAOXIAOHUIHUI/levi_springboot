/*
 * Copyright 2018 flashhold.com All right reserved. This software is the
 * confidential and proprietary information of flashhold.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with flashhold.com.
 */

package com.springboot.levi.leviweb1.enums;

import com.springboot.levi.leviweb1.lock.api.LockType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author wuxiaowu
 */
public enum WcsLock {
    /**
     * agv, 默认1024上限
     */
    AGV(1024, 2),
    /**
     * 工作站,默认64上限
     */
    STATION(128, 4),
    /**
     *
     */
    POINT(20480, 6),
    /**
     * bucket, 默认10240上限
     */
    BUCKET(10240, 8),
    /**
     * Slot, 默认10240上限
     */
    BUCKET_SLOT(10240, 10),
    /**
     * job, 默认1024上限
     */
    JOB(4096, 12),
    /**
     * action,默认上线1024, 排序14
     */
    ACTION(4096, 14),
    /**
     * 状态机, 默认4096
     */
    MACHINE(4096, 16),
    /**
     * 作业单
     */
    WORK(4096, 18),
    /**
     * 请求
     */
    REQUEST(4096, 20),
    /**
     * 容器
     */
    CONTAINER(10240, 24),
    /**
     * 货架可以停放的点位,包括存储点和工作站货架作业点等
     */
    BUCKET_POINT(10240, 28),
    /**
     * 密集存储任务
     */
    HDS_JOB(4096, 30),
    /**
     * 密集存储移动任务
     */
    HDS_JOB_GROUP(4096, 32),
    /**
     * 理货
     */
    TALLY_WORK(128, 34),
    /**
     * 理货移位JOB
     */
    TALLY_JOB(128, 36),
    
    /**
     * 理货空货架计算
     */
    TALLY_CALCULATE_BUCKET(128, 36),

    /**
     * 巷道
     */
    ROAD_WAY(128, 38),
    ;

    private final int threshold;
    private final int priority;

    WcsLock(int threshold, int priority) {
        this.threshold = threshold;
        this.priority = priority;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getPriority() {
        return priority;
    }

    public LockType getLockType() {
        return new LockType(this.name(), this.priority);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
            .append("threshold", threshold)
            .append("sequence", priority)
            .toString();
    }
}
