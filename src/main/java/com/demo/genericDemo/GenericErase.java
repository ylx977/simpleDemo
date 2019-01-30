package com.demo.genericDemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericErase {

	public static void main(String[] args) {
		List<String> list1 = new ArrayList<>();
		list1.add("hahah");
		//泛型被擦除了
		List list2 = list1;
		list2.add(new Date());
		list2.add(new Date());
		System.out.println(list2);
	}

}
