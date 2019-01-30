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
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * 详见https://blog.csdn.net/qq_34021712/article/details/70149604
 * @author fuzamei
 *
 */
public class FDFSQueryfileDemo {
	
	/** 
     * 查看文件信息
     */  
	public static void main(String[] args) throws URISyntaxException, IOException, MyException {
		ClientGlobal.init("fdfs_client.conf");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
		
		String group = "group1";
//		String storagePath = "M00/00/00/wKjFgFsT-AOAUq8hAAIN6x0PjcI899.jpg";
		String storagePath = "M00/00/00/wKjFgFsT356AXkXHAAAALS2GKDE738_big.txt";
		
		
		FileInfo file_info1 = storageClient1.get_file_info1(group+"/"+storagePath);
		
		//打印出来是这样的：source_ip_addr = 192.168.197.128, file_size = 45, create_timestamp = 2018-06-03 20:31:26, crc32 = 763766833
		System.out.println(file_info1);
		
	}
}
