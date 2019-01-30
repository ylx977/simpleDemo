package com.demo.gof23.singleThreadExecution;

public class ToolWare {

	private String name;
	
	public ToolWare(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Tool:" + name;
	}
	
}
