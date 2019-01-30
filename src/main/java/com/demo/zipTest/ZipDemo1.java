package com.demo.zipTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDemo1 {
	public static void main(String[] args) throws Exception {
		//说明：文件夹下多个文件的压缩，将多个文件压缩到一个压缩包中
		
		File srcfile = new File("C:\\Users\\fuzamei\\Desktop\\ziptest");//该路径为文件夹，文件夹下面是多个文件(源文件是多个文件的文件夹)
		File[] listFiles = srcfile.listFiles();
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File("C:\\Users\\fuzamei\\Desktop\\ziptest.zip")));//这里解压到的路径可以是rar结尾，也可以是zip结尾
		for (File commonfile : listFiles) {
			FileInputStream fis = new FileInputStream(commonfile);
			byte[] bs = new byte[1024];
			int len = -1;
			zos.putNextEntry(new ZipEntry(commonfile.getName()));
			while((len=fis.read(bs))!=-1){
				zos.write(bs, 0, len);
			}
			zos.closeEntry();//这里是closeEntry
			fis.close();
		}
		zos.close();//循环结束将整个zos的流关闭
	}
}
