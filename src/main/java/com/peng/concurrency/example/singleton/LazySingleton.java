package com.peng.concurrency.example.singleton;

import com.peng.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式 -》 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@ThreadSafe
public class LazySingleton {

    // 私有构造函数
    private LazySingleton() {

    }

    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // 单例对象 volatile + 双重检测机制 -> 禁止指令重排
    private volatile static LazySingleton instance = null;

    // 静态的工厂方法
    public static LazySingleton getInstance() {
        if (instance == null) { // 双重检测机制       
            synchronized (LazySingleton.class) { // 同步锁
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
