package com.springboot.levi.leviweb1.lock;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Least Recently Used cache
 * @param <K> key type
 * @param <V> value type
 * @author wuxiaowu
 */
public class LruCache<K, V> extends LinkedHashMap<K, V> {
    private final int maxCacheSize;

    public LruCache(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        maxCacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > maxCacheSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
