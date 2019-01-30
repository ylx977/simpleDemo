package com.demo.functionalProgram.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlatMap {
	
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
//		List<Integer> collect = persons.stream().flatMap(new Function<Person, Stream<? extends Integer>>() {
//			@Override
//			public Stream<? extends Integer> apply(Person t) {
//				
//				return Stream.of(t.getAge());
//			}
//		}).collect(Collectors.toList());
		List<Integer> collect = persons.stream().flatMap(x->Stream.of(x.getAge())).collect(Collectors.toList());
		System.out.println(collect);
		
		List<String> strList = Arrays.asList("hello","my","name","is","yang","lingxiao");
		
		Stream<String[]> map = strList.stream().map(x->x.split(""));
		List<String> collect2 = map.flatMap(x->Arrays.stream(x)).collect(Collectors.toList());
		System.out.println(collect2);//flatmap可以继续将流打碎
		
		int sum = strList.stream().flatMapToInt(x->IntStream.of(x.length())).sum();
		System.out.println(sum);
	}

}
