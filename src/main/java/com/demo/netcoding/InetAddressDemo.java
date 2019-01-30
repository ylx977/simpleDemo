package com.demo.netcoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class InetAddressDemo {
	
	
	public static void main(String[] args) throws IOException {
//		InetAddress[] allByName = InetAddress.getAllByName("www.baidu.com");
//		for (int i = 0; i < allByName.length; i++) {
//			System.out.println(allByName[i]);
//		}
		
//		InetAddress byName = InetAddress.getByName("115.239.211.112");
//		System.out.println(byName.getCanonicalHostName());
//		System.out.println(byName.isReachable(100));
		
//		InetAddress byAddress = InetAddress.getByAddress(new byte[]{115,(byte)239,(byte)211,112});
//		System.out.println(byAddress);
		
//		URL url = new URL("https://www.baidu.com");
//		String file = url.getFile();
//		System.out.println(file);
//		System.out.println(url.getAuthority());
//		System.out.println(url.getContent());
//		System.out.println(url.getDefaultPort());
//		System.out.println(url.getPath());
//		System.out.println(url.getProtocol());
//		System.out.println(url.getQuery());
//		
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
//		String content = null;
//		StringBuilder sb = new StringBuilder();
//		while((content = bufferedReader.readLine())!=null){
//			sb.append(content);
//		}
//		bufferedReader.close();
//		System.out.println(content);
		
		System.out.println(Integer.toBinaryString('1'+0));
		System.out.println(Integer.toBinaryString('h'+0));
		System.out.println(Integer.toBinaryString('o'+0));
		System.out.println(Integer.toBinaryString('n'+0));
		System.out.println(Integer.toBinaryString('e'+0));
	}
	
	
}
