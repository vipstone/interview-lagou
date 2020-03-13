package com.lagou.interview;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized vs Lock
 */
public class LockExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        try {
            // 加锁
            lock.lock();
            //......业务处理
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}
