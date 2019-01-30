package com.demo.futureTask.selfDesign;
@FunctionalInterface
public interface Task<IN,OUT> {

	//给定一个参数，计算后返回结果
	OUT get(IN input);
	
}
