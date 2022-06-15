package com.springboot.levi.leviweb1.lock.impl.distributed.redis;

import com.springboot.levi.leviweb1.lock.api.ILock;
import com.springboot.levi.leviweb1.lock.api.LockType;
import com.springboot.levi.leviweb1.utils.SpringBeanFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2022-06-15 16:47
 */
@Slf4j
public class DistributedRedisLock implements ILock {

    /**默认锁闭时间（单位，毫秒）**/
    private static final long DEFAULT_LEASE_TIME = 60 * 1000L;

    private final String key;

    private final int priority;

    private RReadWriteLock lock;

    private DistributedRedisLock(String key, LockType lockType){
        RedissonClient redissonClient = SpringBeanFactory.getBean(RedissonClient.class);
        this.key = lockType.getType()+""+key;
        this.priority = lockType.getPriority();
        this.lock = redissonClient.getReadWriteLock(key);
    }

    public static synchronized DistributedRedisLock valueOf(String key, LockType type) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("invalid key:" + key);
        }
        return new DistributedRedisLock(key, type);
    }
    @Override
    public int compareTo(@Nonnull ILock o) {
        int x = this.priority;
        int y = o.getPriority();
        if (x == y) {
            x = this.key.hashCode();
            y = o.getKey().hashCode();
        }
        return Integer.compare(x, y);
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public void rUnLock() {
        this.lock.writeLock().unlock();
        log.info("read unlock :{}",this.key);
    }

    @Override
    public void wUnLock() {
        this.lock.writeLock().unlock();
        log.info("write unlock :{}",this.key);
    }

    @Override
    public void rLock() {
        this.lock.readLock().lock(DEFAULT_LEASE_TIME, TimeUnit.MILLISECONDS);
        log.info("read lock:{}", this.key);
    }

    @Override
    public void wLock() {
        this.lock.writeLock().lock(DEFAULT_LEASE_TIME, TimeUnit.MILLISECONDS);
        log.info("write lock:{}", this.key);
    }

    @Override
    public boolean tryRLock(long ts, TimeUnit unit) {
        if(unit == TimeUnit.SECONDS) {
            ts = ts * 1000;
        }else if(unit == TimeUnit.MINUTES) {
            ts = ts * 60 * 1000;
        }
        //其他的应该不会使用，就不需要转了
        try {
            boolean ret = this.lock.readLock().tryLock(ts, DEFAULT_LEASE_TIME, TimeUnit.MILLISECONDS);
            log.info("current thread:{} tryRLock:{} result:{}", Thread.currentThread().getName(), this.key, ret);
            return ret;
        } catch (InterruptedException e) {
            log.error("tryRLock InterruptedException.", e);
            return false;
        }
    }

    @Override
    public boolean tryWLock(long ts, TimeUnit unit) {
        if(unit == TimeUnit.SECONDS) {
            ts = ts * 1000;
        }else if(unit == TimeUnit.MINUTES) {
            ts = ts * 60 * 1000;
        }
        //其他的应该不会使用，就不需要转了
        try {
            boolean ret = this.lock.writeLock().tryLock(ts, DEFAULT_LEASE_TIME, TimeUnit.MILLISECONDS);
            log.info("current thread:{} tryWLock:{} result:{}", Thread.currentThread().getName(), this.key, ret);
            return ret;
        } catch (InterruptedException e) {
            log.error("tryRLock InterruptedException.", e);
            return false;
        }
    }

    @Override
    public boolean isWHeldByCurrentThread() {
        return false;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }


}
