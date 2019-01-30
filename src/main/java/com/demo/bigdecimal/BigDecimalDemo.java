package com.demo.bigdecimal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalDemo {

	public static void main(String[] args) {
//		BigDecimal bigDecimal = new BigDecimal("10.2376");
//		BigDecimal bigDecimal = new BigDecimal("10.2376");
//		System.out.println(29.0*0.01);
//		System.out.println(29.0*0.01*100);
		String multiply = multiply("0.29823671","100",9,RoundingMode.HALF_UP);
		System.out.println(multiply);
		System.out.println(Double.parseDouble(multiply));
	}
	public static final String multiply(String x, String y, int scale, RoundingMode strategy){
		BigDecimal xDecimal = new BigDecimal(x);
		BigDecimal yDecimal = new BigDecimal(y);
		BigDecimal one = new BigDecimal("1");
		BigDecimal multiply = xDecimal.multiply(yDecimal);
		BigDecimal divide = multiply.divide(one, scale, strategy);
		return divide.toString();
	}
	
	public static final String add(String x, String y, int scale, RoundingMode strategy){
		BigDecimal xDecimal = new BigDecimal(x);
		BigDecimal yDecimal = new BigDecimal(y);
		BigDecimal one = new BigDecimal("1");
		BigDecimal multiply = xDecimal.add(yDecimal);
		BigDecimal divide = multiply.divide(one, scale, strategy);
		return divide.toString();
	}
	public static final String subtract(String x, String y, int scale, RoundingMode strategy){
		BigDecimal xDecimal = new BigDecimal(x);
		BigDecimal yDecimal = new BigDecimal(y);
		BigDecimal one = new BigDecimal("1");
		BigDecimal multiply = xDecimal.subtract(yDecimal);
		BigDecimal divide = multiply.divide(one, scale, strategy);
		return divide.toString();
	}
	public static final String divide(String x, String y, int scale, RoundingMode strategy){
		BigDecimal xDecimal = new BigDecimal(x);
		BigDecimal yDecimal = new BigDecimal(y);
		BigDecimal multiply = xDecimal.divide(yDecimal,scale, strategy);
		return multiply.toString();
	}

}
