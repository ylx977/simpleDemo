package com.demo.socket.nio;

import java.nio.IntBuffer;

public class BufferDemo2 {

	public static void main(String[] args) {
		
		int[] arr = new int[]{1,2,5};
		IntBuffer buf = IntBuffer.wrap(arr);
		System.out.println(buf);
		
		IntBuffer buf2 = buf.wrap(arr, 0, 2);
		System.out.println(buf2);
	}
	
}
