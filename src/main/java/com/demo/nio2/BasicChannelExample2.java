package com.demo.nio2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class BasicChannelExample2 {

	public static void main(String[] args) throws IOException {
		RandomAccessFile srcFile = new RandomAccessFile("C:\\Users\\fuzamei\\Desktop\\dick.txt", "rw");
		//获取文件的channel
		FileChannel fromChannel = srcFile.getChannel();
		RandomAccessFile destFile = new RandomAccessFile("C:\\Users\\fuzamei\\Desktop\\null.txt", "rw");
		//获取文件的channel
		FileChannel toChannel = destFile.getChannel();
		
		long position = 0;
		long count = fromChannel.size();
//		toChannel.transferFrom(fromChannel, position, count);
		
		fromChannel.transferTo(position, count, toChannel);
		
		/*
		 * 上下两个方法本质都是将src中的内容转移到dest中的文件中
		 */
		
		
		srcFile.close();
		destFile.close();
		fromChannel.close();
		toChannel.close();
	}

}
