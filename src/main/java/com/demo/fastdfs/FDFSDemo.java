package com.demo.fastdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FDFSDemo {
	
	public static void main(String[] args) throws URISyntaxException, IOException {
//		FileInputStream file = new FileInputStream(new File(new URI("http://192.168.197.128/group1/M00/00/00/wKjFgFsTxwiAb1GqAABVgpRlWXU643_big.gif")));
		URL url = new URL("http://192.168.197.128/group1/M00/00/00/wKjFgFsTxwiAb1GqAABVgpRlWXU643_big.gif");
		InputStream file = url.openStream();
		FileOutputStream fout = new FileOutputStream(new File("C:/Users/fuzamei/Desktop/haha.gif"));
		byte[] bs = new byte[1024];
		int count = -1;
		while((count = file.read(bs))!=-1){
			fout.write(bs, 0, count);
		}
		fout.flush();
		fout.close();
		file.close();
	}
}
