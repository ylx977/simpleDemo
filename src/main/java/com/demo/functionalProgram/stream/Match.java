package com.demo.functionalProgram.stream;

import java.util.ArrayList;
import java.util.List;

public class Match {

static List<Person> persons = new ArrayList<Person>();
	
	static{
		persons.add(Person.builder().age(2).Name("jack").build());
		persons.add(Person.builder().age(7).Name("jack2").build());
		persons.add(Person.builder().age(5).Name("jack2").build());
		persons.add(Person.builder().age(3).Name("jack3").build());
		persons.add(Person.builder().age(10).Name("jack4").build());
		persons.add(Person.builder().age(20).Name("jack5").build());
		persons.add(Person.builder().age(12).Name("jack6").build());
		persons.add(Person.builder().age(13).Name("jack7").build());
		persons.add(Person.builder().age(13).Name("jack7").build());
	}
	
	public static void main(String[] args) {
		boolean allMatch = persons.stream().allMatch(x->x.getAge()>10);//是否全部满足条件
		System.out.println("allMatch:"+allMatch);
		boolean anyMatch = persons.stream().anyMatch(x->x.getAge()>10);//是否至少有一个满足条件
		System.out.println("anyMatch:"+anyMatch);
		boolean noneMatch = persons.stream().noneMatch(x->x.getAge()>10);//是否全部不符合条件
		System.out.println("noneMatch:"+noneMatch);
		
		
		long count = persons.stream().count();
		System.out.println("计算了个数为:"+count);
		
		long count2 = persons.stream().distinct().count();
		System.out.println("去重后计算的个数为:"+count2);
		
	}
	
}
