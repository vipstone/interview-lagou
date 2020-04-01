package com.lagou.interview.custom;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Cache 全局类
 */
public class CacheGlobal {
    // 全局缓存对象
    public static ConcurrentMap<String, MyCache> concurrentMap = new ConcurrentHashMap<>();
}
