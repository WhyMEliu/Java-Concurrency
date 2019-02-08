package com.peng.concurrency.example.threadlocal;


//线程封闭
//Ad-hoc线程封闭：程序控制，最糟糕，忽略
//堆栈封闭：局部变量

//ThreadLocal线程封闭：特别好的封闭方法
public class RequestHolder {
	
	private final static ThreadLocal<Long> requestHolder = new ThreadLocal<Long>();
	
	public static void add(Long id){
		requestHolder.set(id);
	}
	
	public static Long getId(){
		return requestHolder.get();
	}
	
	public static void remove(){
		requestHolder.remove();
	}
}
