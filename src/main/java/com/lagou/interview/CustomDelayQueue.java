package com.lagou.interview;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 自定义延迟队列
 */
public class CustomDelayQueue {
    // 延迟消息队列
    private static DelayQueue delayQueue = new DelayQueue();

    public static void main(String[] args) throws InterruptedException {
        producer(); // 调用生产者
        consumer(); // 调用消费者
    }

    // 生产者
    public static void producer() {
        // 添加消息
        delayQueue.put(new MyDelay(1000, "消息1"));
        delayQueue.put(new MyDelay(3000, "消息2"));
    }

    // 消费者
    public static void consumer() throws InterruptedException {
        System.out.println("开始执行时间：" +
                DateFormat.getDateTimeInstance().format(new Date()));
        while (!delayQueue.isEmpty()) {
            System.out.println(delayQueue.take());
        }
        System.out.println("结束执行时间：" +
                DateFormat.getDateTimeInstance().format(new Date()));
    }

    /**
     * 自定义延迟队列
     */
    static class MyDelay implements Delayed {
        // 延迟截止时间（单位：毫秒）
        long delayTime = System.currentTimeMillis();

        // 借助 lombok 实现
        @Getter
        @Setter
        private String msg;

        /**
         * 初始化
         * @param delayTime 设置延迟执行时间
         * @param msg       执行的消息
         */
        public MyDelay(long delayTime, String msg) {
            this.delayTime = (this.delayTime + delayTime);
            this.msg = msg;
        }

        // 获取剩余时间
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        // 队列里元素的排序依据
        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return this.msg;
        }
    }
}
