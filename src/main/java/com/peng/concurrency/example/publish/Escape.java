package com.peng.concurrency.example.publish;

import com.peng.concurrency.annotations.NotRecommend;
import com.peng.concurrency.annotations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

//对象逸出
//定义：一种错误的发布。当一个对象还没有构建完成时，就使它被其他线程所见
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
	private int thisCanBeEscape = 0;
	
	public Escape (){
		new InnerClass();
	}
	
	private class InnerClass {
		public InnerClass(){
			//获取的数据是未创建完的对象数据
			log.info("{}", Escape.this.thisCanBeEscape);
		}
	}
	
	public static void main(String[] args) {
		new Escape();
	}
}
