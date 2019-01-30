package com.basic_knowledge.hash;

import java.util.HashMap;

public class Equal_Hash {
	public static void main(String[] args) {
		
		//hash不一样，直接放在不同key里面了，hash表放key是看hashcode的，如果不重写，那么equals写不写是没用的
		
		HashMap<A, Object> map = new HashMap<>();
		
		A a1 = new A("jack",11);
		A a2 = new A("jack",11);
		
		map.put(a1, "a1");
		map.put(a2, "a2");
		System.out.println(map);
	}
}

class A{
	private String name;
	private Integer age;
	A(String name,Integer age){
		this.name = name;
		this.age = age;
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((age == null) ? 0 : age.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		A other = (A) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "A [name=" + name + ", age=" + age + "]";
	}
	
}
