package com.demo.stringDemo;

import java.io.UnsupportedEncodingException;

public class StringDemo {
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(new String("鍙傛暟闈炴硶".getBytes(),"gbk"));
	}
}
