package com.demo.functionalProgram;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSON;

import lombok.Builder;
import lombok.Data;

public class Stream1 {
	
	static List<Person> persons = new ArrayList<Person>();
	
	static{
		for (int i = 0; i < 50; i++) {
			persons.add(Person.builder().age(i).Name("jack"+i).build());
		}
	}

	public static void main(String[] args) {
		Stream<Person> stream = persons.stream();
		long count = stream.filter(new Predicate<Person>() {
			@Override
			public boolean test(Person t) {
				if(t.getAge()>20){
					return true;
				}
				return false;
			}
			
		}).count();
		System.out.println(count);
		
		Stream<Person> stream2 = persons.stream();
		Predicate<Person> p1 = person-> person.getAge() > 20;
		Predicate<Person> p2 = person-> person.getAge() < 40;
		long count2 = stream2.filter(p1.and(p2)).count();
		System.out.println(count2);
		
		
		
		
		boolean anyMatch = persons.stream().map(new Function<Person, Person>() {
			public Person apply(Person t) {
				t.setAge(11);
				return t;
			};
		}).anyMatch(person->person.getAge()>11);
		System.out.println(anyMatch);
		
		List<Integer> collect = Stream.of(Arrays.asList(1,2),Arrays.asList(3,4),Arrays.asList(1,2)).flatMap(x -> x.stream()).collect(Collectors.toList());
		System.out.println(collect);
		
		
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		Integer max = list.stream().max(Integer::compareTo).get();
		Integer min = list.stream().min(Integer::compareTo).get();
		System.out.println(max);
		System.out.println(min);
		
		
		List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		//max count min 本质都是 reduce
		Integer reduce = list1.stream().reduce(list1.get(0),(acc,element) -> acc > element ? element : acc);
		System.out.println(reduce);
		
		long count3 = list1.parallelStream().count();
		System.out.println(count3);
		
		
		List<String> collect2 = list1.stream().filter(x -> x > 10).map(x->String.valueOf(x)).collect(Collectors.toList());
		System.out.println(collect2);
		
//		list1.stream().forEach(x -> System.out.println(x));
		
		
		List<Integer> collect3 = list1.stream().skip(5).limit(5).collect(Collectors.toList());
		System.out.println(collect3);
		
		IntSummaryStatistics summaryStatistics = list1.stream().mapToInt(x->x).summaryStatistics();
		System.out.println(summaryStatistics.getAverage());
		
		PrintStream pr1 = System.out;
		PrintStream pr2 = System.out;
		System.out.println(pr1);
		System.out.println(pr2);
		
		List<Integer> list2 = Arrays.asList(21,22,23,24,25,26,27,28);
		List<Integer> collect4 = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());
		System.out.println(collect4);
		
		
		
//		List<Double> collect5 = Stream.generate(new Supplier<Double>() {
//			public Double get() {
//				return Math.random();
//			};
//		}).limit(5).collect(Collectors.toList());
		List<Double> collect5 = Stream.generate(Math::random).limit(5).collect(Collectors.toList());
		System.out.println(collect5);
		
		Integer collect6 = list1.stream().collect(Collectors.summingInt(Integer::intValue));
		System.out.println(collect6);

		Double collect7 = list1.stream().collect(Collectors.averagingInt(Integer::intValue));
		System.out.println(collect7);
		
		String collect8 = list1.stream().map(String::valueOf).collect(Collectors.joining(";"));
		System.out.println(collect8);
		
		Optional<Integer> collect9 = list1.stream().collect(Collectors.maxBy(Integer::compareTo));
		System.out.println(collect9.get());
		
		Optional<Integer> collect10 = list1.stream().collect(Collectors.minBy(Integer::compareTo));
		System.out.println(collect10.get());
		
//		Integer collect11 = list1.stream().collect(Collectors.reducing(0, x -> (int)(Math.pow(-1,x))*x, new BinaryOperator<Integer>() {
//			public Integer apply(Integer t, Integer u) {
//				return Integer.sum(t, u);
//			};
//		}));
		Integer collect11 = list1.stream().collect(Collectors.reducing(0, x -> (int)(Math.pow(-1,x))*x, Integer::sum));
		System.out.println(collect11);
		
		Map<String, List<Integer>> collect12 = list1.stream().collect(Collectors.groupingBy(String::valueOf));
		System.out.println(JSON.toJSONString(collect12));
		
		Map<Boolean, List<String>> collect13 = list1.stream().map(String::valueOf).collect(Collectors.partitioningBy(x->(Integer.valueOf(x))%2==0));
		System.out.println(JSON.toJSONString(collect13));
		
		Map<Boolean, Integer> collect14 = list1.stream().collect(Collectors.partitioningBy(MathUtil::isEven,Collectors.summingInt(Integer::intValue)));
		System.out.println(JSON.toJSONString(collect14));

		Map<Boolean, Double> collect15 = list1.stream().collect(Collectors.partitioningBy(MathUtil::isEven,Collectors.averagingInt(Integer::intValue)));
		System.out.println(JSON.toJSONString(collect15));
		
		Map<String, Double> collect16 = list1.stream().collect(Collectors.groupingBy(MathUtil::partition,Collectors.averagingInt(Integer::intValue)));
		System.out.println(JSON.toJSONString(collect16));

		Map<String, Integer> collect17 = list1.stream().collect(Collectors.groupingBy(MathUtil::partition,Collectors.summingInt(Integer::intValue)));
		System.out.println(JSON.toJSONString(collect17));
		
		//多级分组
		Map<String, Map<String, List<Integer>>> collect18 = list1.stream().collect(Collectors.groupingBy(MathUtil::partition,Collectors.groupingBy(MathUtil::isEven2)));
		System.out.println(JSON.toJSONString(collect18));
	}

}

@Data
@Builder
class Person{
	private String Name;
	private Integer age;
}

class Lambdatest2{
	public <T> void fortest(T t){
		System.out.println(t);
	}
}

class MathUtil{
	public static boolean isEven(Integer a){
		return a % 2 == 0;
	}
	public static String partition(Integer a){
		if(a%3==1){
			return "余1";
		}else if(a%3==2){
			return "余2";
		}else{
			return "余0";
		}
	}
	public static String isEven2(Integer a){
		if(a%2==0){
			return "偶数";
		}else{
			return "奇数";
		}
	}
	
	
}
