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
public class FDFSDeletefileDemo {
	
	/** 
     * 删除文件 
     * @param group 组名 如：group1 
     * @param storagePath 不带组名的路径名称 如：M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg 
     * @return -1失败 2表示失败 ,0成功 
     */  
	public static void main(String[] args) throws URISyntaxException, IOException, MyException {
		ClientGlobal.init("fdfs_client.conf");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
		
		String group = "group1";
//		String storagePath = "M00/00/00/wKjFgFsT-AOAUq8hAAIN6x0PjcI899.jpg";
		String storagePath = "M00/00/00/wKjFgFsTxwiAb1GqAABVgpRlWXU643_big.gif";
		
		
		int success = storageClient1.delete_file(group, storagePath);
		
		/**
		 * file_id就是全部路径名称 = group+storagePath
		 * 文件的全部路径 如：group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg 
		 */
//		int success = storageClient1.delete_file1("group1/M00/00/00/wKjFgFsTxwiAb1GqAABVgpRlWXU643_big.gif");
		
		System.out.println(success);
		if(success == -1 || success == 2){
			System.out.println("失败");
		}
		if(success == 0){
			System.out.println("成功");
		}
		
	}
}
