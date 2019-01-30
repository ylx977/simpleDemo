package com.demo.hmac;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 注意Base64如果在web上进行传输，需要使用
 * Base64.getUrlDecoder();
 * Base64.getUrlEncoder().withoutPadding();
 * 
 * getUrlEncoder会将 / 转化为 _   还有将 + 转化为-
 * withoutPadding会将base64尾部的 = 都去除
 * 
 * @author fuzamei
 *
 */
public final class HmacUtil {
	
	private HmacUtil(){
		throw new AssertionError("instantiation is not permitted");
	}
	
	/**
	 * Mac mac = Mac.getInstance("HmacMD5");//16(hash后字节长度)
	 */
	public static final String HMAC_MD5 = "HmacMD5";
	
	/**
	 * Mac mac = Mac.getInstance("HmacSHA1");//20(hash后字节长度)
	 */
	public static final String HMAC_SHA1 = "HmacSHA1";
	
	/**
	 * Mac mac = Mac.getInstance("HmacSHA224");//28(hash后字节长度)
	 */
	public static final String HMAC_SHA224 = "HmacSHA224";
	
	/**
	 * Mac mac = Mac.getInstance("HmacSHA256");//32(hash后字节长度)
	 */
	public static final String HMAC_SHA256 = "HmacSHA256";
	
	/**
	 * Mac mac = Mac.getInstance("HmacSHA384");//48(hash后字节长度)
	 */
	public static final String HMAC_SHA384 = "HmacSHA384";
	
	/**
	 * Mac mac = Mac.getInstance("HmacSHA512");//64(hash后字节长度)
	 */
	public static final String HMAC_SHA512 = "HmacSHA512";
	
	public static String getHmacMD5HexString(String src, String key){
		return getHexStringHashByType(src,key,HMAC_MD5);
	}
	
	public static String getHmacMD5Base64String(String src, String key,boolean urlEncoded){
		return getBase64StringHashByType(src,key,HMAC_MD5,urlEncoded);
	}
	public static String getHmacSHA1HexString(String src, String key){
		return getHexStringHashByType(src,key,HMAC_SHA1);
	}
	
	public static String getHmacSHA1Base64String(String src, String key,boolean urlEncoded){
		return getBase64StringHashByType(src,key,HMAC_SHA1,urlEncoded);
	}
	public static String getHmacSHA224HexString(String src, String key){
		return getHexStringHashByType(src,key,HMAC_SHA224);
	}
	
	public static String getHmacSHA224Base64String(String src, String key,boolean urlEncoded){
		return getBase64StringHashByType(src,key,HMAC_SHA224,urlEncoded);
	}
	public static String getHmacSHA256HexString(String src, String key){
		return getHexStringHashByType(src,key,HMAC_SHA256);
	}
	
	public static String getHmacSHA256Base64String(String src, String key,boolean urlEncoded){
		return getBase64StringHashByType(src,key,HMAC_SHA256,urlEncoded);
	}
	public static String getHmacSHA384HexString(String src, String key){
		return getHexStringHashByType(src,key,HMAC_SHA384);
	}
	
	public static String getHmacSHA384Base64String(String src, String key,boolean urlEncoded){
		return getBase64StringHashByType(src,key,HMAC_SHA384,urlEncoded);
	}
	public static String getHmacSHA512HexString(String src, String key){
		return getHexStringHashByType(src,key,HMAC_SHA512);
	}
	
	public static String getHmacSHA512Base64String(String src, String key,boolean urlEncoded){
		return getBase64StringHashByType(src,key,HMAC_SHA512,urlEncoded);
	}
	
	
	
	public static String getFileHmacMD5HexString(File srcFile, String key){
		return getHexFileHashByType(srcFile,key,HMAC_MD5);
	}
	
	public static String getFileHmacMD5Base64String(File srcFile, String key){
		return getBase64FileHashByType(srcFile,key,HMAC_MD5);
	}
	public static String getFileHmacSHA1HexString(File srcFile, String key){
		return getHexFileHashByType(srcFile,key,HMAC_SHA1);
	}
	
	public static String getFileHmacSHA1Base64String(File srcFile, String key){
		return getBase64FileHashByType(srcFile,key,HMAC_SHA1);
	}
	public static String getFileHmacSHA224HexString(File srcFile, String key){
		return getHexFileHashByType(srcFile,key,HMAC_SHA224);
	}
	
	public static String getFileHmacSHA224Base64String(File srcFile, String key){
		return getBase64FileHashByType(srcFile,key,HMAC_SHA224);
	}
	public static String getFileHmacSHA256HexString(File srcFile, String key){
		return getHexFileHashByType(srcFile,key,HMAC_SHA256);
	}
	
	public static String getFileHmacSHA256Base64String(File srcFile, String key){
		return getBase64FileHashByType(srcFile,key,HMAC_SHA256);
	}
	public static String getFileHmacSHA384HexString(File srcFile, String key){
		return getHexFileHashByType(srcFile,key,HMAC_SHA384);
	}
	
