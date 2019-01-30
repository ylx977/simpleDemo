package com.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 对称加密的汇总工具类
 * @author fuzamei
 *
 */
@Slf4j
public final class SymmetricEncryptUtils {

	private SymmetricEncryptUtils(){
		throw new AssertionError("instantiation is not permitted");
	}
	
	
	/**
     * DES 加密操作
     *
     * @param content 待加密内容
     * @param key 加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key, Mode mode) {
    	log.info("当前加密模式为{}",mode.getPattern());
        try {
            Cipher cipher = Cipher.getInstance(mode.getPattern());// 创建密码器

            byte[] byteContent = content.getBytes("utf-8"); // 将content转化成字节数组
            
            System.out.println("源文件字节长度:"+byteContent.length);

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key,mode));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密
            
            System.out.println("加密后字节长度:"+result.length);

            return Base64.getEncoder().encodeToString(result);//通过Base64转码返回
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    /**
     * 对文件进行加密
     * @param srcFile
     * @param destFile
     * @param key
     */
    public static void encryptFile(File srcFile, File destFile, String key, Mode mode){
    	log.info("当前加密模式为{}",mode.getPattern());
    	if(!srcFile.exists()){
    		throw new RuntimeException("源文件不存在");
    	}
    	if(destFile.exists()){
    		throw new RuntimeException("目标文件已经存在");
    	}
    	try {
            Cipher cipher = Cipher.getInstance(mode.getPattern());// 创建密码器

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key,mode));// 初始化为加密模式的密码器

            FileInputStream fileInputStream = new FileInputStream(srcFile);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(new FileOutputStream(destFile), cipher);

            byte[] buffer = new byte[1024];
            int len = -1;
            while((len = fileInputStream.read(buffer))!=-1){
            	cipherOutputStream.write(buffer, 0, len);
            }
            
            fileInputStream.close();
            cipherOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    /**
     * 对文件进行解密
     * @param srcFile
     * @param destFile
     * @param key
     */
    public static void decryptFile(File srcFile, File destFile, String key, Mode mode){
    	log.info("当前解密模式为{}",mode.getPattern());
    	if(!srcFile.exists()){
    		throw new RuntimeException("源文件不存在");
    	}
    	if(destFile.exists()){
    		throw new RuntimeException("目标文件已经存在");
    	}
    	try {
            Cipher cipher = Cipher.getInstance(mode.getPattern());// 创建密码器

            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key,mode));// 初始化为加密模式的密码器

            CipherInputStream cipherInputStream = new CipherInputStream(new FileInputStream(srcFile),cipher);
            FileOutputStream fileOutputStream = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int len = -1;
            while((len = cipherInputStream.read(buffer))!=-1){
            	fileOutputStream.write(buffer, 0, len);
            }
            
            cipherInputStream.close();
            fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * DES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key,Mode mode) {
    	log.info("当前解密模式为{}",mode.getPattern());
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(mode.getPattern());

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key,mode));

            //执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
        	ex.printStackTrace();
        }

        return null;
    }

    /**
     * 生成加密秘钥
     * final String key 可以是随机的字符串，长度无所谓
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key,final Mode mode) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(mode.getName());
            if(mode.getKeyLen() != 0){
            	kg.init(mode.getKeyLen(), new SecureRandom(key.getBytes()));
            }else{
            	kg.init(new SecureRandom(key.getBytes()));
            }
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), mode.getName());
        } catch (NoSuchAlgorithmException ex) {
        	ex.printStackTrace();
        }
        return null;
    }
	
	@Getter
	public static enum Mode{
		DES_ECB_PKCS5Padding("DES","DES/ECB/PKCS5Padding",56),
		DESede_ECB_PKCS5Padding("DESede","DESede/ECB/PKCS5Padding",0),
		AES_ECB_PKCS5Padding("AES","AES/ECB/PKCS5Padding",128);
		
		Mode(String name,String pattern,int keyLen){
			this.name = name;
			this.pattern = pattern;
			this.keyLen = keyLen;
		}
		//加密名字
		private String name;
		//加密模式
		private String pattern;
		//秘钥长度
		private int keyLen;
	}
	
}
