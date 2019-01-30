package com.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class SocketServerDemo {
	
	/**
	 * 这个是服务端的socket代码
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(8090);
		Socket accept = socket.accept();//这段代码会堵塞
		System.out.println("堵塞2");
		InputStream inputStream = accept.getInputStream();
		int len=-1;
		byte[] bs = new byte[1024];
		StringBuilder sb = new StringBuilder();
		while((len=inputStream.read(bs))!=-1){
			byte[] temp = new byte[len];
			System.arraycopy(bs, 0, temp, 0, len);
			sb.append(new String(bs, "utf-8"));
		}
		System.out.println(sb);
		socket.close();
	}
}
