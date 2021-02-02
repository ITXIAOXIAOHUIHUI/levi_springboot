package com.springboot.levi.leviweb1.java.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author jianghaihui
 * @date 2021/1/29 14:31
 */
public interface ILock extends Comparable<ILock> {

    /**
     * 申请锁默认阻塞时间
     */
    long DEFAULT_DELAY = 1000L;

    /**
     * 获取锁key,锁的唯一表示
     * key str
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
     *阻塞方式申请读锁
     * 阻塞知道申请成功才返回
     */
    void rLock();

    /**
     * 阻塞方式申请写锁
     * 阻塞直至申请成功才返回
     */
    void wLock();


    /**
     * 申请读锁，最多阻塞指定时间ts
     */
    boolean tryRLock(long ts , TimeUnit unit);

    /**
     * 申请写锁，最多阻塞指定时间ts
     */
    boolean tryWLock(long ts,TimeUnit unit);

    /**
     * 判断是否当前线程持有写锁
     */
    boolean isWHeldByCurrentThread();

    /**
     * lock 优先级，用于multiLock时lock顺序排序
     */
    int getPriority();





}
