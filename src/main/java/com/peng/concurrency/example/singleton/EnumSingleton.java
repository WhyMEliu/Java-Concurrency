package com.peng.concurrency.example.singleton;

import com.peng.concurrency.annotations.Recommend;
import com.peng.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式：最安全
 * 
 * 安全发布对象规则：
 * 在静态初始化函数中初始化一个对象引用
 * 将对象的引用保存到一个由锁保护的域中
 * 将对象的引用保存到某个正确构造对象的final类型域中
 * 将对象的引用保存到volatile类型域或者AtomicReference对象中
 */
@ThreadSafe
@Recommend
public class EnumSingleton {

    // 私有构造函数
    private EnumSingleton() {

    }

    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private EnumSingleton singleton;
       
        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return singleton;
        }
    }
}
