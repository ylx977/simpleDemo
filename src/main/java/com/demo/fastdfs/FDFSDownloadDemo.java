package com.demo.fastdfs;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * 详见https://blog.csdn.net/qq_34021712/article/details/70149604
 * @author fuzamei
 *
 */
public class FDFSDownloadDemo {
	
	 /** 
     * 文件下载到磁盘 
     * @param path 图片路径 
     * @param output 输出流 中包含要输出到磁盘的路径 
     * @return -1失败,0成功 
     */ 
	public static void main(String[] args) throws URISyntaxException, IOException, MyException {
		ClientGlobal.init("fdfs_client.conf");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
		
//		byte[] download_file1 = storageClient1.download_file1("group1/M00/00/00/wKjFgFsT-AOAUq8hAAIN6x0PjcI899.jpg");
		byte[] download_file1 = storageClient1.download_file1("group1/M00/00/00/rBP3EVsWJhOAZahqAEDMkI5TVpQ815.exe");
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("C:\\Users\\fuzamei\\Desktop\\wKjFgFsT-AOAUq8hAAIN6x0PjcI899.exe"));
		bos.write(download_file1);
		bos.close();
		System.out.println("下载成功");
	}
}
