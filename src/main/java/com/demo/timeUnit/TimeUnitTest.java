package com.demo.timeUnit;

import java.util.concurrent.TimeUnit;

public class TimeUnitTest {

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		long convert = TimeUnit.HOURS.convert(time, TimeUnit.MILLISECONDS);
		System.out.println(convert);
		System.out.println(convert/365);
		System.out.println(convert/365/24);
	}

}
