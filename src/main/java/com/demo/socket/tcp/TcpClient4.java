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

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 * 模拟发送https请求
 * @author fuzamei
 *
 */
public class TcpClient4 {

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("C:\\Users\\fuzamei\\Desktop\\request4");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fis, Charset.forName("utf-8")));
		String str1 = null;
		StringBuilder sb = new StringBuilder();
		while((str1 = br1.readLine())!=null){
			sb.append(str1);
			sb.append("\r\n");
		}
		
		System.out.println(sb);
		br1.close();
		
//		Socket clientSocket = (SSLSocket) ((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket("", 443);
		Socket clientSocket = ((SSLSocketFactory)SSLSocketFactory.getDefault()).createSocket("bpm.h-shgroup.com", 443);
		
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
