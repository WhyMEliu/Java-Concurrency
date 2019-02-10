package com.peng.concurrency.example.threadpool;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

//ScheduleThreadPool 使用
@Slf4j
public class ScheduleThreadPoolExample {
	
	public static void main(String[] args) {
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		
/*		executor.schedule(new Runnable(){
			@Override
			public void run() {
				log.warn("schedule run");
			}
		}, 3, TimeUnit.SECONDS);*/
		
		executor.scheduleAtFixedRate(new Runnable(){
			@Override
			public void run() {
				log.warn("schedule run");
			}
		}, 1, 3, TimeUnit.SECONDS);
		
		//定时器~一般不停
		//executor.shutdown();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				log.warn("timer run");
				
			}
		}, new Date(), 5 * 1000);
	}
}
