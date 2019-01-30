package com.nio.bytebuffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class Demo {

	public static void main(String[] args) {
		//通过allocate()或者wrap()
		ByteBuffer allocate = ByteBuffer.allocate(100);
		int capacity = allocate.capacity();
		System.out.println(capacity);
		System.out.println(allocate.hasArray());
		
		byte[] bs = new byte[100];
		ByteBuffer wrap = ByteBuffer.wrap(bs);
		int capacity2 = wrap.capacity();
		System.out.println(capacity2);
		System.out.println(wrap.hasArray());
	}
	
}
