package com.peng.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedExample {
	
	//修饰代码块
	//大括号扩括起来的代码，作用于调用的对象
	public void test1(int j){
		synchronized(this){
			for (int i = 0; i < 10; i++) {
				log.info("test1 {} - {}", j, i);
			}
		}
	}
	
	//修饰方法
	//整个方法，作用于调用的对象
	public synchronized void test2(int j){
		for (int i = 0; i < 10; i++) {
			log.info("test2 {} - {}", j, i);
		}
	}
	
	//修饰类
	//括号括起来的部分，作用于所有对象
	public static void test3(int j){
		synchronized(SynchronizedExample.class){
			for (int i = 0; i < 10; i++) {
				log.info("test3 {} - {}", j, i);
			}
		}
	}
	
	//修饰静态方法
	//整个静态方法，作用于所有对象
	public static synchronized void test4(int j){
		for (int i = 0; i < 10; i++) {
			log.info("test4 {} - {}", j, i);
		}
	}
	
	//直接找对应的测试就行
	public static void main(String[] args) {
		SynchronizedExample example1 = new SynchronizedExample();
		SynchronizedExample example2 = new SynchronizedExample();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(()->{
			example1.test1(1);
		}); 
		executorService.execute(()->{
			example2.test1(2);
		}); 
	}
}
