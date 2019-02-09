package com.peng.concurrency.example.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

//Condition 使用
@Slf4j
public class ConditionExample {
	public static void main(String[] args) {
		ReentrantLock reentrantLock = new ReentrantLock();
		Condition condition = reentrantLock.newCondition();
		
		new Thread(() -> {
			try{
				reentrantLock.lock();
				log.info("wait signal");// 1
				condition.await();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			log.info("get signal");// 4
			reentrantLock.unlock();
		}).start();
		
		new Thread(() -> {
			reentrantLock.lock();
			log.info("get Lock");// 2
			try{
				Thread.sleep(3000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			condition.signalAll();
			log.info("send signal ~");// 3
			reentrantLock.unlock();
		}).start();
		
	}
}
