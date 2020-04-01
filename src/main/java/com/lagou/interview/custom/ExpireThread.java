package com.lagou.interview.custom;

import java.util.concurrent.TimeUnit;

/**
 * 过期缓存检测线程
 */
public class ExpireThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                // 每十秒检测一次
                TimeUnit.SECONDS.sleep(10);
                // 缓存检测和清除的方法
                expireCache();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 缓存检测和清除的方法
     */
    private void expireCache() {
        System.out.println("检测缓存是否过期缓存");
        for (String key : CacheGlobal.concurrentMap.keySet()) {
            MyCache cache = CacheGlobal.concurrentMap.get(key);
            // 当前时间 - 写入时间
            long timoutTime = TimeUnit.NANOSECONDS.toSeconds(
                    System.nanoTime() - cache.getWriteTime());
            if (cache.getExpireTime() > timoutTime) {
                // 没过期
                continue;
            }
            // 清除过期缓存
            CacheGlobal.concurrentMap.remove(key);
        }
    }
}
