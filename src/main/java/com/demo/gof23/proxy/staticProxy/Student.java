package com.demo.gof23.proxy.staticProxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Person {

	private String name;
	
	@Override
	public void giveMoney() {
		System.out.println(this.name + "上缴班费50元");
	}

}
