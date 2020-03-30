package com.lagou.interview;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class GuavaExample {
    public static void main(String[] args) throws ExecutionException {
        // 创建缓存容器
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2) // 设置缓存最大长度
                .build();
        // 设置缓存
        cache.put("key", "Hello,Google.");
        // 查询缓存
        String value = cache.get("key", new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "什么也没有"; // 没有结果时的默认值
            }
        });
        // 输出缓存值
        System.out.println(value);
    }
}
