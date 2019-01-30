package com.basic_knowledge.override_overload;

import java.io.IOException;

import com.google.protobuf.TextFormat.ParseException;

class Demo1 {
	
	protected void test() throws IOException{
		System.out.println("father print!");
	}
	
}
//（Overload）重载：发生在同一个类之中，方法名相同、参数列表不同，与返回值无关、与final无关、与修饰符无关、与异常无关。这里SonDemo1的test就是重载方法

public class SonDemo1 extends Demo1{
	
	@Override
	public void test() throws ParseException {
		//重写的方法的异常只能比父类的小，或者没有也是可以的
		System.out.println("son print!");
	}
	
	public String test(String name){
		return name;
	}
	
	private String test(String name,String name2){
		return name+name2;
	}
	
	public static void main(String[] args) throws ParseException {
		SonDemo1 s = new SonDemo1();
		s.test();
	}
}
