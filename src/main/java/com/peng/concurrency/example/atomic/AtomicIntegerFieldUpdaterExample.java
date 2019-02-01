package com.peng.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import com.peng.concurrency.annotations.ThreadSafe;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * AtomicIntegerFieldUpdater
 * 用法介绍：一个类中的数据保证原子性时使用
 * @author peng
 */

@Slf4j
@ThreadSafe
public class AtomicIntegerFieldUpdaterExample {
	
	private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterExample> updater =
			AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterExample.class, "count");
	
	//必须要是volatile,非静态
	@Getter
	public volatile int count = 100;
	
	public static void main(String[] args) {
		AtomicIntegerFieldUpdaterExample example = new AtomicIntegerFieldUpdaterExample();
		
		if(updater.compareAndSet(example, 100, 120)){
			log.info("update success 1, {}", example.getCount());
		}
		
		if(updater.compareAndSet(example, 100, 120)){
			log.info("update success 2, {}", example.getCount());
		}else{
			log.info("update failed, {}",example.getCount());
		}
	}
}
