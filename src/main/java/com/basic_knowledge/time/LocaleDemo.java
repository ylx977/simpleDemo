package com.basic_knowledge.time;

import java.util.Locale;

public class LocaleDemo {
	
	public static void main(String[] args) {
		Locale default1 = Locale.getDefault();
		System.out.println(default1);
		
		Locale sc = Locale.SIMPLIFIED_CHINESE;
		System.out.println(sc);
		
		Locale c = Locale.CHINESE;
		System.out.println(c);
		
		Locale ca = Locale.CHINA;
		System.out.println(ca);
		
		Locale tw = Locale.TAIWAN;
		System.out.println(tw);
		
		
	}
	
}
