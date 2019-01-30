package com.demo.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class TcpServer2 {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8093);
		Socket accept = serverSocket.accept();
		InputStream inputStream = accept.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
		String str = null;
		while((str = br.readLine())!=null){
			System.out.println(str);
		}
		accept.close();
		serverSocket.close();
	}
}
