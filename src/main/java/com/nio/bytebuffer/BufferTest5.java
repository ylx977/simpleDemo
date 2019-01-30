package com.nio.bytebuffer;

import java.nio.ByteBuffer;

public class BufferTest5 {

	public static void main(String[] args) {
		ByteBuffer allocate = ByteBuffer.allocate(100);
		ByteBuffer wrap = allocate.put(new byte[]{1,2,3,4});
		allocate.put(new byte[]{55,66,77,88});
		System.out.println("刚put完数据"+wrap);
		wrap.flip();
		while(wrap.hasRemaining()){
			System.out.println(wrap.get());
			System.out.println(wrap);
		}
		System.out.println(wrap);
//		wrap.rewind();
		wrap.flip();
		System.out.println(wrap);
	}
	
}
