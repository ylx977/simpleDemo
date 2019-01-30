package com.demo.socket.nio;

import java.nio.IntBuffer;

public class BufferDemo {

	public static void main(String[] args) {
		
		//创建指定长度的缓冲区，这里的10是定义capacity
		IntBuffer buf = IntBuffer.allocate(10);
		buf.put(13);//position为止  0 --> 1
		buf.put(21);//position为止  1 --> 2
		buf.put(35);//position为止  2 --> 3
		buf.flip();//把位置复位成0 也就是position从3 --> 0
		System.out.println("使用flip复位："+buf);
		System.out.println("容量为："+buf.capacity());
		System.out.println("限制为："+buf.limit());
		
		System.out.println("获取下标为0的元素："+buf.get(0));
		System.out.println("获取下标为1的元素："+buf.get(1));
		System.out.println("获取下标为2的元素："+buf.get(2));
//		System.out.println("获取下标为3的元素："+buf.get(3));
		
		buf.put(1, 4);
		
		System.out.println("获取下标为0的元素："+buf.get(0));
		System.out.println("获取下标为1的元素："+buf.get(1));
		System.out.println("获取下标为2的元素："+buf.get(2));
//		System.out.println("获取下标为3的元素："+buf.get(3));
		
		System.out.println("======================");
		for(int i = 0; i < buf.limit(); i++){
			System.out.print(buf.get() + "\t");
		}
		
		System.out.println("遍历完了之后："+buf);
		
		buf.clear();
		
		System.out.println("清空之后："+buf);
	}
	
}
