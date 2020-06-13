package com.lagou.interview;

/**
 * 幂等性示例
 */
public class IdempotentExample {
    // 变量
    private static int count = 0;

    /**
     * 非幂等性方法
     */
    public static void addCount() {
        count++;
    }

    
    /**
     * 幂等性方法
     */
    public static void printCount() {
        System.out.println(count);
    }
}
