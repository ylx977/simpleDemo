package com.demo.functionalProgram.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class Reverse {
	
	@Data
	@AllArgsConstructor
	public class A{
		private Integer age;
	}
	
	public static void main(String[] args) {
		Reverse.A a1 = new Reverse().new A(11);
		Reverse.A a2 = new Reverse().new A(12);
		Reverse.A a3 = new Reverse().new A(13);
		List<A> as = new ArrayList<>();
		as.add(a1);
		as.add(a2);
		as.add(a3);
		List<A> collect = as.stream().sorted(Comparator.comparingInt(A::getAge)).collect(Collectors.toList());
		System.out.println(collect);
	}
	
	
}


