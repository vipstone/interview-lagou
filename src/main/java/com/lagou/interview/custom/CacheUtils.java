package com.lagou.interview.custom;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 缓存操作工具类
 */
public class CacheUtils {

    /**
     * 添加缓存
     * @param key
     * @param value
     * @param expire
     */
    public void put(String key, Object value, long expire) {
        // 非空判断，借助 commons-lang3
        if (StringUtils.isBlank(key)) return;
        // 当缓存存在时，更新缓存
        if (CacheGlobal.concurrentMap.containsKey(key)) {
            MyCache cache = CacheGlobal.concurrentMap.get(key);
            cache.setHitCount(cache.getHitCount() + 1);
            cache.setWriteTime(System.currentTimeMillis());
            cache.setLastTime(System.currentTimeMillis());
            cache.setExpireTime(expire);
            cache.setValue(value);
            return;
        }
        // 创建缓存
        MyCache cache = new MyCache();
        cache.setKey(key);
        cache.setValue(value);
        cache.setWriteTime(System.currentTimeMillis());
        cache.setLastTime(System.currentTimeMillis());
        cache.setHitCount(1);
        cache.setExpireTime(expire);
        CacheGlobal.concurrentMap.put(key, cache);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public Object get(String key) {
        // 非空判断
        if (StringUtils.isBlank(key)) return null;
        // 字典中不存在
        if (CacheGlobal.concurrentMap.isEmpty()) return null;
        if (!CacheGlobal.concurrentMap.containsKey(key)) return null;
        MyCache cache = CacheGlobal.concurrentMap.get(key);
        if (cache == null) return null;
        // 惰性删除，判断缓存是否过期
        long timoutTime = TimeUnit.NANOSECONDS.toSeconds(
                System.nanoTime() - cache.getWriteTime());
        if (cache.getExpireTime() <= timoutTime) {
            // 缓存过期
            return null;
        }
        // 清除过期缓存
        CacheGlobal.concurrentMap.remove(key);
        cache.setHitCount(cache.getHitCount() + 1);
        cache.setLastTime(System.currentTimeMillis());
        return cache.getValue();
    }
}
