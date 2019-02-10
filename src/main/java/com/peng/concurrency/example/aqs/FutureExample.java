package com.peng.concurrency.example.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

//Future 使用
@Slf4j
public class FutureExample {
	
	static class MyCallable implements Callable<String>{

		@Override
		public String call() throws Exception {
			log.info("do something in callable");
			Thread.sleep(5000);
			return "Done";
		}
	}
	
	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<String> future = executor.submit(new MyCallable());
		log.info("do something in main");
		Thread.sleep(1000);
		String result = future.get();
		log.info("result: {}", result);
	}
}
