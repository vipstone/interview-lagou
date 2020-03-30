package com.lagou.interview;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * EhCache 使用示例
 */
public class EhCacheExample {
    public static void main(String[] args) {
        // 创建缓存管理器
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        // 初始化 EhCache
        cacheManager.init();
        // 创建缓存（存储器）
        Cache<String, String> myCache = cacheManager.createCache("MYCACHE",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
                        ResourcePoolsBuilder.heap(10))); // 设置缓存的最大容量
        // 设置缓存
        myCache.put("key", "Hello,Java.");
        // 读取缓存
        String value = myCache.get("key");
        // 输出缓存
        System.out.println(value);
        // 关闭缓存
        cacheManager.close();
    }
}
