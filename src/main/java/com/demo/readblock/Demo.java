package com.demo.readblock;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Demo {

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("C:\\Users\\fuzamei\\Desktop\\request2");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fis, Charset.forName("utf-8")));
		String str1 = null;
		StringBuilder sb = new StringBuilder();
		while((str1 = br1.readLine())!=null){
			sb.append(str1);
			sb.append("\r\n");
		}
		
		System.out.println(sb);
		br1.close();
	}
	
}
