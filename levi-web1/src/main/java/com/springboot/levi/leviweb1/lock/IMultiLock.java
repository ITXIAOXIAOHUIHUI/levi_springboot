package com.springboot.levi.leviweb1.lock;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author jianghaihui
 * @date 2021/5/28 13:46
 */
public interface IMultiLock {

    /**
     * 增加需要申请的读锁
     * @param lock 提交的读锁
     * @return 操作是否成功
     */
    boolean preRLock(ILock lock);

    /**
     * 增加需要申请的写锁
     * @param lock 提交的写锁
     * @return 操作是否成功
     */
    boolean preWLock(ILock lock);

    /**
     * 增加需要申请的读锁
     * @param lock 提交的读锁
     * @return 操作是否成功
     */
    boolean preRLockS(List<ILock> lock);

    /**
     * 增加需要申请的写锁
     * @param lock 提交的写锁
     * @return 操作是否成功
     */
    boolean preWLockS(List<ILock> lock);
    /**
     * 阻塞式批量申请锁
     * @return 操作是否成功
     */
    boolean lock();

    /**
     * 非阻塞式批量申请锁
     * @param ts 操作等待阻塞时间
     * @param unit 操作等待阻塞时间单位
     * @return 操作是否成功
     */
    boolean tryLock(long ts, TimeUnit unit);

    /**
     * 释放锁
     */
    void unLock();
}
