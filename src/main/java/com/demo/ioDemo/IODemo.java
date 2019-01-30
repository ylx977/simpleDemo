package com.demo.ioDemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

public class IODemo {
	
	@Test
	public void test01() throws Exception{
//		ByteArrayInputStream bais = new ByteArrayInputStream();
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		baos.to
//		URL url=new URL("http://www.baidu.com");
//		url.op
		MockMultipartFile file1 = new MockMultipartFile("业务审核文件", new FileInputStream("C:\\Users\\fuzamei\\Desktop\\业务审核文件.docx"));
		System.out.println(file1.getName());
		System.out.println(file1.getOriginalFilename());
	}
	
	@Test
	public void test02() throws Exception{
		DataInputStream dis = new DataInputStream(new FileInputStream("C:\\Users\\fuzamei\\Desktop\\业务审核文件.docx"));
		PrintWriter pw =new PrintWriter("C:\\Users\\fuzamei\\Desktop\\test.txt","utf-8");
		pw.println();
		pw.append("你好啊");
		pw.close();
	}
}
