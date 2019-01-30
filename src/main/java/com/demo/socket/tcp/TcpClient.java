package com.demo.socket.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClient {
	public static void main(String[] args) throws Exception {
		Socket clientSocket = new Socket("127.0.0.1",12321);
		while(true){
			System.out.print("请输入信息：");
			Scanner sc = new Scanner(System.in);
			String data = sc.nextLine();
			OutputStream outputStream = clientSocket.getOutputStream();
			outputStream.write(data.getBytes());
			outputStream.flush();
//			if(data.equalsIgnoreCase("exit")){
//				break;
//			}
		}
//		String date = "hello tcp?";
		
//		clientSocket.close();
//		InputStream inputStream = clientSocket.getInputStream();
	}
}
