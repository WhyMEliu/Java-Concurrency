package com.peng.concurrency.example.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

//CyclicBarrier使用方式
@Slf4j
public class CyclicBarrierExample {
	
	//private static CyclicBarrier barrier = new CyclicBarrier(5);
	private static CyclicBarrier barrier = new CyclicBarrier(5, ()->{
		log.info("callback is running");
	});
	
	public static void main(String[] args) throws Exception {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 10; i++) {
			final int threadNum = i;
			Thread.sleep(1000);
			executor.execute(() -> {
				try{
					race(threadNum);
				}catch(Exception e){
					log.error("exception", e);
				}
			}); 
		}
		executor.shutdown();
	}
	
	private static void race(int threadNum) throws Exception{
		Thread.sleep(1000);
		log.info("{} is ready", threadNum);
		try{
			//可以进行等待时间控制，超过时间则直接执行
			barrier.await(2000, TimeUnit.MILLISECONDS);
		}catch(Exception e){
			log.warn("Exception", e);
		}
		//barrier.await();
		log.info("{} continue", threadNum);
	}
}
