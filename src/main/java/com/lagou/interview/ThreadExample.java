package com.lagou.interview;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
//        joinTest(); // join() 使用测试
        yieldTest(); // yield() 使用测试
    }

    /**
     * yield() 测试
     */
    private static void yieldTest() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("线程：" +
                            Thread.currentThread().getName() + " I：" + i);
                    if (i == 5) {
                        Thread.yield();
                    }
                }
            }
        };
        Thread t1 = new Thread(runnable, "T1");
        Thread t2 = new Thread(runnable, "T2");
        t1.start();
        t2.start();
    }

    /**
     * join() 使用
     * @throws InterruptedException
     */
    private static void joinTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 1; i < 6; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程睡眠：" + i + "秒。");
            }
        });
        thread.start(); // 开启线程
        thread.join(2000);
        // 主线程执行
        for (int i = 1; i < 4; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程睡眠：" + i + "秒。");
        }
    }
}
