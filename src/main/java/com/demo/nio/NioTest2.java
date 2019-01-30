package com.demo.nio;

import java.nio.ByteBuffer;

public class NioTest2 {

	public static void main(String[] args) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(15);
		System.out.println("limit:"+byteBuffer.limit()+"---"+"position:"+byteBuffer.position()+"---"+"capacity:"+byteBuffer.capacity());
		for (int i = 0; i < 10; i++) {
			byteBuffer.put((byte) i);
		}
		System.out.println("存入10个byte");
		System.out.println("limit:"+byteBuffer.limit()+"---"+"position:"+byteBuffer.position()+"---"+"capacity:"+byteBuffer.capacity());
		byteBuffer.flip();//读写转换
		System.out.println("读写转换");
		System.out.println("limit:"+byteBuffer.limit()+"---"+"position:"+byteBuffer.position()+"---"+"capacity:"+byteBuffer.capacity());
		for (int i = 0; i < 5; i++) {
			System.out.println(byteBuffer.get());
		}
		System.out.println("读了5个数据");
		System.out.println("limit:"+byteBuffer.limit()+"---"+"position:"+byteBuffer.position()+"---"+"capacity:"+byteBuffer.capacity());
		byteBuffer.flip();
		System.out.println("limit:"+byteBuffer.limit()+"---"+"position:"+byteBuffer.position()+"---"+"capacity:"+byteBuffer.capacity());
	}

}
