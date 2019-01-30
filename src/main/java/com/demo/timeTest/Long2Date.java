package com.demo.timeTest;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class Long2Date {

	public static void main(String[] args) {
//		System.out.println(new Date(1525467600001L));
		System.out.println(new Date(1L*Integer.MAX_VALUE*1000));
//		ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
//		Double d = (Double) map.get("haha");
//		System.out.println(d);
	}

}
