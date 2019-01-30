package com.demo.functionalProgram.stream;

import java.util.ArrayList;
import java.util.List;

public class ForEach {
	
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
		persons.stream().forEachOrdered(x->System.out.println(x));//串行处理
		persons.stream().forEach(x->System.out.println(x));//并行处理(效率更高)
	}

}
