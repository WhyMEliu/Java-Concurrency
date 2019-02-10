package com.peng.concurrency.example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

//FixedThreadPool 使用
@Slf4j
public class FixedThreadPoolExample {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < 10; i++) {
			final int index = i;
			executor.execute(new Runnable(){
				@Override
				public void run() {
					log.info("task:{}", index);
				}
			});
		}
		executor.shutdown();
	}
}
