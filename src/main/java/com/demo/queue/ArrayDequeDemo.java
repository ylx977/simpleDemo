package com.demo.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeDemo {
	
	public static void main(String[] args) {
		Deque<String> queue = new ArrayDeque<String>();
		queue.push("1");
		queue.push("2");
		queue.push("3");
		queue.push("4");
		queue.push("5");
		
		System.out.println(queue);
		
		queue.pop();
		
		System.out.println(queue);
		
		queue.poll();
		
		System.out.println(queue);
		
		queue.pollLast();
		
		System.out.println(queue);
	}
	
}