	public static String getFileHmacSHA384Base64String(File srcFile, String key){
		return getBase64FileHashByType(srcFile,key,HMAC_SHA384);
	}
	public static String getFileHmacSHA512HexString(File srcFile, String key){
		return getHexFileHashByType(srcFile,key,HMAC_SHA512);
	}
	
	public static String getFileHmacSHA512Base64String(File srcFile, String key){
		return getBase64FileHashByType(srcFile,key,HMAC_SHA512);
	}
	
	
	/**
	 * 获取字符串的16进制字符串的hash值
	 * @param src
	 * @param key
	 * @param type
	 * @return
	 */
	private static String getHexStringHashByType(String src, String key, String type){
		try {
			byte[] digest = getHashByType(src.getBytes("utf-8"),key.getBytes("utf-8"),type);
			return new BigInteger(1, digest).toString(16);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("字符串编码类型不支持:" + e.getMessage());
		}
	}
	
	/**
	 * 获取字符串的base64编码的hash值
	 * @param src
	 * @param key
	 * @param type
	 * @return
	 */
	private static String getBase64StringHashByType(String src, String key, String type, boolean urlEncoded){
		try {
			byte[] digest = getHashByType(src.getBytes("utf-8"),key.getBytes("utf-8"),type);
			return urlEncoded ? Base64.getUrlEncoder().withoutPadding().encodeToString(digest) : 
						Base64.getEncoder().encodeToString(digest);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("字符串编码类型不支持:" + e.getMessage());
		}
	}
	
	/**
	 * 对不是特别大的字符串进行hash运算
	 * @param src
	 * @param key
	 * @param type
	 * @return
	 */
	public static byte[] getHashByType(byte[] src, byte[] key, String type){
		if(type == null || "".equals(type.trim())){
			throw new RuntimeException("类型不能为空");
		}
		try {
			Mac mac = Mac.getInstance(type);
			SecretKeySpec secret = new SecretKeySpec(key, mac.getAlgorithm());
			mac.init(secret);
			return mac.doFinal(src);
		} catch (InvalidKeyException e) {
			throw new RuntimeException("密钥无效:" + e.getMessage());
		} catch (IllegalStateException e) {
			throw new RuntimeException("状态非法:" + e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("hash算法类型不支持:" + e.getMessage());
		}
	}
	
	/**
	 * 获取文件的16进制hash值
	 * @param srcFile
	 * @param key
	 * @param type
	 * @return
	 */
	private static String getHexFileHashByType(File srcFile, String key, String type){
		if(type == null || "".equals(type.trim())){
			throw new RuntimeException("类型不能为空");
		}
		try {
			byte[] digest = getFileHashByType(srcFile,key.getBytes("utf-8"),type);
			return new BigInteger(1, digest).toString(16);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("字符串编码类型不支持:" + e.getMessage());
		}
	}
	
	/**
	 * 获取文件的Base64hash值
	 * @param srcFile
	 * @param key
	 * @param type
	 * @return
	 */
	private static String getBase64FileHashByType(File srcFile, String key, String type){
		try {
			byte[] digest = getFileHashByType(srcFile,key.getBytes("utf-8"),type);
			return Base64.getEncoder().encodeToString(digest);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("字符串编码类型不支持:" + e.getMessage());
		}
	}
	
	/**
	 * 大文件或者含有大字符串的都可以采用这个方式进行hash计算
	 * @param srcFile
	 * @param key
	 * @param type
	 * @return
	 */
	private static byte[] getFileHashByType(File srcFile,byte[] key ,String type){
		if(!srcFile.exists()){
    		throw new RuntimeException("该文件不存在");
    	}
        if (!srcFile.isFile()) {  
        	throw new RuntimeException("无法对文件夹进行hash计算");
        }
        FileInputStream in = null;
		try {
			Mac mac = Mac.getInstance(type);
			SecretKeySpec secret = new SecretKeySpec(key, mac.getAlgorithm());
			mac.init(secret);
			in = new FileInputStream(srcFile);  
			byte[] buffer = new byte[1024];  
			int len;
			while((len = in.read(buffer))!=-1){
				mac.update(buffer, 0, len);
			}
			return mac.doFinal();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("hash算法类型不支持:" + e.getMessage());
		} catch (FileNotFoundException e) {
			throw new RuntimeException("该文件不存在:" + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException("文件IO读取异常:" + e.getMessage());
		} catch (InvalidKeyException e) {
			throw new RuntimeException("密钥无效:" + e.getMessage());
		}finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					in = null;
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
//		String fileHmacMD5Base64String = getFileHmacSHA512Base64String(new File("C:\\Users\\fuzamei\\Desktop\\elasticsearch-analysis-ik-6.5.3.zip"), "keykey");
//		System.out.println(fileHmacMD5Base64String);
//		String fileHmacMD5HexString = getFileHmacSHA512HexString(new File("C:\\Users\\fuzamei\\Desktop\\elasticsearch-analysis-ik-6.5.3.zip"), "keykey");
//		System.out.println(fileHmacMD5HexString);
	}
	
}
