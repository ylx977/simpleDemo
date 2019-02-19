package com.demo.timeTest.java8time;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

public class LocalDateDemo {

	public static void main(String[] args) {
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		System.out.println(localDate.getYear());
		System.out.println(localDate.getMonthValue());
		System.out.println(localDate.getMonth());
		System.out.println(localDate.getDayOfYear());
		System.out.println(localDate.getDayOfMonth());
		System.out.println(localDate.getDayOfWeek());
		
		//直接输入年月日创建LocalDate对象
		LocalDate birthday = LocalDate.of(1988, 9, 17);
		System.out.println(birthday);
		
		
		MonthDay monthDay = MonthDay.of(birthday.getMonthValue(), birthday.getDayOfMonth());
		
		MonthDay nowMonthday = MonthDay.from(localDate);
		
		if(nowMonthday.equals(monthDay)) {
			System.out.println("今天是你生日");
		}else {
			System.out.println("今天不是你的生日");
		}
		
		
	}
	
}
