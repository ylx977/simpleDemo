package com.demo.runtime;

import lombok.Data;

public class RuntimeDemo3 {

	public static void main(String[] args) {
		TTT t1 = new TTT();
		t1.setAge(100);
		t1.setName("t1");

		TTT t2 = new TTT();
		t2.setAge(200);
		t2.setName("t2");
		
		t1 = t2;
		
		System.out.println(t1);
	}

}

@Data
class TTT{
	private String name;
	private Integer age;
}
