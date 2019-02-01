package com.peng.concurrency.example.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

import com.peng.concurrency.annotations.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * LongAdder
 * 实现原理：木知....听说是1.8出的，比AtomicLong效率高 
 * 网上说：将热点数据分离,比如,可以将AtomicInteger的内部核心数据value分离成一个数组,
 * 每个线程访问时,通过哈希等算法映射到其中一个数字进行计数,
 * 而最终的计数结果,则为这个数组的求和累加,
 * 其中,热点数据value被分离成多个单元cell,
 * 每个cell独自维护内部的值,当前对象的实际值由所有的cell累计合成,
 * 这样,热点就进行了有效的分离,提高了并行度,LongAdder正是使用了这种思想
 * @author peng
 */

@Slf4j
@ThreadSafe
public class LongAdderExample {
	//请求总数
	public static int clientTotal = 5000;
	
	//同时并发执行的线程数
	public static int threadTotal = 200;
	
	public static LongAdder count = new LongAdder();
	
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
		count.increment();
	}
}
