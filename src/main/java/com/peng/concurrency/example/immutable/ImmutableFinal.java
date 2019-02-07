package com.peng.concurrency.example.immutable;

import java.util.Map;

import com.google.common.collect.Maps;
import com.peng.concurrency.annotations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

//不可变对象需要满足的条件
//	对象创建以后其状态就不能修改
//	对象所有域都是final类型
//	对象时正确创建的（在对象创建期间，this引用没有逸出）

//final关键字：类，方法，变量
//	修饰类：不能被继承
//	修饰方法：1.锁定方法不能被继承类修改（默认为private）;2.效率
//	修饰变量：基本数据类型变量，引用类型变量
@Slf4j
@NotThreadSafe
public class ImmutableFinal {
	private final static Integer a = 1;
	private final static String b = "2";
	//guava
	private final static Map<Integer, Integer> map = Maps.newHashMap();
	
	static {
		map.put(1, 2);
		map.put(3, 4);
		map.put(5, 6);
	}
	
	public static void main(String[] args) {
		
		//a = 2;
		//b = "3";
		//map = Maps.newHashMap();
		//map引用不能修改，但是内部的值还是可以进行修改~
		map.put(1, 3);
		log.info("{}",map.get(1));
	}
	
	//final参数不能修改
	private void test(final int a){
		// a = 1
	}
}
