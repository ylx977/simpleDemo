package com.demo.zipTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipFinal {
	
	/**
	 * 解压不需要递归，只要把相应的文件夹给创建出来即可
	 * @param srcZipFilePath
	 * @param destDirPath
	 * @param charset
	 * @throws IOException
	 */
	public static final void unzipFile(String srcZipFilePath,String destDirPath,Charset charset) throws IOException{
		ZipFile zipFile = new ZipFile(srcZipFilePath, charset);//要加上编码，不然会解压失败:etc:Charset.forName("gbk")
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		while (entries.hasMoreElements()) {
			ZipEntry zipEntry = (ZipEntry) entries.nextElement();
			if(zipEntry.isDirectory()){//遇到文件夹就直接进行文件夹创建就可以了
				new File(destDirPath+"/"+zipEntry.getName()).mkdirs();
			}else{
				String name = zipEntry.getName();
				InputStream is = zipFile.getInputStream(zipEntry);
				FileOutputStream fos = new FileOutputStream(destDirPath+"/"+name);
				byte[] bs = new byte[1024];
				int len = -1;
				while((len=is.read(bs))!=-1){
					fos.write(bs, 0, len);
				}
				fos.flush();
				fos.close();
				is.close();
			}
		}
		zipFile.close();
	}
	
	public static void main(String[] args) throws IOException {
		unzipFile("C:\\Users\\fuzamei\\Desktop\\test.zip","C:\\Users\\fuzamei\\Desktop\\hehe",Charset.forName("gbk"));
	}
	
}
