package com.basic_knowledge.enums;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

public enum Size {
	
	SMALL,MEDIUM,BIG;
	
	
	public static void main(String[] args) {
		Size size = Size.SMALL;
		System.out.println(size.toString());
		System.out.println(size.name());
		
		System.out.println(size==Size.SMALL);
		System.out.println(size.equals(Size.SMALL));
		System.out.println(size==Size.MEDIUM);
		
		//声明时候的顺序，从0开始
		System.out.println(size.ordinal());
		
		Size valueOf = Size.valueOf("SMALL");
		System.out.println(valueOf);
		
		Size[] values = Size.values();
		Arrays.sort(values);
		System.out.println(Arrays.toString(values));//枚举java.lang.Enum实现了comparable接口，比较的是ordinal()
		
		//switch比较的是每个类的ordinal()的值
		switch (size) {
			case SMALL : 
				System.out.println("small");
				break;
			case MEDIUM : 
				System.out.println("MEDIUM");
				break;
			case BIG : 
				System.out.println("BIG");
				break;
			default: break;
		}
		
		int[][] iss = {{1,2,3},{4,5,6},{7,8,9}};
		System.out.println(Arrays.deepToString(iss));
		
		
		System.out.println(Charset.defaultCharset());

		//多个资源用分号隔开try-with-resources
		try(AutoCloseable a = new FileInputStream("hello");AutoCloseable b = new FileInputStream("hello")){
			System.out.println(a);
			System.out.println(b);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
