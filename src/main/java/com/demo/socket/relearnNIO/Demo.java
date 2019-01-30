package com.demo.socket.relearnNIO;

import java.nio.channels.SelectionKey;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Demo {
	public static void main(String[] args) {
		System.out.println("{\"code\":300,\"success\":false,\"message\":\"Authorization为空\"}".getBytes().length);
		System.out.println("为空".getBytes().length);
		System.out.println((SelectionKey.OP_READ)|(SelectionKey.OP_WRITE));
	}
}
