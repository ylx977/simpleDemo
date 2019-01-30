package com.demo.functionalProgram.collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.demo.functionalProgram.sombean.Bean1;

public class MyCollector implements Collector<Integer, List<Integer>, List<Bean1<Integer>>>{

	 /**
     * 用来创建并且返回一个可变结果容器
     */
	@Override
	public Supplier<List<Integer>> supplier() {
		return ArrayList::new;
	}

	/**
     * 将一个值叠进一个可变结果容器
     */
	@Override
	public BiConsumer<List<Integer>, Integer> accumulator() {
		return new BiConsumer<List<Integer>, Integer>() {
			@Override
			public void accept(List<Integer> t, Integer u) {
				t.add(u);
			}
		};
	}

	/**
     * 接受两个部分结果并将它们合并。可能是把一个参数叠进另一个参数并且返回另一个参数，
     * 也有可能返回一个新的结果容器，多线程处理时会用到
     */
	@Override
	public BinaryOperator<List<Integer>> combiner() {
		return new BinaryOperator<List<Integer>>() {
			@Override
			public List<Integer> apply(List<Integer> t, List<Integer> u) {
				t.addAll(u);
				return t;
			}
		};
	}

	 /**
     * 将中间类型执行最终的转换，转换成最终结果类型
     * 如果属性 IDENTITY_TRANSFORM 被设置，该方法会假定中间结果类型可以强制转成最终结果
     * 类型
     */
	@Override
	public Function<List<Integer>, List<Bean1<Integer>>> finisher() {
		return new Function<List<Integer>, List<Bean1<Integer>>>() {
			@Override
			public List<Bean1<Integer>> apply(List<Integer> t) {
				
				Map<String, List<Integer>> map = t.stream().collect(Collectors.groupingBy(x->String.valueOf(x%3)));
				
				Set<String> keySet = map.keySet();
				
				List<Bean1<Integer>> list = new ArrayList<>();
				
				for (String key : keySet) {
					Bean1<Integer> bean1 = new Bean1<Integer>();
					bean1.setKey(key);
					bean1.setAccumulator(map.get(key));
					list.add(bean1);
				}
				return list;		
			}		
		};
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
	}
	

}
