package com.nio.bytebuffer;

import java.nio.IntBuffer;
import java.util.Arrays;

public class BufferTest {
	
	public static void main(String[] args) {
		//创建指定长度的缓冲区
		IntBuffer buffer = IntBuffer.allocate(10);
		
		int[] array = new int[]{3,5,1};
		
		//使用数组来创建一个缓冲区视图
		buffer = buffer.wrap(array);
		
		//使用数组的某一个区间来创建一个缓冲区视图
//		buffer = buffer.wrap(array, 0, 2);
		
		//对缓冲区某个位置上进行元素的修改
		buffer.put(0,7);
		
		//遍历缓冲区中的数据
		System.out.println("缓冲区中的数据如下：");
		
		for(int i = 0;i<buffer.limit();i++){
			System.out.println(buffer.get() + "\t");
		}
		//原来的数组也被改变了
		System.out.println(Arrays.toString(array));
		
//		buffer.flip();//对缓冲区进行反转，（limit=pos;pos=0）
//		buffer.clear();
		
		System.out.println(buffer);
		
		
		IntBuffer duplicate = buffer.duplicate();
		System.out.println(duplicate);
	}

}
