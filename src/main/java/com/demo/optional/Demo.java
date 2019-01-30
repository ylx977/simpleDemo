package com.demo.optional;

import lombok.Data;

public class Demo {
	
	public static void main(String[] args) {
		TestDemo t = new TestDemo(){
			{
				System.out.println("内部1x");
				this.setName("jack2");
				this.setAge(200);
				System.out.println("内部2x");
			}
		};
		System.out.println(t);
		TestDemo t2 = new TestDemo();
		System.out.println(t2);
	}
	
	
}

@Data
class TestDemo {
	
	
	
	TestDemo(){
		try {
			System.out.println("构造方法");
			return;
		} finally {
			System.out.println("构造方法最后执行的");
		}
	}
	private String name;
	private Integer age;
	
	{
		System.out.println("内部1");
		this.setName("jack");
		this.setAge(20);
		System.out.println("内部2");
	}
}
