package com.peng.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

//Semaphore 使用
//用于控制同一时间的并发数，可对线程进行丢弃，控制
@Slf4j
public class SemaphoreExample {
	
	 private final static int threadCount = 200;

	    public static void main(String[] args) throws Exception {

	        ExecutorService exec = Executors.newCachedThreadPool();
	        
	        //同时并发允许许可只有3个
	        final Semaphore semaphore = new Semaphore(3);

	        for (int i = 0; i < threadCount; i++) {
	            final int threadNum = i;
	            exec.execute(() -> {
	                try {
	                	//尝试获取一个许可,如果不能获取就直接跳过
	                	//if(semaphore.tryAcquire()){
	                	//还可以设置尝试等待时间，和尝试次数~
	                	if(semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)){
	                		test(threadNum);
		                    semaphore.release();//丢弃一个许可
	                	}
	                	//semaphore.acquire();//获取一个许可
	                	//semaphore.acquire(3); //也可以获取多个许可
	                    //test(threadNum);
	                    //semaphore.release();//丢弃一个许可
	                    //semaphore.acquire(3); //也可以丢弃多个许可
	                } catch (Exception e) {
	                    log.error("exception", e);
	                } 
	            });
	        }
	        log.info("finish");
	        exec.shutdown();
	    }

	    private static void test(int threadNum) throws Exception {
	        log.info("{}", threadNum);
	        Thread.sleep(1000);
	    }
}
