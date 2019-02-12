package com.peng.concurrency.example.ratelimiter;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

import lombok.extern.slf4j.Slf4j;

//应用限流：GuavaRateLimiter(令牌桶算法)
@Slf4j
public class RateLimiterExample{
	
	private static RateLimiter rateLimiter = RateLimiter.create(5);
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 100; i++) {
			if(rateLimiter.tryAcquire(210, TimeUnit.MILLISECONDS)){
				handle(i);
			}
		}
	}
	
	private static void handle(int i){
		log.info("{}", i);
	}
}
