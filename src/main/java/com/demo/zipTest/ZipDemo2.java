package com.demo.zipTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDemo2 {

	/**
	 * 将一个文件压缩到一个压缩文件中去
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\fuzamei\\Desktop\\api.proto");
		
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File("C:\\Users\\fuzamei\\Desktop\\api.proto.zip")));
		
		zos.putNextEntry(new ZipEntry("dir/"+file.getName()));//将文件信息写入Zipentry中（如果不加"dir/就是不带文件夹的压缩文件"）
		
		FileInputStream fis = new FileInputStream(file);//源文件流
		
		byte[] bs = new byte[1024];
		
		int len = -1;
		
		while((len=fis.read(bs))!=-1){
			zos.write(bs, 0, len);
		}
		zos.closeEntry();
		zos.close();
		fis.close();
	}

}
