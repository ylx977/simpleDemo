package com.basic_knowledge.time;

import java.time.ZoneId;
import java.util.TimeZone;

public class TimeZoneDemo {

	public static void main(String[] args) {
		
		String property1 = System.getProperty("user.timezone");
		String property2 = System.getProperty("user.country");
		String property3 = System.getProperty("java.home");
		System.out.println(property1);
		System.out.println(property2);
		System.out.println(property3);
		
		TimeZone default1 = TimeZone.getDefault();
		System.out.println(default1.getID());
		System.out.println(default1.getOffset(System.currentTimeMillis()));
		System.out.println(default1.getOffset(System.currentTimeMillis())/3600/1000);
		
		TimeZone timeZone8 = TimeZone.getTimeZone("GMT+08:00");
	}
	
}
