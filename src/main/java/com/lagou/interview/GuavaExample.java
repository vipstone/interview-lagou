package com.lagou.interview;

import com.google.common.cache.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaExample {
    public static void main(String[] args) throws ExecutionException {
        // 创建方式一：LoadingCache
        LoadingCache<String, String> loadCache = CacheBuilder.newBuilder()
                // 并发级别设置为5，是指可以同时写缓存的线程数
                .concurrencyLevel(5)
                // 设置 8 秒钟过期
                .expireAfterWrite(8, TimeUnit.SECONDS)
                //设置缓存容器的初始容量为10
                .initialCapacity(10)
                // 设置缓存最大容量为100，超过之后就会按照 LRU 算法移除缓存项
                .maximumSize(100)
                // 设置要统计缓存的命中率
                .recordStats()
                // 设置缓存的移除通知
                .removalListener(new RemovalListener<Object, Object>() {
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
                // 指定 CacheLoader，缓存不存在时，可自动加载缓存
                .build(
                        new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) throws Exception {
                                // 自动加载缓存的业务
                                return "cache-value:" + key;
                            }
                        }
                );
        // 设置缓存
        loadCache.put("c1", "Hello, c1.");
        // 查询缓存
        String val = loadCache.get("c1");
        System.out.println(val);
        // 查询不存在的缓存
        String noval = loadCache.get("noval");
        System.out.println(noval);

        // 创建方式二：Callable
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2) // 设置缓存最大长度
                .build();
        // 设置缓存
        cache.put("k1", "Hello, k1.");
        // 查询缓存
        String value = cache.get("k1", new Callable<String>() {
            @Override
            public String call() {
                // 缓存不存在时，执行
                return "nil";
            }
        });
        // 输出缓存值
        System.out.println(value);
        // 查询缓存
        String nokey = cache.get("nokey", new Callable<String>() {
            @Override
            public String call() {
                // 缓存不存在时，执行
                return "nil";
            }
        });
        // 输出缓存值
        System.out.println(nokey);
    }
}
