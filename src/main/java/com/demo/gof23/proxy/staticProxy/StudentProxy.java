package com.demo.gof23.proxy.staticProxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProxy implements Person {

	private Person student;
	
	@Override
	public void giveMoney() {
		System.out.println("张三最近有进步哦");
		this.student.giveMoney();
	}

}
