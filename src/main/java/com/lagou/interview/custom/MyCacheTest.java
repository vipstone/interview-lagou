package com.lagou.interview.custom;

public class MyCacheTest {
    public static void main(String[] args) {
        CacheUtils cache = new CacheUtils();
        // 存入缓存
        cache.put("key", "老王", 10);
        // 查询缓存
        String val = (String) cache.get("key");
        System.out.println(val);
        // 查询不存在的缓存
        String noval = (String) cache.get("noval");
        System.out.println(noval);
    }
}
