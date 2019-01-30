package com.demo.functionalProgram.stream;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class Reduce {

	public static void main(String[] args) {
		Stream<Integer> of = Stream.of(1,2,3,4,5,6,7,8,9,10);
		
		Optional<Integer> reduce = of.reduce(Integer::sum);
//		Optional<Integer> reduce = of.reduce(new BinaryOperator<Integer>() {
//			
//			@Override
//			public Integer apply(Integer t, Integer u) {
//				return t+u;
//			}
//		});
		System.out.println(reduce.get());
		
		
		Stream<Integer> of2 = Stream.of(1,2,3,4,5,6,7,8,9,10);
		Integer reduce2 = of2.reduce(100, new BinaryOperator<Integer>() {

			@Override
			public Integer apply(Integer t, Integer u) {
				return t+u;
			}
		});
		System.out.println(reduce2);
	}

}
