package com.demo.socket.niosocket;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class TransferUtil {
	
	/**
	 * 将String转换成Byte
	 * @param str
	 * @return
	 */
	public static ByteBuffer getByteBuffer(String str){
		return ByteBuffer.wrap(str.getBytes());
	}
	
	public static String getString(ByteBuffer buffer){
		Charset charset = null;
		CharsetDecoder decoder = null;
		CharBuffer charBuffer = null;
		try {
			charset = Charset.forName("UTF-8");
			decoder = charset.newDecoder();
			//用这个的话，只能输出来一次结果，第二次显示为空
			// charBuffer = decoder.decode(buffer);
			charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
		return charBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	}
	
	
}
