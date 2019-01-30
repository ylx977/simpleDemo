package com.demo.timeTest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class TimeZoneTest {
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		
		
		String format = sdf.format(new Date());
		
		System.out.println(format);
		
		
		
		//上下两套时区方式都是可以的
		
		
//		String format = LocalDateTime.now(ZoneId.of("+8")).format(DateTimeFormatter.ofPattern("HH:mm"));
//		
//		System.out.println(format);
	}
	
}
