package com.demo.functionalProgram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Stream4 {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>(){
			{
				this.put("1", "100001");
				this.put("2", "100002");
				this.put("3", "100003");
				this.put("4", "100004");
				this.put("5", "100005");
				this.put("6", "100006");
				this.put("7", "100007");
				this.put("8", "100008");
				this.put("9", "100009");
				this.put("10", "100010");
				this.put("11", "100011");
				this.put("12", "100012");
				this.put("13", "100013");
			}
		};
		
		List<Integer> collect = map.values().stream().map(Integer::parseInt).collect(Collectors.toList());
		List<String> collect2 = map.values().stream().map(String::valueOf).collect(Collectors.toList());
		System.out.println(collect);
		System.out.println(collect2);
		
		
	}

}
