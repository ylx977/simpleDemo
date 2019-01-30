package com.demo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 8090);
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("hello".getBytes("utf-8"));
		outputStream.flush();
		outputStream.close();
		socket.close();
	}
}
