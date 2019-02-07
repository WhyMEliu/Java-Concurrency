package com.peng.concurrency.example.immutable;

import java.util.Collections;
import java.util.Map;

import com.google.common.collect.Maps;
import com.peng.concurrency.annotations.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

//Map内部可修改解决办法
@Slf4j
@ThreadSafe
public class ImmutableMap {
	private static Map<Integer, Integer> map = Maps.newHashMap();
	
	static{
		map.put(1, 2);
		map.put(3, 4);
		map.put(5, 6);
		map = Collections.unmodifiableMap(map);
	}
	
	public static void main(String[] args) {
		//会报错哈~UnsupportedOperationException
		map.put(1, 3);
		log.info("{}", map.get(1));
	}
}
