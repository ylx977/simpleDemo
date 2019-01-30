package com.demo.functionalProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Data;

public class Stream5 {
	static String[] cities = {"杭州","武汉","广州","东莞","北京","上海","海南","成都","长沙","桂林","哈尔滨","武汉","甘肃","青海","呼和浩特"};
	static List<People> lists = new ArrayList<>();
	static {
		for (int i = 0; i < 20; i++) {
			People p = People.builder().city(cities[(int)Math.floor(Math.random()*cities.length)]).height((int)(Math.random()*100)).build();
			lists.add(p);
		}
	}
	static List<People> lists2 = new ArrayList<>();
	static {
		for (int i = 0; i < 20; i++) {
			People p = People.builder().city(cities[(int)Math.floor(Math.random()*cities.length)]).height((int)(Math.random()*100)).build();
			lists2.add(p);
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(lists);
//		Optional<People> reduce = lists.stream().reduce((x,y)->{
//			if(x.getHeight()>=y.getHeight()){
//				return People.builder().city(x.getCity()).height(x.getHeight()).build();
//			}else{
//				return People.builder().city(y.getCity()).height(y.getHeight()).build();
//			}
//		});
//		System.out.println(reduce.get());
		
		
//		List<String> collect = lists.stream().map(x -> x.getCity()).distinct().collect(Collectors.toList());
//		System.out.println(collect);
		
		
//		//flatMap相当于把集合内部的元素（也是集合类型），转化成流，并合并成一个流
//		List<String> lis = Stream.of(lists,lists2).flatMap(x -> x.stream()).map(x->x.getCity()).distinct().collect(Collectors.toList());
//		System.out.println(lis);
		
		
		Map<String, Optional<People>> collect = lists.stream().collect(Collectors.groupingBy(People::getCity,Collectors.reducing((x,y)->{
			if(x.getHeight() > y.getHeight()){
				return x;
			}else{
				return y;
			}
		})));
		Map<String, List<People>> collect2 = collect.values().stream().map(Optional::get).collect(Collectors.groupingBy(People::getCity));
		System.out.println(collect2);
	}

}

@Builder
@Data
class People{
	private String city;
	private Integer height;
}