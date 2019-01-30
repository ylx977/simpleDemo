package com.demo.stringbuilder;

import com.demo.hashUtils.HashUtil;

public class Demo {
	public static void main(String[] args) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("1234567890");
//		System.out.println(sb);
//		
//		sb.replace(sb.length()-1, sb.length(), "]");
//		System.out.println(sb);
		
		long start = System.currentTimeMillis();
		String upperCase = HashUtil.getStringHashByType
				("app_id=7212949161166:app_key=NC5yaS8SHhOOlBZU0rMl:timestamp=2019-01-30 14:50:20:type=MD5", "MD5")
				.toUpperCase();
		
		System.out.println(upperCase);
		long end = System.currentTimeMillis();
		System.out.println(end-start+"ms");
	}
}
