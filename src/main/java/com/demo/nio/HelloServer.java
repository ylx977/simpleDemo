package com.demo.nio;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HelloServer {
	
	public static void main(String[] args) throws UnknownHostException {
		InetAddress add = InetAddress.getByName("IP");
		System.out.println(add);
	}
}
