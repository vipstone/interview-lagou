package com.lagou.interview;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 自定义消息队列
 */
public class CustomQueue {
    // 定义消息队列
    private static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {
        producer(); // 调用生产者
        consumer(); // 调用消费者
    }

    // 生产者
    public static void producer() {
        // 添加消息
        queue.add("first message.");
        queue.add("second message.");
        queue.add("third message.");
    }

    // 消费者
    public static void consumer() {
        while (!queue.isEmpty()) {
            // 消费消息
            System.out.println(queue.poll());
        }
    }
}

