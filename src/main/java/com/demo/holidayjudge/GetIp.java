package com.demo.holidayjudge;

import java.net.InetSocketAddress;

public class GetIp {

	public static void main(String[] args) {
		InetSocketAddress inetSocketAddress = new InetSocketAddress("api.goseek.cn", 80);
		System.out.println(inetSocketAddress.getAddress().getHostAddress());
	}
	
}
