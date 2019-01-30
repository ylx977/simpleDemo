package com.demo.gof23.proxy.dynamicProxy;

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
		try {
	          //假设数钱花了一秒时间
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	       System.out.println(this.name + "上交班费50元");
	}

}
