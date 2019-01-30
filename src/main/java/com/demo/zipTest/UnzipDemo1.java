package com.demo.zipTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipDemo1 {

	public static void main(String[] args) throws IOException {
		ZipFile zipFile = new ZipFile("C:\\Users\\fuzamei\\Desktop\\ziptest.zip",Charset.forName("gbk"));//要加上编码，不然会解压失败
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		while(entries.hasMoreElements()){
			ZipEntry nextElement = entries.nextElement();
			String name = nextElement.getName();
			InputStream is = zipFile.getInputStream(nextElement);
			FileOutputStream fos = new FileOutputStream("C:\\Users\\fuzamei\\Desktop\\"+name);
			byte[] bs = new byte[1024];
			int len = -1;
			while((len=is.read(bs))!=-1){
				fos.write(bs, 0, len);
			}
			fos.flush();
			fos.close();
		}
		zipFile.close();
	}

}
