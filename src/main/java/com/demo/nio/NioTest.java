package com.demo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {
	public static void main(String[] args) throws Exception {
		//A FileChannel cannot be set into non-blocking mode. It always runs in blocking mode.
		//记住FileChannel严格来说不是非阻塞的，也就是说他并非真正的NIO，底层还是用inputStream和outputStream
		FileInputStream fis = new FileInputStream("C:\\Users\\fuzamei\\Desktop\\api.proto");
		FileOutputStream fos = new FileOutputStream("C:\\Users\\fuzamei\\Desktop\\api2.proto");
		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		while(true){
			byteBuffer.clear();
			int len = inChannel.read(byteBuffer);//缓冲区此时用于读的时候用
			if(len==-1){
				break;
			}
			byteBuffer.flip();//读写转换，用来将buffer转换成写的时候去用
			
			//Notice how the FileChannel.write() method is called inside a while-loop. 
			//There is no guarantee of how many bytes the write() method writes to the FileChannel. 
			//Therefore we repeat the write() call until the Buffer has no further bytes to write.
			//这里加了一个while循环是确保byteBuffer里面没有残余数据，都被写出去了，只是一种确保措施
			while(byteBuffer.hasRemaining()){
				outChannel.write(byteBuffer);
			}
			
		}
		inChannel.close();
		outChannel.close();
		
	}
}
