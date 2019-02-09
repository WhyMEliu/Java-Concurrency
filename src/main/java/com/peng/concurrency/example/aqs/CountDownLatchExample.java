package com.peng.concurrency.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

//CountDownLatch 使用
@Slf4j
public class CountDownLatchExample {
	
	private final static int threadCount = 200;
	
	public static void main(String[] args) throws Exception {
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		
		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.execute(() -> {
				try{
					test(threadNum);
				}catch(Exception e) {
					log.error("Exception",e);
				}finally{
					//threadCount - 1 
					countDownLatch.countDown();
				}
			});
		}
		//直到 threadCount 为 0 
		//countDownLatch.await(); 
		
		//设定到达多少秒没有执行完，就不再等待直接执行以后的操作
		countDownLatch.await(100, TimeUnit.MILLISECONDS);
		log.info("finish");
		exec.shutdown();
	}
	
	private static void test(int threadNum) throws Exception{
		Thread.sleep(100);
		log.info("{}", threadNum);
		Thread.sleep(100);
	}
}
