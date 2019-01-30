package com.demo.fastdfs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;

/**
 * fastdfs防盗链的使用
 * filename 是没有group名字的，格式是这样的M00/00/00/wKjFgFsV-LeAJk1pAAIN6x0PjcI453.jpg
 * 防盗链需要在/etc/fdfs/http.conf中修改
 * 
 * 
 
# HTTP default content type
http.default_content_type = application/octet-stream//这个不用管

# MIME types mapping filename
# MIME types file format: MIME_type  extensions
# such as:  image/jpeg  jpeg jpg jpe
# you can use apache's MIME file: mime.types
http.mime_types_filename=mime.types//这个不用管

# if use token to anti-steal
# default value is false (0)
http.anti_steal.check_token=true//这个必须设置为true，然后重启服务生效

# token TTL (time to live), seconds
# default value is 600
http.anti_steal.token_ttl=900//文件访问失效时间，单位为秒

# secret key to generate anti-steal token
# this parameter must be set when http.anti_steal.check_token set to true
# the length of the secret key should not exceed 128 bytes
http.anti_steal.secret_key=FastDFS12345678900//关键的秘钥，生成token时候java端的配置文件中的秘钥要和这个一致

# return the content of the file when check token fail
# default value is empty (no file sepecified)
http.anti_steal.token_check_fail=/home/software/fastdfs/httppic/anti-steal.jpg//如果token错误的话访问默认的图片路径

 * 
 * 
 * 
 * @author fuzamei
 *
 */
public class FDFS_Antisteal {
	public static void main(String[] args) throws NoSuchAlgorithmException, MyException, IOException {
		//首先要初始化下，否则会使用默认的配置，导致secret_key为默认的
		ClientGlobal.init("fdfs_client.conf");
		
		String filename = "M00/00/00/wKjFgFsV-LeAJk1pAAIN6x0PjcI453.jpg";
		
		//ts为秒级别的unix时间戳
		int ts = (int)(System.currentTimeMillis()/1000);
		
		//根据fastdfs提供的方法生成token
		String token = ProtoCommon.getToken(filename, ts , ClientGlobal.getG_secret_key());
		System.out.println(ClientGlobal.getG_secret_key());
		System.out.println(token);
		System.out.println(ts);
		System.out.println("?token="+token+"&ts="+ts);
		
		//将最终的token拼接进去，格式为
		//http://192.168.197.128/group1/M00/00/00/wKjFgFsV-LeAJk1pAAIN6x0PjcI453.jpg?token=f3ee401e942594ed86b26bfb0d249984&ts=1528166866
		System.out.println("http://192.168.197.128/"+"group1/"+filename+"?token="+token+"&ts="+ts);
	}
}
