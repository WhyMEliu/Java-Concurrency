package com.peng.concurrency.example.singleton;

import com.peng.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 */
@ThreadSafe
public class HungrySingleton {

    // 私有构造函数
    private HungrySingleton() {

    }

    // 单例对象
    private static HungrySingleton instance = new HungrySingleton();

   /*
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }*/
    
    // 静态的工厂方法
    public static HungrySingleton getInstance() {
        return instance;
    }
}
