package com.lagou.interview.ext;

import java.util.concurrent.TimeUnit;

public class LockExample {
    public static void main(String[] args) {
        deadLock(); // 死锁

        reentrantA(); // 可重入锁
    }

    /**
     * 可重入锁 A 方法
     */
    private synchronized static void reentrantA() {
        System.out.println(Thread.currentThread().getName() + "：执行 reentrantA");
        reentrantB();
    }

    /**
     * 可重入锁 B 方法
     */
    private synchronized static void reentrantB() {
        System.out.println(Thread.currentThread().getName() + "：执行 reentrantB");
    }

    /**
     * 死锁
     */
    private static void deadLock() {
        Object lock1 = new Object();
        Object lock2 = new Object();
        // 线程一拥有 lock1 试图获取 lock2
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("获取 lock1 成功");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 试图获取锁 lock2
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }).start();
        // 线程二拥有 lock2 试图获取 lock1
        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("获取 lock2 成功");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 试图获取锁 lock1
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }).start();
    }
}
