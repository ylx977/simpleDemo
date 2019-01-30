package com.demo.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {
	public static void main(String[] args) throws IOException {
		DatagramSocket server = new DatagramSocket(12321);
		
		byte[] bys = new byte[1024];
		DatagramPacket packet = new DatagramPacket(bys, bys.length);
		//这里会阻塞的
		System.out.println("blocked");
		server.receive(packet);
		
		String hostAddress = packet.getAddress().getHostAddress();
		System.out.println(hostAddress);
		byte[] data = packet.getData();
		System.out.println("接收到了来自"+hostAddress+"的信息："+new String(data));
		
		server.close();
	}
}
