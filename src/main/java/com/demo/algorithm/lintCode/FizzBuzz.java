package com.demo.algorithm.lintCode;

import java.util.ArrayList;
import java.util.List;

/**
9. Fizz Buzz
Given number n. Print number from 1 to n. But:

when number is divided by 3, print "fizz".
when number is divided by 5, print "buzz".
when number is divided by both 3 and 5, print "fizz buzz".
Example
If n = 15, you should return:

[
  "1", "2", "fizz",
  "4", "buzz", "fizz",
  "7", "8", "fizz",
  "buzz", "11", "fizz",
  "13", "14", "fizz buzz"
]
Challenge
Can you do it with only one if statement?
 * @author fuzamei
 *
 */
public class FizzBuzz {
	
	/**
     * @param n: An integer
     * @return: A list of strings.
     */
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>(n);
        for(int i=1;i<=n;i++){
        	list.add(i%3==0 ? (i%5==0 ? "fizz buzz" : "fizz") : (i%5==0 ? "buzz" : String.valueOf(i)));
        }
        return list;
    }

	public static void main(String[] args) {
		FizzBuzz fizzBuzz = new FizzBuzz();
		List<String> fizzBuzz2 = fizzBuzz.fizzBuzz(10);
		System.out.println(fizzBuzz2);
	}

}
