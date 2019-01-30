package com.demo.gof23.singleThreadExecution;

public class EatNoodleThread extends Thread {

	private final String name;
	
//	private final ToolWare leftWare;
//	private final ToolWare rightWare;
//	
//	public EatNoodleThread(String name,ToolWare left,ToolWare right) {
//		this.name = name;
//		this.leftWare = left;
//		this.rightWare = right;
//	}
	
	private final ToolPair toolPair;
	
	public EatNoodleThread(String name,ToolPair toolPair) {
		this.name = name;
		this.toolPair = toolPair;
	}
	
	@Override
	public void run() {
		while(true){
			synchronized (toolPair.getLeft()) {
				System.out.println(name + "take up" + toolPair.getLeft() + "left");
				synchronized (toolPair.getRight()) {
					System.out.println(name + "take up" + toolPair.getRight() +"right");
					System.out.println(name + "is eating now");
					System.out.println(name + "put down" + toolPair.getRight() +"right");
				}
				System.out.println(name + "put down" + toolPair.getLeft() +"left");
			}
		}
	}
	
	public static void main(String[] args) {
		ToolWare fork = new ToolWare("fork");
		ToolWare knife = new ToolWare("knife");
		ToolPair toolPair = new ToolPair(fork, knife);
		new EatNoodleThread("A", toolPair).start();
		new EatNoodleThread("B", toolPair).start();
	}
	
}
