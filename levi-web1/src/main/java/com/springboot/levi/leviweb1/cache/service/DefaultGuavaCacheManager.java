package com.springboot.levi.leviweb1.cache.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jianghaihui
 * @date 2021/7/12 15:16
 */
@Slf4j
public class DefaultGuavaCacheManager {
    //缓存包装类
    private static AbstractGuavaCache<String, Object> cacheWrapper;

    /**
     * 初始化缓存容器
     */
    public static boolean initGuavaCache() {
        try {
            cacheWrapper = DefaultGuavaCache.getInstance();
            if (cacheWrapper != null) {
                return true;
            }
        } catch (Exception e) {
            log.error("Failed to init Guava cache;", e);
        }
        return false;
    }

    public static void put(String key, Object value) {
        cacheWrapper.getCache().put(key, value);
    }

    /**
     * 指定缓存时效
     * @param key
     */
    public static void invalidate(String key) {
        cacheWrapper.getCache().invalidate(key);
    }

    /**
     * 批量清除
     * @param keys
     */
    public static void invalidateAll(Iterable<?> keys) {
        cacheWrapper.getCache().invalidateAll(keys);
    }

    /**
     * 清除所有缓存项 ： 慎用
     */
    public static void invalidateAll() {
        cacheWrapper.getCache().invalidateAll();
    }

    public static Object get(String key) {
        try {
            return cacheWrapper.getCache().get(key);
        } catch (Exception e) {
            log.error("Failed to get value from guava cache;", e);
        }
        return null;
    }

    /**
     * 使用静态内部类实现一个默认的缓存，委托给manager来管理
     *
     * DefaultGuavaCache 使用一个简单的单例模式
     * @param <String>
     * @param <Object>
     */
    private static class DefaultGuavaCache<String, Object> extends
            AbstractGuavaCache<String, Object> {

        private static AbstractGuavaCache cache = new DefaultGuavaCache();

        /**
         * 处理自动载入缓存，按实际情况载入
         * @param key
         * @return
         */
        @Override
        protected Object fetchData(String key) {
            return null;
        }

        public static AbstractGuavaCache getInstance() {
            return DefaultGuavaCache.cache;
        }

    }
}
