package com.nio.bytebuffer;

import java.nio.ByteBuffer;

public class BufferTest4 {

	public static void main(String[] args) {
		//-Xmx:最大堆内存
		//-Xms:初始堆内存
		//-Xmn:年轻代堆内存
		
		
		System.out.println("------before allocate------");
		System.out.println("max-memory:"+Runtime.getRuntime().maxMemory());
		System.out.println("free memory:"+Runtime.getRuntime().freeMemory());
		
		// 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？  
	    // 要超过多少内存大小JVM才能感觉到？  
	    ByteBuffer buffer = ByteBuffer.allocate(102400);  
	    System.out.println("buffer = " + buffer); 
	    
	    System.out.println("after alocate:" + Runtime.getRuntime().freeMemory());
	    
	    
	    
		// 这部分直接用的系统内存，所以对JVM的内存没有影响  
	    ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);  
	    System.out.println("directBuffer = " + directBuffer);  
	    System.out.println("after direct alocate:" + Runtime.getRuntime().freeMemory());  
	    
	    
	    
	    
	}
	
}
