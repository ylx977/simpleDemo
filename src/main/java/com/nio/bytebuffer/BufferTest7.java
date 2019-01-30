package com.nio.bytebuffer;

import java.nio.ByteBuffer;

public class BufferTest7 {

	public static void main(String[] args) {
	    byte[] bytes = new byte[]{'a','b','c','d'};  
	    ByteBuffer buffer = ByteBuffer.allocate(32);
	    buffer.put(bytes);
	    String string = new String(buffer.array());
	    System.out.println(string);
	    System.out.println(string.length());
	    System.out.println(buffer.arrayOffset());
	}
	
}
