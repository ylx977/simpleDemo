package com.encrypt.symmetrical;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * DES是一种对称加密算法，是一种非常简便的加密算法，但是密钥长度比较短。
 * DES加密算法出自IBM的研究，后来被美国政府正式采用，之后开始广泛流传，
 * 但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，24小时内即可被破解。
 * 虽然如此，在某些简单应用中，我们还是可以使用DES加密算法.简单的DES加密算法实现：
 * @author fuzamei
 *
 */
@Slf4j
public class DES {

	private static final String KEY_ALGORITHM = "DES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";//默认的加密算法
    
    /**
     * DES 加密操作
     *
     * @param content 待加密内容
     * @param key 加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8"); // 将content转化成字节数组
            
            System.out.println("源文件字节长度:"+byteContent.length);

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

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
    public static void encryptFile(File srcFile, File destFile, String key){
    	if(!srcFile.exists()){
    		throw new RuntimeException("源文件不存在");
    	}
    	if(destFile.exists()){
    		throw new RuntimeException("目标文件已经存在");
    	}
    	try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

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
    public static void decryptFile(File srcFile, File destFile, String key){
    	if(!srcFile.exists()){
    		throw new RuntimeException("源文件不存在");
    	}
    	if(destFile.exists()){
    		throw new RuntimeException("目标文件已经存在");
    	}
    	try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器

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
    public static String decrypt(String content, String key) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));

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
    private static SecretKeySpec getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            //DES 要求密钥长度为 56
            kg.init(56, new SecureRandom(key.getBytes()));
            
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为DES专用密钥
//            return new SecretKeySpec(Arrays.copyOf(key.getBytes("utf-8"), 8), KEY_ALGORITHM);// 转换为DES专用密钥（这个是网站的做法，key就是输入的字符串）
        } catch (NoSuchAlgorithmException ex) {
        	ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws Exception {
//        String content = "hello,您好，我是未加密的内容";
    	String content = "hello";
//        String key = "sde@5f98H*^hsff%dfs$r344&df8543*er";
        String key = "keykeykeykeykeykey";
        System.out.println("未DES加密的内容:" + content);
        String s1 = DES.encrypt(content, key);
        System.out.println("DES加密后的内容(用了BASE64编码):" + s1);
        System.out.println("DES解密后的内容:"+ DES.decrypt(s1, key));

//    	encryptFile(new File("C:\\Users\\fuzamei\\Desktop\\aa")
//    			, new File("C:\\Users\\fuzamei\\Desktop\\aa-en"), "keykeykeykeykeykey");
    	File file = new File("C:\\Users\\fuzamei\\Desktop\\aa-en");
    	FileInputStream fileInputStream = new FileInputStream(file);
    	byte[] bs = new byte[fileInputStream.available()];
    	fileInputStream.read(bs);
    	System.out.println(Base64.getEncoder().encodeToString(bs));
    	
//    	decryptFile(new File("C:\\Users\\fuzamei\\Desktop\\Effective.Java.3rd.Edition.2018.1.pdf-en")
//    			, new File("C:\\Users\\fuzamei\\Desktop\\Effective.Java.3rd.Edition.2018.1-2.pdf"), "keykeykeykeykeykey");
    }
	
}
