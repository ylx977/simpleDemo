package com.demo.gof23.decorator;

public class Origin implements OriginalInterface{
	public Origin(){}
	
	@Override
	public void method(){
		System.out.println("original method!");
	}
	@Override
	public void method1(){
		System.out.println("original method1!");
	}
	@Override
	public void method2(){
		System.out.println("original method2!");
	}
	@Override
	public void method3(){
		System.out.println("original method3!");
	}
}
