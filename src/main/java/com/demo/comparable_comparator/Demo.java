package com.demo.comparable_comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Demo {
	
	/**
	 * comparable和comparator都是接口
	 * 
	 * 实现comparable接口的类都能进行排序，Arrays.sort或者Collections.sort是采用实现的排序规则排序的
	 * 
	 * 而comparator接口是在原来的comparable接口之外，提供的外置排序规则，也就是当不想采用默认排序规则的时候就要手动实现comparable接口去帮你自定义自己的排序规则
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		A a1 = new A(10,100,"a1");
		A a2 = new A(6,200,"a2");
		A a3 = new A(12,20,"a3");
		A a4 = new A(3,70,"a4");
		A a5 = new A(20,600,"a5");
		
		List<A> list = new ArrayList<A>();
		list.add(a1);
		list.add(a2);
		list.add(a3);
		list.add(a4);
		list.add(a5);
		System.out.println(list);
//		Collections.sort(list);
		
		Collections.sort(list, (x,y) -> x.getSize()-y.getSize());
		
		System.out.println(list);
	}
	
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class A implements Comparable<A>{

	private Integer age;
	private Integer size;
	private String name;
	
	@Override
	public int compareTo(A o) {
		return this.getAge() - o.getAge();
	}
	
}
