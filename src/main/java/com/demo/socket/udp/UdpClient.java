package com.demo.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClient {
	public static void main(String[] args) throws Exception {
		DatagramSocket client = new DatagramSocket();
		
		String message = "hello udp?";
		byte[] bytes = message.getBytes();
		InetAddress address = InetAddress.getLocalHost();
		int port = 12321;
		
		DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, port);
		client.send(packet);
		client.close();
	}
}
