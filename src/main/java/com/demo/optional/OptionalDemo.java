package com.demo.optional;

import java.util.Optional;

public class OptionalDemo {

	public static void main(String[] args) {
		Optional<String> ofOptional = Optional.of("张三");
		System.out.println(ofOptional.get());
		
//		Optional<String> ofOptional2 = Optional.of(null);//传入空会报错的
//		System.out.println(ofOptional2.get());
		
		
//		Optional<String> ofOptional3 = Optional.ofNullable(null);
		Optional<String> ofOptional3 = Optional.empty();
		System.out.println(ofOptional3.get());
	}

}
