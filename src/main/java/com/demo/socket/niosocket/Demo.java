package com.demo.socket.niosocket;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

public class Demo {
	public static void main(String[] args) throws CharacterCodingException {
		String str = "这是来客户端的文字";
		ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
		
		System.out.println(TransferUtil.getString(buffer));
		
	}
}
