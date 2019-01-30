package com.demo.timeTest;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeTest2 {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		System.out.println(year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second);//calendar要这样获取设置timezone才有效
		
		System.out.println(calendar.getTime());//这样获取的时间 timezone是不起作用的
	}
}
