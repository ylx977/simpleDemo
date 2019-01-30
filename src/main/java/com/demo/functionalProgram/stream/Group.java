package com.demo.functionalProgram.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class Group {
	
	@Data
	@AllArgsConstructor
	public class A{
		private Integer id;
		private Integer age;
		private String name;
	}
	
	public static void main(String[] args) {
		Group.A a1 = new Group().new A(1,11,"张三");
		Group.A a2 = new Group().new A(2,12,"李四");
		Group.A a3 = new Group().new A(3,15,"王五");
		Group.A a4 = new Group().new A(4,16,"赵六");
//		Group.A a41 = new Group().new A(4,17,"赵六2");
		Group.A a5 = new Group().new A(5,15,"孙琦");
		List<A> as = new ArrayList<>();
		as.add(a1);
		as.add(a2);
		as.add(a3);
		as.add(a4);
//		as.add(a41);
		as.add(a5);
		//如果是Collectors.toMap，key必须唯一
		Map<Integer, String> collect = as.stream().collect(Collectors.toMap(A::getId, A::getName));
		System.out.println(collect);
		
		//根据id对A对象中的name进行分组
		Map<Integer, List<String>> collect2 = as.stream().collect(Collectors.groupingBy(A::getId, Collectors.mapping(A::getName, Collectors.toList())));
		System.out.println(collect2);
		
		//相当于as.stream.map(A::getId).collect(Collectors.toList());
		List<Integer> collect3 = as.stream().collect(Collectors.mapping(A::getId, Collectors.toList()));
		System.out.println(collect3);
		
		//收集数据的和
		Integer collect4 = as.stream().collect(Collectors.summingInt(A::getAge));
		System.out.println(collect4);
		
	}
	
	
}


