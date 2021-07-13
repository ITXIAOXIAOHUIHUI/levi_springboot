/*
 * Copyright 2018 flashhold.com All right reserved. This software is the
 * confidential and proprietary information of flashhold.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with flashhold.com.
 */

package com.springboot.levi.leviweb1.lock;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

/**

 * 锁类型,例如agv/station等
 */
public class LockType {
    /** 锁类型描述 */
    private final String type;
    /** 锁优先级,multiLock申请时排序用 */
    private final int priority;

    public LockType(String type, int priority) {
        this.type = type;
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        LockType lockType = (LockType)o;
        return priority == lockType.priority &&
            Objects.equals(type, lockType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, priority);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
            .append("type", type)
            .append("sequence", priority)
            .toString();
    }
}
