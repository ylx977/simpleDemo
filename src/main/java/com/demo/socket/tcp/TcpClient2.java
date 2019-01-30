package com.demo.socket.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.http.HttpServlet;

public class TcpClient2 {
	
	
//	public static final String httpRequest = "POST /contractchain/api/login/checkAccount HTTP/1.1\r\nHost: 122.224.124.250:10053\r\nContent-Type: application/json\r\nCache-Control: no-cache\r\nPostman-Token: 23b46fd7-f835-4bdd-b397-81789867772f\r\n\r\n{\r\n    \"accountName\":\"17826852176\",\r\n    \"accountType\":\"1\"\r\n  }";
	
	
	public static void main(String[] args) throws Exception {
		
//		FileInputStream fis = new FileInputStream("D:\\复习知识库\\Socket编程\\httpRequest3");
		FileInputStream fis = new FileInputStream("C:\\Users\\fuzamei\\Desktop\\request2");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fis, Charset.forName("utf-8")));
		String str1 = null;
		StringBuilder sb = new StringBuilder();
		while((str1 = br1.readLine())!=null){
			sb.append(str1);
			sb.append("\r\n");
		}
		
		System.out.println(sb);
		br1.close();
		
//		Socket clientSocket = new Socket("localhost",10001);
		Socket clientSocket = new Socket("172.16.100.31",8080);
//		clientSocket.setKeepAlive(true);
//		for(int i = 0;i<100;i++){
			Thread.sleep(3000);
			System.out.println("连接是否还在："+clientSocket.isConnected());
			OutputStream outputStream = clientSocket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream,Charset.forName("utf-8")));
			bw.write(sb.toString());
			bw.flush();
			
			InputStream inputStream = clientSocket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
			String str = null;
			while((str = br.readLine())!=null){
				//这里br.read()在读完后一直会阻塞，要等1分钟之后服务器断开后才会退出阻塞模式
				System.out.println(str);
			}
			System.out.println("结束一次");
//			byte[] bytes = new byte[102400];
//			int len = -1;
//			while((len = inputStream.read(bytes))!=-1){
//				System.out.println(new String(bytes,0,len,"utf-8"));
//				break;
//			}
			
//		}
		
		clientSocket.close();
		
	}
}
