package com.peng.concurrency.example.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

//FutureTast 使用
@Slf4j
public class FutureTastExample {
	
	public static void main(String[] args) throws Exception {
		FutureTask<String> futureTask = new FutureTask<>(new Callable<String>(){

			@Override
			public String call() throws Exception {
				log.info("do something in callable");
				Thread.sleep(5000);
				return "Done";
			}
			
		});
		
		new Thread(futureTask).start();
		log.info("do something in main");
		Thread.sleep(1000);
		String result = futureTask.get();
		log.info("result: {}", result);
	}
	
}
