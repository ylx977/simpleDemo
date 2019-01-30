package com.demo.commonTest;

import lombok.Data;

public class PartialTest {

	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.setHeight("89");
		System.out.println(demo);
		test(demo);
		System.out.println(demo);
	}
	
	public static void test(Demo demo){
		demo.setAge("11");
		demo.setName("jack");
	}
	
	
	@Data
	public static class Demo{
		private String name;
		private String age;
		private String height;
	}

}
