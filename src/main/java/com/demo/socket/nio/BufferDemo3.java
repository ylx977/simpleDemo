package com.demo.socket.nio;

import java.nio.IntBuffer;

public class BufferDemo3 {

	public static void main(String[] args) {
		
		IntBuffer buf = IntBuffer.allocate(10);
		int[] arr = new int[]{1,2,5};
		buf.put(arr);
		System.out.println(buf);
		IntBuffer duplicate = buf.duplicate();
		System.out.println(duplicate);
		
		duplicate.position(1);
		System.out.println(duplicate);
		
		int[] arr2 = new int[duplicate.remaining()];
		duplicate.get(arr2);
		for (int i : arr2) {
			System.out.print(Integer.toString(i)+",");
		}
		
	}
	
}
