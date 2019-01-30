package com.demo.functionalProgram;

import org.apache.commons.math3.primes.Primes;

import com.demo.functionalProgram.interfaces.Face1;
import com.demo.functionalProgram.interfaces.Face2;
import com.demo.functionalProgram.interfaces.Face3;
import com.demo.functionalProgram.interfaces.Face4;

public class Lambda1 {
	
	public static void main(String[] args) {
		
		Integer integer = 3;
		new Thread(()->{
			Long start = System.currentTimeMillis();
			System.out.println("haha");
			Long end = System.currentTimeMillis();
			System.out.println(end-start);
			}).start();
		
		Runnable runnable = ()->System.out.println("haha");
		
		Face1 f1 = ()->System.out.println("face1");
		f1.test();
		
		Face2 f2 = (Long a,Long b) -> System.out.println(a+b);
		f2.test(1L, 2L);
		
		Face2 f3 = (a,b) -> {
			System.out.println(a+b);
			//这里的integer在lambda表达式可以不用声明为final，但是如果改变引用类型就会编译报错
			System.out.println(integer+4);};
		f2.test(1L, 2L);
		
		Face3 f4 = (a,b)-> a > b;
		System.out.println(f4.test(1L, 3L));
		
		Face4 f5 = Math::abs;
		Integer test = f5.test(-5);
		System.out.println(test);
		
		Lambdatest l = new Lambdatest();
		Face4 f6 = l::fortest;
		System.out.println(f6.test(10));
		
		Face4 f7 = Integer::new;
		System.out.println(f7.test(10));
		
		
	}
	
}

class Lambdatest{
	public Integer fortest(Integer x){
		return x+1;
	}
}

