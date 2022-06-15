/*
 * Copyright 2018 flashhold.com All right reserved. This software is the
 * confidential and proprietary information of flashhold.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with flashhold.com.
 */

package com.springboot.levi.leviweb1.lock.multi;

import com.springboot.levi.leviweb1.lock.api.ILock;
import com.springboot.levi.leviweb1.lock.api.IMultiLock;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wuxiaowu
 */
public class MultiLock implements IMultiLock {
    private static final Logger LOG = LoggerFactory.getLogger(MultiLock.class);

    /**
     * 持有多锁的线程id
     */
    private volatile long holder;
    /**
     * 锁状态
     */
    private AtomicInteger state;

    /**
     * 未申请到的读锁(需要申请的读锁)
     */
    private List<ILock> unHoldRLocks;
    /**
     * 已申请到的读锁
     */
    private List<ILock> holdRLocks;
    /**
     * 未申请到的写锁(需要申请的写锁)
     */
    private List<ILock> unHoldWLocks;
    /**
     * 已申请到的写锁
     */
    private List<ILock> holdWLocks;

    public MultiLock() {
        this.holder = -1;
        this.state = new AtomicInteger(MultiLockState.FREE.getValue());
        this.unHoldRLocks = new ArrayList<>();
        this.holdRLocks = new ArrayList<>();
        this.unHoldWLocks = new ArrayList<>();
        this.holdWLocks = new ArrayList<>();
    }

    /**
     * 增加需要申请的读锁
     *
     * @param lock lock
     * @return result
     */
    @Override
    public boolean preRLock(ILock lock) {
        // 只有free状态才能执行
        setState(MultiLockState.FREE, MultiLockState.FREE);

        this.unHoldRLocks.add(lock);
        Collections.sort(this.unHoldRLocks);
        return true;
    }

    /**
     * 增加需要申请的写锁
     *
     * @param lock lock
     * @return result
     */
    @Override
    public boolean preWLock(ILock lock) {
        // 只有free状态才能执行
        setState(MultiLockState.FREE, MultiLockState.FREE);

        this.unHoldWLocks.add(lock);
        Collections.sort(this.unHoldWLocks);
        return true;
    }



    @Override
    public boolean preRLockS(List<ILock> locks) {
        // 只有free状态才能执行
        setState(MultiLockState.FREE, MultiLockState.FREE);

        this.unHoldRLocks.addAll(locks);
        Collections.sort(this.unHoldRLocks);
        return true;
    }

    @Override
    public boolean preWLockS(List<ILock> locks) {
        // 只有free状态才能执行
        setState(MultiLockState.FREE, MultiLockState.FREE);

        this.unHoldWLocks.addAll(locks);
        Collections.sort(this.unHoldWLocks);
        return true;
    }

    /**
     * 阻塞式申请多锁 尽量少用,以免出现死锁
     *
     * @return result
     */
    @Override
    public boolean lock() {
        setState(MultiLockState.FREE, MultiLockState.LOCKING);

        ILock readLock;
        Iterator<ILock> readIterator = this.unHoldRLocks.iterator();
        while (readIterator.hasNext()) {
            readLock = readIterator.next();
            readLock.rLock();
            this.holdRLocks.add(readLock);
            readIterator.remove();
        }

        ILock writeLock;
        Iterator<ILock> writeIterator = this.unHoldWLocks.iterator();
        while (writeIterator.hasNext()) {
            writeLock = writeIterator.next();
            writeLock.wLock();
            this.holdWLocks.add(writeLock);
            writeIterator.remove();
        }

        // 更新锁状态
        setState(MultiLockState.LOCKING, MultiLockState.LOCKED);
        recordHolder();
        LOG.debug("lock done.");
        return true;
    }

