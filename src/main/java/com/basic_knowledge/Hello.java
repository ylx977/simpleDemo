package com.basic_knowledge;

import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sun.misc.Unsafe;

public class Hello {
	
	static void add(int a,int b) {
		
	}
	static void add(byte a,byte b) {
		
	}
	static void add(short a,short b) {
		
	}
	static void add(double a,double b) {
		
	}
	static void add(float a,float b) {
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println(args.length);
		System.out.println(Arrays.toString(args));
		System.out.println("helloworld");
		add('a','b');
		X x = X.A;
		switch (x) {
		case A:
			
			break;
		case B:
			
			break;

		default:
			break;
		}
		
		
//		AtomicInteger ai = new AtomicInteger(0);
//		AtomicStampedReference<Integer> asf = new AtomicStampedReference<Integer>(0, 0);
//		AtomicReference<Thread> ar = new AtomicReference<Thread>();
//		OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
//	            .readTimeout(1, TimeUnit.HOURS)
//	            .connectTimeout(30,TimeUnit.SECONDS)
//	            .retryOnConnectionFailure(false)
//	            .build();
//		
//		for(int i = 0;i<100;i++) {
//			new Thread(()->{
//				for(int j = 0;j<100;j++) {
//					Request request = new Request.Builder()
//							.url("http://172.16.100.16:8888/getInfo")
//							.get()
//							.build();
//					
//					Response response;
//					try {
//						response = HTTP_CLIENT.newCall(request).execute();
//						Thread.sleep(2);
//						System.out.println(response.body().string());
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}).start();
//		}
		
		
		System.out.println(Integer.toBinaryString(Float.floatToIntBits(0.1f)));
		System.out.println(Long.toBinaryString(Double.doubleToLongBits(0.1111111111d)));
		System.out.println(Arrays.toString("你好啊".getBytes("utf-8")));
		System.out.println(Arrays.toString("你好啊".getBytes("GBK")));
		System.out.println((int)'你' + "->" + Integer.toBinaryString(20320));
		System.out.println((int)'好' + "->" + Integer.toBinaryString(22909));
		System.out.println((int)'啊' + "->" + Integer.toBinaryString(21834));
		System.out.println((byte)160);
		System.out.println((byte)189);
		
		Child c = new Child();
		c.template();
	}
}
enum X{
	A,B,C,D,E;
}

interface Face{
	
}

class Parent{
	protected void test1() {
		
	}
	protected void test2() {
		
	}
	/**
	 * 这是一个模板方法
	 */
	public final void template() {
		System.out.println("使用test1");
		test1();
		System.out.println("使用test2");
		test2();
	}
}
class Child extends Parent {
	@Override
	protected void test1() {
		System.out.println("我是test1方法");
	}
	@Override
	protected void test2() {
		System.out.println("我是test2方法");
	}
}