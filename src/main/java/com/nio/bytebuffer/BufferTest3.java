package com.nio.bytebuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class BufferTest3 {
	
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\fuzamei\\Desktop\\elasticsearch-analysis-ik-6.5.3.zip"));
		FileOutputStream fos = new FileOutputStream(new File("G:\\JAVA编程常用工具\\elasticsearch\\ik\\elasticsearch-analysis-ik-6.5.3.zip"));
		ByteBuffer bf = ByteBuffer.allocate(1024);
		FileChannel inchannel = fis.getChannel();
		FileChannel outchannel = fos.getChannel();
		int read = inchannel.read(bf);
		while(read!=-1){
			bf.flip();
			outchannel.write(bf);
			bf.clear();
			read = inchannel.read(bf);
		}
		inchannel.close();
		outchannel.close();
		System.out.println("文件传输完成");
	}

}
