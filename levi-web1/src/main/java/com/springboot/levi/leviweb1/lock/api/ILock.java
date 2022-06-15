package com.springboot.levi.leviweb1.lock.api;

import java.util.concurrent.TimeUnit;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-15 16:36
 */
public interface ILock  extends Comparable<ILock>{
    /**
     * 申请默认阻塞时间
     */
    /**
     * 申请锁默认阻塞时间
     */
    long DEFAULT_DELAY = 1000L;

    /**
     * 获取锁key,锁的唯一标识
     * @return key str
     */
    String getKey();

    /**
     * 释放读锁
     */
    void rUnLock();

    /**
     * 释放写锁
     */
    void wUnLock();

    /**
     * 阻塞方式申请读锁
     * 阻塞直至申请成功才返回
     */
    void rLock();

    /**
     * 阻塞方式申请写锁
     * 阻塞直至申请成功才返回
     */
    void wLock();

    /**
     * 申请读锁,最多阻塞指定时间ts
     * @param ts 时间
     * @param unit 时间单位
     * @return lock结果
     */
    boolean tryRLock(long ts, TimeUnit unit);

    /**
     * 申请写锁,最多阻塞指定时间ts
     * @param ts 时间
     * @param unit 时间单位
     * @return lock结果
     */
    boolean tryWLock(long ts, TimeUnit unit);

    /**
     * 判断是否当前线程持有写锁
     * @return boolean
     */
    boolean isWHeldByCurrentThread();

    /**
     * lock优先级,用于multiLock时lock顺序排序
     * @return sequence
     */
    int getPriority();
}
