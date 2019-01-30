package com.demo.functionalProgram.collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

import com.demo.functionalProgram.sombean.Bean1;
import com.demo.functionalProgram.sombean.Bean2;

public class MyCollector2 implements Collector<Integer, Map<Integer,List<Integer>>, List<Bean2<Integer>>>{

	@Override
	public Supplier<Map<Integer,List<Integer>>> supplier() {
		return HashMap::new;
	}

	private void pack(Map<Integer,List<Integer>> map, Integer element){
		Integer key = element%3;
		if(map.containsKey(key)){
			List<Integer> list = map.get(key);
			list.add(element);
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(element);
			map.put(key, list);
		}
	}
	
	@Override
	public BiConsumer<Map<Integer,List<Integer>>, Integer> accumulator() {
		return (map,element)->{
			if(element%3==0){
				pack(map,element);
			}else if(element%3==1){
				pack(map,element);
			}else{
				pack(map,element);
			}
		};
	}

	@Override
	public BinaryOperator<Map<Integer,List<Integer>>> combiner() {
		return (map1,map2)->{
			Set<Integer> keySet = map1.keySet();
			Set<Integer> keySet2 = map2.keySet();
			keySet.addAll(keySet2);
			for(Integer key : keySet){
				List<Integer> list1 = map1.get(key);
				List<Integer> list2 = map2.get(key);
				if(list1==null && list2!=null){
					map1.put(key, list2);
				}else if(list1!=null && list2==null){
					map1.put(key, list1);
				}else{
					list1.addAll(list2);
					map1.put(key, list1);
				}
			}
			return map1;
		};
	}

	@Override
	public Function<Map<Integer,List<Integer>>, List<Bean2<Integer>>> finisher() {
		return map->{
			
			Set<Integer> keySet = map.keySet();
			List<Bean2<Integer>> list = new ArrayList();
			for(Integer key : keySet){
				Bean2 bean = new Bean2();
				bean.setKey(key);
				bean.setAccumulator(map.get(key));
				list.add(bean);
			}
			return list;
		};
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
	}


}
