package com.basic_knowledge.hash;

import java.util.HashMap;
import java.util.HashSet;

public class Equal_Hash2 {
	public static void main(String[] args) {
		//hash存key先看hashcode的，如果hashcode不一样，那没问题
		//但是如果hashcode一样，但是equal不一样，那么hash表还是会存入相应的key值
		HashSet<Object> set = new HashSet<>();
		set.add(new A1());
		set.add(new A1());
		set.add(new B1());
		set.add(new B1());
		set.add(new C1());
		set.add(new C1());
		set.add(new D1());
		set.add(new D1());
		System.out.println(set);
	}
}

class A1{
	@Override
	public boolean equals(Object obj) {
		return true;
	}
	@Override
	public String toString() {
		return "A1";
	}
}
class B1{
	@Override
	public int hashCode() {
		return 1;
	}
	@Override
	public String toString() {
		return "B1";
	}
}
class C1{
	@Override
	public boolean equals(Object obj) {
		return true;
	}
	@Override
	public int hashCode() {
		return 2;
	}
	@Override
	public String toString() {
		return "C1";
	}
}
class D1{
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	@Override
	public int hashCode() {
		return 2;
	}
	@Override
	public String toString() {
		return "D1";
	}
}
