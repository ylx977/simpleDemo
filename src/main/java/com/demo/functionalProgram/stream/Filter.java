package com.demo.functionalProgram.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Data;


public class Filter {

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
	}

	public static void main(String[] args) {
//		long count = persons.stream().filter(x->x.getAge()<10 && x.getAge()>3).count();
//		long count = persons.stream().filter(x->x.getAge()<10 && x.getAge()>3).count();
//		System.out.println(count);
		
		System.out.println(persons);
		List<Person> collect = persons.stream().sorted((x,y)->x.getAge()-y.getAge()).collect(Collectors.toList());
		System.out.println(collect);
		System.out.println(persons);
		
		List<Person> collect2 = persons.stream().limit(3).collect(Collectors.toList());
		System.out.println(collect2);
		
		Random random = new Random();
		IntStream ints = random.ints();
		List<Integer> collect3 = ints.limit(3).collect(new Supplier<List<Integer>>() {
			public List<Integer> get() {
				return new ArrayList<>();
			};
		},new ObjIntConsumer<List<Integer>>() {
			public void accept(List<Integer> t, int value) {
				t.add(value);
			};
		},new BiConsumer<List<Integer>, List<Integer>>() {
			@Override
			public void accept(List<Integer> t, List<Integer> u) {
				t.addAll(u);
			}
		});
		System.out.println(collect3);
		
		List<String> list = Arrays.asList(new String[]{"1","2","3"});
		String collect4 = list.stream().collect(Collectors.joining(";"));//这个只针对字符串的
		System.out.println(collect4);
	}
	
}


@Data
@Builder
class Person{
	private String Name;
	private Integer age;
}
