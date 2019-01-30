package com.demo.zipTest;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFinal {
	
	/**
	 * 针对整个文件夹的递归压缩文件
	 * @param srcFile 源文件(包括文件或文件夹)
	 * @param destFile 目标要压缩到的文件
	 * @throws IOException 
	 */
	public static final void zipFile(File srcFile,File destFile) {
		//首先判断文件是否存在
		if(!srcFile.exists()){
			throw new RuntimeException("文件不存在");
		}
		//判断是否是文件夹
		if(srcFile.isDirectory()){//按照文件夹的方式进行压缩处理
			
		}
		//判断是否是单个文件
		if(srcFile.isFile()){//按照文件的方式进行压缩处理
			ZipOutputStream zos=null;
			FileInputStream fis=null;
			try {
				zos = new ZipOutputStream(new FileOutputStream(destFile));
				zos.putNextEntry(new ZipEntry(srcFile.getName()));
				fis = new FileInputStream(srcFile);//源文件流
				byte[] bs = new byte[1024];
				int len = -1;
				while((len=fis.read(bs))!=-1){
					zos.write(bs, 0, len);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}finally{
				if(zos!=null){
					try {
						zos.closeEntry();
					} catch (IOException e) {
						zos=null;
					}
				}
				closeStream(zos,fis);
			}
		}
	}
	
	/**
	 * 针对整个文件夹的递归压缩文件
	 * @param srcFile 源文件(包括文件或文件夹)
	 * @param destFile 目标要压缩到的文件
	 * @throws IOException 
	 */
	private static final void compressDir(File file, ZipOutputStream zos,String basePath) throws IOException{
		if(file.isFile()){//如果是单纯的文件的话
			FileInputStream fis = new FileInputStream(file);
			zos.putNextEntry(new ZipEntry(basePath+file.getName()));
			byte[] bs = new byte[1024];
			int len = -1;
			while((len=fis.read(bs))!=-1){
				zos.write(bs, 0, len);
			}
			zos.closeEntry();
			fis.close();
		}else{//如果是文件夹的话
			File[] listFiles = file.listFiles();
			if(listFiles.length==0){
				zos.putNextEntry(new ZipEntry(basePath+file.getName()));//表示创建空的压缩文件
				zos.closeEntry();
				zos.close();
				return;
			}
			String dirPath = basePath+file.getName()+File.separator;
			for (File commentfile : listFiles) {
				compressDir(commentfile,zos,dirPath);
			}
		}
		
//		File[] listFiles = dir.listFiles();
//		String dirPath = basePath+dir.getName()+File.separator;
//		if(listFiles.length==0){
//			zos.putNextEntry(new ZipEntry(basePath));//表示创建空的压缩文件
//			zos.closeEntry();
//			zos.close();
//			return;
//		}
//		for (File file : listFiles) {
//			if(file.isFile()){
//				FileInputStream fis = new FileInputStream(file);
//				zos.putNextEntry(new ZipEntry(dirPath+file.getName()));
//				byte[] bs = new byte[1024];
//				int len = -1;
//				while((len=fis.read(bs))!=-1){
//					zos.write(bs, 0, len);
//				}
//				zos.closeEntry();
//				fis.close();
//			}else{
//				compressDir(file,zos,dirPath);
//			}
//		}
	}
	
	
	private static final void closeStream(Closeable...closeables){
		for (Closeable closeable : closeables) {
			if(closeable!=null){
				try {
					closeable.close();
				} catch (IOException e) {
					closeable=null;
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
//		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File("C:\\Users\\fuzamei\\Desktop\\api.proto.zip")));
//		
//		zos.putNextEntry(new ZipEntry("dir/"));
//		
//		zos.closeEntry();
////		zos.close();
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File("C:\\Users\\fuzamei\\Desktop\\test.zip")));
		compressDir(new File("C:\\Users\\fuzamei\\Desktop\\test"),zos,"");
		zos.close();
	}
}
