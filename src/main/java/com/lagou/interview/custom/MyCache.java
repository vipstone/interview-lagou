package com.lagou.interview.custom;

import lombok.Getter;
import lombok.Setter;

/**
 * 缓存实体类
 */
@Getter
@Setter
public class MyCache implements Comparable<MyCache> {
    // 缓存键
    private Object key;
    // 缓存值
    private Object value;
    // 最后访问时间
    private long lastTime;
    // 创建时间
    private long writeTime;
    // 存活时间
    private long expireTime;
    // 命中次数
    private Integer hitCount;

    @Override
    public int compareTo(MyCache o) {
        return hitCount.compareTo(o.hitCount);
    }
}
