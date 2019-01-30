package com.demo.nio2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class BasicChannelExample {

	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("C:\\Users\\fuzamei\\Desktop\\dick.txt", "rw");
		//获取文件的channel
		FileChannel channel = file.getChannel();
		//分配buffer
		ByteBuffer buf = ByteBuffer.allocate(48);
		int read = channel.read(buf);
		byte[] bs = new byte[0];
		byte[] bs2 = new byte[0];
		while(read!=-1){
			buf.flip();
			while(buf.hasRemaining()){
				byte[] temp = new byte[read];
				//将buf中所有的东西装入temp中
				buf.get(temp);
				int sum = bs.length + read;
				byte[] newBs = Arrays.copyOf(bs, sum);
				System.arraycopy(temp, 0, newBs, bs.length, read);
				bs = newBs;
				
				buf.rewind();//rewind表示把原来的buf的positon打回到原来的0，但是其实数据这时候还没有被清除的
				byte[] temp2 = new byte[read];
				//将buf中所有的东西装入temp中
				buf.get(temp2);
				int sum2 = bs2.length + read;
				byte[] newBs2 = Arrays.copyOf(bs2, sum2);
				System.arraycopy(temp2, 0, newBs2, bs2.length, read);
				bs2 = newBs2;
			}
			buf.clear();
//			buf.compact();
			//以下是clear和compact的区别
			//The clear() method clears the whole buffer. 
			//The compact() method only clears the data which you have already read. 
			//Any unread data is moved to the beginning of the buffer, and data will now be written into the buffer after the unread data.
			read = channel.read(buf);
		}
		file.close();
		System.out.println(new String(bs, "gbk"));
		System.out.println(new String(bs2, "gbk"));
	}

}
