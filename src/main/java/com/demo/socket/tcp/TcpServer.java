package com.demo.socket.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(12321);
		while(true){
			Socket accept = serverSocket.accept();
			byte[] bys = new byte[10240];
			InputStream inputStream = accept.getInputStream();
			int len = inputStream.read(bys);
			System.out.println(new String(bys,0,len,"utf-8"));
			if(new String(bys,0,len).equalsIgnoreCase("exit")){
				accept.close();
				break;
			}
			accept.close();
		}
		
		serverSocket.close();
	}
}
