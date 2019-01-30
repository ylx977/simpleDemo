package com.demo.functionalProgram.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Skip {
	
	
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
		List<Person> collect = persons.stream().skip(3).collect(Collectors.toList());
		System.out.println(collect);
	}

}
