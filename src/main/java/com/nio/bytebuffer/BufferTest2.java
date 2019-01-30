package com.nio.bytebuffer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

public class BufferTest2 {
	
	public static void main(String[] args) {
		ByteBuffer allocate = ByteBuffer.allocate(20);
		ByteBuffer put = allocate.put(new byte[]{1,2,3,4});
		System.out.println(put);
		System.out.println(put.hasRemaining());
		System.out.println("flip之后");
		put.flip();
		System.out.println(put);
		System.out.println(put.hasRemaining());
		byte[] array = put.array();
		System.out.println(Arrays.toString(array));
		put.rewind();
		System.out.println(put);
		System.out.println(put.hasRemaining());
//		put.clear();
//		System.out.println("clear之后");
//		System.out.println(put);
//		System.out.println(put.hasRemaining());
		
		
		
//		put.compact();
//		System.out.println("compact之后");
//		System.out.println(put);
//		System.out.println(put.hasRemaining());
		
	}

}
