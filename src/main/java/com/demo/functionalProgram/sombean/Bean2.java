package com.demo.functionalProgram.sombean;

import java.util.List;

import lombok.Data;

@Data
public class Bean2<T> {

	private T key;
	private List<T> accumulator;
	
}
