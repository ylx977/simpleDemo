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

public class TcpClient3 {
	
	
	public static final String httpRequest = "POST /contractchain/api/login/checkAccount HTTP/1.1\r\nHost: 122.224.124.250:10053\r\nContent-Type: application/json\r\nCache-Control: no-cache\r\nPostman-Token: 23b46fd7-f835-4bdd-b397-81789867772f\r\n\r\n{\r\n    \"accountName\":\"17826852176\",\r\n    \"accountType\":\"1\"\r\n  }";
	
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fis = new FileInputStream("D:\\复习知识库\\Socket编程\\httpRequest2");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fis, Charset.forName("utf-8")));
		String str1 = null;
		StringBuilder sb = new StringBuilder();
		while((str1 = br1.readLine())!=null){
			sb.append(str1);
			sb.append("\r\n");
		}
		
		System.out.println(sb);
		br1.close();
		
		Socket clientSocket = new Socket("127.0.0.1",8093);
		OutputStream outputStream = clientSocket.getOutputStream();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream,Charset.forName("utf-8")));
		bw.write(sb.toString());
		bw.flush();
		
		InputStream inputStream = clientSocket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
		String str = null;
		while((str = br.readLine())!=null){
			System.out.println(str);
		}
		
		clientSocket.close();
		
	}
}
