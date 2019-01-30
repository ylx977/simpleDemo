package com.demo.hashUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * SHA加密主要有 SHA1/SHA-1 SHA-224 SHA-256 SHA-384 SHA-512
 * MD加密主要有MD5
 * 
 * 注：所有获取的都是16进制的字符串格式，当然也可以做成base64的字符串格式，根据业务需求做改变即可
 * 
 * @author ylx
 * @Descri 文件hash计算工具类
 */
public final class HashUtil {
	private HashUtil(){
		throw new AssertionError("instantiation is not permitted");
	}
	
	public static final String MD2 = "MD2";
	public static final String MD5 = "MD5";
	public static final String SHA_1 = "SHA-1";
	public static final String SHA_224 = "SHA-224";
	public static final String SHA_256 = "SHA-256";
	public static final String SHA_384 = "SHA-384";
	public static final String SHA_512 = "SHA-512";
	
	/** 
     * 根据文件计算出文件的MD2
     * 文件hash的值和文件名没有关系
     * @param file 
     * @return 
     */  
    public static final String getFileMD2(File file) {  
        return getFileHashByType(file,MD2);
    }
    public static final String getFileMD2(String filePath){
    	return getFileMD2(new File(filePath));
    }
    public static final String getStringMD2(String str){
    	return getStringHashByType(str,MD2);
    }
    /** 
     * 根据文件计算出文件的MD5
     * 文件hash的值和文件名没有关系
     * @param file 
     * @return 
     */  
    public static final String getFileMD5(File file) {  
    	return getFileHashByType(file,MD5);
    }
    public static final String getFileMD5(String filePath){
    	return getFileMD5(new File(filePath));
    }
    public static final String getStringMD5(String str){
    	return getStringHashByType(str,MD5);
    }
    
    /** 
     * 根据文件计算出文件的SHA1
     * 文件hash的值和文件名没有关系
     * @param file 
     * @return 
     */
    public static final String getFileSHA1(File file) {  
    	return getFileHashByType(file,SHA_1);
    }
    public static final String getFileSHA1(String filePath){
    	return getFileSHA1(new File(filePath));
    }
    public static final String getStringSHA1(String str){
    	return getStringHashByType(str,SHA_1);
    }
    
    /** 
     * 根据文件计算出文件的SHA224
     * 文件hash的值和文件名没有关系
     * @param file 
     * @return 
     */
    public static final String getFileSHA224(File file) {  
    	return getFileHashByType(file,SHA_224);
    }
    public static final String getFileSHA224(String filePath){
    	return getFileSHA224(new File(filePath));
    }
    public static final String getStringSHA224(String str){
    	return getStringHashByType(str,SHA_224);
    }
    
    /** 
     * 根据文件计算出文件的SHA384
     * 文件hash的值和文件名没有关系
     * @param file 
     * @return 
     */
    public static final String getFileSHA384(File file) {  
    	return getFileHashByType(file,SHA_384);
    }
    public static final String getFileSHA384(String filePath){
    	return getFileSHA384(new File(filePath));
    }
    public static final String getStringSHA384(String str){
    	return getStringHashByType(str,SHA_384);
    }
    /** 
     * 根据文件计算出文件的SHA512
     * 文件hash的值和文件名没有关系
     * @param file 
     * @return 
     */
    public static final String getFileSHA512(File file) {  
    	return getFileHashByType(file,SHA_512);
    }
    public static final String getFileSHA512(String filePath){
    	return getFileSHA512(new File(filePath));
    }
    public static final String getStringSHA512(String str){
    	return getStringHashByType(str,SHA_512);
    }
    /** 
     * 根据文件计算出文件的SHA224
     * 文件hash的值和文件名没有关系
     * @param file 
     * @return 
     */
    public static final String getFileSHA256(File file) {  
    	return getFileHashByType(file,SHA_256);
    }
    public static final String getFileSHA256(String filePath){
    	return getFileSHA256(new File(filePath));
    }
    public static final String getStringSHA256(String str){
    	return getStringHashByType(str,SHA_256);
    }
    
    
    /** 
     * 根据文件计算出文件的 type类型的签名信息
     * 文件hash的值和文件名没有关系
     * @param file 
     * @return 
     */  
    public static final String getFileHashByType(File file,String type) {  
    	if(!file.exists()){
    		throw new RuntimeException("该文件不存在");
    	}
        if (!file.isFile()) {  
        	throw new RuntimeException("无法对文件夹进行hash计算");
        }  
        MessageDigest digest = null;  
        FileInputStream in = null;  
        byte[] buffer = new byte[1024];  
        int len;  
        try {  
            digest = MessageDigest.getInstance(type);
            in = new FileInputStream(file);  
            while ((len = in.read(buffer)) != -1) {  
                digest.update(buffer, 0, len);  
            }  
        } catch (Exception e) {  
        	throw new RuntimeException("计算文件hash出错:" + e.getMessage());
        }finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					in = null;
				}
			}
		}
        BigInteger bigInt = new BigInteger(1, digest.digest());  
        return bigInt.toString(16);//转换成16进制
    }
    
	
    /** 
     * 根据类型计算字符串的hash值
     * @param str
     * @return 
     */
    public static final String getStringHashByType(String str,String type) {
    	MessageDigest digest = null;  
        try {  
            digest = MessageDigest.getInstance(type);
            digest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {  
        	throw new RuntimeException("计算文件hash出错:" + e.getMessage());
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());  
        return bigInt.toString(16);//转换成16进制
    }
    
    public static void main(String[] args) {
    	String strMd2 = getStringMD2("111");
    	System.out.println(strMd2);
//    	String strMd5 = getStringMD5("111");
//    	System.out.println(strMd5);
//    	String fileMD5 = getFileSHA1(new File("c:/Users/fuzamei/Desktop/image.png"));
//    	String fileMD52 = getFileSHA224(new File("c:/Users/fuzamei/Desktop/image.png"));
//    	String fileMD52 = getFileMD5(new File("c:/Users/fuzamei/Desktop/image.png"));
//    	String fileMD52 = getFileSHA224(new File("c:/Users/fuzamei/Desktop/image.png"));
//    	String fileMD5 = getFileSHA512(new File("c:/Users/fuzamei/Desktop/elasticsearch-6.5.3.zip"));
//    	System.out.println(fileMD52);
//    	System.out.println(fileMD5.equals("10cc7d28a79c26b3fb0e14499a112ddccc5ac4890a913fef1d948a91613e4898c287b3f2a65b7bc9f464784bde99df55b95ebbbc3a6962e81e048b6dc74cc459"));
	}

}
