package com.demo.timeTest.java8time;

import java.time.LocalTime;
import java.util.LinkedList;

public class LocalTimeDemo {

	public static void main(String[] args) {
//		LocalTime localTime = LocalTime.MAX;
//		LocalTime localTime = LocalTime.MIDNIGHT;
//		LocalTime localTime = LocalTime.MIN;
		LocalTime localTime = LocalTime.now();
		System.out.println(localTime);
		System.out.println(localTime.getHour());
		System.out.println(localTime.getMinute());
		System.out.println(localTime.getSecond());
		System.out.println(localTime.getNano());
		
		//+1hour
		LocalTime plusHours = localTime.plusHours(1);
		System.out.println(plusHours);
		
		//+1min
		LocalTime plusMinutes = localTime.plusMinutes(1);
		System.out.println(plusMinutes);
		
		//+100seconds
		LocalTime plusSeconds = localTime.plusSeconds(100);
		System.out.println(plusSeconds);
		
		//由于localtime是不可变对象，所以是无法被修改的
		System.out.println(localTime);
		
		
	}
	
}
