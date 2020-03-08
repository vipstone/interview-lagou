package com.lagou.interview;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // 模拟线程池溢出
        Executor executor = new ThreadPoolExecutor(2, 4, 10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 6; i++) {
            executor.execute(() -> {
                System.out.println("当前线程：" + Thread.currentThread().getName());
            });
        }

    }
}
