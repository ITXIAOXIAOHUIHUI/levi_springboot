package com.springboot.levi.leviweb1.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author jianghaihui
 * @date 2021/5/28 14:19
 */
@Slf4j
public class SingletonLock extends LocalLock {

    private static final Logger LOG = LoggerFactory.getLogger(SingletonLock.class);

    /**
     * 缓存最小值
     */
    private static final int CACHE_NUM_MIN = 1;
    /**
     * 缓存最大值10k
     */
    private static final int CACHE_NUM_MAX = 10240;

    /**
     * 缓存各种类型的锁单例对象
     */
    private static final Map<LockType, LruCache<String, SingletonLock>> CACHE_LOCKS = new HashMap<>();

    private final LockType lockType;

    private SingletonLock(String key, LockType lockType) {
        super(key, lockType.getPriority());
        this.lockType = lockType;
    }

    private SingletonLock(boolean fair, String key, LockType lockType) {
        super(fair, key, lockType.getPriority());
        this.lockType = lockType;
    }

    /**
     * 通过该方法生成一个lock对象 缓存的删除由put触发,也即当前方法,所以需要synchronized做并发控制
     *
     * @param key key
     * @return lock
     */
    public static synchronized SingletonLock valueOf(String key, LockType type) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("invalid key:" + key);
        }

        if (!CACHE_LOCKS.containsKey(type)) {
            throw new RuntimeException(String.format("please register type:%s firstly.", type));
        }

        LruCache<String, SingletonLock> cacheLock = CACHE_LOCKS.get(type);
        if (!cacheLock.containsKey(key)) {
            cacheLock.put(key, new SingletonLock(key, type));
        }

        return cacheLock.get(key);
    }

    public static synchronized SingletonLock valueOf(boolean fair, String key, LockType type) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("invalid key:" + key);
        }

        if (!CACHE_LOCKS.containsKey(type)) {
            throw new RuntimeException(String.format("please register type:%s firstly.", type));
        }

        LruCache<String, SingletonLock> cacheLock = CACHE_LOCKS.get(type);
        if (!cacheLock.containsKey(key)) {
            cacheLock.put(key, new SingletonLock(fair, key, type));
        }

        return cacheLock.get(key);
    }

    /**
     * 注册LockType,指定缓存数目
     *
     * @param type     type
     * @param cacheNum cacheNum
     * @return boolean
     */
    public static synchronized boolean registerLockType(LockType type, int cacheNum) {
        if (CACHE_LOCKS.containsKey(type)) {
            return true;
        }

        // 最少缓存1个,最多缓存2000个
        cacheNum = (cacheNum < CACHE_NUM_MIN) ? CACHE_NUM_MIN : cacheNum;
        cacheNum = (cacheNum > CACHE_NUM_MAX) ? CACHE_NUM_MAX : cacheNum;

        LruCache<String, SingletonLock> cacheLock = new LruCache<String, SingletonLock>(cacheNum) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                // 是否满
                boolean full = super.removeEldestEntry(eldest);

                // lock是否free
                SingletonLock lock = (SingletonLock)eldest.getValue();
                boolean free = (0 == lock.getReadHoldCount() && 0 == lock.getWriteHoldCount() && !lock
                        .hasQueuedThreads());

                // cache满了,lock不是free,异常情况
                if (full && !free) {
                    LOG.error("cache is full, eldest not free. lock:{}.", lock);
                }

                return (free && full);
            }
        };
        CACHE_LOCKS.put(type, cacheLock);
        return true;
    }

    @Override
    public void rUnLock() {
        access();
        super.rUnLock();
    }

    @Override
    public void wUnLock() {
        access();
        super.wUnLock();
    }

    @Override
    public void rLock() {
        access();
        super.rLock();
    }

    @Override
    public void wLock() {
        access();
        super.wLock();
    }

    @Override
    public boolean tryRLock(long ts, TimeUnit unit) {
        access();
        return super.tryRLock(ts, unit);
    }

    @Override
    public boolean tryWLock(long ts, TimeUnit unit) {
        access();
        return super.tryWLock(ts, unit);
    }

    /**
     * 在cache中访问lock,刷新lru
     */
    private void access() {
        CACHE_LOCKS.get(lockType).get(this.getKey());
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
