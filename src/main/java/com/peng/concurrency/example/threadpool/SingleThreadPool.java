package com.peng.concurrency.example.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

//SingleThreadPool 使用
@Slf4j
public class SingleThreadPool {
	
	 public static void main(String[] args) {

	        ExecutorService executorService = Executors.newSingleThreadExecutor();

	        for (int i = 0; i < 10; i++) {
	            final int index = i;
	            executorService.execute(new Runnable() {
	                @Override
	                public void run() {
	                    log.info("task:{}", index);
	                }
	            });
	        }
	        executorService.shutdown();
	    }
}
