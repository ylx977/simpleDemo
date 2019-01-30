package com.demo.algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SumDemo3 {
	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++) {
			BigDecimal result = new BigDecimal(1d/i+1d/(i+1)+1d/(i+2)+1d/(i+3));
			if(new BigDecimal(19d/20).setScale(8, RoundingMode.HALF_EVEN).toString().equals(result.setScale(8, RoundingMode.HALF_EVEN).toString())){
				System.out.println(i);
			}
		}
	}
}
