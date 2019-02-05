package com.peng.concurrency.example.publish;

import java.util.Arrays;

import com.peng.concurrency.annotations.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

//发布对象
//定义：使一个对象能够被当前范围之外的代码所使用
@Slf4j
@NotThreadSafe
public class UnsafePublish {
	private String[] states = {"a", "b", "c"};
	
	public String[] getStates(){
		return states;
	}
	
	public static void main(String[] args) {
		UnsafePublish unsafePublish = new UnsafePublish();
		log.info("{}", Arrays.toString(unsafePublish.getStates()));
		
		unsafePublish.getStates()[0] = "d";
		log.info("{}", Arrays.toString(unsafePublish.getStates()));
	}
}
