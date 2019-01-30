package com.demo.functionalProgram.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Find {
	
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
//		Optional<Person> findAny = persons.stream().findAny();
		Optional<Person> findAny = persons.stream().findAny();
		Optional<Person> findAny1 = persons.stream().filter(x->x.getAge()<10).findAny();
		Optional<Person> findAny2 = persons.parallelStream().findAny();
		Optional<Person> findAny3 = persons.parallelStream().distinct().findAny();
		System.out.println("发现随机的一个："+findAny.get());
		System.out.println("发现随机的一个："+findAny1.get());
		System.out.println("发现随机的一个："+findAny2.get());
		System.out.println("发现随机的一个："+findAny3.get());
		Optional<Person> findFirst = persons.stream().findFirst();
		Optional<Person> findFirst1 = persons.stream().filter(x->x.getAge()<10).findFirst();
		Optional<Person> findFirst2 = persons.parallelStream().findFirst();
		Optional<Person> findFirst3 = persons.parallelStream().distinct().findFirst();
		System.out.println("发现随机的一个："+findFirst.get());
		System.out.println("发现随机的一个："+findFirst1.get());
		System.out.println("发现随机的一个："+findFirst2.get());
		System.out.println("发现随机的一个："+findFirst3.get());
		
		
		
		Optional<Person> max = persons.stream().max((x,y)->x.getAge()-y.getAge());
		Optional<Person> min = persons.stream().min((x,y)->x.getAge()-y.getAge());
		System.out.println(String.format("最小值：%s", min.get()));
		System.out.println(String.format("最大值：%s", max.get()));
	}

}
