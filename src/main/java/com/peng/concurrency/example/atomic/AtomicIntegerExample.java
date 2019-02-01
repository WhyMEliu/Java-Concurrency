package com.peng.concurrency.example.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import com.peng.concurrency.annotations.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * Atomic（基本类型都有）
 * 实现原理：
 * 用当前线程的值不停的去主内存中取数据，
 * 再和当前数据做对比，当数据相同时再进行相加操作
 * 重点：CAS同步机制（Unsafe.compareAndSwapInt）
 * 缺点：并发量大的时候，效率低
 * @author 
 */


@Slf4j
@ThreadSafe
public class AtomicIntegerExample {

	//请求总数
	public static int clientTotal = 5000;
	
	//同时并发执行的线程数
	public static int threadTotal = 200;
	
	public static AtomicInteger count = new AtomicInteger(0);
	
	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++) {
			executorService.execute(() -> {
				try{
					semaphore.acquire();
					add();
					semaphore.release();
				} catch (Exception e){
					log.error("exception" , e);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		log.info("count:{}" , count);
		
	}
	
	private static void add(){
		//先加，再赋值
		count.incrementAndGet();
		//先赋值，再加
		//count.getAndIncrement();
	}
}
