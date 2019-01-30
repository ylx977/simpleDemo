package com.demo.stringDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

public class StringDemo2 {

	public static void main(String[] args) {
//		String str = "dshd dsa   sdha   sdh     sdha  sadhau    sadhua";
//		String[] split = str.split("[' ']+");
//		System.out.println(Arrays.toString(split));
//		List<String> final1 = getFinal();
//		String string = final1.get(0);
//		string = new String("haha");
//		System.out.println(final1);
		A[] final1 = getFinal();
//		这个引用a其实不是真正的那个引用，只是一个副本的引用
		A a = final1[0];
		A CHANGE = new A();
		CHANGE.setName("change");
		a = CHANGE;
		System.out.println(Arrays.toString(final1));
	}
	
	public static A[] getFinal(){
		final A shit1 = new A();
		shit1.setName("shit1");
		final A shit2 = new A();
		shit2.setName("shit2");
		final A shit3 = new A();
		shit3.setName("shit3");
		final A shit4 = new A();
		shit4.setName("shit4");
		A[] list = new A[]{shit1,shit2,shit3,shit4};
//		这个引用a其实不是真正的那个引用，只是一个副本的引用
		A a = list[0];
		A CHANGE = new A();
		CHANGE.setName("change");
		a = CHANGE;
//		List<A> list = new ArrayList<>();
//		list.add(shit1);
//		list.add(shit2);
//		list.add(shit3);
//		list.add(shit4);
		return list;
	}

}
@Data
class A{
	private String name;
}
