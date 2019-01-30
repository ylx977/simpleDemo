package com.demo.functionalProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSON;
import com.demo.functionalProgram.Person.PersonBuilder;
import com.demo.functionalProgram.collector.MyCollector;
import com.demo.functionalProgram.collector.MyCollector2;
import com.demo.functionalProgram.sombean.Bean1;
import com.demo.functionalProgram.sombean.Bean2;

import lombok.Builder;
import lombok.Data;

public class Stream2 {

	public static void main(String[] args) {
		Stream<Object> build = Stream.builder().add(Person2.builder().age(11).Name("jack").build()).add(Person2.builder().age(12).Name("jack2").build()).build();
		Collector<Object, ?, List<Object>> list = Collectors.toList();
		List<Object> collect = build.collect(list);
		System.out.println(collect);
		
		
		boolean anyMatch = Stream.of(Person2.builder().age(11).Name("jack").build()).anyMatch(person -> person.getAge() > 10);
		System.out.println(anyMatch);
		
		List<Person2> personList = new ArrayList<>();
		for(int i = 0; i < 20; i++){
			personList.add(Person2.builder().age(i).Name("jack"+i).build());
		}
		Map<Integer, List<Person2>> collect2 = personList.stream().collect(Collectors.groupingBy(x->x.getAge()%3,Collectors.toList()));
		System.out.println(JSON.toJSONString(collect2));
		
		
		List<Integer> listx = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		List<Bean1<Integer>> collect3 = listx.stream().collect(new MyCollector());
		System.out.println(JSON.toJSONString(collect3));
		
		List<Object> collect4 = listx.stream().map(String::valueOf).collect(ArrayList::new, List::add, List::addAll);
		System.out.println(collect4);
		
		List<Bean2<Integer>> collect5 = listx.stream().collect(new MyCollector2());
		System.out.println(JSON.toJSONString(collect5));
	}

}

@Data
@Builder
class Person2{
	private String Name;
	private Integer age;
}
