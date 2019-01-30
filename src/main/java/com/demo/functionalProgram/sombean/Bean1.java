package com.demo.functionalProgram.sombean;

import java.util.List;

import lombok.Data;

@Data
public class Bean1<T> {

	private String key;
	private List<T> accumulator;
	
}
