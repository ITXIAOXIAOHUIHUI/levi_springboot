package com.springboot.levi.leviweb1.lock.impl.local;

import com.springboot.levi.leviweb1.lock.api.ILock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-15 17:14
 */
@Slf4j
public class LocalLock extends ReentrantReadWriteLock implements ILock {
    /**
     * 锁的默认优先级
     */
    private static final int DEFAULT_PRI = 0;

    private final String key;
    private final int priority;

    /**
     * 记录持有写锁的线程名
     */
    private volatile String wHolder;

    public String getWHolder() {
        return wHolder;
    }

    public LocalLock(String key) {
        this.key = key;
        this.priority = DEFAULT_PRI;
    }

    public LocalLock(String key, int priority) {
        Objects.requireNonNull(key);
        this.key = key;
        this.priority = priority;
    }

    public LocalLock(boolean fair, String key, int priority) {
        super(fair);
        Objects.requireNonNull(key);
        this.key = key;
        this.priority = priority;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public void rUnLock() {
        this.readLock().unlock();
        recordUnlock("rUnLock");
    }

    @Override
    public void wUnLock() {
        this.writeLock().unlock();
        recordUnlock("wUnLock");
    }

    @Override
    public void rLock() {
        debug("rLock");
        this.readLock().lock();
        recordRHolder(true);
    }

    @Override
    public void wLock() {
        debug("wLock");
        this.writeLock().lock();
        recordWHolder(true);
    }

    @Override
    public boolean tryRLock(long ts, TimeUnit unit) {
        try {
            debug("tryRLock");
            boolean ret = this.readLock().tryLock(ts, unit);
            recordRHolder(ret);
            warn("tryRLock", ret);
            return ret;
        } catch (InterruptedException e) {
            log.error("tryRLock InterruptedException.", e);
            return false;
        }
    }

    @Override
    public boolean tryWLock(long ts, TimeUnit unit) {
        try {
            debug("tryWLock");
            boolean ret = this.writeLock().tryLock(ts, unit);
            recordWHolder(ret);
            warn("tryWLock", ret);
            return ret;
        } catch (InterruptedException e) {
            log.error("tryWLock InterruptedException.", e);
            return false;
        }
    }

    @Override
    public boolean isWHeldByCurrentThread() {
        return this.writeLock().isHeldByCurrentThread();
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    /**
     * 按优先级排序 优先级相同,则按照hash code 排序
     *
     * @param o object
     * @return result
     */
    @Override
    public int compareTo(ILock o) {
        int x = this.priority;
        int y = o.getPriority();
        if (x == y) {
            x = this.key.hashCode();
            y = o.getKey().hashCode();
        }
        return Integer.compare(x, y);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("key", key)
                .append("sequence", priority)
                .append("wHolder", wHolder)
                .toString();
    }

    /**
     * debug记录锁操作
     *
     * @param method method
     */
    private void debug(String method) {
        log.debug("current thread:{} {} {}.", Thread.currentThread().getName(), method, this.key);
    }

    /**
     * warn记录锁操作失败
     *
     * @param method method
     * @param ret    ret
     */
    private void warn(String method, boolean ret) {
        if (!ret) {
            log.warn("current thread:{} failed to {} {}.", Thread.currentThread().getName(), method, this.key);
        }
    }

    /**
     * 记录读锁holder
     *
     * @param ret ret
     */
    private void recordRHolder(boolean ret) {
        if (ret) {
            log.info("current thread:{} holds read lock:{}.", Thread.currentThread().getName(), this.key);
        }
    }

    /**
     * 记录写锁holder
     *
     * @param ret ret
     */
    private void recordWHolder(boolean ret) {
        if (ret) {
            log.info("current thread:{} holds write lock:{}.", Thread.currentThread().getName(), this.key);
            this.wHolder = Thread.currentThread().getName();
        }
    }

    /**
     * 记录释放锁
     *
     * @param method method
     */
    private void recordUnlock(String method) {
        log.info("current thread:{} {} lock:{}.", Thread.currentThread().getName(), method, this.key);
        if (Objects.equals(method, "wUnLock")) {
            this.wHolder = null;
        }
    }
}
