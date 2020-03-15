package com.lagou.interview.ext;

public class VolatileExample {
    // 非同步代码演示参数
    private static int count = 0; // 计数器
    private static final int size = 100000; // 循环测试次数
    // 可见性演示参数
    private static volatile boolean vflag = false;

    // 指令重排参数
    private static volatile int a = 0, b = 0;
    private static volatile int x = 0, y = 0;

    public static void main(String[] args) throws InterruptedException {
//        noSyncTest(); // 非同步代码演示
//        visibleTest(); // 内存可见性演示


        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Thread t1 = new Thread(() -> {
                // 有可能发生指令重排，先 x=b 再 a=1
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                // 有可能发生指令重排，先 y=a 再 b=1
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("第 " + i + "次，x=" + x + " | y=" + y);
            if (x == 0 && y == 0) {
                // 发生了指令重排
                break;
            }
            // 初始化变量
            a = 0;
            b = 0;
            x = 0;
            y = 0;
        }
    }

    /**
     * 内存可见性演示
     */
    private static void visibleTest() {
        new Thread(() -> {
            try {
                // 暂停 0.5s 执行
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            vflag = true;
            System.out.println("flag 被修改成 true");
        }).start();

        // 一直循环检测 flag=true
        while (true) {
            if (vflag) {
                System.out.println("检测到 flag 变为 true");
                break;
            }
        }
    }

    /**
     * 非同步代码演示
     */
    private static void noSyncTest() {
        // i++
        Thread thread = new Thread(() -> {
            for (int i = 1; i <= size; i++) {
                synchronized (VolatileExample.class) {
                    count++;
                }
            }
        });
        thread.start();
        // i--
        for (int i = 1; i <= size; i++) {
            synchronized (VolatileExample.class) {
                count--;
            }
        }
        // 等所有线程执行完成
        while (thread.isAlive()) {
        }
        System.out.println(count); // 打印结果
    }
}