    /**
     * 最多阻塞ts*锁数量 时间申请多锁
     *
     * @param ts   ts
     * @param unit unit
     * @return result
     */
    @Override
    public boolean tryLock(long ts, TimeUnit unit) {
        setState(MultiLockState.FREE, MultiLockState.LOCKING);

        boolean success = true;
        try {
            ILock readLock;
            Iterator<ILock> readIterator = this.unHoldRLocks.iterator();
            while (readIterator.hasNext()) {
                readLock = readIterator.next();
                if (!readLock.tryRLock(ts, unit)) {
                    success = false;
                    throw new Exception("tryRLock failed.");
                }
                this.holdRLocks.add(readLock);
                readIterator.remove();
            }

            ILock writeLock;
            Iterator<ILock> writeIterator = this.unHoldWLocks.iterator();
            while (writeIterator.hasNext()) {
                writeLock = writeIterator.next();
                if (!writeLock.tryWLock(ts, unit)) {
                    success = false;
                    throw new Exception("tryWLock failed.");
                }
                this.holdWLocks.add(writeLock);
                writeIterator.remove();
            }
        } catch (Exception e) {
            LOG.warn("tryLock failed in {} ms.", ts);
        } finally {
            if (success) {
                // 成功则更新M锁状态
                setState(MultiLockState.LOCKING, MultiLockState.LOCKED);
                recordHolder();
            } else {
                // 失败则释放已持有的锁,并恢复M锁状态
                rollbackLock();
                setState(MultiLockState.LOCKING, MultiLockState.FREE);
            }
        }

        LOG.debug("lock done.");
        return success;
    }

    @Override
    public void unLock() {
        checkHolder();

        setState(MultiLockState.LOCKED, MultiLockState.UNLOCKING);
        rollbackLock();
        setState(MultiLockState.UNLOCKING, MultiLockState.FREE);

        clearHolder();
        LOG.debug("unLock done.");
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
            .append("holder", holder)
            .append("state", MultiLockState.getState(state.intValue()))
            .append("unHoldRLocks", unHoldRLocks)
            .append("holdRLocks", holdRLocks)
            .append("unHoldWLocks", unHoldWLocks)
            .append("holdWLocks", holdWLocks)
            .toString();
    }

    /**
     * 回退所有的锁占有
     */
    private void rollbackLock() {
        ILock readLock;
        Iterator<ILock> readIterator = this.holdRLocks.iterator();
        while (readIterator.hasNext()) {
            readLock = readIterator.next();
            readLock.rUnLock();
            this.unHoldRLocks.add(readLock);
            readIterator.remove();
        }

        ILock writeLock;
        Iterator<ILock> writeIterator = this.holdWLocks.iterator();
        while (writeIterator.hasNext()) {
            writeLock = writeIterator.next();
            writeLock.wUnLock();
            this.unHoldWLocks.add(writeLock);
            writeIterator.remove();
        }
    }

    /**
     * 记录多锁持有者
     */
    private void recordHolder() {
        this.holder = Thread.currentThread().getId();
    }

    /**
     * 清除holder
     */
    private void clearHolder() {
        if (this.holder == Thread.currentThread().getId()) {
            this.holder = -1;
        }
    }

    /**
     * 校验是否为多锁持有线程
     */
    private void checkHolder() {
        if (Thread.currentThread().getId() != this.holder) {
            throw new RuntimeException("Only holder can unlock.");
        }
    }

    /**
     * 设置多锁状态
     *
     * @param expect source status
     * @param update dest status
     */
    private void setState(MultiLockState expect, MultiLockState update) {
        if (!this.state.compareAndSet(expect.getValue(), update.getValue())) {
            LOG.debug("Not support current operation, state:" + MultiLockState.getState(this.state.intValue()));
            throw new RuntimeException(
                "Not support current operation, state:" + MultiLockState.getState(this.state.intValue()));
        }
    }

    enum MultiLockState {
        /**
         * 锁空闲
         */
        FREE(0),
        /**
         * 申请中
         */
        LOCKING(1),
        /**
         * 已被持有
         */
        LOCKED(2),
        /**
         * 释放中
         */
        UNLOCKING(3);

        private static Map<Integer, MultiLockState> states;

        static {
            states = new HashMap<>();
            for (MultiLockState state : values()) {
                states.put(state.getValue(), state);
            }
        }

        private final Integer value;

        MultiLockState(Integer value) {
            this.value = value;
        }

        public static MultiLockState getState(Integer value) {
            return states.get(value);
        }

        public int getValue() {
            return value;
        }
    }
}
