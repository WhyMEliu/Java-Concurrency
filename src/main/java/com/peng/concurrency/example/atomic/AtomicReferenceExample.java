package com.peng.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicReference;

import com.peng.concurrency.annotations.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * AtomicReference
 * 基本用法介绍 ：用于类的判断
 * @author 52745
 */

@Slf4j
@ThreadSafe
public class AtomicReferenceExample {
	
	private static AtomicReference<Integer> count = new AtomicReference<>(0);
	
	public static void main(String[] args) {
		count.compareAndSet(0, 2);// 2
		count.compareAndSet(0, 1);// no
		count.compareAndSet(1, 3);// no
		count.compareAndSet(2, 4);// 4
		count.compareAndSet(3, 5);// no
		
		log.info("count:{}",count.get());
	}
}
