package com.demo.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalDemo3 {

	public static void main(String[] args) {
		BigDecimal big = new BigDecimal("1.2345600001");
		
		BigDecimal setScale = big.setScale(2, RoundingMode.HALF_UP);
		
		String string = setScale.toString();
		
		System.out.println(string);
	}
	
}
