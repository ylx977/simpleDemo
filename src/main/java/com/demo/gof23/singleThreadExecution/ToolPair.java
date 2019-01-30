package com.demo.gof23.singleThreadExecution;

public class ToolPair {

	private final ToolWare left;
	private final ToolWare right;
	
	public ToolPair(ToolWare left,ToolWare right){
		this.left = left;
		this.right = right;
	}
	
	public ToolWare getLeft(){
		return this.left;
	}
	public ToolWare getRight(){
		return this.right;
	}
	
}
