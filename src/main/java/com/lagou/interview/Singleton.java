package com.lagou.interview;

/**
 * 单例示例
 */
public class Singleton {
    //    //---------- 饿汉模式
//    // 声明私有对象
//    private static Singleton instance = new Singleton();
//
//    // 获取实例（单例对象）
//    public static Singleton getInstance() {
//        return instance;
//    }
//    //---------- 懒汉模式（非线程安全）
//    // 声明私有对象
//    private static Singleton instance;
//
//    // 获取实例（单例对象）
//    public static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }

//    //---------- 双重检测锁
//    // 声明私有对象
//    private volatile static Singleton instance;
//
//    // 获取实例（单例对象）
//    public static Singleton getInstance() {
//        // 第一次判断
//        if (instance == null) {
//            synchronized (Singleton.class) {
//                // 第二次判断
//                if (instance == null) {
//                    instance = new Singleton();
//                }
//            }
//        }
//        return instance;
//    }

//    // 静态内部类
//    private static class SingletonInstance {
//        private static final Singleton instance = new Singleton();
//    }
//
//    // 获取实例（单例对象）
//    public static Singleton getInstance() {
//        return SingletonInstance.instance;
//    }

    // 枚举类型是线程安全的，并且只会装载一次
    private enum SingletonEnum {
        INSTANCE;
        // 声明单例对象
        private final Singleton instance;

        // 实例化
        SingletonEnum() {
            instance = new Singleton();
        }

        private Singleton getInstance() {
            return instance;
        }
    }

    // 获取实例（单例对象）
    public static Singleton getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }

    private Singleton() {
    }

    // 类方法
    public void sayHi() {
        System.out.println("Hi,Java.");
    }
}

class SingletonTest {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        singleton.sayHi();
    }
}