package com.demo.timeTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeTest {
	public static void main(String[] args) throws IOException {
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println(currentTimeMillis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));//simpleDateFormat设置了时区才有效
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeZone(TimeZone.getTimeZone("GMT-1:00"));
		String format = sdf.format(new Date(currentTimeMillis));
//		String format = sdf.format(calendar.getTime());
		System.out.println(format);
		
		
		
		
	}
}
