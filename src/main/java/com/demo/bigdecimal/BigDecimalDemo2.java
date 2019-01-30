package com.demo.bigdecimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BigDecimalDemo2 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
//		double a = 0.555d;
//		double b = 0.123456;
//		double result = a/b;
//		BigDecimal d = new BigDecimal(String.valueOf(result));
//		BigDecimal setScale = d.setScale(2, RoundingMode.HALF_UP);
//		System.out.println(d);
//		System.out.println(setScale);
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update("123".getBytes());
		byte[] digest = messageDigest.digest();
		BigInteger bigInt = new BigInteger(1, digest);  
        System.out.println(bigInt.toString(16));//转换成16进制
	}
	
}
