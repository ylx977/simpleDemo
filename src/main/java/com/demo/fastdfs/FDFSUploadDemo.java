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

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.mock.web.MockMultipartFile;

/**
 * 详见https://blog.csdn.net/qq_34021712/article/details/70149604
 * @author fuzamei
 * 
 * 上传服务器有个重要的注意事项
 * 如果上传一直失败，首先要看服务器的2个端口是否开放，一个是tracker的22122端口另外一个是storage的23000端口
 * 如果还是上传不了就要改下面
 * /etc/fdfs/storage.conf的tracker_server地址必须是【外网地址】，重启FastDFS就好了。
 *
 */
public class FDFSUploadDemo {
	
	public static void main(String[] args) throws URISyntaxException, IOException, MyException {
		ClientGlobal.init("fdfs_client.conf");
		
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		
		
//		MockMultipartFile mockMultipartFile = new MockMultipartFile("", new FileInputStream(""));
		
		StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
		
		String result = storageClient1.upload_file1("C:\\Users\\fuzamei\\Desktop\\logo.jpg", "jpg", null);
		System.out.println(result);
	}
}
