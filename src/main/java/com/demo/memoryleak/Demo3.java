package com.demo.memoryleak;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Demo3 {
	
	public static void main(String[] args) throws InterruptedException {
		String name = "name";
		Integer age = 10;
		int i = 100;
		double d = 100d;
		A a = new A(age, name,i,d);
		name = null;
		age = null;
		i = 0;
		d = 0d;
		//就算置空，对象本身还能获取引用
		System.out.println(a.getName());
		System.out.println(a.getAge());
		System.out.println(a.getId());
		System.out.println(a.getId2());
		//停100秒
		Thread.sleep(100000);
	}
	
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class A{
	
	private Integer age;
	private String name;
	private int id;
	private double id2;
	
}
